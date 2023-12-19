/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MinhHieu
 */
public class Message {
    private int id;
    private int id_Receive;
    private int id_Send;
    private String noidung;
    
    public Message(){}
        public Message(int id, int id_Receive, int id_Send, String noidung) {
        this.id = id;
        this.id_Receive = id_Receive;
        this.id_Send = id_Send;
        this.noidung = noidung;
    }
        
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Receive() {
        return id_Receive;
    }

    public void setId_Receive(int id_Receive) {
        this.id_Receive = id_Receive;
    }

    public int getId_Send() {
        return id_Send;
    }

    public void setId_Send(int id_Send) {
        this.id_Send = id_Send;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
    
}
