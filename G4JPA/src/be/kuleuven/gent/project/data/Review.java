package be.kuleuven.gent.project.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idreview", nullable=false,length=16)
	private int id;
	
	@Column(name="descriptie", nullable=false)
	private String descriptie;
	
	@Column(name="score", nullable=false)
	private int score;
	
	@Column(name="modus", nullable=false)
	private int modus; //0=driver, 1=passagier
	
	@JoinColumn(name="ontvangerId", nullable=false)
	private Profiel ontvanger;
	
	@JoinColumn(name="verzenderId", nullable=false)
	private Profiel afzender;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescriptie() {
		return descriptie;
	}
	public void setDescriptie(String descriptie) {
		this.descriptie = descriptie;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getModus() {
		return modus;
	}
	public void setModus(int modus) {
		this.modus = modus;
	}
	public Profiel getOntvanger() {
		return ontvanger;
	}
	public void setOntvanger(Profiel ontvanger) {
		this.ontvanger = ontvanger;
	}
	public Profiel getAfzender() {
		return afzender;
	}
	public void setAfzender(Profiel afzender) {
		this.afzender = afzender;
	}
	
	
	
	
}
