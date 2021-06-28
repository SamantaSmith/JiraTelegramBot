package com.example.jiratelegram.config;

import com.example.jiratelegram.JiraTelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Configuration
public class ApplicationConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi() {
        ApiContextInitializer.init();
        return new TelegramBotsApi();
    }

    @Bean
    public JiraTelegramBot jiraTelegramBot (
            TelegramBotsApi telegramBotsApi,
            @Value("${app.bot.token}") String token,
            @Value("${app.bot.username}") String userName) throws TelegramApiRequestException {
        JiraTelegramBot jiraTelegramBot = new JiraTelegramBot(token, userName);
        telegramBotsApi.registerBot(jiraTelegramBot);
        return jiraTelegramBot;
    }
}
