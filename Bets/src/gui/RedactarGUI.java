package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Mensaje;
import domain.Usuario;

public class RedactarGUI extends JFrame {

	private JPanel contentPane;
	private Usuario usu=null;
	private BLFacade businessLogic= LoginGUI.getBusinessLogic();

	/**
	 * Create the frame.
	 */
	public RedactarGUI(Usuario user) {
		this.usu=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		final JLabel lblQueEsLo = new JLabel("Que es lo que quiere escribir?");
		lblQueEsLo.setBounds(5, 19, 424, 14);
		lblQueEsLo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblQueEsLo);
		
		final JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(5, 44, 424, 171);
		contentPane.add(editorPane);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String s= editorPane.getText();
				if(s.equals("")) {
					lblQueEsLo.setText(ResourceBundle.getBundle("Etiquetas").getString("NoRe"));
					lblQueEsLo.setForeground(Color.RED);
				}
				else {
					List<Mensaje> mensajes= businessLogic.getMensajes();
					Mensaje mensaje = new Mensaje(mensajes.size(), usu, s, new Date());
					businessLogic.anadirMensajeUsuario(mensaje, usu);
				
				ForoGUI foro= new ForoGUI(usu);
				setVisible(false);
				foro.setVisible(true);
				}
			}
		});
		btnAceptar.setBounds(272, 227, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ForoGUI foro= new ForoGUI(usu);
				setVisible(false);
				foro.setVisible(true);
			}
		});
		btnCancelar.setBounds(93, 226, 89, 23);
		contentPane.add(btnCancelar);
	}

}
