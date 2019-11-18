package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logika.HerniPlan;
import logika.IHra;
import logika.Prostor;
import logika.Vec;
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
    private VBox predmety;
    @FXML
    private VBox batoh;

    public void setHra(IHra hra) {
        this.hra = hra;
        HerniPlan herniPlan = hra.getHerniPlan();
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        zmenProstor(aktualniProstor);
    }
    /*Podminka do toho ifu dole*/
    //!hra.getHerniPlan().getAktualniProstor().nejakeNpcVProstor()
    //        || hra.getHerniPlan().getAktualniProstor().npcJeVProstoru("kral")
    //        ||prostor.getNazev().equals(predchoziMistnost)
    private void zmenProstor(Prostor prostor) {
        if(true
        ){
            predchoziMistnost = hra.getHerniPlan().getAktualniProstor().getNazev();
            hra.zpracujPrikaz("jdi " + prostor.getNazev());

            nazevLokace.setText(prostor.getNazev());
            popisLokace.setText(prostor.getPopis());

            String nazevObrazku = "/" + prostor.getNazev() + ".jpg";
            Image image = new Image(getClass().getResourceAsStream(nazevObrazku), 1280, 720, false, false);

            obrazekLokace.setImage(image);
            pridejVychody(prostor);
            pridejPredmety(prostor);

        }
        else AlertBox.zobrazAlertBox("Oznámení",
                "V oblasti se stále nachází nějaké NPC, \n" +
                        "abys mohl projít do další lokace, musíš ho porazit.");



    }

    private void pridejVychody(Prostor prostor) {
        vychody.getChildren().clear();
        for (Prostor p : prostor.getVychody()) {
            HBox vychod = new HBox();
            vychod.setSpacing(10);
            vychod.setPadding(new Insets(3,3,3,3));
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
        predmety.getChildren().clear();

        for (Vec vec : prostor.getSeznamVeci()) {
            pridejPredmetDoMistnosti(vec);
        }

    }

    private void pridejPredmetDoMistnosti(Vec vec) {
/*
        HBox predmet = new HBox();
        predmet.setSpacing(10);
        predmet.setPadding(new Insets(3,3,3,3));
        Label nazevPredmetu = new Label(vec.getNazev());

        ImageView predmetImageView = new ImageView();
        Image predmetImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + vec.getNazev() + ".jpg"));
        predmetImageView.setFitHeight(VYSKA_IKONY);
        predmetImageView.setFitWidth(SIRKA_IKONY);
        predmetImageView.setImage(predmetImage);


        predmet.getChildren().addAll(predmetImageView, nazevPredmetu);

        predmety.getChildren().add(predmet);
*/


        HBox predmet = new HBox();
        predmet.setSpacing(10);
        predmet.setPadding(new Insets(3,3,3,3));

        Label nazevPredmetu = new Label(vec.getNazev());

        ImageView predmetImageView = new ImageView();
        Image predmetImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + vec.getNazev() + ".jpg"));
        predmetImageView.setFitHeight(VYSKA_IKONY);
        predmetImageView.setFitWidth(SIRKA_IKONY);
        predmetImageView.setImage(predmetImage);

        predmet.getChildren().addAll(predmetImageView,nazevPredmetu);

        predmety.getChildren().add(predmet);




        /*Label nazevVeci = new Label(vec.getNazev());
        predmety.getChildren().add(nazevVeci);
        */


        predmet.setOnMouseClicked(event -> {

            if (vec.isPrenositelna() && hra.getBatoh().velikostBatohu()!=5) {
                hra.zpracujPrikaz("seber " + vec.getNazev());

                HBox predmetVBatohu = new HBox();
                predmetVBatohu.setSpacing(10);
                predmetVBatohu.setPadding(new Insets(3,3,3,3));

                Label vecVBatohu = new Label(vec.getNazev());
                //batoh.getChildren().add(vecVBatohu);

                ImageView predmetVBatohuImageView = new ImageView();
                Image predmetVBatohuImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + vec.getNazev() + ".jpg"));
                predmetVBatohuImageView.setFitHeight(VYSKA_IKONY);
                predmetVBatohuImageView.setFitWidth(SIRKA_IKONY);
                predmetVBatohuImageView.setImage(predmetVBatohuImage);

                predmetVBatohu.getChildren().addAll(predmetVBatohuImageView,vecVBatohu);

                batoh.getChildren().add(predmetVBatohu);
                predmety.getChildren().remove(predmet);
                hra.getBatoh().pridejVec(vec);

                System.out.println(hra.getBatoh().obsahujeVec("ocelovy_mec"));
                System.out.println(hra.getBatoh().velikostBatohu());


                predmetVBatohu.setOnMouseClicked(event1 -> {
                    hra.zpracujPrikaz("odhod "+ vec.getNazev());
                    batoh.getChildren().remove(predmetVBatohu);
                    pridejPredmetDoMistnosti(vec);
                    hra.getBatoh().vyberPredmet(vec.getNazev());
                    System.out.println(hra.getBatoh().velikostBatohu());
                    System.out.println(hra.getBatoh().obsahujeVec("ocelovy_mec"));

                });
            } else if (hra.getBatoh().velikostBatohu() == 5) {
                AlertBox.zobrazAlertBox("Oznámení", "Batoh je plný, pokud chceš předmět sebrat,\n" +
                        "musíš nejprve uvolnit místo v batohu.");
            } else if (vec.isPrenositelna()==false){
                AlertBox.zobrazAlertBox("Oznámení", "Předmět nelze sebrat, protože je moc velký a těžký");
            }
        });
        //System.out.println(hra.getBatoh().obsahujeVec("ocelovy_mec"));
    }



}
