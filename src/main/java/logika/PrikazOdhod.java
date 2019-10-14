package logika;
/**
 *  Třída PrikazOdhod implementuje pro hru příkaz info.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Thanh Nam Nguyen
 *@version    01.06.2019
 */
public class PrikazOdhod implements IPrikaz {

    private static final String NAZEV = "odhod";
    private final HerniPlan herniPlan;
    /**
     * Konstruktor třídy
     */

    public PrikazOdhod(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;

    }

    /**
     * Provádí příkaz odhod, pokud není vybrán předmět vypíše, že metoda neví co odhodit,
     * pokud se odhazuje předmět, který není v batohu, vypíše, že se daný předmět v batohu nenachází,
     * pokud se daný předmět v batohu nachází, dojde k odhození předmětu.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0){
            return "Nevim co mám odhodit.";
        }
        String nazevVeci = parametry[0];
        Vec vec = herniPlan.getBatoh().vyberPredmet(nazevVeci);
        if(vec == null){
            return "Věc s názvem " + nazevVeci + " se v tvém batohu nenachází.";
        }

        herniPlan.getAktualniProstor().pridejVec(vec);
        return "Předmět " + nazevVeci +" si odhodil.";
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }


}
