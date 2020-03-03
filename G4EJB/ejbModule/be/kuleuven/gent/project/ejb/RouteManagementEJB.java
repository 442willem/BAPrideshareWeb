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
    public List<Route> findRoutes(Route model){
		Query q = em.createQuery("SELECT r FROM Route r");
		return q.getResultList();
    }
	@Override
	public int getAantalRoutes() {
		Query q = em.createQuery("SELECT COUNT(*) FROM Rit");
		return q.getFirstResult();
	}

}
