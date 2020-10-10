package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Usuario;

public class LoginGUI extends JFrame {

	private JPanel contentPane;

	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JPanel jContentPane = null;
	private JTextField textUser = null;
	private JPasswordField textPasswd = null;
	private JButton jLogButton = null;
	private JButton jregButton = null;
	private JLabel lblNewLabel = null;
	private JLabel lblNewLabel_1 = null;
	private JLabel lblNewLabel_2 = null;
	private JLabel lblNewLabel_3 = null;
	private JLabel lblError = null;

	public static BLFacade getBusinessLogic() {
		return businessLogic;
	}

	private static BLFacade businessLogic;

	public void setBusinessLogic(BLFacadeImplementation businessLogic) {
		this.businessLogic = businessLogic;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Login"));
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
	public LoginGUI() {
		this.initialize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void setBusinessLogic(BLFacade buss) {
		businessLogic = buss;
	}

	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}

	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}

	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 255, 459, 35);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		jLogButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		jregButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("User"));
		lblNewLabel_1.setText(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblNewLabel_2.setText(ResourceBundle.getBundle("Etiquetas").getString("Identify"));
		lblNewLabel_3.setText(ResourceBundle.getBundle("Etiquetas").getString("NoAccount"));
		lblError.setVisible(false);
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Login"));

	}

	private void initialize() {
		setBounds(100, 100, 491, 388);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Login"));
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			jContentPane.setLayout(null);
			jContentPane.add(getTextUser());
			jContentPane.add(getPanel());
			jContentPane.add(getTextPasswd());
			jContentPane.add(getJLogButton());
			jContentPane.add(getjregButton());
			jContentPane.add(getlblNewLabel());
			jContentPane.add(getlblNewLabel_1());
			jContentPane.add(getlblNewLabel_2());
			jContentPane.add(getlblNewLabel_3());

			lblError = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
			lblError.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$ //$NON-NLS-2$
			lblError.setHorizontalAlignment(SwingConstants.CENTER);
			lblError.setBounds(82, 303, 272, 16);
			jContentPane.add(lblError);
			lblError.setVisible(false);
			lblError.setForeground(Color.red);

		}
		return jContentPane;
	}

	private JTextField getTextUser() {
		if (textUser == null) {
			textUser = new JTextField();
			textUser.setBounds(122, 60, 232, 32);
			textUser.setColumns(10);
		}
		return textUser;
	}

	private JTextField getTextPasswd() {
		if (textPasswd == null) {
			textPasswd = new JPasswordField();
			textPasswd.setBounds(122, 119, 232, 32);
			textPasswd.setColumns(10);
		}
		return textPasswd;
	}

	private JButton getJLogButton() {
		if (jLogButton == null) {
			jLogButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login"));
			jLogButton.setBounds(181, 164, 120, 25);
			jLogButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String nombreUser = textUser.getText();
					String password = textPasswd.getText();
					if (nombreUser.equals("") || password.equals("")) {
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("NoRe"));
						lblError.setVisible(true);
					} else if (!businessLogic.existeUsuario(nombreUser)) {
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErUs"));
						lblError.setVisible(true);
					} else {
						Usuario user = businessLogic.getUserByNombreUsuario(nombreUser);
						if (user != null) {
							if (password.equals(user.getContrasena())) {
								if (user.isEsAdmin()) {
									MainAdminGUI pestanaAdmin = new MainAdminGUI(user);
									setVisible(false);
									pestanaAdmin.setVisible(true);
								} else {
									MainUserGUI pestanaAdmin = new MainUserGUI(user);
									setVisible(false);
									pestanaAdmin.setVisible(true);
								}
							} else {
								lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("CoIn"));
								lblError.setVisible(true);
							}
						}

					}
				}
			});
		}
		return jLogButton;
	}

	private JButton getjregButton() {
		if (jregButton == null) {
			jregButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
			jregButton.setBounds(318, 215, 141, 25);
			jregButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					RegisterGUI pestanaRegistro = new RegisterGUI();
					setVisible(false);
					pestanaRegistro.setVisible(true);
				}
			});
		}
		return jregButton;
	}

	private JLabel getlblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("User"));
			lblNewLabel.setBounds(25, 64, 85, 24);
		}
		return lblNewLabel;
	}

	private JLabel getlblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
			lblNewLabel_1.setBounds(25, 127, 78, 16);
		}
		return lblNewLabel_1;
	}

	private JLabel getlblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Identify"));
			lblNewLabel_2.setBounds(120, 15, 234, 32);
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		}
		return lblNewLabel_2;
	}

	private JLabel getlblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NoAccount"));
			lblNewLabel_3.setBounds(82, 215, 200, 25);

		}
		return lblNewLabel_3;
	}
}
