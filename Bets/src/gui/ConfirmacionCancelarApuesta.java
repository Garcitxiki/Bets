package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Question;
import domain.Usuario;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmacionCancelarApuesta extends JFrame {

	private JPanel contentPane;
	private BLFacade businessLogic= LoginGUI.getBusinessLogic();
	/**
	 * Create the frame.
	 */
	public ConfirmacionCancelarApuesta(final Question pregunta, final Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 302, 208);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEstasSeguro = new JLabel("Estas Seguro?");
		lblEstasSeguro.setBounds(0, 11, 276, 20);
		lblEstasSeguro.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEstasSeguro.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEstasSeguro);
		
		JLabel lblSoloSeTe = new JLabel("Solo se te podrá devolver el 50%");
		lblSoloSeTe.setBounds(48, 36, 228, 53);
		contentPane.add(lblSoloSeTe);
		
		JLabel lblDeLaCantidad = new JLabel("de la cantidad apostada!");
		lblDeLaCantidad.setBounds(48, 77, 159, 20);
		contentPane.add(lblDeLaCantidad);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(44, 135, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i< user.getApuestas().size();i++) {
					if(user.getApuestas().get(i).getPreguntaAsociada().getQuestionNumber()==pregunta.getQuestionNumber()) {
					double cant = user.getApuestas().get(i).getCantidad()/2;
					//En este for simplemente busca cual es la apuesta que tiene que borrar dentro del vector de apouestas, y luego simplemente lo borra con el remove
					for(int j = 0; j< user.getApuestas().size();j++) {
						if(user.getApuestas().get(j).getCantNumber() == user.getApuestas().get(i).getCantNumber())
							user.getApuestas().remove(j);
							businessLogic.actualizarApuestas(user.getApuestas(), user);
					}
					cant= Math.floor(cant*100)/100;
					businessLogic.addFounds(user, cant);
					String numusu= user.getNombreUsuario();
					Usuario newusu= businessLogic.getUserByNombreUsuario(numusu);
					MisApuestasGUI misa= new MisApuestasGUI(newusu);
					setVisible(false);
					misa.setVisible(true);
					break;
					}
				}
			}
		});
		btnConfirmar.setBounds(153, 135, 105, 23);
		contentPane.add(btnConfirmar);
	}
}
