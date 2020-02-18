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
	
	@Column(name="name", nullable=false)
	private String name;

	@Column(name="prijs", nullable=false)
	private int prijs;
	
	@Column(name="betaald", nullable=false)
	private boolean betaald;
	
	@Column(name="aantal_personen", nullable=false)
	private int aantalPersonen;
	
	@Column(name="vertrektijd", nullable=false)
	private Timestamp vertrektijd;
	
	@Column(name="eindtijd", nullable=false)
	private Timestamp eindtijd;
	
	@Column(name="passagier", nullable=false)
	private Profiel passagier;
	
	@Column(name="route", nullable=false)
	private Route route;
}
