package be.kuleuven.gent.project.ejb;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.*;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Rit;

/**
 * Session Bean implementation class RitManagementEJB
 */
@Stateless
@LocalBean
public class RitManagementEJB implements RitManagementEJBLocal {

	@PersistenceContext(unitName="db")
	private EntityManager em;
	
	@EJB
	private UserManagementEJBLocal userEJB;
	@Resource
	private SessionContext ctx;	
    /**
     * Default constructor. 
     */
    public RitManagementEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createRit(Rit r) {	
    	String login = ctx.getCallerPrincipal().getName();
		Profiel p = userEJB.findProfiel(login);
    	
		r.setPassagier(p);
		em.persist(r);
	}

	@Override
	public void wijzigRit() {
		
	}

}
