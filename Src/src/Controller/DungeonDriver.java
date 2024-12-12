package Controller;

import View.DungeonGUI;
import java.awt.EventQueue;

/**
 * The DungeonDriver class acts as the entry point for this program.
 * @author George Njane
 * @version 1.0
 */
public class DungeonDriver {

    /**
     * Private constructor to prevent construction of instances.
     */
    private DungeonDriver() {
        // do nothing
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DungeonGUI();
            }
        });
    }
}