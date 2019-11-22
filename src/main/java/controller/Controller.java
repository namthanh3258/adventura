package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.*;
import okna.AlertBox;

/**
 *  Třída slouží k ovládání grafické verze původní textové Adventury
 *
 *@author     Thanh Nam Nguyen
 *@version    21.11.2019
 */

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

    @FXML
    private MenuItem novaHra;
    @FXML
    private MenuItem napoveda;
    @FXML
    private MenuItem status;

    @FXML
    private ScrollPane scrollPane;



    public void setHra(IHra hra) {
        this.hra = hra;
        HerniPlan herniPlan = hra.getHerniPlan();
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        zmenProstor(aktualniProstor);
    }
    /**
     *  Metoda sloužící k fungování jednotlivých tlačítek v menu liště
     */
    private void menu(){
        novaHra.setOnAction(event -> {
            Hra novaHra = new Hra();
            AlertBox.zobrazAlertBox("Nová hra", "Spouští se nová hra.");
            batoh.getChildren().clear();
            setHra(novaHra);
        });
        napoveda.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Nápověda");
            StackPane layout = new StackPane();

            WebView webView = new WebView();
            WebEngine engine = webView.getEngine();

            engine.load(String.valueOf(getClass().getResource("/about.html")));
            layout.getChildren().add(webView);

            Scene scene = new Scene(layout, 800, 300);
            stage.setScene(scene);
            stage.show();
        });
        status.setOnAction(event -> {
            AlertBox.zobrazAlertBox("Status","Tvé aktuální HP je - [" + hra.getHerniPlan().getHp() + "]\n" +
                    "V tvém batohu je " + hra.getBatoh().velikostBatohu() + "/6 předmětů");
        });
    }

    /**
     *  Tato metoda ma za úkol přepinat mezi jednotlivými lokacemi
     * @param prostor ze, kterého se získávají vedlejší prostory
     */
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
            pridejPredmety(prostor);
            pridejNPC(prostor);
            menu();
        }
        else AlertBox.zobrazAlertBox("Oznámení",
                "V oblasti se stále nachází nějaké NPC, \n" +
                        "abys mohl projít do další lokace, musíš ho porazit.");



    }
    /**
     *  Tato metoda přidává jednotlivé vychody, které jsou připojeny k aktuálním prostoru
     * @param prostor, ze kterého se získávají vedlejší prostory
     */
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
    /**
     *  Metoda přidáva jednotlivé npc, které se v aktuálním prostoru nachází
     */
    private void pridejNPC(Prostor prostor ){
        npcVProstoru.getChildren().clear();
        for (NPC npc : prostor.getSeznamNPC()){
            pridejNPCDoMisnosti(npc);
        }
    }
    /**
     *  Metoda vytváří HBox s názvem a obrázkem pro jednotlivé npc
     *  Druhá část metody umožňuje útočit na npc, u některých po jejich poražení dojde k vytvoření nového předmětu.
     * @param npc ze, kterého se získávají informace o určitém npc
     */
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
                hra.konecHry();
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
                hra.konecHry();
            }else if(npc.getNazev().equals("princezna") && !hra.getBatoh().obsahujeVec("klic")){
                AlertBox.zobrazAlertBox("Oznámení", "Musíš nejprve najít klíč od princeziny cely, " +
                        "aby si ji mohl zachránit.");
                npcVProstoru.getChildren().clear();
            }

            else {
                System.out.println("sethp");
                if (hra.getHerniPlan().getHp() == 0){
                    hra.konecHry();
                    AlertBox.zobrazAlertBox("Zemřel si", "Zemřel si během plněni tvého úkolu. " +
                            "Třeba se ti to povede příště.");
                    Hra novaHra = new Hra();
                    AlertBox.zobrazAlertBox("Nová hra", "Spouští se nová hra.");
                    setHra(novaHra);
                    return;
                }
                AlertBox.zobrazAlertBox("Oznámení", "NPC " + npc.getNazev() + " se ti nepodařilo porazit, povedlo se ti," +
                        " ale jen taktak utéct. Tvé aktuální HP je - [" + hra.getHerniPlan().getHp() + "]");
                return;
            }
        });

    }
    /**
     *  Metoda přidává předměty do prostoru
     * @param prostor pomocí tohoto parametru se zjištují, které předměty se nacházejí v aktuálním prostoru
     */
    public void pridejPredmety(Prostor prostor){
        predmety.getChildren().clear();

        for (Vec vec : prostor.getSeznamVeci()) {
            pridejPredmetDoMistnosti(vec);
        }

    }
    /**
     *  Metoda vytváří HBox s názvem a obrázkem pro jednotlivé předměty
     *  V druhé části metody je přídávání a odhazování věcí z batohu
     * @param vec získává informace o jednotlivých předmětech
     */
    private void pridejPredmetDoMistnosti(Vec vec) {

        HBox predmet = new HBox();
        predmet.setSpacing(10);
        predmet.setPadding(new Insets(3,3,3,3));
        scrollPane.setStyle("-fx-background: transparent;");
        Label nazevPredmetu = new Label(vec.getNazev());

        ImageView predmetImageView = new ImageView();
        Image predmetImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + vec.getNazev() + ".jpg"));
        predmetImageView.setFitHeight(VYSKA_IKONY);
        predmetImageView.setFitWidth(SIRKA_IKONY);
        predmetImageView.setImage(predmetImage);

        predmet.getChildren().addAll(predmetImageView,nazevPredmetu);

        predmety.getChildren().add(predmet);

        predmet.setOnMouseClicked(event -> {

            if (vec.isPrenositelna() && hra.getBatoh().velikostBatohu()!=6
            )
            {
                if(!hra.getHerniPlan().getAktualniProstor().nejakeNpcVProstor()
                        || hra.getHerniPlan().getAktualniProstor().npcJeVProstoru("kral")) {
                    hra.zpracujPrikaz("seber " + vec.getNazev());

                    HBox predmetVBatohu = new HBox();
                    predmetVBatohu.setSpacing(10);
                    predmetVBatohu.setPadding(new Insets(3, 3, 3, 3));

                    Label vecVBatohu = new Label(vec.getNazev());
                    //batoh.getChildren().add(vecVBatohu);

                    ImageView predmetVBatohuImageView = new ImageView();
                    Image predmetVBatohuImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + vec.getNazev() + ".jpg"));
                    predmetVBatohuImageView.setFitHeight(VYSKA_IKONY);
                    predmetVBatohuImageView.setFitWidth(SIRKA_IKONY);
                    predmetVBatohuImageView.setImage(predmetVBatohuImage);

                    predmetVBatohu.getChildren().addAll(predmetVBatohuImageView, vecVBatohu);

                    batoh.getChildren().add(predmetVBatohu);
                    predmety.getChildren().remove(predmet);
                    hra.getBatoh().pridejVec(vec);


                    predmetVBatohu.setOnMouseClicked(event1 -> {
                        hra.zpracujPrikaz("odhod " + vec.getNazev());
                        batoh.getChildren().remove(predmetVBatohu);
                        pridejPredmetDoMistnosti(vec);
                        hra.getBatoh().vyberPredmet(vec.getNazev());
                    });
                }else{
                    AlertBox.zobrazAlertBox("Oznámení", "Předmět nelze sebrat jelikož se v prostoru" +
                            "stále náchazí nějaký nepřítel.\nMusíš ho nejprve porazit.");
                }
            } else if (hra.getBatoh().velikostBatohu() == 6) {
                AlertBox.zobrazAlertBox("Oznámení", "Batoh je plný. Pokud chceš předmět sebrat,\n" +
                        "musíš nejprve uvolnit místo v batohu.");
            } else if (vec.isPrenositelna()==false){
                AlertBox.zobrazAlertBox("Oznámení", "Předmět nelze sebrat, protože je moc velký a těžký");
            }
        });
    }

}
