package be.kuleuven.gent.project.data;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="tussenstop")
public class Tussenstop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idTussenstop", nullable=false,length=16)
	private int id;
	
	@Column(name="plaats_lat", nullable=false)
	private DecimalFormat plaats_latitude;
	
	@Column(name="plaats_long", nullable=false)
	private DecimalFormat plaats_longtitude;
	
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

	public DecimalFormat getPlaats_latitude() {
		return plaats_latitude;
	}

	public void setPlaats_latitude(DecimalFormat plaats_latitude) {
		this.plaats_latitude = plaats_latitude;
	}

	public DecimalFormat getPlaats_longtitude() {
		return plaats_longtitude;
	}

	public void setPlaats_longtitude(DecimalFormat plaats_longtitude) {
		this.plaats_longtitude = plaats_longtitude;
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
	
}
