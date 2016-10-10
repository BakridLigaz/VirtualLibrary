package com.ligaz.server.service;

import com.ligaz.server.entity.Book;
import com.ligaz.server.entity.Content;
import com.ligaz.server.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    ContentRepository repository;

    @Override
    public boolean addContent(Content content) {
        repository.saveAndFlush(content);
        return true;
    }

    @Override
    public boolean deleteContent(Book book) {
        repository.deleteContentByBook(book);
        return true;
    }

    @Override
    public byte[] getContent(Book book) {
        return repository.findContentByBook(book).getContent();
    }
}
