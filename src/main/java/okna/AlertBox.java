package okna;

import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {

    public static void zobrazAlertBox(String nadpis, String zprava){
        Stage okno = new Stage();

        okno.initModality(Modality.APPLICATION_MODAL);
        okno.setTitle(nadpis);
        okno.setMinWidth(250);

        Label label = new Label();
        label.setText(zprava);
        label.setTextAlignment(TextAlignment.CENTER);

        Button zavrit = new Button("Zavřít");
        zavrit.setOnAction(e -> okno.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, zavrit);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(layout);
        okno.setScene(scene);
        okno.showAndWait();


    }
}
