package be.kuleuven.gent.project.ejb;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Tussenstop;

/**
 * Session Bean implementation class TussenstopManagementEJB
 */
@Stateless
@LocalBean
public class TussenstopManagementEJB implements TussenstopManagementEJBLocal {
	@PersistenceContext(unitName="db")
	private EntityManager em;
	
	@EJB
	private UserManagementEJBLocal userEJB;
	@Resource
	private SessionContext ctx;
	
    /**
     * Default constructor. 
     */
    public TussenstopManagementEJB() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void createTir(Tussenstop t) {
    	//iets van calculate prijs op basis van extra tijd voor de omweg
    	t.setPrijs(20);
    	t.setBetaald(false);
    	t.setGoedgekeurd(false);
    	String login = ctx.getCallerPrincipal().getName();
		Profiel p = userEJB.findProfiel(login);
		t.setPassagier(p);
    	em.persist(t);
    }
	@Override
	public Tussenstop findRit(int id) {
		return em.find(Tussenstop.class, id);
	}
	@Override
	public void keurRitGoed(int ritId) {
		  Tussenstop r = em.find(Tussenstop.class, ritId);
		  
		  System.out.println("HALLO HIER");
		  r.setGoedgekeurd(true);
		  em.persist(r);
	}

}
