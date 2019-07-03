package emelyanov.partslist.service;

import emelyanov.partslist.config.AppInit;
import emelyanov.partslist.config.HibernateConfig;
import emelyanov.partslist.config.WebConfig;
import emelyanov.partslist.dao.PartFilter;
import emelyanov.partslist.model.Part;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

import static emelyanov.partslist.service.PartServiceTestData.*;
import static emelyanov.partslist.dao.PartDAOImpl.PAGE_RECORD_COUNT;

@ContextConfiguration(classes = {AppInit.class, HibernateConfig.class, WebConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Sql(scripts = "classpath:init_test.sql", config = @SqlConfig(encoding = "UTF-8"))
public class PartServiceImplTest {

    private PartService service;

    @Autowired
    public void setService(PartService service) {
        this.service = service;
    }

    @Test
    public void partsList() {
        assertEquals(PAGE_RECORD_COUNT, service.partsList(PAGE_1, PartFilter.ALL, "").size());
        assertEquals(SIZE_ALL - PAGE_RECORD_COUNT, service.partsList(PAGE_2, PartFilter.ALL, "").size());
        assertEquals(SIZE_ESSENTIAL, service.partsList(PAGE_1, PartFilter.ESSENTIAL, "").size());
        assertEquals(SIZE_OPTIONAL, service.partsList(PAGE_1, PartFilter.OPTIONAL, "").size());
    }

    @Test
    public void add() {
        final int NEW_ID = SIZE_ALL + 1;
        Part part = new Part();
        part.setName("Новая деталь");
        part.setEssential(false);
        part.setQuantity(7);
        service.add(part);
        part.setId(NEW_ID);
        assertEquals(part.toString(), service.getById(NEW_ID).toString());
    }

    @Test
    public void update() {
        final String NEW_NAME = "Супер деталь";
        Part part = service.getById(ID_1);
        part.setName(NEW_NAME);
        service.update(part);
        assertEquals(part.toString(), service.getById(ID_1).toString());
    }

    @Test
    public void delete() {
        service.delete(service.getById(ID_1));
        assertEquals(SIZE_ALL - 1, service.size(PartFilter.ALL, ""));
    }

    @Test
    public void getById() {
        Part actual = service.getById(ID_1);
        assertEquals(PART_1.toString(), actual.toString());
    }

    @Test
    public void size() {
        assertEquals(SIZE_ALL,         service.size(PartFilter.ALL, ""));
        assertEquals(SIZE_NAME_SEARCH, service.size(PartFilter.NAME_SEARCH, "Деталь 0"));
        assertEquals(SIZE_ESSENTIAL,   service.size(PartFilter.ESSENTIAL, ""));
        assertEquals(SIZE_OPTIONAL,    service.size(PartFilter.OPTIONAL, ""));
    }

    @Test
    public void ability() {
        assertEquals(SIZE_ABILITY, service.ability());
    }
}