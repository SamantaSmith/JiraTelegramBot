package org.solit.jira.telegram.repository;

import org.solit.jira.telegram.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository <Team, Long> {
}
