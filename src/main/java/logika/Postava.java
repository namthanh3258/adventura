package logika;
/**
 * Třída, která inicializuje hlavní postavu
 *@author     Thanh Nam Nguyen
 *@version    01.06.2019
 */
public class Postava {

    private int pocetZivotu;

    /**
     *Konstruktor, který vytvoří postavu
     */
    public Postava (){
        this.pocetZivotu = 3;
    }

    /**
     *Metoda vracící aktuálni počet životů
     */
    public int getPocetZivotu() {return pocetZivotu;}

    /**
     *Metoda nastaví nový počet životů
     */

    public void setPocetZivotu(int pocet){
        this.pocetZivotu = pocet;
    }

}
