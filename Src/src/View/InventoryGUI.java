package View;

import Model.Hero;

import javax.swing.*;
import java.awt.*;

/**
 * The InventoryGUI class displays the items that the user currently have.
 *
 * @author Regan Lai
 * @version 1.0
 */
public class InventoryGUI extends JFrame {

    /** Width for JFrame*/
    private static final int FRAME_WIDTH = 700;

    /** Height for JFrame*/
    private static final int FRAME_HEIGHT = 300;

    /** JLabel that holds the health potion image. */
    private JLabel myHealthPotionImage;

    /** JLabel that holds the number of health potion the user has. */
    private JLabel myHealthPotionCount;

    /** Button that allows user to use health potion. */
    private JButton myUseHealthPotion;

    /** JLabel that holds the health pillar image. */
    private JLabel myPillarImage;

    /** JLabel that holds the number of pillars the user has. */
    private JLabel myPillarCount;

    /** The hero user is using. */
    private Hero myHero;

    /**
     * Initializes the GUI.
     * @param theHero the hero that the user is using right now
     */
    public InventoryGUI(Hero theHero) {
        myHealthPotionImage = new JLabel();
        myHealthPotionCount = new JLabel();
        myUseHealthPotion = new JButton();
        myPillarImage = new JLabel();
        myPillarCount = new JLabel();
        myHero = theHero;

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
     * Sets the position and size of the JLabel that holds the health potion image.
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
     * Displays the number of health potion the user has currently.
     */
    private void setHealthPotionCount() {
        myHealthPotionCount.setBounds(150, 40, 200, 50);
        myHealthPotionCount.setText("Health Potion: x " + myHero.getMyHealthPotions());
        myHealthPotionCount.setFont(new Font("Arial", Font.PLAIN, 20));
        add(myHealthPotionCount);
    }

    /**
     * Sets the position and size of the JButton that allows user to use health potion.
     */
    private void setUseHealthPotionButton() {
        myUseHealthPotion.setBounds(500, 40, 150, 40);
        myUseHealthPotion.setText("Use");
        myUseHealthPotion.setVisible(true);

        myUseHealthPotion.addActionListener(e ->{
            myHero.usePotion();
            setHealthPotionCount();
        });

        add(myUseHealthPotion);
    }

    /**
     * Sets the position and size of the JLabel that holds the image of the pillar.
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
     * Displays the number of pillars the user has currently.
     */
    private void setPillarCount() {
        myPillarCount.setBounds(150, 170, 200, 50);
        myPillarCount.setText("Pillars Collected: x " + myHero.getMyPillarsCollected() + "/4");
        myPillarCount.setFont(new Font("Arial", Font.PLAIN, 20));
        add(myPillarCount);
    }
}
