/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import animatefx.animation.BounceIn;
import animatefx.animation.BounceOut;
import animatefx.animation.FadeIn;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Icon By Freepik, Pixel perfect use BorderPane
 *
 * @author ezizu
 */
public class Display extends Application {

    // Buat komponent global !
    private final BorderPane root = new BorderPane();
    private final VBox blpan = new VBox();
    private final VBox clpan = new VBox();
    private final VBox lpan = new VBox();
    private final HBox rpan = new HBox();
    private final VBox rbox = new VBox();
    private final VBox lbox = new VBox();
    private final VBox lboxt = new VBox();
    private final VBox lboxb = new VBox();
    private final Label list = new Label("List Jadwal:");
    private final Label judl = new Label("INPUT");
    private final Label mapl = new Label("mapel: ");
    private final Label jaml = new Label("waktu: ");
    private final Label gedl = new Label("gedung: ");
    private final Label rual = new Label("ruang: ");
    private final Label dosl = new Label("dosen: ");
    private final HBox mapb = new HBox();
    private final HBox jamb = new HBox();
    private final HBox gedb = new HBox();
    private final HBox ruab = new HBox();
    private final HBox dosb = new HBox();
    private final TextField mapf = new TextField();
    private final TextField jamf = new TextField();
    private final TextField gedf = new TextField();
    private final TextField ruaf = new TextField();
    private final TextField dosf = new TextField();
    private final Button upp = new Button();
    private final Button del = new Button();
    private final Button add = new Button();
    private final Button def = new Button();
    private final Button submit = new Button("submit");
    private final Button remove = new Button("remove");
    private final HBox subb = new HBox();
    private final HBox remb = new HBox();
    private boolean ctab = true;
    private boolean cput = false;
    private boolean fdef = false;
    private boolean fdel;

    private TableColumn<Data, String> mapcol = new TableColumn("Mapel");
    private TableColumn<Data, String> jamcol = new TableColumn("Waktu");
    private TableColumn<Data, String> gedcol = new TableColumn("Gedung");
    private TableColumn<Data, String> ruacol = new TableColumn("Ruang");
    private TableColumn<Data, String> doscol = new TableColumn("Dosen");

    private final TableView<Data> tabel = new TableView<>();
    public static ObservableList<Data> data = FXCollections.observableArrayList();

    private BufferedReader read;
    private final String url = "src\\Main\\Database.txt";
    private File file = new File(url);

    private void Export() throws IOException {
        Writer writer = null;
        try {
            String smapel = null, swaktu = null, sgdung = null, sruang = null, sdosen = null;
            writer = new BufferedWriter(new FileWriter(file));

            String judl = mapcol.getText() + " | " + jamcol.getText() + " | " + gedcol.getText() + " | " + ruacol.getText() + " | " + doscol.getText() + "\n";
            writer.write(judl);

            for (Data save : data) {
                if (save.getMapel().isEmpty()) {
                    smapel = "0";
                } else {
                    smapel = save.getMapel();
                    smapel = smapel.replace(" ", "_");
                }

                if (save.getWaktu().isEmpty()) {
                    swaktu = "0";
                } else {
                    swaktu = save.getWaktu();
                    swaktu = swaktu.replace(" ", "_");
                }

                if (save.getGdung().isEmpty()) {
                    sgdung = "0";
                } else {
                    sgdung = save.getGdung();
                    sgdung = sgdung.replace(" ", "_");
                }

                if (save.getRuang().isEmpty()) {
                    sruang = "0";
                } else {
                    sruang = save.getRuang();
                    sruang = sruang.replace(" ", "_");
                }

                if (save.getDosen().isEmpty()) {
                    sdosen = "0";
                } else {
                    sdosen = save.getDosen();
                    sdosen = sdosen.replace(" ", "_");
                }

                String text = smapel + " | " + swaktu + " | " + sgdung + " | " + sruang + " | " + sdosen + "\n";
                writer.write(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            writer.flush();
            writer.close();
        }
    }

    private void Import() throws IOException {

        try {

            read = new BufferedReader(new FileReader(file));
            String flines = read.readLine();

            Object[] ambil = read.lines().toArray();
            for (int i = 0; i < ambil.length; i++) {
                String baris = ambil[i].toString();
                baris = baris.replace(" |", "");
                String[] hasil = baris.split(" ");

                for (int j = 0; j < hasil.length; j++) {
                    if (hasil[j].equals("0")) {
                        hasil[j] = " ";
                    }
                    hasil[j] = hasil[j].replace("_", " ");
                }

                data.add(new Data(hasil[i], hasil[i + 1], hasil[i + 2], hasil[i + 3], hasil[i + 4]));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            read.close();
        }
    }

    private void InputIn() {
        ScaleTransition ps = new ScaleTransition(Duration.seconds(1), lpan);
        ps.setFromX(0.5);
        ps.setFromY(0.5);
        ps.setToY(1);
        ps.setToX(1);

        TranslateTransition pt = new TranslateTransition(Duration.seconds(1), lpan);
        pt.setFromX(20);
        pt.setToX(0);

        TranslateTransition lt = new TranslateTransition(Duration.seconds(1), blpan);
        lt.setFromX(140);
        lt.setToX(0);

        ScaleTransition ls = new ScaleTransition(Duration.seconds(1), blpan);
        ls.setFromX(2);
        ls.setFromY(2);
        ls.setToY(1);
        ls.setToX(1);

        TranslateTransition rt = new TranslateTransition(Duration.seconds(1), rpan);
        rt.setFromX(340);
        rt.setToX(0);

        rt.play();
        pt.play();
        lt.play();
        ps.play();
        ls.play();

        cput = true;

    }

    private void InputOut() {
        ScaleTransition ps = new ScaleTransition(Duration.seconds(1), lpan);
        ps.setFromX(1);
        ps.setFromY(1);
        ps.setToY(0.5);
        ps.setToX(0.5);

        TranslateTransition pt = new TranslateTransition(Duration.seconds(1), lpan);
        pt.setFromX(0);
        pt.setToX(20);

        TranslateTransition lt = new TranslateTransition(Duration.seconds(1), blpan);
        lt.setFromX(0);
        lt.setToX(140);

        ScaleTransition ls = new ScaleTransition(Duration.seconds(1), blpan);
        ls.setFromX(1);
        ls.setFromY(1);
        ls.setToY(2);
        ls.setToX(2);

        TranslateTransition rt = new TranslateTransition(Duration.seconds(1), rpan);
        rt.setFromX(0);
        rt.setToX(340);

        ps.play();
        pt.play();
        rt.play();
        lt.play();
        ls.play();

        ps.setOnFinished(e -> {
            cput = false;
        });

    }

    private void TabelIn() {
        ScaleTransition ps = new ScaleTransition(Duration.seconds(1), lpan);
        TranslateTransition pt = new TranslateTransition(Duration.seconds(1), lpan);

        if (!fdel && !ctab && !tabel.getItems().isEmpty()) {

            if (!cput) {
                ps.setFromX(0.5);
                ps.setFromY(0.5);
                ps.setToY(0.5);
                ps.setToX(0.5);
            } else {
                ps.setFromX(1);
                ps.setFromY(1);
                ps.setToY(1);
                ps.setToX(1);
            }

            pt.setFromX(0);
            pt.setFromY(-400);
            pt.setToY(0);
            pt.setToX(0);

            pt.play();
            ps.play();

        } else {
            ps.setFromX(1);
            ps.setFromY(0.5);
            ps.setToY(0.5);
            ps.setToX(0.5);

            pt.setFromX(0);
            pt.setFromY(-400);
            pt.setToY(0);
            pt.setToX(20);

            pt.play();
            ps.play();
        }

        TranslateTransition ct = new TranslateTransition(Duration.seconds(2), clpan);
        ct.setFromY(-800);
        ct.setToY(0);
        ct.play();

        ps.setOnFinished(e -> {
            if (fdel) {
                lpan.getChildren().add(remb);
                new BounceIn(remb).play();
                fdel = true;
            }
        });

        ctab = true;
    }

    private void TabelOut() {
        ScaleTransition ps = new ScaleTransition(Duration.seconds(1), lpan);
        if (!cput) {
            ps.setFromX(0.5);
            ps.setFromY(0.5);
            ps.setToY(0.5);
            ps.setToX(0.5);
        } else {
            ps.setFromX(1);
            ps.setFromY(1);
            ps.setToY(1);
            ps.setToX(1);
        }

        TranslateTransition ct = new TranslateTransition(Duration.seconds(1), clpan);
        ct.setFromY(0);
        ct.setToY(-800);

        TranslateTransition pt = new TranslateTransition(Duration.seconds(1), lpan);
        pt.setFromX(0);
        pt.setFromY(0);
        pt.setToY(-400);
        pt.setToX(0);

        ct.play();
        pt.play();
        ps.play();

        ps.setOnFinished(e -> {
            ctab = false;
        });
    }

    private void Default(ActionEvent event) throws IOException {

        if (!def.isDefaultButton()) {
            InputOut();
            TabelOut();
            Import();
        } else {
            if (cput) {
                InputOut();

            }
            if (ctab) {
                TabelOut();
            }
        }

        fdef = true;
    }

    private void Upp(ActionEvent event) {
        if (cput) {
            InputOut();
        }
        if (!ctab) {
            TabelIn();
        }
        if (fdel) {
            BounceOut b = new BounceOut(remb);
            b.play();
            b.setOnFinished(e -> {
                lpan.getChildren().remove(remb);
            });

            fdel = false;
        }

        tabel.setEditable(true);
        upp.setDefaultButton(true);

        mapcol.setCellFactory(TextFieldTableCell.<Data>forTableColumn());
        mapcol.setOnEditCommit((CellEditEvent<Data, String> event1) -> {
            Data datas = event1.getRowValue();
            datas.setMapel(event1.getNewValue());
            try {
                Export();
            } catch (IOException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        jamcol.setCellFactory(TextFieldTableCell.<Data>forTableColumn());
        jamcol.setOnEditCommit((CellEditEvent<Data, String> event1) -> {
            Data datas = event1.getRowValue();
            datas.setWaktu(event1.getNewValue());
            try {
                Export();
            } catch (IOException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        gedcol.setCellFactory(TextFieldTableCell.<Data>forTableColumn());
        gedcol.setOnEditCommit((CellEditEvent<Data, String> event1) -> {
            Data datas = event1.getRowValue();
            datas.setGdung(event1.getNewValue());
            try {
                Export();
            } catch (IOException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ruacol.setCellFactory(TextFieldTableCell.<Data>forTableColumn());
        ruacol.setOnEditCommit((CellEditEvent<Data, String> event1) -> {
            Data datas = event1.getRowValue();
            datas.setRuang(event1.getNewValue());
            try {
                Export();
            } catch (IOException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        doscol.setCellFactory(TextFieldTableCell.<Data>forTableColumn());
        doscol.setOnEditCommit((CellEditEvent<Data, String> event1) -> {
            Data datas = event1.getRowValue();
            datas.setDosen(event1.getNewValue());
            try {
                Export();
            } catch (IOException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

//    clear
    private void Add(ActionEvent event) {
        if (!cput && !fdel) {
            InputIn();
        }

        if (!ctab && !tabel.getItems().isEmpty()) {
            TabelIn();
        } else {
            if (fdel) {
                if (tabel.getItems().isEmpty()) {
                    BounceOut b = new BounceOut(remb);
                    b.play();
                    b.setOnFinished(e -> {
                        InputIn();
                        TabelOut();
                        lpan.getChildren().remove(remb);
                    });
                } else if (!tabel.getItems().isEmpty()) {
                    BounceOut b = new BounceOut(remb);
                    b.play();
                    b.setOnFinished(e -> {
                        InputIn();
                        lpan.getChildren().remove(remb);
                    });
                }

                fdel = false;
            } else if (ctab && tabel.getItems().isEmpty()) {
                TabelOut();
            }
        }

        /* System.out.println("tabel size : " + tabel.getItems().size()); */
        submit.setOnAction((ActionEvent e) -> {
            try {
                if (!mapf.getText().equals("") || !jamf.getText().equals("") || !gedf.getText().equals("") || !ruaf.getText().equals("") || !dosf.getText().equals("")) {

                    data.add(new Data(mapf.getText(), jamf.getText(), gedf.getText(), ruaf.getText(), dosf.getText()));

                    if (!ctab && !tabel.getItems().isEmpty()) {
                        TabelIn();
                        lpan.getChildren().remove(remb);
                    }

                    new Data(mapf.getText(), jamf.getText(), gedf.getText(), ruaf.getText(), dosf.getText());
                    
                    mapf.clear();
                    jamf.clear();
                    gedf.clear();
                    ruaf.clear();
                    dosf.clear();

                    Export();
                } else {
                    System.out.println("harap input diisi");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void Dell(ActionEvent event) {
        if (cput) {
            InputOut();
        }

        if (!ctab) {
            TabelIn();
            fdel = true;
        }

        if (!fdel) {
            lpan.getChildren().add(remb);
            new BounceIn(remb).play();
            fdel = true;
        }

        remove.setOnAction((ActionEvent e) -> {
            try {
                Data pilih = tabel.getSelectionModel().getSelectedItem();
                tabel.getItems().remove(pilih);
                Export();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @SuppressWarnings("unchecked")
    public void Collom() {
        mapcol.setCellValueFactory(new PropertyValueFactory<>("Mapel"));
        mapcol.setPrefWidth(73);

        jamcol.setCellValueFactory(new PropertyValueFactory<>("Waktu"));
        jamcol.setPrefWidth(73);

        gedcol.setCellValueFactory(new PropertyValueFactory<>("Gdung"));
        gedcol.setPrefWidth(73);

        ruacol.setCellValueFactory(new PropertyValueFactory<>("Ruang"));
        ruacol.setPrefWidth(73);

        doscol.setCellValueFactory(new PropertyValueFactory<>("Dosen"));
        doscol.setPrefWidth(73);

        tabel.setItems(data);
    }

    @Override
    @SuppressWarnings({"unchecked", "unchecked"})
    public void start(Stage stage) throws Exception {
        try {
//        COMPONENT
            lpan.getStyleClass().add("lpan");
            clpan.getStyleClass().add("clpan");
            blpan.getStyleClass().add("blpan");
            rpan.getStyleClass().add("rpan");

            /*VBox in Content*/
            rbox.getStyleClass().add("rbox");
            lbox.getStyleClass().add("lbox");

            lboxt.getStyleClass().add("lbox");
            lboxb.getStyleClass().add("lbox");

            /*Label*/
            list.getStyleClass().add("list");
            judl.getStyleClass().add("judl");

            mapl.getStyleClass().add("mapl");
            jaml.getStyleClass().add("jaml");
            gedl.getStyleClass().add("gedl");
            rual.getStyleClass().add("rual");
            dosl.getStyleClass().add("dosl");

            /*Image*/
            ImageView addg = new ImageView(new Image("/image/add.png"));
            addg.setFitHeight(22);
            addg.setPreserveRatio(true);

            ImageView uppg = new ImageView(new Image("/image/updateb.png"));
            uppg.setFitHeight(22);
            uppg.setPreserveRatio(true);

            ImageView delg = new ImageView(new Image("/image/trash.png"));
            delg.setFitHeight(22);
            delg.setPreserveRatio(true);

            ImageView defg = new ImageView(new Image("/image/back.png"));
            defg.setFitHeight(22);
            defg.setPreserveRatio(true);

            /*Field*/
            mapf.getStyleClass().add("field");
            jamf.getStyleClass().add("field");
            gedf.getStyleClass().add("field");
            ruaf.getStyleClass().add("field");
            dosf.getStyleClass().add("field");

            /*Tombol*/
            upp.setGraphic(uppg);
            upp.getStyleClass().add("menu");

            del.setGraphic(delg);
            del.getStyleClass().add("menu");

            add.setGraphic(addg);
            add.getStyleClass().add("menu");

            def.setGraphic(defg);
            def.getStyleClass().add("menu");

            submit.getStyleClass().add("submit");
            remove.getStyleClass().add("remove");

            /*Tabel*/
            tabel.setPlaceholder(new Label("belum ada jadwal tertulis"));
            tabel.getItems().setAll(data);
            tabel.getSelectionModel().selectFirst();
            tabel.setItems(data);

//        CAUSE
            remb.setPrefHeight(15);
            remb.setPrefWidth(200);
            remb.setAlignment(Pos.CENTER_LEFT);

            /* tlpan */
            VBox.setVgrow(lpan, Priority.ALWAYS);
            VBox.setVgrow(clpan, Priority.ALWAYS);

            /* rpan */
            rpan.setPrefWidth(400);
            HBox.setHgrow(rbox, Priority.ALWAYS);
            HBox.setHgrow(lbox, Priority.ALWAYS);

            lbox.setPrefWidth(50);
            lbox.setMaxWidth(60);
            lbox.setPadding(new Insets(10, 10, 20, 10));
            VBox.setVgrow(lboxt, Priority.ALWAYS);
            VBox.setVgrow(lboxb, Priority.ALWAYS);

            lboxt.setPrefHeight(200);
            lboxt.setAlignment(Pos.TOP_CENTER);

            lboxb.setPrefHeight(200);
            lboxb.setAlignment(Pos.BOTTOM_CENTER);
            lboxb.setSpacing(10);

            subb.setPrefHeight(15);
            subb.setPrefWidth(Double.MAX_EXPONENT);
            subb.setAlignment(Pos.CENTER_RIGHT);

            rbox.setSpacing(5);
            rbox.setAlignment(Pos.CENTER_LEFT);
            VBox.setMargin(subb, new Insets(15, 0, 0, 0));
            VBox.setMargin(judl, new Insets(0, 0, 5, 0));
            rbox.setPadding(new Insets(10, 30, 10, 10)); //(top/right/bottom/left)

            /* lpan */
            clpan.setAlignment(Pos.CENTER_LEFT);

            blpan.setAlignment(Pos.CENTER_LEFT);

            lpan.setPrefHeight(400);
            lpan.setPadding(new Insets(20));
            lpan.setAlignment(Pos.CENTER_LEFT);
            lpan.setSpacing(5);
            VBox.setMargin(remb, new Insets(5, 40, 0, 0)); //(top/right/bottom/left)

            add.setPrefSize(38, 38);
            upp.setPrefSize(38, 38);
            del.setPrefSize(38, 38);
            def.setPrefSize(38, 38);

            mapb.setPrefHeight(10);
            jamb.setPrefHeight(10);
            gedb.setPrefHeight(10);
            ruab.setPrefHeight(10);
            dosb.setPrefHeight(10);

            mapf.setPrefWidth(Double.MAX_EXPONENT);
            jamf.setPrefWidth(Double.MAX_EXPONENT);
            gedf.setPrefWidth(Double.MAX_EXPONENT);
            ruaf.setPrefWidth(Double.MAX_EXPONENT);
            dosf.setPrefWidth(Double.MAX_EXPONENT);


            /*Default*/
            def.setDefaultButton(false);

            Default(null);
            Collom();

            tabel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tabel.getColumns().addAll(mapcol, jamcol, gedcol, ruacol, doscol);
            tabel.setPrefSize(350, 300);
            tabel.getStyleClass().add("tabel");

//        OUTPUT
            root.setCenter(blpan);
            root.setRight(rpan);

            remb.getChildren().add(remove);
            subb.getChildren().add(submit);

            mapb.getChildren().add(mapf);
            jamb.getChildren().add(jamf);
            gedb.getChildren().add(gedf);
            ruab.getChildren().add(ruaf);
            dosb.getChildren().add(dosf);

            lboxt.getChildren().add(def);
            lboxb.getChildren().addAll(add, upp, del);

            lbox.getChildren().addAll(lboxt, lboxb);
            rbox.getChildren().addAll(judl, mapl, mapb, jaml, jamb, gedl, gedb, rual, ruab, dosl, dosb, subb);

            lpan.getChildren().addAll(list, tabel);
            rpan.getChildren().addAll(lbox, rbox);
            clpan.getChildren().addAll(lpan);
            blpan.getChildren().add(clpan);

//        ACTION
            /*changed*/
            def.setOnAction((ActionEvent e) -> {
                def.setDefaultButton(true);

                add.setDefaultButton(false);
                upp.setDefaultButton(false);
                del.setDefaultButton(false);
                tabel.setEditable(false);

                try {
                    Default(e);
                } catch (IOException ex) {
                    Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            add.setOnAction((ActionEvent e) -> {
                add.setDefaultButton(true);

                def.setDefaultButton(false);
                upp.setDefaultButton(false);
                del.setDefaultButton(false);
                tabel.setEditable(false);

                Add(e);
            });

            del.setOnAction((ActionEvent e) -> {
                del.setDefaultButton(true);

                add.setDefaultButton(false);
                upp.setDefaultButton(false);
                def.setDefaultButton(false);
                tabel.setEditable(false);

                Dell(e);
            });

            upp.setOnAction((ActionEvent e) -> {
                add.setDefaultButton(false);
                def.setDefaultButton(false);
                del.setDefaultButton(false);

                Upp(e);
            });

//        SHOW
            Scene scene = new Scene(root, 800, 400);
            scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

            stage.setScene(scene);
            stage.show();

            new FadeIn(root).play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
