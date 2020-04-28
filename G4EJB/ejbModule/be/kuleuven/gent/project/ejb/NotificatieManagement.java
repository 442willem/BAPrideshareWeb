package be.kuleuven.gent.project.ejb;

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
    	return q.getResultList();
	}
	public void createNotificatie(Notificatie n) {
		em.persist(n);
	}

}
