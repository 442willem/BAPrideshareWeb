package be.kuleuven.gent.project.ejb;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.kuleuven.gent.project.data.Notificatie;
import be.kuleuven.gent.project.data.Tussenstop;
import be.kuleuven.gent.project.utils.ConversationSortByTime;
import be.kuleuven.gent.project.utils.NotificationSortByTime;

/**
 * Session Bean implementation class NotificatieManagement
 */
@Stateless
@LocalBean
public class NotificatieManagement implements NotificatieManagementLocal {
	@PersistenceContext(unitName="db")
	private EntityManager em;
	
	@EJB
	private UserManagementEJBLocal userEJB;
	@Resource
	private SessionContext ctx;
	
	
    public NotificatieManagement() {
       
    }

	@Override
	public List<Notificatie> findNotificaties(int id) {
    	Query q=em.createQuery("SELECT n FROM Notificatie n WHERE n.profiel.id=?1");
    	q.setParameter(1, id);
    	List<Notificatie> notificaties = q.getResultList();
    	Collections.sort(notificaties,new NotificationSortByTime());
    	return notificaties;
	}
	public void createNotificatie(Notificatie n) {
		n.setGelezen(false);
		em.persist(n);
	}

	@Override
	public void delete(int id) {
		Notificatie n = em.find(Notificatie.class, id);
		em.remove(n);		
	}

	@Override
	public long getAantalNotificaties(int id) {
    	Query q=em.createQuery("SELECT COUNT(n.id) FROM Notificatie n WHERE n.profiel.id=?1 AND n.gelezen=FALSE");
    	q.setParameter(1, id);
    	return (long) q.getSingleResult();
	}

	@Override
	public void setGelezen(int id) {
		Notificatie n = em.find(Notificatie.class, id);
		n.setGelezen(true);
		em.persist(n);		
	}
}
