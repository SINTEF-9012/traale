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

/**
 *
 * @author ffl
 */
public interface TraaleListener {
   
    void skinTemperature(double temp, int timestamp);
    void skinTemperatureInterval(int value);
    
    void humidity(int t1, int h1, int t2, int h2, int timestamp);
    void humidityInterval(int value);
    
    void imu(int ax, int ay, int az, int gx, int gy, int gz, int timestamp);
    
    void quaternion(int w, int x, int y, int z, int timestamp);
    
    void imuMode(int value);
    void imuInterrupt(int value);
    
    void magnetometer(int x, int y, int z, int timestamp);
    void magnetometerInterval(int value);
    
    void battery(int battery, int timestamp);
    
    void testPattern(byte[] data, int timestamp);
    
    void timeSync(int seq, int timestamp);
    
    
    void manufacturer(String value);
    void model_number(String value);
    void serial_number(String value);
    void hw_revision(String value);
    void fw_revision(String value);
    
}
