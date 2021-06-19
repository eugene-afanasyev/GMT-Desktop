package org.gmtdesk.utility;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.Point;

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

            Coordinate lon = Coordinate.fromDegrees(Double.parseDouble(rawData[0]));
            Coordinate lat = Coordinate.fromDegrees(Double.parseDouble(rawData[1]));
            Point point = Point.at(lat, lon);

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
