package controller;

import database.ConnnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;

public class MessageController {

    Connection con = ConnnectDatabase.getConnection();

    public boolean addMess(Message ms) {
        try {
            String sql = "Insert into dbo.[Message](id_Send, id_Receive, noidung) values (?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, ms.getId_Send());
            stm.setInt(2, ms.getId_Receive());
            stm.setString(3, ms.getNoidung());
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LikePostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isMess(int id_send, int id_receive) {
        try {
            String sql = "select * from dbo.[Message] where id_Send = " + id_send + " and id_Receive = " + id_receive;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteMess(int id) {
        try {
            String sql = "delete from dbo.[Message] where id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LikePostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteMessbyId(int id_send, int id_receive){
        try {
            String sql = "delete from dbo.[Message] where id_Send = ? and id_Receive = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id_send);
            stm.setInt(2, id_receive);
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<Message> getMessage(int id_Receive) {
        try {
            List<Message> list = new ArrayList<>();
            String sql = "select * from dbo.[Message] where id_Receive = " + id_Receive;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Message ms = new Message();
                ms.setId(rs.getInt("id"));
                ms.setId_Send(rs.getInt("id_Send"));
                ms.setId_Receive(rs.getInt("id_Receive"));
                ms.setNoidung(rs.getString("noidung"));
                list.add(ms);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
