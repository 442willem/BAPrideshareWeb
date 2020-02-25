package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Route;

@Local
public interface RouteManagementEJBLocal {
	public void createRoute(Route r);
}
