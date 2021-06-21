package org.gmtdesk.scene;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.util.Pair;
import org.gmtdesk.utility.DataReader;
import org.gmtdesk.utility.PointHeight;
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

    @FXML
    private LineChart<Double, Double> chart;

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

    public void draw_graphic(ArrayList<Pair<Double, Double>> dist) {
        chart.getData().clear();
        XYChart.Series series = new XYChart.Series();
        for(int i = 0; i < dist.size(); i++) {
            var a = dist.get(i).getKey();
            var b = dist.get(i).getValue();
            var aRes = (Number)a;
            var bRes = (Number)b;
            if(!b.isNaN()) {
                series.getData().add(new XYChart.Data(aRes, bRes));
            }
        }
        series.setName("Height");
        chart.getData().add(series);
        chart.setCreateSymbols(false);
    }

    public Double search_value(ArrayList<Pair<Double, Double>> dist, int k) {
        Double left = null;
        Double right = null;
        int t_right = k;
        int t_left = k;
        if(k != 0) {
            left = dist.get(k-1).getValue();
        }

        while(dist.get(t_left).getValue().isNaN() && t_left > 0) {
            t_left--;
        }

        while(dist.get(t_right).getValue().isNaN() && t_right != dist.size()-1) {
            t_right++;
        }

        if(!dist.get(t_right).getValue().isNaN()) {
            right = dist.get(t_right).getValue();
        }

        if(!dist.get(t_left).getValue().isNaN()) {
            left = dist.get(t_left).getValue();
        }

        if(left == null) {
            return right;
        } else if(right == null) {
            return left;
        } else {
            return (left + right) / 2;
        }
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
                var distToHeight = coords2dist(data);
                // TODO: сделать визуализацию данных на графике
                // получить точки можно с помощью commandFactory.getProcessedData()
                draw_graphic(distToHeight);
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

    private ArrayList<Pair<Double, Double>> coords2dist(ArrayList<PointHeight> data) {
        ArrayList<Pair<Double, Double>> distToHeight = new ArrayList<>();
        distToHeight.add(new Pair<>(0.0, data.get(0).getHeight()));
        Point prevPoint = data.get(0).getPoint();

        for (int i = 1; i < data.size(); i++) {
            Point curPoint = data.get(i).getPoint();

            double distance = EarthCalc.gcd.distance(prevPoint, curPoint);

            distToHeight.add(new Pair<>(distance / 1000.0, data.get(i - 1).getHeight()));
            System.out.println(distToHeight.get(i));
        }

        return distToHeight;
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
                System.out.println(e.getMessage());
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
