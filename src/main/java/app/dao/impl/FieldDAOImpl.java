package app.dao.impl;

import app.dao.FieldDAO;
import app.dao.GenericDAO;
import app.model.Field;
import app.util.SearchQueryTemplate;
import org.hibernate.SessionFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class FieldDAOImpl extends GenericDAO<Integer, Field> implements FieldDAO {

    public FieldDAOImpl() {
        super(Field.class);
    }

    public FieldDAOImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public Field findByName(String name) {
        return (Field) getSession().createQuery("FROM Field WHERE name = :name").setParameter("name", name)
                .getSingleResult();
    }

//    @Override
//    public List<Field> searchFields(String key) {
//        return getSession().createQuery("FROM Field WHERE name like :key").setParameter("key", "%"+key+"%").getResultList();
//    }

    @Override
    public Page<Field> searchFields(String key, Pageable pageable) {
        String sql = "FROM Field WHERE name LIKE :key";
        String countSql = "SELECT COUNT(*) FROM Field WHERE name LIKE :key";
        SearchQueryTemplate searchQueryTemplate = new SearchQueryTemplate(sql, countSql, pageable);
        searchQueryTemplate.addParameter("key", "%"+key+"%");
        searchQueryTemplate.addOrder(Sort.Direction.ASC, "name");
        return paginate(searchQueryTemplate);
    }

    @Override
    public List<Field> loadFields() {
        return getSession().createQuery("FROM Field").getResultList();
    }

    @Override
    public Page<Field> paginateField(Pageable pageable) {
        String sql = "FROM Field";
        String countSql = "SELECT COUNT(*) FROM Field";
        SearchQueryTemplate searchQueryTemplate = new SearchQueryTemplate(sql, countSql, pageable);
        searchQueryTemplate.addOrder(Sort.Direction.ASC, "name");
        return paginate(searchQueryTemplate);
    }
}
