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
@Table(name="notificatie")
public class Notificatie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idnotificatie", nullable=false,length=16)
	private int id;

	@Column(name="type", nullable=false)
	private String type;

	@JoinColumn(name="profielId", nullable=false)
	private Profiel profiel;

	@JoinColumn(name="ritId", nullable=false)
	private Tussenstop rit;

	@JoinColumn(name="routeId", nullable=false)
	private Route route;

	@Column(name="tijdstip", nullable=false)
	private Timestamp tijdstip;
	
	@Column(name="gelezen", nullable=false)
	private Boolean gelezen;

	@Transient
	private Date tijdstipDate;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Profiel getProfiel() {
		return profiel;
	}

	public void setProfiel(Profiel profiel) {
		this.profiel = profiel;
	}

	public Tussenstop getRit() {
		return rit;
	}

	public void setRit(Tussenstop rit) {
		this.rit = rit;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Timestamp getTijdstip() {
		return tijdstip;
	}

	public void setTijdstip(Timestamp timestamp) {
		this.tijdstip = timestamp;
	}
	public void setTijdstipDat(Date tijdstipDate) {
		this.tijdstipDate = tijdstipDate;
		this.tijdstip= new Timestamp(tijdstipDate.getTime());
	}

	public String tijdstipToString() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(tijdstip);
	}
	public Boolean getGelezen() {
		return gelezen;
	}

	public void setGelezen(Boolean gelezen) {
		this.gelezen = gelezen;
	}
	
	public boolean heeftRoute() {
		if(route!=null)return true;
		else return false;
	}
	public boolean heeftRit() {
		if(rit!=null)return true;
		else return false;
	}
	public boolean isRouteHerrinering() {
		if(type.equals("routeHerinnering"))return true;
		else return false;
	}
	public boolean isRitHerrinering() {
		if(type.equals("ritHerinnering"))return true;
		else return false;
	}
	public boolean isBericht() {
		if(type.equals("bericht"))return true;
		else return false;
	}
	public boolean isReview() {
		if(type.equals("review"))return true;
		else return false;
	}
	public boolean isRitRequest() {
		if(type.equals("ritRequest"))return true;
		else return false;
	}
	public boolean isRitChange() {
		if(type.equals("ritChange"))return true;
		else return false;
	}
	public boolean isRitAccepted() {
		if(type.equals("ritAccepted"))return true;
		else return false;
	}
	
	public String getMessage() {
		String message;
		switch(type) {
		case "betaling":
			message="You have been payed for a ride";
			break;
		case "review":
			message="Someone has left a review on your profile";
			break;
		case "routeHerinnering":
			message="You have an upcoming ride as driver";
			break;
		case "ritHerinnering":
			message="You have an upcoming ride as passenger";
			break;
		case "ritAccepted":
			message="A ride you have booked has been accepted";
			break;
		case "ritChange":
			message="A ride you have requested has been changed";
			break;
		case "ritRequest":
			message="Someone has requested to join your ride";
			break;
		case "bericht":
			message="You have a new message";
			break;
		default:
			message=null;
		}
		return message;
	}

	public Notificatie(String type) {
		this.type=type;
		Date date = new Date();
		tijdstip=new Timestamp(date.getTime());
	}
	public Notificatie() {
		
	}

}
