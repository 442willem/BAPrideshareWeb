package be.kuleuven.gent.project.ejb;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Rit;
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
	@Override
	public Route findRoute(int id) {
		return em.find(Route.class, id);
	}
	
    @Override
    public List<Route> findAllRoutes(){
		Query q = em.createQuery("SELECT r FROM Route r");
		return q.getResultList();
    }
    
    @Override
    public List<Route> findBestuurderRoute(int bestuurderID){
    	Query q=em.createQuery("SELECT r FROM Route r WHERE r.bestuurder.id=?1");
    	q.setParameter(1, bestuurderID);
    	return q.getResultList();
    }
    
    @Override
    public void filterRoutes() {}
    
    @Override
    public List<Route> findRoutes(String beginpunt,String eindpunt, Timestamp vertrektijd, Timestamp eindtijd){
		Query q;	
		
		if(vertrektijd!=null) {
			if(eindtijd!=null)q=em.createQuery("SELECT r FROM Route r WHERE r.beginpunt LIKE ?1 AND r.eindpunt LIKE ?2 AND (TIMESTAMPDIFF(HOUR,r.vertrektijd,vertrektijd)<=1) AND (TIMESTAMPDIFF(HOUR,r.eindtijd,eindtijd)<=1)");
			else q=em.createQuery("SELECT r FROM Route r WHERE r.beginpunt LIKE ?1 AND r.eindpunt LIKE ?2 AND (TIMESTAMPDIFF(HOUR,r.vertrektijd,vertrektijd)<=1)");
		}
		else {
			if(eindtijd!=null)q=em.createQuery("SELECT r FROM Route r WHERE r.beginpunt LIKE ?1 AND r.eindpunt LIKE ?2 AND (TIMESTAMPDIFF(HOUR,r.eindtijd,eindtijd)<=1)");
			else q=em.createQuery("SELECT r FROM Route r WHERE r.beginpunt LIKE ?1 AND r.eindpunt LIKE ?2");	
		}

		if(beginpunt!=null&&!beginpunt.isEmpty()) {
			q.setParameter(1,beginpunt);
		}else q.setParameter(1, "%");
		
		if(eindpunt!=null&&!eindpunt.isEmpty()) {
			q.setParameter(2, eindpunt);
		}else q.setParameter(2, "%");
		
		return q.getResultList();
    }
	@Override
	public int getAantalRoutes() {
		Query q = em.createQuery("SELECT COUNT(*) FROM Rit");
		return q.getFirstResult();
	}
	@Override
	public void boekIn(Profiel passagier, int routeId){
		Route route = findRoute(routeId);
		Rit rit = new Rit(passagier,route);
		em.persist(rit);	
	}
}
