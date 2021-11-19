/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsaap;

/**
 *
 * @author locatelli_andrea
 */
public class messaggio {
  

    int id;
    String contenuto;
    boolean tipo; 
    boolean attessa; 

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
}
