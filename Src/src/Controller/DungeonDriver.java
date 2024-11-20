package Controller;

import View.DungeonGUI;
import java.awt.EventQueue;

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