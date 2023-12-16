/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.ConnnectDatabase;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author HP ADMIN
 */
public class UserController {

    Connection con = ConnnectDatabase.getConnection();

    public int getUserID(String name, String dob, String sex){
        try {
            String sql = "select * from dbo.[User] where Name = N'"+name+"' and DoB = '"+dob+"' and Sex = '"+sex+"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                int userID = rs.getInt("UserID");
                return userID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public boolean addUser(User user) {
        try {
            String sql = "insert into dbo.[User](Name, DoB, Sex) values(?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setNString(1, user.getName());
            stm.setNString(2, user.getDob());
            stm.setNString(3, user.getSex());
            if (stm.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            /*try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
        return false;
    }
    
    public User getUser(int userID){
        try {
            String sql = "select * from dbo.[User] where UserID = "+userID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                User user = new User();
                user.setUserID(userID);
                user.setName(rs.getNString("Name"));
                user.setDob(rs.getNString("DoB"));
                user.setSex(rs.getNString("Sex"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean updateUser(User user){
        try {
            String sql = "update dbo.[User] set Name = ?, DoB = ?, Sex = ? where UserID = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setNString(1, user.getName());
            stm.setNString(2, user.getDob());
            stm.setNString(3, user.getSex());
            stm.setInt(4, user.getUserID());
            if(stm.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
