package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Mensaje;
import domain.Usuario;

public class ForoGUI extends JFrame {

	private BLFacade businessLogic= LoginGUI.getBusinessLogic();
	private JPanel contentPane;
	private Usuario user=null;

	/**
	 * Create the frame.
	 */
	public ForoGUI(Usuario usu) {
		this.user=usu;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 386);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("Foro"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTextPane textArea = new JTextPane();
		contentPane.add(textArea, BorderLayout.CENTER);
		
		JButton btnRedactar = new JButton("Redactar");
		contentPane.add(btnRedactar, BorderLayout.NORTH);
		btnRedactar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RedactarGUI red = new RedactarGUI(user);
				setVisible(false);
				red.setVisible(true);
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(user.isEsAdmin()) {
					MainAdminGUI admin = new MainAdminGUI(user);
					setVisible(false);
					admin.setVisible(true);
				}
				else {
					MainUserGUI mainUser = new MainUserGUI(user);
					setVisible(false);
					mainUser.setVisible(true);
				}
			}
		});
		contentPane.add(btnSalir, BorderLayout.SOUTH);
		
		JScrollPane js = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(js);
		
		textArea.setEditable(false);
		String s1,s2,s3;
		List<Mensaje> mensajes = businessLogic.getMensajes();
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(int i = 0; i<mensajes.size();i++) {
			Usuario user1 = businessLogic.getUserByNombreUsuario(mensajes.get(i).getDeQuien().getNombreUsuario());
			Mensaje mensaje = mensajes.get(i);
			s1 = formato.format(mensaje.getData()) + " -->" + user1.getNombre() + " " + user1.getApellido() + " ";
			if (user1.isEsAdmin()) {
			s3="(ADMIN) ";
			}
			else {
			s3="";
			}
			s2 = "dice:\n" + mensaje.getContenido() + "\n\n\n";
			textArea.setText(textArea.getText()+s1+s3+s2);
			s3="";
		}
		
	}
	
	

}
