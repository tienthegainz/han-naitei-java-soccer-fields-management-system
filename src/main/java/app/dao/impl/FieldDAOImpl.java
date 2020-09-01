package app.dao.impl;

import app.dao.FieldDAO;
import app.dao.GenericDAO;
import app.model.Field;
import org.hibernate.SessionFactory;

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

    @Override
    public List<Field> searchFields(String key) {
        return getSession().createQuery("FROM Field WHERE name like :key").setParameter("key", "%"+key+"%").getResultList();
    }

    @Override
    public List<Field> loadFields() {
        return getSession().createQuery("FROM Field").getResultList();
    }
}
