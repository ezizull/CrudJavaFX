/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author ezizu
 */
public class DbConnect {

    public static Connection getConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/jadwal", "root", "");
            
            return conn;
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("connectimysql : "+ex.getMessage());
            
            return null;
        }

    }
}
