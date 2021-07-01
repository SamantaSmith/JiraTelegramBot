package org.solit.jira.telegram.repository;

import org.solit.jira.telegram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
