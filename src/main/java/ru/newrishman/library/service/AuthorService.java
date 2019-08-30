package ru.newrishman.library.service;

import ru.newrishman.library.domain.Author;

import java.util.List;

public interface AuthorService {

    public Author addAuthor(Author author);

    public void updateAuthor(Author author);

    public void deleteAuthor(long id);

    public Author getAuthorById(long id);

    public List<Author> getAllAuthors();

    public List<Author> findAuthorByBook(String book);

    public Author findAuthorByName(String author);
}
