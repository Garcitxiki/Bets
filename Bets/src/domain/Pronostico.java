package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pronostico {
@Id @GeneratedValue
	private int pronosticoNumber;
	private String pronostico;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Question preguntaPronostico;
	private double cuota;
	
	public Pronostico(String pronostico, double cuota, Question preguntaPronostico) {
		this.pronostico = pronostico;
		this.cuota=cuota;
		this.preguntaPronostico = preguntaPronostico;
	}

	public int getPronosticoNumber() {
		return pronosticoNumber;
	}

	public void setPronosticoNumber(int pronosticoNumber) {
		this.pronosticoNumber = pronosticoNumber;
	}

	public String getPronostico() {
		return pronostico;
	}

	public void setPronostico(String pronostico) {
		this.pronostico = pronostico;
	}

	public Question getPreguntaPronostico() {
		return preguntaPronostico;
	}

	public void setPreguntaPronostico(Question preguntaPronostico) {
		this.preguntaPronostico = preguntaPronostico;
	}

	public double getCuota() {
		return cuota;
	}

	public void setCuota(double cuota) {
		this.cuota = cuota;
	}

	public String imprimir() {
		String s=this.pronostico+": "+this.cuota;
		return s;
	}
	
	@Override
	public String toString() {
		return this.pronostico+": "+this.cuota;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Pronostico) {
			if (this.pronostico.equals(((Pronostico) o).getPronostico()) &&
				this.cuota == ((Pronostico) o).getCuota() &&
				this.preguntaPronostico == ((Pronostico) o).getPreguntaPronostico()) {
					return true;
			} else {
					return false;
			}
		}else {
			return false;
		}
	}
	


}
