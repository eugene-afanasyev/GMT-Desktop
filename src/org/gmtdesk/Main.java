package org.gmtdesk;

import javafx.application.Application;
import javafx.stage.Stage;
import org.gmtdesk.scene.SceneDescriptor;
import org.gmtdesk.utility.SceneLoader;

public class Main extends Application {

    public static SceneLoader sceneLoader = new SceneLoader();

    @Override
    public void start(Stage primaryStage) throws Exception{
        SceneLoader.stage = primaryStage;
        sceneLoader.loadScene(SceneDescriptor.MAIN);

        System.out.println(System.getProperty("os.name"));
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
