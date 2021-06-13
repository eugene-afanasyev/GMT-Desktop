package org.gmtdesk.scene;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import org.gmtdesk.utility.SceneLoader;

import java.io.File;

public class TrackController {
    public Label inputFileLabel;

    private File inputFile;

    public void initialize() {
        HeaderController.prevSceneDesk = SceneDescriptor.MAIN;
    }

    public void chooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        inputFile = fileChooser.showOpenDialog(SceneLoader.stage);

        if (inputFile != null)
            inputFileLabel.textProperty().set("Файл: " + inputFile.getName());
    }


}
