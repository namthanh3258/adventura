package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Thanh Nam Nguyen
 * @version  31.06.2019
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {

        assertEquals("kralovstvi", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber ocelovy_mec");
        hra1.zpracujPrikaz("jdi louka");
        assertEquals("louka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("bandita"));
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().vecJeVProstoru("luk"));
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().vecJeVProstoru("sipy"));
        hra1.zpracujPrikaz("utok bandita");
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("bandita"));
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().vecJeVProstoru("luk"));
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().vecJeVProstoru("sipy"));
        hra1.zpracujPrikaz("seber luk");
        hra1.zpracujPrikaz("seber sipy");
        hra1.zpracujPrikaz("jdi les");
        assertEquals("les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi vchod_jeskyne");
        assertEquals("les",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("medved"));
        hra1.zpracujPrikaz("utok medved");
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("medved"));
        hra1.zpracujPrikaz("jdi vchod_jeskyne");
        assertEquals("vchod_jeskyne", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("zly_rytir"));
        hra1.zpracujPrikaz("utok zly_rytir");
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("zly_rytir"));
        hra1.zpracujPrikaz("seber cesnek");
        hra1.zpracujPrikaz("jdi vnitrek_jeskyne");
        assertEquals("vnitrek_jeskyne", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("seber stribrny_mec");
        hra1.zpracujPrikaz("jdi vchod_jeskyne");
        assertEquals("vchod_jeskyne",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi les");
        assertEquals("les",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("odhod ocelovy_mec");
        hra1.zpracujPrikaz("odhod luk");
        hra1.zpracujPrikaz("odhod sipy");
        hra1.zpracujPrikaz("jdi baziny");
        assertEquals("baziny",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("utopenec"));
        hra1.zpracujPrikaz("utok utopenec");
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("utopenec"));
        hra1.zpracujPrikaz("seber dreveny_kul");
        hra1.zpracujPrikaz("jdi hory");
        assertEquals("hory",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi zchatraly_dum");
        assertEquals("zchatraly_dum", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("upir"));
        hra1.zpracujPrikaz("utok upir");
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("upir"));
        hra1.zpracujPrikaz("seber kniha_se_zaklinadly");
        hra1.zpracujPrikaz("jdi hory");
        assertEquals("hory",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi zaklety_les");
        assertEquals("zaklety_les",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("seber kouzelna_rostlina");
        assertEquals(false,hra1.getHerniPlan().getBatoh().obsahujeVec("kouzelna_rostlina"));
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("vlkodlak"));
        hra1.zpracujPrikaz("utok vlkodlak");
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("vlkodlak"));
        hra1.zpracujPrikaz("seber kouzelna_rostlina");
        assertEquals(true,hra1.getHerniPlan().getBatoh().obsahujeVec("kouzelna_rostlina"));
        hra1.zpracujPrikaz("jdi zricenina");
        assertEquals("zricenina",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("zaklety_rytir"));
        hra1.zpracujPrikaz("utok zaklety_rytir");
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("zaklety_rytir"));
        hra1.zpracujPrikaz("seber kouzelne_brneni");
        hra1.zpracujPrikaz("jdi zaklety_les");
        assertEquals("zaklety_les",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi temny_hrad");
        assertEquals("temny_hrad",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("Gul_Dan"));
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().vecJeVProstoru("klic"));
        hra1.zpracujPrikaz("utok Gul_Dan");
        assertEquals(false,hra1.getHerniPlan().getAktualniProstor().npcJeVProstoru("Gul_Dan"));
        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().vecJeVProstoru("klic"));
        hra1.zpracujPrikaz("jdi zalar");
        assertEquals("zalar",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("seber princezna");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi temny_hrad");
        assertEquals("temny_hrad",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("odhod cesnek");
        hra1.zpracujPrikaz("seber klic");
        hra1.zpracujPrikaz("jdi zalar");
        assertEquals("zalar",hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("seber princezna");
        assertEquals(true, hra1.konecHry());
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());

    }
}
