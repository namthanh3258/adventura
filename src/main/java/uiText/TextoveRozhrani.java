package uiText;


import java.io.*;
import java.util.Scanner;

import com.sun.org.apache.xpath.internal.SourceTree;
import logika.IHra;
/**
 *  Class TextoveRozhrani
 * 
 *  Toto je uživatelského rozhraní aplikace Adventura
 *  Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *  
 *  
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class TextoveRozhrani {
    private IHra hra;

    /**
     *  Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println(hra.vratEpilog());
    }

    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void hrajZeSoubouru(String jmenoSouboru) throws FileNotFoundException { //na automaticke hrani or what
        try(BufferedReader cteni = new BufferedReader(new FileReader(jmenoSouboru));
                PrintWriter zapis = new PrintWriter(new FileWriter("vystup.txt"))){ //stream

            zapis.println(hra.vratUvitani());
            System.out.println(hra.vratUvitani());


            String radek = cteni.readLine();
            while (radek != null && !hra.konecHry()) {
                zapis.println("> " + radek);
                System.out.println("> " + radek);

                zapis.println(hra.zpracujPrikaz(radek));
                System.out.println(hra.zpracujPrikaz(radek));

                radek = cteni.readLine();
            }

            zapis.println(hra.vratEpilog());
            System.out.println(hra.vratEpilog());

        } catch (FileNotFoundException e){
            System.out.println("Soubor nebyl nalezen");
            File file = new File (jmenoSouboru);
            System.out.println("Hledany soubor: " + file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("FINALLY");
        }

        }
    }


