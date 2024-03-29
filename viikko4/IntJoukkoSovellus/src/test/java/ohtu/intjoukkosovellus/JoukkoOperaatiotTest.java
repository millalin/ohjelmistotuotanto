
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {
    @Test
    public void testYhdiste() {
        IntJoukko eka = teeJoukko(1,2);
        IntJoukko toka = teeJoukko(3,4);
        
        IntJoukko tulos = IntJoukko.kahdenJoukonYhdiste(eka, toka);
        int[] vastauksenLuvut = tulos.siistiLista();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {1,2,3,4};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    } 

    @Test
    public void testLeikkaus() {
        IntJoukko eka = teeJoukko(1,2);
        IntJoukko toka = teeJoukko(2,3,4);
        
        IntJoukko tulos = IntJoukko.kahdenJoukonLeikkaus(eka, toka);
        int[] vastauksenLuvut = tulos.siistiLista();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {2};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    }     
    
    @Test
    public void testErotus() {
        IntJoukko eka = teeJoukko(1,2,5,6);
        IntJoukko toka = teeJoukko(2,3,4);
        
        IntJoukko tulos = IntJoukko.kahdenJoukonErotus(eka, toka);
        int[] vastauksenLuvut = tulos.siistiLista();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {1, 5, 6};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    }  

    private IntJoukko teeJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();
        
        for (int luku : luvut) {
            joukko.lisaa(luku);
        }
        
        return joukko;
    }
}
