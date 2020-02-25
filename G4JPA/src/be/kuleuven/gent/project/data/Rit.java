package be.kuleuven.gent.project.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.sun.jmx.snmp.Timestamp;

@Entity
@Table(name="Rit")
public class Rit implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRit", nullable=false,length=16)
	private int id;
	
	@Column(name="prijs", nullable=false)
	private int prijs;
	
	@Column(name="betaald", nullable=false)
	private boolean betaald;
	
	@Column(name="aantal_personen", nullable=false)
	private int aantalPersonen;
	
	@Column(name="vertrektijd", nullable=false)
	private Timestamp vertrektijd;
	
	@Column(name="eindtijd", nullable=false)
	private Timestamp eindtijd;
	
	@JoinColumn(name="passagier", nullable=false)
	private Profiel passagier;
	
	@JoinColumn(name="route", nullable=false)
	private Route route;
	
	public Rit() {}
	
	public Rit(int aantalPersonen,int prijs, Timestamp vertrektijd, Timestamp eindtijd, Profiel passagier,Route route) {
		this.aantalPersonen=aantalPersonen;
		this.vertrektijd=vertrektijd;
		this.eindtijd=eindtijd;
		this.passagier=passagier;
		this.route=route;
		betaald=false;
		this.prijs=prijs;
	}
	
}
