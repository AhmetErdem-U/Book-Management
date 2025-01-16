package LoginPage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Bücher_Löschen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bücher_Löschen frame = new Bücher_Löschen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bücher_Löschen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 contentPane.setLayout(null);

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Buch Name / ID eingeben zum Löschen!");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(25, 34, 419, 24);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.ITALIC, 15));
		textField.setBounds(25, 85, 376, 43);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Löschen");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 17));
		btnNewButton.setBounds(37, 167, 143, 63);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(evt -> Löschen(evt));
		
		JButton btnZurück = new JButton("Zurück");
		btnZurück.setFont(new Font("Arial", Font.BOLD, 17));
		btnZurück.setBounds(258, 167, 143, 63);
		contentPane.add(btnZurück);
		
		btnZurück.addActionListener(e -> dispose()); 
		
	}

	private void Löschen(java.awt.event.ActionEvent evt) {
        String url = "jdbc:mysql://localhost:3306/bibliothek"; // URL der Datenbank
        String user = "root"; // Benutzername der Datenbank
        String password = "Tyzrfds38!"; // Passwort der Datenbank
        String id=textField.getText();
        
     // SQL-Abfrage zum Löschen der Bücher mit der gleichen Buch_Id / Namen
        String query = "Delete From  Bücher where Buch_ID='"+id+"'or Name='"+id+"';";  
        
 
        
        
        try  { // Abfrage ausführen und Ergebnisse abrufen

        	Connection conn = DriverManager.getConnection(url, user, password); // Verbindung zur Datenbank herstellen
            Statement stmt = conn.createStatement(); // Statement für die Abfrage erstellen
           int rows=stmt.executeUpdate(query);
           
             if(rows>0) { 
            	 // Wenn der Name oder die ID Vorhanden ist -> Löschen
            	JOptionPane.showMessageDialog(this, "Ein Buch wurde Gelöscht!");
            } else {
            	
            	JOptionPane.showMessageDialog(this, "Nicht Vorhanden!");
            	
            }
             textField.setText(null);
             
        
        } catch (Exception e) {
            e.printStackTrace(); // Fehler ausgeben, falls ein Problem auftritt
        }
    }
}
