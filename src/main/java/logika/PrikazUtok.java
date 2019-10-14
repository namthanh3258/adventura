package logika;
/**
 *  Třída PrikazUtok implementuje pro hru příkaz info.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Thanh Nam Nguyen
 *@version    01.06.2019
 */

public class PrikazUtok implements IPrikaz {

    private static final String NAZEV = "utok";
    private final HerniPlan herniPlan;
    private Hra hra;

    /**
     *  Konstruktor třídy
     */
    public PrikazUtok(HerniPlan herniPlan, Hra hra) {
        this.herniPlan = herniPlan;

        this.hra = hra;
    }

    /**
     *  Metoda provádící příkaz útok, vypisuje úspěšnou či neúspěšnost útoku
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length == 0){
            return "Nevim na koho zaútočit.";
        }

        String nazevNpc = parametry[0];

        if(!herniPlan.getAktualniProstor().npcJeVProstoru(nazevNpc)){
            return "NPC s názvem " + nazevNpc + " se v prostoru nenáchází.";
        }

        if (herniPlan.getAktualniProstor().npcJeVProstoru("kral")){

            //Hra hra = new Hra();

            hra.setKonecHry(true);
            return "Pokusil ses zabít krále, byl si odsouzen a popraven za velezradu.\n";

            //hra.vratEpilog();
            //System.exit(0);
        } else if (herniPlan.getAktualniProstor().npcJeVProstoru("princezna")){

            hra.setKonecHry(true);
            return "Zabil si priceznu. Prohrál jsi.\n";

        } else if (herniPlan.getAktualniProstor().npcJeVProstoru("bandita") &&
                herniPlan.getBatoh().obsahujeVec("ocelovy_mec")){

            herniPlan.getAktualniProstor().odstranNpc("bandita");
            Vec luk = new Vec("luk", true);
            Vec sipy = new Vec("sipy", true);
            herniPlan.getAktualniProstor().pridejVec(luk);
            herniPlan.getAktualniProstor().pridejVec(sipy);

            return "NPC "+ nazevNpc + " se ti podařilo porazit.";
        } else if (herniPlan.getAktualniProstor().npcJeVProstoru("medved") &&
                herniPlan.getBatoh().obsahujeVec("luk") &&
                herniPlan.getBatoh().obsahujeVec("sipy")){

            herniPlan.getAktualniProstor().odstranNpc("medved");

            return "NPC "+ nazevNpc + " se ti podařilo porazit.";
        }else if(herniPlan.getAktualniProstor().npcJeVProstoru("zly_rytir") &&
                herniPlan.getBatoh().obsahujeVec("ocelovy_mec")){

            herniPlan.getAktualniProstor().odstranNpc("zly_rytir");

            return "NPC "+ nazevNpc + " se ti podařilo porazit.";

        } else if (herniPlan.getAktualniProstor().npcJeVProstoru("utopenec") &&
                herniPlan.getBatoh().obsahujeVec("stribrny_mec")){

            Vec dreveny_kul = new Vec("dreveny_kul",true);

            herniPlan.getAktualniProstor().odstranNpc("utopenec");
            herniPlan.getAktualniProstor().pridejVec(dreveny_kul);

            return "NPC "+ nazevNpc + " se ti podařilo porazit.";
        } else if (herniPlan.getAktualniProstor().npcJeVProstoru("upir") &&
                herniPlan.getBatoh().obsahujeVec("dreveny_kul") &&
                herniPlan.getBatoh().obsahujeVec("cesnek")){

            Vec kniha_se_zaklinadly = new Vec("kniha_se_zaklinadly",true);

            herniPlan.getAktualniProstor().odstranNpc("upir");
            herniPlan.getAktualniProstor().pridejVec(kniha_se_zaklinadly);

            return "NPC "+ nazevNpc + " se ti podařilo porazit.";
        } else if (herniPlan.getAktualniProstor().npcJeVProstoru("vlkodlak") &&
                herniPlan.getBatoh().obsahujeVec("stribrny_mec")){

            herniPlan.getAktualniProstor().odstranNpc("vlkodlak");

            return "NPC "+ nazevNpc + " se ti podařilo porazit.";
        } else if (herniPlan.getAktualniProstor().npcJeVProstoru("zaklety_rytir") &&
                herniPlan.getBatoh().obsahujeVec("kniha_se_zaklinadly")) {

            Vec kouzelne_brneni = new Vec("kouzelne_brneni", true);

            herniPlan.getAktualniProstor().odstranNpc("zaklety_rytir");
            herniPlan.getAktualniProstor().pridejVec(kouzelne_brneni);

            return "NPC "+ nazevNpc + " se ti podařilo porazit.";
        } else if (herniPlan.getAktualniProstor().npcJeVProstoru("Gul_Dan") &&
                herniPlan.getBatoh().obsahujeVec("stribrny_mec") &&
                herniPlan.getBatoh().obsahujeVec("dreveny_kul") &&
                herniPlan.getBatoh().obsahujeVec("cesnek") &&
                herniPlan.getBatoh().obsahujeVec("kouzelna_rostlina") &&
                herniPlan.getBatoh().obsahujeVec("kniha_se_zaklinadly") &&
                herniPlan.getBatoh().obsahujeVec("kouzelne_brneni")){

            Vec klic = new Vec("klic", true);

            herniPlan.getAktualniProstor().odstranNpc("Gul_Dan");
            herniPlan.getAktualniProstor().pridejVec(klic);

            return "Dokázal si porazit si Gul'Dana! Nyní musíš najít princeznu.";
        }

        herniPlan.setHp();
        if(herniPlan.getHp() == 0){

            hra.setKonecHry(true);
            return "Zemřel si během plněni tvého úkolu. Třeba se ti to povede příště.\n";

        }
        return "NPC " + nazevNpc +" se ti nepodařilo porazit, povedlo se ti, ale jen taktak utéct. Tvé aktuální HP je - [" + herniPlan.getHp() + "]";



        //herniPlan.getAktualniProstor().odstranNpc(nazevNpc);

        //return nazevNpc + " se ti podarilo porazit!";


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
