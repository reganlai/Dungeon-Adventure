package View;

import javax.swing.*;
import java.awt.*;

public class ExitGUI extends JPanel {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    private final JFrame myMainFrame;
    private final CardLayout myCardLayout;
    private final JPanel myCardPanel;

    public ExitGUI(final JFrame theMainFrame,
                   final CardLayout theCardLayout,
                   final JPanel theCardPanel) {
        myMainFrame = theMainFrame;
        myCardLayout = theCardLayout;
        myCardPanel = theCardPanel;
    }

}
