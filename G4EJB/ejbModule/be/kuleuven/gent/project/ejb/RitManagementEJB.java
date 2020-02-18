package be.kuleuven.gent.project.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;

import be.kuleuven.gent.project.data.Rit;

/**
 * Session Bean implementation class RitManagementEJB
 */
@Stateless
@LocalBean
public class RitManagementEJB implements RitManagementEJBLocal {

	@PersistenceContext(unitName="db")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public RitManagementEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean createRit() {
		Rit r=new Rit();		
		em.persist(r);
		
//		if()return false;
		
		return true;
	}

	@Override
	public boolean wijzigRit() {
		Rit r=new Rit();
		
//		if()return false;
		
		return true;
	}

}
