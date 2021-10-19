package app;

import model.Circle;
import model.Line;
import model.Point;
import model.Polygon;
import rasterize.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

/**
 * trida pro kresleni na platno: zobrazeni pixelu, ovladani mysi
 *
 * @author PGRF FIM UHK
 * @version 2021
 */
public class Controller2D {
    private final JPanel panel;
    private final RasterBufferedImage raster;
    private final LineRasterizer lineRasterizer;
    private final PolygonRasterizer polygonRasterizer;
    private final Circle circle = new Circle();
    private final Key key = new Key();
    private final Polygon polygon = new Polygon();
    private final VypocetThaletu vypocetThaletu = new VypocetThaletu();

    private boolean modeDragged;

    public Controller2D(int width, int height) {
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        raster = new RasterBufferedImage(width, height);
        lineRasterizer = new LineRasterizerGraphics(raster);
        polygonRasterizer = new PolygonRasterizer(lineRasterizer);

        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        key.keys(polygon, raster, panel);

        Graphics g = raster.getGraphics();


        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                raster.clear();
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (polygon.getPointsCount() == 0) {
                    } else {
                        int indx = polygon.vypocetNejbl(e);
                        polygon.setPoints(indx, new Point(e.getX(), e.getY()));
                        polygonRasterizer.rasterize(polygon, false);
                        panel.repaint();
                    }
                } else {
                    if (polygon.getPointsCount() == 0) {
                        Point endPoint = new Point(e.getX(), e.getY());
                        Line line = new Line(endPoint, endPoint, 0xff0000);
                        lineRasterizer.rasterize(line);
                        panel.repaint();
                    } else if (polygon.getPointsCount() >= 3 && key.getMode() == 1) {
                        polygon.removeAll();
                        raster.clear();
                        panel.repaint();
                        return;
                    } else if (polygon.getPointsCount() >= 2 && key.getMode() == 1) {
                        if (modeDragged) {
                            circle.drawCircle(circle.getX(), circle.getY(), circle.getH(), circle.getW(), g);
                        } else {
                            vypocetThaletuController(e, g);
                        }
                    }
                    if (polygon.getPointsCount() >= 2 && key.getMode() == 1) {
                        polygon.addPoint(new Point(vypocetThaletu.getXk(), vypocetThaletu.getYk()));
                    } else {
                        polygon.addPoint(new Point(e.getX(), e.getY()));
                    }
                    polygonRasterizer.rasterize(polygon, false);
                    panel.repaint();
                    modeDragged = false;
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                raster.clear();
                Point endPoint = new Point(e.getX(), e.getY());


                if (polygon.getPointsCount() == 1) {
                    Line line = new Line(polygon.getPoint(0), endPoint, 0xff0000);
                    lineRasterizer.rasterizeDashed(line);
                    panel.repaint();
                } else if (key.getMode() == 1 && polygon.getPointsCount() == 2) {
                    vypocetThaletuController(e, g);
                    modeDragged = true;
                    polygon.addPoint(new Point(vypocetThaletu.getXk(), vypocetThaletu.getYk()));
                    polygonRasterizer.rasterize(polygon, true);
                    panel.repaint();
                    polygon.removePoint(polygon.getPointsCount() - 1);
                } else if (key.getMode() == 1 && polygon.getPointsCount() >= 3) {
                } else {
                    polygon.addPoint(new Point(e.getX(), e.getY()));
                    polygonRasterizer.rasterize(polygon, true);
                    panel.repaint();
                    polygon.removePoint(polygon.getPointsCount() - 1);
                }
            }
        });

    }

    public void clear() {
        raster.clear();
    }

    public void present(Graphics graphics) {
        raster.repaint(graphics);
    }

    public void start() {
        clear();
        panel.repaint();
    }

    void vypocetThaletuController(MouseEvent e, Graphics g) {
        vypocetThaletu.vypocetThaletu(e, polygon);
        double dP = vypocetThaletu.getdP();
        circle.drawCircle((int) (vypocetThaletu.getX3() - dP), (int) (vypocetThaletu.getY3() - dP), (int) (dP * 2), (int) (dP * 2), g);
    }
}
