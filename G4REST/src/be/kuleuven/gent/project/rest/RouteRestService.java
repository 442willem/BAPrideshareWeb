package be.kuleuven.gent.project.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletContextEvent;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import be.kuleuven.gent.project.data.Route;
import be.kuleuven.gent.project.ejb.RouteManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

@Path("route_service")
@Stateless
@JWTTokenNeeded
public class RouteRestService {

	@EJB
	private UserManagementEJBLocal userRepo;
	
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
	

}
