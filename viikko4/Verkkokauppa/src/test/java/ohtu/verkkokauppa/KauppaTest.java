package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;

    @Before
public void setUp() {
    
    //luodaan mock oliot
    pankki = mock(Pankki.class);
    viite = mock(Viitegeneraattori.class);
    varasto = mock(Varasto.class);
}

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        
        // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaTiedoilla() {
        
        // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"),eq(42) ,eq("12345") , anyString(),eq(5));   
        
    }
    
     @Test
    public void kahdenEriOstoksen() {
        
        // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        when(varasto.saldo(2)).thenReturn(5); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"),eq(42) ,eq("12345") , anyString(),eq(7));   
        
    }
    
    @Test
    public void kahdenSamanOstoksenJälkeenTilisiirtoaKutsutaanOikein() {
        
        // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(2)).thenReturn(5); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"),eq(42) ,eq("12345") , anyString(),eq(4));   
        
    }
       @Test
    public void tilisiirtoaKutsutaanOikeinKunLisatataanTuoteJotaTarpeeksiJaTuoteJokaLoppu() {
        
        // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(0); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "sivettikissan kahvipavut", 299));
        when(varasto.saldo(2)).thenReturn(5); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"),eq(42) ,eq("12345") , anyString(),eq(2));   
        
    }
    
         @Test
    public void uudenAsioinninAloitusNollaaEdellisetOstokset() {
        
        // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "sivettikissan kahvipavut", 299));
        when(varasto.saldo(2)).thenReturn(5); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        k.aloitaAsiointi();     
        k.lisaaKoriin(2);
        
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"),eq(42) ,eq("12345") , anyString(),eq(2));   
        
    }
    
    @Test
    public void varmistusEttaKauppaPyytaaUudenViitteen() {

        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "sivettikissan kahvipavut", 299));
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Keijo", "1122");

        verify(viite, times(1)).uusi();
        k.aloitaAsiointi();
        k.lisaaKoriin(2);

        k.tilimaksu("pekka", "12345");
        verify(viite, times(2)).uusi();
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(2));

    }

    @Test
    public void poistaminenOstorkoristaToimii() {

        // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "sivettikissan kahvipavut", 299));
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));
        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.poistaKorista(1);

        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(2));

    }
}
