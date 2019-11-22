/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package start;



import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class Start extends Application
{

    public static void main(String[] args) throws FileNotFoundException {

        if(args.length > 0 && args[0].equals("text")) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } else {
            launch(args);
        }
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
        Parent rootComponent = loader.load();

        Scene scene = new Scene(rootComponent);
        primaryStage.setScene(scene);


        IHra hra = new Hra();
        Controller controller = loader.getController();
        controller.setHra(hra);

        primaryStage.setTitle("Záchrana princezny");
        InputStream iconStream = getClass().getResourceAsStream("/ikona.png");
        Image icon = new Image(iconStream);
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }
}
