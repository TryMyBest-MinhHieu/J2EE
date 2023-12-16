/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP ADMIN
 */
public class Comment {
    private int cmtID;
    private String content;
    private int postID;
    private int accID;

    public Comment() {
    }

    public Comment(int cmtID, String content, int postID, int accID) {
        this.cmtID = cmtID;
        this.content = content;
        this.postID = postID;
        this.accID = accID;
    }

    public int getCmtID() {
        return cmtID;
    }

    public void setCmtID(int cmtID) {
        this.cmtID = cmtID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }
    
}
