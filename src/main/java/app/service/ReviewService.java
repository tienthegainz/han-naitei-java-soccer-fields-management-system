package app.service;

import app.info.FieldInfo;
import app.info.ReviewInfo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ReviewService {

    ReviewInfo findReview(int id);

    boolean createReview(ReviewInfo fieldType) throws InvocationTargetException, IllegalAccessException;

    List<ReviewInfo> loadReviews(FieldInfo fieldInfo);

    List<ReviewInfo> loadReviews(FieldInfo fieldInfo, List<Integer> userIds);

    ReviewInfo loadCurrentUsersReview(FieldInfo fieldInfo);

    List<ReviewInfo> loadOtherUsersReviews(FieldInfo fieldInfo);
}
