package be.kuleuven.gent.project.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.kuleuven.gent.project.data.Bericht;
import be.kuleuven.gent.project.utils.*;

/**
 * Session Bean implementation class BerichtManagementEJB
 */
@Stateless
@LocalBean
public class BerichtManagementEJB implements BerichtManagementEJBLocal {
	@PersistenceContext(unitName="db")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public BerichtManagementEJB() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void createBericht(Bericht b) {
    	em.persist(b);
    }
    
	@Override
	public List<Bericht> getConversation(int zenderID, int ontvangerID) {
		Query q = em.createQuery("SELECT b FROM Bericht b where b.zender.id=?1 AND b.ontvanger.id=?2");
		q.setParameter(1, zenderID);
		q.setParameter(2, ontvangerID);
		
		Query q2 = em.createQuery("SELECT b FROM Bericht b where b.zender.id=?1 AND b.ontvanger.id=?2");
		q2.setParameter(2, zenderID);
		q2.setParameter(1, ontvangerID);
		
		List<Bericht> convo = new ArrayList<Bericht>();
		convo.addAll(q.getResultList());
		convo.addAll(q2.getResultList());
			
		Collections.sort(convo,new ConversationSortByTime());
		return convo;
	}

}
