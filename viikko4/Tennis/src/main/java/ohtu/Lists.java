/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author milla
 */
public class Lists {
    
     public String scoreEven(int score) {
        List <String>list = new ArrayList();
        list.addAll( Arrays.asList("Love-All", "Fifteen-All", "Thirty-All", "Forty-All", "Deuce" ) );
        return list.get(score);
    }
     public String score(int score) {
        List <String>list = new ArrayList();
        list.addAll( Arrays.asList("Love", "Fifteen", "Thirty", "Forty") );
        return list.get(score);
    }
    
     public String scoreOver(int res) {
           if (res == 1) {
                return "Advantage player1";
            } else if (res == -1) {
                return "Advantage player2";
            } else if (res >= 2) {
                return "Win for player1";
            } else {
                return "Win for player2";
            }
     }
}
