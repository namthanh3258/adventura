package logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    String predchoziMistnost;
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) { //libovolny pocet parametru typu String


        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else if (!plan.getAktualniProstor().nejakeNpcVProstor() || smer.equals(predchoziMistnost) ||
                plan.getAktualniProstor().npcJeVProstoru("kral")){

            predchoziMistnost = plan.getAktualniProstor().getNazev();

            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        } /*else if (smer.equals("zalar") && plan.getBatoh().obsahujeVec("klic")){
            predchoziMistnost = plan.getAktualniProstor().getNazev();

            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        } else if (smer.equals("zalar") && !plan.getBatoh().obsahujeVec("klic")){

            return "Žalář je zamčená, musíš nejprve sehnat klíč.";
        } */
        else {
            return "V oblasti se stale nachazi nejake NPC, abys kolem nich mohl projit, musis ho porazit.";
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
