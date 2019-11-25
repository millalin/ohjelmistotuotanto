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
public class Nollaa extends Komento {

    private final TextField tuloskentta;
    private final TextField syotekentta;
    private final Button nollaa;
    private final Button undo;
    private final Sovelluslogiikka sovellus;
    private int edellinenTulos;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    public void suorita() {

        try {
            edellinenTulos = Integer.parseInt(tuloskentta.getText());
        } catch (Exception e) {
        }
        sovellus.nollaa();

        syotekentta.setText("");
        tuloskentta.setText("0");

        nollaa.disableProperty().set(true);

        undo.disableProperty().set(false);
    }

    public void peru() {
        tuloskentta.setText("" + edellinenTulos);
        sovellus.plus(edellinenTulos);
        undo.disableProperty().set(true);
    }

}
