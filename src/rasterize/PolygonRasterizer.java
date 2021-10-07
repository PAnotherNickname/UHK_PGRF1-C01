package rasterize;

import model.Line;
import model.Polygon;

public class PolygonRasterizer {
    private final LineRasterizer rasterizer;

    public PolygonRasterizer(LineRasterizer rasterizer) {
        this.rasterizer = rasterizer;
    }

    public void rasterize(Polygon polygon) {
        if(polygon.getPointsCount() != 4)
            return;

        rasterizer.rasterize(new Line(polygon.getPoint(0), polygon.getPoint(1), 0xff0000));
        rasterizer.rasterize(new Line(polygon.getPoint(1), polygon.getPoint(2), 0xff0000));
        rasterizer.rasterize(new Line(polygon.getPoint(2), polygon.getPoint(3), 0xff0000));
        rasterizer.rasterize(new Line(polygon.getPoint(3), polygon.getPoint(0), 0xff0000));

        // pro všechny vrcholy polygonu
        // {
        // 1. vezmu aktuální vrchol
        // 2. vezmu vrchol, který následuje - POZOR! co když už žádný další není?
        // 3. mám 2 vrcholy, mohu spojit úsečkou
        // }
    }
}
