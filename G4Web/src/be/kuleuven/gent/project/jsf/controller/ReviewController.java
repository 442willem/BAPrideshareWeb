package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import be.kuleuven.gent.project.data.*;
import be.kuleuven.gent.project.ejb.*;
import be.kuleuven.gent.project.ejb.ProfielManagementEJBLocal;

@Named
@ViewScoped
public class ReviewController implements Serializable {
	private static final long serialVersionUID = 6731274724536164355L;
	
	@EJB
	private ProfielManagementEJBLocal profielEJB;

	@EJB
	private UserManagementEJBLocal userEJB;
	
	@EJB
	private ReviewManagementEJBLocal reviewEJB;
	
	@EJB
	private NotificatieManagementLocal notificatieEJB;
	
	private Review review;
	
	@PostConstruct
	public void init() {
		review = new Review();
		review.setOntvanger(new Profiel());
		review.setAfzender(profielEJB.getProfiel());
	}
	
	public Profiel getOntvanger() {
		return review.getOntvanger();
	}
	public void setOntvanger(Profiel ontvanger) {
		this.review.setOntvanger(ontvanger);
	}	
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}

	public String createReview() {
		review.setAfzender(profielEJB.getProfiel());
		reviewEJB.createReview(review);
		Notificatie n = new Notificatie("review");
		n.setProfiel(review.getOntvanger());
		notificatieEJB.createNotificatie(n);
		return "myRoutes.faces?faces-redirect=true";
	}
	public List<Review> findReviewsProfielDriver(){
		return reviewEJB.findReviewsDriver(profielEJB.getProfiel().getId());
	}
	public List<Review> findReviewsProfielPassenger(){
		return reviewEJB.findReviewsPassenger(profielEJB.getProfiel().getId());
	}
	
}
