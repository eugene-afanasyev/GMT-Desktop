package org.gmtdesk.utility;

import java.awt.geom.Point2D;

public class PointHeight {
    private final Point2D point;
    private final double height;

    public PointHeight(Point2D point, double height) {
        this.height = height;
        this.point = point;
    }

    public double getHeight() {
        return height;
    }

    public Point2D getPoint() {
        return point;
    }
}
