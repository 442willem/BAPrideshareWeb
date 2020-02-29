package be.kuleuven.gent.project.ejb;

import java.util.List;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Rit;

@Local
public interface RitManagementEJBLocal {
	public void createRit(Rit r);
	public void wijzigRit();
	public List<Rit> findAllRitten();
	int getAantalRitten();
}
