package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import configuration.UtilDate;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textUsuario;
	private JTextField textDNI;
	private JTextField textPasswd;
	private JTextField textPasswd2;
	private JTextField textDia;
	private JTextField textAno;
	private JTextField textMes;
	private DefaultComboBoxModel<String> monthNames = new DefaultComboBoxModel<String>();
	private JLabel labelerror;
	private BLFacade businessLogic = LoginGUI.getBusinessLogic();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 397);
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setBounds(122, 43, 167, 28);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Name"));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(40, 38, 116, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Surname"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(40, 101, 81, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Birt1"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(308, 33, 167, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("User"));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(40, 169, 81, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("DNI:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(40, 236, 56, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(308, 107, 161, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RepPass1"));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(308, 169, 161, 16);
		contentPane.add(lblNewLabel_6);
		
		JButton Registernbttn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		Registernbttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jtextRellenados()) {
					labelerror.setVisible(true);
					labelerror.setForeground(Color.red);
					labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("NoRe"));
				}else if(Integer.parseInt(textAno.getText())>2002) {
					labelerror.setVisible(true);
					labelerror.setForeground(Color.red);
					labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("NoMa"));
				}else if(textDNI.getText().length()!=9) {
					labelerror.setVisible(true);
					labelerror.setForeground(Color.red);
					labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("DNIi"));
				}else if(businessLogic.existeUsuario(textUsuario.getText())){
					labelerror.setVisible(true);
					labelerror.setForeground(Color.red);
					labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("UsEx"));
				}else if(!(textPasswd.getText().equals(textPasswd2.getText()))){
					labelerror.setVisible(true);
					labelerror.setForeground(Color.red);
					labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("CoDi"));
				}else {
					try {
						Date fechaNacimiento = UtilDate.newDate(Integer.parseInt(textAno.getText()),Integer.parseInt(textMes.getText()),Integer.parseInt(textDia.getText()));
						businessLogic.ingresarUsuario(textNombre.getText(), textApellido.getText(), fechaNacimiento, textPasswd.getText(), textDNI.getText(), textUsuario.getText());
						LoginGUI pestanaLogin = new LoginGUI();
						setVisible(false);
						pestanaLogin.setVisible(true);
					}catch(Exception el) {
						labelerror.setVisible(true);
						labelerror.setForeground(Color.red);
						labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("DoEr"));
					}
				}
			}
		});
		Registernbttn.setBounds(371, 253, 203, 51);
		contentPane.add(Registernbttn);
		
		labelerror = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.lblNewLabel_12.text")); //$NON-NLS-1$ //$NON-NLS-2$
		labelerror.setVisible(false);
		labelerror.setBounds(40, 303, 238, 14);
		contentPane.add(labelerror);
		
		textApellido = new JTextField();
		textApellido.setBounds(122, 106, 167, 28);
		contentPane.add(textApellido);
		textApellido.setColumns(10);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(122, 165, 167, 28);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		textDNI = new JTextField();
		textDNI.setBounds(122, 232, 167, 28);
		contentPane.add(textDNI);
		textDNI.setColumns(10);
		
		textPasswd = new JTextField();
		textPasswd.setBounds(422, 103, 203, 28);
		contentPane.add(textPasswd);
		textPasswd.setColumns(10);
		
		textPasswd2 = new JTextField();
		textPasswd2.setBounds(422, 165, 203, 28);
		contentPane.add(textPasswd2);
		textPasswd2.setColumns(10);
		
		textMes = new JTextField();
		textMes.setBounds(490, 46, 64, 22);
		contentPane.add(textMes);
		textMes.setColumns(10);
		
		textDia = new JTextField();
		textDia.setBounds(422, 46, 45, 22);
		contentPane.add(textDia);
		textDia.setColumns(10);
		
		textAno = new JTextField();
		textAno.setBounds(566, 46, 59, 22);
		contentPane.add(textAno);
		textAno.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Birt2"));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(308, 61, 139, 16);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RepPass2"));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_8.setBounds(308, 195, 102, 22);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Day"));
		lblNewLabel_9.setBounds(422, 17, 56, 16);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Month"));
		lblNewLabel_10.setBounds(490, 17, 56, 16);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Year"));
		lblNewLabel_11.setBounds(566, 17, 56, 16);
		contentPane.add(lblNewLabel_11);
		
	}
	private boolean jtextRellenados() {
		boolean f = false;
		if(this.textAno.getText().equals("")) {
			f = true;
		}
		if(this.textApellido.getText().equals("")) {
			f = true;
		}
		if(this.textDia.getText().equals("")) {
			f = true;
		}
		if(this.textDNI.getText().equals("")) {
			f = true;
		}
		if(this.textMes.getText().equals("")) {
			f = true;
		}
		if(this.textNombre.getText().equals("")) {
			f = true;
		}
		if(this.textPasswd.getText().equals("")) {
			f = true;
		}
		if(this.textPasswd2.getText().equals("")) {
			f = true;
		}
		if(this.textUsuario.getText().equals("")) {
			f = true;
		}
		return f;
	}
}
