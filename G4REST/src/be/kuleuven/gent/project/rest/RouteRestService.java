package be.kuleuven.gent.project.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletContextEvent;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Route;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;
import be.kuleuven.gent.project.ejb.RouteManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

@Path("route_service")
@Stateless
@JWTTokenNeeded
public class RouteRestService {

	@EJB
	private UserManagementEJBLocal userRepo;
	
	@EJB
	private ProfielManagementEJBLocal profielRepo;
	
	@EJB
	private RouteManagementEJBLocal routeRepo;
	
	@Context
	private UriInfo uriInfo;
	
	@Context
	private SecurityContext ctx;
	
	@GET
	@Path("alleRoutes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRoutes() {
		try {
		List<Route> alleRoutes = routeRepo.findAllRoutes();
        return Response.ok().entity(alleRoutes).build();
        
		}catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}
	
	@POST
	@Path("createRoute/{username}")
	public Response createRoute(@PathParam("username") String username, InputStream incomingData) {
		
		StringBuilder sb = new StringBuilder();
    	Gson json = new Gson();
    
    	try {
    		BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
    		String line =null;
    	
    		while((line =in.readLine())!=null) {
    			sb.append(line);
    		}
    		
    		Profiel profiel =profielRepo.findProfiel(username);
    	
    		
    		Route route = new Route(json.fromJson(sb.toString(), Route.class));
    	
    		route.setBestuurder(profiel);	
    		
    		
    		routeRepo.createRoute(route,0);
    		
    		return Response.ok().entity(route).build();
    		
    		
    	}catch (Exception e) {
			return Response.serverError().build();
		}
		
	}
	
	@GET
	@Path("searchRoutes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchRoute(@QueryParam("beginpunt") String beginpunt,@QueryParam("eindpunt") String eindpunt,@QueryParam("begintijd") String begintijd,@QueryParam("eindtijd") String eindtijd) {
		System.out.println("begin: "+beginpunt+"eind:"+eindpunt+"begint:"+begintijd+"eindt:"+eindtijd);
		try {
			Timestamp beginTimestamp;
			Timestamp eindTimestamp;
			if(begintijd==null||begintijd.equals("") )beginTimestamp=null;
			else beginTimestamp=routeRepo.toTimestamp(begintijd);
			if(eindtijd==null||eindtijd.equals(""))eindTimestamp=null;
			else eindTimestamp=routeRepo.toTimestamp(eindtijd);
			
//			System.out.println("begin: "+beginTimestamp.toString());
//			System.out.println("eind: "+eindTimestamp.toString());
			
			List<Route> routelist = routeRepo.findRoutes(beginpunt, eindpunt, beginTimestamp, eindTimestamp);
    		
			System.out.println("grootte: "+routelist.size());
    		
    		return Response.ok().entity(routelist).build();
    		
    		
    	}catch (Exception e) {
    		System.out.println("error: "+e.toString());
			return Response.serverError().build();
		}
		
	}
	
	@GET
	@Path("myRoute/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response myRoute(@PathParam("username") String username) {
		try {
			
			Profiel profiel = profielRepo.findProfiel(username);
			
			List<Route> routelist = routeRepo.findBestuurderRoute(profiel.getId());
    		
			System.out.println("grootte: "+routelist.size());
    		
    		return Response.ok().entity(routelist).build();
    		
    		
    	}catch (Exception e) {
			return Response.serverError().build();
		}
		
	}
	
	@GET
	@Path("tussenstops/{routeid}")
	public Response getTussenstops(@PathParam("routeid") int routeid) {
		try {
			
			
			String[] routelist = routeRepo.zoekGeaccepteerdeTussenstops(routeid);
    		
			System.out.println("grootte: ");
    		
    		return Response.ok().entity(routelist).build();
    		
    		
    	}catch (Exception e) {
			return Response.serverError().build();
		}
		
	}
	
	
	
	

}
