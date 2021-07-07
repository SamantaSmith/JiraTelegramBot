package org.solit.jira.telegram;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.solit.jira.telegram.entity.Team;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

@SpringBootApplication
public class JiratelegramApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(JiratelegramApplication.class, args);




	}
}
