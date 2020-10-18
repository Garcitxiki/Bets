package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

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
import domain.Pronostico;
import domain.Question;

public class ObterPronosticoFacadeTest {
	
	@Mock
	DataAccess dataAccess = Mockito.mock(DataAccess.class);

	BLFacadeImplementation sut = new BLFacadeImplementation(dataAccess);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(dataAccess);
	}
	
	@Test
	@DisplayName("Test2: Devuelve lista de pronosticos")
	void test2 () {
		//Crear los valores para que devuelva el mockito
		try {
			//Preparamos lo que queremos que devuelva el mock
			Categoria ca = new Categoria("Tenis");
			Date eventDate;
			eventDate = sdf.parse("19/11/2020");
			String description = "Nadal-Djokovic";
			Event eve = new Event(description, eventDate, ca);
			Question q = new Question("¿Quien ganara el partido?", 1, eve);
			Pronostico p = new Pronostico("1", 1.2, q);
			Vector<Pronostico> pronosticos = new Vector<>();
			pronosticos.add(p);
			
			//Configuramos el dataaccess para cuando se le llame a obtener pronosticos que queremos que devuelva
			Mockito.doReturn(pronosticos).when(dataAccess).obtenerPronostico(Mockito.anyInt());
			
			Vector<Pronostico> expected = new Vector<>();
			expected.add(p);
			
			Vector<Pronostico> obtained = sut.obtenerPronostico(72);
			
			assertEquals(expected, obtained);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Test
	@DisplayName("Test1: No hay preguntas")
	void test1() {
		Mockito.doReturn(null).when(dataAccess).obtenerPronostico(Mockito.anyInt());
		
		Vector<Pronostico> expected = null;
		
		Vector<Pronostico> obtained = sut.obtenerPronostico(12);
		
		assertEquals(expected, obtained);
	}
		
}
