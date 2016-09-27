package com.ligaz.server.service;

import com.ligaz.server.entity.Role;
import com.ligaz.server.entity.User;
import com.ligaz.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUser(long id) {
        return repository.findOne(id);
    }

    @Override
    public void addUser(User user) {
        if(!repository.exists(user.getId())){
            repository.saveAndFlush(user);
        }
    }

    @Override
    public void updateUser(User user) {
        if(repository.exists(user.getId())){
            repository.saveAndFlush(user);
        }
    }

    @Override
    public void removeUser(User user) {
        if(repository.exists(user.getId())){
            repository.delete(user);
        }
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return null;
    }
}
