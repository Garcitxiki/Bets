package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;

public class DeletePronGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	private JComboBox<Question> jComboBoxQuestions = new JComboBox<Question>();
	DefaultComboBoxModel<Question> modelQuestions = new DefaultComboBoxModel<Question>();

	private JComboBox<Pronostico> jcomboBoxPron = new JComboBox<Pronostico>();
	DefaultComboBoxModel<Pronostico> modelProns = new DefaultComboBoxModel<Pronostico>();
	

	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarMio = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonDelete = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DelPro"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private Usuario user=null;

	private BLFacade businessLogic= LoginGUI.getBusinessLogic();

	public DeletePronGUI(Usuario user) {
		this.user=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(552, 318));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("DelPro"));

		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(275, 47, 250, 20));
		jComboBoxEvents.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BLFacade facade = LoginGUI.getBusinessLogic();
					Event event = (Event) jComboBoxEvents.getSelectedItem();
					Vector<Question> listque = event.getQuestions();
					if (listque.isEmpty()) {
						jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents") + ": ");
					} else {
						jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": ");
					}
					jComboBoxQuestions.removeAllItems();
					System.out.println("Questions " + listque);
					for (domain.Question ev : listque)
						modelQuestions.addElement(ev);
					jComboBoxQuestions.repaint();


				} catch (Exception e1) {

					jLabelError.setText(e1.getMessage());
				}
			}
		});

		jLabelListOfEvents.setBounds(new Rectangle(275, 18, 277, 20));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonDelete.setBounds(new Rectangle(50, 213, 178, 30));
		jButtonDelete.setEnabled(false);

		jButtonDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonDelete_action(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(275, 213, 169, 30));
		jButtonClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 155, 259, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(275, 191, 250, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonDelete, null);
		this.getContentPane().add(jLabelListOfEvents, null);
		this.getContentPane().add(jComboBoxEvents, null);

		this.getContentPane().add(jCalendar, null);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);

		jComboBoxQuestions.setBounds(275, 109, 250, 22);
		getContentPane().add(jComboBoxQuestions);
		jComboBoxQuestions.setModel(modelQuestions);
		jComboBoxQuestions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelProns.removeAllElements();
				Question quel =(Question) jComboBoxQuestions.getSelectedItem();
				Vector<Pronostico> lili= quel.getPronostico();
				for(Pronostico popo: lili) {
					modelProns.addElement(popo);
				}
				jcomboBoxPron.setModel(modelProns);
				jcomboBoxPron.repaint();
				if (modelEvents.getSize() != 0) {
					jButtonDelete.setEnabled(true);
				} else {
					jButtonDelete.setEnabled(false);
				}
			}
			});
		

		JLabel lblQuestion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelEv")); //$NON-NLS-1$ //$NON-NLS-2$
		lblQuestion.setBounds(275, 80, 250, 16);
		getContentPane().add(lblQuestion);
		
		jcomboBoxPron.setBounds(275, 167, 247, 22);
		getContentPane().add(jcomboBoxPron);
		
		JLabel lblPron = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Pron")); //$NON-NLS-1$ //$NON-NLS-2$
		lblPron.setBounds(275, 144, 247, 16);
		getContentPane().add(lblPron);

		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				jButtonDelete.setEnabled(false);
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					jCalendar.setCalendar(calendarMio);
					Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));

					try {
						BLFacade facade = LoginGUI.getBusinessLogic();

						Vector<domain.Event> events = facade.getEvents(firstDay);

						if (events.isEmpty()) {
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarMio.getTime()));
						} else {
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarMio.getTime()));
						}
						jComboBoxQuestions.removeAllItems();
						jComboBoxEvents.removeAllItems();
						jcomboBoxPron.removeAllItems();
						System.out.println("Events " + events);
						for (domain.Event ev : events)
							modelEvents.addElement(ev);
						jComboBoxEvents.repaint();

					} catch (Exception e1) {

						jLabelError.setText(e1.getMessage());
					}

				}
				paintDaysWithEvents(jCalendar);
			}
		});
	}

	public static void paintDaysWithEvents(JCalendar jCalendar) {
		BLFacade facade = LoginGUI.getBusinessLogic();
		Vector<Date> dates = facade.getEventsMonth(jCalendar.getDate());
		Calendar calendar = jCalendar.getCalendar();
		int month = calendar.get(Calendar.MONTH);
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;

		for (Date d : dates) {
			calendar.setTime(d);
			System.out.println(d);
			Component o = jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
		}

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, month);

	}
	private void jButtonDelete_action(ActionEvent e) {
		Pronostico por = (Pronostico) jcomboBoxPron.getSelectedItem();
		int res = businessLogic.eliminarPronotico(por);
		if (res==0) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("NoDelPro"));
			jLabelError.setVisible(true);
			jLabelError.setForeground(Color.RED);		
		}else {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("YesDelPro"));
			jLabelError.setVisible(true);
			jLabelError.setForeground(Color.GREEN);		
		}
		
		
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		MainAdminGUI dsf = new MainAdminGUI(user);
		dsf.setVisible(true);
	}
}