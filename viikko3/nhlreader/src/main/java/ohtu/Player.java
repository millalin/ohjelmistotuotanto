
package ohtu;

public class Player {
    public String name;
    public String team;
    public int goals;
    public int assists;
    public int penalties;
    public String nationality;
    public String birthday;
    

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return name + " Team: " + team + " Goals: "+goals + " Assists: " + assists;
    }
      
}
