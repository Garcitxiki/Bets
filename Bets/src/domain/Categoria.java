package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Categoria{
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer catNumber;
	private String description; 
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Vector<Event> events=new Vector<Event>();
	
	
	
	
	
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categoria(String description, Vector<Event> events) {
		super();
		this.description = description;
		this.events = events;
	}
	public Categoria(String description) {
		super();
		this.description = description;
	}
	public Integer getCatNumber() {
		return catNumber;
	}
	public void setCatNumber(Integer catNumber) {
		this.catNumber = catNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Vector<Event> getEvents() {
		return events;
	}
	public void setEvents(Vector<Event> events) {
		this.events = events;
	}
	
	public void addEvent(Integer eventNumber, String description,Date eventDate) {
		Event e= new Event(eventNumber, description, eventDate);
		events.add(e);
	}
	
	public void addEvent(Event e) {
		events.add(e);
	}
	public void removeEvent(Event e) {
		events.remove(e);
	}
	@Override
	public String toString() {
		return this.description;
	}
	
	
	
	
	
	
}
