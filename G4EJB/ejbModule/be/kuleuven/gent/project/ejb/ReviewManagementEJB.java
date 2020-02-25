package be.kuleuven.gent.project.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}