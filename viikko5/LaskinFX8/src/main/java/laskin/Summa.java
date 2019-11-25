/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;

/**
 *
 * @author milla
 */
public class Summa extends Komento {

    private final TextField tuloskentta;
    private final TextField syotekentta;
    private final Button nollaa;
    private final Button undo;
    private final Sovelluslogiikka sovellus;
    private int arvo;

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    public void suorita() {

        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }

        sovellus.plus(arvo);

        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);

        nollausPainikkeenToiminta(laskunTulos);
        undo.disableProperty().set(false);
    }

    public void peru() {

        sovellus.miinus(arvo);
        int laskunTulos = sovellus.tulos();
        tuloskentta.setText("" + laskunTulos);
        undo.disableProperty().set(true);
        nollausPainikkeenToiminta(laskunTulos);

    }

    public void nollausPainikkeenToiminta(int laskunTulos) {
        if (laskunTulos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
    }
}
