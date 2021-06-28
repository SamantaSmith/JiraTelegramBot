package com.example.jiratelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class JiraTelegramBot extends TelegramLongPollingBot {

    public static final String TOKEN = "1805537410:AAF1OBqFPbpn4DtK-CoCTsm8gIQ5AEm_hPs";
    public static final String USERNAME = "@jira_telega_bot";


    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
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
