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

public class Mitarbeiter_details extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 private JTable table; // Tabelle für die Darstellung der Mitarbeiter

	/**
	 * Startpunkt der Anwendung
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mitarbeiter_details frame = new Mitarbeiter_details();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Erstellt das GUI-Fenster
	 */
	public Mitarbeiter_details() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 contentPane.setLayout(null);

		setContentPane(contentPane);
		 // Tabellenmodell erstellen
        DefaultTableModel model = new DefaultTableModel();

        // Spaltennamen hinzufügen
        model.addColumn("Mitarbeiter_ID");
        model.addColumn("Name");
        model.addColumn("Kontakt");
     

        // Daten aus der Datenbank laden
        loadMitarbeiterData(model);

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
    private void loadMitarbeiterData(DefaultTableModel model) {
    	
    
        String url = "jdbc:mysql://localhost:3306/bibliothek?useSSL=false"; // URL der Datenbank
        String user = "root"; // Benutzername der Datenbank
        String password = "Tyzrfds38!"; // Passwort der Datenbank
        String query = "SELECT * FROM Mitarbeiter"; // SQL-Abfrage zur Auswahl aller Mitarbeiter

        try (Connection conn = DriverManager.getConnection(url, user, password); // Verbindung zur Datenbank herstellen
             Statement stmt = conn.createStatement(); // Statement für die Abfrage erstellen
             ResultSet rs = stmt.executeQuery(query)) { // Abfrage ausführen und Ergebnisse abrufen

            while (rs.next()) {
                // Daten aus der ResultSet holen und dem Modell hinzufügen
                String id = rs.getString("Mitarbeiter_ID");
                String name = rs.getString("Name");
                int kontakt = rs.getInt("Kontakt");
             

                model.addRow(new Object[] { id, name, kontakt }); // Neue Zeile in das Modell einfügen
            }

        } catch (Exception e) {
            e.printStackTrace(); // Fehler ausgeben, falls ein Problem auftritt
        }
    }

	}


