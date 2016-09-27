package com.ligaz.server.service;


import com.ligaz.server.entity.Role;
import com.ligaz.server.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUser(long id);
    public void addUser(User user);
    public void updateUser(User user);
    public void removeUser(User user);
    public List<User> getUsersByRole(Role role);
}
