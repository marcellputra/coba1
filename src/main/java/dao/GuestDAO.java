/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Guest;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class GuestDAO {

    public void addGuest(Guest guest) throws SQLException {
        String sql = "INSERT INTO tamu (nama, tujuan, pesan, waktu_kunjungan) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getPurpose());
            stmt.setString(3, guest.getMessage());
            stmt.setTimestamp(4, Timestamp.valueOf(guest.getVisitTime()));
            stmt.executeUpdate();
        }
    }

    public List<Guest> getAllGuests() throws SQLException {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT * FROM tamu ORDER BY waktu_kunjungan DESC";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Guest guest = new Guest();
                guest.setId(rs.getInt("id"));
                guest.setName(rs.getString("nama"));
                guest.setPurpose(rs.getString("tujuan"));
                guest.setMessage(rs.getString("pesan"));
                guest.setVisitTime(rs.getTimestamp("waktu_kunjungan").toLocalDateTime());
                guests.add(guest);
            }
        }
        return guests;
    }

    public void deleteGuest(int id) throws SQLException {
        String sql = "DELETE FROM tamu WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
