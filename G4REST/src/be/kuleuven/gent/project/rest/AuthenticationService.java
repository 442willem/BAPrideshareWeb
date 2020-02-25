package be.kuleuven.gent.project.rest;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/authentication")
public class AuthenticationService {
	
	@EJB
	private UserManagementEJBLocal userEJB;

    @GET
    @Path("/login")
    public Response authenticateUser(@QueryParam("login") String login, @QueryParam("password") String password) {

        try {
            // Authenticate the user using the credentials provided
            Profiel p = authenticate(login, password);
            // Issue a token for the user
            String token = issueToken(p);
            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }

    }
    
    private Profiel authenticate(String login, String password) throws Exception {
    	
    	Profiel person = userEJB.findProfiel(login);
    	
    	MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes("UTF-8"));
		byte[] passwordDigest = md.digest();
		String pHash = DatatypeConverter.printBase64Binary(passwordDigest);
		
		if(person.getPassword().equals(pHash)){										//needed gethPassword uit DB
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
                .setId(person.getLogin())										//needed getLogin en getGroup --> niet zeker omdat je ze alle 2 kan zijn
                .setSubject(person.getGroup())
                .setIssuer("http://localhost:8080/RESTDemo/rest_example/")
                .setIssuedAt(curDate)
                .setExpiration(cal.getTime())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return jwtToken;

    }
}
