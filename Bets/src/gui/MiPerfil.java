package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.Usuario;

public class MiPerfil extends JFrame {

	private JPanel contentPane;
	private JTextField Dinerito;
	private DefaultComboBoxModel<String> apuestas = new DefaultComboBoxModel<String>();
	private JLabel Error;
	private JLabel Cartera;
	private Usuario u;

	/**
	 * Create the frame.
	 */
	public MiPerfil(final Usuario user) {
		u = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("Profile"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		redibujar();
		JLabel NombreUsuario = new JLabel(u.getNombreUsuario());
		NombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		NombreUsuario.setFont(new Font("Arial Black", Font.PLAIN, 15));
		NombreUsuario.setBounds(152, 7, 128, 29);
		contentPane.add(NombreUsuario);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Wallet")+ ":");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(20, 134, 75, 29);
		contentPane.add(lblNewLabel);

		JLabel Nombre = new JLabel(u.getNombre());
		Nombre.setFont(new Font("Arial", Font.PLAIN, 13));
		Nombre.setBounds(107, 46, 92, 22);
		contentPane.add(Nombre);

		JLabel Apellido = new JLabel(u.getApellido());
		Apellido.setFont(new Font("Arial", Font.PLAIN, 13));
		Apellido.setBounds(302, 46, 88, 22);
		contentPane.add(Apellido);

		JLabel lblTipo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Type")+":");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipo.setBounds(20, 108, 75, 22);
		contentPane.add(lblTipo);

		JLabel EsAdmin = new JLabel("New label");
		EsAdmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		if (u.isEsAdmin())
			EsAdmin.setText("Admin");
		else
			EsAdmin.setText(ResourceBundle.getBundle("Etiquetas").getString("Client"));
		EsAdmin.setBounds(107, 110,102,19);
		contentPane.add(EsAdmin);

		JLabel DNI = new JLabel(u.getDNI());
		DNI.setFont(new Font("Arial", Font.PLAIN, 13));
		DNI.setBounds(107, 75, 67, 22);
		contentPane.add(DNI);

		JLabel lblNombre = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Name")+":");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(20, 46, 75, 22);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Surname")+":");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellido.setBounds(218, 46, 62, 22);
		contentPane.add(lblApellido);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDni.setBounds(20, 78, 67, 17);
		contentPane.add(lblDni);
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Addfon"));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AnadirFondosBuenoGUI n = new AnadirFondosBuenoGUI(u);
				n.setVisible(true);
				setVisible(false);

			}
		});
		btnNewButton.setBounds(20, 210, 193, 23);
		contentPane.add(btnNewButton);

		Error = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.lblNewLabel_12.text"));
		Error.setVisible(false);
		Error.setBounds(269, 241, 155, 14);
		contentPane.add(Error);

		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainUserGUI usuar = new MainUserGUI(u);
				usuar.setVisible(true);
				setVisible(false);
			}
		});
		btnClose.setBounds(254, 209, 97, 25);
		contentPane.add(btnClose);

		JButton MisApuestas = new JButton(
				ResourceBundle.getBundle("Etiquetas").getString("MyBets")); //$NON-NLS-1$ //$NON-NLS-2$
		MisApuestas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MisApuestasGUI apuest = new MisApuestasGUI(u);
				apuest.setVisible(true);
				setVisible(false);
			}
		});
		MisApuestas.setBounds(20, 169, 139, 29);
		contentPane.add(MisApuestas);

		JButton Contrasena = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Cont")); //$NON-NLS-1$ //$NON-NLS-2$
		Contrasena.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ContrasenaGUI cont = new ContrasenaGUI(u);
				cont.setVisible(true);
				setVisible(false);
			}
		});
		Contrasena.setBounds(254, 169, 155, 29);
		contentPane.add(Contrasena);
	}

	public String getCartera() {
		return Cartera.getText();
	}

	public void setCartera(Double cartera) {
		Cartera.setText(cartera.toString());
	}

	public void redibujar() {
		Cartera = new JLabel(Double.toString(u.getCartera()));
		Cartera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Cartera.setBounds(107, 134, 88, 29);
		contentPane.add(Cartera);
	}
}