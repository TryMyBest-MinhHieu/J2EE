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
import model.Post;

/**
 *
 * @author HP ADMIN
 */
public class PostController {

    Connection con = ConnnectDatabase.getConnection();
    public List<Post> getPosts(int accID){
        List<Post> posts = new ArrayList<>();
        try {
            String sql = "select * from Post where AccID ="+accID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                int postID = rs.getInt("PostID");
                String caption = rs.getNString("Caption");
                String date = rs.getNString("Date");
                String image = rs.getNString("Image");
                Post post = new Post(postID, caption, image, date, accID);
                posts.add(post);
            }
            return posts;
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Post> getPostsFriend(int accID){
        List<Post> posts = new ArrayList<>();
        try {
            String sql = "select * from dbo.[Friend] as f, Post as p where f.FriendID = p.AccID and f.AccID ="+accID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                int postID = rs.getInt("PostID");
                String caption = rs.getNString("Caption");
                String date = rs.getNString("Date");
                String image = rs.getNString("Image");
                int accountID = rs.getInt("FriendID");
                Post post = new Post(postID, caption, image, date, accountID);
                posts.add(post);
            }
            return posts;
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Post getPostByID(int postID){
        try {
            String sql = "select * from Post where PostID ="+postID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                String caption = rs.getNString("Caption");
                String image = rs.getNString("Image");
                int accID = rs.getInt("AccID");
                String date = rs.getNString("Date");
                Post post = new Post(postID, caption, image, date, accID);
                return post;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean addPost(Post post){
        try {
            String sql = "insert into Post(Caption, Image, AccID, Date) values(?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setNString(1, post.getCaption());
            stm.setNString(2, post.getImage());
            stm.setInt(3, post.getAccID());
            stm.setNString(4, post.getDate());
            if(stm.executeUpdate() >0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
