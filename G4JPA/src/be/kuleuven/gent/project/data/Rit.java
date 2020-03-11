package be.kuleuven.gent.project.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Table(name="Rit")
public class Rit implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idrit", nullable=false,length=16)
	private int id;
	
	@Column(name="prijs", nullable=false)
	private int prijs;
	
	@Column(name="eindpunt", nullable=false)
	private String eindpunt;
	
	@Column(name="begintpunt", nullable=false)
	private String begintpunt;
	

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
	
	public String getEindpunt() {
		return eindpunt;
	}

	public void setEindpunt(String eindpunt) {
		this.eindpunt = eindpunt;
	}

	public String getBegintpunt() {
		return begintpunt;
	}

	public void setBegintpunt(String begintpunt) {
		this.begintpunt = begintpunt;
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
	public String vertrektijdToString() {
		return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(vertrektijd);
	}

	public void setVertrektijd(Timestamp vertrektijd) {
		this.vertrektijd = vertrektijd;
	}

	public Timestamp getEindtijd() {
		return eindtijd;
	}
	public String eindtijdToString() {
		return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(eindtijd);
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
	
	public Rit(Profiel passagier, Route route) {
		this.passagier=passagier;
		this.route=route;
	}
	
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
