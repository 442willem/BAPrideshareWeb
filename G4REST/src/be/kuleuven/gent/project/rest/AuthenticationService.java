package be.kuleuven.gent.project.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;



@Path("authentication")
public class AuthenticationService {
	
	@EJB
	private UserManagementEJBLocal userEJB;
	
	@EJB
	private ProfielManagementEJBLocal profielEJB;

    @GET
    @Path("login")
    public Response authenticateUser(@QueryParam("login") String login, @QueryParam("password") String password) {

        try {
   
            // Authenticate the user using the credentials provided
            Profiel p = authenticate(login.trim(), password.trim());
            
            // Issue a token for the user
          
            String token = issueToken(p);
          
            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }

    }
    
    @POST
    @Path("registratie")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(InputStream incomingData) {
    	
    	StringBuilder sb = new StringBuilder();
    	Gson json = new Gson();
    
    	try {
    		BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
    		String line =null;
    	
    		while((line =in.readLine())!=null) {
    			sb.append(line);
    		}
    	
    		
    		Profiel p = json.fromJson(sb.toString(), Profiel.class);
    		p.setGroup("Driver");
    		profielEJB.createProfiel(p);
    		
    		return Response.ok().entity(p).build();
    		
    		
    	}catch (Exception e) {
			return Response.serverError().build();
		}
    
    }
    
 
    
    private Profiel authenticate(String login, String password) throws Exception {
    	
    	Profiel person = userEJB.findProfiel(login);
    	
    	MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes("UTF-8"));
		byte[] passwordDigest = md.digest();
		String pHash = DatatypeConverter.printBase64Binary(passwordDigest);
		
		if(person.getPassword().equals(pHash)){										
			return person;
		}else{
			throw new SecurityException("Invalid user/password");
		}
		          
    }

    private String issueToken(Profiel person) {
    	Date curDate = new Date();
    	Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.DATE, 2);
        
        Key key = ApplicationConfig.JWT_KEY;
        String jwtToken = Jwts.builder()
                .setId(person.getLogin())										
                .setSubject(person.getGroup())
                .setIssuer("http://10.0.2.2:8080/G4REST/restApp/")
                .setIssuedAt(curDate)	
                .setExpiration(cal.getTime())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return jwtToken;

    }
}
