package app;

import javax.swing.*;

public class AppStart {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Controller2D(800, 600).start());
    }


}
