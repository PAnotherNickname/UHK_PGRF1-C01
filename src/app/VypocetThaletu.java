package app;

import model.Circle;
import model.Point;
import model.Polygon;

import java.awt.*;
import java.awt.event.MouseEvent;

public class VypocetThaletu {
     double x3;
     double y3;
     double d;
     double dP;
     double xk;
     double yk;

    void vypocetThaletu(MouseEvent e, Polygon polygon){
        Point p1 = polygon.getPoint(0);
        Point p2 = polygon.getPoint(1);
        int x1 = p1.getX();
        int x2 = p2.getX();
        int y1 = p1.getY();
        int y2 = p2.getY();
        this.x3 = (x1+x2)/2;
        this.y3 = (y1+y2)/2;
        double x4 = e.getX();
        double y4 = e.getY();
        this.d = Math.sqrt(Math.pow(x4 - x3, 2) + Math.pow(y4 - y3, 2));
        this.dP = Math.sqrt(Math.pow(x3-x1,2) + Math.pow(y3-y1,2));
        double xkP = (((x4-x3)/d)*dP);
        double ykP = (((y4-y3)/d)*dP);
        this.xk = xkP+x3;
        this.yk = ykP+y3;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getdP() {
        return dP;
    }

    public void setdP(double dP) {
        this.dP = dP;
    }


    public double getXk() {
        return xk;
    }

    public void setXk(double xk) {
        this.xk = xk;
    }

    public double getYk() {
        return yk;
    }

    public void setYk(double yk) {
        this.yk = yk;
    }


}
