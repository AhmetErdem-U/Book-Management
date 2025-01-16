package LoginPage;

// Importieren der erforderlichen Klassen für die GUI, Datenbank und andere Funktionen
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L; // Serialisierung für JFrame (für konsistente Zustände bei der Speicherung))
    private JPanel contentPane; // Hauptpanel der Anwendung
    private JTextField username_textfeld; // Textfeld für die Eingabe des Benutzernamens
    private JPasswordField password_textfeld; // Textfeld für die Eingabe des Passworts

    /**
     * Hauptmethode: Startpunkt der Anwendung.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { // GUI wird auf dem Event-Dispatch-Thread ausgeführt
            public void run() {
                try {
                    LoginPage frame = new LoginPage(); // Erstellt ein neues Login-Page
                    frame.setVisible(true); // Zeigt das Fenster an
                } catch (Exception e) {
                    e.printStackTrace(); // Gibt Fehler aus, falls das Fenster nicht geladen werden kann
                }
            }
        });
    }

    /**
     * Konstruktor: Erstellt die Login-Seite (GUI).
     */
    public LoginPage() {
        // Setzt das Verhalten beim Schließen des Fensters (beendet die Anwendung)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setzt die Position und Größe des Fensters
        setBounds(100, 100, 450, 300);

        // Initialisierung des Hauptpanels
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Rand für das Panel
        contentPane.setLayout(null); // Absolutes Layout (ermöglicht manuelles Platzieren der Komponenten)
        setContentPane(contentPane); // Setzt das Panel als Inhalt des Fensters

        // Überschrift für die Login-Seite
        JLabel login_Label = new JLabel("LOGIN PAGE");
        login_Label.setHorizontalAlignment(SwingConstants.CENTER);
        login_Label.setFont(new Font("Arial", Font.BOLD, 18));
        login_Label.setBounds(75, 6, 283, 29);
        contentPane.add(login_Label);

        // Beschriftung für das Benutzernamen-Feld
        JLabel username_Label = new JLabel("USERNAME");
        username_Label.setFont(new Font("Arial", Font.BOLD, 15));
        username_Label.setHorizontalAlignment(SwingConstants.CENTER);
        username_Label.setBounds(42, 75, 129, 29);
        contentPane.add(username_Label);

        // Beschriftung für das Passwort-Feld
        JLabel password_Label = new JLabel("PASSWORD");
        password_Label.setHorizontalAlignment(SwingConstants.CENTER);
        password_Label.setFont(new Font("Arial", Font.BOLD, 15));
        password_Label.setBounds(42, 143, 129, 29);
        contentPane.add(password_Label);

        // Eingabefeld für den Benutzernamen
        username_textfeld = new JTextField();
        username_textfeld.setFont(new Font("Arial", Font.BOLD, 15));
        username_textfeld.setBounds(199, 76, 185, 26);
        contentPane.add(username_textfeld);
        username_textfeld.setColumns(10);

        // Eingabefeld für das Passwort
        password_textfeld = new JPasswordField();
        password_textfeld.setFont(new Font("Arial", Font.BOLD, 15));
        password_textfeld.setBounds(199, 144, 185, 26);
        contentPane.add(password_textfeld);

        // Login-Button erstellen
        JButton login_Button = new JButton("LOGIN");
        login_Button.setFont(new Font("Arial", Font.BOLD, 15));
        login_Button.setBounds(126, 199, 185, 36);
        contentPane.add(login_Button);

        // ActionListener hinzufügen: Ruft die Login-Logik auf, wenn der Button geklickt wird
        login_Button.addActionListener(evt -> jButtonlogin_ButtonPerformed(evt));
    }

    /**
     * Login-Logik: Wird ausgeführt, wenn der Login-Button gedrückt wird.
     */
    public void jButtonlogin_ButtonPerformed(java.awt.event.ActionEvent evt) {
        // Verbindung zur MySQL-Datenbank
        String url = "jdbc:mysql://localhost:3306/bibliothek?useSSL=false"; // URL der MySQL-Datenbank
        String mysqluser = "root"; // MySQL-Benutzername
        String mysqlpwd = "Tyzrfds38!"; // MySQL-Passwort

        // Eingegebene Werte aus den Textfeldern
        String pswrd = new String(password_textfeld.getPassword()); // Eingegebenes Passwort
        String username = username_textfeld.getText(); // Eingegebener Benutzername

        // SQL-Query: Holt das Passwort aus der Datenbank basierend auf den Benutzernamen
        String query = ("SELECT Passwort FROM admin WHERE Benutzer_ID='" + username + "'");

        try {
            // Verbindung zur MySQL-Datenbank herstellen
            Connection conn = DriverManager.getConnection(url, mysqluser, mysqlpwd);

            // Statement erstellen und Query ausführen
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);

            // Überprüfen, ob der Benutzer existiert
            if (rs.next()) {
                // Passwort aus der Datenbank
                String realpswrd = rs.getString("Passwort");

                // Überprüfung: Stimmen die Passwörter überein?
                if (realpswrd.equals(pswrd)) {
                    // Öffne das Dashboard-Fenster
                    Dashboard dsh = new Dashboard();
                    dsh.setVisible(true);
                    this.dispose(); // Schließt die Login-Seite
                } else {
                    // Fehlermeldung bei falschem Passwort
                    JOptionPane.showMessageDialog(this, "Name oder Passwort ist falsch!");
                }
            } else {
                // Fehlermeldung bei falschem Benutzernamen
                JOptionPane.showMessageDialog(this, "Falscher Username!");
            }
        } catch (Exception e) {
            // Zeigt die Fehlermeldung an, falls ein Fehler auftritt
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
