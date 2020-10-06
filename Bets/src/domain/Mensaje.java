package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mensaje {
	@Id
	private int id;
	private Usuario deQuien;
	private String contenido;
	private Date data;
	
	public Mensaje(int id, Usuario deQuien, String contenido, Date data) {
		
		this.id = id;
		this.deQuien = deQuien;
		this.contenido = contenido;
		this.data=data;
	}

	public Mensaje(Usuario deQuien, String contenido, Date data) {
		this.deQuien = deQuien;
		this.contenido = contenido;
		this.data = data;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Usuario getDeQuien() {
		return deQuien;
	}


	public void setDeQuien(Usuario deQuien) {
		this.deQuien = deQuien;
	}


	public String getContenido() {
		return contenido;
	}


	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}
