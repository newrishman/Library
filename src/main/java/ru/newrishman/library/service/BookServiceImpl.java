package ru.newrishman.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.newrishman.library.domain.Book;
import ru.newrishman.library.repository.BookRepository;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setRepository(BookRepository repository) {
        this.bookRepository = repository;
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        Book update = bookRepository.findById(book.getId()).orElse(new Book());
        update.setTitle(book.getTitle());
        bookRepository.save(update);
    }

    @Override
    @Transactional
    public void deleteBook(long id) {
        bookRepository.deleteByIdBook(id);
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElse(new Book());
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthors_nameLikeIgnoreCase(author);
    }

    @Override
    @Transactional
    public Book findBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }
}