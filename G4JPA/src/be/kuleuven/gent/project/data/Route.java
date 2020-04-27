package be.kuleuven.gent.project.data;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.sql.Timestamp;

@Entity
@Table(name="route")
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRoute", nullable=false,length=16)
	private int id;
	
	@Column(name="beginpunt", nullable=false)
	private String beginpunt;
	
	@Column(name="eindpunt", nullable=false)
	private String eindpunt;
	
	@Column(name="maximum_personen", nullable=false)
	private int maxPersonen;

	@Column(name="eindtijd", nullable=false)
	private Timestamp eindtijd;
	
	@Column(name="vertrektijd", nullable=false)
	private Timestamp vertrektijd;
	
	@JoinColumn(name="bestuurderId", nullable=false)
	private Profiel bestuurder;
	
	@Transient
	private Date eindtijdDate;
	@Transient
	private Date vertrektijdDate;
	@Transient 
	private String tussenstops;

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
	public void setEindtijd(Date eindtijd) {
		this.eindtijd = new Timestamp(eindtijd.getTime());
	}
	public String eindtijdToString() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(eindtijd);
	}

	public Timestamp getVertrektijd() {
		return vertrektijd;
	}
	
	public void setVertrektijd(Timestamp vertrektijd) {
		this.vertrektijd = vertrektijd;
	}
	public void setVertrektijd(Date vertrektijd) {
		this.vertrektijd = new Timestamp(vertrektijd.getTime());
	}
	public String vertrektijdToString() {	
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(vertrektijd);
	}

	public Profiel getBestuurder() {
		return bestuurder;
	}

	public void setBestuurder(Profiel bestuurder) {
		this.bestuurder = bestuurder;
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
	
	public int getMaxPersonen() {
		return maxPersonen;
	}

	public void setMaxPersonen(int maxPersonen) {
		this.maxPersonen = maxPersonen;
	}

	public Route() {
	}
	
	public Route(Timestamp eindtijd, Timestamp vertrektijd, Profiel bestuurder) {
		this.eindtijd=eindtijd;
		this.vertrektijd=vertrektijd;
		this.bestuurder=bestuurder;
	}

	public Date getEindtijdDate() {
		return eindtijdDate;
	}

	public void setEindtijdDate(Date eindtijdDate) {
		this.eindtijdDate = eindtijdDate;
		this.eindtijd= new Timestamp(eindtijdDate.getTime());
	}

	public Date getVertrektijdDate() {
		return vertrektijdDate;
	}

	public void setVertrektijdDate(Date vertrektijdDate) {
		this.vertrektijdDate = vertrektijdDate;
		this.vertrektijd= new Timestamp(vertrektijdDate.getTime());
	}
	
	public String getTussenstops() {
		return tussenstops;
	}

	public void setTussenstops(String tussenstops) {
		this.tussenstops = tussenstops;
	}
}
