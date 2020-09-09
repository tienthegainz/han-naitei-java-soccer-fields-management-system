package app.info;

import app.model.User;
import app.validator.FieldMatch;
import app.validator.UniqueEmail;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@FieldMatch(message = "Password not matching", first = "password", second = "confirmPassword")
public class UserInfo extends BaseInfo{

    private Integer id;

    private String username;

    @NotBlank(message = "Password must not blank")
    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20")
    private String password;

    private String confirmPassword;

    @UniqueEmail()
    private String email;

    private String phone;

    private String name;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthday;

    private Date createdAt;

    private String avatar;

    private String facebook_id;

    private String google_id;

    private User.Role role;

    public UserInfo() {
    }

    public UserInfo(Integer id, String username, String password, String confirmPassword, String email,
                    String phone, String name, Date birthday, String avatar,
                    String facebook_id, String google_id, User.Role role, Date createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.birthday = birthday;
        this.avatar = avatar;
        this.facebook_id = facebook_id;
        this.google_id = google_id;
        this.role = role;
        this.createdAt = createdAt;
    }

    public UserInfo(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.name = user.getName();
        this.birthday = user.getBirthday();
        this.avatar = user.getAvatar();
        this.facebook_id = user.getFacebook_id();
        this.google_id = user.getGoogle_id();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean validatePassword(){
        return (this.password.equals(this.confirmPassword));
    }


    public User toUser(){
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
