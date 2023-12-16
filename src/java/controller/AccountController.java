/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;
import database.ConnnectDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author HP ADMIN
 */
public class AccountController {

    Connection con = ConnnectDatabase.getConnection();

    public Account getAccount(String username, String password) {
        Account acc = new Account();
        try {
            String sql = "select * from Account where Email = '" + username + "' and Password ='" + password + "'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                acc.setUserID(rs.getInt("UserID"));
                acc.setUsername(rs.getNString("UserName"));
                acc.setPassword(rs.getString("Password"));
                acc.setEmail(rs.getString("Email"));
                acc.setBio(rs.getString("Bio"));
                acc.setAccID(rs.getInt("AccID"));
                acc.setImage(rs.getString("Image"));
            }
            return acc;
        } catch (SQLException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account getAccountByID(int accID) {
        Account acc = new Account();
        try {
            String sql = "select * from Account where AccID = " + accID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                acc.setUserID(rs.getInt("UserID"));
                acc.setUsername(rs.getString("UserName"));
                acc.setPassword(rs.getString("Password"));
                acc.setEmail(rs.getString("Email"));
                acc.setBio(rs.getString("Bio"));
                acc.setAccID(rs.getInt("AccID"));
                acc.setImage(rs.getString("Image"));
            }
            return acc;
        } catch (SQLException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkLogin(String username, String password) {
        try {
            String sql = "select * from Account where Email = '" + username + "' and Password ='" + password + "'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkEmail(String email) {
        try {
            String sql = "select * from Account where Email = '" + email + "'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean addAcc(Account acc) {
        try {
            String sql = "insert into Account(UserName, Password, Bio, Email, Image, UserID) values(?,?,?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setNString(1, acc.getUsername());
            stm.setNString(2, acc.getPassword());
            stm.setNString(3, acc.getBio());
            stm.setNString(4, acc.getEmail());
            stm.setNString(5, acc.getImage());
            stm.setInt(6, acc.getUserID());
            if (stm.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            /*try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
        return false;
    }

    public boolean updateImg(String img, int accID) {
        try {
            String sql = "update Account set Image = ? where AccID = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setNString(1, img);
            stm.setInt(2, accID);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean updateAcc(Account acc) {
        try {
            String sql = "update Account set UserName = ?, Bio = ?, Email = ?, Image = ? where AccID = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setNString(1, acc.getUsername());
            stm.setNString(2, acc.getBio());
            stm.setNString(3, acc.getEmail());
            stm.setNString(4, acc.getImage());
            stm.setInt(5, acc.getAccID());
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Account> searchAcc(String username) {
        try {
            List<Account> accounts = new ArrayList<>();
            String sql = "select * from ACCOUNT where UserName LIKE N'%" + username + "%'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Account acc = new Account();
                acc.setUserID(rs.getInt("UserID"));
                acc.setUsername(rs.getNString("UserName"));
                acc.setPassword(rs.getString("Password"));
                acc.setEmail(rs.getString("Email"));
                acc.setBio(rs.getNString("Bio"));
                acc.setAccID(rs.getInt("AccID"));
                acc.setImage(rs.getString("Image"));
                accounts.add(acc);
            }
            return accounts;
        } catch (SQLException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
