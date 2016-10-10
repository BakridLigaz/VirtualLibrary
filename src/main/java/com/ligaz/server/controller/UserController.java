package com.ligaz.server.controller;

import com.ligaz.server.entity.Role;
import com.ligaz.server.entity.User;
import com.ligaz.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAll() {
        return service.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getById(@PathVariable("id") long id) {
        return service.getUser(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        service.addUser(user);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
        service.updateUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable("id") long id) {
        service.removeUser(id);
    }

    @RequestMapping(value = "/getByRole", method = RequestMethod.GET)
    public List<User> getUsersByRole(@RequestBody Role role) {
        return service.getUsersByRole(role);
    }
}
