package logika;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 *
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Thanh Nam Nguyen
 *@version    01.06.2019
 */

public class HerniPlan {
    
    private Prostor aktualniProstor;
    private Postava postava;
    private Batoh batoh;

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */

    public HerniPlan() {
        zalozProstoryHry();
        batoh = new Batoh();
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor kralovstvi = new Prostor("kralovstvi","království, ve kterém žíjes a odkud byla unesena princezna");
        Prostor louka = new Prostor("louka", "zarostlá louka");
        Prostor les = new Prostor("les","obyčejný les, ve kterém se mohou nacházet zvířata");
        Prostor vchodJeskyne = new Prostor("vchod_jeskyne","stará a plesnivá jeskyně, u vchodu leží velký kotlik, ze kterého se kouří");
        Prostor vnitrekJeskyne = new Prostor("vnitrek_jeskyne","vnitřek jeskyně, na zemi leží nějaký blískající se předmět");
        Prostor baziny = new Prostor("baziny","smradlavá bažina, kde se může objevovat utopenec (na utopenec nemusí fungovat obyčejný ocelový meč, či jiné běžné zbraně)");
        Prostor hory = new Prostor("hory", "hory");
        Prostor zchatralyDum = new Prostor("zchatraly_dum", "zchatralý dum v horách, uprostřed domu leží na zemi prázdná rakev");
        Prostor zakletyLes = new Prostor("zaklety_les", "zakletý les, o kterém se říka, že v něm straší");
        Prostor zricenina = new Prostor("zricenina", "zřícenina, o které se tradují všelijaké strašidelné histroky.");
        Prostor temnyHrad = new Prostor("temny_hrad", "temný hrad, ve kterém žije strašlivý Gul'Dan, nachází se zde i princezna");
        Prostor zalar = new Prostor("zalar", "žalář, kde se v jedné z cel nachází princezna");

        // přiřazují se průchody mezi prostory (sousedící prostory)
        kralovstvi.setVychod(louka);
        louka.setVychod(les);
        louka.setVychod(kralovstvi);
        les.setVychod(louka);
        les.setVychod(vchodJeskyne);
        vchodJeskyne.setVychod(vnitrekJeskyne);
        vchodJeskyne.setVychod(les);
        vnitrekJeskyne.setVychod(vchodJeskyne);
        les.setVychod(baziny);
        baziny.setVychod(les);
        baziny.setVychod(hory);
        hory.setVychod(baziny);
        hory.setVychod(zchatralyDum);
        hory.setVychod(zakletyLes);
        zchatralyDum.setVychod(hory);
        zakletyLes.setVychod(hory);
        zakletyLes.setVychod(zricenina);
        zricenina.setVychod(zakletyLes);
        zakletyLes.setVychod(temnyHrad);
        temnyHrad.setVychod(zakletyLes);
        temnyHrad.setVychod(zalar);
        zalar.setVychod(temnyHrad);


        Vec ocelovy_mec = new Vec("ocelovy_mec", true);
        Vec stribrny_mec = new Vec("stribrny_mec", true);
        Vec cesnek = new Vec("cesnek", true);
        Vec velky_kotlik = new Vec("velky_kotlik", false);
        Vec prazdna_rakev = new Vec("prazdna_rakev", false);
        Vec kouzelna_rostlina = new Vec("kouzelna_rostlina", true);
        Vec syrove_maso = new Vec("syrove_maso",true);
        Vec obycejny_ker = new Vec("obecejny_ker", true);
        Vec obrovsky_kamen = new Vec("obrovsky_kamen", false);
        Vec maly_klacik = new Vec("maly_klacik", true);
        Vec maly_nozik = new Vec("maly_nozik",true);
        Vec koste = new Vec("koste", true);

        kralovstvi.pridejVec(ocelovy_mec);
        vnitrekJeskyne.pridejVec(stribrny_mec);
        vchodJeskyne.pridejVec(cesnek);
        vchodJeskyne.pridejVec(velky_kotlik);
        zchatralyDum.pridejVec(prazdna_rakev);
        zakletyLes.pridejVec(kouzelna_rostlina);
        vchodJeskyne.pridejVec(syrove_maso);
        zakletyLes.pridejVec(obycejny_ker);
        zricenina.pridejVec(obrovsky_kamen);
        les.pridejVec(maly_klacik);
        vchodJeskyne.pridejVec(maly_nozik);
        zchatralyDum.pridejVec(koste);



        NPC kral = new NPC("kral");
        NPC Gul_Dan = new NPC("Gul_Dan");
        NPC bandita = new NPC("bandita");
        NPC medved = new NPC("medved");
        NPC utopenec = new NPC("utopenec");
        NPC zly_rytir = new NPC("zly_rytir");
        NPC upir = new NPC("upir");
        NPC vlkodlak = new NPC("vlkodlak");
        NPC zaklety_rytir = new NPC("zaklety_rytir");
        NPC princezna = new NPC("princezna");

        louka.pridejNPC(bandita);
        les.pridejNPC(medved);
        kralovstvi.pridejNPC(kral);
        baziny.pridejNPC(utopenec);
        vchodJeskyne.pridejNPC(zly_rytir);
        zchatralyDum.pridejNPC(upir);
        zakletyLes.pridejNPC(vlkodlak);
        zalar.pridejNPC(princezna);
        zricenina.pridejNPC(zaklety_rytir);
        temnyHrad.pridejNPC(Gul_Dan);

                
        aktualniProstor = kralovstvi;  // hra začíná v kralovstvi

        postava = new Postava();
    }


    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    /**
     *  Metoda nastavuje HP hráče
     *
     */
    public void setHp () {
        int hp = postava.getPocetZivotu();
        postava.setPocetZivotu(hp-1);
    }
    /**
     *  Metoda získa aktuální HP hráče
     *
     * @return pocet HP
     */
    public int getHp() {
        return postava.getPocetZivotu();
    }
    /**
     *  Metoda vrací batoh
     *
     * @return batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }

}
