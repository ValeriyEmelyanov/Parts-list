package emelyanov.partslist.dao;

import emelyanov.partslist.model.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Отвечает за доступ к данным (работа с базой данных).
 */
@Repository
public class PartDAOImpl implements PartDAO {
    public static final int PAGE_RECORD_COUNT = 10;
    private SessionFactory sessionFactory;
    private final static Logger logger = LoggerFactory.getLogger(PartDAOImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Возвращает список деталей для вывода на страницу.
     * @param page номер странцы
     * @param filter фильтр
     * @param searchName строка поиска
     * @return список деталей
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Part> partsList(int page, PartFilter filter, String searchName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Part> query = session.createQuery(filter.getSqlList());
        if (filter == PartFilter.NAME_SEARCH) {
            query.setParameter("nameParam", "%" + searchName + "%");
        }
        logger.info("Getting part list. Page: {}", page);
        return query
                .setFirstResult(PAGE_RECORD_COUNT * (page - 1))
                .setMaxResults(PAGE_RECORD_COUNT)
                .list();
    }

    /**
     * Добавляет новую деталь в базу данных.
     * @param part деталь
     */
    @Override
    public void add(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(part);
        logger.info("Part is added. Details: {}", part);
    }

    /**
     * Изменяет существующую деталь в базе данных.
     * @param part деталь
     */
    @Override
    public void update(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
        logger.info("Part is updated. Details: {}", part);
    }

    /**
     * Удаляет деталь из базы данных.
     * @param part деталь
     */
    @Override
    public void delete(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(part);
        logger.info("Part id removed. Details: {}", part);
    }

    /**
     * Получает деталь по идентификатору.
     * @param id идентификатор
     * @return деталь
     */
    @Override
    public Part getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Part part = session.get(Part.class, id);
        logger.info("Getting part by id: {}. Details: {}", id, part);
        return part;
    }

    /**
     * Возвращает количество строк списка в соотвествии с установленными фильтрами.
     * @param filter фильтр
     * @param searchName строка поиска
     * @return количество строк списка
     */
    @Override
    public int size(PartFilter filter, String searchName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Number> query = session.createQuery(filter.getSqlCount(), Number.class);
        if (filter == PartFilter.NAME_SEARCH) {
            query.setParameter("nameParam", "%" + searchName + "%");
        }
        int size = query
                .getSingleResult()
                .intValue();
        logger.info("Getting list size: {}. Filter: {} {}", size, filter,
                ((searchName != null && !searchName.isEmpty()) ? (". Search: " + searchName) : ""));
        return size;
    }

    /**
     * Возвращает количество компьютеров, которые можно собрать из имеющихся деталей.
     * @return возможное количество компьютеров
     */
    @Override
    public int ability() {
        Session session = sessionFactory.getCurrentSession();
        int ability = session
                .createQuery("select min(quantity) from Part where essential = true", Number.class)
                .getSingleResult()
                .intValue();
        logger.info("Getting ability: {}", ability);
        return ability;
    }
}
