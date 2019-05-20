package emelyanov.partslist.dao;

import emelyanov.partslist.model.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartDAOImpl implements PartDAO {
    private static final int PAGE_RECORD_COUNT = 10;
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Part> partsList(int page, PartFilter filter, String searchName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Part> query = session.createQuery(filter.getSqlList());
        if (filter == PartFilter.NAME_SEARCH) {
            query.setParameter("nameParam", "%" + searchName + "%");
        }
        return query
                .setFirstResult(PAGE_RECORD_COUNT * (page - 1))
                .setMaxResults(PAGE_RECORD_COUNT)
                .list();
    }

    @Override
    public void add(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(part);
    }

    @Override
    public void update(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
    }

    @Override
    public void delete(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(part);
    }

    @Override
    public Part getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Part.class, id);
    }

    @Override
    public int size(PartFilter filter, String searchName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Number> query = session.createQuery(filter.getSqlCount(), Number.class);
        if (filter == PartFilter.NAME_SEARCH) {
            query.setParameter("nameParam", "%" + searchName + "%");
        }
        return query
                .getSingleResult()
                .intValue();
    }

    @Override
    public int ability() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select min(quantity) from Part where essential = true", Number.class)
                .getSingleResult()
                .intValue();
    }
}
