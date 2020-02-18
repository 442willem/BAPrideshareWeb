package be.kuleuven.gent.project.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class RitManagementEJB
 */
@Stateless
@LocalBean
public class RitManagementEJB implements RitManagementEJBLocal {

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
		
		if()return false;
		
		return true;
	}

	@Override
	public boolean wijzigRit() {
		Rit r=new Rit();
		
		if()return false;
		
		return true;
	}

}
