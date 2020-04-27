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
		return "driver/indexD.faces?faces-redirect=true&login=1&index=0";
	}
	
	public String activatePassenger(){
		
		return "passenger/indexP.faces?faces-redirect=true&login=1";
	}
	
	public String goToLogin() {
		return "driver/indexD.faces?faces-redirect=true&login=1&index=0";
	}
	public String goToCreateAccount() {
		return "createAccount.faces?faces-redirect=true";
	}
	public String goToTest() {
		return "test.faces?faces-redirect=true";
	}
	public String goToIndex() {
		return "index.faces?faces-redirect=true&index=0";
	}
	public String goToCreateRoute() {
		return "createRoute.faces?faces-redirect=true";
	}
	public String goToMyRoutes() {
		return "myRoutes.faces?faces-redirect=true";
	}
	public String goToMyConversations() {
		return "myConversations.faces?faces-redirect=true";
	}
	public String goToViewPassengers(int i){
		return "viewPassengers.faces?faces-redirect=true&login=1&route="+i;
	}
	public String goToIndexD() {
		System.out.println("joooow");
		return "indexD.faces?faces-redirect=true&login=1&index=0";
	}
	

}
