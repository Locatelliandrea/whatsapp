/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsaap;

import java.util.ArrayList;

/**
 *
 * @author locatelli_andrea
 */
public class Messaggi {
       ArrayList<messaggio> Messaggi;
       int id;
       boolean comunicazione;
       boolean stampato;
        public Messaggi() {
        id = 0;
        Messaggi = new ArrayList<messaggio>();
        comunicazione = false;
        stampato=false;
    }
          public synchronized void aggiungi(String s, boolean tipo) {
        Messaggi.add(new messaggio(s, id++, tipo));
    }
          
          
       public messaggio controllo() {
        messaggio m = new messaggio();
        for (int i = 0; i < Messaggi.size(); i++) {
            if (!Messaggi.get(i).attessa) {
                Messaggi.get(i).attessa = true;
                return Messaggi.get(i);
            }
        }
        return m;
    }      
          
          
          
          
          
          
}
