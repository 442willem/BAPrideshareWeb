package be.kuleuven.gent.project.ejb;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Route;

@Local
public interface RouteManagementEJBLocal {
	public void createRoute(Route r);
	public List<Route> findAllRoutes();
	public int getAantalRoutes();
	public Route findRoute(int id);
	public void filterRoutes();
	public List<Route> findRoutes(String beginpunt, String eindpunt, Timestamp begintijd, Timestamp eindtijd);
}
