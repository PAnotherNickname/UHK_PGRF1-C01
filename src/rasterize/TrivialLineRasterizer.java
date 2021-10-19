package rasterize;

public class TrivialLineRasterizer extends LineRasterizer {

    public TrivialLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    protected void dashedLine(int x1, int y1, int x2, int y2) {
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {

        /*
        Triviální algoritmus
        Výhoda: postup použitelný i pro složitější křivky
        Nevýhoda: násobení a sčítání v plovoucí řádové čárce neefektivní
         */


        int dx = x2 - x1;
        int dy = y2 - y1;

        float k = dy / (float) dx;
        float q = y1 - k * x1;

        if (x1 > x2) {
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
        }

        for (int x = x1; x <= x2; x++) {
            float y = k * x + q;
            raster.setPixel(x, Math.round(y), this.color.getRGB());
        }

    }
}
