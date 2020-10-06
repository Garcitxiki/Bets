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
import domain.Question;
import domain.Usuario;

public class DeleteEventGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarMio = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonDelete = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DelEv"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private Usuario user=null;

	private BLFacade businessLogic= LoginGUI.getBusinessLogic();

	public DeleteEventGUI(Usuario user) {
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
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("DelEv"));

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
					if (modelEvents.getSize() != 0) {
						jButtonDelete.setEnabled(true);
					} else {
						jButtonDelete.setEnabled(false);
					}

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

		jLabelError.setBounds(new Rectangle(275, 182, 250, 20));
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
						jComboBoxEvents.removeAllItems();
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
		Event eev=(Event) jComboBoxEvents.getSelectedItem();
		int res = businessLogic.eliminarEvento(eev);
		if (res==0) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("NoDelEv"));
			jLabelError.setVisible(true);
			jLabelError.setForeground(Color.RED);		
		}else {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("YesDelEv"));
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