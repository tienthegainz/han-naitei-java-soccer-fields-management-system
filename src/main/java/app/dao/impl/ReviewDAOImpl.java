package app.dao.impl;

import app.dao.GenericDAO;
import app.dao.ReviewDAO;
import app.model.Field;
import app.model.Review;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewDAOImpl extends GenericDAO<Integer, Review> implements ReviewDAO {

    public ReviewDAOImpl() {
        super(Review.class);
    }

    public ReviewDAOImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public List<Review> loadReviews(Field field) {
        return ((Field) getSession().merge(field)).getReviews();
    }

    @Override
    public List<Review> loadReviews(Field field, List<Integer> userIds) {
        return ((Field) getSession().merge(field)).getReviews()
                .stream()
                .filter(review -> userIds.contains(review.getUser().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> loadReviewsExcept(Field field, List<Integer> userIds) {
        return ((Field) getSession().merge(field)).getReviews()
                .stream()
                .filter(review -> !userIds.contains(review.getUser().getId()))
                .collect(Collectors.toList());
    }
}
