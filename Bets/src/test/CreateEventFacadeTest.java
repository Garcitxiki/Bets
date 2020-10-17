package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Categoria;
import domain.Event;

class CreateEventFacadeTest {

	private BLFacadeImplementation sut;
	@Mock
	private DataAccess DAO;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Event eve;
	private Categoria ca;
	private Date eventDate;
	private String description;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		sut = new BLFacadeImplementation(DAO);
	}

	@Test
	@DisplayName("Evento inexistente")
	void test1Mockt() {
		try {
			ca = new Categoria("Baloncesto");
			eventDate = sdf.parse("12/11/2020");
			description = "Raptors-Cavaliers";
			eve = new Event(description, eventDate, ca);
			Mockito.doReturn(eve).when(DAO).createEvent(description, eventDate, ca);
			Event ev = sut.createEvent(description, eventDate, ca);
			assertEquals(eve, ev);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	@DisplayName("Evento existente, distinta fecha")
	void test2Mockt() {
		try {
			ca = new Categoria("Baloncesto");
			eventDate = sdf.parse("18/11/2020");
			description = "Raptors-Cavaliers";
			eve = new Event(description, eventDate, ca);
			Mockito.doReturn(eve).when(DAO).createEvent(description, eventDate, ca);
			Event ev = sut.createEvent(description, eventDate, ca);
			assertEquals(eve, ev);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	@DisplayName("El evento existe")
	void test3Junit() {
		try {
			ca = sut.getCategoria(2);
			eventDate = sdf.parse("17/11/2020");
			description = "Raptors-Cavaliers";
			eve = sut.createEvent(description, eventDate, ca);
			assertNull(eve);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
