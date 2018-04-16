package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class SPNView {
    private SPNController controller;
    private SPNModel model;

    // Layouts
    private GridPane view;
    private GridPane formInputs;
    private VBox encryption;
    private VBox decryption;

    // Form input elements
    private TextField rounds;
    private TextField bitsN;
    private TextField bitsM;
    private TextField sbox;
    private TextField bitPerm;
    private TextField keyLength;
    private TextField mode;

    // Encryption elements
    private TextField key;
    private TextField text;
    private TextField chiffre;

    // Buttons
    private Button encryptButton;
    private Button decryptButton;

    public SPNView(SPNController c, SPNModel m) {
        this.controller = c;
        this.model = m;

        createGridPane();
        createModelObservable();
        createEventHanlders();
    }

    /******************************************************
     * Create all view elements with the specific layouts
     ******************************************************/
    public GridPane getView() {
        return this.view;
    }

    private void createGridPane() {
        view = new GridPane();
        view.setHgap(50);
        view.setVgap(100);
        view.setPadding(new Insets(25, 25, 25, 25));
        view.getStyleClass().add("mainGrid");

        // create left column
        view.add(createLeftColumnContent(),0, 0, 1, 2);
        view.add(createRightColumnEncryptContent(), 1, 0, 1, 1);
        view.add(createRightColumnDecryptContent(), 1, 1, 1, 1);
    }

    private GridPane createLeftColumnContent() {
        formInputs = new GridPane();
        formInputs.setAlignment(Pos.CENTER);
        formInputs.setHgap(10);
        formInputs.setVgap(10);
        formInputs.getStyleClass().add("formInputGrid");

        Label roundsLabel = new Label("Runden");
        rounds  = new TextField();
        formInputs.add(roundsLabel, 0, 0);
        formInputs.add(rounds, 1, 0);

        Label bitsNLabel = new Label("Bits n");
        bitsN  = new TextField();
        formInputs.add(bitsNLabel, 0, 1);
        formInputs.add(bitsN, 1, 1);

        Label bitsMLabel = new Label("Bits m");
        bitsM  = new TextField();
        formInputs.add(bitsMLabel, 0, 2);
        formInputs.add(bitsM, 1, 2);

        Label sboxLabel = new Label("S-Box");
        sbox  = new TextField();
        formInputs.add(sboxLabel, 0, 3);
        formInputs.add(sbox, 1, 3);

        Label bitPermLabel = new Label("Bitpermutation");
        bitPerm  = new TextField();
        formInputs.add(bitPermLabel, 0, 4);
        formInputs.add(bitPerm, 1, 4);

        Label keyLabel = new Label("Schlüssel");
        key  = new TextField();
        formInputs.add(keyLabel, 0, 5);
        formInputs.add(key, 1, 5);

        Label keyLengthLabel = new Label("Schlüssellänge");
        keyLength  = new TextField();
        formInputs.add(keyLengthLabel, 0, 6);
        formInputs.add(keyLength, 1, 6);

        Label modeLabel = new Label("Verschlüsselungs-Modus");
        mode  = new TextField();
        formInputs.add(modeLabel, 0, 7);
        formInputs.add(mode, 1, 7);

        return formInputs;
    }

    private VBox createRightColumnEncryptContent() {
        encryption = new VBox();
        encryption.setAlignment(Pos.CENTER);
        encryption.getStyleClass().add("encryptionVbox");

        Label textLabel = new Label("Klartext");
        text  = new TextField();
        encryption.getChildren().add(textLabel);
        encryption.getChildren().add(text);

        encryptButton = new Button("Chiffrieren");
        encryption.getChildren().add(encryptButton);

        return encryption;
    }

    private VBox createRightColumnDecryptContent() {
        decryption = new VBox();
        decryption.setAlignment(Pos.CENTER);
        decryption.getStyleClass().add("decryptionVbox");

        Label chiffreLabel = new Label("Chiffretext");
        chiffre  = new TextField();
        decryption.getChildren().add(chiffreLabel);
        decryption.getChildren().add(chiffre);

        decryptButton = new Button("Dechiffrieren");
        decryption.getChildren().add(decryptButton);

        return decryption;
    }

    /**************************************************************
     * Create observable for all view elements on model properties
     **************************************************************/
    private void createModelObservable() {
        rounds.textProperty().bindBidirectional(model.roundsProperty(), new NumberStringConverter());
        bitsN.textProperty().bindBidirectional(model.bitsNProperty(), new NumberStringConverter());
        bitsM.textProperty().bindBidirectional(model.bitsMProperty(), new NumberStringConverter());
        keyLength.textProperty().bindBidirectional(model.keyLengthProperty(), new NumberStringConverter());
//        sbox.textProperty().bindBidirectional();
//        bitPerm.textProperty().bindBidirectional();
//        mode.textProperty().bindBidirectional();

        key.textProperty().bindBidirectional(model.keyProperty());
        text.textProperty().bindBidirectional(model.textProperty());
        chiffre.textProperty().bindBidirectional(model.chiffreProperty());
    }

    /**************************************************************
     * Create events handlers for all necessary events (decryption)
     **************************************************************/
    private void createEventHanlders() {
        createChangeEvents();
        createButtonsEvents();
    }

    private void createChangeEvents() {
        // @todo
    }

    private void createButtonsEvents() {
        encryptButton.setOnAction(event -> {
            // @todo: start encryption (method)
            System.out.println("encryption");
            controller.Encryption();
        });
        decryptButton.setOnAction(event -> {
            // @todo: start decryption (method)
            System.out.println("decryption");
            controller.Decryption();
        });
    }
}
