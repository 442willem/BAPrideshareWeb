package be.kuleuven.gent.project.ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.kuleuven.gent.project.data.*;
import be.kuleuven.gent.project.utils.AuthenticationUtils;

/**
 * Session Bean implementation class ProfielManagementEJB
 */
@Stateless
@LocalBean
public class ProfielManagementEJB implements ProfielManagementEJBLocal {
	@PersistenceContext(unitName="db")
	private EntityManager em;
	@EJB
	private UserManagementEJBLocal userEJB;
	@Resource
	private SessionContext ctx;	
    /**
     * Default constructor. 
     */
    public ProfielManagementEJB() {
        // TODO Auto-generated constructor stub
    }
    @Override
     public void createProfiel(Profiel p) {
    	try {
			p.setPassword(AuthenticationUtils.encodeSHA256(p.getPassword()));
		} catch (Exception e) {
		}
		em.persist(p);
    }
    
    @Override
    public Profiel findProfiel(String username) {
    	Query q = em.createQuery("SELECT p FROM Profiel p where p.login=?1");
		q.setParameter(1, username);
		if(q.getResultList().size()>1)return null;
		return (Profiel) q.getResultList().get(0);
    }
    
	@Override
	public Profiel getProfiel() {
		String login = ctx.getCallerPrincipal().getName();
		Profiel p = userEJB.findProfiel(login);
		return p;
	}
	@Override
	public Profiel findProfiel(int id) {
		Query q = em.createQuery("SELECT p FROM Profiel p where p.id=?1");
		q.setParameter(1, id);
		if(q.getResultList().size()>1)return null;
		return (Profiel) q.getResultList().get(0);
	}
	@Override
	public String getDriverscore(int id) {
		System.out.println(id);
		Query q = em.createQuery("SELECT r FROM Review r where r.ontvanger.id=?1 AND r.modus=0");
		q.setParameter(1, id);
		List<Review> reviews=q.getResultList();
		System.out.println(reviews.size());
		double dScore=0;
		double dReviews=0;
		
		for(Review r:reviews) {
			
				dScore+=r.getScore();
				dReviews++;
			
		}
		if(dReviews>0) return String.format ("%.1f", (dScore/dReviews));
		else return "5";
	}
	@Override
	public String getPassagierscore(int id) {
		System.out.println(id);
		Query q = em.createQuery("SELECT r FROM Review r where r.ontvanger.id=?1 AND r.modus=1");
		q.setParameter(1, id);
		List<Review> reviews=q.getResultList();
		System.out.println(reviews.size());
		double pScore=0;
		double pReviews=0;
		
		for(Review r:reviews) {
				pScore+=r.getScore();
				pReviews++;
		}
		if(pReviews>0) return String.format ("%.1f", (pScore/pReviews));
		else return "5";
	}
}
