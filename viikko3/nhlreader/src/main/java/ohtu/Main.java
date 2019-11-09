package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        Gson mapper = new Gson();
        //Player[] players = mapper.fromJson(bodyText, Player[].class);
        List<Player> players = Arrays.asList(mapper.fromJson(bodyText, Player[].class));
        Collections.sort(players);

        for (int i = 0; i < players.size(); i++) {
          if ( players.get(i).getNationality().equals("FIN")) {
              System.out.println(players.get(i));
          }
            
        }
        
    
    }

}