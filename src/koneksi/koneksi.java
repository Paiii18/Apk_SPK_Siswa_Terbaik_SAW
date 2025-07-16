/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class koneksi {
    
    private java.sql.Connection con;
    public java.sql.Connection connect() {
       try {
           Class.forName("com.mysql.jdbc.Driver");
           System.out.println("KONEKSI BERHASIL");
       } catch (ClassNotFoundException e) {
           System.out.println("KONEKSI GAGAL" +e);
       }
       String url = "jdbc:mysql://localhost:3306/";
       try {
           con = DriverManager.getConnection(url, "root", "");
           System.out.println("KONEKSI DATABASE BERHASIL");
       } catch (SQLException e) {
           System.out.println("GAGAL KONEKSI DATABASE" +e);
       }
       return con;
    }
}
