package com.ligaz.server.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.type.ImageType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private long id;

    @Column(name = "publish_date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date publish_date;

    @Column(name = "count")
    private int count;

    @Column(name = "description",nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre")
    private Genre genre;

    @Column(name="image_path",nullable = false)
    private String imagePath;

    @Column(name = "file_path",nullable = false)
    private String filePath;

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
