package LoginPage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Starpunkt der Anwendung
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Dashboard-Erstellt
	 */
	public Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);// Absolutes Layout verwenden ( Zum Platzieren an beliebiger stelle ) 

		setContentPane(contentPane);
		
		JLabel dashboard_Label = new JLabel("DASHBOARD");
		dashboard_Label.setFont(new Font("Arial", Font.BOLD, 20));
		dashboard_Label.setHorizontalAlignment(SwingConstants.CENTER);
		dashboard_Label.setBounds(17, 6, 372, 31);
		contentPane.add(dashboard_Label);
		
		JButton btnBücherVerfügbar = new JButton("Bücher Verfügbar");
		btnBücherVerfügbar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBücherVerfügbar.setBounds(20, 60, 159, 43);
		contentPane.add(btnBücherVerfügbar);
		
		btnBücherVerfügbar.addActionListener(e -> {
		    Bücher_Verfügbar buecherFrame = new Bücher_Verfügbar();
		    buecherFrame.setVisible(true); // Öffnet das Bücher_Verfügbar-Fenster
		});
		
		
		JButton btnBücherHinzufügen = new JButton("Bücher hinzufügen");
		btnBücherHinzufügen.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBücherHinzufügen.setBounds(20, 115, 159, 41);
		contentPane.add(btnBücherHinzufügen);
		
		btnBücherHinzufügen.addActionListener(e -> {
		    Bücher_Hinzufügen buecherFrame2 = new Bücher_Hinzufügen();
		    buecherFrame2.setVisible(true); 
		});
		
		JButton btnBücherLöschen = new JButton("Bücher Löschen");
		btnBücherLöschen.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBücherLöschen.setBounds(20, 168, 159, 42);
		contentPane.add(btnBücherLöschen);
		
		btnBücherLöschen.addActionListener(e -> {
			Bücher_Löschen buecherFrame5 = new Bücher_Löschen();
		    buecherFrame5.setVisible(true); 
		});
		
		JButton btnMitarbeiterDetails = new JButton("Mitarbeiter Details");
		btnMitarbeiterDetails.setFont(new Font("Arial", Font.PLAIN, 15));
		btnMitarbeiterDetails.setBounds(219, 60, 170, 43);
		contentPane.add(btnMitarbeiterDetails);
		
		btnMitarbeiterDetails.addActionListener(e -> {
			Mitarbeiter_details buecherFrame3 = new Mitarbeiter_details();
		    buecherFrame3.setVisible(true); 
		});
		
		JButton btnMitarbeiterHinzufügen = new JButton("Mitarbeiter hinzufügen");
		btnMitarbeiterHinzufügen.setFont(new Font("Arial", Font.PLAIN, 15));
		btnMitarbeiterHinzufügen.setBounds(219, 115, 170, 41);
		contentPane.add(btnMitarbeiterHinzufügen);
		
		btnMitarbeiterHinzufügen.addActionListener(e -> {
		    Mitarbeiter_Hinzufügen buecherFrame4 = new Mitarbeiter_Hinzufügen();
		    buecherFrame4.setVisible(true); 
		});
		
		JButton btnMitarbeiterLöschen = new JButton("Mitarbeiter Löschen");
		btnMitarbeiterLöschen.setFont(new Font("Arial", Font.PLAIN, 15));
		btnMitarbeiterLöschen.setBounds(219, 168, 170, 42);
		contentPane.add(btnMitarbeiterLöschen);
		
		btnMitarbeiterLöschen.addActionListener(e -> {
			Mitarbeiter_Löschen buecherFrame5 = new Mitarbeiter_Löschen();
		    buecherFrame5.setVisible(true); 
		});
		
	
	
	}

}
