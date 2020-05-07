package be.kuleuven.gent.project.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.kuleuven.gent.project.data.Notificatie;
import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Route;
import be.kuleuven.gent.project.data.Tussenstop;

/**
 * Session Bean implementation class TussenstopManagementEJB
 */
@Stateless
@LocalBean
public class TussenstopManagementEJB implements TussenstopManagementEJBLocal {
	@PersistenceContext(unitName="db")
	private EntityManager em;

	@EJB
	private UserManagementEJBLocal userEJB;
	@Resource
	private SessionContext ctx;
	@Resource
	TimerService timerService;

	/**
	 * Default constructor. 
	 */
	public TussenstopManagementEJB() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void createTir(Tussenstop t) {
		//iets van calculate prijs op basis van extra tijd voor de omweg
		t.setBetaald(false);
		t.setGoedgekeurd(false);
		String login = ctx.getCallerPrincipal().getName();
		Profiel p = userEJB.findProfiel(login);
		t.setPassagier(p);
		em.persist(t);
		Notificatie n = new Notificatie("ritRequest");
		n.setProfiel(t.getPassagier());
		n.setRit(t);
		n.setGelezen(false);
		em.persist(n);
	}
	@Override
	public Tussenstop findRit(int id) {
		return em.find(Tussenstop.class, id);
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
	public void deleteRit(int id) {
		Tussenstop r = em.find(Tussenstop.class, id);
		Query query = em.createNativeQuery("DELETE FROM notificatie WHERE ritId = " + id);
		query.executeUpdate();
		em.remove(r);	
	}

}
