package be.kuleuven.gent.project.rest;

import java.net.URI;
import java.security.Principal;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;

import be.kuleuven.gent.project.data.Notificatie;
import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Rit;
import be.kuleuven.gent.project.data.Route;
import be.kuleuven.gent.project.ejb.NotificatieManagementLocal;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;
import be.kuleuven.gent.project.ejb.RitManagementEJBLocal;
//mss route EJB & Rit EJB nodig
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

@Path("notification_service")
@Stateless
@JWTTokenNeeded
public class NotificatieRestService {
	@EJB
	private RitManagementEJBLocal ritRepo;
	
	@EJB
	private UserManagementEJBLocal userRepo;
	
	@EJB
	private ProfielManagementEJBLocal profielRepo;
	
	@EJB
	private NotificatieManagementLocal notificatieRepo;
	
	@Context
	private UriInfo uriInfo;
	
	@Context
	private SecurityContext ctx;
	
	@GET
	@Path("alleNotificaties/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllNotifications(@PathParam("username") String id) {
		try {
		Profiel pro = profielRepo.findProfiel(id);
		List<Notificatie> alleNotificaties = notificatieRepo.findNotificaties(pro.getId());
        return Response.ok().entity(alleNotificaties).build();
        
		}catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}
	

}
