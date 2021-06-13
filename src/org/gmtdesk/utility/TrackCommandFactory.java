package org.gmtdesk.utility;

import java.io.File;

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

    private String command;

    TrackCommandFactory(File dbFile, double[] points) {
        if (System.getProperty("os.name").startsWith("Linux"))
            osVersion = OSVersion.LINUX;
        else
            osVersion = OSVersion.WINDOWS;

        this.points = points;
        this.inputFile = dbFile;

        outputFile = new File("/tmp/out.txt");
    }

    public void execute() {

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
