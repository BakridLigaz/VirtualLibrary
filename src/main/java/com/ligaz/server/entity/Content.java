package com.ligaz.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "content")
public class Content implements Serializable {
    @Id
    private Long id;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "content", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] content;

    public Content() {
    }

    public Content(Long id, String bookName, byte[] content) {
        this.id = id;
        this.bookName = bookName;
        this.content = content;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

}
