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
public class messaggio {
  

    int id;
    String contenuto;
    boolean tipo; 
    boolean attessa; 
    
    DatagramSocket socket;
    InetAddress indirizzoDestinatario;
    int portaDestinatario;

    public messaggio(String contenuto, int id, boolean t) {
        this.contenuto = contenuto;
        this.id = id;
        this.tipo = t;
        if (tipo == true) {
            attessa = false;
        }
        attessa = true;
    }

    public messaggio() {
        id = -1;
        contenuto = "";
        tipo = false;
        attessa = false;
    }
    
    public void Invia(InetAddress indirizzoDestinatario) throws SocketException {
        this.socket = new DatagramSocket(666);
        this.indirizzoDestinatario = indirizzoDestinatario;
        this.portaDestinatario = 12345;
    }

    public void setIndirizzoDestinatario(InetAddress indirizzoDestinatario) {
        this.indirizzoDestinatario = indirizzoDestinatario;
    }
    
    public void azzera(){
        indirizzoDestinatario=null;
    }

    void send(String messaggio) throws IOException {
        byte[] responseBuffer = messaggio.getBytes();

        DatagramPacket pacchetto = new DatagramPacket(responseBuffer, responseBuffer.length);

        
        pacchetto.setAddress(indirizzoDestinatario);

        pacchetto.setPort(portaDestinatario);

        socket.send(pacchetto);
    }
}
