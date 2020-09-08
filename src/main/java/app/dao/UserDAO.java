package app.dao;

import app.info.UserInfo;
import app.model.Field;
import app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDAO extends BaseDAO<Integer, User> {

    User findByEmail(String email);

    Page<User> paginateUser(Pageable pageable);
}
