package app.dao;

import app.model.Field;
import app.model.Review;

import java.util.List;

public interface ReviewDAO extends BaseDAO<Integer, Review> {

    List<Review> loadReviews(Field field);

    List<Review> loadReviews(Field field, List<Integer> userIds);

    List<Review> loadReviewsExcept(Field field, List<Integer> userIds);

    Double averageRatingByFieldId(int id);

    Long sumReviewByFieldId(int id);

    List<Object[]> countReviewGroupByRating(int id);
}
