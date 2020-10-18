package test;

/**
 * Auxiliary DataAccess class for testing 
 */

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.Event;
import domain.Pronostico;
import domain.Question;

public class TestDataAccess {
	protected EntityManager db;
	protected EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public TestDataAccess() {

		System.out.println("Creating TestDataAccess instance");

		open();

	}

	public void open() {

		System.out.println("Opening TestDataAccess instance ");

		String fileName = c.getDbFilename();

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

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e != null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else
			return false;
	}

	public Event addEvent(String desc, Date d) {
		System.out.println(">> DataAccessTest: addEvent");
		Event ev = null;
		db.getTransaction().begin();
		try {
			ev = new Event(desc, d);
			db.persist(ev);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ev;
	}
	
	/*
	public Question addQuestion(String question, int cuota, Event ev) {
		Question q1 = null;
		
		try {
			Event e = db.find(Event.class, ev.getEventNumber());
			db.getTransaction().begin();
			q1 = e.addQuestion("¿Quién ganará el partido?", 1);
			db.persist(q1);
			db.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return q1;
	}
	
	public boolean removeQuestion(Question q) {
		System.out.println(">> DataAccessTest: removeQuestion");
		Question quest = db.find(Question.class, q.getQuestionNumber());
		if (quest != null) {
			db.getTransaction().begin();
			db.remove(quest);
			db.getTransaction().commit();
			return true;
		} else
			return false;
		
	}
	
	public boolean removePronostico(Pronostico p) {
		System.out.println(">> DataAccessTest: removePronostico");
		Pronostico pro = db.find(Pronostico.class, p.getPronosticoNumber());
		if (pro != null) {
			db.getTransaction().begin();
			db.remove(pro);
			db.getTransaction().commit();
			return true;
		} else
			return false;
		
	}
	
	public Pronostico addPronostico(String pronostico, double cuota, Question q) {
		Pronostico p1 = null;
		
		try {
			Question quest = db.find(Question.class, q.getQuestionNumber());
			db.getTransaction().begin();
			p1 = quest.addPronostico("1", 1.6, q);
			db.persist(p1);
			db.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return p1;
	}*/
	
}