/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.Objects;

/**
 *
 * @author milla
 */
public class Player {
    
    private String name;
    private int score;
    
    public Player (String name) {
        this.name = name;
        this.score = 0;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }
    
    
    public void addScore() {
        this.score += 1;
    }
    
    public int getScore() {
        return this.score;
    }
    
}
