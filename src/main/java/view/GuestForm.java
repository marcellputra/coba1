package view;

import dao.GuestDAO;
import model.Guest;
import mongodb.MongoLogger;
import util.I18n;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.Locale;

public class GuestForm extends JFrame {

    private JTextField nameField;
    private JTextField purposeField;
    private JTextArea messageArea;
    private JButton submitButton;
    private JComboBox<String> languageSelector;

    public GuestForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle(I18n.get("form.title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ComboBox bahasa
        languageSelector = new JComboBox<>(new String[]{"Bahasa Indonesia", "English"});
        languageSelector.addActionListener(e -> changeLanguage());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(languageSelector);
        add(topPanel, BorderLayout.NORTH);

        // Panel input
        JPanel inputPanel = new JPanel(new GridLayout(6, 1, 5, 5));

        inputPanel.add(new JLabel(I18n.get("label.name")));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel(I18n.get("label.purpose")));
        purposeField = new JTextField();
        inputPanel.add(purposeField);

        inputPanel.add(new JLabel(I18n.get("label.message")));
        messageArea = new JTextArea(3, 20);
        inputPanel.add(new JScrollPane(messageArea));

        add(inputPanel, BorderLayout.CENTER);

        // Tombol submit
        submitButton = new JButton(I18n.get("button.submit"));
        submitButton.addActionListener(this::handleSubmit);
        add(submitButton, BorderLayout.SOUTH);
    }

    private void handleSubmit(ActionEvent e) {
        String name = nameField.getText().trim();
        String purpose = purposeField.getText().trim();
        String message = messageArea.getText().trim();

        if (name.isEmpty() || purpose.isEmpty()) {
            JOptionPane.showMessageDialog(this, I18n.get("dialog.warning"), "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Guest guest = new Guest();
        guest.setName(name);
        guest.setPurpose(purpose);
        guest.setMessage(message);
        guest.setVisitTime(LocalDateTime.now());

        try {
            GuestDAO dao = new GuestDAO();
            dao.addGuest(guest);

            MongoLogger logger = new MongoLogger();
            logger.logAsync(name, "Tamu melakukan check-in");

            JOptionPane.showMessageDialog(this, I18n.get("dialog.success"));
            nameField.setText("");
            purposeField.setText("");
            messageArea.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, I18n.get("dialog.error") + ": " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeLanguage() {
        int selectedIndex = languageSelector.getSelectedIndex();
        if (selectedIndex == 1) {
            I18n.setLocale(new Locale("en"));
        } else {
            I18n.setLocale(new Locale("id"));
        }

        // Refresh UI
        getContentPane().removeAll();
        initComponents();
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        // Gunakan bahasa default Indonesia saat awal
        I18n.setLocale(new Locale("id"));
        SwingUtilities.invokeLater(() -> new GuestForm().setVisible(true));
    }
}
