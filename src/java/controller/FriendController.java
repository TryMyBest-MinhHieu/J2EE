
package controller;

import database.ConnnectDatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Friend;

public class FriendController {

    Connection con = ConnnectDatabase.getConnection();
    
    public List<Friend> getFriend(int accID){
        try {
            List<Friend> list = new ArrayList<>();
            String sql = "select * from Friend where AccID = "+accID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                int friendID = rs.getInt("FriendID");
                Friend friend = new Friend(accID, friendID);
                list.add(friend);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(FriendController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean isFriend(int accID, int friendID){
        try {
            String sql = "select * from Friend where AccID = "+accID+" and FriendID = "+friendID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean addFriend(int accID, int friendID){
        try {
            String sql = "insert into Friend(AccID, FriendID) values(?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, accID);
            stm.setInt(2, friendID);
            if(stm.executeUpdate()>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
     
    public boolean UnFriend(int accID, int friendID){
        try {
            String sql = "delete from Friend where AccID = ? and FriendID = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, accID);
            stm.setInt(2, friendID);
            if(stm.executeUpdate()>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
}
