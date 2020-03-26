package be.kuleuven.gent.project.ejb;

import java.util.List;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Bericht;
import be.kuleuven.gent.project.data.Profiel;

@Local
public interface BerichtManagementEJBLocal {
	public void createBericht(Bericht b);
	public List<Bericht> getConversation(int id, int id2);
	public List<Profiel> getConversations(int id);
}
