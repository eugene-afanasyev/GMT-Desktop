package org.gmtdesk.utility;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

enum OSVersion {
    LINUX ("Linux"),
    WINDOWS ("Windows");

    private String name;

    OSVersion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class TrackCommandFactory {
    private final OSVersion osVersion;
    private final File inputFile;
    private final double[] points;
    private File outputFile;

    private StringBuilder command = new StringBuilder();

    public TrackCommandFactory(File dbFile, double[] points) throws IOException {
        if (System.getProperty("os.name").startsWith("Linux")) {
            osVersion = OSVersion.LINUX;
            command.append("gmt grdtrack ");
        }
        else {
            osVersion = OSVersion.WINDOWS;
            command.append("grdtrack ");
        }

        this.points = points;
        this.inputFile = dbFile;

        outputFile = new File(".out.txt");
        if (outputFile.createNewFile()) {
            System.out.println(outputFile.getAbsolutePath());
        }
    }

    public void execute() throws IOException, InterruptedException {
        StringBuilder pointsArg = new StringBuilder(new String("-E"));
        for (int i = 0; i < 4; i++) {
            pointsArg.append(points[i]);
            if (i != 3)
                pointsArg.append('/');

        }
        command.append("-G").append(inputFile.getAbsolutePath()).append(" ");
        command.append(pointsArg).append(" ");
        command.append("> ").append(outputFile.getAbsolutePath());

        System.out.println(command);

        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command.toString());
        process.waitFor();

        readOutputFile();
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

    public ArrayList<double[]> getProcessedData() {
        ArrayList<double[]> data = new ArrayList<>();
        return data;
    }

    public OSVersion getOsVersion() {
        return osVersion;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    private void buildCommand() {

    }
}
