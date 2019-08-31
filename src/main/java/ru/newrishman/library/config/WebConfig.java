package ru.newrishman.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.newrishman.library.service.AuthorService;
import ru.newrishman.library.service.AuthorServiceImpl;
import ru.newrishman.library.service.BookService;
import ru.newrishman.library.service.BookServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(value = "ru.newrishman.library")
public class WebConfig implements WebMvcConfigurer {

@Bean
public AuthorService authorService(){
    return new AuthorServiceImpl();
}
@Bean
    public BookService bookService(){
    return new BookServiceImpl();
}
}