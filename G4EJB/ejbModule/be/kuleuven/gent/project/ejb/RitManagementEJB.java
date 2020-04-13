package be.kuleuven.gent.project.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.*;

import com.sun.jmx.snmp.Timestamp;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Rit;
import be.kuleuven.gent.project.data.Route;

/**
 * Session Bean implementation class RitManagementEJB
 */
@Stateless
@LocalBean
public class RitManagementEJB implements RitManagementEJBLocal {

	@PersistenceContext(unitName="db")
	private EntityManager em;
	
	@EJB
	private UserManagementEJBLocal userEJB;
	@Resource
	private SessionContext ctx;	
	
	@EJB
	private RouteManagementEJBLocal routeEJB;
	
    /**
     * Default constructor. 
     */
    public RitManagementEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createRit(Rit r) {	
    	String login = ctx.getCallerPrincipal().getName();
		Profiel p = userEJB.findProfiel(login);
    	
		r.setPassagier(p);
		em.persist(r);
	}

	@Override
	public void wijzigRit() {
		
	}
	@Override
	public int getAantalRitten() {
		Query q = em.createQuery("SELECT COUNT(*) FROM Rit");
		return q.getFirstResult();
	}
	@Override
	public List<Rit> findAllRitten(){
		Query q = em.createQuery("SELECT r FROM Rit r");
		return q.getResultList();
		
		//test functie
//		List<Rit> ritten =new ArrayList<Rit>();
//		Rit r1=new Rit(1,12, new Timestamp(), new Timestamp(), new Profiel(), new Route());
//		ritten.add(r1);
//		return ritten;
	}

	@Override
	public List<Rit> findRitten(int route) {
		Query q = em.createQuery("SELECT r FROM Rit r where r.route.id=?1");
		q.setParameter(1, route);
		return q.getResultList();
	}

	@Override
	public void keurRitGoed(int ritId) {
		  Rit r = em.find(Rit.class, ritId);
		  
		  System.out.println("HALLO HIER");
		  r.setGoedgekeurd(true);
		  em.persist(r);
	}

	@Override
	public void keurRitAf(int ritId) {
		  Rit r = em.getReference(Rit.class, ritId);
		  
		  System.out.println(r.getId()+" "+ritId);
		  r.setGoedgekeurd(false);
		  em.persist(r);
	}

    @Override
    public List<Route> findPassagierRit(int passagierID){
    	Query q=em.createQuery("SELECT r FROM Rit r WHERE r.passagier.id=?1");
    	q.setParameter(1, passagierID);
    	return q.getResultList();
    }
    
    @Override
	public void boekIn(Rit r, Profiel passagier, int routeId){
		Route route = routeEJB.findRoute(routeId);
		r.setPassagier(passagier);
		r.setRoute(route);
		em.persist(r);
	}
    
	@Override
	public void betaalRit(int id) {
		Rit r = em.find(Rit.class, id);
		r.setBetaald(true);
		em.persist(r);	
	}

	@Override
	public Rit findRit(int id) {
		return em.find(Rit.class, id);
	}
}
