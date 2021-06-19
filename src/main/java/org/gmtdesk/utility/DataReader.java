package org.gmtdesk.utility;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
    private final File outputFile;

    public DataReader(File outputFile) {
        this.outputFile = outputFile;
    }

    public ArrayList<PointHeight> readOutputFile() throws IOException {
        ArrayList<PointHeight> data = new ArrayList<>();
        Scanner reader = new Scanner(outputFile);
        while (reader.hasNextLine()) {
            PointHeight pointHeight;

            String[] rawData = reader.nextLine().split("\\s+");

            Point2D point = new Point();
            point.setLocation(Double.parseDouble(rawData[0]), Double.parseDouble(rawData[1]));

            double height = Double.parseDouble(rawData[2]);

            pointHeight = new PointHeight(point, height);

            data.add(pointHeight);
        }

        for (PointHeight ph :
                data) {
            System.out.println(ph.getPoint());
            System.out.println(ph.getHeight());
        }

        return data;
    }
}
