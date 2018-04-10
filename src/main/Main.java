package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // controlling settings
        SPNController spnController = new SPNController();

        // scene settings
        Scene scene = new Scene(createGridPane(), 520, 300);
        scene.getStylesheets().add("stylesheet.css");
        primaryStage.setTitle("KRYSI - Bonusaufgabe SPN");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /******************************
     * Frontend Creation
     ******************************/

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(40);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("mainGrid");

        // create left column
        grid.add(createLeftColumnContent(),0, 0, 1, 2);
        grid.add(createRightColumnTextContent(), 1, 0, 1, 1);
        grid.add(createRightColumnButtonContent(), 1, 1, 1, 1);

        return grid;
    }

    private static GridPane createLeftColumnContent() {
        GridPane formInputs = new GridPane();
        formInputs.setAlignment(Pos.CENTER);
        formInputs.setHgap(10);
        formInputs.setVgap(10);
        formInputs.getStyleClass().add("formInputGrid");

        Label roundsLabel = new Label("Runden");
        TextField rounds  = new TextField();
        formInputs.add(roundsLabel, 0, 0);
        formInputs.add(rounds, 1, 0);

        Label bitsNLabel = new Label("Bits n");
        TextField bitsN  = new TextField();
        formInputs.add(bitsNLabel, 0, 1);
        formInputs.add(bitsN, 1, 1);

        Label bitsMLabel = new Label("Bits m");
        TextField bitsM  = new TextField();
        formInputs.add(bitsMLabel, 0, 2);
        formInputs.add(bitsM, 1, 2);

        Label sboxLabel = new Label("S-Box");
        TextField sbox  = new TextField();
        formInputs.add(sboxLabel, 0, 3);
        formInputs.add(sbox, 1, 3);

        Label bitPermLabel = new Label("Bitpermutation");
        TextField bitPerm  = new TextField();
        formInputs.add(bitPermLabel, 0, 4);
        formInputs.add(bitPerm, 1, 4);

        Label keyLengthLabel = new Label("Schlüssellänge");
        TextField keyLength  = new TextField();
        formInputs.add(keyLengthLabel, 0, 5);
        formInputs.add(keyLength, 1, 5);

        Label modeLabel = new Label("Verschlüsselungs-Modus");
        TextField mode  = new TextField();
        formInputs.add(modeLabel, 0, 6);
        formInputs.add(mode, 1, 6);

        return formInputs;
    }

    private static VBox createRightColumnTextContent() {
        VBox textInputs = new VBox();
        textInputs.setAlignment(Pos.CENTER);
        textInputs.getStyleClass().add("textInputVbox");

        Label keyLabel = new Label("Schlüssel");
        TextField key  = new TextField();
        textInputs.getChildren().add(keyLabel);
        textInputs.getChildren().add(key);

        Label textLabel = new Label("Klartext");
        TextField text  = new TextField();
        textInputs.getChildren().add(textLabel);
        textInputs.getChildren().add(text);

        Label chiffreLabel = new Label("Chiffretext");
        TextField chiffre  = new TextField();
        textInputs.getChildren().add(chiffreLabel);
        textInputs.getChildren().add(chiffre);

        return textInputs;
    }

    private VBox createRightColumnButtonContent() {
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.getStyleClass().add("buttonsVbox");

        Button encryptButton = new Button("Chiffrieren");
        buttons.getChildren().add(encryptButton);

        Button decryptButton = new Button("Dechiffrieren");
        buttons.getChildren().add(decryptButton);

        return buttons;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
