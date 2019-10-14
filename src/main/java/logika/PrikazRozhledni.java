package logika;
/**
 *  Třída PrikazRozhledni implementuje pro hru příkaz info.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Thanh Nam Nguyen
 *@version    01.06.2019
 */
public class PrikazRozhledni implements IPrikaz{

    private static final String NAZEV = "rozhlednout";
    private HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     */
    public PrikazRozhledni(HerniPlan herniPlan){
        this.herniPlan = herniPlan;
    }
    /**
     *  Metoda provádící příkaz rozhlédní, vypíše východy, předměty a npc, které se nacházejí v prostoru
     */
    public String provedPrikaz(String... parametry) { //libovolny pocet parametru typu String

            Prostor aktualniProstor = herniPlan.getAktualniProstor();
            return aktualniProstor.kratkyPopis();

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
