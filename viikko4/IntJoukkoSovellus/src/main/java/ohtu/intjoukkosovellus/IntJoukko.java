
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5; // aloitustalukon koko

    private int kasvatuskoko = 5;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukkoLista;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        joukkoLista = new int[KAPASITEETTI];
        alkioidenLkm = 0;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        joukkoLista = new int[kapasiteetti];
        alkioidenLkm = 0;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kasvatuskoko < 0 || kapasiteetti < 0) {
            throw new RuntimeException();
        }

        joukkoLista = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {

        if (!sisaltaa(luku)) {
            joukkoLista[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % joukkoLista.length == 0) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    public boolean sisaltaa(int luku) {

        if (Arrays.stream(joukkoLista)
                .filter(l -> l == luku)
                .findFirst().isPresent()) {
            return true;
        }
        return false;

    }

    public boolean poista(int luku) {

        if (sisaltaa(luku)) {

            this.joukkoLista = Arrays.stream(joukkoLista)
                    .filter(lukuListalla -> lukuListalla != luku)
                    .toArray();
            alkioidenLkm--;
            return true;
        }

        return false;
    }

    private void kasvataTaulukkoa() {
        int[] vanhaLista = joukkoLista;

        joukkoLista = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(vanhaLista, joukkoLista);
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {

        int[] vastaus = this.siistiLista();
        String joukko = Arrays.toString(vastaus);
        joukko = joukko.replace("[", "{");
        joukko = joukko.replace("]", "}");
        return joukko;//
    }

    public int[] siistiLista() {
        int[] siistittyJoukkolista = new int[alkioidenLkm];
        for (int i = 0; i < siistittyJoukkolista.length; i++) {
            siistittyJoukkolista[i] = joukkoLista[i];
        }
        return siistittyJoukkolista;
    }

    public static IntJoukko kahdenJoukonYhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();

        Arrays.stream(a.siistiLista())
                .forEach(luku -> uusiJoukko.lisaa(luku));
        Arrays.stream(b.siistiLista())
                .forEach(luku -> uusiJoukko.lisaa(luku));

        return uusiJoukko;
    }

    public static IntJoukko kahdenJoukonLeikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        Arrays.stream(a.siistiLista())
                .forEach(luku -> uusiJoukko.lisaa(luku));
        Arrays.stream(uusiJoukko.siistiLista()).filter(luku -> !b.sisaltaa(luku))
                .forEach(luku -> uusiJoukko.poista(luku));

        return uusiJoukko;

    }

    public static IntJoukko kahdenJoukonErotus(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        Arrays.stream(a.siistiLista())
                .forEach(luku -> uusiJoukko.lisaa(luku));
        Arrays.stream(b.siistiLista()).
                forEach(luku -> uusiJoukko.poista(luku));

        return uusiJoukko;
    }

}
