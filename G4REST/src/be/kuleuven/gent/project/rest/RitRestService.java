package be.kuleuven.gent.project.rest;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Rit;
import be.kuleuven.gent.project.data.Route;
import be.kuleuven.gent.project.data.Tussenstop;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;
import be.kuleuven.gent.project.ejb.RitManagementEJBLocal;
import be.kuleuven.gent.project.ejb.RouteManagementEJBLocal;
import be.kuleuven.gent.project.ejb.TussenstopManagementEJBLocal;
//mss route EJB & Rit EJB nodig
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;


@Path("rit_service")
@Stateless
@JWTTokenNeeded
public class RitRestService {

	@EJB
	private RitManagementEJBLocal ritRepo;
	
	@EJB
	private RouteManagementEJBLocal routeRepo;
	
	@EJB
	private UserManagementEJBLocal userRepo;
	
	@EJB
	private ProfielManagementEJBLocal profielRepo;
	
	@EJB
	private TussenstopManagementEJBLocal tussenRepo;
	
	@Context
	private UriInfo uriInfo;
	
	@Context
	private SecurityContext ctx;

	@GET
	@Path("myRides/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response myRides(@PathParam("username") String username) {
		try {
			
			Profiel profiel = profielRepo.findProfiel(username);
			
			
			List<Tussenstop> routelist = ritRepo.findPassagierRit(profiel.getId());
    		
    		
    		return Response.ok().entity(routelist).build();
    		
    		
    	}catch (Exception e) {
			return Response.serverError().build();
		}
		
	}
	
	@POST
	@Path("deleteRit/{Rid}")
	public Response deleteRit(@PathParam("Rid")String ritid) {
		try {
			int ritId = Integer.parseInt(ritid);
			tussenRepo.deleteRit(ritId);
			return Response.ok().build();
		}catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
}
