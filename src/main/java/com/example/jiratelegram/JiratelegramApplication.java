package com.example.jiratelegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class JiratelegramApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiratelegramApplication.class, args);
	}

}
