package be.kuleuven.gent.project.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.sun.jmx.snmp.Timestamp;

import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@Table(name="profiel")
public class Profiel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idProfiel", nullable=false,length=16)
	private int id;
	
	@Column(name="driverscore", nullable=false)
	private int driverscore;

	@Column(name="passagierscore", nullable=false)
	private int passagierscore;
	
	@Column(name="voornaam", nullable=false)
	private String voornaam;
	
	@Column(name="achternaam", nullable=false)
	private String achternaam;
	
	@Column(name="stad", nullable=false)
	private String stad;
	
	@Column(name="straat", nullable=false)
	private String straat;
	
	@Column(name="huisnummer", nullable=false)
	private String huisnummer;
	
	@Column(name="login", nullable=false)
	private String login;
	
	@Column(name="password", nullable=false)
	private String password;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDriverscore() {
		return driverscore;
	}

	public void setDriverscore(int driverscore) {
		this.driverscore = driverscore;
	}

	public int getPassagierscore() {
		return passagierscore;
	}

	public void setPassagierscore(int passagierscore) {
		this.passagierscore = passagierscore;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getStad() {
		return stad;
	}

	public void setStad(String stad) {
		this.stad = stad;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public String getHuisnummer() {
		return huisnummer;
	}

	public void setHuisnummer(String huisnummer) {
		this.huisnummer = huisnummer;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profiel() {}
	
	public Profiel(String stad,String straat,String huisnummer,String login, String password,String achternaam, String voornaam) {
		this.achternaam=achternaam;
		this.stad=stad;
		this.straat=straat;
		this.huisnummer=huisnummer;
		this.login=login;
		this.password=password;
		this.voornaam=voornaam;
	}
}
