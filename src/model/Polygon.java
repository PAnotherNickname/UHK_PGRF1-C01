package model;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    List<Point> points;

    public Polygon() {
        this.points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public int getPointsCount() {
        return points.size();
    }

    public Point getPoint(int i) {
        return points.get(i);
    }
}
