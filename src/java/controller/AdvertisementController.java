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
import model.Advertisement;

/**
 *
 * @author HP ADMIN
 */
public class AdvertisementController {
    Connection con = ConnnectDatabase.getConnection();
    
    public List<Advertisement> getAll(){
        try {
            List<Advertisement> list = new ArrayList<>();
            String sql = "select * from Advertisement";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("Id");
                String image = rs.getNString("Image");
                String dis = rs.getNString("Discription");
                Advertisement ad = new Advertisement(id, image, dis);
                list.add(ad);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AdvertisementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
