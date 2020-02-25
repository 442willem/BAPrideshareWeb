package be.kuleuven.gent.project.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import be.kuleuven.gent.project.data.Bericht;

/**
 * Session Bean implementation class BerichtManagementEJB
 */
@Stateless
@LocalBean
public class BerichtManagementEJB implements BerichtManagementEJBLocal {
	@PersistenceContext(unitName="db")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public BerichtManagementEJB() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void createBericht(Bericht b) {
    	em.persist(b);
    }

}
