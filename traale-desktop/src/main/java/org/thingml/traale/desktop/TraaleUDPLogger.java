/**
 * Copyright (C) 2012 SINTEF <steffen.dalgard@sintef.no>
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

import org.thingml.traale.driver.Traale;
import org.thingml.traale.driver.TraaleListener;

/**
 *
 * @author steffend
 */
public class TraaleUDPLogger implements TraaleListener {

    private Traale traale;
    private String probeName;
    private UDPOscComm vOscAccX;
    private UDPOscComm vOscAccY;
    private UDPOscComm vOscAccZ;
    private UDPOscComm vOscGyrX;
    private UDPOscComm vOscGyrY;
    private UDPOscComm vOscGyrZ;
    private boolean logging = false;
    
    public TraaleUDPLogger(String probeName, Traale traale) {
        this.traale = traale;
        this.probeName = probeName;
        this.logging = false;
    }
    
    public void startLogging() {
           
           vOscAccX = new UDPOscComm();
           vOscAccX.open_communication("127.0.0.1", this.probeName + ".AccX");
           vOscAccY = new UDPOscComm();
           vOscAccY.open_communication("127.0.0.1", this.probeName + ".AccY");
           vOscAccZ = new UDPOscComm();
           vOscAccZ.open_communication("127.0.0.1", this.probeName + ".AccZ");
           vOscGyrX = new UDPOscComm();
           vOscGyrX.open_communication("127.0.0.1", this.probeName + ".GyrX");
           vOscGyrY = new UDPOscComm();
           vOscGyrY.open_communication("127.0.0.1", this.probeName + ".GyrY");
           vOscGyrZ = new UDPOscComm();
           vOscGyrZ.open_communication("127.0.0.1", this.probeName + ".GyrZ");
           logging = true;
    }
    
    public void stopLogging() {
        if (logging ) {
            logging = false;
            vOscAccX.close_communication();
            vOscAccX = null;
            vOscAccY.close_communication();
            vOscAccY = null;
            vOscAccZ.close_communication();
            vOscAccZ = null;
            vOscGyrX.close_communication();
            vOscGyrX = null;
            vOscGyrY.close_communication();
            vOscGyrY = null;
            vOscGyrZ.close_communication();
            vOscGyrZ = null;
        }
    }

    @Override
    public void skinTemperature(double temp, int timestamp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void skinTemperatureInterval(int value) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void humidity(int t1, int h1, int t2, int h2, int timestamp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void humidityInterval(int value) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imu(int ax, int ay, int az, int gx, int gy, int gz, int timestamp) {
        if (logging) {
            long ts = traale.getEpochTimestamp(timestamp);
            vOscAccX.send_ts_data(ts, ax);
            vOscAccY.send_ts_data(ts, ay);
            vOscAccZ.send_ts_data(ts, az);
            vOscGyrX.send_ts_data(ts, gx);
            vOscGyrY.send_ts_data(ts, gy);
            vOscGyrZ.send_ts_data(ts, gz);
        }
    }

    @Override
    public void quaternion(int w, int x, int y, int z, int timestamp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imuMode(int value) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imuInterrupt(int value) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void magnetometer(int x, int y, int z, int timestamp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void magnetometerInterval(int value) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void battery(int battery, int timestamp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void testPattern(byte[] data, int timestamp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void timeSync(int seq, int timestamp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void manufacturer(String value) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void model_number(String value) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void serial_number(String value) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void hw_revision(String value) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fw_revision(String value) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void alertLevel(int value) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
