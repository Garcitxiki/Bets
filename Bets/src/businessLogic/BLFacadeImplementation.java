package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Apuestas;
import domain.Categoria;
import domain.Event;
import domain.Mensaje;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {

	DataAccess dbManager;

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		dbManager.open(false);
		dbManager.close();
	}

	public BLFacadeImplementation(boolean t) {
		System.out.println("Creating BLFacadeImplementation instance");
		dbManager.open(false);
		dbManager.close();
	}

	public BLFacadeImplementation(DataAccess da) {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c = ConfigXML.getInstance();
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();
		}
		dbManager = da;
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	@Override
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {
		dbManager.open(false);
		Question qry = null;
		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
		qry = dbManager.createQuestion(event, question, betMinimum);
		dbManager.close();
		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@Override
	@WebMethod
	public Vector<Event> getEvents(Date date) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@Override
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	/**
	 * Obtiene en Usuario a traves del nombre de usuario
	 * 
	 * @param nombreUsuario
	 * @return
	 */
	@Override
	public Usuario getUserByNombreUsuario(String nombreUsuario) {
		dbManager.open(false);
		Usuario u = dbManager.getUserByNombreUsuario(nombreUsuario);
		dbManager.close();
		return u;
	}

	/**
	 * Dados los datos de un evento, lo crea y lo almacena en la BD
	 * 
	 * @param description
	 * @param eventDate
	 * @param ca
	 */
	@Override
	public void createEvent(String description, Date eventDate, Categoria ca) {
		dbManager.open(false);
		dbManager.createEvent(description, eventDate, ca);
		dbManager.close();
	}

	/**
	 * Dado un nombre de usuario mira si existe el usuario en la BD
	 * 
	 * @param nombreUsuario
	 * @return TRUE si existe, FALSE si no existe
	 */
	@Override
	public boolean existeUsuario(String nombreUsuario) {
		dbManager.open(false);
		boolean t = dbManager.existeUsuario(nombreUsuario);
		dbManager.close();
		return t;
	}

	/**
	 * Dado los datos de un nuevo usuario lo crea y lo almacena en la BD
	 * 
	 * @param nombre
	 * @param apellido
	 * @param fechaNacimiento
	 * @param contraseña
	 * @param dNI
	 * @param nombreUsuario
	 */
	@Override
	public void ingresarUsuario(String nombre, String apellido, Date fechaNacimiento, String contrasena, String dNI,
			String nombreUsuario) {
		dbManager.open(false);
		dbManager.ingresarUsuario(nombre, apellido, fechaNacimiento, contrasena, dNI, nombreUsuario);
		dbManager.close();
	}

	/**
	 * Dado un nombre de usuario lo convierte en administrador
	 * 
	 * @param s
	 */
	@Override
	public void hacerAdmin(String s) {
		dbManager.open(false);
		dbManager.hacerAdmin(s);
		dbManager.close();
	}

	/**
	 * Dados los datos de un pronostico, lo crea y lo guarda en la base de datos
	 * 
	 * @param pronostico
	 * @param cuota
	 * @param preguntaPronostico
	 */
	@Override
	public void anadirPronostico(String pronostico, double cuota, Question preguntaPronostico) {
		dbManager.open(false);
		dbManager.anadirPronostico(pronostico, cuota, preguntaPronostico);
		dbManager.close();
	}

	/**
	 * Dado un usuario y una cantidad de dinero, le añade ese dinero al usario
	 * NOTA: Puede usarse para restar dinero si la cantidad de dinero es negativa
	 * 
	 * @param u
	 * @param f
	 */
	@Override
	public void addFounds(Usuario u, double f) {
		dbManager.open(false);
		dbManager.addFounds(u, f);
		dbManager.close();
	}

	/**
	 * Dado un numero de pregunta, obtiene sus pronosticos
	 * 
	 * @param j
	 * @return
	 */
	@Override
	public Vector<domain.Pronostico> obtenerPronostico(int j) {
		dbManager.open(false);
		Vector<domain.Pronostico> pron = dbManager.obtenerPronostico(j);
		dbManager.close();
		return pron;
	}

	/**
	 * Dado un usuario obtiene el dinero que tiene en la cartera
	 * 
	 * @param usu
	 * @return
	 */
	@Override
	public Double getCartera(Usuario usu) {
		dbManager.open(false);
		Double cart = dbManager.getCartera(usu);
		dbManager.close();
		return cart;
	}

	/**
	 * Dado un string con la descripcion de un evento, lo busca y lo devuelve
	 * 
	 * @param usu descripcion del evento
	 * @return
	 */
	@Override
	public Event getEvent(String usu) {
		dbManager.open(false);
		Event cart = dbManager.getEvent(usu);
		dbManager.close();
		return cart;
	}

	/**
	 * Dado un string con la descripcion de una pregunta, la busca y la devuelve
	 * 
	 * @param usu descripcion de la pregunta
	 * @return
	 */
	@Override
	public Question getQuestion(String usu) {
		dbManager.open(false);
		Question cart = dbManager.getQuestion(usu);
		dbManager.close();
		return cart;
	}

	/**
	 * Dado el numero de la pregunta, la busca y la devuelve
	 * 
	 * @param nu numero de la pregunta
	 * @return
	 */
	@Override
	public Question getQuestionByNumber(int nu) {
		dbManager.open(false);
		Question cart = dbManager.getQuestionByNumber(nu);
		dbManager.close();
		return cart;
	}

	/**
	 * Dado el numero del pronostico, lo busca y lo devuelve
	 * 
	 * @param pronosticoNumber numero del pronostico
	 * @return
	 */
	@Override
	public Pronostico getPronosticoByNumber(int pronosticoNumber) {
		dbManager.open(false);
		Pronostico pro = dbManager.getPronosticoByNumber(pronosticoNumber);
		dbManager.close();
		return pro;
	}

	/**
	 * Dado un pronostico, una cantidad y un usuario, crea la apuesta, la guarda y
	 * la asigna a usuario
	 * 
	 * @param pronostico
	 * @param cantidad
	 * @param user
	 */
	@Override
	public void anadirApuestaUsuario(Pronostico pronostico, double cantidad, Usuario user) {
		dbManager.open(false);
		dbManager.anadirApuestaUsuario(pronostico, cantidad, user);
		dbManager.close();
	}

	/**
	 * Dado un usuario y un Mensaje, le añade el mensaje al usuario
	 * 
	 * @param mensaje
	 * @param user
	 */
	@Override
	public void anadirMensajeUsuario(Mensaje mensaje, Usuario user) {
		dbManager.open(false);
		dbManager.anadirMensajeUsuario(mensaje, user);
		dbManager.close();
	}

	/**
	 * Dado un pronostico, lo valida, da el dinero a las apuestas que han ganado y
	 * borra la pregunta, los pronosticos y las apuestas asociadas a esa pregunta
	 * 
	 * @param pronostico
	 */
	@Override
	public void validarPronostico(Pronostico pronostico) {
		dbManager.open(false);
		dbManager.validarPronostico(pronostico);
		Question q = pronostico.getPreguntaPronostico();
		dbManager.eliminarApuestaPregunta(q);
		dbManager.eliminarPregunta(q);
		dbManager.close();

	}

	/**
	 * Dada una apuesta la elimina
	 * 
	 * @param ap
	 */
	@Override
	public void eliminarApuesta(Apuestas ap) {
		dbManager.open(false);
		dbManager.eliminarApuesta(ap);
		dbManager.close();

	}

	/**
	 * Dado un vector de apuestas, se las asigna al usuario
	 * 
	 * @param aps
	 * @param usu
	 */
	@Override
	public void actualizarApuestas(Vector<Apuestas> aps, Usuario usu) {
		dbManager.open(false);
		dbManager.actualizarApuestas(aps, usu);
		dbManager.close();
	}

	/**
	 * Dado un usuario y una string, le actualiza la contraeña a ese string
	 * 
	 * @param u
	 * @param contraseña
	 */
	@Override
	public void cambiarContrasena(Usuario u, String contrasena) {
		dbManager.open(false);
		dbManager.cambiarContrasena(u, contrasena);
		dbManager.close();
	}

	/**
	 * Dada la pregunta la elimina
	 * 
	 * @param q
	 * @return 1 si la ha eliminado, 0 si tenia apuestas y no se ha podido eliminar,
	 *         -1 else
	 */
	@Override
	public int eliminarPregunta(Question q) {
		dbManager.open(false);
		int s = dbManager.eliminarPregunta(q);
		dbManager.close();
		return s;
	}

	/**
	 * Devuelve un vector con las distintas categorias existentes
	 * 
	 * @return
	 */
	@Override
	public Vector<Categoria> getCategories() {
		dbManager.open(false);
		Vector<Categoria> ca = dbManager.obtainCategories();
		dbManager.close();
		return ca;

	}

	/**
	 * Devuelve una lista con los distintos mensajes existentes
	 * 
	 * @return
	 */
	@Override
	public List<Mensaje> getMensajes() {
		dbManager.open(false);
		List<Mensaje> mensajes = dbManager.getMensajes();
		dbManager.close();
		return mensajes;
	}

	/**
	 * Dado un pronostico lo trata de eliminar
	 * 
	 * @param p
	 * @return 1 si se ha eliminado, 0 si tenia apuestas asociadas
	 */
	@Override
	public int eliminarPronotico(Pronostico p) {
		dbManager.open(false);
		int s = dbManager.eliminarPronostico(p);
		dbManager.close();
		return s;
	}

	/**
	 * Dado un evento lo trata de eliminar
	 * 
	 * @param p
	 * @return 1 si se ha eliminado, 0 si tenia apuestas asociadas
	 */
	@Override
	public int eliminarEvento(Event ev) {
		dbManager.open(false);
		boolean b = dbManager.eventoTieneApuestas(ev);
		int res = -1;
		if (b) {
			res = 0;
		} else {
			Vector<Question> n = ev.getQuestions();
			for (Question q : n) {
				dbManager.eliminarPregunta(q);
			}
			res = dbManager.eliminarEvento(ev);

		}
		dbManager.close();
		return res;
	}

	/**
	 * Dado un usuario y un string añade, crea el mensaje y lo almacena
	 * 
	 * @param user
	 * @param s
	 */
	@Override
	public void addMensaje(Usuario user, String s) {
		dbManager.open(false);
		dbManager.addMensaje(user, s);
		dbManager.close();
	}

	/**
	 * Dada una descripcion, obtiene la categoria cuya descripcion coincida con el
	 * string dado
	 * 
	 * @param description
	 * @return
	 */
	@Override
	public Categoria obtenerCategoriaPorDescripcion(String description) {
		dbManager.open(false);
		Categoria cat = dbManager.obtenerCategoriaPorDescripcion(description);
		dbManager.close();
		return cat;
	}

	/**
	 * Devuelve las fechas en los que hay eventos de una categoria en especial
	 * 
	 * @param date
	 * @param cat
	 * @return
	 */
	@Override
	public Vector<Date> getEventsCategoryMonth(Date date, Categoria cat) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getEventsCategoryMonth(date, cat);
		dbManager.close();
		return dates;
	}

}
