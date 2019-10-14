package logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy PříkazOdhod a PříkazSeber
 *
 * @author    Thanh Nam Nguyen
 * @version   31.06.2019
 */
public class SeberOdhodTest
{
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
     *Testuje se zde zda jde sebrat a odhodit předmět
     */
    @Test
    public  void testSeberOdhodPredmet() {
        Hra hra = new Hra();
        Vec predmet_1 = new Vec("koste", true);
        String nazevVeci = "koste";
        hra.getHerniPlan().getAktualniProstor().pridejVec(predmet_1);
        assertEquals(true,hra.getHerniPlan().getAktualniProstor().vecJeVProstoru("koste"));
        hra.zpracujPrikaz("seber koste");
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().vecJeVProstoru("koste"));
        hra.zpracujPrikaz("odhod koste");
        assertEquals(true,hra.getHerniPlan().getAktualniProstor().vecJeVProstoru("koste"));
        assertEquals("Věc s názvem list se v tvém batohu nenachází.", hra.zpracujPrikaz("odhod list"));
        hra.zpracujPrikaz("seber koste");
        assertEquals(1,hra.getHerniPlan().getBatoh().velikostBatohu());
        assertEquals("Předmět koste si odhodil.",hra.zpracujPrikaz("odhod koste"));

    }

}
