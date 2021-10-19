package rasterize;

import model.Line;
import model.Polygon;

public class PolygonRasterizer {
    private final LineRasterizer rasterizer;

    public PolygonRasterizer(LineRasterizer rasterizer) {
        this.rasterizer = rasterizer;
    }

    public void rasterize(Polygon polygon, boolean dashad) {

        for (int i = 0; i < polygon.getPointsCount() - 1; i++) {
            if (i > 0) {
                rasterizer.rasterize(new Line(polygon.getPoint(i - 1), polygon.getPoint(i), 0xff0000));
            }
            if (i == polygon.getPointsCount() - 2 && dashad) {
                rasterizer.rasterizeDashed(new Line(polygon.getPoint(i), polygon.getPoint(i + 1), 0xff0000));
                rasterizer.rasterizeDashed(new Line(polygon.getPoint(i + 1), polygon.getPoint(0), 0xff0000));
            } else if (i == polygon.getPointsCount() - 2 && !dashad) {
                rasterizer.rasterize(new Line(polygon.getPoint(i), polygon.getPoint(i + 1), 0xff0000));
                rasterizer.rasterize(new Line(polygon.getPoint(i + 1), polygon.getPoint(0), 0xff0000));
            }
        }
    }
}
