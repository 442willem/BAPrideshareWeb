package be.kuleuven.gent.project.ejb;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.util.Date;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.kuleuven.gent.project.data.Notificatie;
import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Rit;
import be.kuleuven.gent.project.data.Route;
import be.kuleuven.gent.project.data.Tussenstop;

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
	@Resource
	TimerService timerService;


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
// setting timer for 24 hours before start to send notification
		long t = r.getVertrektijd().getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(t-86400000);
		Date date = cal.getTime();
		Timer timer = timerService.createSingleActionTimer(date, new TimerConfig(r,true));
		System.out.println("TimerBean: timeout initiated for:" + date.toString());		
		em.persist(r);
	}
	
	@Override
	public void createRoute (Route r, int i) {
		// setting timer for 24 hours before start to send notification
				long t = r.getVertrektijd().getTime();
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(t-86400000);
				Date date = cal.getTime();
				Timer timer = timerService.createSingleActionTimer(date, new TimerConfig(r,true));
				System.out.println("TimerBean: timeout initiated for:" + date.toString());		
				em.persist(r);
		
	}
	
	@Override
	public Timestamp toTimestamp(String date) {
		Timestamp timestamp;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date parsedDateVertrek = dateFormat.parse(date);
            timestamp = new java.sql.Timestamp(parsedDateVertrek.getTime());
        } catch(Exception e) {
           System.out.println(e.toString());
            timestamp=null;
        }

        return  timestamp;
		
	}
	@Timeout
	public void timeout(Timer timer) {
		System.out.println("TimerBean: timeout occurred");
		Route r=(Route) timer.getInfo();
		Notificatie n = new Notificatie("routeHerinnering");
		System.out.println("routeid:"+r.getId()+"bestuurderid"+r.getBestuurder().getId());
		n.setProfiel(r.getBestuurder());
		em.persist(n);
	}
	@Override
	public void notifyAllRitten(int ritId) {
		Query q=em.createQuery("SELECT r FROM Tussenstop r WHERE r.route.id=?1 AND r.id<>?2");
		q.setParameter(1, em.find(Tussenstop.class, ritId).getRoute().getId());
		q.setParameter(2, ritId);
		List<Tussenstop> ritten = q.getResultList();

		for (Tussenstop rit: ritten) {
			Notificatie n = new Notificatie("ritChange");
			n.setProfiel(rit.getPassagier());
			n.setRit(rit);
			em.persist(n);
		}
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
				q=em.createQuery("SELECT r FROM route r WHERE r.beginpunt LIKE ?1 AND r.eindpunt LIKE ?2 AND r.eindtijd <=?3 AND r.eindtijd >=?4 AND r.vertrektijd <=?5 and r.vertrektijd >=?6");

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
			q.setParameter(1,"%"+beginpunt+"%");
		}else q.setParameter(1, "%");

		if(eindpunt!=null && !eindpunt.isEmpty()) {
			q.setParameter(2, "%"+eindpunt+"%");
		}else q.setParameter(2, "%");
		return q.getResultList();
	}
	@Override
	public long getAantalRoutes() {
		Query q = em.createQuery("SELECT COUNT(r.beginpunt) FROM Route r");
		return  (long) q.getSingleResult();
	}
	//	@Override
	//	public void boekIn(Profiel passagier, int routeId){
	//		
	//		Route route = findRoute(routeId);
	//		Rit rit = new Rit(passagier,route);
	//		em.persist(rit);	
	//	}

	//geeft het beginpunt en eindpunt van de gegeven rit terug
	@Override
	public String[] zoekTijdelijkeTussenstops(int routeID,int ritID){
		Query q;
		String[] tussenstops= new String[2];
		q= em.createQuery("SELECT r FROM Tussenstop r WHERE r.route = ?1 AND r.id= ?2 ");
		q.setParameter(1, routeID);
		q.setParameter(2, ritID);
		Tussenstop tijdelijkeRit = (Tussenstop) q.getSingleResult();

		tussenstops[0]=tijdelijkeRit.getBeginpunt();
		tussenstops[1]=tijdelijkeRit.getEindpunt();

		return tussenstops;	
	}

	// geeft alle geaccepteerde tussenstoppen terug
	@Override
	public String[] zoekGeaccepteerdeTussenstops(int routeID){
		Query q;
		String[] tussenstops;
		int index=0;
		System.out.println(routeID);
		q= em.createQuery("SELECT r FROM Tussenstop r WHERE r.route.id = ?1 AND r.goedgekeurd=TRUE");
		q.setParameter(1, routeID);
		List<Tussenstop> tijdelijkeRitten = q.getResultList();
		System.out.println(tijdelijkeRitten.size());
		tussenstops = new String[tijdelijkeRitten.size()*2];

		for (Tussenstop r: tijdelijkeRitten) {
			tussenstops[index]=r.getBeginpunt();
			index++;
			tussenstops[index]=r.getEindpunt();
			index++;
		}
		System.out.println("aantal tussenstops:"+ tussenstops.length);
		return tussenstops;	
	}
}
