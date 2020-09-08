package app.service;

import app.info.UserInfo;
import app.model.User;
import org.springframework.data.domain.Page;

public interface UserService {

    User getCurrentUser();

    boolean create(UserInfo userInfo);

    Page<UserInfo> paginate(UserInfo userInfo);

    User findByEmail(String email);
}

