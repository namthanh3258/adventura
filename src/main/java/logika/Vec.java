package logika;
/**
 * Třída, která inicializuje předměty
 *@author     Thanh Nam Nguyen
 *@version    01.06.2019
 */
public class Vec {

    private String nazev;
    private final boolean prenositelna;
    /**
     * Konstruktor třídy, zadává se název a přenositelnost
     */
    public Vec (String nazev, boolean prenositelna){
        this.nazev = nazev;
        this.prenositelna = prenositelna;

    }
    /**
     * Metoda vrací název předmětu
     */
    public String getNazev() {
        return nazev;
    }
    /**
     * Metoda nastaví název předmetu
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }
    /**
     * Metoda určující, zda je určity předmět přenositelný
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }
}
