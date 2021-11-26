/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsaap;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Locatelli_andrea
 */
public class Thread_attesa extends Thread {
    
    DatagramSocket socket;
    InetAddress indirizzoMittente;
    comunicazone c1;
    static InetAddress vecchio;

    public Thread_attesa(String Nome) throws SocketException {
        this.socket = new DatagramSocket(12345);
        indirizzoMittente = null;
        c1 = comunicazone.getComunicazione();
        vecchio = null;
        comunicazone.getComunicazione().NomeMittente=Nome;

    }
     public void setoperazioneEdato(String mess) {
        String[] dati = mess.split(";");
        c1.setDato(dati[1]);
        c1.setOperazione(dati[0]);
    }

    @Override
    public void run() {

        while (true) {

            byte[] b = new byte[1500];

            DatagramPacket p = new DatagramPacket(b , b .length);

            try {
                socket.receive(p);
            } catch (IOException ex) {
               
            }
            if (vecchio != null) {
                indirizzoMittente = p.getAddress();
                if (vecchio.equals(indirizzoMittente)) {

                    try {
                        c1.setInvia(indirizzoMittente);
                        setoperazioneEdato(new String(p.getData(), 0, p.getLength()));
                    } catch (SocketException ex) {
                       
                    }
               

            } else {
                vecchio = p.getAddress();
                try {                   
                    c1.setInvia(vecchio);
                    setoperazioneEdato(new String(p.getData(), 0, p.getLength()));
                } catch (SocketException ex) {
                    
                }

            }
        }
    }
        
    
    }
}
