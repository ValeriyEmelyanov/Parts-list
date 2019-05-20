package emelyanov.partslist.service;

import emelyanov.partslist.dao.PartFilter;
import emelyanov.partslist.model.Part;

import java.util.List;

public interface PartService {
    List<Part> partsList(int page, PartFilter filter, String searchName);
    void add(Part part);
    void update(Part part);
    void delete(Part part);
    Part getById(int id);
    int size(PartFilter filter, String searchName);
    int ability();
}
