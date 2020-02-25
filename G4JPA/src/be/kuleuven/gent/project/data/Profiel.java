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
@Table(name="Profiel")
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
	private int huisnummer;
	
	@Column(name="login", nullable=false)
	private String login;
	
	@Column(name="password", nullable=false)
	private String password;
	
	public Profiel() {}
	
	public Profiel(String stad,String straat,int huisnummer,String login, String password,String achternaam, String voornaam) {
		this.achternaam=achternaam;
		this.stad=stad;
		this.straat=straat;
		this.huisnummer=huisnummer;
		this.login=login;
		this.password=password;
		this.voornaam=voornaam;
	}
}
