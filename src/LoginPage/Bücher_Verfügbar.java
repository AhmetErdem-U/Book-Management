package LoginPage;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Bücher_Verfügbar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table; // Tabelle für die Anzeige der Bücherdaten

    /**
     * Diese Methode startet die Anwendung und zeigt das Fenster 'Bücher_Verfügbar' an.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Bücher_Verfügbar frame = new Bücher_Verfügbar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Der Konstruktor erstellt das GUI-Fenster, fügt die Tabelle und den Zurück-Button hinzu und lädt die Daten aus der Datenbank.
     */
    public Bücher_Verfügbar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // SChließt die Anwendung bei Fenster Schließung
        setBounds(100, 100, 600, 500); // Fenstergröße 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        setContentPane(contentPane);

        // Tabellenmodell erstellen
        DefaultTableModel model = new DefaultTableModel();

        // Spaltennamen hinzufügen
        model.addColumn("Bücher_ID");
        model.addColumn("Kategorie");
        model.addColumn("Name");
        model.addColumn("Autor");
        model.addColumn("Kopien");

        // Daten aus der Datenbank laden
        loadTableData(model);

        // Tabelle erstellen
        table = new JTable(model);
        
        // Tabelle in ein ScrollPane einfügen
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 560, 340); // Position und Größe des ScrollPane
        contentPane.add(scrollPane);

        // Zurück-Button erstellen
        JButton backButton = new JButton("Zurück");
        backButton.setBounds(250, 400, 100, 30); // Position und Größe des Buttons
        contentPane.add(backButton);

        // ActionListener für den Zurück-Button
        backButton.addActionListener(e -> dispose()); // Schließt das aktuelle Fenster
    }

    /**
     * Lädt die Daten aus der Datenbank in das Tabellenmodell.
     * Diese Methode verbindet sich mit der Datenbank, führt die SQL-Abfrage aus und fügt die Ergebnisse in das Tabellenmodell ein.
     */
    private void loadTableData(DefaultTableModel model) {
        String url = "jdbc:mysql://localhost:3306/bibliothek?useSSL=false"; // URL der Datenbank
        String user = "root"; // Benutzername der Datenbank
        String password = "Tyzrfds38!"; // Passwort der Datenbank
        String query = "SELECT * FROM Bücher"; // SQL-Abfrage zum Abrufen aller Bücher

        try (Connection conn = DriverManager.getConnection(url, user, password); // Verbindung zur Datenbank herstellen
             Statement stmt = conn.createStatement(); // Statement für die Abfrage 
             ResultSet rs = stmt.executeQuery(query)) { // Abfrage ausführen und Ergebnisse speichern

            while (rs.next()) {
                // Liest die Daten aus der aktuellen Zeile des ResultSet
                String id = rs.getString("Buch_ID");
                String kategorie = rs.getString("Kategorie");
                String name = rs.getString("Name");
                String autor = rs.getString("Autor");
                int kopien = rs.getInt("Kopien");

                // Fügt eine neue Zeile in das Tabellenemodell ein
                model.addRow(new Object[] { id, kategorie, name, autor, kopien }); 
            }

        } catch (Exception e) {
            e.printStackTrace(); // Fehler ausgeben, falls ein Problem auftritt
        }
    }
}