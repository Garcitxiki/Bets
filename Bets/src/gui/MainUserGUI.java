package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ResourceBundle;

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
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Usuario;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainUserGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPopupMenu popupMenu;
	private JPopupMenu popupMenu_1;
	private JButton btnPuntos;
	private JPanel jContentPane = null;
	private JButton jButtonQueryEvents = null;

	
	private Usuario usu=null;
    private static BLFacade appFacadeInterface = LoginGUI.getBusinessLogic();;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * This is the default constructor
	 */
	public MainUserGUI( Usuario u) {
		super();
		usu=u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(495, 341);
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
			
			JButton btnLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Logout")); 
			btnLogout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
						LoginGUI pestanaLogin = new LoginGUI();
						setVisible(false);
						pestanaLogin.setVisible(true);
					}
				});
				
			btnLogout.setBounds(10, 22, 136, 23);
			jContentPane.add(btnLogout);
			
			JButton btnUser = new JButton(); 
			BufferedImage imagenPerfil = null;
			try {
				imagenPerfil = ImageIO.read(getClass().getResource("/miperfil.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			btnUser.setBackground(Color.GRAY);
			btnUser.setOpaque(false);
			btnUser.setContentAreaFilled(false);
			btnUser.setBorderPainted(false);
			btnUser.setVerticalTextPosition(SwingConstants.CENTER);
			btnUser.setHorizontalTextPosition(SwingConstants.CENTER);
			btnUser.setBounds(431, 22, 38, 33);
			Image scaledInstance = imagenPerfil.getScaledInstance(btnUser.getWidth(), btnUser.getHeight(), Image.SCALE_SMOOTH);
	        // convert the Image to BufferedImage
			btnUser.setIcon(new ImageIcon(scaledInstance));
			
			btnUser.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MiPerfil m = new MiPerfil(usu);
					m.setVisible(true);
					setVisible(false);
				}
			});
			jContentPane.add(btnUser);
			
			JButton btnForo = new JButton(); 
			btnForo.setBounds(10, 250, 50, 41);
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
			Image scaledInstanceForo = imagenForo.getScaledInstance(btnForo.getWidth(), btnForo.getHeight(), Image.SCALE_SMOOTH);
	        // convert the Image to BufferedImage
			btnForo.setIcon(new ImageIcon(scaledInstanceForo));
			btnForo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ForoGUI foro = new ForoGUI(usu);
					setVisible(false);
					foro.setVisible(true);
				}
			});
			
			jContentPane.add(btnForo);
			
			
			JButton btnFutbol = new JButton();
			btnFutbol.setVerticalTextPosition(SwingConstants.CENTER);
			btnFutbol.setOpaque(false);
			btnFutbol.setHorizontalTextPosition(SwingConstants.CENTER);
			btnFutbol.setContentAreaFilled(false);
			btnFutbol.setBorderPainted(true);
			btnFutbol.setBackground(Color.GRAY);
			btnFutbol.setBounds(54, 74, 64, 62);
			BufferedImage imagenFutbol = null;
			try {
				imagenFutbol = ImageIO.read(getClass().getResource("/futbol.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Image scaledInstanceFutbol = imagenFutbol.getScaledInstance(btnFutbol.getWidth(), btnFutbol.getHeight(), Image.SCALE_SMOOTH);
	        // convert the Image to BufferedImage
			btnFutbol.setIcon(new ImageIcon(scaledInstanceFutbol));
			btnFutbol.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new FindQuestionsGUI(usu, appFacadeInterface.obtenerCategoriaPorDescripcion("Futbol"));
					a.setVisible(true);
					setVisible(false);
				}
			});
			jContentPane.add(btnFutbol);
			
			JButton btnBaloncesto = new JButton();
			btnBaloncesto.setVerticalTextPosition(SwingConstants.CENTER);
			btnBaloncesto.setOpaque(false);
			btnBaloncesto.setHorizontalTextPosition(SwingConstants.CENTER);
			btnBaloncesto.setContentAreaFilled(false);
			btnBaloncesto.setBorderPainted(true);
			btnBaloncesto.setBackground(Color.GRAY);
			btnBaloncesto.setBounds(156, 74, 64, 62);
			BufferedImage imagenBaloncesto = null;
			try {
				imagenBaloncesto = ImageIO.read(getClass().getResource("/baloncesto.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Image scaledInstanceBaloncesto = imagenBaloncesto.getScaledInstance(btnBaloncesto.getWidth(), btnBaloncesto.getHeight(), Image.SCALE_SMOOTH);
	        // convert the Image to BufferedImage
			btnBaloncesto.setIcon(new ImageIcon(scaledInstanceBaloncesto));
			btnBaloncesto.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new FindQuestionsGUI(usu, appFacadeInterface.obtenerCategoriaPorDescripcion("Baloncesto"));
					a.setVisible(true);
					setVisible(false);
				}
			});
			jContentPane.add(btnBaloncesto);
			
			JButton btnTenis = new JButton();
			btnTenis.setVerticalTextPosition(SwingConstants.CENTER);
			btnTenis.setOpaque(false);
			btnTenis.setHorizontalTextPosition(SwingConstants.CENTER);
			btnTenis.setContentAreaFilled(false);
			btnTenis.setBorderPainted(true);
			btnTenis.setBackground(Color.GRAY);
			btnTenis.setBounds(273, 74, 64, 62);
			BufferedImage imagenTenis = null;
			try {
				imagenTenis = ImageIO.read(getClass().getResource("/tenis.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Image scaledInstanceTenis = imagenTenis.getScaledInstance(btnTenis.getWidth(), btnTenis.getHeight(), Image.SCALE_SMOOTH);
	        // convert the Image to BufferedImage
			btnTenis.setIcon(new ImageIcon(scaledInstanceTenis));
			btnTenis.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new FindQuestionsGUI(usu, appFacadeInterface.obtenerCategoriaPorDescripcion("Tenis"));
					a.setVisible(true);
					setVisible(false);
				}
			});
			jContentPane.add(btnTenis);
			
			JButton btnBeisbol = new JButton();
			btnBeisbol.setVerticalTextPosition(SwingConstants.CENTER);
			btnBeisbol.setOpaque(false);
			btnBeisbol.setHorizontalTextPosition(SwingConstants.CENTER);
			btnBeisbol.setContentAreaFilled(false);
			btnBeisbol.setBorderPainted(true);
			btnBeisbol.setBackground(Color.GRAY);
			btnBeisbol.setBounds(378, 74, 64, 62);
			BufferedImage imagenBeisbol = null;
			try {
				imagenBeisbol = ImageIO.read(getClass().getResource("/beisbol.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Image scaledInstanceBeisbol = imagenBeisbol.getScaledInstance(btnBeisbol.getWidth(), btnBeisbol.getHeight(), Image.SCALE_SMOOTH);
	        // convert the Image to BufferedImage
			btnBeisbol.setIcon(new ImageIcon(scaledInstanceBeisbol));
			btnBeisbol.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new FindQuestionsGUI(usu, appFacadeInterface.obtenerCategoriaPorDescripcion("Beisbol"));
					a.setVisible(true);
					setVisible(false);
				}
			});
			jContentPane.add(btnBeisbol);
			
			JButton btnBoxeo = new JButton();
			btnBoxeo.setVerticalTextPosition(SwingConstants.CENTER);
			btnBoxeo.setOpaque(false);
			btnBoxeo.setHorizontalTextPosition(SwingConstants.CENTER);
			btnBoxeo.setContentAreaFilled(false);
			btnBoxeo.setBorderPainted(true);
			btnBoxeo.setBackground(Color.GRAY);
			btnBoxeo.setBounds(50, 177, 64, 62);
			BufferedImage imagenBoxeo = null;
			try {
				imagenBoxeo = ImageIO.read(getClass().getResource("/boxeo.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Image scaledInstanceBoxeo = imagenBoxeo.getScaledInstance(btnBoxeo.getWidth(), btnBoxeo.getHeight(), Image.SCALE_SMOOTH);
	        // convert the Image to BufferedImage
			btnBoxeo.setIcon(new ImageIcon(scaledInstanceBoxeo));
			btnBoxeo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new FindQuestionsGUI(usu, appFacadeInterface.obtenerCategoriaPorDescripcion("Boxeo"));
					a.setVisible(true);
					setVisible(false);
				}
			});
			jContentPane.add(btnBoxeo);
			
			JButton btnFutbolAmericano = new JButton();
			btnFutbolAmericano.setVerticalTextPosition(SwingConstants.CENTER);
			btnFutbolAmericano.setOpaque(false);
			btnFutbolAmericano.setHorizontalTextPosition(SwingConstants.CENTER);
			btnFutbolAmericano.setContentAreaFilled(false);
			btnFutbolAmericano.setBorderPainted(true);
			btnFutbolAmericano.setBackground(Color.GRAY);
			btnFutbolAmericano.setBounds(156, 177, 64, 62);
			BufferedImage imagenFutbolAmericano = null;
			try {
				imagenFutbolAmericano = ImageIO.read(getClass().getResource("/futbolamericano.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Image scaledInstanceFutbolAmericano = imagenFutbolAmericano.getScaledInstance(btnFutbolAmericano.getWidth(), btnFutbolAmericano.getHeight(), Image.SCALE_SMOOTH);
	        // convert the Image to BufferedImage
			btnFutbolAmericano.setIcon(new ImageIcon(scaledInstanceFutbolAmericano));
			btnFutbolAmericano.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new FindQuestionsGUI(usu, appFacadeInterface.obtenerCategoriaPorDescripcion("FutbolAmericano"));
					a.setVisible(true);
					setVisible(false);
				}
			});
			jContentPane.add(btnFutbolAmericano);
			
			JButton btnUfc = new JButton();
			btnUfc.setVerticalTextPosition(SwingConstants.CENTER);
			btnUfc.setOpaque(false);
			btnUfc.setHorizontalTextPosition(SwingConstants.CENTER);
			btnUfc.setContentAreaFilled(false);
			btnUfc.setBorderPainted(true);
			btnUfc.setBackground(Color.GRAY);
			btnUfc.setBounds(273, 177, 64, 62);
			BufferedImage imagenUfc = null;
			try {
				imagenUfc = ImageIO.read(getClass().getResource("/ufc.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Image scaledInstanceUfc = imagenUfc.getScaledInstance(btnUfc.getWidth(), btnUfc.getHeight(), Image.SCALE_SMOOTH);
	        // convert the Image to BufferedImage
			btnUfc.setIcon(new ImageIcon(scaledInstanceUfc));
			btnUfc.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new FindQuestionsGUI(usu, appFacadeInterface.obtenerCategoriaPorDescripcion("Ufc"));
					a.setVisible(true);
					setVisible(false);
				}
			});
			jContentPane.add(btnUfc);
			
			
			btnPuntos = new JButton();
			popupMenu = new JPopupMenu();
			btnPuntos.setVerticalTextPosition(SwingConstants.CENTER);
			btnPuntos.setOpaque(false);
			btnPuntos.setHorizontalTextPosition(SwingConstants.CENTER);
			btnPuntos.setContentAreaFilled(false);
			btnPuntos.setBorderPainted(true);
			btnPuntos.setBackground(Color.GRAY);
			btnPuntos.setBounds(378, 177, 64, 62);
			BufferedImage imagenPuntos = null;
			try {
				imagenPuntos = ImageIO.read(getClass().getResource("/puntos.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Image scaledInstancePuntos = imagenPuntos.getScaledInstance(btnPuntos.getWidth(), btnPuntos.getHeight(), Image.SCALE_SMOOTH);
	        // convert the Image to BufferedImage
			btnPuntos.setIcon(new ImageIcon(scaledInstancePuntos));
			
			popupMenu_1 = new JPopupMenu();
			final JMenuItem f1 = new JMenuItem(appFacadeInterface.obtenerCategoriaPorDescripcion("Formula1").getDescription());
			f1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new FindQuestionsGUI(usu, appFacadeInterface.obtenerCategoriaPorDescripcion("Formula1"));
					a.setVisible(true);
					setVisible(false);
				}
			});
			
			final JMenuItem mgp = new JMenuItem(appFacadeInterface.obtenerCategoriaPorDescripcion("MotoGP").getDescription());
			mgp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new FindQuestionsGUI(usu, appFacadeInterface.obtenerCategoriaPorDescripcion("MotoGP"));
					a.setVisible(true);
					setVisible(false);
				}
			});
			
			

			popupMenu_1.add(f1);
			popupMenu_1.add(mgp);
			btnPuntos.addMouseListener(new MouseAdapter() {
				@Override
	            public void mouseClicked(MouseEvent e) {
					popupMenu_1.show(e.getComponent(), 60, 60);
	            }
	        });
			jContentPane.add(btnPuntos);
			addPopup(btnPuntos, popupMenu_1);
			
		}
		return jContentPane;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(20, 1, 282, 62);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return jLabelSelectOption;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
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