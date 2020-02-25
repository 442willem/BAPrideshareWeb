package be.kuleuven.gent.project.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="chat")
public class Chat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idChat", nullable=false,length=16)
	private int id;
	
	@JoinColumn(name="eerste_persoonId", nullable=false)
	private Profiel eerste_persoon;
	
	@JoinColumn(name="tweede_persoonId", nullable=false)
	private Profiel tweede_persoon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Profiel getEerste_persoon() {
		return eerste_persoon;
	}

	public void setEerste_persoon(Profiel eerste_persoon) {
		this.eerste_persoon = eerste_persoon;
	}

	public Profiel getTweede_persoon() {
		return tweede_persoon;
	}

	public void setTweede_persoon(Profiel tweede_persoon) {
		this.tweede_persoon = tweede_persoon;
	}
}
