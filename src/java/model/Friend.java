/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP ADMIN
 */
public class Friend {
    private int Id;
    private int accID;
    private int friendID;

    public Friend() {
    }

    public Friend(int accID, int friendID) {
        this.accID = accID;
        this.friendID = friendID;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public int getfriendID() {
        return friendID;
    }

    public void setfriendID(int friendID) {
        this.friendID = friendID;
    }
    
    
}
