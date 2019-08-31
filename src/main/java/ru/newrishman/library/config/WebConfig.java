package ru.newrishman.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.newrishman.library.service.AuthorService;
import ru.newrishman.library.service.AuthorServiceImpl;
import ru.newrishman.library.service.BookService;
import ru.newrishman.library.service.BookServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(value = "ru.newrishman.library")
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public AuthorService authorService() {
        return new AuthorServiceImpl();
    }

    @Bean
    public BookService bookService() {
        return new BookServiceImpl();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
}