package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Review;

@Local
public interface ReviewManagementEJBLocal {
	public void createReview(Review r);
}
