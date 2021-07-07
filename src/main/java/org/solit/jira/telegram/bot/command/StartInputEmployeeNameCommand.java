package org.solit.jira.telegram.bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solit.jira.telegram.service.LeaderService;
import org.solit.jira.telegram.service.TeamService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartInputEmployeeNameCommand implements IBotCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartInputEmployeeNameCommand.class);

    private final String chatId;
    private final LeaderService leaderService;
    private final TeamService teamService;

    public StartInputEmployeeNameCommand(Long chatId, LeaderService leaderService, TeamService teamService) {
        this.chatId = String.valueOf(chatId);
        this.leaderService = leaderService;
        this.teamService = teamService;
    }

    @Override
    public IBotCommand apply(TelegramLongPollingBot bot, Update update) throws TelegramApiException {

        bot.execute(new SendMessage(chatId, "Введите имя и фамилию сотрудника"));
        return new CreateNewEmployeeCommand(leaderService, teamService, chatId);
    }
}
