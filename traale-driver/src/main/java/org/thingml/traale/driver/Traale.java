/**
 * Copyright (C) 2012 SINTEF <franck.fleurey@sintef.no>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.traale.driver;

import java.util.ArrayList;
import org.thingml.bglib.BGAPIDefaultListener;
import org.thingml.bglib.BGAPI;
import org.thingml.rtsync.core.TimeSynchronizable;
import org.thingml.rtsync.core.TimeSynchronizer;
//import org.thingml.bglib.samples.ByteUtils;

/**
 *
 * @author ffl
 */
public class Traale extends BGAPIDefaultListener implements TimeSynchronizable {
    
    
    private final int DEFAULT_SUB = 0x01; // 0x01 for notifications and 0x02 for indications
    
    private ArrayList<TraaleListener> listeners = new ArrayList<TraaleListener>();
    
    public synchronized void addTraaleListener(TraaleListener l) {
        listeners.add(l);
    }
    
    public synchronized void removeTraaleListener(TraaleListener l) {
        listeners.remove(l);
    }
    
    protected BGAPI bgapi;
    protected int connection;
    
    // 16 bits timestamps with a 4ms resolution -> 18bits timestamps in ms -> Max value = 0x3FFF
    private TimeSynchronizer rtsync = new TimeSynchronizer(this, 0x03FFFF);

    public TimeSynchronizer getTimeSynchronizer() {
        return rtsync;
    }
    
    public long getEpochTimestamp(int ts) {
        if (rtsync.isRunning()) return rtsync.getSynchronizedEpochTime(ts);
        else return 0;
    }
    
    public Traale(BGAPI bgapi, int connection) {
        this.bgapi = bgapi;
        this.connection = connection;
        bgapi.addListener(this);
    }
    
    public void disconnect() {
        bgapi.removeListener(this);
    }
    
    public void startTimeSync() {
        subscribeTimeSync();
        rtsync.start_timesync();
    }
    
    public void stopTimeSync() {
        unsubscribeTimeSync();
        rtsync.stop_timesync();
    }
    
    public static final int ALERT_LEVEL = 0x4B;
    
    public void readAlertLevel() {
        bgapi.send_attclient_read_by_handle(connection, ALERT_LEVEL);
    }
    
    public void setAlertLevel(int value) {
        byte[] i = new byte[1];
        i[0] = (byte)(value & 0xFF);
        bgapi.send_attclient_write_command(connection, ALERT_LEVEL, i);
    }
    
    /**************************************************************
     * Skin Temperature
     **************************************************************/ 
    public static final int THERMOMETER_VALUE = 0x1F;
    public static final int THERMOMETER_CONFIG = 0x20;
    public static final int THERMOMETER_INTERVAL = 0x24;
    
    public void subscribeSkinTemperature() {
        bgapi.send_attclient_write_command(connection, THERMOMETER_CONFIG, new byte[]{0x02, 0x00});
    }
    
    public void unsubscribeSkinTemperature() {
        bgapi.send_attclient_write_command(connection, THERMOMETER_CONFIG, new byte[]{0x00, 0x00});
    }
    
    public void readSkinTemperatureInterval() {
        bgapi.send_attclient_read_by_handle(connection, THERMOMETER_INTERVAL);
    }
    
    public void setSkinTemperatureInterval(int value) {
        byte[] i = new byte[2];
        i[1] = (byte)((value>>8) & 0xFF);
        i[0] = (byte)(value & 0xFF);
        bgapi.send_attclient_write_command(connection, THERMOMETER_INTERVAL, i);
    }
    
    private synchronized void skinTemperature(byte[] value) {
        
        int ts = ((value[6] & 0xFF) << 8) + (value[5] & 0xFF);
        
        for (TraaleListener l : listeners) {
            l.skinTemperature(getTemperature(value), ts*4);
        }
    }
    private synchronized void skinTemperatureInterval(byte[] value) {
        for (TraaleListener l : listeners) {
            l.skinTemperatureInterval((value[1]<<8) + (value[0] & 0xFF));
        }
    }
    
    /**************************************************************
     * Humidity
     **************************************************************/ 
    public static final int HUMIDITY_VALUE = 0x2C;
    public static final int HUMIDITY_CONFIG = 0x2D;
    public static final int HUMIDITY_INTERVAL = 0x2F;
    
    public void subscribeHumidity() {
        bgapi.send_attclient_write_command(connection, HUMIDITY_CONFIG, new byte[]{0x01, 0x00});
    }
    
    public void unsubscribeHumidity() {
        bgapi.send_attclient_write_command(connection, HUMIDITY_CONFIG, new byte[]{0x00, 0x00});
    }
    
    public void readHumidityInterval() {
        bgapi.send_attclient_read_by_handle(connection, HUMIDITY_INTERVAL);
    }
    
    public void setHumidityInterval(int value) {
        byte[] i = new byte[2];
        i[1] = (byte)((value>>8) & 0xFF);
        i[0] = (byte)(value & 0xFF);
        bgapi.send_attclient_write_command(connection, HUMIDITY_INTERVAL, i);
    }
    
    private synchronized void humidity(byte[] value) {
        int t1 = ((value[1] & 0xFF) << 8) + (value[0] & 0xFF); if (t1 > (1<<14)) { t1 = t1 - (1<<15); }
        int h1 = ((value[3] & 0xFF) << 8) + (value[2] & 0xFF);
        int t2 = ((value[5] & 0xFF) << 8) + (value[4] & 0xFF); if (t2 > (1<<14)) { t2 = t2 - (1<<15); }
        int h2 = ((value[7] & 0xFF) << 8) + (value[6] & 0xFF);
        
        int ts = ((value[9] & 0xFF) << 8) + (value[8] & 0xFF);
        
        for (TraaleListener l : listeners) {
            l.humidity(t1, h1, t2, h2, ts*4);
        }
    }
    private synchronized void humidityInterval(byte[] value) {
        for (TraaleListener l : listeners) {
            l.humidityInterval((value[1]<<8) + (value[0] & 0xFF));
        }
    }
    
    
    /**************************************************************
     * IMU
     **************************************************************/ 
    public static final int IMU_VALUE = 0x33;
    public static final int IMU_CONFIG = 0x34;
    
    public static final int QUAT_VALUE = 0x36;
    public static final int QUAT_CONFIG = 0x37;
    
    public static final int IMU_MODE = 0x42;
    
    public static final int IMU_INTERRUPT_VALUE = 0x3F;
    public static final int IMU_INTERRUPT_CONFIG = 0x40;
    
    public void subscribeIMU() {
        bgapi.send_attclient_write_command(connection, IMU_CONFIG, new byte[]{DEFAULT_SUB, 0x00});
    }
    
    public void unsubscribeIMU() {
        bgapi.send_attclient_write_command(connection, IMU_CONFIG, new byte[]{0x00, 0x00});
    }
    
    public void subscribeQuaternion() {
        bgapi.send_attclient_write_command(connection, QUAT_CONFIG, new byte[]{DEFAULT_SUB, 0x00});
    }
    
    public void unsubscribeQuaternion() {
        bgapi.send_attclient_write_command(connection, QUAT_CONFIG, new byte[]{0x00, 0x00});
    }
    
    public void subscribeIMUInterrupt() {
        bgapi.send_attclient_write_command(connection, IMU_INTERRUPT_CONFIG, new byte[]{0x01, 0x00});
    }
    
    public void unsubscribeIMUInterrupt() {
        bgapi.send_attclient_write_command(connection, IMU_INTERRUPT_CONFIG, new byte[]{0x00, 0x00});
    }
    
    public void readIMUMode() {
        bgapi.send_attclient_read_by_handle(connection, IMU_MODE);
    }
    
    public void setIMUMode(int value) {
        byte[] i = new byte[1];
        i[0] = (byte)(value & 0xFF);
        bgapi.send_attclient_write_command(connection, IMU_MODE, i);
    }
    
    private synchronized void imu(byte[] value) {
        
        int gx = ((value[1] & 0xFF) << 8) + (value[0] & 0xFF); if (gx > (1<<15)) { gx = gx - (1<<16); }
        int gy = ((value[3] & 0xFF) << 8) + (value[2] & 0xFF); if (gy > (1<<15)) { gy = gy - (1<<16); }
        int gz = ((value[5] & 0xFF) << 8) + (value[4] & 0xFF); if (gz > (1<<15)) { gz = gz - (1<<16); }
        
        int ax = ((value[7] & 0xFF) << 8) + (value[6] & 0xFF); if (ax > (1<<15)) { ax = ax - (1<<16); }
        int ay = ((value[9] & 0xFF) << 8) + (value[8] & 0xFF); if (ay > (1<<15)) { ay = ay - (1<<16); }
        int az = ((value[11] & 0xFF) << 8) + (value[10] & 0xFF); if (az > (1<<15)) { az = az - (1<<16); }
        
        int ts = ((value[13] & 0xFF) << 8) + (value[12] & 0xFF);
        
        for (TraaleListener l : listeners) {
            l.imu(ax, ay, az, gx, gy, gz, ts*4);
        }
    }
    
    private synchronized void quaternion(byte[] value) {
        int w = ((value[1] & 0xFF) << 8) + (value[0] & 0xFF); if (w > (1<<15)) { w = w - (1<<16); }
        int x = ((value[3] & 0xFF) << 8) + (value[2] & 0xFF); if (x > (1<<15)) { x = x - (1<<16); }
        int y = ((value[5] & 0xFF) << 8) + (value[4] & 0xFF); if (y > (1<<15)) { y = y - (1<<16); }
        int z = ((value[7] & 0xFF) << 8) + (value[6] & 0xFF); if (z > (1<<15)) { z = z - (1<<16); }
        int ts = ((value[9] & 0xFF) << 8) + (value[8] & 0xFF);
        
        for (TraaleListener l : listeners) {
            l.quaternion(w, x, y, z, ts*4);
        }
    }
    
    private void imuMode(byte[] value) {
        for (TraaleListener l : listeners) {
            l.imuMode((value[0] & 0xFF));
        }
    }
    
    private void alertLevel(byte[] value) {
        for (TraaleListener l : listeners) {
            l.alertLevel((value[0] & 0xFF));
        }
    }
    
    private void imuInterrupt(byte[] value) {
        for (TraaleListener l : listeners) {
            l.imuInterrupt((value[0] & 0xFF));
        }
    }
    
    /**************************************************************
     * MAGNETOMETER
     **************************************************************/ 
    public static final int MAG_VALUE = 0x39;
    public static final int MAG_CONFIG = 0x3A;
    public static final int MAG_INTERVAL = 0x3C;
    
    public void subscribeMagnetometer() {
        bgapi.send_attclient_write_command(connection, MAG_CONFIG, new byte[]{DEFAULT_SUB, 0x00});
    }
    
    public void unsubscribeMagnetometer() {
        bgapi.send_attclient_write_command(connection, MAG_CONFIG, new byte[]{0x00, 0x00});
    }
    
    public void readMagnetometerInterval() {
        bgapi.send_attclient_read_by_handle(connection, MAG_INTERVAL);
    }
    
    public void setMagnetometerInterval(int value) {
        byte[] i = new byte[2];
        i[1] = (byte)((value>>8) & 0xFF);
        i[0] = (byte)(value & 0xFF);
        bgapi.send_attclient_write_command(connection, MAG_INTERVAL, i);
    }
    
    private synchronized void magnetometer(byte[] value) {
        int x = ((value[1] & 0xFF) << 8) + (value[0] & 0xFF); if (x > (1<<15)) { x = x - (1<<16); }
        int y = ((value[3] & 0xFF) << 8) + (value[2] & 0xFF); if (y > (1<<15)) { y = y - (1<<16); }
        int z = ((value[5] & 0xFF) << 8) + (value[4] & 0xFF); if (z > (1<<15)) { z = z - (1<<16); }
        
        int ts = ((value[7] & 0xFF) << 8) + (value[6] & 0xFF);
        
        for (TraaleListener l : listeners) {
            l.magnetometer(x, y, z, ts*4);
        }
    }
    private synchronized void magnetometerInterval(byte[] value) {
        for (TraaleListener l : listeners) {
            l.magnetometerInterval((value[1]<<8) + (value[0] & 0xFF));
        }
    }
    
    /**************************************************************
     * TESTING PATTERN
     **************************************************************/ 
    public static final int CLK_VALUE = 0x44;
    public static final int CLK_CONFIG = 0x45;
    
    @Override
    public void sendTimeRequest(int seqNum) {
        bgapi.send_attclient_write_command(connection, CLK_VALUE, new byte[]{(byte)seqNum});
    }
    
    public void subscribeTimeSync() {
        bgapi.send_attclient_write_command(connection, CLK_CONFIG, new byte[]{0x01, 0x00});
    }
    
    public void unsubscribeTimeSync() {
        bgapi.send_attclient_write_command(connection, CLK_CONFIG, new byte[]{0x00, 0x00});
    }
    
    private synchronized void timeSync(byte[] value) {
        
        int ts = ((value[2] & 0xFF) << 8) + (value[1] & 0xFF);
        
        rtsync.receive_TimeResponse(value[0] & 0xFF, ts*4);
        
        for (TraaleListener l : listeners) {
            l.timeSync(value[0] & 0xFF, ts*4);
        }
    }
    
    /**************************************************************
     * TESTING PATTERN
     **************************************************************/ 
    public static final int TEST_VALUE = 0x47;
    public static final int TEST_CONFIG = 0x48;
    
    public void subscribeTestPattern() {
        bgapi.send_attclient_write_command(connection, TEST_CONFIG, new byte[]{0x01, 0x00});
    }
    
    public void unsubscribeTestPattern() {
        bgapi.send_attclient_write_command(connection, TEST_CONFIG, new byte[]{0x00, 0x00});
    }
    
    private synchronized void testPattern(byte[] value) {
        
        int ts = (value[0] & 0xFF);
        
        for (TraaleListener l : listeners) {
            l.testPattern(value, ts*4);
        }
    }
    
    /**************************************************************
     * Battery
     **************************************************************/ 

    public static final int BATTERY_VALUE = 0x28;
    public static final int BATTERY_CONFIG = 0x29;
    
    public void subscribeBattery() {
        bgapi.send_attclient_write_command(connection, BATTERY_CONFIG, new byte[]{0x01, 0x00});
    }
    
    public void unsubscribeBattery() {
        bgapi.send_attclient_write_command(connection, BATTERY_CONFIG, new byte[]{0x00, 0x00});
    }
    
    private synchronized void battery(byte[] value) {
        
        if (value.length < 3) {
            System.err.println("ISenseU Error: battery value length " + value.length + "instead of 3 (1 byte for the batery level + 2 bytes for timestamps" );
            return;
        }
        
        int ts = ((value[2] & 0xFF) << 8) + (value[1] & 0xFF);
        
        for (TraaleListener l : listeners) {
            l.battery((int)value[0], ts*4);
        }
    }
    
    /**************************************************************
     * Device info
     **************************************************************/ 
    
    public static final int MANUFACTURER = 0x0B;
    public static final int MODEL = 0x19;
    public static final int SERIAL = 0x11;
    public static final int HW_REV = 0x16;
    public static final int FW_REV = 0x0E;
    
    public void requestDeviceInfo() {
        bgapi.send_attclient_read_by_handle(connection, MANUFACTURER);
    }
    
    synchronized void manufacturer(byte[] value) {
        for (TraaleListener l : listeners) {
            l.manufacturer(new String(value));
        }
        bgapi.send_attclient_read_by_handle(connection, MODEL);
    }
    
    synchronized void model_number(byte[] value) {
        for (TraaleListener l : listeners) {
            l.model_number(new String(value));
        }
        bgapi.send_attclient_read_by_handle(connection, SERIAL);
    }
    
    synchronized void serial_number(byte[] value) {
        for (TraaleListener l : listeners) {
            l.serial_number(new String(value));
        }
        bgapi.send_attclient_read_by_handle(connection, HW_REV);
    }
    
    synchronized void hw_revision(byte[] value) {
        for (TraaleListener l : listeners) {
            l.hw_revision(new String(value));
        }
        bgapi.send_attclient_read_by_handle(connection, FW_REV);
    }
    
    synchronized void fw_revision(byte[] value) {
        for (TraaleListener l : listeners) {
            l.fw_revision(new String(value));
        }
    }
    
    
    /**************************************************************
     * Receive attribute values
     **************************************************************/ 

    long receivedBytes = 0;
    
    public long getReceivedBytes() {
        return receivedBytes;
    }
    
    @Override
    public void receive_attclient_attribute_value(int connection, int atthandle, int type, byte[] value) {
        if (this.connection == connection) {
            receivedBytes += value.length;
            switch(atthandle) {
                
                case THERMOMETER_VALUE: skinTemperature(value); break;
                case THERMOMETER_INTERVAL: skinTemperatureInterval(value); break;
                
                case HUMIDITY_VALUE: humidity(value); break;
                case HUMIDITY_INTERVAL: humidityInterval(value); break;
                
                case IMU_VALUE: imu(value); break;
                case QUAT_VALUE: quaternion(value); break;
                case IMU_MODE: imuMode(value); break;
                case IMU_INTERRUPT_VALUE: imuInterrupt(value); break;
                
                case MAG_VALUE: magnetometer(value); break;
                case MAG_INTERVAL: magnetometerInterval(value); break;
                    
                case BATTERY_VALUE: battery(value); break;
                    
                case MANUFACTURER: manufacturer(value); break;
                case MODEL: model_number(value); break;
                case SERIAL: serial_number(value); break;
                case HW_REV: hw_revision(value); break;
                case FW_REV: fw_revision(value); break;
                
                case CLK_VALUE: timeSync(value); break;
                case TEST_VALUE: testPattern(value); break;
                    
                case ALERT_LEVEL: alertLevel(value); break;
                        
                default: 
                    System.out.println("[Traale Driver] Got unknown attribute. Handle=" + Integer.toHexString(atthandle) + " val = " + bytesToString(value));
                    break;
            }
        }
    }
    
    public static double getTemperature(byte[] value) {
        if (value.length < 5) {
            //System.err.println("Cannot convert " + ByteUtils.bytesToString(value) + " to a temperature (expecting 5 bytes).");
            System.err.println("Cannot convert " + value + " to a temperature (expecting 5 bytes).");
            return 0;
        }
        int flags = value[0] & 0xFF;
        int exp = value[4];
        int mantissa = (value[3] << 16) + (value[2] << 8) + (value[1] & 0xFF);
        double result = mantissa * Math.pow(10, exp);
        return result;
    }
    
    public String bytesToString(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        result.append("[ ");
        for(byte b : bytes) result.append( Integer.toHexString(b & 0xFF) + " ");
        result.append("]");
        return result.toString();        
    }
    
}
