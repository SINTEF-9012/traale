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

import java.awt.Color;
import org.thingml.traale.driver.TraaleListener;
import org.thingml.traale.driver.Traale;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import org.thingml.bglib.BGAPITransport;
import org.thingml.bglib.BGAPI;
import org.thingml.rtcharts.swing.DataBuffer;
import org.thingml.rtcharts.swing.GraphBuffer;
import org.thingml.rtsync.ui.TimeSyncFrame;

/**
 *
 * @author ffl
 */
public class TraaleFrame2 extends javax.swing.JFrame implements TraaleListener {

    private SimpleDateFormat timestampFormat = new SimpleDateFormat("HH:mm:ss.SSS");
    private DecimalFormat numFormat = new DecimalFormat("0.00");
    private DecimalFormat imunumFormat = new DecimalFormat("0.00000");
    protected BLEExplorerDialog bledialog = new BLEExplorerDialog();
    protected BitRateCounter bitrate;
    
    protected Traale traale;
    
    /**
     * Creates new form TraaleFrame
     */
    public TraaleFrame2() {
        initComponents();
        reset();
        bledialog.setModal(true);
    }
    
    protected void reset() {
        if (traale != null) { 
            traale.removeTraaleListener(this);
            traale.disconnect();
        }
        jProgressBarValueTemp.setValue(0);
        jTextFieldIntervalTemp.setText("N/A");
        jTextFieldTimeTemp.setText("N/A");
        jProgressBarValueTemp.setString("N/A");
        jCheckBoxSubscribeTemp.setSelected(false);
        
        jProgressBarRH1Humid.setValue(0);
        jProgressBarRH1Temp.setValue(0);
        jTextFieldRH1Humid.setText("N/A");
        jTextFieldRH1Temp.setText("N/A");
        jProgressBarRH2Humid.setValue(0);
        jProgressBarRH2Temp.setValue(0);
        jTextFieldRH2Humid.setText("N/A");
        jTextFieldRH2Temp.setText("N/A");
        jTextFieldIntervalHumid.setText("N/A");
        jTextFieldTimeHumid.setText("N/A");
        jCheckBoxSubscribeHumid.setSelected(false);
        
        jProgressBarBatt.setValue(0);
        jProgressBarBatt.setString("N/A");
        jCheckBoxSubscribeBatt.setSelected(true);
        
        jTextFieldTimeIMU.setText("N/A");
        jTextFieldIMUMode.setText("N/A");
        jCheckBoxSubscribeIMU.setSelected(false);
        jCheckBoxSubscribeQuat.setSelected(false);
        jCheckBoxIMUInterrupt.setSelected(false);
        jTextFieldQW.setText("N/A");
        jTextFieldQX.setText("N/A");
        jTextFieldQY.setText("N/A");
        jTextFieldQZ.setText("N/A");   
        jTextFieldPitch.setText("N/A");
        jTextFieldRoll.setText("N/A");
        jTextFieldYaw.setText("N/A");
        
        jCheckBoxSubscribeMag.setSelected(false);
        jTextFieldIntervalMag.setText("N/A");
        jTextFieldTimeMag.setText("N/A");
        jProgressBarMagX.setValue(0);
        jProgressBarMagY.setValue(0);
        jProgressBarMagZ.setValue(0);
        jProgressBarMagX.setString("N/A");
        jProgressBarMagY.setString("N/A");
        jProgressBarMagZ.setString("N/A");
        
      
        jProgressBarAccX.setValue(0);
        jProgressBarAccY.setValue(0);
        jProgressBarAccZ.setValue(0);
        jProgressBarAccX.setString("N/A");
        jProgressBarAccY.setString("N/A");
        jProgressBarAccZ.setString("N/A");
        
       
        jProgressBarGyroX.setValue(0);
        jProgressBarGyroY.setValue(0);
        jProgressBarGyroZ.setValue(0);
        jProgressBarGyroX.setString("N/A");
        jProgressBarGyroY.setString("N/A");
        jProgressBarGyroZ.setString("N/A");
        
        jTextFieldInfoFW.setText("N/A");
        jTextFieldInfoHW.setText("N/A");
        jTextFieldInfoSerial.setText("N/A");
        jTextFieldInfoModel.setText("N/A");
        jTextFieldInfoManuf.setText("N/A");
        
        jTextFieldPong.setText("N/A");
        jCheckBoxBWTest.setSelected(false);
        
        jCheckBoxSubsTimeSync.setSelected(false);
        
        last_ski = -1;
        last_hum = -1;
        last_mag = -1;
        last_imu = -1;
        last_qat = -1;
        
         if (logform != null) {
            logform.setVisible(false);
            logform.dispose();
         }
    } 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonConnection = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        jButtonLog = new javax.swing.JButton();
        jButtonTS = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldInfoManuf = new javax.swing.JTextField();
        jTextFieldInfoModel = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldInfoFW = new javax.swing.JTextField();
        jTextFieldInfoHW = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldInfoSerial = new javax.swing.JTextField();
        jButtonReqInfo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jProgressBarValueTemp = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldIntervalTemp = new javax.swing.JTextField();
        jButtonReadIntervalTemp = new javax.swing.JButton();
        jButtonWriteIntervalTemp = new javax.swing.JButton();
        jCheckBoxSubscribeTemp = new javax.swing.JCheckBox();
        jButtonGraphTemp = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldTimeTemp = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButtonURTemp = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldIntervalHumid = new javax.swing.JTextField();
        jButtonReadIntervalHumid = new javax.swing.JButton();
        jButtonWriteIntervalHumid = new javax.swing.JButton();
        jCheckBoxSubscribeHumid = new javax.swing.JCheckBox();
        jButtonGraphHumid = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTimeHumid = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jProgressBarRH1Temp = new javax.swing.JProgressBar();
        jProgressBarRH1Humid = new javax.swing.JProgressBar();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldRH1Temp = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldRH1Humid = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jProgressBarRH2Temp = new javax.swing.JProgressBar();
        jProgressBarRH2Humid = new javax.swing.JProgressBar();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldRH2Temp = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldRH2Humid = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jButtonURHum = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jCheckBoxSubscribeMag = new javax.swing.JCheckBox();
        jButtonGraphMag = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldIntervalMag = new javax.swing.JTextField();
        jButtonReadIntervalMag = new javax.swing.JButton();
        jButtonWriteIntervalMag = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldTimeMag = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jProgressBarMagX = new javax.swing.JProgressBar();
        jProgressBarMagY = new javax.swing.JProgressBar();
        jProgressBarMagZ = new javax.swing.JProgressBar();
        jButtonURMag = new javax.swing.JButton();
        jButtonGraphMag1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldIMUMode = new javax.swing.JTextField();
        jButtonReadIntervalIMU = new javax.swing.JButton();
        jButtonWriteIntervalIMU = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldTimeIMU = new javax.swing.JTextField();
        jButtonGraphIMU = new javax.swing.JButton();
        jCheckBoxSubscribeIMU = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldQX = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldQY = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextFieldQZ = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextFieldQW = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextFieldRoll = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextFieldPitch = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextFieldYaw = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jButtonGraphAcc = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jProgressBarAccX = new javax.swing.JProgressBar();
        jProgressBarAccY = new javax.swing.JProgressBar();
        jProgressBarAccZ = new javax.swing.JProgressBar();
        jPanel14 = new javax.swing.JPanel();
        jButtonGraphGyro = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jProgressBarGyroX = new javax.swing.JProgressBar();
        jProgressBarGyroY = new javax.swing.JProgressBar();
        jProgressBarGyroZ = new javax.swing.JProgressBar();
        jButtonURIMU = new javax.swing.JButton();
        jTextFieldIMUInterrupt = new javax.swing.JTextField();
        jCheckBoxIMUInterrupt = new javax.swing.JCheckBox();
        jLabel28 = new javax.swing.JLabel();
        jCheckBoxSubscribeQuat = new javax.swing.JCheckBox();
        jButtonURIMU2 = new javax.swing.JButton();
        jButtonPing = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jTextFieldPong = new javax.swing.JTextField();
        jCheckBoxSubsTimeSync = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jCheckBoxBWTest = new javax.swing.JCheckBox();
        jButtonBWTestGraphs = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jProgressBarBatt = new javax.swing.JProgressBar();
        jCheckBoxSubscribeBatt = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButtonConnection.setText("Connection...");
        jButtonConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnectionActionPerformed(evt);
            }
        });

        jLabel2.setText("Status : ");

        jTextFieldStatus.setText("No Connection.");

        jButtonLog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/file-3.png"))); // NOI18N
        jButtonLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogActionPerformed(evt);
            }
        });

        jButtonTS.setText("Time...");
        jButtonTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonConnection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLog))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonConnection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonTS))
            .addComponent(jButtonLog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jTextFieldStatus, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
        );

        jLabel23.setText("Manufacturer:");

        jLabel24.setText("Model:");

        jTextFieldInfoManuf.setText("N/A");
        jTextFieldInfoManuf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInfoManufActionPerformed(evt);
            }
        });

        jTextFieldInfoModel.setText("N/A");

        jLabel25.setText("Hardware revision:");

        jLabel26.setText("Firmware revision:");

        jTextFieldInfoFW.setText("N/A");

        jTextFieldInfoHW.setText("N/A");

        jLabel27.setText("Serial Number:");

        jTextFieldInfoSerial.setText("N/A");

        jButtonReqInfo.setText("Get Infos");
        jButtonReqInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReqInfoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Health Thermometer"));

        jProgressBarValueTemp.setForeground(new java.awt.Color(255, 51, 51));
        jProgressBarValueTemp.setMaximum(45);
        jProgressBarValueTemp.setMinimum(15);
        jProgressBarValueTemp.setValue(37);
        jProgressBarValueTemp.setString("37.00°C");
        jProgressBarValueTemp.setStringPainted(true);

        jLabel3.setText("Interval (ms) : ");

        jTextFieldIntervalTemp.setText("0");

        jButtonReadIntervalTemp.setText("Read");
        jButtonReadIntervalTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReadIntervalTempActionPerformed(evt);
            }
        });

        jButtonWriteIntervalTemp.setText("Write");
        jButtonWriteIntervalTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWriteIntervalTempActionPerformed(evt);
            }
        });

        jCheckBoxSubscribeTemp.setText("Subscribe");
        jCheckBoxSubscribeTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSubscribeTempActionPerformed(evt);
            }
        });

        jButtonGraphTemp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chart-1.png"))); // NOI18N
        jButtonGraphTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGraphTempActionPerformed(evt);
            }
        });

        jLabel4.setText("Time : ");

        jTextFieldTimeTemp.setText("No Data.");

        jLabel9.setText("Skin Temperature : ");

        jButtonURTemp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chart-file.png"))); // NOI18N
        jButtonURTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonURTempActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldIntervalTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonReadIntervalTemp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonWriteIntervalTemp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonURTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTimeTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jProgressBarValueTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxSubscribeTemp)
                    .addComponent(jButtonGraphTemp))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBarValueTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxSubscribeTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonGraphTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldIntervalTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonWriteIntervalTemp)
                        .addComponent(jButtonReadIntervalTemp)
                        .addComponent(jTextFieldTimeTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonURTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("I2C Humidity Sensors"));

        jLabel5.setText("Interval (ms) : ");

        jTextFieldIntervalHumid.setText("0");

        jButtonReadIntervalHumid.setText("Read");
        jButtonReadIntervalHumid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReadIntervalHumidActionPerformed(evt);
            }
        });

        jButtonWriteIntervalHumid.setText("Write");
        jButtonWriteIntervalHumid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWriteIntervalHumidActionPerformed(evt);
            }
        });

        jCheckBoxSubscribeHumid.setText("Subscribe");
        jCheckBoxSubscribeHumid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSubscribeHumidActionPerformed(evt);
            }
        });

        jButtonGraphHumid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chart-1.png"))); // NOI18N
        jButtonGraphHumid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGraphHumidActionPerformed(evt);
            }
        });

        jLabel6.setText("Time : ");

        jTextFieldTimeHumid.setText("No Data.");

        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 38));

        jProgressBarRH1Temp.setForeground(new java.awt.Color(0, 204, 51));
        jProgressBarRH1Temp.setMaximum(5000);
        jProgressBarRH1Temp.setValue(2500);

        jProgressBarRH1Humid.setForeground(new java.awt.Color(0, 153, 204));
        jProgressBarRH1Humid.setMaximum(10000);
        jProgressBarRH1Humid.setValue(4000);

        jLabel7.setText("T (°C)");

        jLabel8.setText("H (%)");

        jLabel18.setText("RH Sensor 1 : ");

        jTextFieldRH1Temp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldRH1Temp.setText("100.00");

        jLabel14.setText("°C");

        jTextFieldRH1Humid.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldRH1Humid.setText("100.00");

        jLabel15.setText("%");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBarRH1Humid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBarRH1Temp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldRH1Temp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldRH1Humid, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextFieldRH1Temp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jTextFieldRH1Humid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBarRH1Temp, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBarRH1Humid, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(100, 38));

        jProgressBarRH2Temp.setForeground(new java.awt.Color(0, 204, 51));
        jProgressBarRH2Temp.setMaximum(5000);
        jProgressBarRH2Temp.setValue(2500);

        jProgressBarRH2Humid.setForeground(new java.awt.Color(0, 153, 204));
        jProgressBarRH2Humid.setMaximum(10000);
        jProgressBarRH2Humid.setValue(6000);

        jLabel10.setText("T (°C)");

        jLabel11.setText("H (%)");

        jLabel19.setText("RH Sensor 2 : ");

        jTextFieldRH2Temp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldRH2Temp.setText("100.00");

        jLabel16.setText("°C");

        jTextFieldRH2Humid.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldRH2Humid.setText("100.00");

        jLabel17.setText("%");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBarRH2Temp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBarRH2Humid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldRH2Temp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldRH2Humid, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldRH2Temp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jTextFieldRH2Humid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBarRH2Temp, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBarRH2Humid, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jButtonURHum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chart-file.png"))); // NOI18N
        jButtonURHum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonURHumActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldIntervalHumid, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonReadIntervalHumid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonWriteIntervalHumid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonURHum, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTimeHumid, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxSubscribeHumid)
                    .addComponent(jButtonGraphHumid))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxSubscribeHumid)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldIntervalHumid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonReadIntervalHumid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonWriteIntervalHumid)
                    .addComponent(jButtonGraphHumid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTimeHumid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButtonURHum, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Magnetometer"));

        jCheckBoxSubscribeMag.setText("Subscribe");
        jCheckBoxSubscribeMag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSubscribeMagActionPerformed(evt);
            }
        });

        jButtonGraphMag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chart-1.png"))); // NOI18N
        jButtonGraphMag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGraphMagActionPerformed(evt);
            }
        });

        jLabel21.setText("Interval (ms) : ");

        jTextFieldIntervalMag.setText("0");

        jButtonReadIntervalMag.setText("Read");
        jButtonReadIntervalMag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReadIntervalMagActionPerformed(evt);
            }
        });

        jButtonWriteIntervalMag.setText("Write");
        jButtonWriteIntervalMag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWriteIntervalMagActionPerformed(evt);
            }
        });

        jLabel22.setText("Time : ");

        jTextFieldTimeMag.setText("No Data.");

        jProgressBarMagX.setForeground(new java.awt.Color(255, 102, 0));
        jProgressBarMagX.setMaximum(2048);
        jProgressBarMagX.setMinimum(-2047);
        jProgressBarMagX.setString("N/A");
        jProgressBarMagX.setStringPainted(true);

        jProgressBarMagY.setForeground(new java.awt.Color(255, 102, 0));
        jProgressBarMagY.setMaximum(2048);
        jProgressBarMagY.setMinimum(-2047);
        jProgressBarMagY.setString("N/A");
        jProgressBarMagY.setStringPainted(true);

        jProgressBarMagZ.setForeground(new java.awt.Color(255, 102, 0));
        jProgressBarMagZ.setMaximum(2048);
        jProgressBarMagZ.setMinimum(-2047);
        jProgressBarMagZ.setString("N/A");
        jProgressBarMagZ.setStringPainted(true);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBarMagX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jProgressBarMagY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jProgressBarMagZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jProgressBarMagX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarMagY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBarMagZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButtonURMag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chart-file.png"))); // NOI18N
        jButtonURMag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonURMagActionPerformed(evt);
            }
        });

        jButtonGraphMag1.setText("X-Y");
        jButtonGraphMag1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGraphMag1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldIntervalMag, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonReadIntervalMag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonWriteIntervalMag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTimeMag, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jButtonURMag, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jCheckBoxSubscribeMag)
                            .addGap(40, 40, 40))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addComponent(jButtonGraphMag)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jButtonGraphMag1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButtonGraphMag, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGraphMag1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonURMag, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldTimeMag, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonWriteIntervalMag)
                                .addComponent(jButtonReadIntervalMag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldIntervalMag, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jCheckBoxSubscribeMag)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Combined IMU Data"));

        jLabel13.setText("IMU Mode:");

        jTextFieldIMUMode.setText("0");

        jButtonReadIntervalIMU.setText("Read");
        jButtonReadIntervalIMU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReadIntervalIMUActionPerformed(evt);
            }
        });

        jButtonWriteIntervalIMU.setText("Write");
        jButtonWriteIntervalIMU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWriteIntervalIMUActionPerformed(evt);
            }
        });

        jLabel20.setText("Time : ");

        jTextFieldTimeIMU.setText("No Data.");

        jButtonGraphIMU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chart-1.png"))); // NOI18N
        jButtonGraphIMU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGraphIMUActionPerformed(evt);
            }
        });

        jCheckBoxSubscribeIMU.setText("Subscribe IMU");
        jCheckBoxSubscribeIMU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSubscribeIMUActionPerformed(evt);
            }
        });

        jLabel1.setText("Quaternion");

        jTextFieldQX.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldQX.setText("N/A");

        jLabel12.setText("x:");

        jLabel29.setText("y:");

        jTextFieldQY.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldQY.setText("N/A");

        jLabel30.setText("z:");

        jTextFieldQZ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldQZ.setText("N/A");

        jLabel31.setText("w:");

        jTextFieldQW.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldQW.setText("N/A");

        jLabel32.setText("Angles (deg)");

        jLabel33.setText("roll:");

        jTextFieldRoll.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldRoll.setText("N/A");

        jLabel34.setText("pitch:");

        jTextFieldPitch.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldPitch.setText("N/A");

        jLabel35.setText("yaw:");

        jTextFieldYaw.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldYaw.setText("N/A");

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Accelerometers"));

        jButtonGraphAcc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chart-1.png"))); // NOI18N
        jButtonGraphAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGraphAccActionPerformed(evt);
            }
        });

        jProgressBarAccX.setForeground(new java.awt.Color(255, 204, 0));
        jProgressBarAccX.setMaximum(2048);
        jProgressBarAccX.setMinimum(-2047);
        jProgressBarAccX.setString("N/A");
        jProgressBarAccX.setStringPainted(true);

        jProgressBarAccY.setForeground(new java.awt.Color(255, 204, 0));
        jProgressBarAccY.setMaximum(2048);
        jProgressBarAccY.setMinimum(-2047);
        jProgressBarAccY.setString("N/A");
        jProgressBarAccY.setStringPainted(true);

        jProgressBarAccZ.setForeground(new java.awt.Color(255, 204, 0));
        jProgressBarAccZ.setMaximum(2048);
        jProgressBarAccZ.setMinimum(-2047);
        jProgressBarAccZ.setString("N/A");
        jProgressBarAccZ.setStringPainted(true);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBarAccX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jProgressBarAccY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jProgressBarAccZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jProgressBarAccX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarAccY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBarAccZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addComponent(jButtonGraphAcc)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGraphAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Gyroscopes"));

        jButtonGraphGyro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chart-1.png"))); // NOI18N
        jButtonGraphGyro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGraphGyroActionPerformed(evt);
            }
        });

        jProgressBarGyroX.setForeground(new java.awt.Color(255, 153, 0));
        jProgressBarGyroX.setMaximum(2048);
        jProgressBarGyroX.setMinimum(-2047);
        jProgressBarGyroX.setString("N/A");
        jProgressBarGyroX.setStringPainted(true);

        jProgressBarGyroY.setForeground(new java.awt.Color(255, 153, 0));
        jProgressBarGyroY.setMaximum(2048);
        jProgressBarGyroY.setMinimum(-2047);
        jProgressBarGyroY.setString("N/A");
        jProgressBarGyroY.setStringPainted(true);

        jProgressBarGyroZ.setForeground(new java.awt.Color(255, 153, 0));
        jProgressBarGyroZ.setMaximum(2048);
        jProgressBarGyroZ.setMinimum(-2047);
        jProgressBarGyroZ.setString("N/A");
        jProgressBarGyroZ.setStringPainted(true);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBarGyroX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jProgressBarGyroY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jProgressBarGyroZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jProgressBarGyroX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarGyroY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBarGyroZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addComponent(jButtonGraphGyro)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGraphGyro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonURIMU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chart-file.png"))); // NOI18N
        jButtonURIMU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonURIMUActionPerformed(evt);
            }
        });

        jTextFieldIMUInterrupt.setText("N/A");

        jCheckBoxIMUInterrupt.setText("subscribe");
        jCheckBoxIMUInterrupt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxIMUInterruptActionPerformed(evt);
            }
        });

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Interrupts");

        jCheckBoxSubscribeQuat.setText("Subscribe Quaternion");
        jCheckBoxSubscribeQuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSubscribeQuatActionPerformed(evt);
            }
        });

        jButtonURIMU2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chart-file.png"))); // NOI18N
        jButtonURIMU2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonURIMU2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel12)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jTextFieldQZ, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel35))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jTextFieldQY, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel34))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                            .addComponent(jTextFieldQX, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(37, 37, 37)
                                            .addComponent(jLabel33)))
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldRoll, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldYaw, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldPitch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jTextFieldQW, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldIMUInterrupt, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBoxIMUInterrupt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldIMUMode, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(jButtonReadIntervalIMU)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonWriteIntervalIMU))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel20)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jButtonGraphIMU)
                                    .addGap(46, 46, 46)))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jCheckBoxSubscribeQuat)
                                .addComponent(jCheckBoxSubscribeIMU)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jTextFieldTimeIMU, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonURIMU, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(71, 71, 71))))
                .addGap(52, 52, 52)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jButtonURIMU2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(937, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jTextFieldQX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33)
                                    .addComponent(jTextFieldRoll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29)
                                    .addComponent(jTextFieldQY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34)
                                    .addComponent(jTextFieldPitch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(jTextFieldQZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35)
                                    .addComponent(jTextFieldYaw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31)
                                    .addComponent(jTextFieldQW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonGraphIMU, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBoxSubscribeQuat, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldIMUInterrupt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel20)
                                        .addComponent(jTextFieldTimeIMU, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButtonURIMU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBoxIMUInterrupt)
                                    .addComponent(jCheckBoxSubscribeIMU))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextFieldIMUMode, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonReadIntervalIMU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonWriteIntervalIMU)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addContainerGap(180, Short.MAX_VALUE)
                    .addComponent(jButtonURIMU2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(48, 48, 48)))
        );

        jButtonPing.setText("Ping");
        jButtonPing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPingActionPerformed(evt);
            }
        });

        jLabel36.setText("Pong:");

        jTextFieldPong.setText("N/A");

        jCheckBoxSubsTimeSync.setText("Subscribe Ping");
        jCheckBoxSubsTimeSync.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSubsTimeSyncActionPerformed(evt);
            }
        });

        jCheckBoxBWTest.setText("Bandwidth test pattern");
        jCheckBoxBWTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxBWTestActionPerformed(evt);
            }
        });

        jButtonBWTestGraphs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chart-file.png"))); // NOI18N
        jButtonBWTestGraphs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBWTestGraphsActionPerformed(evt);
            }
        });

        jButton1.setText("BW");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jCheckBoxBWTest)
                        .addGap(0, 41, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButtonBWTestGraphs, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jCheckBoxBWTest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonBWTestGraphs, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Battery"));

        jProgressBarBatt.setForeground(new java.awt.Color(102, 102, 102));
        jProgressBarBatt.setValue(70);
        jProgressBarBatt.setStringPainted(true);

        jCheckBoxSubscribeBatt.setText("Subscribe");
        jCheckBoxSubscribeBatt.setOpaque(false);
        jCheckBoxSubscribeBatt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSubscribeBattActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jProgressBarBatt, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxSubscribeBatt)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jProgressBarBatt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxSubscribeBatt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldInfoManuf)
                    .addComponent(jTextFieldInfoModel, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldInfoFW, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jButtonReqInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPong, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldInfoHW, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldInfoSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxSubsTimeSync)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonPing, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jTextFieldInfoManuf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)
                            .addComponent(jTextFieldInfoHW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldInfoSerial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonPing, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jCheckBoxSubsTimeSync))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jTextFieldInfoModel)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26)
                    .addComponent(jTextFieldInfoFW)
                    .addComponent(jButtonReqInfo)
                    .addComponent(jLabel36)
                    .addComponent(jTextFieldPong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConnectionActionPerformed

        reset();
        if (traale != null) traale.stopTimeSync();
        
        bledialog.setVisible(true);
        
        if (bledialog.isConnected()) {
            
            traale = new Traale(bledialog.getBgapi(), bledialog.getConnection());
            traale.addTraaleListener(this);
            traale.subscribeBattery();
            
            if (bitrate != null) bitrate.request_stop();
            bitrate = new BitRateCounter();
            bitrate.start();
            jTextFieldStatus.setText("Connected.");
            
            traale.startTimeSync();
            jCheckBoxSubsTimeSync.setSelected(true);
            
        }
        if(!bledialog.isConnected()) {

            jTextFieldStatus.setText("Not Connected.");
        }
    }//GEN-LAST:event_jButtonConnectionActionPerformed

    private void jButtonReadIntervalTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReadIntervalTempActionPerformed
        if (traale != null) traale.readSkinTemperatureInterval();
    }//GEN-LAST:event_jButtonReadIntervalTempActionPerformed

    private void jButtonWriteIntervalTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWriteIntervalTempActionPerformed
        if (traale != null) traale.setSkinTemperatureInterval(Integer.parseInt(jTextFieldIntervalTemp.getText()));
    }//GEN-LAST:event_jButtonWriteIntervalTempActionPerformed

    private void jCheckBoxSubscribeTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSubscribeTempActionPerformed
        if (traale != null) {
            if (jCheckBoxSubscribeTemp.isSelected()) {
                traale.subscribeSkinTemperature();
                traale.readSkinTemperatureInterval();
            }
            else {
                traale.unsubscribeSkinTemperature();
            }
        }
    }//GEN-LAST:event_jCheckBoxSubscribeTempActionPerformed

    private void jButtonReadIntervalHumidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReadIntervalHumidActionPerformed
        if (traale != null) traale.readHumidityInterval();
    }//GEN-LAST:event_jButtonReadIntervalHumidActionPerformed

    private void jButtonWriteIntervalHumidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWriteIntervalHumidActionPerformed
        if (traale != null) traale.setHumidityInterval(Integer.parseInt(jTextFieldIntervalHumid.getText()));
    }//GEN-LAST:event_jButtonWriteIntervalHumidActionPerformed

    private void jCheckBoxSubscribeHumidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSubscribeHumidActionPerformed
        if (traale != null) {
            if (jCheckBoxSubscribeHumid.isSelected()) {
                traale.subscribeHumidity();
                traale.readHumidityInterval();
            }
            else {
                traale.unsubscribeHumidity();
            }
        }
    }//GEN-LAST:event_jCheckBoxSubscribeHumidActionPerformed

    private void jCheckBoxSubscribeBattActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSubscribeBattActionPerformed
        if (traale != null) {
            if (jCheckBoxSubscribeBatt.isSelected()) {
                traale.subscribeBattery();
            }
            else {
                traale.unsubscribeBattery();
            }
        }
    }//GEN-LAST:event_jCheckBoxSubscribeBattActionPerformed

    private void jCheckBoxSubscribeMagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSubscribeMagActionPerformed
        if (traale != null) {
            if (jCheckBoxSubscribeMag.isSelected()) {
                traale.subscribeMagnetometer();
                traale.readMagnetometerInterval();
            }
            else {
                traale.unsubscribeMagnetometer();
            }
        }
    }//GEN-LAST:event_jCheckBoxSubscribeMagActionPerformed

    private void jButtonReadIntervalMagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReadIntervalMagActionPerformed
        if (traale != null) traale.readMagnetometerInterval();
    }//GEN-LAST:event_jButtonReadIntervalMagActionPerformed

    private void jButtonWriteIntervalMagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWriteIntervalMagActionPerformed
        if (traale != null) traale.setMagnetometerInterval(Integer.parseInt(jTextFieldIntervalMag.getText()));
    }//GEN-LAST:event_jButtonWriteIntervalMagActionPerformed

    private void jTextFieldInfoManufActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInfoManufActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInfoManufActionPerformed

    private void jButtonReqInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReqInfoActionPerformed
        if (traale != null) traale.requestDeviceInfo();
    }//GEN-LAST:event_jButtonReqInfoActionPerformed

    private void jCheckBoxSubscribeIMUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSubscribeIMUActionPerformed
        if (traale != null) {
            if (jCheckBoxSubscribeIMU.isSelected()) {
                traale.subscribeIMU();
                traale.readIMUMode();
            }
            else {
                traale.unsubscribeIMU();
            }
        }
    }//GEN-LAST:event_jCheckBoxSubscribeIMUActionPerformed

    private void jButtonWriteIntervalIMUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWriteIntervalIMUActionPerformed
        if (traale != null) traale.setIMUMode(Integer.parseInt(jTextFieldIMUMode.getText()));
    }//GEN-LAST:event_jButtonWriteIntervalIMUActionPerformed

    private void jButtonReadIntervalIMUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReadIntervalIMUActionPerformed
        if (traale != null) traale.readIMUMode();
    }//GEN-LAST:event_jButtonReadIntervalIMUActionPerformed

    private void jButtonGraphTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGraphTempActionPerformed
    SkinTempGraphFrame tempform = new SkinTempGraphFrame(buff_skinTemperature);
    tempform.setSize(600, 300);
    tempform.setVisible(true);
    }//GEN-LAST:event_jButtonGraphTempActionPerformed

    private void jButtonGraphHumidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGraphHumidActionPerformed
        HumidityGraphFrame tempform = new HumidityGraphFrame(buff_t1, buff_h1, buff_t2, buff_h2);
        tempform.setSize(600, 200*4);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButtonGraphHumidActionPerformed

    private void jButtonGraphIMUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGraphIMUActionPerformed
        QuaternionGraphFrame tempform = new QuaternionGraphFrame(buff_qw, buff_qx, buff_qy, buff_qz);
        tempform.setSize(600, 200*4);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButtonGraphIMUActionPerformed

    private void jButtonGraphMagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGraphMagActionPerformed
        ThreeAxisGraphFrame tempform = new ThreeAxisGraphFrame(buff_mx, buff_my, buff_mz, "Magnetometer", new Color(255,102,0) , -1023, 1024, 256);
        tempform.setSize(600, 200*3);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButtonGraphMagActionPerformed

    private void jButtonGraphGyroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGraphGyroActionPerformed
        ThreeAxisGraphFrame tempform = new ThreeAxisGraphFrame(buff_gx, buff_gy, buff_gz, "Gyroscopes", new Color(255,153,0) , -1023, 1024, 256);
        tempform.setSize(600, 200*3);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButtonGraphGyroActionPerformed

    private void jButtonGraphAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGraphAccActionPerformed
        ThreeAxisGraphFrame tempform = new ThreeAxisGraphFrame(buff_ax, buff_ay, buff_az, "Accelerometers", new Color(255,204,0) , -2047, 2048, 256);
        tempform.setSize(600, 200*3);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButtonGraphAccActionPerformed

    
    FileLoggerForm logform;
            
    private void jButtonLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogActionPerformed
        if (logform != null) {
            logform.setVisible(false);
            logform.dispose();
        }
        if (traale != null) {
            logform = new FileLoggerForm(traale);
            logform.pack();
            logform.setVisible(true);
        }
        else {
            System.err.println("Not Connected.");
        }
    }//GEN-LAST:event_jButtonLogActionPerformed

    private void jButtonURTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonURTempActionPerformed
        UpdateRateGraphFrame tempform = new UpdateRateGraphFrame(buff_dski, "Skin Temperature Rate", new java.awt.Color(255, 51, 51));
        tempform.setSize(600, 300);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButtonURTempActionPerformed

    private void jButtonURHumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonURHumActionPerformed
        UpdateRateGraphFrame tempform = new UpdateRateGraphFrame(buff_dhum, "Humidity Sensors Rate",new java.awt.Color(0, 204, 51));
        tempform.setSize(600, 300);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButtonURHumActionPerformed

    private void jButtonURMagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonURMagActionPerformed
        UpdateRateGraphFrame tempform = new UpdateRateGraphFrame(buff_dmag, "Magnetometers Rate",new java.awt.Color(255, 102, 0));
        tempform.setSize(600, 300);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButtonURMagActionPerformed

    private void jButtonURIMUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonURIMUActionPerformed
        UpdateRateGraphFrame tempform = new UpdateRateGraphFrame(buff_dimu, "IMU Rate",new java.awt.Color(255, 204, 0));
        tempform.setSize(600, 300);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButtonURIMUActionPerformed

    private void jCheckBoxIMUInterruptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxIMUInterruptActionPerformed
        if (traale != null) {
            if (jCheckBoxIMUInterrupt.isSelected()) {
                traale.subscribeIMUInterrupt();
                traale.readIMUMode();
            }
            else {
                traale.unsubscribeIMUInterrupt();
            }
        }
    }//GEN-LAST:event_jCheckBoxIMUInterruptActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.out.println("Closing connections.");
        if (traale!=null) traale.disconnect();
        if (bledialog!=null) bledialog.disconnect();
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void jCheckBoxSubscribeQuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSubscribeQuatActionPerformed
          if (traale != null) {
            if (jCheckBoxSubscribeQuat.isSelected()) {
                traale.subscribeQuaternion();
                traale.readIMUMode();
            }
            else {
                traale.unsubscribeQuaternion();
            }
        }
    }//GEN-LAST:event_jCheckBoxSubscribeQuatActionPerformed

    private void jButtonBWTestGraphsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBWTestGraphsActionPerformed
        UpdateRateGraphFrame tempform = new UpdateRateGraphFrame(buff_test, "Test packet rate",new java.awt.Color(255, 204, 0));
        tempform.setSize(600, 300);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButtonBWTestGraphsActionPerformed

    private int ping_seq = 0;
    
    private void jButtonPingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPingActionPerformed
        if (traale != null) {
            traale.sendTimeRequest(ping_seq);
            ping_seq++;
            if (ping_seq > 0xFF) ping_seq = 0;
        }
    }//GEN-LAST:event_jButtonPingActionPerformed

    private void jButtonURIMU2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonURIMU2ActionPerformed
        UpdateRateGraphFrame tempform = new UpdateRateGraphFrame(buff_dqat, "quaternion Rate",new java.awt.Color(255, 204, 0));
        tempform.setSize(600, 300);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButtonURIMU2ActionPerformed

    private void jCheckBoxBWTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxBWTestActionPerformed
        if (traale != null) {
            if (jCheckBoxBWTest.isSelected()) traale.subscribeTestPattern();
            else traale.unsubscribeTestPattern();
        }
    }//GEN-LAST:event_jCheckBoxBWTestActionPerformed

    private void jCheckBoxSubsTimeSyncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSubsTimeSyncActionPerformed
        if (traale != null) {
            if (jCheckBoxSubsTimeSync.isSelected()) traale.subscribeTimeSync();
            else traale.unsubscribeTimeSync();
        }
    }//GEN-LAST:event_jCheckBoxSubsTimeSyncActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BitRateGraphFrame tempform = new BitRateGraphFrame(brate);
        tempform.setSize(600, 200);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTSActionPerformed
        if (traale != null) {
            TimeSyncFrame f = new TimeSyncFrame(traale.getTimeSynchronizer());
            f.pack();
            f.setVisible(true);
        }
    }//GEN-LAST:event_jButtonTSActionPerformed

    private void jButtonGraphMag1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGraphMag1ActionPerformed
        ThreeAxisXYGraphFrame tempform = new ThreeAxisXYGraphFrame(magxy, magxz, magyz, "Magnetometer", new Color(255,102,0) , -512, 512, 128);
        tempform.setSize(400, 300*3);
        tempform.setVisible(true);
    }//GEN-LAST:event_jButtonGraphMag1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            // Set System L&F 
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            System.out.println(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TraaleFrame2().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBWTestGraphs;
    private javax.swing.JButton jButtonConnection;
    private javax.swing.JButton jButtonGraphAcc;
    private javax.swing.JButton jButtonGraphGyro;
    private javax.swing.JButton jButtonGraphHumid;
    private javax.swing.JButton jButtonGraphIMU;
    private javax.swing.JButton jButtonGraphMag;
    private javax.swing.JButton jButtonGraphMag1;
    private javax.swing.JButton jButtonGraphTemp;
    private javax.swing.JButton jButtonLog;
    private javax.swing.JButton jButtonPing;
    private javax.swing.JButton jButtonReadIntervalHumid;
    private javax.swing.JButton jButtonReadIntervalIMU;
    private javax.swing.JButton jButtonReadIntervalMag;
    private javax.swing.JButton jButtonReadIntervalTemp;
    private javax.swing.JButton jButtonReqInfo;
    private javax.swing.JButton jButtonTS;
    private javax.swing.JButton jButtonURHum;
    private javax.swing.JButton jButtonURIMU;
    private javax.swing.JButton jButtonURIMU2;
    private javax.swing.JButton jButtonURMag;
    private javax.swing.JButton jButtonURTemp;
    private javax.swing.JButton jButtonWriteIntervalHumid;
    private javax.swing.JButton jButtonWriteIntervalIMU;
    private javax.swing.JButton jButtonWriteIntervalMag;
    private javax.swing.JButton jButtonWriteIntervalTemp;
    private javax.swing.JCheckBox jCheckBoxBWTest;
    private javax.swing.JCheckBox jCheckBoxIMUInterrupt;
    private javax.swing.JCheckBox jCheckBoxSubsTimeSync;
    private javax.swing.JCheckBox jCheckBoxSubscribeBatt;
    private javax.swing.JCheckBox jCheckBoxSubscribeHumid;
    private javax.swing.JCheckBox jCheckBoxSubscribeIMU;
    private javax.swing.JCheckBox jCheckBoxSubscribeMag;
    private javax.swing.JCheckBox jCheckBoxSubscribeQuat;
    private javax.swing.JCheckBox jCheckBoxSubscribeTemp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBarAccX;
    private javax.swing.JProgressBar jProgressBarAccY;
    private javax.swing.JProgressBar jProgressBarAccZ;
    private javax.swing.JProgressBar jProgressBarBatt;
    private javax.swing.JProgressBar jProgressBarGyroX;
    private javax.swing.JProgressBar jProgressBarGyroY;
    private javax.swing.JProgressBar jProgressBarGyroZ;
    private javax.swing.JProgressBar jProgressBarMagX;
    private javax.swing.JProgressBar jProgressBarMagY;
    private javax.swing.JProgressBar jProgressBarMagZ;
    private javax.swing.JProgressBar jProgressBarRH1Humid;
    private javax.swing.JProgressBar jProgressBarRH1Temp;
    private javax.swing.JProgressBar jProgressBarRH2Humid;
    private javax.swing.JProgressBar jProgressBarRH2Temp;
    private javax.swing.JProgressBar jProgressBarValueTemp;
    private javax.swing.JTextField jTextFieldIMUInterrupt;
    private javax.swing.JTextField jTextFieldIMUMode;
    private javax.swing.JTextField jTextFieldInfoFW;
    private javax.swing.JTextField jTextFieldInfoHW;
    private javax.swing.JTextField jTextFieldInfoManuf;
    private javax.swing.JTextField jTextFieldInfoModel;
    private javax.swing.JTextField jTextFieldInfoSerial;
    private javax.swing.JTextField jTextFieldIntervalHumid;
    private javax.swing.JTextField jTextFieldIntervalMag;
    private javax.swing.JTextField jTextFieldIntervalTemp;
    private javax.swing.JTextField jTextFieldPitch;
    private javax.swing.JTextField jTextFieldPong;
    private javax.swing.JTextField jTextFieldQW;
    private javax.swing.JTextField jTextFieldQX;
    private javax.swing.JTextField jTextFieldQY;
    private javax.swing.JTextField jTextFieldQZ;
    private javax.swing.JTextField jTextFieldRH1Humid;
    private javax.swing.JTextField jTextFieldRH1Temp;
    private javax.swing.JTextField jTextFieldRH2Humid;
    private javax.swing.JTextField jTextFieldRH2Temp;
    private javax.swing.JTextField jTextFieldRoll;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldTimeHumid;
    private javax.swing.JTextField jTextFieldTimeIMU;
    private javax.swing.JTextField jTextFieldTimeMag;
    private javax.swing.JTextField jTextFieldTimeTemp;
    private javax.swing.JTextField jTextFieldYaw;
    // End of variables declaration//GEN-END:variables

    protected GraphBuffer buff_skinTemperature = new GraphBuffer(100);
    
    protected GraphBuffer buff_t1 = new GraphBuffer(100);
    protected GraphBuffer buff_h1 = new GraphBuffer(100);
    protected GraphBuffer buff_t2 = new GraphBuffer(100);
    protected GraphBuffer buff_h2 = new GraphBuffer(100);
    
    protected GraphBuffer buff_ax = new GraphBuffer(250);
    protected GraphBuffer buff_ay = new GraphBuffer(250);
    protected GraphBuffer buff_az = new GraphBuffer(250);
    
    protected GraphBuffer buff_gx = new GraphBuffer(250);
    protected GraphBuffer buff_gy = new GraphBuffer(250);
    protected GraphBuffer buff_gz = new GraphBuffer(250);
    
    protected GraphBuffer buff_mx = new GraphBuffer(150);
    protected GraphBuffer buff_my = new GraphBuffer(150);
    protected GraphBuffer buff_mz = new GraphBuffer(150);
    
    protected GraphBuffer buff_qw = new GraphBuffer(250);
    protected GraphBuffer buff_qx = new GraphBuffer(250);
    protected GraphBuffer buff_qy = new GraphBuffer(250);
    protected GraphBuffer buff_qz = new GraphBuffer(250);
    
    protected GraphBuffer buff_dski = new GraphBuffer(100);
    protected GraphBuffer buff_dhum = new GraphBuffer(100);
    protected GraphBuffer buff_dmag = new GraphBuffer(100);
    protected GraphBuffer buff_dimu = new GraphBuffer(100);
    protected GraphBuffer buff_dqat = new GraphBuffer(100);
    protected GraphBuffer buff_test = new GraphBuffer(100);
    
    long last_ski = -1;
    long last_hum = -1;
    long last_mag = -1;
    long last_imu = -1;
    long last_qat = -1;
    long last_test = -1;
    
    public void skinTemperature(double temp, int timestamp) {
        jProgressBarValueTemp.setString(numFormat.format(temp) + "°C");
        jProgressBarValueTemp.setValue((int)temp);
        jTextFieldTimeTemp.setText(""+timestamp);
        buff_skinTemperature.insertData((int)(temp*100));
        
        long time = timestamp;
        int diff = (int)(time - last_ski);
        if (diff < 0) diff += 0x40000;
        if (last_ski > 0) {
           buff_dski.insertData( diff ); 
        }
        last_ski = time;
        
    }

    public void skinTemperatureInterval(int value) {
        jTextFieldIntervalTemp.setText("" + value);
    }


    public void humidityInterval(int value) {
        jTextFieldIntervalHumid.setText("" + value);
    }

    public void battery(int battery, int timestamp) {
        jProgressBarBatt.setValue(battery);
        jProgressBarBatt.setString(battery + "%");
    }

    public void imuInterval(int value) {
        jTextFieldIMUMode.setText("" + value);
    }

    DataBuffer magxy = new DataBuffer(2, 100);
    DataBuffer magxz = new DataBuffer(2, 100);
    DataBuffer magyz = new DataBuffer(2, 100);
    
    
    public void magnetometer(int x, int y, int z, int timestamp) {
        //double dx = x / 100.0;
        //double dy = y / 100.0;
        //double dz = z / 100.0;
        
        jTextFieldTimeMag.setText(""+timestamp);
        jProgressBarMagX.setValue(x);
        jProgressBarMagY.setValue(y);
        jProgressBarMagZ.setValue(z);
        jProgressBarMagX.setString(""+x);
        jProgressBarMagY.setString(""+y);
        jProgressBarMagZ.setString(""+z);
        
        buff_mx.insertData(x);
        buff_my.insertData(y);
        buff_mz.insertData(z);
        
        magxy.appendDataRow(new int[]{x,y});
        magxz.appendDataRow(new int[]{x,z});
        magyz.appendDataRow(new int[]{y,z});

        long time = timestamp;
        int diff = (int)(time - last_mag);
        if (diff < 0) diff += 0x40000;
        if (last_mag > 0) {
           buff_dmag.insertData( diff ); 
        }
        last_mag = time;
        
    }

    public void magnetometerInterval(int value) {
        jTextFieldIntervalMag.setText("" + value);
    }

    @Override
    public void humidity(int t1, int h1, int t2, int h2, int timestamp) {
        jTextFieldRH1Humid.setText(numFormat.format(h1/100.0));
        jTextFieldRH1Temp.setText(numFormat.format(t1/100.0));
        jProgressBarRH1Humid.setValue(h1);
        jProgressBarRH1Temp.setValue(t1);

        jTextFieldRH2Humid.setText(numFormat.format(h2/100.0));
        jTextFieldRH2Temp.setText(numFormat.format(t2/100.0));
        jProgressBarRH2Humid.setValue(h2);
        jProgressBarRH2Temp.setValue(t2);
        
        jTextFieldTimeHumid.setText(""+timestamp);
        
        buff_t1.insertData(t1);
        buff_h1.insertData(h1);
        buff_t2.insertData(t2);
        buff_h2.insertData(h2);
        
        long time = timestamp;
        int diff = (int)(time - last_hum);
        if (diff < 0) diff += 0x40000;
        if (last_hum > 0) {
           buff_dhum.insertData( diff ); 
        }
        last_hum = time;
        
    }

    @Override
    public void imu(int ax, int ay, int az, int gx, int gy, int gz, int timestamp) {
        jTextFieldTimeIMU.setText(""+timestamp);
        
        jProgressBarAccX.setValue(ax);
        jProgressBarAccY.setValue(ay);
        jProgressBarAccZ.setValue(az);
        jProgressBarAccX.setString("" + ax);
        jProgressBarAccY.setString("" + ay);
        jProgressBarAccZ.setString("" + az);
        
        jProgressBarGyroX.setValue(gx);
        jProgressBarGyroY.setValue(gy);
        jProgressBarGyroZ.setValue(gz);
        jProgressBarGyroX.setString("" + gx);
        jProgressBarGyroY.setString("" + gy);
        jProgressBarGyroZ.setString("" + gz);
        
        buff_ax.insertData(ax);
        buff_ay.insertData(ay);
        buff_az.insertData(az);
        
        buff_gx.insertData(gx);
        buff_gy.insertData(gy);
        buff_gz.insertData(gz);
        
        long time = timestamp;
        int diff = (int)(time - last_imu);
        if (diff < 0) diff += 0x40000;
        if (last_imu > 0) {
           buff_dimu.insertData( diff ); 
        }
        last_imu = time;
    }
    
    
    @Override
    public void quaternion(int iw, int ix, int iy, int iz, int timestamp) {
        //jTextFieldTimeIMU.setText(timestampFormat.format( Calendar.getInstance().getTime()));
        //System.out.println("IMU: \tx:" + ix + " \ty:" + iy + " \tz:" + iz + " \tw:" + iw);
        
        double w = ((double)iw) / (1<<15);
        double x = ((double)ix) / (1<<15);
        double y = ((double)iy) / (1<<15);
        double z = ((double)iz) / (1<<15);
        
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
        
        
        jTextFieldQW.setText(""+iw);
        jTextFieldQX.setText(""+ix);
        jTextFieldQY.setText(""+iy);
        jTextFieldQZ.setText(""+iz);

        jTextFieldYaw.setText(numFormat.format(attitude* 180 / Math.PI));
        jTextFieldRoll.setText(numFormat.format(bank* 180 / Math.PI));
        jTextFieldPitch.setText(numFormat.format(heading* 180 / Math.PI));
        
        buff_qw.insertData(iw);
        buff_qx.insertData(ix);
        buff_qy.insertData(iy);
        buff_qz.insertData(iz);
        
        long time = timestamp;
        int diff = (int)(time - last_qat);
        if (diff < 0) diff += 0x40000;
        if (last_qat > 0) {
           buff_dqat.insertData( diff ); 
        }
        last_qat = time;

    }

    @Override
    public void imuMode(int value) {
        jTextFieldIMUMode.setText("" + value);
    }

    @Override
    public void manufacturer(String value) {
        jTextFieldInfoManuf.setText(value);
    }

    @Override
    public void model_number(String value) {
        jTextFieldInfoModel.setText(value);
    }

    @Override
    public void serial_number(String value) {
        jTextFieldInfoSerial.setText(value);
    }

    @Override
    public void hw_revision(String value) {
        jTextFieldInfoHW.setText(value);
    }

    @Override
    public void fw_revision(String value) {
        jTextFieldInfoFW.setText(value);
    }

    @Override
    public void imuInterrupt(int value) {
        if (value == 4) {
            jTextFieldIMUInterrupt.setText("NO MOTION");
        }
        else if (value == 2) {
            jTextFieldIMUInterrupt.setText("MOTION");
        }
        else jTextFieldIMUInterrupt.setText("INT: " + value);
    }

    @Override
    public void testPattern(byte[] data, int timestamp) {
        long time = timestamp;
        int diff = (int)(time - last_test);
        if (diff < 0) diff += 0x100;
        if (last_test > 0) {
           buff_test.insertData( diff ); 
        }
        last_test = time;
    }

    @Override
    public void timeSync(int seq, int timestamp) {
        jTextFieldPong.setText(""+ timestamp + "[" + seq + "]");
    }
    
    protected GraphBuffer brate = new GraphBuffer(100);

    @Override
    public void alertLevel(int value) {
       
    }
    
    class BitRateCounter extends Thread {
    
        private int update_rate = 100; // 1000 ms

        private boolean stop = false;
        public void request_stop() {
            stop = true;
        }

        public void run() {

           long old_time = System.currentTimeMillis();
           long old_bytes = traale.getReceivedBytes();

           while (traale != null && !stop) {
                try {
                    Thread.sleep(update_rate);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BitRateCounter.class.getName()).log(Level.SEVERE, null, ex);
                }
                long new_time = System.currentTimeMillis();
                long new_bytes = traale.getReceivedBytes();

                int bitrate = (int)(((new_bytes - old_bytes) * 1000) / (new_time - old_time));

                brate.insertData(bitrate);
                
                old_time = new_time;
                old_bytes = new_bytes;
           }
        }
    }
}
