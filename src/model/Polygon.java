package model;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polygon {

    int click = 0;
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

    public void setPoints(int i, Point point) {
        this.points.set(i, point);
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public void removePoint(int point) {
        this.points.remove(point);
    }

    public void removeAll() {
        this.points.clear();
    }

    public int vypocetNejbl(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        ArrayList<Double> lenghts = new ArrayList<>();
        for (Point point : points) {
            int x2 = point.getX();
            int y2 = point.getY();
            double lenght = Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
            lenghts.add(lenght);
        }

        return lenghts.indexOf(Collections.min(lenghts));

    }

}
