package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logika.HerniPlan;
import logika.IHra;
import logika.Prostor;
import okna.AlertBox;

public class Controller {

    public static final int SIRKA_IKONY = 45;
    public static final int VYSKA_IKONY = 30;

    String predchoziMistnost;

    @FXML
    private Label popisLokace;
    @FXML
    private Label nazevLokace;
    private IHra hra;

    public ImageView obrazekLokace;

    @FXML
    private VBox vychody;
    @FXML
    private VBox seznamPredmetuVMistnosti;

    public void setHra(IHra hra) {
        this.hra = hra;
        HerniPlan herniPlan = hra.getHerniPlan();
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        zmenProstor(aktualniProstor);
    }

    private void zmenProstor(Prostor prostor) {
        if(!hra.getHerniPlan().getAktualniProstor().nejakeNpcVProstor()
        || hra.getHerniPlan().getAktualniProstor().npcJeVProstoru("kral")
        ||prostor.getNazev().equals(predchoziMistnost)
        ){
            predchoziMistnost = hra.getHerniPlan().getAktualniProstor().getNazev();
            hra.zpracujPrikaz("jdi " + prostor.getNazev());

            nazevLokace.setText(prostor.getNazev());
            popisLokace.setText(prostor.getPopis());

            String nazevObrazku = "/" + prostor.getNazev() + ".jpg";
            Image image = new Image(getClass().getResourceAsStream(nazevObrazku), 1280, 720, false, false);

            obrazekLokace.setImage(image);
            pridejVychody(prostor);

        }
        else AlertBox.zobrazAlertBox("Oznámení",
                "V oblasti se stale nachazi nejake NPC, \n" +
                        "abys kolem nich mohl projit, musis ho porazit.");


       // pridejPredmety(prostor);
    }

    private void pridejVychody(Prostor prostor) {
        vychody.getChildren().clear();
        for (Prostor p : prostor.getVychody()) {
            HBox vychod = new HBox();
            vychod.setSpacing(10);
            Label nazevProstoru = new Label(p.getNazev());

            ImageView vychodImageView = new ImageView();
            Image vychodImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + p.getNazev() + ".jpg"));
            vychodImageView.setFitHeight(VYSKA_IKONY);
            vychodImageView.setFitWidth(SIRKA_IKONY);
            vychodImageView.setImage(vychodImage);


            vychod.getChildren().addAll(vychodImageView, nazevProstoru);

            vychody.getChildren().add(vychod);
            vychod.setOnMouseClicked(event -> {
                zmenProstor(p);
            });
        }
    }

    public void pridejPredmety(Prostor prostor){


    }



}
