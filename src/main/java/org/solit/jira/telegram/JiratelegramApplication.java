package org.solit.jira.telegram;

import org.solit.jira.telegram.entity.Team;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JiratelegramApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiratelegramApplication.class, args);

		Team team = new Team("1_Team");
		Team team1 = new Team("2_Team");
		Team team2 = new Team("3_Team");
		Team team3 = new Team("4_Team");


	}

}
