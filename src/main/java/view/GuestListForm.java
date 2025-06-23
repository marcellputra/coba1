/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.GuestDAO;
import model.Guest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USER
 */
public class GuestListForm extends JFrame {

    private JTable guestTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton, deleteButton;

    public GuestListForm() {
        setTitle("Daftar Tamu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tabel
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Tujuan", "Pesan", "Waktu Kunjungan"}, 0);
        guestTable = new JTable(tableModel);
        add(new JScrollPane(guestTable), BorderLayout.CENTER);

        // Panel tombol
        JPanel buttonPanel = new JPanel();
        refreshButton = new JButton("Refresh");
        deleteButton = new JButton("Hapus");

        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Aksi
        refreshButton.addActionListener(e -> loadGuests());
        deleteButton.addActionListener(e -> deleteSelectedGuest());

        // Load awal
        loadGuests();
    }

    private void loadGuests() {
        tableModel.setRowCount(0);
        try {
            GuestDAO dao = new GuestDAO();
            List<Guest> guests = dao.getAllGuests();
            for (Guest g : guests) {
                tableModel.addRow(new Object[]{
                    g.getId(),
                    g.getName(),
                    g.getPurpose(),
                    g.getMessage(),
                    g.getVisitTime()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedGuest() {
        int selectedRow = guestTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int guestId = (int) tableModel.getValueAt(selectedRow, 0);
                try {
                    GuestDAO dao = new GuestDAO();
                    dao.deleteGuest(guestId);
                    loadGuests();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih salah satu data yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
}
