package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Apuestas;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;

public class MisApuestasGUI extends JFrame {

	private JPanel contentPane;
	private DefaultComboBoxModel<String> apuestaPartido = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> apuestaPregunta = new DefaultComboBoxModel<String>();
	private JLabel lblSeleccionaLaPregunta = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Sel"));
	private JLabel lblCuota = new JLabel("");
	private JLabel lblPronostico = new JLabel("");
	
	private JLabel Pronostico = new JLabel("");
	private JLabel Cuota = new JLabel("");
	private JLabel lblPartido_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Match"));
	
	private String selectedString;
	private String selectedString2;
	private Event selectedEvent;
	private Question selectedQuestion;
	private Vector<Question> vec= new Vector<Question>();
	private final JLabel lblApuesta = new JLabel("");
	private final JLabel lblBeneficio = new JLabel("");
	private final JLabel Apuesta = new JLabel("");
	private final JLabel Beneficio = new JLabel("");
	private final JButton btnCancelarApuesta = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MisApuestasGUI.btnCancelarApuesta.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private Question recogePreguntas = null;
	

	/**
	 * Create the frame.
	 */
	public MisApuestasGUI(final Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MyBets"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMisApuestas = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MyBets"));
		lblMisApuestas.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		lblMisApuestas.setBounds(170, 11, 111, 24);
		contentPane.add(lblMisApuestas);

		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MiPerfil perf = new MiPerfil(user);
				perf.setVisible(true);
				setVisible(false);
			}
		});

		lblApuesta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApuesta.setBounds(172, 159, 78, 33);
		contentPane.add(lblApuesta);

		lblBeneficio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBeneficio.setBounds(170, 195, 80, 32);
		contentPane.add(lblBeneficio);

		Apuesta.setFont(new Font("Tahoma", Font.BOLD, 13));
		Apuesta.setBounds(260, 159, 164, 33);
		contentPane.add(Apuesta);

		Beneficio.setFont(new Font("Tahoma", Font.BOLD, 13));
		Beneficio.setBounds(260, 195, 144, 32);
		contentPane.add(Beneficio);

		btnClose.setBounds(335, 227, 89, 23);
		contentPane.add(btnClose);
		
		this.Pronostico.setBounds(99, 159, 253, 33);
		contentPane.add(Pronostico);
		
		this.Cuota.setBounds(99, 195, 253, 29);
		contentPane.add(Cuota);
		
		this.lblPartido_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.lblPartido_1.setBounds(20, 60, 50, 24);
		contentPane.add(lblPartido_1);		
		this.lblSeleccionaLaPregunta.setBounds(55, 179, 349, 24);
		contentPane.add(lblSeleccionaLaPregunta);

		JLabel lblPartido = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Question"));
		lblPartido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPartido.setBounds(20, 118, 69, 24);
		contentPane.add(lblPartido);
		this.lblPronostico.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.lblPronostico.setBounds(20, 162, 69, 24);
		contentPane.add(lblPronostico);
		this.lblCuota.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.lblCuota.setBounds(20, 197, 69, 24);
		contentPane.add(lblCuota);
		
		
		btnCancelarApuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmacionCancelarApuesta confirmar = new ConfirmacionCancelarApuesta(recogePreguntas, user);
				setVisible(false);
				confirmar.setVisible(true);
			}
		});
		btnCancelarApuesta.setBounds(170, 228, 154, 23);
		btnCancelarApuesta.setEnabled(false);
		contentPane.add(btnCancelarApuesta);

		JComboBox Partido = new JComboBox();
		Partido.setBounds(99, 60, 253, 33);
		contentPane.add(Partido);
		Partido.setModel(apuestaPartido);
		Vector<Apuestas> aux = user.getApuestas();
		Vector<Event> aux3 = new Vector<Event>();
		int tamano = aux.size();
		if (tamano == 0)
			apuestaPartido.addElement(ResourceBundle.getBundle("Etiquetas").getString("NoBets"));
		else {
			System.out.println("Mis apuestas");
			System.out.println(tamano);
			
			for (int i = 0; i < tamano; i++) {
				Pronostico part= aux.get(i).getPronostico();
				Question quest = part.getPreguntaPronostico();
				System.out.println(quest.getEvent().toString());
				Event even2 = quest.getEvent();
				boolean encontrado = false;
				int h = 0;
				while (h < aux3.size() && !encontrado) {
					Event even3 = aux3.get(h);
					if (even3.getDescription().equals(even2.getDescription())) {
						encontrado = true;
					} else {
						h++;
					}
				}
				if (!encontrado) {
					apuestaPartido.addElement(even2.getDescription());
					aux3.add(even2);
				}
			}
		}
		
		Partido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BLFacade facade=LoginGUI.getBusinessLogic();
				if(apuestaPartido.getSize()>0) {
					selectedString = (String) apuestaPartido.getSelectedItem();
					selectedEvent=  facade.getEvent(selectedString);
					vec = selectedEvent.getQuestions();
					apuestaPregunta.removeAllElements();
					for(int i =0;i<vec.size();i++) {
						for(int j=0; j<user.getApuestas().size();j++) {
							if(vec.get(i).getQuestion().equals(user.getApuestas().get(j).getPronostico().getPreguntaPronostico().getQuestion())) {
								apuestaPregunta.addElement(vec.get(i).getQuestion());
								break;
							}
						}
					}
				}
			}
		});

		JComboBox Pregunta = new JComboBox();
		Pregunta.setBounds(99, 115, 253, 33);
		contentPane.add(Pregunta);
		Pregunta.setModel(apuestaPregunta);
		


		String even = apuestaPartido.getSelectedItem().toString();
		for (int j = 0; j < tamano; j++) {
			Pronostico part = aux.get(j).getPronostico();
			Question quest = part.getPreguntaPronostico();
			if (quest.getEvent().getDescription().equals(even)) {
				apuestaPregunta.addElement(quest.getQuestion());
			}
		}
		Pregunta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BLFacade facade=LoginGUI.getBusinessLogic();
				if(apuestaPregunta.getSize()>0) {
					selectedString2 = (String) apuestaPregunta.getSelectedItem();
					selectedString = (String) apuestaPartido.getSelectedItem();
					selectedEvent=  facade.getEvent(selectedString);
					vec = selectedEvent.getQuestions();
					for(int i=0;i<vec.size();i++) {
						if(vec.get(i).getQuestion().equals(selectedString2)) {
							selectedQuestion= vec.get(i);
							break;
						}
					}
					for(int i=0;i<user.getApuestas().size();i++) {
						if(user.getApuestas().get(i).getPronostico().getPreguntaPronostico().getQuestionNumber()==selectedQuestion.getQuestionNumber()) {
							String pronos= user.getApuestas().get(i).getPronostico().getPronostico();
							Double cuota= user.getApuestas().get(i).getPronostico().getCuota();
							Double apuesta = user.getApuestas().get(i).getCantidad();
							Double beneficio = user.getApuestas().get(i).getCantidad()
									* user.getApuestas().get(i).getPronostico().getCuota();
							beneficio=Math.floor(beneficio*100)/100;
							Pronostico.setText(pronos);
							lblSeleccionaLaPregunta.setText("");
							Cuota.setText(cuota.toString());
							Apuesta.setText(apuesta.toString());
							Beneficio.setText(beneficio.toString());
							lblPronostico.setText(ResourceBundle.getBundle("Etiquetas").getString("Pron"));
							lblCuota.setText(ResourceBundle.getBundle("Etiquetas").getString("Cuo"));
							lblApuesta.setText(ResourceBundle.getBundle("Etiquetas").getString("Apo"));
							lblBeneficio.setText(ResourceBundle.getBundle("Etiquetas").getString("Bene"));
							btnCancelarApuesta.setEnabled(true);
							recogePreguntas = user.getApuestas().get(i).getPronostico().getPreguntaPronostico();
							break;
						}
					}
				}
			}
		});

		

	}
}
