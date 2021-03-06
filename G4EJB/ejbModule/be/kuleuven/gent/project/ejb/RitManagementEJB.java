package be.kuleuven.gent.project.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.*;

import com.sun.jmx.snmp.Timestamp;

import be.kuleuven.gent.project.data.Notificatie;
import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Rit;
import be.kuleuven.gent.project.data.Route;
import be.kuleuven.gent.project.data.Tussenstop;

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
	@Resource
	TimerService timerService;
	
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
		Query q = em.createQuery("SELECT COUNT(*) FROM Tussenstop");
		return q.getFirstResult();
	}
	@Override
	public List<Rit> findAllRitten(){
		Query q = em.createQuery("SELECT r FROM Tussenstop r");
		return q.getResultList();
		
		//test functie
//		List<Rit> ritten =new ArrayList<Rit>();
//		Rit r1=new Rit(1,12, new Timestamp(), new Timestamp(), new Profiel(), new Route());
//		ritten.add(r1);
//		return ritten;
	}

	@Override
	public List<Rit> findRitten(int route) {
		Query q = em.createQuery("SELECT r FROM Tussenstop r where r.route.id=?1");
		q.setParameter(1, route);
		return q.getResultList();
	}

	@Override
	public void keurRitGoed(int ritId) {
		  Tussenstop r = em.find(Tussenstop.class, ritId);
		  
		  System.out.println("HALLO HIER");
		  r.setGoedgekeurd(true);
		// setting timer for 24 hours before start to send notification
			long t = r.getRoute().getVertrektijd().getTime();
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(t-86400000);
			Date date = cal.getTime();
			Timer timer = timerService.createSingleActionTimer(date, new TimerConfig(r,true));
			System.out.println("TimerBean: timeout initiated for:" + date.toString());		
		  em.persist(r);
	}
	@Timeout
	public void timeout(Timer timer) {
		System.out.println("TimerBean: timeout occurred");
		Tussenstop r=(Tussenstop) timer.getInfo();
		Notificatie n = new Notificatie("ritHerinnering");
		System.out.println("ritid:"+r.getId()+"passagierId"+r.getPassagier().getId());
		n.setProfiel(r.getPassagier());
		n.setGelezen(false);
		em.persist(n);
	}

	@Override
	public void keurRitAf(int ritId) {
		Tussenstop r = em.getReference(Tussenstop.class, ritId);
		  
		  System.out.println(r.getId()+" "+ritId);
		  r.setGoedgekeurd(false);
		  em.persist(r);
	}

    @Override
    public List<Tussenstop> findPassagierRit(int passagierID){
    	Query q=em.createQuery("SELECT r FROM Tussenstop r WHERE r.passagier.id=?1");
    	q.setParameter(1, passagierID);
    	return q.getResultList();
    }
    
  
	@Override
	public void betaalRit(int id) {
		Tussenstop r = em.find(Tussenstop.class, id);
		r.setBetaald(true);
		em.persist(r);	
	}

	@Override
	public Tussenstop findRit(int id) {
		return em.find(Tussenstop.class, id);
	}
	
}
