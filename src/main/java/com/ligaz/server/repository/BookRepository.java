package com.ligaz.server.repository;

import com.ligaz.server.entity.Author;
import com.ligaz.server.entity.Book;
import com.ligaz.server.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findBooksByAuthor(Author author);

    List<Book> findBooksByGenre(Genre genre);

    Book findBooksByName(String name);

    @SuppressWarnings("JpaQlInspection")
    @Query(value = "select b from book b WHERE b.name like :query", nativeQuery = true)
    List<Book> findBooksBySearchQuery(@Param("query") String query);
}
