package org.solit.jira.telegram.bot.command;

import org.solit.jira.telegram.entity.Team;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class ShowTeamsCommand implements IBotCommand{

    private final String chatId;
    private List<Team> teams;

    public ShowTeamsCommand(Long chatId, List<Team> teams) {
        this.chatId = String.valueOf(chatId);
        this.teams = teams;
    }

    @Override
    public IBotCommand apply(TelegramLongPollingBot bot, Update update) throws TelegramApiException {
        bot.execute(new SendMessage(chatId, String.valueOf(teams)));
        return null;
    }
}
