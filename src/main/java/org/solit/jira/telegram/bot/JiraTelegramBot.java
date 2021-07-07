package org.solit.jira.telegram.bot;

import org.aspectj.apache.bcel.classfile.Unknown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solit.jira.telegram.bot.command.*;
import org.solit.jira.telegram.entity.Leader;
import org.solit.jira.telegram.entity.Team;
import org.solit.jira.telegram.service.LeaderService;
import org.solit.jira.telegram.service.TeamService;
import org.solit.jira.telegram.service_impl.LeaderServiceImpl;
import org.solit.jira.telegram.service_impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


public class JiraTelegramBot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(JiraTelegramBot.class);

    private final String token;
    private final String username;
    private IBotCommand prevBotCommand;

    @Autowired
    private LeaderService leaderService;

    @Autowired
    private TeamService teamService;

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


        if (update.getMessage() == null || !update.getMessage().hasText()) {
            return;
        }


        long chatId = update.getMessage().getChatId();
        if(!loginLeaderChecker(update)) {
            sendTelegramMessage(chatId, "Здесь водятся драконы");
            return;
        }

        try {
            if (prevBotCommand != null) {
                prevBotCommand = prevBotCommand.apply(this, update);
                prevBotCommand = null;
                return;
            }

            String message = update.getMessage().getText();
            IBotCommand command = getCommand(update, message);

            prevBotCommand = command.apply(this, update);

        } catch (Exception e) {
            LOGGER.error("Ошибка при выполнении команды", e);
        }
    }

    private IBotCommand getCommand(Update update, String message) {
        switch (message) {
            case "/add_team":
                return new StartInputTeamNameCommand(update.getMessage().getChatId(), leaderService, teamService);
            case "/help":
                return new HelpCommand(update.getMessage().getChatId());
            case "/show_teams":
                Leader lead = findLeaderByNickname(update);
                return new ShowTeamsCommand(update.getMessage().getChatId(), lead.getTeams());
            case "/add_employee":
                return new StartInputEmployeeNameCommand(update.getMessage().getChatId(), leaderService, teamService);
            case "/delete_team":
                return new StartDeleteTeamCommand(update.getMessage().getChatId(), leaderService, teamService);

            default:
                return new UnknownCommand(update.getMessage().getChatId());
        }
    }


    public boolean loginLeaderChecker(Update update) {
        String nickname = update.getMessage().getFrom().getUserName();
        List<Leader> leaderList = leaderService.getLeaders();
        boolean flag = false;
        for (Leader l: leaderList) {
            if (l.getLogin().equals(nickname)){
                flag = true;
            }
        }
        return flag;
    }

    public Leader findLeaderByNickname(Update update) {
        String nickname = update.getMessage().getFrom().getUserName();
        List<Leader> leaderList = leaderService.getLeaders();
        boolean flag = false;
        for (Leader l: leaderList) {
            if (l.getLogin().equals(nickname)){
                return l;
            }
        }
        return null;
    }

    private void sendTelegramMessage(long chatId, String message) {
        try {
            execute(new SendMessage(String.valueOf(chatId), message));
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка при отправке telegram-сообщения", e);
        }
    }
}
