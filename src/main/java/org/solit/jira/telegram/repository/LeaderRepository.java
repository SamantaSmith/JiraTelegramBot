package org.solit.jira.telegram.repository;


import org.solit.jira.telegram.entity.Leader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderRepository extends JpaRepository<Leader, Long> {


}
