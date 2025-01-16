package LoginPage;



import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Mitarbeiter_Hinzufügen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Start der Anwendung
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mitarbeiter_Hinzufügen frame = new Mitarbeiter_Hinzufügen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * GUI Fenster erstellen
	 */
	public  Mitarbeiter_Hinzufügen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		  contentPane.setLayout(null);

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Mitarbeiter-ID");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(60, 23, 90, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial", Font.PLAIN, 15));
		lblName.setBounds(60, 53, 90, 25);
		contentPane.add(lblName);
		
		JLabel lblKategorie = new JLabel("Kontakt");
		lblKategorie.setFont(new Font("Arial", Font.PLAIN, 15));
		lblKategorie.setBounds(60, 87, 90, 25);
		contentPane.add(lblKategorie);
		
		
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		textField.setBounds(197, 13, 213, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(197, 49, 213, 28);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(197, 83, 213, 28);
		contentPane.add(textField_2);
		
		
		
		JButton btnHinzufügen = new JButton("Hinzufügen");
		btnHinzufügen.setFont(new Font("Arial", Font.BOLD, 20));
		btnHinzufügen.setBounds(50, 188, 129, 53);
		contentPane.add(btnHinzufügen);
		
		 btnHinzufügen.addActionListener(evt -> Hinzufügen(evt));
		
		JButton btnAbbruch = new JButton("Abbruch");
		btnAbbruch.setFont(new Font("Arial", Font.BOLD, 20));
		btnAbbruch.setBounds(243, 188, 129, 53);
		contentPane.add(btnAbbruch);
		
		btnAbbruch.addActionListener(e -> dispose()); 
	}
	private void Hinzufügen(java.awt.event.ActionEvent evt) {
        String url = "jdbc:mysql://localhost:3306/bibliothek"; // URL der Datenbank
        String user = "root"; // Benutzername der Datenbank
        String password = "Tyzrfds38!"; // Passwort der Datenbank
        String query = "Insert into Mitarbeiter values(?,?,?)"; // SQL-Abfrage
        
        // Werte aus den Textfeldern lesen
        String id=textField.getText();
        String name=textField_1.getText();
        int kontakt= Integer.parseInt(textField_2.getText());
       
        

        try  { // Abfrage ausführen und Ergebnisse abrufen

        	Connection conn = DriverManager.getConnection(url, user, password); // Verbindung zur Datenbank herstellen
            PreparedStatement stmt = conn.prepareCall(query); // Statement für die Abfrage erstellen
           
            	stmt.setString(1, id);
            	
            	stmt.setString(2, name);
            
            	stmt.setInt(3, kontakt);
            	
            	stmt.execute();
            	JOptionPane.showMessageDialog(this, "Ein Mitarbeiter wurde Erfolgreich Hinzugefügt");
            	
            
             textField.setText(null);
             textField_1.setText(null);
             textField_2.setText(null);
             textField_3.setText(null);
             textField_4.setText(null);
        
        } catch (Exception e) {
            e.printStackTrace(); // Fehler ausgeben, falls ein Problem auftritt
        }
    }
}
