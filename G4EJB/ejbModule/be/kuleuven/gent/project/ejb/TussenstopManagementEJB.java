package be.kuleuven.gent.project.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import be.kuleuven.gent.project.data.Tussenstop;

/**
 * Session Bean implementation class TussenstopManagementEJB
 */
@Stateless
@LocalBean
public class TussenstopManagementEJB implements TussenstopManagementEJBLocal {
	@PersistenceContext(unitName="db")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public TussenstopManagementEJB() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void createTussenstop(Tussenstop t) {
    	em.persist(t);
    }

}
