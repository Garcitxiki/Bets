package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Usuario;

public class AnadirFondosBuenoGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTarjeta;
	private JTextField textFieldMes;
	private JTextField textField_CVV;
	private JTextField textField_Ano;
	private JTextField textField_Importe;

	private JLabel lblError;

	private BLFacade businessLogic = LoginGUI.getBusinessLogic();
	/**
	 * Create the frame.
	 */
	public AnadirFondosBuenoGUI(final Usuario usu) {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Addfon"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 242, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CaNu"));
		lblNewLabel.setBounds(22, 26, 144, 16);
		contentPane.add(lblNewLabel);

		textFieldTarjeta = new JTextField();
		textFieldTarjeta.setBounds(22, 55, 186, 22);
		contentPane.add(textFieldTarjeta);
		textFieldTarjeta.setColumns(10);

		textFieldMes = new JTextField();
		textFieldMes.setBounds(22, 119, 56, 22);
		contentPane.add(textFieldMes);
		textFieldMes.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Month"));
		lblNewLabel_2.setBounds(22, 90, 56, 16);
		contentPane.add(lblNewLabel_2);

		textField_CVV = new JTextField();
		textField_CVV.setBounds(152, 119, 50, 22);
		contentPane.add(textField_CVV);
		textField_CVV.setColumns(10);

		textField_Ano = new JTextField();
		textField_Ano.setBounds(90, 119, 56, 22);
		contentPane.add(textField_Ano);
		textField_Ano.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Year"));
		lblNewLabel_3.setBounds(90, 90, 56, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("CVV");
		lblNewLabel_4.setBounds(152, 90, 56, 16);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ImAn"));
		lblNewLabel_5.setBounds(22, 154, 101, 16);
		contentPane.add(lblNewLabel_5);

		textField_Importe = new JTextField();
		textField_Importe.setBounds(135, 154, 61, 22);
		contentPane.add(textField_Importe);
		textField_Importe.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("€");
		lblNewLabel_6.setBounds(200, 156, 15, 16);
		contentPane.add(lblNewLabel_6);

		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Addfon"));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (esCorrecto()) {
					double d = Double.parseDouble(textField_Importe.getText());
					businessLogic.addFounds(usu, d);
					String numusu= usu.getNombreUsuario();
					Usuario newusu= businessLogic.getUserByNombreUsuario(numusu);
					MiPerfil n = new MiPerfil(newusu);
					n.setVisible(true);
					setVisible(false);
				} else {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErTar"));
					lblError.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(22, 208, 186, 25);
		contentPane.add(btnNewButton);

		lblError = new JLabel("");
		lblError.setVisible(false);
		lblError.setForeground(Color.RED);
		lblError.setBounds(32, 183, 164, 16);
		contentPane.add(lblError);
		
		JButton btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario usunu=businessLogic.getUserByNombreUsuario(usu.getNombreUsuario());
				MainUserGUI mus= new MainUserGUI (usunu);
				mus.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(66, 241, 93, 25);
		contentPane.add(btnNewButton_1);
		lblError.setVisible(false);
	}
	
	
	/**
	 * Combrueba si los datos de la tarjeta son correctos o no 
	 * @return TRUE si lo son, FALSE si no lo son
	 */
	public boolean esCorrecto() {
		if (textField_Ano.getText().length() != 4) {
			return false;
		}
		if (textFieldMes.getText().length() != 2) {
			return false;
		}
		if (textField_CVV.getText().length() != 3) {
			return false;
		}
		if (textFieldTarjeta.getText().length() != 16) {
			return false;
		}
		return true;
	}
}