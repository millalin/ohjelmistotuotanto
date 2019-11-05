package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface KirjanpitoIO {
    void lisaaTapahtuma(String tapahtuma);

    ArrayList<String> getTapahtumat();
}
