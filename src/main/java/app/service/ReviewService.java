package app.service;

import app.info.FieldInfo;
import app.info.ReviewInfo;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReviewService {

    String averageRatingByFieldId(int id);

    Long sumReviewByFieldId(int id);

    ReviewInfo findReview(int id);

    boolean createReview(ReviewInfo fieldType) throws InvocationTargetException, IllegalAccessException;

    List<ReviewInfo> loadReviews(FieldInfo fieldInfo);

    List<ReviewInfo> loadReviews(FieldInfo fieldInfo, List<Integer> userIds);

    ReviewInfo loadCurrentUsersReview(FieldInfo fieldInfo);

    List<ReviewInfo> loadOtherUsersReviews(FieldInfo fieldInfo);

    HashMap<Integer, Long> countReviewGroupByRating(int id);

    boolean deleteFieldType(int id);
}
