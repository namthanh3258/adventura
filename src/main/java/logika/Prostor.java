package logika;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci;
    private Map<String, NPC> npcka;


    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        npcka = new HashMap<>();
      //  this.postava = postava;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu() + "\n"
                + popisVeci() + "\n"
                + popisNpc();
    }

    public String getPopis() {
        return popis;
    }

    public String kratkyPopis(){
        return popisVychodu() + "\n"
                + popisVeci() + "\n"
                + popisNpc();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
    public String popisVeci() {
        String vracenyText = "veci:";
        for (String nazevVeci : veci.keySet()){
            vracenyText += " " + nazevVeci;
        }

        return vracenyText;
    }

    public String popisNpc() {
        String vracenyText = "npc:";
        for (String nazevNpc : npcka.keySet()){
            vracenyText += " " + nazevNpc;
        }

        return vracenyText;
    }




    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }



    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    /**
     * Metoda přídávající předmět do prostoru.
     */
    public void pridejVec(Vec vec){
        veci.put(vec.getNazev(), vec);
    }

    /**
     *Metoda vracící true/false podle toho zda se zadaný předmět náchází v prostoru
     */

    public Boolean vecJeVProstoru(String nazevVeci){
        return veci.containsKey(nazevVeci);
    }

    /**
     *Metoda, které odstraňuje zadaný předmět z prostoru
     */
    public Vec odstranVec(String nazevVeci){
        return veci.remove(nazevVeci);
    }
    /**
     * Metoda vypisující všechny předměty v prostoru
     */
    public Map<String, Vec> getVeci() {
        return veci;
    }
    /**
     * Metoda, která vybere daný předmět z prostoru.
     */
    public Vec vyberVec(String nazevVeci){
        Vec vec;
        if (veci.containsKey(nazevVeci)){
            vec = veci.get(nazevVeci);
            if(vec.isPrenositelna()) {
                return vec;
            }
            return null;
        }
        return null;
    }


    /**
     * Metoda přidávající NPC do prostoru
     */
    public void pridejNPC (NPC npc) {npcka.put(npc.getNazev(), npc);}
    /**
     * Metoda vracící true/false, zda se zadané NPC nachází v prostoru
     */
    public Boolean npcJeVProstoru(String nazevNpc) {return npcka.containsKey(nazevNpc);}
    /**
     * Metoda vracící true/false, zda se nějaké NPC nachází v prostoru
     */
    public Boolean nejakeNpcVProstor() {
        if (npcka.size()==0){
            return false;
        }
        return true;
    }
    /**
     * Metoda, která odstraní NPC po úspěšném útoku
     */

    public NPC odstranNpc (String nazevNpc) {return npcka.remove(nazevNpc);}

    public Collection<Vec> getSeznamVeci() {
        return veci.values();
    }


    public Collection<NPC> getSeznamNPC() {
        return npcka.values();
    }


    /*
    public Map<String, NPC> getNpc() {
        return npcka;
    }
    */


}
