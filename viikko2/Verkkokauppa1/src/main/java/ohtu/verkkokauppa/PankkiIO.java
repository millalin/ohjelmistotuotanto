package ohtu.verkkokauppa;

public interface PankkiIO {


    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}
