/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/bukutamu_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = ""; // sesuaikan passwordmu

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                System.out.println("Koneksi berhasil ke database.");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Gagal koneksi ke database: " + e.getMessage());
                throw new SQLException("Tidak dapat konek ke database", e);
            }
        }
        return connection;
    }
}
