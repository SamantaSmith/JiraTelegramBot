package org.solit.jira.telegram.bot.command;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UnknownCommand implements IBotCommand {

    private final String chatId;

    public UnknownCommand(Long chatId) {
        this.chatId = String.valueOf(chatId);
    }

    @Override
    public IBotCommand apply(TelegramLongPollingBot bot, Update update) throws TelegramApiException {
        bot.execute(new SendMessage(chatId, "Команда не распознана. Введите /help для получения списка команд"));
        return null;
    }
}
