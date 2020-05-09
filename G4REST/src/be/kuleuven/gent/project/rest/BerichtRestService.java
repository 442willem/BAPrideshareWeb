package be.kuleuven.gent.project.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import be.kuleuven.gent.project.data.Bericht;
import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.data.Route;
import be.kuleuven.gent.project.data.Tussenstop;
import be.kuleuven.gent.project.ejb.BerichtManagementEJBLocal;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;

@Path("bericht_service")
@Stateless
@JWTTokenNeeded
public class BerichtRestService {
	
	@EJB
	private BerichtManagementEJBLocal berichtRepo;
	
	@EJB
	private ProfielManagementEJBLocal profielRepo;
	
	@Context
	private UriInfo uriInfo;
	
	@Context
	private SecurityContext ctx;
	
	@GET
	@Path("myConversations/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response myConversations(@PathParam("username") String username) {
		try {
			
			Profiel profiel = profielRepo.findProfiel(username);
			
			
			List<Profiel> conversationPartners = berichtRepo.getConversations(profiel.getId());
    		
    		
    		return Response.ok().entity(conversationPartners).build();
    		
    		
    	}catch (Exception e) {
			return Response.serverError().build();
    	}

	}

	@GET
	@Path("myMessages/{owner}/{otherPerson}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response myMessages(@PathParam("owner") String owner,@PathParam("otherPerson") int other) {
		try {
			
			Profiel profiel = profielRepo.findProfiel(owner);

			List<Bericht> messageList=berichtRepo.getConversation(profiel.getId(), other);
						
			List<Bericht> omgekeerdeList=new ArrayList<Bericht>();
			
		
			for(int i=messageList.size()-1;i>0;i--) {
				omgekeerdeList.add(messageList.get(i));
			}

			return Response.ok().entity(omgekeerdeList).build();

		}catch (Exception e) {
			System.out.println(e.toString());
			return Response.serverError().build();
		
		}

	}
	
	@POST
	@Path("createBericht/{username}/{otherPerson}")
	public Response createRoute(@PathParam("username") String username,@PathParam("otherPerson") String otherPerson, InputStream incomingData) {
		
		StringBuilder sb = new StringBuilder();
    	Gson json = new Gson();
    
    	try {
    		BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
    		String line =null;
    	
    		while((line =in.readLine())!=null) {
    			sb.append(line);
    		}
    		
    		Profiel sender =profielRepo.findProfiel(username);
    		Profiel reciever = profielRepo.findProfiel(otherPerson);
    	
    		Bericht message = json.fromJson(sb.toString(), Bericht.class);
    		
    		message.setOntvanger(reciever);
    		message.setZender(sender);
    		Date date=new Date();
    		
    		message.setTimestamp(new Timestamp(date.getTime()));
    		
    		berichtRepo.createBericht(message);
    		
    		
    		return Response.ok().entity(message).build();
    		
    		
    	}catch (Exception e) {
			return Response.serverError().build();
		}
		
	}

}
