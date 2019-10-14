package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Vec
 *
 * @author    Thanh Nam Nguyen
 * @version   31.06.2019
 */
public class VecTest {

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
     * Testování přenositelnosti předmětu
     */
    @Test
    public void testPrenositelnosti() {
        Vec predmet_1 = new Vec("koste",true);
        Vec predmet_2 = new Vec("lednice", false);

        assertEquals(true,predmet_1.isPrenositelna());
        assertEquals(false,predmet_2.isPrenositelna());
    }
    /***************************************************************************
     * Testování vkladu předmětu do prostoru
     */
    @Test
    public void testVkladuVeciDoProstoru(){
        Hra hra = new Hra();
        Vec predmet_1 = new Vec("koste",true);
        Vec predmet_2 = new Vec("lednice", false);
        hra.getHerniPlan().getAktualniProstor().pridejVec(predmet_1);
        hra.getHerniPlan().getAktualniProstor().pridejVec(predmet_2);
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().vecJeVProstoru("koste"));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().vecJeVProstoru("lednice"));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().vecJeVProstoru("ponozka"));
    }
}
