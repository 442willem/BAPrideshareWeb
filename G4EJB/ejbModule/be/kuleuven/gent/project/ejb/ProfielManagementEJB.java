package be.kuleuven.gent.project.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import be.kuleuven.gent.project.data.Profiel;

/**
 * Session Bean implementation class ProfielManagementEJB
 */
@Stateless
@LocalBean
public class ProfielManagementEJB implements ProfielManagementEJBLocal {
	@PersistenceContext(unitName="db")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ProfielManagementEJB() {
        // TODO Auto-generated constructor stub
    }
    
     public boolean createProfiel() {
    	Profiel p =new Profiel();
		em.persist(p);
    	return true;
    }
}
