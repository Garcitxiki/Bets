package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Usuario;

public class ContrasenaGUI extends JFrame {

	private JPanel contentPane;
	private JButton Camb;
	private JButton Close;
	private JLabel AvisoF;
	private BLFacade businessLogic = LoginGUI.getBusinessLogic();
	private JPasswordField ContAct2;
	private JPasswordField ContNew2;
	private JPasswordField ContRep2;



	/**
	 * Create the frame.
	 */
	public ContrasenaGUI(final Usuario u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel ContActual = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ContA")); 
		ContActual.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ContActual.setBounds(26, 31, 135, 22);
		contentPane.add(ContActual);

		JLabel ContNew = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ContN")); 
		ContNew.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ContNew.setBounds(26, 100, 135, 22);
		contentPane.add(ContNew);

		JLabel ContRep = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ContR")); 
		ContRep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ContRep.setBounds(26, 170, 135, 22);
		contentPane.add(ContRep);

		Camb = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Camb")); 
		Camb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String contAc = ContAct2.getText();
				if (contAc.equals(u.getContrasena())) {
					String contN = ContNew2.getText();
					String contR = ContRep2.getText();
					if (contN.equals("")) {
						AvisoF.setText(ResourceBundle.getBundle("Etiquetas").getString("IntCon"));
						AvisoF.setForeground(Color.RED);
					} else {
						if (contN.equals(contR)) {
							businessLogic.cambiarContrasena(u, contN);
							AvisoF.setText(ResourceBundle.getBundle("Etiquetas").getString("CambEx"));
							AvisoF.setForeground(Color.GREEN);
						} else {
							AvisoF.setText(ResourceBundle.getBundle("Etiquetas").getString("CoDi"));
							AvisoF.setForeground(Color.RED);
						}
					}
				} else {
					AvisoF.setText(ResourceBundle.getBundle("Etiquetas").getString("CoIn"));
					AvisoF.setForeground(Color.RED);
				}
			}
		});
		Camb.setBounds(171, 221, 113, 28);
		contentPane.add(Camb);

		Close = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); 
		Close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MiPerfil perf = new MiPerfil(u);
				perf.setVisible(true);
				setVisible(false);
			}
		});
		Close.setBounds(333, 222, 91, 26);
		contentPane.add(Close);

		AvisoF = new JLabel(""); 
		AvisoF.setBounds(26, 225, 135, 22);
		contentPane.add(AvisoF);

		ContAct2 = new JPasswordField();
		ContAct2.setText(""); 
		ContAct2.setBounds(171, 29, 206, 28);
		contentPane.add(ContAct2);

		ContNew2 = new JPasswordField();
		ContNew2.setText(""); 
		ContNew2.setBounds(171, 98, 206, 28);
		contentPane.add(ContNew2);

		ContRep2 = new JPasswordField();
		ContRep2.setText(""); 
		ContRep2.setBounds(171, 168, 206, 28);
		contentPane.add(ContRep2);
	}
}
