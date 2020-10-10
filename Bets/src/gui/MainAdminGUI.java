package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.imageio.ImageIO;
/**
 * @author Software Engineering teachers
 */
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Event;
import domain.Usuario;

public class MainAdminGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	private Usuario user = null;
	private JButton jButtonLogout = null;
	private JButton jButtonCreateEvent = null;
	private static BLFacade appFacadeInterface;
	private JPopupMenu popupMenu_1;
	private JPopupMenu popupMenu_2;
	private JButton btnEliminar;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;

	/**
	 * This is the default constructor
	 */
	public MainAdminGUI(Usuario usu) {
		super();
		this.user = usu;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(490, 352);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());

			JButton btnLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Logout"));
			btnLogout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);

					LoginGUI pestanaLogin = new LoginGUI();
					setVisible(false);
					pestanaLogin.setVisible(true);
					setVisible(false);

				}
			});

			btnLogout.setBounds(319, 22, 150, 23);
			jContentPane.add(btnLogout);

			JButton btnAddAdmin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddAdmin"));
			btnAddAdmin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					AddAdminGUI add = new AddAdminGUI();
					add.setVisible(true);
				}
			});
			btnAddAdmin.setBounds(0, 117, 479, 62);
			jContentPane.add(btnAddAdmin);

			JButton btnValidarPron = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ValPron"));
			btnValidarPron.setBounds(0, 180, 479, 59);
			btnValidarPron.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ValidPronGUI val = new ValidPronGUI(user);
					val.setVisible(true);
					setVisible(false);
				}
			});
			jContentPane.add(btnValidarPron);

			btnEliminar = new JButton();
			popupMenu_1 = new JPopupMenu();

			btnEliminar.setVerticalTextPosition(SwingConstants.CENTER);
			btnEliminar.setOpaque(false);
			btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
			btnEliminar.setContentAreaFilled(false);
			btnEliminar.setBorderPainted(true);
			btnEliminar.setBackground(Color.GRAY);
			btnEliminar.setBounds(411, 250, 52, 47);

			BufferedImage imagenEliminar = null;
			try {
				imagenEliminar = ImageIO.read(getClass().getResource("/eliminar.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Image scaledInstanceEliminar = imagenEliminar.getScaledInstance(btnEliminar.getWidth(),
					btnEliminar.getHeight(), Image.SCALE_SMOOTH);
			btnEliminar.setIcon(new ImageIcon(scaledInstanceEliminar));

			final JMenuItem elimPreg = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("DelQue"));
			elimPreg.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DeleteQuestionGUI del = new DeleteQuestionGUI(user);
					del.setVisible(true);
					setVisible(false);
				}
			});

			final JMenuItem elimPron = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("DelPro"));
			elimPron.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DeletePronGUI del1 = new DeletePronGUI(user);
					del1.setVisible(true);
					setVisible(false);
				}
			});

			final JMenuItem elimEv = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("DelEv"));
			elimEv.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DeleteEventGUI del2 = new DeleteEventGUI(user);
					del2.setVisible(true);
					setVisible(false);
				}
			});

			popupMenu_1.add(elimPreg);
			popupMenu_1.add(elimPron);
			popupMenu_1.add(elimEv);
			btnEliminar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					popupMenu_1.show(e.getComponent(), 10, 40);
				}
			});

			jContentPane.add(btnEliminar);
			addPopup(btnEliminar, popupMenu_1);

			JButton btnForo = new JButton();
			btnForo.setBounds(10, 250, 52, 41);
			BufferedImage imagenForo = null;
			try {
				imagenForo = ImageIO.read(getClass().getResource("/foro.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			btnForo.setBackground(Color.GRAY);
			btnForo.setOpaque(false);
			btnForo.setContentAreaFilled(false);
			btnForo.setBorderPainted(false);
			btnForo.setVerticalTextPosition(SwingConstants.CENTER);
			btnForo.setHorizontalTextPosition(SwingConstants.CENTER);
			Image scaledInstanceForo = imagenForo.getScaledInstance(btnForo.getWidth(), btnForo.getHeight(),
					Image.SCALE_SMOOTH);
			// convert the Image to BufferedImage
			btnForo.setIcon(new ImageIcon(scaledInstanceForo));
			btnForo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ForoGUI foro = new ForoGUI(user);
					setVisible(false);
					foro.setVisible(true);
				}
			});

			jContentPane.add(btnForo);

			JButton btnAnadir = new JButton();
			popupMenu_2 = new JPopupMenu();

			btnAnadir.setVerticalTextPosition(SwingConstants.CENTER);
			btnAnadir.setOpaque(false);
			btnAnadir.setHorizontalTextPosition(SwingConstants.CENTER);
			btnAnadir.setContentAreaFilled(false);
			btnAnadir.setBorderPainted(true);
			btnAnadir.setBackground(Color.GRAY);
			btnAnadir.setBounds(218, 250, 52, 47);

			BufferedImage imagenAnadir = null;
			try {
				imagenAnadir = ImageIO.read(getClass().getResource("/añadir.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Image scaledInstanceAnadir = imagenAnadir.getScaledInstance(btnAnadir.getWidth(), btnAnadir.getHeight(),
					Image.SCALE_SMOOTH);
			btnAnadir.setIcon(new ImageIcon(scaledInstanceAnadir));

			final JMenuItem anadirPreg = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
			anadirPreg.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					BLFacade facade = LoginGUI.getBusinessLogic();
					JFrame a = new CreateQuestionGUI(new Vector<Event>(), user);
					a.setVisible(true);
				}
			});

			final JMenuItem anadirPron = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("TitAnPro"));
			anadirPron.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					AddPronosticGUI g = new AddPronosticGUI(new Vector<domain.Event>(), user);
					g.setVisible(true);
					setVisible(false);
				}
			});

			final JMenuItem anadirEv = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("CreateEvents"));
			anadirEv.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					CreateEventGUI pestanaEvent;
					try {
						pestanaEvent = new CreateEventGUI();
						setVisible(true);
						pestanaEvent.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});

			popupMenu_2.add(anadirPreg);
			popupMenu_2.add(anadirPron);
			popupMenu_2.add(anadirEv);
			btnAnadir.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					popupMenu_2.show(e.getComponent(), 10, 40);
				}
			});

			jContentPane.add(btnAnadir);

		}
		return jContentPane;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(0, 56, 479, 62);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new QueryQuestionAdminGUI(user);
					a.setVisible(true);
					setVisible(false);
				}
			});
		}
		return jButtonQueryQueries;
	}

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(0, 1, 378, 62);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}

	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		jButtonCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvents"));
		jButtonLogout.setText(ResourceBundle.getBundle("Etiquetas").getString("Logout"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
