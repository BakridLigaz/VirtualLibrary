package com.ligaz.server.repository;

import com.ligaz.server.entity.Author;
import com.ligaz.server.entity.Book;
import com.ligaz.server.entity.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findBooksByAuthor(Author author, Pageable pageable);

    List<Book> findBooksByAuthor(Author author);

    List<Book> findBooksByGenre(Genre genre);

    List<Book> findBooksByGenre(Genre genre, Pageable pageable);

    Book findBooksByName(String name);

    @Query(value = "select b from book b order by rand()")
    List<Book> findBooksByRandom(Pageable pageable);

    @Query(value = "SELECT b FROM book b WHERE b.name like ?1")
    List<Book> findBooksBySearchQuery(String query);

    @Query(value = "SELECT b FROM book b WHERE b.name like ?1")
    List<Book> findBooksBySearchQuery(String query,Pageable pageable);
}
