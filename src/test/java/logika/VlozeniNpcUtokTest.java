package logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy PrikazUtok a vložení npc do prostoru
 *
 * @author    Thanh Nam Nguyen
 * @version   31.06.2019
 */
public class VlozeniNpcUtokTest
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
     *Testuje vložení NPC do prostoru a útok
     */
    @Test
    public  void testVlozeniUtok() {
        Hra hra = new Hra();

        NPC vlk = new NPC("vlk");

        hra.getHerniPlan().getAktualniProstor().pridejNPC(vlk);
        assertEquals(true,hra.getHerniPlan().getAktualniProstor().npcJeVProstoru("vlk"));
        assertEquals(false,hra.getHerniPlan().getAktualniProstor().npcJeVProstoru("liska"));
        hra.zpracujPrikaz("utok vlk");
        assertEquals(true,hra.getHerniPlan().getAktualniProstor().npcJeVProstoru("vlk"));
        assertEquals("NPC s názvem medved se v prostoru nenáchází.",hra.zpracujPrikaz("utok medved"));
        assertEquals("Nevim na koho zaútočit.", hra.zpracujPrikaz("utok"));


    }

}
