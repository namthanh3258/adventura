package logika;
/**
 *  Třída PrikazSeber implementuje pro hru příkaz info.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Thanh Nam Nguyen
 *@version    01.06.2019
 */
public class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private final HerniPlan herniPlan;
    private Hra hra;
    /**
     *  Konstruktor třídy
     */
    public PrikazSeber(HerniPlan herniPlan, Hra hra){
        this.herniPlan = herniPlan;
        this.hra = hra;
    }
    /**
     *  Metoda, které provádí příkaz seber. Vrací zda byl předmět úspěšně sebrán čí ne
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0){
            return "Nevim co mam sebrat.";
        }
        String nazevVeci = parametry[0];


        if (nazevVeci.equals("princezna") && herniPlan.getBatoh().obsahujeVec("klic")){
            hra.setKonecHry(true);
            return "Povedlo se ti zachránit princeznu ze spáru Gul'Dana a bezpečně dopravit zpět do království.\n";
        } else if (nazevVeci.equals("princezna") && !herniPlan.getBatoh().obsahujeVec("klic")){
            return "Musíš nejprve najít klíč od princeziny cely, aby si ji mohl zachránit.";
        }

        if(!herniPlan.getAktualniProstor().vecJeVProstoru(nazevVeci)){
            return "Vec s nazvem " + nazevVeci + " se v prostoru nenachazi";
        }
       // System.out.println("trash1");
        Vec vec = herniPlan.getAktualniProstor().vyberVec(nazevVeci);
        Vec vec2 = herniPlan.getAktualniProstor().getVeci().get(nazevVeci);

        if (vec2.isPrenositelna() != true){
            return "Tuto vec neni mozne prenest!";

        } else if (herniPlan.getAktualniProstor().nejakeNpcVProstor() == false || herniPlan.getAktualniProstor().npcJeVProstoru("kral")||
                herniPlan.getAktualniProstor().npcJeVProstoru("princezna")) {

            if (herniPlan.getBatoh().velikostBatohu() < herniPlan.getBatoh().getMAXKAPACITA()) {
                herniPlan.getBatoh().pridejVec(vec);
                herniPlan.getAktualniProstor().odstranVec(nazevVeci);

                return "Sebral jsi vec - " + nazevVeci + ". " + "Aktualni pocet predmetu v batohu: " + herniPlan.getBatoh().velikostBatohu() + "/" + herniPlan.getBatoh().getMAXKAPACITA();
            }
            return "Batoh je plny.";
        } else {
            return "V prostoru se stále nacházi nějaký nepřítel, aby si mohl předmet bezpecně sebrat, musíš ho nejdříve porazit";
        }
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
