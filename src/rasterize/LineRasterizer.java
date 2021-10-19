package rasterize;

import model.Line;

import java.awt.*;

public abstract class LineRasterizer {
    Raster raster;
    Color color;

    public LineRasterizer(Raster raster) {
        this.raster = raster;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColor(int color) {
        this.color = new Color(color);
    }

    public void rasterize(Line line) {
        setColor(line.getColor());
        drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }

    public void rasterize(int x1, int y1, int x2, int y2, Color color) {
        setColor(color);
        drawLine(x1, y1, x2, y2);
    }

    public void rasterizeDashed(Line line) {
        setColor(color);
        dashedLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }

    protected abstract void drawLine(int x1, int y1, int x2, int y2);

    protected abstract void dashedLine(int x1, int y1, int x2, int y2);
}

