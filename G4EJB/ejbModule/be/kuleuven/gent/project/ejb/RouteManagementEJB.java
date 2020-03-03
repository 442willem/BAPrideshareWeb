package be.kuleuven.gent.project.ejb;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Route;

/**
 * Session Bean implementation class RouteManagementEJB
 */
@Stateless
@LocalBean
public class RouteManagementEJB implements RouteManagementEJBLocal {
	@PersistenceContext(unitName="db")
	private EntityManager em;
	
	@EJB
	private UserManagementEJBLocal userEJB;
	@Resource
	private SessionContext ctx;
	
    /**
     * Default constructor. 
     */
    public RouteManagementEJB() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void createRoute(Route r) {
    	String login = ctx.getCallerPrincipal().getName();
		Profiel p = userEJB.findProfiel(login);
    	
		r.setBestuurder(p);
    	em.persist(r);
    }
    
    public List<Route> findAllRoutes(){
		Query q = em.createQuery("SELECT r FROM Route r");
		return q.getResultList();
    }
    public void filterRoutes() {}
    @Override
    public List<Route> findRoutes(String beginpunt,String eindpunt, Timestamp vertrektijd, Timestamp eindtijd){
		Query q = em.createQuery("SELECT r FROM Route r WHERE r.beginpunt=?1 AND r.eindpunt=?2 AND r.vertrektijd>=?3 AND r.vertrektijd<=?4 AND r.eindtijd>=?5 AND r.eindtijd<=?6");
		
		if(beginpunt!=null) {
			q.setParameter(1,beginpunt);
		}else q.setParameter(1, "%");
		
		if(eindpunt!=null) {
			q.setParameter(2, eindpunt);
		}else q.setParameter(2, "%");
		
		if(vertrektijd!=null) {
			q.setParameter(3, vertrektijd);
			q.setParameter(4, vertrektijd);
		}
		else {
			Timestamp t = new Timestamp(0);
			t.setYear(0);
			q.setParameter(3, t);
			t.setYear(8000);
			q.setParameter(4, t);
		}
		
		if(eindtijd!=null) {		
			q.setParameter(5, eindtijd);
			q.setParameter(6, eindtijd);
		}
		else {
			Timestamp t =  new Timestamp(0);
			t.setYear(0);
			q.setParameter(5,t);
			t.setYear(8000);
			q.setParameter(6, t);
		}
		
		return q.getResultList();
    }
	@Override
	public int getAantalRoutes() {
		Query q = em.createQuery("SELECT COUNT(*) FROM Rit");
		return q.getFirstResult();
	}

}
