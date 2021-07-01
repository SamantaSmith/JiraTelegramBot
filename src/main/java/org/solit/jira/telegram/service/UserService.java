package org.solit.jira.telegram.service;

import org.solit.jira.telegram.entity.User;

import java.util.List;

public interface UserService {


    User saveUser(User user);

    /**
     * Возвращает всех пользователей
     * @return список пользователей
     */
    List<User> getUsers();

    User getUserById(Long i);

    Long deleteUser(Long id);

    User updateUser(User user);
}
