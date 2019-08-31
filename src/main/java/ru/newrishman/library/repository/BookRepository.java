package ru.newrishman.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.newrishman.library.domain.Book;

import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
    @Transactional
    List<Book> findByAuthors_nameLikeIgnoreCase(String author);

    @Transactional
    Book findByTitleIgnoreCase(String title);

    @Modifying
    @Transactional
    @Query(value = "delete from author_book where id_book = :id", nativeQuery = true)
    void deleteByIdBook(@Param("id") long id);
}