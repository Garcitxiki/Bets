package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Apuestas;
import domain.Categoria;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;


public class FindQuestionsGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Usuario usu;
	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private final JLabel lblPronostico = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ProCuo")); //$NON-NLS-1$ //$NON-NLS-2$
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private DefaultComboBoxModel<String> pronosticoList = new DefaultComboBoxModel<String>();
	private Pronostico selectedPronostico;
	private JComboBox<String> pronosticoBox = new JComboBox<String>();
	private Pronostico pronosticofinal=null;
	private Vector<Pronostico> pronosticos=null;
	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	BLFacade facade = LoginGUI.getBusinessLogic();
	JLabel labelerror = new JLabel();
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 
	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")
	};

	public FindQuestionsGUI(Usuario u, Categoria cate)
	{
		usu = u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try
		{
			jbInit(cate);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void jbInit(final Categoria cate) throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Apostar"));

		jLabelEventDate.setBounds(new Rectangle(40, 52, 140, 25));
		jLabelQueries.setBounds(40, 248, 406, 14);
		lblPronostico.setBounds(465, 248, 122, 14);
		jLabelEvents.setBounds(292, 56, 259, 16);
		
		

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);
		this.getContentPane().add(lblPronostico);
		
		
		

		jButtonClose.setBounds(new Rectangle(359, 420, 130, 30));
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		jButtonClose.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
				MainUserGUI userGUI = new MainUserGUI(usu);
				userGUI.setVisible(true);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(40, 87, 225, 150));


		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarMio.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarMio.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();
							if(ev.getCat().getDescription().equals(cate.getDescription())) {
								System.out.println("Events "+ev);

								row.add(ev.getEventNumber());
								row.add(ev.getDescription());
								row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
								tableModelEvents.addRow(row);	
							}
								
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
				//pinta los días con eventos en calendario
				CreateQuestionGUI.paintDaysWithEventsAndCategory(jCalendar1, cate);
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 87, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 273, 393, 116));
		pronosticoBox.setBounds(463, 273, 175, 25);

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});
		
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableQueries.getSelectedRow();
				int que = Integer.parseInt(tableModelQueries.getValueAt(i, 0).toString()); // obtain
																							// question
																							// object
				BLFacade facade = LoginGUI.getBusinessLogic();
				pronosticos = facade.obtenerPronostico(que);
				pronosticoList.removeAllElements();
				for (domain.Pronostico q:pronosticos){
					pronosticoList.addElement(q.imprimir());
				}
			}
		});
		pronosticoBox.setModel(pronosticoList);
		pronosticoBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (domain.Pronostico q:pronosticos){
					if(q.imprimir().equals(pronosticoBox.getSelectedItem())){
						pronosticofinal=q;
					}
				}
				}
			}
		);

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		this.getContentPane().add(pronosticoBox);
		
		
		labelerror = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
																														// //
																														// +
		labelerror.setVisible(false);
		labelerror.setBounds(40, 400, 449, 14);
		getContentPane().add(labelerror);
		
		JButton betButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Apo")); //$NON-NLS-1$ //$NON-NLS-2$
		betButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				boolean existente = false;

				int i = tableQueries.getSelectedRow();
				int que = Integer.parseInt(tableModelQueries.getValueAt(i, 0).toString());
				Question q = facade.getQuestionByNumber(que);

				List<Apuestas> apu = usu.getApuestas();
				Vector<Pronostico> prono = new Vector<Pronostico>();
				for (Apuestas a : apu) {
					prono.add(a.getPronostico());
				}
				for (Pronostico p : prono) {
					if (p.getPreguntaPronostico().toString().equals(q.toString())) {
						existente = true;
						break;
					}
				}
				if (!existente) {
					ApostarGUI apostarGUI = new ApostarGUI(pronosticofinal, usu, cate);
					apostarGUI.setVisible(true);
					setVisible(false);
				} else {
					labelerror.setVisible(true);
					labelerror.setForeground(Color.red);
					labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("NoApos"));
				}
			}
		});
		betButton.setBounds(187, 420, 130, 31);
		getContentPane().add(betButton);
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}