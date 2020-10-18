package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;


import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Categoria;
import domain.Event;
import domain.Pronostico;
import domain.Question;

public class ObtenerPronosticoDBTest {
	private DataAccess DAO = new DataAccess(false);
	private TestFacadeImplementation testBL = new TestFacadeImplementation();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Event eve;
	private Categoria ca;
	private Date eventDate;
	private String description;
	private Question q;
	private Pronostico p;
	
	@Test
	@DisplayName("Test3: No esta la pregunta")
	void test3() {
		//No existe la pregunta con identificador 72
		assertNull(DAO.obtenerPronostico(72));
	}
	
	@Test
	@DisplayName("Test2: Devuelve pronostico")
	void test2() {
		try {
			
			//Creamos el evento
			ca = DAO.getCategoria(3);
			eventDate = sdf.parse("19/11/2020");
			description = "Nadal-Djokovic";
			eve = DAO.createEvent(description, eventDate, ca);
			
			//Asociamos la pregunta al evento recientemente creado
			q = DAO.createQuestion(eve, "¿Quien ganara el partido?", 1);
			
			//Creamos el pronostico en base a la pregunta realizada
			p = new Pronostico("1", 1.2, q);
			DAO.anadirPronostico("1", 1.2, q);
			
			//Generamos el valor esperado
			Vector<Pronostico> expected = new Vector<>();
			expected.add(p);
			
			//Obtenemos el valor
			Vector<Pronostico> obtained = DAO.obtenerPronostico(72);
			
			//Verificamos que el valor obtenido y el esperado son iguales.
			//Para ello ha sido necesario reescribir el metodo equals de la clase Pronostico
			assertEquals(expected, obtained);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//Borramos el evento recien creado de la BBDD
			testBL.removeEvent(eve);
		}	
	}
	
	@Test
	@DisplayName("Test1: No hay preguntas")
	void test1() {
		
		//Borramos la preguntas de la BBDD
		Question q;
		for (int i = 5; i >= 0; i--) {
			q = DAO.getQuestionByNumber(i+13);
			DAO.eliminarPregunta(q);
		}
		
		//Llamamos a obtener pronostico con un identificador de pregunta que no existe porque no hay
		//Verificamos que efectivamente devuelve null
		assertNull(DAO.obtenerPronostico(13));
	}
}
