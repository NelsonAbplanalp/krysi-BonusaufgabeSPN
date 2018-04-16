package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SPNModel model = new SPNModel();
        SPNController controller = new SPNController(model);
        SPNView view = new SPNView(controller, model);

        // @todo: remove when spn test successful!
        controller.Encryption();

        // scene settings
        Scene scene = new Scene(view.getView(), 540, 320);
        scene.getStylesheets().add("stylesheet.css");
        primaryStage.setTitle("KRYSI - Bonusaufgabe SPN");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
