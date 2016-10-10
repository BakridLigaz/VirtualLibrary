package com.ligaz.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @OneToOne
    @JoinColumn(name = "book")
    private Book book;

    @Column(name = "content", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] content;

    public Content() {
    }

    public Content(Book book, byte[] content) {
        this.book = book;
        this.content = content;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
