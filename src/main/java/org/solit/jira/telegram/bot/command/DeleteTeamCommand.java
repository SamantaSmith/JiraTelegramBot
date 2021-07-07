package org.solit.jira.telegram.bot.command;

import org.solit.jira.telegram.entity.Leader;
import org.solit.jira.telegram.entity.Team;
import org.solit.jira.telegram.service.LeaderService;
import org.solit.jira.telegram.service.TeamService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class DeleteTeamCommand implements IBotCommand {

    private final LeaderService leaderService;
    private final TeamService teamService;
    private final String chatId;

    public DeleteTeamCommand(LeaderService leaderService, TeamService teamService, String chatId) {
        this.leaderService = leaderService;
        this.teamService = teamService;
        this.chatId = chatId;
    }

    @Override
    public IBotCommand apply(TelegramLongPollingBot bot, Update update) throws Exception {
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

        Team team = teamService.getTeamByName(messageTeamName);
        teamService.deleteTeam(team.getId());
        assert currentLeader != null;

        //leaderService.updateLeader(leaderService.addTeam(currentLeader.getId(), team.getId()));


        bot.execute(new SendMessage(String.valueOf(chatId), "Удалена команда " + messageTeamName));

        return null;
    }
}
