package emelyanov.partslist.service;

import emelyanov.partslist.config.AppInit;
import emelyanov.partslist.config.HibernateConfig;
import emelyanov.partslist.config.WebConfig;
import emelyanov.partslist.dao.PartFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

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

    public PartServiceImplTest() {
    }

    @Test
    public void partsList() {
        assertEquals(service.partsList(1, PartFilter.ALL, "").size(), 10);
        assertEquals(service.partsList(2, PartFilter.ALL, "").size(), 2);
    }

    @Test
    public void add() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void size() {
    }

    @Test
    public void ability() {
    }
}