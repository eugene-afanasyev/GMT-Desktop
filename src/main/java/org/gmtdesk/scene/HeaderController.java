package org.gmtdesk.scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import org.gmtdesk.utility.SceneLoader;

import java.io.IOException;

public class HeaderController {
    public static SceneDescriptor prevSceneDesk = SceneDescriptor.MAIN;

    public Button backButton;

    public void back(ActionEvent actionEvent) throws IOException {
        SceneLoader sceneLoader = new SceneLoader();
        sceneLoader.loadScene(prevSceneDesk);
    }
}
