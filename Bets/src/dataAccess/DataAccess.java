package dataAccess;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Apuestas;
import domain.Categoria;
import domain.Event;
import domain.Mensaje;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;
	private String fileName = "Bets.odb";

	ConfigXML c = ConfigXML.getInstance();

	public DataAccess(boolean initializeMode) {
		open(initializeMode);
	}

	public void open(boolean initializeMode) {

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public DataAccess() {
		new DataAccess(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {
			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}
			Categoria ca1 = new Categoria("Futbol");
			Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17), ca1);
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17), ca1);
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17), ca1);
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17), ca1);
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17), ca1);
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17), ca1);
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17), ca1);
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17), ca1);
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17), ca1);
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17), ca1);
			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 2), ca1);
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 2), ca1);
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 2), ca1);
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 2), ca1);
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 2), ca1);
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 2), ca1);
			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month, 28), ca1);
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month, 28), ca1);
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month, 28), ca1);
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month, 28), ca1);
			ca1.addEvent(ev1);
			ca1.addEvent(ev2);
			ca1.addEvent(ev3);
			ca1.addEvent(ev4);
			ca1.addEvent(ev5);
			ca1.addEvent(ev6);
			ca1.addEvent(ev7);
			ca1.addEvent(ev8);
			ca1.addEvent(ev9);
			ca1.addEvent(ev10);
			ca1.addEvent(ev11);
			ca1.addEvent(ev12);
			ca1.addEvent(ev13);
			ca1.addEvent(ev14);
			ca1.addEvent(ev15);
			ca1.addEvent(ev16);
			ca1.addEvent(ev17);
			ca1.addEvent(ev18);
			ca1.addEvent(ev19);
			ca1.addEvent(ev20);

			Categoria ca2 = new Categoria("Baloncesto");
			Event ev21 = new Event(21, "Lakers-Chicago Bulls", UtilDate.newDate(year, month, 17), ca2);
			Event ev22 = new Event(22, "Celtics-Golden State Warriors", UtilDate.newDate(year, month, 17), ca2);
			Event ev23 = new Event(23, "Miami heat-Memphis Grizzlies", UtilDate.newDate(year, month, 17), ca2);
			Event ev24 = new Event(24, "Raptors-Cavaliers", UtilDate.newDate(year, month, 17), ca2);
			Event ev25 = new Event(25, "Madrid-Barcelona", UtilDate.newDate(year, month, 18), ca2);
			Event ev26 = new Event(26, "Baskonia-Barcelona", UtilDate.newDate(year, month, 20), ca2);
			ca2.addEvent(ev21);
			ca2.addEvent(ev22);
			ca2.addEvent(ev23);
			ca2.addEvent(ev24);
			ca2.addEvent(ev25);
			ca2.addEvent(ev26);

			Categoria ca3 = new Categoria("Tenis");
			Event ev27 = new Event(27, "Roger Federer-Rafael Nadal", UtilDate.newDate(year, month, 5), ca3);
			Event ev28 = new Event(28, "Roger Federer-Rafael Nadal", UtilDate.newDate(year, month, 29), ca3);
			Event ev29 = new Event(29, "Novak Djokovic-Maria Sharapova", UtilDate.newDate(year, month, 9), ca3);
			Event ev30 = new Event(30, "Novak Djokovic-Rafael Nadal", UtilDate.newDate(year, month, 14), ca3);

			ca3.addEvent(ev27);
			ca3.addEvent(ev28);
			ca3.addEvent(ev29);
			ca3.addEvent(ev30);

			Categoria ca4 = new Categoria("Beisbol");
			Event ev31 = new Event(31, "New York Yankees-New York Mets", UtilDate.newDate(year, month, 4), ca4);
			Event ev32 = new Event(32, "San Francisco Giants-New York Yankees", UtilDate.newDate(year, month, 28), ca4);
			Event ev33 = new Event(33, "L.A. Dodgers-Houston Astros", UtilDate.newDate(year, month, 9), ca4);

			ca4.addEvent(ev31);
			ca4.addEvent(ev32);
			ca4.addEvent(ev33);

			Categoria ca5 = new Categoria("Boxeo");
			Event ev34 = new Event(34, "Ryan García-Tyson Fury", UtilDate.newDate(year, month, 5), ca5);
			Event ev35 = new Event(35, "Manny Pacquiao-Floyd Mayweather", UtilDate.newDate(year, month, 28), ca5);
			Event ev36 = new Event(36, "Ryan García-Mike Tyson", UtilDate.newDate(year, month, 24), ca5);
			Event ev37 = new Event(37, "Floyd Mayweather-Mike Tyson", UtilDate.newDate(year, month, 9), ca5);

			ca5.addEvent(ev34);
			ca5.addEvent(ev35);
			ca5.addEvent(ev36);
			ca5.addEvent(ev37);

			Categoria ca6 = new Categoria("FutbolAmericano");
			Event ev38 = new Event(38, "Patriots-DallasCowboys", UtilDate.newDate(year, month, 15), ca6);
			Event ev39 = new Event(39, "New York Giants-Miami Dolphins", UtilDate.newDate(year, month, 15), ca6);
			Event ev40 = new Event(40, "Philadelphia Eagles-Minnesota Vikings", UtilDate.newDate(year, month, 16), ca6);
			Event ev41 = new Event(41, "Arizona Cardinals-Buffalo Bills", UtilDate.newDate(year, month, 16), ca6);

			ca6.addEvent(ev38);
			ca6.addEvent(ev39);
			ca6.addEvent(ev40);
			ca6.addEvent(ev41);

			Categoria ca7 = new Categoria("Ufc");
			Event ev42 = new Event(42, "Conor McGregor-Khabib Nurmagomedov", UtilDate.newDate(year, month, 15), ca7);
			Event ev43 = new Event(43, "Israel Adesanya-Henry Cejudo", UtilDate.newDate(year, month, 15), ca7);
			Event ev44 = new Event(44, "Kamaru Usman-Stipe Miocic", UtilDate.newDate(year, month, 15), ca7);

			Categoria ca8 = new Categoria("Formula1");
			Event ev45 = new Event(45, "Circuito de Montmeló", UtilDate.newDate(year, month, 7), ca8);

			Categoria ca9 = new Categoria("MotoGP");
			Event ev46 = new Event(46, "QNB Gran Prix of Qatar", UtilDate.newDate(year, month, 7), ca9);

			ca7.addEvent(ev42);
			ca7.addEvent(ev43);
			ca7.addEvent(ev44);

			ca8.addEvent(ev45);

			ca9.addEvent(ev46);

			db.persist(ca1);
			db.persist(ca2);
			db.persist(ca3);
			db.persist(ca4);
			db.persist(ca5);
			db.persist(ca6);
			db.persist(ca7);
			db.persist(ca8);
			db.persist(ca9);

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);
			}

			Pronostico p1;
			Pronostico p2;
			Pronostico p3;
			Pronostico p4;
			Pronostico p5;
			Pronostico p6;
			Pronostico p7;
			Pronostico p8;
			Pronostico p9;

			p1 = q1.addPronostico("1", 1.4, q1);
			p2 = q1.addPronostico("X", 3, q1);
			p3 = q1.addPronostico("2", 12.3, q1);
			p4 = q2.addPronostico("Muniain", 1.4, q2);
			p5 = q2.addPronostico("Raul Garcia", 3, q2);
			p6 = q2.addPronostico("Ramos", 5, q2);
			p7 = q5.addPronostico("1", 1.4, q3);
			p8 = q5.addPronostico("X", 3, q5);
			p9 = q5.addPronostico("2", 5, q5);

			Usuario s2 = new Usuario("Julen", "Barro", UtilDate.newDate(2000, 07, 07), "user", "68755555L", "User");
			Usuario s1 = new Usuario("Jon", "Vadillo", UtilDate.newDate(2000, 05, 05), "admin", "78555555L", "Admin");
			s1.setEsAdmin(true);

			Date fecha1 = new Date("2020/03/24 18:43:23");
			Date fecha2 = new Date("2020/03/26 13:23:01");
			Date fecha3 = new Date("2020/03/30 23:23:23");

			Mensaje m1 = new Mensaje(0, s2,
					"Tengo dudas sobre quien ganara el partido entre la Real y el Athletic. Recomendaciones?", fecha1);
			Mensaje m2 = new Mensaje(1, s1, "Buenas Julen, yo apostaria por la Real que anda muy fuerte ultimamente.",
					fecha2);
			Mensaje m3 = new Mensaje(2, s2, "Ok, eso haré. Gracias por la recomendacion.", fecha3);
			Vector<Mensaje> mm = new Vector<Mensaje>();
			Vector<Mensaje> mmm = new Vector<Mensaje>();
			mm.add(m2);
			mmm.add(m1);
			mmm.add(m3);
			s1.setMensajes(mm);
			s2.setMensajes(mmm);

			db.persist(s1);
			db.persist(s2);

			db.persist(p1);
			db.persist(p2);
			db.persist(p3);

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);
			db.persist(ev21);
			db.persist(ev22);
			db.persist(ev23);
			db.persist(ev24);
			db.persist(ev25);
			db.persist(ev26);
			db.persist(ev27);
			db.persist(ev28);
			db.persist(ev29);
			db.persist(ev30);
			db.persist(ev31);
			db.persist(ev32);
			db.persist(ev33);
			db.persist(ev34);
			db.persist(ev35);
			db.persist(ev36);
			db.persist(ev37);
			db.persist(ev38);
			db.persist(ev39);
			db.persist(ev40);
			db.persist(ev41);
			db.persist(ev42);
			db.persist(ev43);
			db.persist(ev44);
			db.persist(ev45);
			db.persist(ev46);

			db.persist(m1);
			db.persist(m2);
			db.persist(m3);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);
		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		db.persist(ev);
		db.getTransaction().commit();
		return q;

	}

	/**
	 * Devuelve las fechas en los que hay eventos de una categoria en especial
	 * 
	 * @param date
	 * @param cat
	 * @return
	 */
	public void createEvent(String description, Date eventDate, Categoria ca) {
		System.out.println(">> DataAccess: createEvent=> description= " + description + " eventDate=" + eventDate);

		boolean t = false;

		TypedQuery<Event> query = db.createQuery("SELECT e FROM Event e WHERE e.description='" + description + "'",
				Event.class);
		List<Event> eventos = query.getResultList();
		for (Event e : eventos) {
			if (e.getEventDate().equals(eventDate)) {
				t = true;
			}
		}

		if (!t) {
			db.getTransaction().begin();

			Categoria c = db.find(Categoria.class, ca.getCatNumber());
			Event e = new Event(description, eventDate, ca);
			c.addEvent(e);
			db.persist(e);
			db.getTransaction().commit();
		}

	}

	/**
	 * Dados los datos de un pronostico, lo crea y lo guarda en la base de datos
	 * 
	 * @param pronostico
	 * @param cuota
	 * @param preguntaPronostico
	 */
	public void anadirPronostico(String pronostico, double cuota, Question preguntaPronostico) {
		System.out.println(">> DataAccess: añadirPronostico=> pronostico= " + pronostico + " preguntaPronostico="
				+ preguntaPronostico);

		db.getTransaction().begin();
		Question q = db.find(Question.class, preguntaPronostico.getQuestionNumber());
		// Pronostico e = q.addPronostico(pronostico, cuota, preguntaPronostico);
		Pronostico e = new Pronostico(pronostico, cuota, q);
		db.persist(e);
		q.addPron(e);
		db.getTransaction().commit();
	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}

	/**
	 * Dado un nombre de usuario lo convierte en administrador
	 * 
	 * @param nombreUsuario
	 */
	public void hacerAdmin(String nombreUsuario) {
		Usuario u = db.find(Usuario.class, nombreUsuario);
		db.getTransaction().begin();
		boolean b = u.isEsAdmin();
		u.setEsAdmin(!b);
		db.getTransaction().commit();
	}

	/**
	 * Obtiene en Usuario a traves del nombre de usuario
	 * 
	 * @param nombreUsuario
	 * @return
	 */
	public Usuario getUserByNombreUsuario(String nombreUsuario) {
		Usuario u = db.find(Usuario.class, nombreUsuario);
		return u;
	}

	/**
	 * Cierra el entity manager
	 */
	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	/**
	 * Dado un nombre de usuario mira si existe el usuario en la BD
	 * 
	 * @param nombreUsuario
	 * @return TRUE si existe, FALSE si no existe
	 */
	public boolean existeUsuario(String nombreUsuario) {
		if (nombreUsuario != null) {
			if (db.find(Usuario.class, nombreUsuario) != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
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
	public void ingresarUsuario(String nombre, String apellido, Date fechaNacimiento, String contrasena, String dNI,
			String nombreUsuario) {
		Usuario u = new Usuario(nombre, apellido, fechaNacimiento, contrasena, dNI, nombreUsuario);
		db.getTransaction().begin();
		db.persist(u);
		db.getTransaction().commit();
	}

	/**
	 * Dado un usuario y una cantidad de dinero, le añade ese dinero al usario
	 * NOTA: Puede usarse para restar dinero si la cantidad de dinero es negativa
	 * 
	 * @param u
	 * @param f
	 */
	public void addFounds(Usuario u, double f) {
		Usuario ub = db.find(Usuario.class, u.getNombreUsuario());
		db.getTransaction().begin();
		double fin = ub.getCartera() + f;
		ub.setCartera(fin);
		db.persist(ub);
		db.getTransaction().commit();
	}

	/**
	 * Dado un numero de pregunta, obtiene sus pronosticos
	 * 
	 * @param j
	 * @return
	 */
	public Vector<domain.Pronostico> obtenerPronostico(int j) {
		Vector<Pronostico> pron = null;
		TypedQuery<Question> query1 = db.createQuery("SELECT que FROM Question que", Question.class);
		List<Question> q = query1.getResultList();
		for (int i = 0; i < q.size(); i++) {
			if (q.get(i).getQuestionNumber() == j) {
				pron = q.get(i).getPronostico();
				System.out.println("Pronsotico añadido: " + pron.toString());
			}
		}

		return pron;
	}

	/**
	 * Dado un usuario obtiene el dinero que tiene en la cartera
	 * 
	 * @param usu
	 * @return
	 */
	public Double getCartera(Usuario usu) {
		Usuario ub = db.find(Usuario.class, usu.getNombreUsuario());
		double cart = ub.getCartera();
		return cart;
	}

	/**
	 * Dado un string con la descripcion de un evento, lo busca y lo devuelve
	 * 
	 * @param usu descripcion del evento
	 * @return
	 */
	public Event getEvent(String str) {
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.description==?1", Event.class);
		query.setParameter(1, str);
		List<Event> eventos = query.getResultList();
		Event ev = eventos.get(0);
		return ev;
	}

	/**
	 * Dado un string con la descripcion de una pregunta, la busca y la devuelve
	 * 
	 * @param usu descripcion de la pregunta
	 * @return
	 */
	public Question getQuestion(String str) {
		TypedQuery<Question> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.question==?1", Question.class);
		query.setParameter(1, str);
		List<Question> eventos = query.getResultList();
		Question que = eventos.get(0);
		return que;
	}

	/**
	 * Dado el numero de la pregunta, la busca y la devuelve
	 * 
	 * @param nu numero de la pregunta
	 * @return
	 */
	public Question getQuestionByNumber(int nu) {
		TypedQuery<Question> query = db.createQuery("SELECT ev FROM Question ev WHERE ev.questionNumber==?1",
				Question.class);
		query.setParameter(1, nu);
		List<Question> eventos = query.getResultList();
		Question que = eventos.get(0);
		return que;
	}

	/**
	 * Devuelve un vector de Usuario con todos los usuarios de la base de datos
	 * 
	 * @return
	 */
	public Vector<Usuario> obtenerUsuarios() {
		Vector<Usuario> res = new Vector<Usuario>();
		TypedQuery<Usuario> query = db.createQuery("SELECT ev FROM Usuario ev", Usuario.class);
		List<Usuario> events = query.getResultList();
		for (Usuario ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	/**
	 * Dado el numero del pronostico, lo busca y lo devuelve
	 * 
	 * @param pronosticoNumber numero del pronostico
	 * @return
	 */
	public Pronostico getPronosticoByNumber(int pronosticoNumber) {
		return db.find(Pronostico.class, pronosticoNumber);
	}

	/**
	 * Dado un pronostico, una cantidad y un usuario, crea la apuesta, la guarda y
	 * la asigna a usuario
	 * 
	 * @param pronostico
	 * @param cantidad
	 * @param user
	 */
	public void anadirApuestaUsuario(Pronostico pronostico, double cantidad, Usuario user) {
		Usuario u = db.find(Usuario.class, user.getNombreUsuario());
		Pronostico p = db.find(Pronostico.class, pronostico.getPronosticoNumber());
		Apuestas a = new Apuestas(p, cantidad, u, p.getPreguntaPronostico(), p.getPreguntaPronostico().getEvent());
		db.getTransaction().begin();
		u.anadirApuesta(a);
		db.persist(a);
		db.getTransaction().commit();
	}

	/**
	 * Dado un usuario y un Mensaje, le añade el mensaje al usuario
	 * 
	 * @param mensaje
	 * @param user
	 */
	public void anadirMensajeUsuario(Mensaje mensaje, Usuario user) {
		Usuario u = db.find(Usuario.class, user.getNombreUsuario());
		db.getTransaction().begin();
		u.anadirMensaje(mensaje);
		db.persist(u);
		db.getTransaction().commit();
	}

	/**
	 * Valida el pronostico dado, da el dinero a las apuestas que han ganado
	 * 
	 * @param pronostico
	 */
	public void validarPronostico(Pronostico pronostico) {
		TypedQuery<Apuestas> query = db.createQuery("SELECT a FROM Apuestas a WHERE a.pronostico==?1", Apuestas.class);
		query.setParameter(1, pronostico);
		List<Apuestas> apu = query.getResultList();
		db.getTransaction().begin();
		Question q = db.find(Question.class, pronostico.getPreguntaPronostico());
		q.setEstaValidado(true);
		db.persist(q);
		db.getTransaction().commit();
		for (Apuestas a : apu) {
			Usuario u = a.getUsuario();
			double cant = pronostico.getCuota() * a.getCantidad();
			db.getTransaction().begin();
			u.setCartera(u.getCartera() + cant);
			db.persist(u);
			db.getTransaction().commit();
		}
	}

	/**
	 * Dada una apuesta la elimina
	 * 
	 * @param ap
	 */
	public void eliminarApuesta(Apuestas ap) {

		int cant = ap.getCantNumber();
		db.getTransaction().begin();
		Query query = db.createQuery("DELETE FROM Apuestas p WHERE p.cantNumber=" + cant);
		int deletedBets = query.executeUpdate();
		db.getTransaction().commit();

	}

	/**
	 * Dado un vector de apuestas, se las asigna al usuario
	 * 
	 * @param aps
	 * @param usu
	 */
	public void actualizarApuestas(Vector<Apuestas> aps, Usuario usu) {
		Usuario ub = db.find(Usuario.class, usu.getNombreUsuario());
		db.getTransaction().begin();
		ub.setApuestas(aps);
		db.persist(ub);
		db.getTransaction().commit();
	}

	/**
	 * Dado un usuario y una string, le actualiza la contraeña a ese string
	 * 
	 * @param u
	 * @param contraseña
	 */
	public void cambiarContrasena(Usuario u, String contrasena) {
		Usuario usu = db.find(Usuario.class, u.getNombreUsuario());
		db.getTransaction().begin();
		usu.setContrasena(contrasena);
		db.persist(usu);
		db.getTransaction().commit();
	}

	/**
	 * Dada la pregunta la elimina
	 * 
	 * @param q
	 * @return 1 si la ha eliminado, 0 si tenia apuestas y no se ha podido eliminar
	 */
	public int eliminarPregunta(Question q) {
		Question que = db.find(Question.class, q.getQuestionNumber());
		TypedQuery<Apuestas> query = db.createQuery("SELECT DISTINCT ap FROM Apuestas ap WHERE ap.preguntaAsociada==?1",
				Apuestas.class);
		query.setParameter(1, que);
		List<Apuestas> apu = query.getResultList();
		for (Apuestas a : apu) {
			return 0;
		}
		db.getTransaction().begin();
		Question quee = db.find(Question.class, q.getQuestionNumber());
		Event ev = db.find(Event.class, quee.getEvent());
		Vector<Pronostico> pro = quee.getPronostico();
		for (Pronostico p : pro) {
			// Pronostico po = db.find(Pronostico.class,p.getPronosticoNumber());
			db.remove(p);
		}
		ev.removeQuestion(quee);
		db.remove(quee);
		db.getTransaction().commit();
		return 1;
	}

	/**
	 * Devuelve un vector con las distintas categorias existentes
	 * 
	 * @return
	 */
	public Vector<Categoria> obtainCategories() {
		Vector<Categoria> res = new Vector<Categoria>();

		TypedQuery<Categoria> query = db.createQuery("SELECT DISTINCT ca FROM Categoria ca", Categoria.class);
		List<Categoria> apu = query.getResultList();
		for (Categoria a : apu) {
			res.add(a);
		}
		return res;
	}

	/**
	 * Devuelve una lista con los distintos mensajes existentes
	 * 
	 * @return
	 */
	public List<Mensaje> getMensajes() {

		TypedQuery<Mensaje> query = db.createQuery("SELECT men FROM Mensaje men", Mensaje.class);
		List<Mensaje> mensajes = query.getResultList();
		return mensajes;
	}

	/**
	 * Dado una pregunta, elimina todas las apuestas que haya sobre ella
	 * 
	 * @param q
	 */
	public void eliminarApuestaPregunta(Question q) {
		Question que = db.find(Question.class, q.getQuestionNumber());
		TypedQuery<Apuestas> query = db.createQuery("SELECT a FROM Apuestas a WHERE a.preguntaAsociada==?1",
				Apuestas.class);
		query.setParameter(1, que);
		List<Apuestas> as = query.getResultList();
		db.getTransaction().begin();
		for (Apuestas a : as) {
			Usuario u = a.getUsuario();
			u.eliminarApuesta(a);
			db.remove(a);
		}
		db.getTransaction().commit();
	}

	/**
	 * Dado un pronostico lo elimina
	 * 
	 * @param q
	 * @return 1 si lo ha eliminado, 0 si tenia apuestas y no se ha podido eliminar
	 */
	public int eliminarPronostico(Pronostico p) {
		Pronostico pro = db.find(Pronostico.class, p.getPronosticoNumber());
		TypedQuery<Apuestas> query = db.createQuery("SELECT DISTINCT ap FROM Apuestas ap WHERE ap.pronostico==?1",
				Apuestas.class);
		query.setParameter(1, pro);
		List<Apuestas> apu = query.getResultList();
		for (Apuestas a : apu) {
			return 0;
		}
		db.getTransaction().begin();
		Question quee = db.find(Question.class, pro.getPreguntaPronostico());
		quee.removePro(pro);
		Pronostico proo = db.find(Pronostico.class, p.getPronosticoNumber());
		db.remove(proo);
		db.getTransaction().commit();
		return 1;
	}

	/**
	 * Dado un evento lo elimina
	 * 
	 * @param q
	 * @return 1 si lo ha eliminado
	 */
	public int eliminarEvento(Event ev) {
		db.getTransaction().begin();
		Event e = db.find(Event.class, ev.getEventNumber());
		Categoria ca1 = db.find(Categoria.class, e.getCat().getCatNumber());
		ca1.removeEvent(e);
		db.remove(e);
		db.getTransaction().commit();
		return 1;
	}

	/**
	 * Comprueba si un evento tiene apuestas
	 * 
	 * @param ev
	 * @return TRUE si tiene apuestas, FALSE si no
	 */
	public boolean eventoTieneApuestas(Event ev) {
		Event e = db.find(Event.class, ev.getEventNumber());
		TypedQuery<Apuestas> query = db.createQuery("SELECT DISTINCT ap FROM Apuestas ap WHERE ap.eventoAsociado==?1",
				Apuestas.class);
		query.setParameter(1, e);
		List<Apuestas> apu = query.getResultList();
		for (Apuestas a : apu) {
			return true;
		}
		return false;
	}

	/**
	 * Dado un usuario y un string añade, crea el mensaje y lo almacena
	 * 
	 * @param user
	 * @param s
	 */
	public void addMensaje(Usuario user, String s) {
		List<Mensaje> mensajes = getMensajes();
		int i = mensajes.size();
		Mensaje m = new Mensaje(i, user, s, new Date());
		db.getTransaction().begin();
		db.persist(m);
		db.getTransaction().commit();
	}

	/**
	 * Dada una descripcion, obtiene la categoria cuya descripcion coincida con el
	 * string dado
	 * 
	 * @param description
	 * @return
	 */
	public Categoria obtenerCategoriaPorDescripcion(String description) {
		TypedQuery<Categoria> query = db
				.createQuery("SELECT p FROM Categoria p WHERE p.description='" + description + "'", Categoria.class);
		List<Categoria> categorias = query.getResultList();
		return categorias.get(0);
	}

	/**
	 * Devuelve las fechas en los que hay eventos de una categoria en especial
	 * 
	 * @param date
	 * @param cat
	 * @return
	 */
	public Vector<Date> getEventsCategoryMonth(Date date, Categoria cat) {
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Event> query = db.createQuery("SELECT p FROM Event p", Event.class);

		List<Event> eventos = query.getResultList();

		TypedQuery<Date> query1 = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query1.setParameter(1, firstDayMonthDate);
		query1.setParameter(2, lastDayMonthDate);
		List<Date> fechas = query1.getResultList();

		// miramos entre todos los eventos, que coincidan con los eventos con las fechas
		// buenas
		Vector<Event> eventosFechas = new Vector<Event>();
		for (Event e : eventos) {
			for (Date fecha : fechas) {
				if (e.getEventDate().compareTo(fecha) == 0) {
					eventosFechas.add(e);
				}
			}
		}

		// miramos los eventos con las fechas buenas que coincidan con la
		// categoría
		Vector<Date> dates = new Vector<Date>();
		for (Event e : eventosFechas) {
			if (e.getCat().getDescription().equals(cat.getDescription())) {
				dates.add(e.getEventDate());
			}
		}

		for (Date d : dates) {
			res.add(d);
		}
		return res;
	}

}
