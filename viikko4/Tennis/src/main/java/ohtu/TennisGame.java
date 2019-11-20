package ohtu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TennisGame {

    private Player player1;
    private Player player2;
    Lists lists = new Lists();

    public TennisGame(String player1Name, String player2Name) {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
    }

    public void wonPoint(String playerName) {

        if (player1.getName().equals(playerName)) {
            player1.addScore();
        } else {
            player2.addScore();
        }
    }

    public String getScore() {

        if (player1.getScore() == player2.getScore()) {
            return lists.scoreEven(player1.getScore());

        } else if (player1.getScore() >= 4 || player2.getScore() >= 4) {
            int minusResult = player1.getScore() - player2.getScore();
            return lists.scoreOver(minusResult);
        } else {

            return lists.score(player1.getScore()) + "-" + lists.score(player2.getScore());

        }

    }
}
