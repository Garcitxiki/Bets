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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import domain.Categoria;
import domain.Event;
import domain.Question;

import javax.swing.JComboBox;

public class CreateEventGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();
	private JLabel jLabelQuery = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventName"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private JTextField jTextFieldQuery = new JTextField();
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private JLabel lblNewLabel=null;
	
	private JComboBox comboBoxCate;
	DefaultComboBoxModel<Categoria> modelCate = new DefaultComboBoxModel<Categoria>();
	
	private BLFacade businessLogic= LoginGUI.getBusinessLogic();

	public CreateEventGUI() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jbInit();
	}

	private void jbInit() throws Exception {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(361, 418));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		jLabelQuery.setBounds(new Rectangle(30, 262, 105, 20));
		jTextFieldQuery.setBounds(new Rectangle(33, 295, 232, 20));
		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonCreate.setBounds(new Rectangle(30, 328, 130, 30));
		jButtonCreate.setEnabled(true);

		jButtonCreate.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			jButtonCreate_actionPerformed(e);
			if(jTextFieldQuery.getText().equals("")) {
				jLabelError.setVisible(true);
				jLabelError.setText("ERROR");
			}else {
				Calendar c = jCalendar.getCalendar();
				int month = c.get(Calendar.MONTH);
				JDayChooser day= jCalendar.getDayChooser();
				int year =c.get(Calendar.YEAR);
				Date fecha = UtilDate.newDate(year,month,day.getDay());
				Categoria ca= (Categoria) comboBoxCate.getSelectedItem();
				businessLogic.createEvent(jTextFieldQuery.getText(), fecha, ca);
				lblNewLabel.setVisible(true);
				}
			}
		});

		jButtonClose.setBounds(new Rectangle(176, 328, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 182, 305, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(274, 240, 59, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);
		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jTextFieldQuery, null);
		this.getContentPane().add(jLabelQuery, null);
		this.getContentPane().add(jCalendar, null);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);
		
		lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EveCre")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(160, 266, 105, 16);
		lblNewLabel.setVisible(false);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setForeground(Color.green);


		
		comboBoxCate = new JComboBox();
		comboBoxCate.setBounds(100, 227, 120, 22);
		getContentPane().add(comboBoxCate);
		comboBoxCate.setModel(modelCate);
		
		JLabel lblcat = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Cat")); //$NON-NLS-1$ //$NON-NLS-2$
		lblcat.setBounds(100, 205, 97, 16);
		getContentPane().add(lblcat);
		Vector<Categoria> caca = businessLogic.getCategories();
		for (Categoria cacaca : caca) {
			modelCate.addElement(cacaca);
		}

		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					jCalendar.setCalendar(calendarMio);
					Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
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
		int today=calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;
		
	 	for (Date d:dates){
	 		calendar.setTime(d);
			System.out.println(d);
			Component o = jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
	 	}

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, month);
	}
	
	
	private void jButtonCreate_actionPerformed(ActionEvent e) {

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");
			String inputQuery = jTextFieldQuery.getText();
		} catch (java.lang.NumberFormatException e1) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}