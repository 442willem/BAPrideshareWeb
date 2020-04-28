package be.kuleuven.gent.project.ejb;

import java.util.List;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.Review;

@Local
public interface ReviewManagementEJBLocal {
	public void createReview(Review r);
	public List<Review> findReviewsDriver(int id);
	public List<Review> findReviewsPassenger(int id);
}
