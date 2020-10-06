package domain;

import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String contrasena;
	private String DNI;
	@Id
	private String nombreUsuario;
	private double cartera;
	private boolean esAdmin;
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Apuestas> apuestas = new Vector<Apuestas>();
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Vector<Mensaje> mensajes = new Vector<Mensaje>();

	public Usuario(String nombre, String apellido, Date fechaNacimiento, String contrasena, String dNI,
			String nombreUsuario) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.contrasena = contrasena;
		this.DNI = dNI;
		this.nombreUsuario = nombreUsuario;
		this.cartera = 0;
		this.esAdmin = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public double getCartera() {
		return cartera;
	}

	public void setCartera(double cartera) {
		this.cartera = cartera;
	}

	public boolean isEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	
	public Vector<Apuestas> getApuestas() {
		return apuestas;
	}

	public void setApuestas(Vector<Apuestas> apuestas) {
		this.apuestas = apuestas;
	}

	public Vector<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(Vector<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public void anadirApuesta(Apuestas apuesta) {
		this.apuestas.add(apuesta);
	}
	
	public void eliminarApuesta(Apuestas apuesta) {
		this.apuestas.remove(apuesta);
	}

	public void anadirMensaje(Mensaje mensaje) {
		this.mensajes.add(mensaje);
	}

	public void eliminarApuesta(Mensaje mensaje) {
		this.apuestas.remove(mensaje);
	}
 

}
