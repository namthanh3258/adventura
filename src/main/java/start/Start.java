/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package start;



import logika.*;
import uiText.TextoveRozhrani;

import java.io.FileNotFoundException;


public class    Start
{

    public static void main(String[] args) throws FileNotFoundException {
        
        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        ui.hraj();

       /* if(args.length == 0){// automaticke hrani
            ui.hraj();
        } else {
           ui.hrajZeSoubouru(args[0]);
        } */

    }
}
