package com.ligaz.server.service;

import com.ligaz.server.entity.Book;
import com.ligaz.server.entity.Content;

public interface ContentService {
    public boolean addContent(Content content);

    public boolean deleteContent(Book book);

    public byte[] getContent(Book book);
}
