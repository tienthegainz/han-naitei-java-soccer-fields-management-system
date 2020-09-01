package app.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "fields")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "image")
    private String image;

    @Column(name = "area")
    private Integer area;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private FieldType fieldType;

    public Field() {
    }

    public Field(int id, String name, String address, Integer area, FieldType fieldType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.area = area;
        this.fieldType = fieldType;
    }

    public Field(String name, String address, Integer area, FieldType fieldType) {
        this.name = name;
        this.address = address;
        this.area = area;
        this.fieldType = fieldType;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "field")
    private List<BookingRequest> bookingRequests;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "field")
    private List<Review> reviews;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "field")
    private List<Rating> ratings;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Date getCreateAt() {
        return createdAt;
    }

    public Date getUpdateAt() {
        return updatedAt;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                ", area=" + area +
                ", price=" + price +
                ", fieldType=" + fieldType.toString() +
                '}';
    }
}
