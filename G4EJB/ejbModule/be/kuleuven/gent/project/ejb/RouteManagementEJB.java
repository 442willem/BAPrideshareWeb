package be.kuleuven.gent.project.ejb;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
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
			if(eindtijd!=null) {
				q=em.createQuery("SELECT r FROM Route r WHERE r.beginpunt LIKE ?1 AND r.eindpunt LIKE ?2 AND r.eindtijd <=?3 AND r.eindtijd >=?4 AND r.vertrektijd <=?5 and r.vertrektijd >=?6");
				
				LocalDateTime eindtijd1=eindtijd.toLocalDateTime().plusHours(2);
				LocalDateTime eindtijd2=eindtijd.toLocalDateTime().minusHours(2);
				
				q.setParameter(3, Timestamp.valueOf(eindtijd1));
				q.setParameter(4, Timestamp.valueOf(eindtijd2) );
				
				LocalDateTime vertrektijd1=vertrektijd.toLocalDateTime().plusHours(2);
				LocalDateTime vertrektijd2=vertrektijd.toLocalDateTime().minusHours(2);
			
				q.setParameter(5, Timestamp.valueOf(vertrektijd1));
				q.setParameter(6, Timestamp.valueOf(vertrektijd2) );
			}
			else {
				q=em.createQuery("SELECT r FROM Route r WHERE r.beginpunt LIKE ?1 AND r.eindpunt LIKE ?2 AND r.vertrektijd <=?3 AND r.vertrektijd >=?4 ");
				LocalDateTime vertrektijd1=vertrektijd.toLocalDateTime().plusHours(2);
				LocalDateTime vertrektijd2=vertrektijd.toLocalDateTime().minusHours(2);
				
				System.out.println(vertrektijd.toString());
				System.out.println(vertrektijd1.toString());
				System.out.println(vertrektijd2.toString());
				q.setParameter(3, Timestamp.valueOf(vertrektijd1));
				q.setParameter(4, Timestamp.valueOf(vertrektijd2) );
			}
		}
		else {
			if(eindtijd!=null) {
				q=em.createQuery("SELECT r FROM Route r WHERE r.beginpunt LIKE ?1 AND r.eindpunt LIKE ?2 AND r.eindtijd <=?3 AND r.eindtijd >=?4");
				
				LocalDateTime eindtijd1=eindtijd.toLocalDateTime().plusHours(2);
				LocalDateTime eindtijd2=eindtijd.toLocalDateTime().minusHours(2);
				
				q.setParameter(3, Timestamp.valueOf(eindtijd1));
				q.setParameter(4, Timestamp.valueOf(eindtijd2) );
				
				}
			else q=em.createQuery("SELECT r FROM Route r WHERE r.beginpunt LIKE ?1 AND r.eindpunt LIKE ?2");	
		}
		
		if(beginpunt!=null && !beginpunt.isEmpty()) {
			q.setParameter(1,beginpunt);
		}else q.setParameter(1, "%");
		
		if(eindpunt!=null && !eindpunt.isEmpty()) {
			q.setParameter(2, eindpunt);
		}else q.setParameter(2, "%");
		
		if(eindtijd==null && vertrektijd==null)q=em.createQuery("SELECT r FROM Route r");
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
