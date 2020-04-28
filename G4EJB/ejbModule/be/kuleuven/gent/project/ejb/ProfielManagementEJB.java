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
	public void updateScores(int id) {
		System.out.println(id);
		Query q = em.createQuery("SELECT r FROM Review r where r.ontvanger.id=?1");
		q.setParameter(1, id);
		List<Review> reviews=q.getResultList();
		System.out.println(reviews.size());
		int dScore=0;
		int dReviews=0;
		int pScore=0;
		int pReviews=0;
		
		for(Review r:reviews) {
			if(r.getModus()==0) {
				dScore+=r.getScore();
				dReviews++;
			}
			if(r.getModus()==1) {
				pScore+=r.getScore();
				pReviews++;
			}
		}
		Profiel p = em.find(Profiel.class, id);
		if(dReviews>0)p.setDriverscore((int)(dScore/dReviews));else p.setDriverscore(5);
		if(pReviews>0)p.setPassagierscore((int)(pScore/pReviews));else p.setPassagierscore(5);
		em.persist(p);
	}
}
