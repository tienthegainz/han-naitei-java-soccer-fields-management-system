package app.info;

import app.model.BookingRequest;
import app.model.Field;
import app.model.FieldType;
import app.model.Review;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

public class FieldInfo extends BaseInfo {

    private Integer id;

    private String name;

    private String address;

    private String image;

    private Integer area;

    private Integer price;

    private FieldType fieldType;

    private List<BookingRequest> bookingRequests;

    private List<Review> reviews;

    private Date createdAt;

    private Date updatedAt;

    public FieldInfo() {
    }

    public FieldInfo(Field field) {
        this.id = field.getId();
        this.name = field.getName();
        this.address = field.getAddress();
        this.image = field.getImage();
        this.area = field.getArea();
        this.price = field.getPrice();
        this.fieldType = field.getFieldType();
        this.bookingRequests = field.getBookingRequests();
        this.reviews = field.getReviews();
        this.createdAt = field.getCreatedAt();
        this.updatedAt = field.getUpdatedAt();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public List<BookingRequest> getBookingRequests() {
        return bookingRequests;
    }

    public void setBookingRequests(List<BookingRequest> bookingRequests) {
        this.bookingRequests = bookingRequests;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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

    public Field toField() {
        Field field = new Field();

        BeanUtils.copyProperties(this, field);

        return field;
    }
}


