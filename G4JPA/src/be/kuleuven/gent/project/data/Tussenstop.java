package be.kuleuven.gent.project.data;

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
	
	@Column(name="plaats", nullable=false)
	private String plaats;
	
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
	
	public String getPlaats() {
		return plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
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
	
	public Tussenstop() {}
	
}
