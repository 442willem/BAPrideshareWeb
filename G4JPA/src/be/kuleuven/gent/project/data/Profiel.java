package be.kuleuven.gent.project.data;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Table(name="profiel")
@SecondaryTable(name = "profiel_Group", pkJoinColumns=@PrimaryKeyJoinColumn(name="idProfiel", referencedColumnName="login"))
public class Profiel implements Serializable {
	//alle parameters die in de DB staan , nog iets ...
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idProfiel", nullable=false,length=16)
	private int id;
	
	@Column(name="driverScore", nullable=false)
	private int driverscore;

	@Column(name="passagierScore", nullable=false)
	private int passagierscore;
	
	@Column(name="voornaam", nullable=false)
	private String voornaam;
	
	@Column(name="achternaam", nullable=false)
	private String achternaam;
	
	@Column(name="login", nullable=false)
	private String login;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="paypalemail", nullable=false)
	private String paypalemail;

	@Column(table="profiel_Group", name="idGroup", nullable =false)
	private String group;
	
	public int getId() {
		return id;
	}

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
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
	
	public String getPaypalemail() {
		return paypalemail;
	}

	public void setPaypalemail(String paypalemail) {
		this.paypalemail = paypalemail;
	}

	public Profiel() {}
	
	public Profiel(String group,String login, String password,String achternaam, String voornaam) {
		this.achternaam=achternaam;
		this.group=group;
		
		this.login=login;
		this.password=password;
		this.voornaam=voornaam;
	}
}
