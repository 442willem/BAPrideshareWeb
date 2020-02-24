package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;

@Local
public interface ProfielManagementEJBLocal {
	public boolean createProfiel();
}
