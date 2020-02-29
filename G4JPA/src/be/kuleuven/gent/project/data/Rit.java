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
	@Column(name="idrit", nullable=false,length=16)
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
	
	@JoinColumn(name="passagierId", nullable=false)
	private Profiel passagier;
	
	@JoinColumn(name="routeId", nullable=false)
	private Route route;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrijs() {
		return prijs;
	}

	public void setPrijs(int prijs) {
		this.prijs = prijs;
	}

	public boolean isBetaald() {
		return betaald;
	}

	public void setBetaald(boolean betaald) {
		this.betaald = betaald;
	}

	public int getAantalPersonen() {
		return aantalPersonen;
	}

	public void setAantalPersonen(int aantalPersonen) {
		this.aantalPersonen = aantalPersonen;
	}

	public Timestamp getVertrektijd() {
		return vertrektijd;
	}

	public void setVertrektijd(Timestamp vertrektijd) {
		this.vertrektijd = vertrektijd;
	}

	public Timestamp getEindtijd() {
		return eindtijd;
	}

	public void setEindtijd(Timestamp eindtijd) {
		this.eindtijd = eindtijd;
	}

	public Profiel getPassagier() {
		return passagier;
	}

	public void setPassagier(Profiel passagier) {
		this.passagier = passagier;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

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
