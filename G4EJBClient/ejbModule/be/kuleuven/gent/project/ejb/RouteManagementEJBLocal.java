package be.kuleuven.gent.project.ejb;

import java.util.List;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Route;

@Local
public interface RouteManagementEJBLocal {
	public void createRoute(Route r);
	public List<Route> findAllRoutes();
	public int getAantalRoutes();
	public List<Route> findRoutes(Route queryRoute);
	public void filterRoutes();
}
