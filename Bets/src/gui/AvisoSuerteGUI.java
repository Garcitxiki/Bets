package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Usuario;
import javafx.scene.text.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AvisoSuerteGUI extends JFrame {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */	
	private BLFacade businessLogic= LoginGUI.getBusinessLogic();

	public AvisoSuerteGUI(final double cantidadinicial, final double cantidadfinal, final double valorRan, final Usuario usu) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 234);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Usuario usunu=businessLogic.getUserByNombreUsuario(usu.getNombreUsuario());
				MiPerfil perf = new MiPerfil(usunu);
				perf.setVisible(true);
			}
		});
		btnNewButton.setBounds(153, 149, 97, 25);
		contentPanel.add(btnNewButton);
		
		JLabel lblMulti = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Ranmul")); //$NON-NLS-1$ //$NON-NLS-2$
		lblMulti.setBounds(37, 34, 254, 16);
		contentPanel.add(lblMulti);
		
		JLabel lblIniValue = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IniVal")); //$NON-NLS-1$ //$NON-NLS-2$
		lblIniValue.setBounds(37, 63, 254, 16);
		contentPanel.add(lblIniValue);
		
		JLabel lblFinValue = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LastVal")); //$NON-NLS-1$ //$NON-NLS-2$
		lblFinValue.setBounds(37, 92, 254, 16);
		contentPanel.add(lblFinValue);
		
		JLabel lblsorry = new JLabel(); 
		lblsorry.setHorizontalAlignment(SwingConstants.CENTER);
		lblsorry.setBounds(37, 119, 334, 16);
		contentPanel.add(lblsorry);
		lblsorry.setVisible(false);
		if(valorRan>1) {
			lblsorry.setText(ResourceBundle.getBundle("Etiquetas").getString("YesLucky"));
			lblsorry.setVisible(true);

		}else {
			lblsorry.setText(ResourceBundle.getBundle("Etiquetas").getString("NoLucky"));
			lblsorry.setVisible(true);

		}
		
		
		JLabel lblNewLabel = new JLabel(); 
		lblNewLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(315, 34, 56, 16);
		lblNewLabel.setText(String.valueOf(valorRan));
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(); 
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
		lblNewLabel_1.setBounds(315, 63, 56, 16);
		lblNewLabel_1.setText(String.valueOf(cantidadinicial));

		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(); 
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
		lblNewLabel_2.setBounds(315, 92, 56, 16);
		lblNewLabel_2.setText(String.valueOf(cantidadfinal));
		contentPanel.add(lblNewLabel_2);

	}
}
