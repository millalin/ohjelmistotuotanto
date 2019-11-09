
package ohtu;

public class Player implements Comparable<Player> {
    public String name;
    public String team;
    public int goals;
    public int assists;
    public int penalties;
    public String nationality;
    public String birthday;

    public Player (String name, String team, int goals, int assists,
                   int penalties, String nationality, String birthday) {
        this.name = name;
        this.team = team;
        this.goals = goals;
        this. assists = assists;
        this.penalties = penalties;
        this.nationality = nationality;
        this.birthday = birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return this.goals + this.assists;
    }
    
    public String getNationality() {
        return nationality;
    }

    @Override
    public int compareTo(Player player) {
        return Integer.compare(player.getTotal(), this.getTotal());
    }

    @Override
    public String toString() {
        return name + "  " +team + "  " + goals + " + " + assists + " = " + this.getTotal();
    }


}
