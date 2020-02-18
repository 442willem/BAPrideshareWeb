package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.*;

@Local
public interface UserManagementEJBLocal {

	public Profiel findProfiel(String login);
}