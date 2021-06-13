package org.gmtdesk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.gmtdesk.scene.SceneDescriptor;
import org.gmtdesk.util.SceneLoader;

import java.util.Objects;

public class Main extends Application {

    public static SceneLoader sceneLoader;

    @Override
    public void start(Stage primaryStage) throws Exception{
        sceneLoader = new SceneLoader(primaryStage);
        sceneLoader.loadScene(SceneDescriptor.MAIN);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
