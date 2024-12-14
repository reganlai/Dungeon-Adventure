package View;

import Controller.DungeonController;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

/**
 * JFrame that displays the user's inventory.
 *
 * @author Regan Lai
 * @version 1.0
 */
public class InventoryGUI extends JFrame {

    /** JFrame width in pixels. */
    private static final int FRAME_WIDTH = 700;

    /** JFrame height in pixels. */
    private static final int FRAME_HEIGHT = 300;

    /** JLabel that shows health potion image. */
    private JLabel myHealthPotionImage;

    /** JLabel that shows the number of health potion user has. */
    private JLabel myHealthPotionCount;

    /** JButton that allows user to use health potion. */
    private JButton myUseHealthPotion;

    /** JLabel that shows pillar image. */
    private JLabel myPillarImage;

    /** JLabel that shows the number of pillars user has. */
    private JLabel myPillarCount;

    /** Controller of the program. */
    private DungeonController myController;

    /**
     * Initializes the GUI.
     * @param theController the controller of the program
     */
    public InventoryGUI(final DungeonController theController) {
        myController = theController;
        myHealthPotionImage = new JLabel();
        myHealthPotionCount = new JLabel();
        myUseHealthPotion = new JButton();
        myPillarImage = new JLabel();
        myPillarCount = new JLabel();

        setTitle("Inventory");
        setIconImage(new ImageIcon("images/bag.png").getImage());
        setLayout(null);
        getContentPane().setBackground(Color.GRAY);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setHealthPotionImage();
        setHealthPotionCount();
        setPillarImage();
        setPillarCount();
        setUseHealthPotionButton();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Sets the health potion image.
     */
    private void setHealthPotionImage() {
        myHealthPotionImage.setBounds(15, 15, 100, 100);
        ImageIcon originalIcon = new ImageIcon("images/healthpotion.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        myHealthPotionImage.setIcon(new ImageIcon(scaledImage));
        myHealthPotionImage.setVisible(true);
        add(myHealthPotionImage);
    }

    /**
     * Sets the number of health potions user has.
     */
    private void setHealthPotionCount() {
        myHealthPotionCount.setBounds(150, 40, 200, 50);
        myHealthPotionCount.setText("Health Potion: x " + myController.getHero().getMyHealthPotions());
        myHealthPotionCount.setFont(new Font("Arial", Font.PLAIN, 20));
        add(myHealthPotionCount);
    }

    /**
     * Sets the button that allows user to use health potion.
     */
    private void setUseHealthPotionButton() {
        myUseHealthPotion.setBounds(500, 40, 150, 40);
        myUseHealthPotion.setText("Use");
        myUseHealthPotion.setVisible(true);
        myUseHealthPotion.addActionListener(e -> {
            myController.getHero().usePotion();
            setHealthPotionCount();
        });
        add(myUseHealthPotion);
    }

    /**
     * Sets the pillar image.
     */
    private void setPillarImage() {
        myPillarImage.setBounds(15, 145, 100, 100);
        ImageIcon originalIcon = new ImageIcon("images/pillar.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        myPillarImage.setIcon(new ImageIcon(scaledImage));
        myPillarImage.setVisible(true);
        add(myPillarImage);
    }

    /**
     * Sets the number of pillars user has.
     */
    private void setPillarCount() {
        myPillarCount.setBounds(150, 170, 200, 50);
        myPillarCount.setText("Pillars Collected: x " + myController.getHero().getMyPillarsCollected() + "/4");
        myPillarCount.setFont(new Font("Arial", Font.PLAIN, 20));
        add(myPillarCount);
    }
}
