package be.kuleuven.gent.project.ejb;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Rit;
import be.kuleuven.gent.project.data.Route;

@Local
public interface RouteManagementEJBLocal {
	public void createRoute(Route r);
	public List<Route> findAllRoutes();
	public long getAantalRoutes();
	public Route findRoute(int id);
	public void filterRoutes();
	public List<Route> findRoutes(String beginpunt, String eindpunt, Timestamp begintijd, Timestamp eindtijd);
	List<Route> findBestuurderRoute(int bestuurderID);
	public void boekIn(Profiel passagier, int routeId);
	public String[] zoekGeaccepteerdeTussenstops(int routeID);
	public String[] zoekTijdelijkeTussenstops(int routeID,int ritID);
}
