package ru.newrishman.library.service;

import ru.newrishman.library.domain.Book;

import java.util.List;

public interface BookService {

    public Book addBook(Book book);

    public void updateBook(Book book);

    public void deleteBook(long id);

    public Book getBookById(long id);

    public List<Book> getAllBooks();

    public List<Book> findBookByAuthor(String author);

    public Book findBookByTitle(String title);
}
