package be.kuleuven.gent.project.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Table(name="bericht")
public class Bericht {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idBericht", nullable=false,length=16)
	private int id;
	
	@Column(name="content", nullable=false)
	private String content;
	
	@JoinColumn(name="zenderId", nullable=false)
	private Profiel zender;

	@JoinColumn(name="ontvangerId", nullable=false)
	private Profiel ontvanger;
	
	@Column(name="timestamp", nullable=false)
	private Timestamp timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Profiel getZender() {
		return zender;
	}

	public void setZender(Profiel zender) {
		this.zender = zender;
	}

	public Profiel getOntvanger() {
		return ontvanger;
	}

	public void setOntvanger(Profiel ontvanger) {
		this.ontvanger = ontvanger;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public String tijdstipToString() {
		return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
	}
	
}
