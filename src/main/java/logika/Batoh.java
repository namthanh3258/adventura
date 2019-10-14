package logika;

import java.util.HashMap;
import java.util.Map;
/**
 * Třída Batoh vytváří batoh, do kterého se vkládají jednotlivé přenositelné
 * předměty.
 *@author     Thanh Nam Nguyen
 *@version    01.06.2019
 */
public class Batoh {

    private final int MAXKAPACITA = 6;
    private Map<String, Vec> batohNaVeci;
    /**
     *  Konstruktor který vytváří batoh.
     */
    public Batoh (){
        batohNaVeci = new HashMap<>();
    }
    /**
     *  Metoda, která vrací obsah batohu.
     */
    public String toString(){
        if(batohNaVeci.size() == 0) {
            return "Batoh je prazdny.";
        }
        String result = "";
        for(String s : batohNaVeci.keySet()) {
            result += s+ ", ";
        }
        return result;
    }
    /**
     *  Metoda, která vrací true/false na dotaz jestli batoh obsahuje dotazovaný předmět
     */
    public boolean obsahujeVec(String nazev) {
        return batohNaVeci.containsKey(nazev);
    }
    /**
     *  Metoda na přidání předmětů do batohu. Vrací true/false
     */
    public boolean pridejVec(Vec vec){
        if (vec.isPrenositelna()) {
            batohNaVeci.put(vec.getNazev(), vec);
            return true;
        }
        return false;
    }
    /**
     *  Metoda vracící počet předmětů v batohu
     */
    public int velikostBatohu(){
        return batohNaVeci.size();
    }
    /**
     *  Metoda vypisující jednotlivé prvky uložene v batohu.
     */
    public String obsahBatohu() {
        String obsah = "Batoh obsahuje: ";
        for (String jmenoVeci : batohNaVeci.keySet()){
            obsah += jmenoVeci + " ";
        }
        return obsah;
    }
    /**
     *  Metoda, která vybere dotazovaný předmět z batohu.
     */
    public Vec vyberPredmet (String nazev){
        Vec vybranyPredmet;
        if (batohNaVeci.containsKey(nazev)){
            vybranyPredmet = batohNaVeci.get(nazev);
            batohNaVeci.remove(nazev);

            return vybranyPredmet;
        }
        return null;
    }
    /**
     *  Metoda vracící maximální kapacitu batohu
     */
    public int getMAXKAPACITA() {
        return MAXKAPACITA;
    }
}
