package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Doge Portal v1.0");
        primaryStage.setScene(new Scene(root, 300, 275));

        //kept stage dimensions static
        primaryStage.setWidth(333.0);
        primaryStage.setHeight(333.0);

        //disabled window resizing
        primaryStage.setResizable(false);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
