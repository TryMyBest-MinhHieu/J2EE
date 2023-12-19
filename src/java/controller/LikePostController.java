package controller;

import java.sql.*;
import database.ConnnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LikePostController {
    Connection con = ConnnectDatabase.getConnection();
    
    public boolean addLike(int id_Account, int id_Post) {
        try {
            String sql = "insert into dbo.[LikePost](id_Account, id_Post) values (?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id_Account);
            stm.setInt(2, id_Post);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LikePostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public boolean UnlikePost(int accID, int postID) {
        try {
            String sql = "delete from dbo.[LikePost] where id_Account = ? and id_Post = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, accID);
            stm.setInt(2, postID);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LikePostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    public boolean isLike(int accID, int postID){
        try {
            String sql = "select * from dbo.[LikePost] where id_Account = "+accID+" and id_Post = "+postID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LikePostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getNumberOfLike(int id_Post){
        int number = 0;
        try {
            String sql = "select * from dbo.[LikePost] where id_Post = "+id_Post;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                number++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LikePostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }   
    
}
