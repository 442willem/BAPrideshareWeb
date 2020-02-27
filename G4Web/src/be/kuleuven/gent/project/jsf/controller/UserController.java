package be.kuleuven.gent.project.jsf.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import be.kuleuven.gent.project.data.Profiel;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;
	
@Named
@Stateless
public class UserController implements Serializable{
	private static final long serialVersionUID = 6737147724536164355L;
	
	@EJB
	private UserManagementEJBLocal userEJB;
	
	public String activateDriver(){
		return "driver/indexD.faces?faces-redirect=true&login=1";
	}
	
	public String activatePassenger(){
		return "passenger/indexP.faces?faces-redirect=true&login=1";
	}
	
	public String goToLogin() {
		return "role.faces?faces-redirect=true";
	}
	public String goToCreateAccount() {
		return "createAccount.faces?faces-redirect=true";
	}
	public String goToTest() {
		return "test.faces?faces-redirect=true";
	}
	
	
	public void logout() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
	    try {
			ec.redirect(ec.getRequestContextPath() + "/index.faces");
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	
}
