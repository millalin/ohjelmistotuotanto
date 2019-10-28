package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {

        stats = new Statistics(readerStub);
    }

    @Test
    public void searchedRight() {
        Player semenko = new Player("Semenko", "EDM", 4, 12);
        Player player = stats.search("Semenko");
        assertEquals(semenko.getName(), player.getName());
    }

    @Test
    public void searchReturnsNullWhenNotFound() {

        Player player = stats.search("Milla");
        assertEquals(null, player);
    }

    @Test
    public void returnsRightTeam() {

        List<Player> team = stats.team("PIT");
        int size = team.size();

        ArrayList<Player> p = new ArrayList<>();
        p.add(new Player("Lemieux", "PIT", 45, 54));

        assertEquals(team.get(0).getName(), p.get(0).getName());
        assertEquals(1, size);
    }

    @Test
    public void returnsRightTopscorersListSize() {

        List<Player> top = stats.topScorers(2);
        int size = top.size();
        System.out.println(top);

        assertEquals(2, size);
    }

    @Test
    public void returnsRightTopscorer() {

        List<Player> top = stats.topScorers(1);
        Player player = top.get(0);
        Player topPlayer = new Player("Gretzky", "EDM", 35, 89);

        assertEquals(player.getName(), topPlayer.getName());
    }
}