package emelyanov.partslist.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Инициализация приложения
 */
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Регистритует конфигурацию Hibernate.
     * @return массив классов
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{HibernateConfig.class};
    }

    /**
     * Регистрирует WebConfig.
     * @return массив классов
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * Регистрирует адреса.
     * @return массив адресов
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Возвращает фильтр, для предварительной обработки запросов.
     * Решает проблемы с кодировкой.
     * @return массив фильтров
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{characterEncodingFilter};
    }
}
