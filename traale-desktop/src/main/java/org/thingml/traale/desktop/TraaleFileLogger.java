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
package org.thingml.traale.desktop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.thingml.traale.driver.TraaleListener;

/**
 *
 * @author ffl
 */
public class TraaleFileLogger implements TraaleListener {
    
    private SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private String SEPARATOR = "\t";
    
    protected File folder;
    protected boolean logging = false;
    protected boolean request_start = false;
    protected long startTime = 0;
    protected long last_ski = 0;
    protected long last_hum = 0;
    protected long last_mag = 0;
    protected long last_imu = 0;
    
    
    protected PrintWriter log;
    protected PrintWriter ski;
    protected PrintWriter imu;
    protected PrintWriter hum;
    protected PrintWriter mag;
    
    public TraaleFileLogger(File folder) {
        this.folder = folder;
    }
    
    public boolean isLogging() {
        return logging;
    }
    
    public void startLogging() {
       String sName = createSessionName(); 
       File sFolder = new File(folder, sName);
       sFolder.mkdir();
       try {
           log = new PrintWriter(new FileWriter(new File(sFolder, "Traale_log.csv")));
           log.println("# This file contains one line per data received from the traale unit.");
           
           ski = new PrintWriter(new FileWriter(new File(sFolder, "Traale_ski.csv")));
           ski.println("Time" + SEPARATOR + "Time (ms)" + SEPARATOR + "dT (ms)" + SEPARATOR + "Skin Temperature (°C)");
           
           hum = new PrintWriter(new FileWriter(new File(sFolder, "Traale_hum.csv")));
           hum.println("Time" + SEPARATOR + "Time (ms)" + SEPARATOR + "dT (ms)" + SEPARATOR + "T1" + SEPARATOR + "H1" + SEPARATOR + "T2" + SEPARATOR + "H2");
           
           mag = new PrintWriter(new FileWriter(new File(sFolder, "Traale_mag.csv")));
           mag.println("Time" + SEPARATOR + "Time (ms)" + SEPARATOR + "dT (ms)" + SEPARATOR + "Mag. X" + SEPARATOR + "Mag. Y" + SEPARATOR + "Mag. Z");
           
           
           imu = new PrintWriter(new FileWriter(new File(sFolder, "Traale_imu.csv")));
           imu.println("Time" + SEPARATOR + "Time (ms)" + SEPARATOR + "dT (ms)" + SEPARATOR + "Quad. W" + SEPARATOR + "Quad. X" + SEPARATOR + "Quad. Y" + SEPARATOR + "Quad. Z" + SEPARATOR + "Acc. X" + SEPARATOR + "Acc. Y" + SEPARATOR + "Acc. Z" + SEPARATOR + "Gyro. X" + SEPARATOR + "Gyro. Y" + SEPARATOR + "Gyro. Z" + SEPARATOR + "Pitch" + SEPARATOR + "Roll" + SEPARATOR + "Yaw");
           
       } catch (IOException ex) {
           Logger.getLogger(TraaleFileLogger.class.getName()).log(Level.SEVERE, null, ex);
       }
       last_ski = System.currentTimeMillis();
       last_hum = System.currentTimeMillis();
       last_mag = System.currentTimeMillis();
       last_imu = System.currentTimeMillis();
       startTime = System.currentTimeMillis();
       logging = true;
    }
    
    public void stopLogging() {
        if (logging) {
            logging = false;
            log.close();
            ski.close();
            imu.close();
            mag.close();
            hum.close();
            log = null;
            ski = null;
            mag = null;
            imu = null;
            mag = null;
        }
    }
    
    public String createSessionName() {
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        return timestampFormat.format( Calendar.getInstance().getTime());
    }
    
    public String currentTimeStamp() {
        return timestampFormat.format( Calendar.getInstance().getTime()) + SEPARATOR + (System.currentTimeMillis()-startTime);
    }
        
    private DecimalFormat tempFormat = new DecimalFormat("0.00");
    @Override
    public void skinTemperature(double temp) {
        if (logging) {
            ski.println(currentTimeStamp() + SEPARATOR + (System.currentTimeMillis() - last_ski) + SEPARATOR + tempFormat.format(temp));
            log.println(currentTimeStamp() + SEPARATOR + "[skinTemperature]" + SEPARATOR + tempFormat.format(temp));
            last_ski = System.currentTimeMillis();
        }
    }

    @Override
    public void skinTemperatureInterval(int value) {
        if (logging) log.println(currentTimeStamp() + SEPARATOR + "[skinTemperatureInterval]" + SEPARATOR + value);
    }

    @Override
    public void humidity(int t1, int h1, int t2, int h2) {
        if (logging) {
            hum.println(currentTimeStamp() + SEPARATOR + (System.currentTimeMillis() - last_hum) + SEPARATOR + tempFormat.format(t1/100.0)+ SEPARATOR + tempFormat.format(h1/100.0)+ SEPARATOR + tempFormat.format(t2/100.0)+ SEPARATOR + tempFormat.format(h2/100.0));
            log.println(currentTimeStamp() + SEPARATOR + "[humidity]" + SEPARATOR + tempFormat.format(t1/100.0)+ SEPARATOR + tempFormat.format(h1/100.0)+ SEPARATOR + tempFormat.format(t2/100.0)+ SEPARATOR + tempFormat.format(h2/100.0));
            last_hum = System.currentTimeMillis();
        }
    }

    @Override
    public void humidityInterval(int value) {
        if (logging) log.println(currentTimeStamp() + SEPARATOR + "[humidityInterval]" + SEPARATOR + value);
    }

    @Override
    public void imu(int qw, int qx, int qy, int qz, int ax, int ay, int az, int gx, int gy, int gz) {
        if (logging) {
                       
            double w = ((double)qw) / (1<<15);
            double x = ((double)qx) / (1<<15);
            double y = ((double)qy) / (1<<15);
            double z = ((double)qz) / (1<<15);

            double heading, attitude, bank;

            double sqw = w*w;
            double sqx = x*x;
            double sqy = y*y;
            double sqz = z*z;

            double unit = sqx + sqy + sqz + sqw; // if normalised is one, otherwise is correction factor
            double test = x*y + z*w;

            if (test > 0.499*unit) { // singularity at north pole
                    heading = 2 * Math.atan2(x,w);
                    attitude = Math.PI/2;
                    bank = 0;
            }
            else if (test < -0.499*unit) { // singularity at south pole
                    heading = -2 * Math.atan2(x,w);
                    attitude = -Math.PI/2;
                    bank = 0;
            }
            else {
                heading = Math.atan2(2*y*w-2*x*z , sqx - sqy - sqz + sqw);
                attitude = Math.asin(2*test/unit);
                bank = Math.atan2(2*x*w-2*y*z , -sqx + sqy - sqz + sqw);
            }
            
            int pi = (int)(heading* 180 / Math.PI);
            int ro = (int)(bank* 180 / Math.PI);
            int ya = (int)(attitude* 180 / Math.PI);
            
            imu.println(currentTimeStamp() + SEPARATOR + (System.currentTimeMillis() - last_imu) + SEPARATOR + qw + SEPARATOR + qx + SEPARATOR + qy + SEPARATOR + qz
                                           + SEPARATOR + ax + SEPARATOR + ay + SEPARATOR + az
                                           + SEPARATOR + gx + SEPARATOR + gy + SEPARATOR + gz
                                           + SEPARATOR + pi + SEPARATOR + ro + SEPARATOR + ya);
            log.println(currentTimeStamp() + SEPARATOR + "[imu]" + SEPARATOR + qw + SEPARATOR + qx + SEPARATOR + qy + SEPARATOR + qz
                                           + SEPARATOR + ax + SEPARATOR + ay + SEPARATOR + az
                                           + SEPARATOR + gx + SEPARATOR + gy + SEPARATOR + gz
                                           + SEPARATOR + pi + SEPARATOR + ro + SEPARATOR + ya);
            last_imu = System.currentTimeMillis();
        }
    }

    @Override
    public void imuMode(int value) {
        if (logging) log.println(currentTimeStamp() + SEPARATOR + "[imuMode]" + SEPARATOR + value);
    }

    @Override
    public void magnetometer(int x, int y, int z) {
       if (logging) {
           mag.println(currentTimeStamp() + SEPARATOR + (System.currentTimeMillis() - last_mag) + SEPARATOR + x + SEPARATOR + y + SEPARATOR + z);
           log.println(currentTimeStamp() + SEPARATOR + "[magnetometer]" + SEPARATOR + x + SEPARATOR + y + SEPARATOR + z);
           last_mag = System.currentTimeMillis();
       }
    }

    @Override
    public void magnetometerInterval(int value) {
        if (logging) log.println(currentTimeStamp() + SEPARATOR + "[magnetometerInterval]" + SEPARATOR + value);
    }

    @Override
    public void battery(int battery) {
        if (logging) log.println(currentTimeStamp() + SEPARATOR + "[battery]" + SEPARATOR + battery + "%");
    }

    @Override
    public void manufacturer(String value) {
        if (logging) log.println(currentTimeStamp() + SEPARATOR + "[manufacturer]" + SEPARATOR + value);
    }

    @Override
    public void model_number(String value) {
        if (logging) log.println(currentTimeStamp() + SEPARATOR + "[model_number]" + SEPARATOR + value);
    }

    @Override
    public void serial_number(String value) {
        if (logging) log.println(currentTimeStamp() + SEPARATOR + "[serial_number]" + SEPARATOR + value);
    }

    @Override
    public void hw_revision(String value) {
        if (logging) log.println(currentTimeStamp() + SEPARATOR + "[hw_revision]" + SEPARATOR + value);
    }

    @Override
    public void fw_revision(String value) {
        if (logging) log.println(currentTimeStamp() + SEPARATOR + "[fw_revision]" + SEPARATOR + value);
    }

    @Override
    public void imuInterrupt(int value) {
        if (logging) log.println(currentTimeStamp() + SEPARATOR + "[imuInterrupt]" + SEPARATOR + value);
    }
    
}