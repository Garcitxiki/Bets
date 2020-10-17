package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dataAccess.DataAccess;
import domain.Categoria;
import domain.Event;

class CajaBlancaTest {
	private DataAccess DAO = new DataAccess(false);
	private TestFacadeImplementation testBL = new TestFacadeImplementation();

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Event eve;
	private Categoria ca;
	private Date eventDate;
	private String description;

	@Test
	@DisplayName("El evento existe")
	void test1() {
		try {
			ca = DAO.getCategoria(2);
			eventDate = sdf.parse("17/11/2020");
			description = "Raptors-Cavaliers";
			eve = DAO.createEvent(description, eventDate, ca);
			assertNull(eve);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	@DisplayName("Mismo nombre, evento distinto")
	void test2() throws ParseException {
		// “Raptors-Cavaliers , 2020/10/17,Baloncesto”
		ca = DAO.getCategoria(2);
		eventDate = sdf.parse("18/11/2020");
		description = "Raptors-Cavaliers";
		try {
			eve = DAO.createEvent(description, eventDate, ca);
			assertEquals(description, eve.getDescription());
			assertEquals(eventDate, eve.getEventDate());
			assertEquals(ca, eve.getCat());
		} finally {
			testBL.removeEvent(eve);
		}
	}

	@Test
	@DisplayName("Evento inexistente")
	void test3() throws ParseException {
		// “Raptors-Cavaliers , 2020/10/17,Baloncesto”
		Categoria ca = DAO.getCategoria(2);
		Date eventDate = sdf.parse("12/11/2020");
		String description = "Raptors-Madrid";
		try {
			eve = DAO.createEvent(description, eventDate, ca);
			assertEquals(description, eve.getDescription());
			assertEquals(eventDate, eve.getEventDate());
			assertEquals(ca, eve.getCat());
		} finally {
			testBL.removeEvent(eve);
		}
	}

}
