package app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ratings")
public class Rating implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    private Field field;

    @Column(name = "score")
    private Integer score;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "user=" + user.toString() +
                ", field=" + field.toString() +
                ", score=" + score +
                '}';
    }
}
