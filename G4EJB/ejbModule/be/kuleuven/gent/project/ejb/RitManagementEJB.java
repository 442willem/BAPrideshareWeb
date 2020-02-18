package be.kuleuven.gent.project.ejb;

import javax.persistence.*;

public class RitManagementEJB implements RitManagementEJBLocal {
	
	@PersistenceContext(unitName="db")
	private EntityManager em;
	
    public RitManagementEJB() {
        // TODO Auto-generated constructor stub
    }
	
    @Override
	public boolean createRit() {		
//		Rit r=new Rit();
//		
//		if()return false;
		
		return true;
	}
    @Override
	public boolean wijzigRit() {		
//		Rit r=new Rit();
//		
//		if()return false;
		
		return true;
	}
}
