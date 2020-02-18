package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;

@Local
public interface RitManagementEJBLocal {
	public boolean createRit();
	public boolean wijzigRit();
}
