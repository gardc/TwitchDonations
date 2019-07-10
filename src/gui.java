import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.JTextArea;

public class gui {

	private JFrame frmTwitchDonationGenerator;
	private JTextField messageText;
	private JTextField ammountText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frmTwitchDonationGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTwitchDonationGenerator = new JFrame();
		frmTwitchDonationGenerator.setType(Type.UTILITY);
		frmTwitchDonationGenerator.setResizable(false);
		frmTwitchDonationGenerator.setTitle("Twitch donation generator by gnerd");
		frmTwitchDonationGenerator.setBounds(100, 100, 450, 356);
		frmTwitchDonationGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frmTwitchDonationGenerator.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblEnterTwitchDonation = new JLabel("Enter twitch donation message");
		lblEnterTwitchDonation.setBounds(50, 12, 244, 15);
		layeredPane.add(lblEnterTwitchDonation);
		
		messageText = new JTextField();
		messageText.setBounds(50, 33, 340, 28);
		layeredPane.add(messageText);
		messageText.setColumns(10);
		
		JRadioButton rdbtnNorsk = new JRadioButton("Norsk");
		JRadioButton rdbtnEnglish = new JRadioButton("English");
		rdbtnEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnNorsk.setSelected(false);
			}
		});
		
		JLabel lblAmmount = new JLabel("Ammount");
		lblAmmount.setBounds(50, 73, 244, 15);
		layeredPane.add(lblAmmount);
		
		ammountText = new JTextField();
		ammountText.setColumns(10);
		ammountText.setBounds(50, 89, 340, 28);
		layeredPane.add(ammountText);
		rdbtnEnglish.setSelected(true);
		rdbtnEnglish.setBounds(50, 129, 88, 23);
		layeredPane.add(rdbtnEnglish);
		
		//JRadioButton rdbtnNorsk = new JRadioButton("Norsk");
		rdbtnNorsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnEnglish.setSelected(false);
			}
		});
		rdbtnNorsk.setBounds(150, 129, 144, 23);
		layeredPane.add(rdbtnNorsk);
		
		// Output text
		JTextArea outputText = new JTextArea();
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = messageText.getText();
				
				if(rdbtnEnglish.isSelected())
				{
					text = "/me donated $" + ammountText.getText() + " with the message: " + text;
				}
				else
				{
					text = "/me donerte kr " + ammountText.getText() + " med meldingen: " + text;
				}
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				
				outputText.setText(outputText.getText() + dtf.format(now) + " Copied donation to clipboard.\n");
				
				/* Clipboard */
				StringSelection stringSelection = new StringSelection(text);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		btnGenerate.setBounds(50, 160, 340, 25);
		layeredPane.add(btnGenerate);
		
		JLabel lblOutputLog = new JLabel("Output log");
		lblOutputLog.setBounds(50, 202, 128, 15);
		layeredPane.add(lblOutputLog);
		
		//JTextArea outputText = new JTextArea();
		outputText.setEditable(false);
		outputText.setBounds(50, 229, 340, 78);
		layeredPane.add(outputText);
	}
}
