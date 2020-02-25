package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Bericht;

@Local
public interface BerichtManagementEJBLocal {
	public void createBericht(Bericht b);
}
