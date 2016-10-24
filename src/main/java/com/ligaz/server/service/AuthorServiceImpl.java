package com.ligaz.server.service;

import com.ligaz.server.entity.Author;
import com.ligaz.server.entity.Book;
import com.ligaz.server.repository.AuthorRepository;
import com.ligaz.server.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository repository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    public Author getAuthor(int id) {
        return repository.findOne(id);
    }

    @Override
    public String addAuthor(Author author) {
        if(!repository.exists(author.getId())){
            repository.saveAndFlush(author);
            return "Автор " + author.getName() + " добавлен";
        }
        return "Автор с id " + author.getId() + " уже существует";
    }

    @Override
    public String updateAuthor(Author author) {
        if(repository.exists(author.getId())){
            repository.saveAndFlush(author);
            return "Автор " + author.getName() + " обновлен";
        }
        return "Автор с id" + author.getId() + " не найден";
    }

    @Override
    public boolean isUniqueName(String name) {
        List<Author> list = repository.findAll();
        for (Author author: list){
            String curAuthorName = author.getName();
            if(curAuthorName.equals(name)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isUsed(int id) {
        Author author = repository.findOne(id);
        List<Book> books = bookRepository.findBooksByAuthor(author);
        if(books.isEmpty()){
            return false;
        }else return true;
    }

    @Override
    public String delete(int id) {
        try {
            Author author = repository.findOne(id);
            repository.delete(id);
            return "Автор "+author.getName()+" успешно удален";
        }catch (Exception ex){
            ex.printStackTrace();
            return "Автор не удален";
        }
    }
}
