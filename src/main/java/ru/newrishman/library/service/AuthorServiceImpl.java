package ru.newrishman.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.newrishman.library.domain.Author;
import ru.newrishman.library.repository.AuthorRepository;

import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public void setRepository(AuthorRepository repository) {
        this.authorRepository = repository;
    }

    @Override
    @Transactional
    public Author addAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }

    @Override
    @Transactional
    public void updateAuthor(Author author) {
        Author update = authorRepository.getOne(author.getId());
        update.setName(author.getName());
        authorRepository.save(update);
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) {
        authorRepository.deleteByIdAuthor(id);
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Author getAuthorById(long id) {
        return authorRepository.getOne(id);
    }

    @Override
    @Transactional
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public List<Author> findAuthorByBook(String book) {
        return authorRepository.findByBooks_titleLikeIgnoreCase(book);
    }

    @Override
    @Transactional
    public Author findAuthorByName(String author) {
        return authorRepository.findByNameIgnoreCase(author);
    }
}