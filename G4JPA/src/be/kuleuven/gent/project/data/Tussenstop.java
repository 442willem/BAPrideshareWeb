package be.kuleuven.gent.project.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="tir")
public class Tussenstop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idTir", nullable=false,length=16)
	private int id;
	
	@Column(name="beginpunt", nullable = false)
	private String beginpunt;
	
	@Column(name="eindpunt", nullable = false)
	private String eindpunt;
	
	@Column(name="prijs", nullable = false)
	private int prijs;
	
	@Column(name="vertrektijd", nullable = false)
	private Timestamp vertrektijd;
	
	@Column(name="aantal_personen", nullable=false)
	private int aantalPersonen;
	
	@Column(name="betaald", nullable = false)
	private boolean betaald;
	
	@Column(name="goedgekeurd", nullable = false)
	private boolean goedgekeurd;
	
	@JoinColumn(name="routeID", nullable =false)
	private Route route;
	
	@JoinColumn(name="passagierID", nullable=false)
	private Profiel passagier;
	
	@Transient
	private Date vertrektijdDate;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getBeginpunt() {
		return beginpunt;
	}

	public void setBeginpunt(String beginpunt) {
		this.beginpunt = beginpunt;
	}
	

	public String getEindpunt() {
		return eindpunt;
	}

	public void setEindpunt(String eindpunt) {
		this.eindpunt = eindpunt;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
	

	public Profiel getPassagier() {
		return passagier;
	}

	public void setPassagier(Profiel passagier) {
		this.passagier = passagier;
	}
	

	public int getPrijs() {
		return prijs;
	}

	public void setPrijs(int prijs) {
		this.prijs = prijs;
	}
	

	public Timestamp getVertrektijd() {
		return vertrektijd;
	}

	public void setVertrektijd(Timestamp vertrektijd) {
		System.out.println("SET VERTREKTIJD (timestamp)");
		this.vertrektijd = vertrektijd;
	}
	public void setVertrektijd(Date vertrektijd) {
		System.out.println("SET VERTREKTIJD (date)");
		this.vertrektijd = new Timestamp(vertrektijd.getTime());
	}

	public Date getVertrektijdDate() {
		return vertrektijdDate;
	}

	public void setVertrektijdDate(Date vertrektijdDate) {
		System.out.println("SET VERTREKTIJDDATE");
		this.vertrektijdDate = vertrektijdDate;
		this.vertrektijd= new Timestamp(vertrektijdDate.getTime());
	}
	
	public String vertrektijdToString() {	
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(vertrektijd);
	}
	


	public int getAantalPersonen() {
		return aantalPersonen;
	}

	public void setAantalPersonen(int aantalPersonen) {
		this.aantalPersonen = aantalPersonen;
	}
	

	public boolean isBetaald() {
		return betaald;
	}

	public void setBetaald(boolean betaald) {
		this.betaald = betaald;
	}
	

	public boolean isGoedgekeurd() {
		return goedgekeurd;
	}

	public void setGoedgekeurd(boolean goedgekeurd) {
		this.goedgekeurd = goedgekeurd;
	}

	public Tussenstop() {}
	
}
