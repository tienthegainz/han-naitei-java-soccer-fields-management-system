package app.info;

import app.model.Comment;
import app.model.Field;
import app.model.Review;
import app.model.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public class ReviewInfo {

    private Integer id;

    private String content;

    private Integer rating;

    private User user;

    private Field field;

    private List<Comment> comments;

    private Date createdAt;

    private Date updatedAt;

    public ReviewInfo() {
    }

    public ReviewInfo(Review review) {
        this.id = review.getId();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.user = review.getUser();
        this.field = review.getField();
        this.comments = review.getComments();
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Review toReview() throws InvocationTargetException, IllegalAccessException {
        Review review = new Review();

        BeanUtils.copyProperties(review, this);

        return review;
    }
}
