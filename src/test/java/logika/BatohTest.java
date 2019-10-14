package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Batoh
 *
 * @author    Thanh Nam Nguyen
 * @version  31.06.2019
 */
public class BatohTest {
    private Vec predmet_1;
    private Vec predmet_2;
    private Vec predmet_3;
    private Batoh batoh;

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
    public void setUp(){
        predmet_1 = new Vec("koste",true);
        predmet_2 = new Vec("tuzka",true);
        predmet_3 = new Vec("lednice",false);
        batoh = new Batoh();
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
     * Test testuje vložení do batohu
     */
    @Test
    public void testVlozeniDoBatohu() {
        batoh.pridejVec(predmet_1);
        assertEquals(true,batoh.obsahujeVec("koste"));
        assertEquals(false, batoh.obsahujeVec("ponozka"));
        batoh.pridejVec(predmet_2);
        assertEquals(true, batoh.obsahujeVec("tuzka"));
        batoh.pridejVec(predmet_3);
        assertEquals(false,batoh.obsahujeVec("lednice"));
    }
    /***************************************************************************
     * Testuje kolik je předmětů v batohu
     */
    @Test
    public void testVelikostObsahu(){
        batoh.pridejVec(predmet_1);
        assertEquals(1,batoh.velikostBatohu());
        batoh.pridejVec(predmet_2);
        assertEquals(2,batoh.velikostBatohu());
    }
    /***************************************************************************
     * Testuje výběr určitého předmětu z batohu
     */
    @Test
    public void testVyberuPredmetu(){
        batoh.pridejVec(predmet_1);
        batoh.pridejVec(predmet_2);
        assertEquals(predmet_1,batoh.vyberPredmet("koste"));
        assertEquals(predmet_2,batoh.vyberPredmet("tuzka"));
        assertEquals(null,batoh.vyberPredmet("lednice"));
    }
    /***************************************************************************
     * Test kolik je maximální kapacita
     */
    @Test
    public void testMaxKapacita(){
        assertEquals(6,batoh.getMAXKAPACITA());
    }
}
