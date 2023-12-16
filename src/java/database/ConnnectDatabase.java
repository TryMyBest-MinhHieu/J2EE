/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnnectDatabase {
    static Connection con;
    public static Connection getConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String conStr = "jdbc:sqlserver://localhost:1433;databaseName=db";
            con = DriverManager.getConnection(conStr, "sa", "hieuhieudo");
            return con;
        } catch (Exception ex) {
            Logger.getLogger(ConnnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
            
}
