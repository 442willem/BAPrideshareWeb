package be.kuleuven.gent.project.data;
import java.io.Serializable;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.sun.jmx.snmp.Timestamp;

@Entity
@Table(name="route")
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRoute", nullable=false,length=16)
	private int id;
	
	@Column(name="beginpunt_lat", nullable=false)
	private DecimalFormat beginpuntLat;
	
	@Column(name="beginpunt_long", nullable=false)
	private DecimalFormat beginpuntLong;
	
	@Column(name="eindpunt_lat", nullable=false)
	private DecimalFormat eindpuntLat;
	
	@Column(name="eindpunt_long", nullable=false)
	private DecimalFormat eindpuntLong;

	@Column(name="eindtijd", nullable=false)
	private Timestamp eindtijd;
	
	@Column(name="vertrektijd", nullable=false)
	private Timestamp vertrektijd;
	
	@JoinColumn(name="bestuurder", nullable=false)
	private Profiel bestuurder;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DecimalFormat getBeginpuntLat() {
		return beginpuntLat;
	}

	public void setBeginpuntLat(DecimalFormat beginpuntLat) {
		this.beginpuntLat = beginpuntLat;
	}

	public DecimalFormat getBeginpuntLong() {
		return beginpuntLong;
	}

	public void setBeginpuntLong(DecimalFormat beginpuntLong) {
		this.beginpuntLong = beginpuntLong;
	}

	public DecimalFormat getEindpuntLat() {
		return eindpuntLat;
	}

	public void setEindpuntLat(DecimalFormat eindpuntLat) {
		this.eindpuntLat = eindpuntLat;
	}

	public DecimalFormat getEindpuntLong() {
		return eindpuntLong;
	}

	public void setEindpuntLong(DecimalFormat eindpuntLong) {
		this.eindpuntLong = eindpuntLong;
	}

	public Timestamp getEindtijd() {
		return eindtijd;
	}

	public void setEindtijd(Timestamp eindtijd) {
		this.eindtijd = eindtijd;
	}

	public Timestamp getVertrektijd() {
		return vertrektijd;
	}

	public void setVertrektijd(Timestamp vertrektijd) {
		this.vertrektijd = vertrektijd;
	}

	public Profiel getBestuurder() {
		return bestuurder;
	}

	public void setBestuurder(Profiel bestuurder) {
		this.bestuurder = bestuurder;
	}
	public Route() {}
	
	public Route(Timestamp eindtijd, Timestamp vertrektijd, Profiel bestuurder) {
		this.eindtijd=eindtijd;
		this.vertrektijd=vertrektijd;
		this.bestuurder=bestuurder;
	}
}
