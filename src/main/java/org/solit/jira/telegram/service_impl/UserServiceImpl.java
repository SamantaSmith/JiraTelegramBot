package org.solit.jira.telegram.service_impl;


import org.solit.jira.telegram.service.UserService;
import org.solit.jira.telegram.entity.User;
import org.solit.jira.telegram.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Long deleteUser(Long id) {
        repository.deleteById(id);
        return id;
    }

    public User updateUser(User user) {
        User currentUser = repository.findById(user.getId()).orElse(null);
        if (currentUser == null) {
            throw new IllegalArgumentException("Пользователь не найден: " + user.getId());
        }
        currentUser.setLogin(user.getLogin());
        return repository.save(currentUser);
    }


}
