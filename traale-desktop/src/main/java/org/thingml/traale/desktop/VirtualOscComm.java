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

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 *
 * @author steffend
 */
public class VirtualOscComm {
    
    protected DatagramSocket udpSocket;
    protected InetAddress IPAddress;
    protected int IPPort;
    protected int channel = -1;
    protected String displayName;
    

    
    public int open_communication(String address, String displayName) {
       int ret = 0;
       
       this.displayName = displayName;
       try {
           this.udpSocket = new DatagramSocket();
           this.IPAddress = InetAddress.getByName(address);
           //IPAddress = InetAddress.getByName("127.0.0.1");
           this.IPPort = 30000;

           // Request new channelnumber from remote
           String sendStr = new String("$" + " 1 " + displayName);
           System.out.println("Send port request : " + sendStr);
           byte[] sendData;
           sendData = sendStr.getBytes();
           DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, this.IPAddress, this.IPPort);
           this.udpSocket.send(sendPacket);
           
           // Wait for reply
           byte[] buf = new byte[256];
           DatagramPacket packet = new DatagramPacket(buf, buf.length);
           this.udpSocket.setSoTimeout(1000);
           this.udpSocket.receive(packet);
           String received = new String(packet.getData(), 0, packet.getLength());
           //System.out.println("UdpReceived: " + received);
           this.channel = Integer.parseInt(received);
           System.out.println("Channel is now: " + this.channel);


           
       } catch (SocketTimeoutException ex) {
           System.out.println("Timeout waiting for VirtualOsc reply: ");
           ret = -1;
           this.channel = -1;
       } catch (IOException ex) {
           ex.printStackTrace();
       }
       return ret;
    }

    public void close_communication() {
       try {
           // Release channelnumber from remote
           String sendStr = new String("$" + " 0 " + this.channel);
           System.out.println("Send release request : " + sendStr);
           byte[] sendData;
           sendData = sendStr.getBytes();
           DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, this.IPAddress, this.IPPort);
           this.udpSocket.send(sendPacket);
       } catch (IOException ex) {
           ex.printStackTrace();
       }
       this.udpSocket.close();
       this.udpSocket = null;
       this.channel = -1;
    }
    
    public void send_ts_data(long valEpoc, float val) {
        
        if( this.channel >= 0) {
            long txEpoc = System.currentTimeMillis();
            // System.out.println("txEpoc = " + txEpoc);
            // System.out.println("valEpoc = " + valEpoc);
            String sendStr = new String("#" + " " + this.channel + " " + txEpoc + " " + valEpoc + " " + val);
            //System.out.println(sendStr);
            byte[] sendData;
            sendData = sendStr.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, this.IPAddress, this.IPPort);
            try {
                this.udpSocket.send(sendPacket);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void send_data(float val) {
        
        if( this.channel >= 0) {
            long txEpoc = System.currentTimeMillis();
            // System.out.println("txEpoc = " + txEpoc);
            // System.out.println("valEpoc = " + valEpoc);
            String sendStr = new String("#" + " " + this.channel + " " + txEpoc + " " + txEpoc + " " + val);
            //System.out.println(sendStr);
            byte[] sendData;
            sendData = sendStr.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, this.IPAddress, this.IPPort);
            try {
                this.udpSocket.send(sendPacket);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}

