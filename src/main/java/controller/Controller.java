package controller;

import javafx.application.Platform;
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
import logika.*;
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
    @FXML
    private VBox npcVProstoru;

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
            pridejNPC(prostor);

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

    private void pridejNPC(Prostor prostor ){
        npcVProstoru.getChildren().clear();
        for (NPC npc : prostor.getSeznamNPC()){
            pridejNPCDoMisnosti(npc);
        }
    }

    private void pridejNPCDoMisnosti(NPC npc) {
        HBox npcBox = new HBox();
        npcBox.setSpacing(10);
        npcBox.setPadding(new Insets(3,3,3,3));

        Label nazevNPC = new Label(npc.getNazev());

        ImageView npcImageView = new ImageView();
        Image npcImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + npc.getNazev() + ".jpg"));
        npcImageView.setFitHeight(VYSKA_IKONY);
        npcImageView.setFitWidth(SIRKA_IKONY);
        npcImageView.setImage(npcImage);

        npcBox.getChildren().addAll(npcImageView,nazevNPC);

        npcVProstoru.getChildren().add(npcBox);
        System.out.println(hra.getHerniPlan().getAktualniProstor().npcJeVProstoru("bandita"));
        System.out.println(hra.getHerniPlan().getHp());

        npcBox.setOnMouseClicked(event -> {
            hra.zpracujPrikaz("utok " + npc.getNazev());
            if (npc.getNazev().equals("bandita")&& hra.getBatoh().obsahujeVec("ocelovy_mec")){
                AlertBox.zobrazAlertBox("Oznámení", "NPC " + npc.getNazev() + " se ti podařilo porazit.");
                npcVProstoru.getChildren().clear();

                Vec luk = new Vec("luk", true);
                Vec sipy = new Vec("sipy", true);

                hra.getHerniPlan().getAktualniProstor().pridejVec(luk);
                hra.getHerniPlan().getAktualniProstor().pridejVec(sipy);

                pridejPredmetDoMistnosti(luk);
                pridejPredmetDoMistnosti(sipy);

            }
            else if(npc.getNazev().equals("medved") && hra.getBatoh().obsahujeVec("luk") && hra.getBatoh().obsahujeVec("sipy")){
                AlertBox.zobrazAlertBox("Oznámení", "NPC " + npc.getNazev() + " se ti podařilo porazit.");
                npcVProstoru.getChildren().clear();
            } else if (npc.getNazev().equals("kral")) {
                AlertBox.zobrazAlertBox("Oznámení", "Pokusil ses zabít krále, byl si popraven za zradu." +
                        " Díky, že jste si zahráli.");
                Platform.exit();
            } else if(npc.getNazev().equals("zly_rytir") && hra.getBatoh().obsahujeVec("ocelovy_mec")){
                AlertBox.zobrazAlertBox("Oznámení", "NPC " + npc.getNazev() + " se ti podařilo porazit.");
                npcVProstoru.getChildren().clear();

            } else if(npc.getNazev().equals("utopenec") && hra.getBatoh().obsahujeVec("stribrny_mec")){
                AlertBox.zobrazAlertBox("Oznámení", "NPC " + npc.getNazev() + " se ti podařilo porazit.");
                npcVProstoru.getChildren().clear();

                Vec dreveny_kul = new Vec("dreveny_kul",true);
                hra.getHerniPlan().getAktualniProstor().pridejVec(dreveny_kul);
                pridejPredmetDoMistnosti(dreveny_kul);

            } else if(npc.getNazev().equals("upir") && hra.getBatoh().obsahujeVec("dreveny_kul") && hra.getBatoh().obsahujeVec("cesnek")){
                AlertBox.zobrazAlertBox("Oznámení", "NPC " + npc.getNazev() + " se ti podařilo porazit.");
                npcVProstoru.getChildren().clear();

                Vec kniha_se_zaklinadly = new Vec("kniha_se_zaklinadly",true);
                hra.getHerniPlan().getAktualniProstor().pridejVec(kniha_se_zaklinadly);
                pridejPredmetDoMistnosti(kniha_se_zaklinadly);

            } else if (npc.getNazev().equals("vlkodlak") && hra.getBatoh().obsahujeVec("stribrny_mec")){

                AlertBox.zobrazAlertBox("Oznámení", "NPC " + npc.getNazev() + " se ti podařilo porazit.");
                npcVProstoru.getChildren().clear();

            } else if (npc.getNazev().equals("zaklety_rytir") && hra.getBatoh().obsahujeVec("kniha_se_zaklinadly") ){
                AlertBox.zobrazAlertBox("Oznámení", "NPC " + npc.getNazev() + " se ti podařilo porazit.");
                npcVProstoru.getChildren().clear();

                Vec kouzelne_brneni = new Vec("kouzelne_brneni", true);
                hra.getHerniPlan().getAktualniProstor().pridejVec(kouzelne_brneni);
                pridejPredmetDoMistnosti(kouzelne_brneni);

            } else if (npc.getNazev().equals("Gul_Dan") && hra.getBatoh().obsahujeVec("stribrny_mec")
                && hra.getBatoh().obsahujeVec("dreveny_kul")
                && hra.getBatoh().obsahujeVec("cesnek")
                && hra.getBatoh().obsahujeVec("kouzelna_rostlina")
                && hra.getBatoh().obsahujeVec("kniha_se_zaklinadly")
                && hra.getBatoh().obsahujeVec("kouzelne_brneni")){

                AlertBox.zobrazAlertBox("Oznámení", "Dokázal si porazit si Gul'Dana! Nyní musíš najít princeznu.");
                npcVProstoru.getChildren().clear();

                Vec klic = new Vec("klic", true);
                hra.getHerniPlan().getAktualniProstor().pridejVec(klic);
                pridejPredmetDoMistnosti(klic);

            } else if(npc.getNazev().equals("princezna") && hra.getBatoh().obsahujeVec("klic")){
                AlertBox.zobrazAlertBox("Oznámení", "Povedlo se ti zachránit princeznu ze spáru Gul'Dana " +
                        "a bezpečně dopravit zpět do království.");
                npcVProstoru.getChildren().clear();
            }else if(npc.getNazev().equals("princezna") && !hra.getBatoh().obsahujeVec("klic")){
                AlertBox.zobrazAlertBox("Oznámení", "Musíš nejprve najít klíč od princeziny cely, " +
                        "aby si ji mohl zachránit.");
                npcVProstoru.getChildren().clear();
            }

            else {
                System.out.println("sethp");
                AlertBox.zobrazAlertBox("Oznámení", "NPC " + npc.getNazev() + " se ti nepodařilo porazit, povedlo se ti," +
                        " ale jen taktak utéct. Tvé aktuální HP je - [" + hra.getHerniPlan().getHp() + "]");
                return;
            }
            System.out.println("npc v prostoru bandita " + hra.getHerniPlan().getAktualniProstor().npcJeVProstoru("bandita"));
        });

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

            if (vec.isPrenositelna() && hra.getBatoh().velikostBatohu()!=6) {
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
                System.out.println(hra.getHerniPlan().getAktualniProstor().npcJeVProstoru("uhh"));
                System.out.println("luk " + hra.getBatoh().obsahujeVec("luk"));


                predmetVBatohu.setOnMouseClicked(event1 -> {
                    hra.zpracujPrikaz("odhod "+ vec.getNazev());
                    batoh.getChildren().remove(predmetVBatohu);
                    pridejPredmetDoMistnosti(vec);
                    hra.getBatoh().vyberPredmet(vec.getNazev());
                    System.out.println(hra.getBatoh().velikostBatohu());
                    System.out.println("obsahuje mec " + hra.getBatoh().obsahujeVec("ocelovy_mec"));

                });
            } else if (hra.getBatoh().velikostBatohu() == 6) {
                AlertBox.zobrazAlertBox("Oznámení", "Batoh je plný. Pokud chceš předmět sebrat,\n" +
                        "musíš nejprve uvolnit místo v batohu.");
            } else if (vec.isPrenositelna()==false){
                AlertBox.zobrazAlertBox("Oznámení", "Předmět nelze sebrat, protože je moc velký a těžký");
            }
        });
        //System.out.println(hra.getBatoh().obsahujeVec("ocelovy_mec"));
    }



}
