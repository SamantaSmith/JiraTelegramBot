package org.solit.jira.telegram.bot.command;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand implements IBotCommand{

    private final String chatId;
    String commandList = "/add_team - добавить команду\n/add_employee - добавить работника в команду\n/show_teams - показать список команд";

    public HelpCommand(Long chatId) {
        this.chatId = String.valueOf(chatId);
    }

    @Override
    public IBotCommand apply(TelegramLongPollingBot bot, Update update) throws TelegramApiException {
        bot.execute(new SendMessage(chatId, commandList));
        return null;
    }

}
