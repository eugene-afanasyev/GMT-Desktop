package org.gmtdesk.scene;

import javafx.event.ActionEvent;
import org.gmtdesk.utility.SceneLoader;

import java.io.IOException;

public class MainSceneController {

    public void toTrackScene(ActionEvent actionEvent) throws IOException {
        SceneLoader sceneLoader = new SceneLoader();
        sceneLoader.loadScene(SceneDescriptor.GRDTRACK);
    }
}
