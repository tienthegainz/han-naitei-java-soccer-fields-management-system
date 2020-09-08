package app.dao;

import app.util.SearchQueryTemplate;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

public abstract class GenericDAO<PK extends Serializable, T> extends HibernateDaoSupport {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> persistentClass;

    public GenericDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    public T findById(Serializable key) {
        return findById(key, false);
    }

    public T findById(Serializable key, boolean lock) {
        if (lock) {
            return (T) getSession().load(getPersistentClass(), key, LockMode.PESSIMISTIC_WRITE);
        } else {
            return (T) getSession().get(getPersistentClass(), key);
        }
    }

    public void persist(T entity) {
        getSession().persist(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public T saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Page<T> paginate(Pageable pageable) {
        String sql = "FROM " + getPersistentClass().getName();
        String countSql = "SELECT COUNT(*) FROM " + getPersistentClass().getName();
        return paginate(new SearchQueryTemplate(sql, countSql, pageable));
    }

    protected Page<T> paginate(SearchQueryTemplate searchQueryTemplate) {
        List<T> results = getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            public List<T> doInHibernate(Session session) {
                Query<T> query = session.createQuery(searchQueryTemplate.getSql(true), getPersistentClass());
                searchQueryTemplate.setPageable(query);
                searchQueryTemplate.setParameters(query);
                return query.list();
            }
        });

        Long count = getHibernateTemplate().execute(new HibernateCallback<Long>() {
            public Long doInHibernate(Session session) {
                Query<Long> query = session.createQuery(searchQueryTemplate.getCountSql(), Long.class);
                searchQueryTemplate.setParameters(query);
                return query.uniqueResult();
            }
        });

        return wrapResult(results, searchQueryTemplate.getPageable(), count);
    }

    private Page<T> wrapResult(List<T> results, Pageable page, long count) {
        if (results == null) {
            results = Collections.emptyList();
        }
        return new PageImpl<>(results, page, count);
    }
}
