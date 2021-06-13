package org.gmtdesk.utility;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.gmtdesk.scene.SceneDescriptor;

import java.io.IOException;
import java.util.Objects;

public class SceneLoader {
    public static Stage stage;

    public void loadScene(SceneDescriptor descriptor) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(descriptor.getScenePath())));
        stage.setTitle(descriptor.getTitle());
        stage.setScene(new Scene(root));
        stage.show();
    }


}
