package test;

/**
 * Auxiliary FacadeImplementation class for testing DataAccess
 */

import java.util.Date;

import configuration.ConfigXML;
import domain.Event;
import domain.Pronostico;
import domain.Question;

public class TestFacadeImplementation {
	TestDataAccess dbManagerTest;

	public TestFacadeImplementation() {

		System.out.println("Creating TestFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();
		dbManagerTest = new TestDataAccess();
		dbManagerTest.close();
	}

	public boolean removeEvent(Event ev) {
		dbManagerTest.open();
		boolean b = dbManagerTest.removeEvent(ev);
		dbManagerTest.close();
		return b;

	}

	public Event addEvent(String desc, Date d) {
		dbManagerTest.open();
		Event o = dbManagerTest.addEvent(desc, d);
		dbManagerTest.close();
		return o;

	}
	/*
	public Pronostico addPronostico(String pronostico, double cuota, Question q) {
		dbManagerTest.open();
		Pronostico p = dbManagerTest.addPronostico(pronostico, cuota, q);
		dbManagerTest.close();
		return p;
	}
	
	public boolean removePronostico(Pronostico p) {
		dbManagerTest.open();
		boolean b = dbManagerTest.removePronostico(p);
		dbManagerTest.close();
		return b;
	}
	
	public boolean removeQuestion(Question q) {
		dbManagerTest.open();
		boolean b = dbManagerTest.removeQuestion(q);
		dbManagerTest.close();
		return b;
	}
	
	public Question addQuestion(String question, int cuota, Event ev) {
		dbManagerTest.open();
		Question q = dbManagerTest.addQuestion(question, cuota, ev);
		dbManagerTest.close();
		return q;
	}*/

}