package logika;
/**
 * Třída NPC slouží k inicializaci jednotlivých NPC
 *@author     Thanh Nam Nguyen
 *@version    01.06.2019
 */
public class NPC {

    private String nazev;


    /**
     *Konstruktor, který vytváří NPC
     */
    public NPC(String nazev) {
        this.nazev = nazev;

    }
    /**
     *Metoda, která vrací název NPC
     */
    public String getNazev() {
        return nazev;
    }
    /**
     *Metoda, která nastavuje název NPC
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

}

