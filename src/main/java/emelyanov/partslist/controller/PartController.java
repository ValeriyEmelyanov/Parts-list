package emelyanov.partslist.controller;

import emelyanov.partslist.dao.PartFilter;
import emelyanov.partslist.model.Part;
import emelyanov.partslist.service.PartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Контроллер - обрабатывает запросы пользователя,
 * создаёт/изменяет соответствующую модель,
 * передаёт модель для отображения в представление.
 */
@Controller
public class PartController {
    private PartService partService;
    private PartFilter filter;
    private String searchName;
    private final static Logger logger = LoggerFactory.getLogger(PartController.class);

    @Autowired
    public void setPartService(PartService partService) {
        this.partService = partService;
    }

    /**
     * Возвращает модель-представление главной страницы, на которую выводится список деталей.
     *
     * @param page номер страницы
     * @return модель + предтавление главной сраницы
     */
    @GetMapping("/")
    public ModelAndView partsList(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "ALL") PartFilter filter,
            @RequestParam(defaultValue = "") String searchName) {
        logger.info("Main page was requested: page={}, filter={}, searchName={}",
                page, filter, searchName);
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

        if (page < 1) {
            page = 1;
        } else if (page > pagesCount) {
            page = pagesCount;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("partsList");
        modelAndView.addObject("partsList", partService.partsList(page, filter, searchName));
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("page", page);
        modelAndView.addObject("filter", filter);
        modelAndView.addObject("searchName", searchName);
        modelAndView.addObject("ability", partService.ability());
        return modelAndView;
    }

    /**
     * Метод получеает страницу редактирования детали.
     *
     * @param id идентификатор детали
     * @return модель+представление - страница редактирования
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editPage(
            @PathVariable("id") int id,
            @RequestParam(defaultValue = "1", required = false) int page) {
        logger.info("Edit page was requested: id={}", id);
        Part part = partService.getById(id);
        if (part != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("editPage");
            modelAndView.addObject("part", part);
            modelAndView.addObject("page", page);
            modelAndView.addObject("filter", filter);
            modelAndView.addObject("searchName", searchName);
            return modelAndView;
        } else {
            logger.warn("Part is not found: id={}", id);
        }
        return new ModelAndView(redirectUrl(page));
    }

    /**
     * Метод изменяет деталь.
     *
     * @param part деталь
     * @return модель+представление - перенаправление на главную страницу
     */
    @PostMapping("/edit")
    public ModelAndView updatePart(
            @ModelAttribute("part") Part part,
            @RequestParam(defaultValue = "1", required = false) int page) {
        logger.info("Update request: {}", part);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(redirectUrl(page));
        partService.update(part);
        return modelAndView;
    }

    /**
     * Метод получает страницу добавления/редактирования детали.
     *
     * @return модель+представление - страница редактирования
     */
    @GetMapping("/add")
    public ModelAndView addPage(
            @RequestParam(defaultValue = "1", required = false) int page) {
        logger.info("Add page was requested.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("page", page);
        modelAndView.addObject("filter", filter);
        modelAndView.addObject("searchName", searchName);
        return modelAndView;
    }

    /**
     * Метод добавляет новую деталь.
     *
     * @param part деталь
     * @return модель+представление - перенаправление на главную страницу
     */
    @PostMapping("/add")
    public ModelAndView addPart(
            @ModelAttribute("part") Part part,
            @RequestParam(defaultValue = "1", required = false) int page) {
        logger.info("Add request:", part);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(redirectUrl(page));
        partService.add(part);
        return modelAndView;
    }

    /**
     * Метод удаленяет делаль по идентификатору.
     *
     * @param id иденификатор детали
     * @return модель+представление - перенаправление на главную страницу
     */
    @GetMapping("/delete/{id}")
    public ModelAndView deletePart(
            @ModelAttribute("id") int id,
            @RequestParam(defaultValue = "1", required = false) int page) {
        logger.info("Delete request: id={}", id);
        Part part = partService.getById(id);
        if (part != null) {
            partService.delete(part);

            // Чтобы не остаться на пустой странице.
            int partSize = partService.size(filter, searchName);
            int pagesCount = (partSize + 9) / 10;
            page = page > pagesCount ? pagesCount : page;

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName(redirectUrl(page));
            return modelAndView;
        } else {
            logger.warn("Part is not found: id={}", id);
        }
        return new ModelAndView(redirectUrl(page));
    }

    /**
     * Возвращает адрес страницы со списком деталей.
     *
     * @return адрес страницы со списком деталей
     */
    private String redirectUrl(int page) {
        try {
            return String.format("redirect:/?page=%s&filter=%s&searchName=%s",
                    page,
                    filter,
                    URLEncoder.encode(searchName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return "redirect:/";
        }
    }
}
