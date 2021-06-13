package org.gmtdesk.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.gmtdesk.scene.SceneDescriptor;

import java.io.IOException;
import java.util.Objects;

public class SceneLoader {
    private final Stage primaryStage;

    public SceneLoader(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void loadScene(SceneDescriptor descriptor, String title) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(descriptor.getSceneName())));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
