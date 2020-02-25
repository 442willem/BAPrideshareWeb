package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Rit;

@Local
public interface RitManagementEJBLocal {
	public void createRit(Rit r);
	public void wijzigRit();
}
