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
//import org.thingml.bglib.samples.ByteUtils;

/**
 *
 * @author ffl
 */
public class Traale extends BGAPIDefaultListener {
    
    private ArrayList<TraaleListener> listeners = new ArrayList<TraaleListener>();
    
    public void addTraaleListener(TraaleListener l) {
        listeners.add(l);
    }
    
    public void removeTraaleListener(TraaleListener l) {
        listeners.remove(l);
    }
    
    protected BGAPI bgapi;
    protected int connection;

    public Traale(BGAPI bgapi, int connection) {
        this.bgapi = bgapi;
        this.connection = connection;
        bgapi.addListener(this);
    }
    
    public void disconnect() {
        bgapi.removeListener(this);
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
    
    private void skinTemperature(byte[] value) {
        for (TraaleListener l : listeners) {
            l.skinTemperature(getTemperature(value));
        }
    }
    private void skinTemperatureInterval(byte[] value) {
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
    
    private void humidity(byte[] value) {
        int t1 = ((value[1] & 0xFF) << 8) + (value[0] & 0xFF);
        int h1 = ((value[3] & 0xFF) << 8) + (value[2] & 0xFF);
        int t2 = ((value[5] & 0xFF) << 8) + (value[4] & 0xFF);
        int h2 = ((value[7] & 0xFF) << 8) + (value[6] & 0xFF);
        
        for (TraaleListener l : listeners) {
            l.humidity(t1, h1, t2, h2);
        }
    }
    private void humidityInterval(byte[] value) {
        for (TraaleListener l : listeners) {
            l.humidityInterval((value[1]<<8) + (value[0] & 0xFF));
        }
    }
    
    
    /**************************************************************
     * IMU
     **************************************************************/ 
    public static final int IMU_VALUE = 0x33;
    public static final int IMU_CONFIG = 0x34;
    public static final int IMU_MODE = 0x3C;
    
    public void subscribeIMU() {
        bgapi.send_attclient_write_command(connection, IMU_CONFIG, new byte[]{0x01, 0x00});
    }
    
    public void unsubscribeIMU() {
        bgapi.send_attclient_write_command(connection, IMU_CONFIG, new byte[]{0x00, 0x00});
    }
    
    public void readIMUMode() {
        bgapi.send_attclient_read_by_handle(connection, IMU_MODE);
    }
    
    public void setIMUMode(int value) {
        byte[] i = new byte[1];
        i[0] = (byte)(value & 0xFF);
        bgapi.send_attclient_write_command(connection, IMU_MODE, i);
    }
    
    private void imu(byte[] value) {
        int w = ((value[1] & 0xFF) << 8) + (value[0] & 0xFF); if (w > (1<<15)) { w = w - (1<<16); }
        int x = ((value[3] & 0xFF) << 8) + (value[2] & 0xFF); if (x > (1<<15)) { x = x - (1<<16); }
        int y = ((value[5] & 0xFF) << 8) + (value[4] & 0xFF); if (y > (1<<15)) { y = y - (1<<16); }
        int z = ((value[7] & 0xFF) << 8) + (value[6] & 0xFF); if (z > (1<<15)) { z = z - (1<<16); }
        
        int gx = ((value[9] & 0xFF) << 8) + (value[8] & 0xFF); if (gx > (1<<15)) { gx = gx - (1<<16); }
        int gy = ((value[11] & 0xFF) << 8) + (value[10] & 0xFF); if (gy > (1<<15)) { gy = gy - (1<<16); }
        int gz = ((value[13] & 0xFF) << 8) + (value[12] & 0xFF); if (gz > (1<<15)) { gz = gz - (1<<16); }
        
        int ax = ((value[15] & 0xFF) << 8) + (value[14] & 0xFF); if (ax > (1<<15)) { ax = ax - (1<<16); }
        int ay = ((value[17] & 0xFF) << 8) + (value[16] & 0xFF); if (ay > (1<<15)) { ay = ay - (1<<16); }
        int az = ((value[19] & 0xFF) << 8) + (value[18] & 0xFF); if (az > (1<<15)) { az = az - (1<<16); }
        
        for (TraaleListener l : listeners) {
            l.imu(w, x, y, z, ax, ay, az, gx, gy, gz);
        }
    }
    
    private void imuMode(byte[] value) {
        for (TraaleListener l : listeners) {
            l.imuMode((value[0] & 0xFF));
        }
    }
    
    /**************************************************************
     * MAGNETOMETER
     **************************************************************/ 
    public static final int MAG_VALUE = 0x36;
    public static final int MAG_CONFIG = 0x37;
    public static final int MAG_INTERVAL = 0x3E;
    
    public void subscribeMagnetometer() {
        bgapi.send_attclient_write_command(connection, MAG_CONFIG, new byte[]{0x01, 0x00});
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
    
    private void magnetometer(byte[] value) {
        int x = ((value[1] & 0xFF) << 8) + (value[0] & 0xFF); if (x > (1<<15)) { x = x - (1<<16); }
        int y = ((value[3] & 0xFF) << 8) + (value[2] & 0xFF); if (y > (1<<15)) { y = y - (1<<16); }
        int z = ((value[5] & 0xFF) << 8) + (value[4] & 0xFF); if (z > (1<<15)) { z = z - (1<<16); }
        
        for (TraaleListener l : listeners) {
            l.magnetometer(x, y, z);
        }
    }
    private void magnetometerInterval(byte[] value) {
        for (TraaleListener l : listeners) {
            l.magnetometerInterval((value[1]<<8) + (value[0] & 0xFF));
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
    
    void battery(byte[] value) {
        for (TraaleListener l : listeners) {
            l.battery((int)value[0]);
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
    
    void manufacturer(byte[] value) {
        for (TraaleListener l : listeners) {
            l.manufacturer(new String(value));
        }
        bgapi.send_attclient_read_by_handle(connection, MODEL);
    }
    
    void model_number(byte[] value) {
        for (TraaleListener l : listeners) {
            l.model_number(new String(value));
        }
        bgapi.send_attclient_read_by_handle(connection, SERIAL);
    }
    
    void serial_number(byte[] value) {
        for (TraaleListener l : listeners) {
            l.serial_number(new String(value));
        }
        bgapi.send_attclient_read_by_handle(connection, HW_REV);
    }
    
    void hw_revision(byte[] value) {
        for (TraaleListener l : listeners) {
            l.hw_revision(new String(value));
        }
        bgapi.send_attclient_read_by_handle(connection, FW_REV);
    }
    
    void fw_revision(byte[] value) {
        for (TraaleListener l : listeners) {
            l.fw_revision(new String(value));
        }
    }
    
    
    /**************************************************************
     * Receive attribute values
     **************************************************************/ 

    @Override
    public void receive_attclient_attribute_value(int connection, int atthandle, int type, byte[] value) {
        if (this.connection == connection) {
            switch(atthandle) {
                
                case THERMOMETER_VALUE: skinTemperature(value); break;
                case THERMOMETER_INTERVAL: skinTemperatureInterval(value); break;
                
                case HUMIDITY_VALUE: humidity(value); break;
                case HUMIDITY_INTERVAL: humidityInterval(value); break;
                
                case IMU_VALUE: imu(value); break;
                case IMU_MODE: imuMode(value); break;
                
                case MAG_VALUE: magnetometer(value); break;
                case MAG_INTERVAL: magnetometerInterval(value); break;
                    
                case BATTERY_VALUE: battery(value); break;
                    
                case MANUFACTURER: manufacturer(value); break;
                case MODEL: model_number(value); break;
                case SERIAL: serial_number(value); break;
                case HW_REV: hw_revision(value); break;
                case FW_REV: fw_revision(value); break;
                        
                default: break;
            }
        }
    }
    
    public static double getTemperature(byte[] value) {
        if (value.length != 5) {
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
    
}
