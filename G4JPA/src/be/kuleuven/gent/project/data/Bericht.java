package be.kuleuven.gent.project.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name="bericht")
public class Bericht {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idBericht", nullable=false,length=16)
	private int id;
	
	@Column(name="content", nullable=false)
	private String content;
	
	@JoinColumn(name="chatId", nullable=false)
	private Chat chat;
	
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

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
