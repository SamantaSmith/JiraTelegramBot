package org.solit.jira.telegram.bot.command;

import org.solit.jira.telegram.entity.Employee;
import org.solit.jira.telegram.entity.User;
import org.solit.jira.telegram.service.LeaderService;
import org.solit.jira.telegram.service.TeamService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.solit.jira.telegram.JsonParsing;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class CreateNewEmployeeCommand implements IBotCommand {

    private final LeaderService leaderService;
    private final TeamService teamService;
    private final String chatId;

    public CreateNewEmployeeCommand(LeaderService leaderService, TeamService teamService, String chatId) {
        this.leaderService = leaderService;
        this.teamService = teamService;
        this.chatId = chatId;
    }



    @Override
    public IBotCommand apply(TelegramLongPollingBot bot, Update update) throws Exception {

        String messageEmployeeName = update.getMessage().getText();
        String[] fi = messageEmployeeName.split("\\s");

        HashMap<String, String> strstr = JsonParsing.findEmployeeByFi(fi[0], fi[1]);

        Collection<String> list = strstr.values();
        for(Iterator<String> itr = list.iterator(); itr.hasNext();)
        {
            if(Collections.frequency(list, itr.next())>1)
            {
                itr.remove();
            }
        }

        Collection<String> list2 = strstr.values();
        Collection<String> list3 = strstr.keySet();

        bot.execute(new SendMessage(String.valueOf(chatId), String.valueOf(list2)));
        bot.execute(new SendMessage(String.valueOf(chatId), String.valueOf(list3)));

        bot.execute(new SendMessage(String.valueOf(chatId), String.valueOf(strstr)));


        Employee employee = new Employee(list2.toString(), list3.toString());



        return null;
    }
}
