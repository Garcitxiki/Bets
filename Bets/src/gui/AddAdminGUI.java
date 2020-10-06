package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddAdminGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblError=null;
	private BLFacade businessLogic= LoginGUI.getBusinessLogic();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAdminGUI frame = new AddAdminGUI();
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
	public AddAdminGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("AddAdmin"));
		textField = new JTextField();
		textField.setBounds(12, 76, 299, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblSetAdmin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UsAd"));
		lblSetAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSetAdmin.setBounds(12, 47, 299, 16);
		contentPane.add(lblSetAdmin);
		
		lblError = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErUs"));
		lblError.setBounds(35, 165, 173, 16);
		lblError.setForeground(Color.red);
		contentPane.add(lblError);
		lblError.setVisible(false);
		
		JButton btnAddAdmin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddAdmin"));
		btnAddAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("")) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("NoRe"));
					lblError.setVisible(true);
				}else if(!businessLogic.existeUsuario(textField.getText())) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErUs"));
					lblError.setVisible(true);
				}else {
					businessLogic.hacerAdmin(textField.getText());
					setVisible(false);
				}
			}
		});
		btnAddAdmin.setBounds(12, 112, 143, 25);
		contentPane.add(btnAddAdmin);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(167, 112, 143, 25);
		contentPane.add(btnNewButton);
	}
}
