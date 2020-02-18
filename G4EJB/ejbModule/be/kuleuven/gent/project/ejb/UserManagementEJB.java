package be.kuleuven.gent.project.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.kuleuven.gent.project.data.*;

/**
 * Session Bean implementation class UserManagementEJB
 */
@Stateless
public class UserManagementEJB implements UserManagementEJBLocal {
	
	@PersistenceContext(unitName="db")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UserManagementEJB() {
        // TODO Auto-generated constructor stub
    }
	
	@Override
	public Profiel findProfiel(String login) {
		
		Query q = em.createQuery("SELECT p FROM Person p WHERE p.login = :login");
		q.setParameter("login", login);
		List<Profiel> profielen = q.getResultList();
		
		if(profielen.size() == 1)
			return profielen.get(0);
		else return null;
	}

}
