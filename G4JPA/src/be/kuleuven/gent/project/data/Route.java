package be.kuleuven.gent.project.data;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name="route")
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRoute", nullable=false,length=16)
	private int id;
	
	@Column(name="beginpunt", nullable=false)
	private DecimalFormat beginpunt;
	
	@Column(name="eindpunt", nullable=false)
	private DecimalFormat eindpunt;
	
	@Column(name="eindtijd", nullable=false)
	private Timestamp eindtijd;
	
	@Column(name="vertrektijd", nullable=false)
	private Timestamp vertrektijd;
	
	@JoinColumn(name="bestuurderId", nullable=false)
	private Profiel bestuurder;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getEindtijd() {
		return eindtijd;
	}

	public void setEindtijd(Timestamp eindtijd) {
		this.eindtijd = eindtijd;
	}
	public String eindtijdToString() {
		return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(eindtijd);
	}

	public Timestamp getVertrektijd() {
		return vertrektijd;
	}

	public void setVertrektijd(Timestamp vertrektijd) {
		this.vertrektijd = vertrektijd;
	}
	public String vertrektijdToString() {
		return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(vertrektijd);
	}

	public Profiel getBestuurder() {
		return bestuurder;
	}

	public void setBestuurder(Profiel bestuurder) {
		this.bestuurder = bestuurder;
	}
	
	public DecimalFormat getBeginpunt() {
		return beginpunt;
	}

	public void setBeginpunt(DecimalFormat beginpunt) {
		this.beginpunt = beginpunt;
	}

	public DecimalFormat getEindpunt() {
		return eindpunt;
	}

	public void setEindpunt(DecimalFormat eindpunt) {
		this.eindpunt = eindpunt;
	}

	public Route() {}
	
	public Route(Timestamp eindtijd, Timestamp vertrektijd, Profiel bestuurder) {
		this.eindtijd=eindtijd;
		this.vertrektijd=vertrektijd;
		this.bestuurder=bestuurder;
	}
}
