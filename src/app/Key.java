package app;

import model.Polygon;
import rasterize.RasterBufferedImage;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Key {

    private int mode = 0;

    public void keys(Polygon polygon, RasterBufferedImage raster, JPanel panel) {

        Action changeMode = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (mode == 1) {
                    mode = 0;
                    raster.setModeString("Jste v rezimu n-uhelnik, pro prepinani rezimu stisknete T, pro mazani C, pro redagovani prave tlacitko mysi");
                } else {
                    mode = 1;
                    raster.setModeString("Jste v rezimu Thaletova kruznice, pro prepinani rezimu stisknete T, pro mazani C, pro redagovani prave tlacitko mysi");
                }
                polygon.removeAll();
                raster.clear();
                panel.repaint();
            }
        };

        Action clearAll = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                polygon.removeAll();
                raster.clear();
                panel.repaint();
            }
        };

        panel.getInputMap().put(KeyStroke.getKeyStroke("C"),
                "clearAll");
        panel.getActionMap().put("clearAll",
                clearAll);

        panel.getInputMap().put(KeyStroke.getKeyStroke("T"),
                "changeMode");
        panel.getActionMap().put("changeMode",
                changeMode);

    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

}
