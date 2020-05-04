package be.kuleuven.gent.project.rest;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Rit;
import be.kuleuven.gent.project.data.Route;
import be.kuleuven.gent.project.ejb.RitManagementEJBLocal;
//mss route EJB & Rit EJB nodig
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

@javax.enterprise.context.RequestScoped
@Path("rit_service")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class RitRestService {

	@EJB
	private RitManagementEJBLocal taskRepo;
	
	@EJB
	private UserManagementEJBLocal userRepo;
	
	@Context
	private UriInfo uriInfo;
	
	@Context
	private SecurityContext ctx;

//	@POST
//	@JWTTokenNeeded
//	public Response createRit(Rit task) {
//		if (task == null)
//			throw new BadRequestException();
//		
//		taskRepo.createRit(task);
//		URI taskUri = uriInfo.getAbsolutePathBuilder().path(task.getId().toString()).build();
//		return Response.created(taskUri).build();
//	}

//	@GET
//	@JWTTokenNeeded
//	public Response getRitten() {
//		
//		Principal p = ctx.getUserPrincipal();
//		Profiel person = userRepo.findProfiel(p.getName());
//		
//		List<Rit> tasks = taskRepo.findIncompletedPatientTasks(person);
//		return Response.ok(tasks).build();
//	}
//	
}
