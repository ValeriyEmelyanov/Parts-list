package emelyanov.partslist.service;

import emelyanov.partslist.dao.PartDAO;
import emelyanov.partslist.dao.PartFilter;
import emelyanov.partslist.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    private PartDAO partDAO;

    @Autowired
    public void setPartDAO(PartDAO partDAO) {
        this.partDAO = partDAO;
    }

    @Override
    @Transactional
    public List<Part> partsList(int page, PartFilter filter, String searchName) {
        return partDAO.partsList(page, filter, searchName);
    }

    @Override
    @Transactional
    public void add(Part part) {
        partDAO.add(part);
    }

    @Override
    @Transactional
    public void update(Part part) {
        partDAO.update(part);
    }

    @Override
    @Transactional
    public void delete(Part part) {
        partDAO.delete(part);
    }

    @Override
    @Transactional
    public Part getById(int id) {
        return partDAO.getById(id);
    }

    @Override
    @Transactional
    public int size(PartFilter filter, String searchName) {
        return partDAO.size(filter, searchName);
    }

    @Override
    @Transactional
    public int ability() {
        return partDAO.ability();
    }
}
