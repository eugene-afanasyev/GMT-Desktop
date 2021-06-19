package org.gmtdesk.utility;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.Point;
import javafx.geometry.Point3D;

import java.awt.geom.Point2D;

public class PointHeight {
    private final Point point;
    private final double height;

    public PointHeight(Point point, double height) {
        this.height = height;
        this.point = point;
    }

    public double getHeight() {
        return height;
    }

    public Point getPoint() {
        return point;
    }
}
