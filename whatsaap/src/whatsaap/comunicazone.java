/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsaap;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JOptionPane;

/**
 *
 * @author Locatelli_andrea
 */
public class comunicazone {
        JFrame f;
    static comunicazone c;
    Thread_attesa thread;
    messaggio m;
    String NomeDestinatario;
    String NomeMittente;
    String operazione;
    String dato;
    
    public comunicazone() throws SocketException {
        
        
        this.operazione = "";
        this.dato = "";
        NomeDestinatario = null;
        NomeMittente = null;
        c = null;
    }
    
    public void azzera() throws SocketException {
        this.m.azzera();
        this.operazione = "";
        this.dato = "";
        NomeDestinatario = null;
        c = null;
    }
    
    public comunicazone(String nome) throws SocketException {
        
       
        this.operazione = "";
        this.dato = "";
        NomeDestinatario = null;
        NomeMittente = nome;
        c = null;
    }
    
    public void setInvia(InetAddress address) throws SocketException {
        this.m.setIndirizzoDestinatario(address);
        
    }
    
    public void setOperazione(String operazione) {
        this.operazione = operazione;
    }
    
    public void setDato(String dato) {
        this.dato = dato;
    }
    
    public static synchronized comunicazone getComunicazione() throws SocketException {
        if (c == null) {
            c = new comunicazone();
        }
        return c;
    }
    
    public void creaMessaggioEInvia() throws IOException {
        if (operazione.equals("a")) {
            if (NomeDestinatario == null) {
                int option = JOptionPane.showConfirmDialog(null, "accettare la comunicazione con:" + this.dato, "richiesta", JOptionPane.YES_NO_OPTION);
                
                if (option == 0) { //The ISSUE is here
                    NomeDestinatario = this.dato;
                    m.send("y;" + NomeMittente);
                 
                    
                } else {
                    m.send("n;;");
                    azzera();
                    m.azzera();    
                    thread.vecchio = null;                    
                }
            } else {
                m.send("n;;");
                thread.vecchio = null;               
            }
            
        } else if (operazione.equals("m")) {
    //creare grafica messaggio            
        } else if (operazione.equals("c")) {           
            azzera();
            m.azzera();
            thread.vecchio = null;
        } else if (operazione.equals("y")) {
          //creare grafica messaggio 
        } else if (operazione.equals("n")) {
            azzera();
            m.azzera();
            thread.vecchio = null;           
        } 
    }

    
    public void InviaMessaggio(String m) throws IOException {
        this.m.send("m;" + m);
    }

    //invia chiusura da form
    public void chiudicomunicazione() throws IOException {
        this.m.send("c;;");
        azzera();
        m.azzera();        
    }

    //invia comunicazione da form
    public void InviaRichiestaComunicazione(String indirizzo,JFrame f) throws IOException {
        this.f=f;
        m.setIndirizzoDestinatario(InetAddress.getByName(indirizzo));
        this.m.send("a;" + this.NomeMittente);
        
    }
}
