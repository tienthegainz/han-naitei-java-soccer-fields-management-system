package app.service;

import app.info.FieldInfo;
import app.info.ReviewInfo;

import java.util.List;

public interface ReviewService {

    ReviewInfo findReview(int id);

    List<ReviewInfo> loadReviews(FieldInfo fieldInfo);

    List<ReviewInfo> loadReviews(FieldInfo fieldInfo, List<Integer> userIds);

    ReviewInfo loadCurrentUsersReview(FieldInfo fieldInfo);

    List<ReviewInfo> loadOtherUsersReviews(FieldInfo fieldInfo);
}
