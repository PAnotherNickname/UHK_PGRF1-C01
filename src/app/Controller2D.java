package app;

import model.Line;
import model.Point;
import model.Polygon;
import rasterize.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * trida pro kresleni na platno: zobrazeni pixelu, ovladani mysi
 * 
 * @author PGRF FIM UHK
 * @version 2020
 */
public class Controller2D {

	private JPanel panel;
	private RasterBufferedImage raster;
	LineRasterizer lineRasterizer;
	PolygonRasterizer polygonRasterizer;

	Polygon polygon = new Polygon();

	public Controller2D(int width, int height) {
		JFrame frame = new JFrame();

		frame.setLayout(new BorderLayout());
		frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		raster = new RasterBufferedImage(width, height);
		lineRasterizer = new LineRasterizerGraphics(raster);
		//lineRasterizer = new TrivialLineRasterizer(raster);
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

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
					polygon.addPoint(new Point(e.getX(), e.getY()));

				polygonRasterizer.rasterize(polygon);
				panel.repaint();
			}
		});
/*
		panel.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);

				raster.clear();

				Point startPoint = new Point(400, 300);
				Point endPoint = new Point(e.getX(), e.getY());
				Line line = new Line(startPoint, endPoint, 0xff0000);

				lineRasterizer.rasterize(line);

				panel.repaint();
			}
		});
 */
	}

	public void clear() {
		raster.clear();
	}

	public void present(Graphics graphics) {
		raster.repaint(graphics);
	}

	public void start() {
		clear();

		int a = 10;
		int b = 7;
		float c = a / (float) b;

		panel.repaint();
	}
}
