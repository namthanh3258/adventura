package logika;
/**
 *  Třída PrikazInfo implementuje pro hru příkaz info.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Thanh Nam Nguyen
 *@version    01.06.2019
 */
public class PrikazInfo implements IPrikaz {

    private static final String NAZEV = "info";
    private final HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     */
    public PrikazInfo (HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }
    /**
     *  Provádí příkaz info, vypíše aktuální žívoty a předměty v batohu
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "Tve aktualni HP: " + "[" + herniPlan.getHp() + "]" + "\n" + herniPlan.getBatoh().obsahBatohu();

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
