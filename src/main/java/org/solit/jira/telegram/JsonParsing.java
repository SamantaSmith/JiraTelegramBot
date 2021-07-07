package org.solit.jira.telegram;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class JsonParsing {

    public static JSONArray rootJsonParsing() throws Exception {

        URL url = new URL("http://185.244.173.31:3111/api/groups");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();

        String inline = "";

        System.out.println(responseCode);

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponceCode: " + responseCode);
        } else {

            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                inline += sc.nextLine();
            }
            //System.out.println(inline);
            sc.close();
        }

        JSONParser parse = new JSONParser();
        JSONArray jsonArray = (JSONArray)parse.parse(inline);
        return jsonArray;
    }


    public static HashMap <String, String> findEmployeeByFi(String name, String surname) throws Exception {

        HashMap<String, String> idFioMap = new HashMap<>();

        JSONArray jsonArray = rootJsonParsing();

        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject groupJsonObject = (JSONObject) jsonArray.get(i);
            String id = (String)groupJsonObject.get("id");
            String grName = (String)groupJsonObject.get("name");
            //System.out.println(id);
            //System.out.println(name);

            JSONArray peopleJsonArray = (JSONArray) groupJsonObject.get("members");
            System.out.println(peopleJsonArray.size());

            for (int j = 0; j < peopleJsonArray.size(); j++) {

                JSONObject peopleJsonObject = (JSONObject) peopleJsonArray.get(j);
                JSONObject peopleJsonObject1 = (JSONObject) peopleJsonObject.get("user");
                String peopleId = (String)peopleJsonObject1.get("id");
                String peopleFio = (String)peopleJsonObject1.get("fio");
                //System.out.println(peopleId);
                //System.out.println(peopleFio);

                String[] parsedFio = peopleFio.split("\\s");
                System.out.print(parsedFio[0]);
                System.out.print("\t" + parsedFio[1]);

                if (parsedFio[0].equals(surname) && parsedFio[1].equals(name)) {
                    System.out.println(peopleFio);
                    idFioMap.put(peopleId, peopleFio);
                }
            }
        }
        return idFioMap;
    }
}
