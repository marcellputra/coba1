/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import view.GuestForm;

/**
 *
 * @author USER
 */
public class Main {

    public static void main(String[] args) {
        // Atur tampilan look and feel bawaan OS
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Gagal set look and feel: " + e.getMessage());
        }

        // Jalankan form GUI dalam thread GUI
        javax.swing.SwingUtilities.invokeLater(() -> {
            new GuestForm().setVisible(true);
        });
    }
}
