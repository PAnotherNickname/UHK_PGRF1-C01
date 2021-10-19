package rasterize;

import java.awt.*;

public class LineRasterizerGraphics extends LineRasterizer {

    Graphics g = ((RasterBufferedImage) raster).getImg().getGraphics();

    public LineRasterizerGraphics(Raster raster) {
        super(raster);
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {
        g.setColor(this.color);
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    protected void dashedLine(int x1, int y1, int x2, int y2) {
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{10}, 0);
        g2d.setStroke(dashed);
        g2d.setColor(this.color);
        g2d.drawLine(x1, y1, x2, y2);

    }


}
