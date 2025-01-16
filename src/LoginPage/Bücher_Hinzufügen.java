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

public class Bücher_Hinzufügen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Startpunkt der Anwendung
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bücher_Hinzufügen frame = new Bücher_Hinzufügen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Erstellt das Gui Fenster 
	 */
	public  Bücher_Hinzufügen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		  contentPane.setLayout(null);

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Buch-ID");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(36, 23, 77, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblKategorie = new JLabel("Kategorie");
		lblKategorie.setFont(new Font("Arial", Font.PLAIN, 15));
		lblKategorie.setBounds(36, 53, 77, 18);
		contentPane.add(lblKategorie);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial", Font.PLAIN, 15));
		lblName.setBounds(36, 87, 77, 18);
		contentPane.add(lblName);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Arial", Font.PLAIN, 15));
		lblAutor.setBounds(36, 117, 77, 18);
		contentPane.add(lblAutor);
		
		JLabel lblKopien = new JLabel("Kopien");
		lblKopien.setFont(new Font("Arial", Font.PLAIN, 15));
		lblKopien.setBounds(36, 147, 77, 18);
		contentPane.add(lblKopien);
		
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
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_3.setColumns(10);
		textField_3.setBounds(197, 113, 213, 28);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(197, 143, 213, 28);
		contentPane.add(textField_4);
		
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
	
	/**
	* Fügt ein Buch der Datenbank hinzu, 
	* Üverprüft,ob das Buch bereits existiert, und erhöht andernfalls die Kopienanzahl
	*/
	private void Hinzufügen(java.awt.event.ActionEvent evt) {
        String url = "jdbc:mysql://localhost:3306/bibliothek"; // URL der Datenbank
        String user = "root"; // Benutzername der Datenbank
        String password = "Tyzrfds38!"; // Passwort der Datenbank
        String query = "Insert into Bücher values(?,?,?,?,?)"; // SQL-Abfrage
        
        // Eingabewerte aus den Textfeldern
        String id=textField.getText();
        String kategorie=textField_1.getText();
        String name=textField_2.getText();
        String autor=textField_3.getText();
        int kopien= Integer.parseInt(textField_4.getText());
        
        // Abfrage zur Überprüfung, ob das Buch bereits existiert, und zur Erhöhung der Kopienanzahl
        String checkquery = "Update Bücher set kopien=kopien+"+kopien+" where Name='"+name+"'and Kategorie='"+kategorie+"'and Autor='"+autor+"';"; 
        
        

        try  { // Abfrage ausführen und Ergebnisse abrufen

        	Connection conn = DriverManager.getConnection(url, user, password); // Verbindung zur Datenbank herstellen
            Statement stmt = conn.createStatement(); // Statement für die Abfrage erstellen
             int rows=stmt.executeUpdate(checkquery); // Überprüfen ob das Buch bereits existiert
           
             if(rows>0) { 
            	 // Wenn das Buch existiert -> Kopienanzahl erhöht
            	JOptionPane.showMessageDialog(this, "Ein Buch wurde Hinzugefügt!");
            } else {
            	// Wenn das Buch nicht existiert -> Neuer Eintrag erstellt
            	
            	PreparedStatement stm = conn.prepareCall(query);
            	stm.setString(1, id);
            	stm.setString(2, kategorie);
            	stm.setString(3, name);
            	stm.setString(4, autor);
            	stm.setInt(5, kopien);
            	stm.execute();
            	JOptionPane.showMessageDialog(this, "Ein Buch wurde Erfolgreich Hinzugefügt");
            	
            }
             // Zurücksetzen der Eingabefelder
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
