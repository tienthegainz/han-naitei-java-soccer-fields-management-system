package app.dao.impl;

import app.dao.GenericDAO;
import app.dao.UserDAO;
import app.info.UserInfo;
import app.model.Field;
import app.model.User;
import app.util.SearchQueryTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {
    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        return (User) getSession().createQuery("FROM User where email = :email")
                .setParameter("email", email).getResultList().stream().findFirst()
                .orElse(null);
    }

    @Override
    public Page<User> paginateUser(Pageable pageable) {
        String sql = "FROM User";
        String countSql = "SELECT COUNT(*) FROM User";
        SearchQueryTemplate searchQueryTemplate = new SearchQueryTemplate(sql, countSql, pageable);
        searchQueryTemplate.addOrder(Sort.Direction.DESC, "created_at");
        return paginate(searchQueryTemplate);
    }

}
