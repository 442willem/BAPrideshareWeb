package be.kuleuven.gent.project.ejb;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.kuleuven.gent.project.data.Review;

/**
 * Session Bean implementation class ReviewManagementEJB
 */
@Stateless
@LocalBean
public class ReviewManagementEJB implements ReviewManagementEJBLocal {
	@PersistenceContext(unitName="db")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ReviewManagementEJB() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void createReview(Review r) {
    	em.persist(r);
    }
	@Override
	public List<Review> findReviewsDriver(int id) {
		Query q = em.createQuery("SELECT r FROM Review r where r.ontvanger.id=?1 AND r.modus=0");
		q.setParameter(1, id);
		return q.getResultList();
	}
	@Override
	public List<Review> findReviewsPassenger(int id) {
		Query q = em.createQuery("SELECT r FROM Review r where r.ontvanger.id=?1 AND r.modus=1");
		q.setParameter(1, id);
		return q.getResultList();
	}
}