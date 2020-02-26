package be.kuleuven.gent.project.ejb;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
