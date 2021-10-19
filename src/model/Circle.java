package model;

import java.awt.*;

public class Circle {
    int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    int y;
    int h;
    int w;

    public void drawCircle(int x, int y, int h, int w, Graphics g){
        this.x=x;
        this.y=y;
        this.h=h;
        this.w=w;
        g.drawOval(x,y,h,w);
    }
}
