package emelyanov.partslist.service;

import emelyanov.partslist.dao.PartDAO;
import emelyanov.partslist.dao.PartFilter;
import emelyanov.partslist.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервисный слой - посредник между контроллером и моделью.
 */
@Service
public class PartServiceImpl implements PartService {
    private PartDAO partDAO;

    @Autowired
    public void setPartDAO(PartDAO partDAO) {
        this.partDAO = partDAO;
    }

    /**
     * Возвращает список деталей для вывода на страницу.
     * @param page номер странцы
     * @param filter фильтр
     * @param searchName строка поиска
     * @return список деталей
     */
    @Override
    @Transactional
    public List<Part> partsList(int page, PartFilter filter, String searchName) {
        return partDAO.partsList(page, filter, searchName);
    }

    /**
     * Добавляет новую деталь.
     * @param part деталь
     */
    @Override
    @Transactional
    public void add(Part part) {
        partDAO.add(part);
    }

    /**
     * Изменяет существующую деталь.
     * @param part деталь
     */
    @Override
    @Transactional
    public void update(Part part) {
        partDAO.update(part);
    }

    /**
     * Удаляет деталь.
     * @param part деталь
     */
    @Override
    @Transactional
    public void delete(Part part) {
        partDAO.delete(part);
    }

    /**
     * Получает деталь по идентификатору.
     * @param id идентификатор
     * @return деталь
     */
    @Override
    @Transactional
    public Part getById(int id) {
        return partDAO.getById(id);
    }

    /**
     * Возвращает количество строк списка в соотвествии с установленными фильтрами.
     * @param filter фильтр
     * @param searchName строка поиска
     * @return количество строк списка
     */
    @Override
    @Transactional
    public int size(PartFilter filter, String searchName) {
        return partDAO.size(filter, searchName);
    }

    /**
     * Возвращает количество компьютеров, которые можно собрать из имеющихся деталей.
     * @return возможное количество компьютеров
     */
    @Override
    @Transactional
    public int ability() {
        return partDAO.ability();
    }
}
