/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package start;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

import java.io.FileNotFoundException;


public class Start extends Application
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scena.fxml"));
        Pa  rent rootComponent = loader.load();

    }
}
