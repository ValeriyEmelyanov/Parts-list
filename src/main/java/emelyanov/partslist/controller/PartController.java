package emelyanov.partslist.controller;

import emelyanov.partslist.dao.PartFilter;
import emelyanov.partslist.model.Part;
import emelyanov.partslist.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartController {
    private PartService partService;
    private int page;
    private PartFilter filter;
    private String searchName;

    @Autowired
    public void setPartService(PartService partService) {
        this.partService = partService;
    }

    /**
     * Возвращает модель-представление главной страницы, на которую выводится список деталей.
     * @param page номер страницы
     * @return модель + предтавление главной сраницы
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView partsList(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "ALL") PartFilter filter,
                                  @RequestParam(defaultValue = "") String searchName) {
        this.page = page;
        this.filter = filter;
        this.searchName = searchName;

        if ((searchName == null || searchName.isEmpty()) && filter == PartFilter.NAME_SEARCH) {
            this.filter = PartFilter.ALL;
        }

        if (filter != PartFilter.NAME_SEARCH) {
            this.searchName = "";
        }

        int partSize = partService.size(filter, searchName);
        int pagesCount = (partSize + 9) / 10;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("partsList");
        modelAndView.addObject("partsList", partService.partsList(page, filter, searchName));
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("page", page);
        modelAndView.addObject("filter", filter);
        modelAndView.addObject("ability", partService.ability());
        modelAndView.addObject("searchName", searchName);
        return modelAndView;
    }

    /**
     * Метод получеает страницу редактирования детали
     * @param id идентификатор детали
     * @return модель+представление - страница редактирования
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Part part = partService.getById(id);
        if (part != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("editPage");
            modelAndView.addObject("part", part);
            return modelAndView;
        }
        return new ModelAndView(redirectUrl());
    }

    /**
     * Метод изменяет деталь.
     * @param part деталь
     * @return модель+представление - перенаправление на главную страницу
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView updatePart(@ModelAttribute("part") Part part){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(redirectUrl());
        partService.update(part);
        return modelAndView;
    }

    /**
     * Метод получает страницу добавления/редактирования детали.
     * @return модель+представление - страница редактирования
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    /**
     * Метод добавляет новую деталь.
     * @param part деталь
     * @return модель+представление - перенаправление на главную страницу
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPart(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(redirectUrl());
        partService.add(part);
        return modelAndView;
    }

    /**
     * Метод удаленяет делаль по идентификатору.
     * @param id иденификато детали
     * @return модель+представление - перенаправление на главную страницу
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePart(@ModelAttribute("id") int id){
        Part part = partService.getById(id);
        if (part != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName(redirectUrl());
            partService.delete(part);
            return modelAndView;
        }
        return new ModelAndView(redirectUrl());
    }

    private String redirectUrl() {
        return String.format("redirect:/?page=%s&filter=%s",
                page,
                filter);
    }
}
