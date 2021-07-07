package org.solit.jira.telegram.bot.command;

import org.solit.jira.telegram.entity.Leader;
import org.solit.jira.telegram.entity.Team;
import org.solit.jira.telegram.service.LeaderService;
import org.solit.jira.telegram.service.TeamService;
import org.solit.jira.telegram.service_impl.LeaderServiceImpl;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class CreateNewTeamCommand implements IBotCommand {

    private final LeaderService leaderService;
    private final TeamService teamService;
    private final String chatId;

    public CreateNewTeamCommand(LeaderService leaderService, TeamService teamService, String chatId) {
        this.leaderService = leaderService;
        this.teamService = teamService;
        this.chatId = chatId;
    }

    @Override
    public IBotCommand apply(TelegramLongPollingBot bot, Update update) throws TelegramApiException {

        String messageTeamName = update.getMessage().getText();

        String nickname = update.getMessage().getFrom().getUserName();
        List<Leader> leaderList = leaderService.getLeaders();
        Leader currentLeader = null;
        for (Leader l: leaderList) {
            if (l.getLogin().equals(nickname)) {
                currentLeader = l;
                break;
            }
        }

        Team team = new Team(messageTeamName);
        teamService.saveTeam(team);
        assert currentLeader != null;


        leaderService.updateLeader(leaderService.addTeam(currentLeader.getId(), team.getId()));


        bot.execute(new SendMessage(String.valueOf(chatId), "Добавлена команда " + messageTeamName ));


        return null;
    }
}
