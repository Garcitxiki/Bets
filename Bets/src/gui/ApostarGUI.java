package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Categoria;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class ApostarGUI extends JFrame{
	private JTextField cantidadQuieroApostarResultado;
	private JButton btnAceptar;
	JPanel panel;
	private JPanel panel_1;
	JButton botonApostar;
	JButton botonClose;
	JLabel lblCantidadMnimaA;
	JLabel cantidadMinimaResultado;
	JLabel lblCantidadQueQuiero;
	JLabel lblCuota;
	JLabel cuotaResultado;
	JLabel lblCantidadAGanar;
	JLabel lblerrores;
	JButton btnReset;
	JLabel cantidadGanarResultado;
	JCheckBox chkSuerte;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	Random r = new Random();
	


	private BLFacade businessLogic= LoginGUI.getBusinessLogic();

	public ApostarGUI(final Pronostico pronostico, final Usuario usuario, final Categoria cat) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("Apostar"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 329);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		getContentPane().add(panel_1, BorderLayout.CENTER);

		botonApostar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Apostar"));
		botonApostar.setEnabled(false);
		botonApostar.setBounds(87, 246, 89, 23);
		botonApostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario u = businessLogic.getUserByNombreUsuario(usuario.getNombreUsuario());
				double cantidadRestarCartera = Double.parseDouble(cantidadQuieroApostarResultado.getText());
				//miramos si tiene la cantidad para apostar
				double fondos= u.getCartera();
				if(fondos >= cantidadRestarCartera) {
					//le quitamos a la cartera la canidad a apostar
					if (!chkSuerte.isSelected()) {
						cantidadRestarCartera=Math.floor(cantidadRestarCartera*100)/100;
						businessLogic.addFounds(u, -cantidadRestarCartera);
						businessLogic.anadirApuestaUsuario(pronostico, cantidadRestarCartera, usuario);
						setVisible(false);
						Usuario usunu=businessLogic.getUserByNombreUsuario(usuario.getNombreUsuario());
						MiPerfil perf = new MiPerfil(usunu);
						perf.setVisible(true);
					}else {
						double randomValue = 0.75 + (1.25 - 0.75) * r.nextDouble();
						double s=Math.floor(randomValue * 100) / 100;
						double cantidadrestfin = cantidadRestarCartera*s;
						cantidadrestfin = Math.floor(cantidadrestfin*100)/100;
						businessLogic.addFounds(u, -cantidadRestarCartera);
						businessLogic.anadirApuestaUsuario(pronostico, cantidadrestfin, usuario);
						setVisible(false);
						AvisoSuerteGUI avi= new AvisoSuerteGUI(cantidadRestarCartera, cantidadrestfin,s , u);
						avi.setVisible(true);

						
						
						
					}

				}else {
					lblerrores.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNoCantidadEnCartera"));
					lblerrores.setForeground(Color.RED);
				}
			}
		});
		panel_1.add(botonApostar);

		chkSuerte = new JCheckBox(ResourceBundle.getBundle("Etiquetas").getString("Lucky")); //$NON-NLS-1$ //$NON-NLS-2$
		chkSuerte.setHorizontalAlignment(SwingConstants.CENTER);
		chkSuerte.setBounds(136, 195, 154, 25);
		panel_1.add(chkSuerte);
		
		
		botonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		botonClose.setBounds(254, 246, 89, 23);
		botonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Usuario usunu=businessLogic.getUserByNombreUsuario(usuario.getNombreUsuario());
				FindQuestionsGUI questionsGUI = new FindQuestionsGUI(usunu,cat);
				questionsGUI.setVisible(true);
			}
		});
		panel_1.add(botonClose);

		lblCantidadMnimaA = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MinimumBetPrice"));
		lblCantidadMnimaA.setBounds(34, 37, 170, 14);
		panel_1.add(lblCantidadMnimaA);

		cantidadMinimaResultado = new JLabel("");
		cantidadMinimaResultado.setBounds(214, 37, 76, 14);
		panel_1.add(cantidadMinimaResultado);

		lblCantidadQueQuiero = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CantidadApuesta"));
		lblCantidadQueQuiero.setBounds(34, 75, 170, 14);
		panel_1.add(lblCantidadQueQuiero);

		lblCuota = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Cuota"));
		lblCuota.setBounds(34, 117, 142, 14);
		panel_1.add(lblCuota);

		cuotaResultado = new JLabel("");
		cuotaResultado.setBounds(214, 117, 46, 14);
		panel_1.add(cuotaResultado);

		lblCantidadAGanar = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CantidadAGanar"));
		lblCantidadAGanar.setBounds(34, 157, 170, 14);
		panel_1.add(lblCantidadAGanar);

		cantidadGanarResultado = new JLabel("");
		cantidadGanarResultado.setBounds(214, 157, 46, 14);
		panel_1.add(cantidadGanarResultado);

		cantidadQuieroApostarResultado = new JTextField();
		cantidadQuieroApostarResultado.setBounds(214, 75, 112, 20);
		panel_1.add(cantidadQuieroApostarResultado);
		cantidadQuieroApostarResultado.setColumns(10);

		lblerrores = new JLabel("");
		lblerrores.setBounds(57, 229, 337, 14);
		panel_1.add(lblerrores);

		//conseguimos el pronostico introducido como parametro
		Pronostico pronost = businessLogic.getPronosticoByNumber(pronostico.getPronosticoNumber());
		cuotaResultado.setText(Double.toString(pronost.getCuota()));
		//conseguimos la pregunta del pronostico introducido como parametro para enseñar
		//la cantidad minima de apuesta
		Question pregunta = businessLogic.getQuestionByNumber(pronost.getPreguntaPronostico().getQuestionNumber());
		cantidadMinimaResultado.setText(Float.toString(pregunta.getBetMinimum()));

		btnAceptar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
		btnAceptar.setBounds(335, 59, 89, 23);
		btnAceptar.setEnabled(true);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//miramos si se a introducido una cantidad para apostar 
				if(!cantidadQuieroApostarResultado.getText().isEmpty()) {
					try{
						//comprobamos si la cantidad introducida es un double y
						//lo redondeamos a dos decimales
						DecimalFormat formato = new DecimalFormat("#.##");
						double numero = Double.parseDouble(cantidadQuieroApostarResultado.getText());	
						double numero1 = Double.parseDouble(formato.format(numero));
						cantidadQuieroApostarResultado.setText(Double.toString(numero1));

						//miramos si la cantidad minima no es vacia y si la cantidad introducida es mayor
						//o igual que la minima y mayor que cero
						if(!cantidadMinimaResultado.getText().isEmpty()) {
							double cantMinima = Double.parseDouble(cantidadMinimaResultado.getText());
							if(numero1 >= cantMinima && numero1 > 0) {
								lblerrores.setText(null);
								cantidadQuieroApostarResultado.setEnabled(false);
								btnAceptar.setEnabled(false);
								btnReset.setEnabled(true);
								botonApostar.setEnabled(true);

								//mostramos la cantidad a ganar y la redondeamos a dos decimales
								double cantidadGanar = Double.parseDouble(cuotaResultado.getText())*numero1;
								cantidadGanarResultado.setText(Double.toString(Double.parseDouble(formato.format(cantidadGanar))));
							}else {
								cantidadQuieroApostarResultado.setText(null);
								lblerrores.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorCantidadMinima"));
								lblerrores.setForeground(Color.RED); 

							}
						}else {
							throw new Exception();
						}

					}catch(NumberFormatException f){
						cantidadQuieroApostarResultado.setText(null);
						lblerrores.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorValorNumerico"));
						lblerrores.setForeground(Color.RED); 
					}catch(Exception u) {

					}
				}else {
					lblerrores.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorCampoVacio"));
					lblerrores.setForeground(Color.RED); 
				}
			}
		});
		panel_1.add(btnAceptar);

		btnReset = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Reset"));
		btnReset.setBounds(336, 93, 89, 23);
		btnReset.setEnabled(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblerrores.setText(null);
				cantidadQuieroApostarResultado.setEnabled(true);
				cantidadQuieroApostarResultado.setText(null);
				cantidadGanarResultado.setText(null);
				btnReset.setEnabled(false);
				btnAceptar.setEnabled(true);
				botonApostar.setEnabled(false);
			}
		});
		panel_1.add(btnReset);
		

	}
}