/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsaap;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author locatelli_andrea
 */
public class utente {
    static DatagramSocket ricezione;
    static DatagramSocket invio;
    InetAddress Idestinatario;

    utente() throws SocketException {
        this.ricezione = new DatagramSocket(12345);
        this.invio = new DatagramSocket(666);
        Idestinatario = null;
    }

    public boolean invia(String s) throws IOException {
        
        if (Idestinatario != null) {
            byte[] responseBuffer = s.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            responsePacket.setAddress(Idestinatario);
            responsePacket.setPort(invio.getPort());
            invio.send(responsePacket);
            return true;
        }
        return false;
    }

    public String ricevi() throws IOException {
        //ricevo un messaggio       
        byte[] b = new byte[1500];
        DatagramPacket packet = new DatagramPacket(b, b.length);
        ricezione.receive(packet);
        byte[] dataReceived = packet.getData(); 
        if (Idestinatario != null) {
            Idestinatario = packet.getAddress();           
            return new String(dataReceived, 0, packet.getLength());
        } else if (packet.getAddress() != Idestinatario) {
           
            invia("n; ;");
        } else {
            //ricevo il pacchetto
            return new String(dataReceived, 0, packet.getLength());
        }
        return "";
    }
}
