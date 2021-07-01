package org.solit.jira.telegram.controller;

import org.solit.jira.telegram.service.UserService;
import org.solit.jira.telegram.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping (path = "/api/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserService service;

    /**
     * Создание нового пользователя
     * @param user модель пользователя
     * @return созданного пользователя
     */
    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping("/")
    public List<User> findAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userModel) {
        return service.updateUser(userModel);
    }

    @DeleteMapping("/{id}")
    public Long deleteUser(@PathVariable Long id) {
        return service.deleteUser(id);
    }
}
