package app.service;

import app.info.FieldInfo;
import app.info.UserInfo;
import app.model.User;
import org.springframework.data.domain.Page;

public interface UserService {

    User findByEmail(String email);

    Page<UserInfo> paginate(UserInfo userInfo);

}

