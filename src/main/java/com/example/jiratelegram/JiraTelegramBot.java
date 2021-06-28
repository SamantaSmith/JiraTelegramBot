package com.example.jiratelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class JiraTelegramBot extends TelegramLongPollingBot {

    private final String token;
    private final String username;

    public JiraTelegramBot(String token, String username) {
        this.token = token;
        this.username = username;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.getMessage()!= null && update.getMessage().hasText()) {

            long chat_id = update.getMessage().getChatId();

            try {

                execute(new SendMessage(String.valueOf(chat_id), "Hello world"));
            } catch (TelegramApiException e) {

                e.printStackTrace();
            }
        }
    }
}
