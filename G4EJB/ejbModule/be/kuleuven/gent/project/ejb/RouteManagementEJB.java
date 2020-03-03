package be.kuleuven.gent.project.ejb;

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
    public List<Route> findRoutes(String beginpunt,String eindpunt, Timestamp begintijd, Timestamp eindtijd){
		Query q = em.createQuery("SELECT r FROM Route r WHERE r.beginpunt=?1 AND r.eindpunt=?2 AND r.begintijd=?3 AND r.eindtijd=?4");
		
		if(beginpunt!=null) {
			q.setParameter(1, beginpunt);
		}
		if(eindpunt!=null) {
			q.setParameter(2, eindpunt);
		}
		if(begintijd!=null) {
			q.setParameter(3, begintijd);
		}
		if(eindtijd!=null) {
			q.setParameter(4, eindtijd);
		}
		
		return q.getResultList();
    }
	@Override
	public int getAantalRoutes() {
		Query q = em.createQuery("SELECT COUNT(*) FROM Rit");
		return q.getFirstResult();
	}

}
