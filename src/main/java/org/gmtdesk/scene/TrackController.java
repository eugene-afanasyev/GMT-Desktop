package org.gmtdesk.scene;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.util.Pair;
import org.gmtdesk.utility.DataReader;
import org.gmtdesk.utility.SceneLoader;
import org.gmtdesk.utility.TrackCommandFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TrackController {
    public Label inputFileLabel;

    public TextField x0field;
    public TextField y0field;
    public TextField x1field;
    public TextField y1field;

    private File inputFile;
    private File outputFile;

    private ArrayList<TextField> fields;

    private TrackCommandFactory commandFactory;

    public void initialize()
    {
        fields = new ArrayList<>(Arrays.asList(x0field, y0field, x1field, y1field));
        HeaderController.prevSceneDesk = SceneDescriptor.MAIN;
    }

    public void chooseFile(ActionEvent actionEvent)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        inputFile = fileChooser.showOpenDialog(SceneLoader.stage);

        if (inputFile != null)
            inputFileLabel.textProperty().set("Файл: " + inputFile.getName());
    }

    public void process(ActionEvent actionEvent) throws IOException, InterruptedException {
        if (isPointsDefined())
        {
            for (TextField field : fields)
            {
                field.setStyle("-fx-border-width: 0; -fx-font-size: 20");
            }

            if (inputFile != null)
            {
                commandFactory = new TrackCommandFactory(inputFile, getFieldsValues());
                if (outputFile != null)
                    commandFactory.setOutputFile(outputFile);

                commandFactory.execute();

                DataReader dataReader = new DataReader(commandFactory.getOutputFile());

                var data = dataReader.readOutputFile();

                // TODO: сделать визуализацию данных на графике
                // получить точки можно с помощью commandFactory.getProcessedData()
            }

        } else
        {
            for (TextField field : fields)
            {
                field.setStyle("-fx-border-width: 2; -fx-border-color: indianred; -fx-font-size: 20; " +
                        "-fx-border-radius: 2px");
            }
        }
    }

    private double[] getFieldsValues()
    {
        double[] values = new double[4];

        for (int i = 0; i < 4; ++i)
        {
            try
            {
                values[i] = Double.parseDouble(fields.get(i).getText());
            }
            catch (NumberFormatException e) {
                System.out.println(e);
            }
        }

        return values;
    }

    private boolean isPointsDefined()
    {
        boolean isFieldsFilled = true;
        for (TextField field : fields)
        {
            if (field.getText().equals(""))
                isFieldsFilled = false;
        }

        return isFieldsFilled;
    }
}
