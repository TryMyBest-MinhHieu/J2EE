/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.ConnnectDatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comment;

/**
 *
 * @author HP ADMIN
 */
public class CommentController {
    Connection con = ConnnectDatabase.getConnection();
    public int countCmt(int postID){
        try {
            int count = 0;
            String sql = "select * from Comment where PostID = "+postID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                count++;
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public boolean addCmt(Comment cmt){
        try {
            String sql = "insert into Comment(CmtContent, PostID, AccID) values(?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setNString(1, cmt.getContent());
            stm.setInt(2, cmt.getPostID());
            stm.setInt(3, cmt.getAccID());
            if(stm.executeUpdate() >0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<Comment> getCommentOfPost(int postID){
        try {
            List<Comment> cmts = new ArrayList<>();
            String sql = "select * from Comment where PostID = "+postID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                int cmtID = rs.getInt("CmtID");
                String cmtContent = rs.getNString("CmtContent");
                int accID = rs.getInt("accID");
                Comment cmt = new Comment(cmtID, cmtContent, postID, accID);
                cmts.add(cmt);
            }
            return cmts;
        } catch (SQLException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}
