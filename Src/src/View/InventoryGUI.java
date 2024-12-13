package View;

import Controller.DungeonController;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InventoryGUI extends JFrame {

    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 300;
    private final PropertyChangeSupport myPCS = new PropertyChangeSupport(this);

    private JLabel myHealthPotionImage;
    private JLabel myHealthPotionCount;
    private JButton myUseHealthPotion;

    private JLabel myPillarImage;
    private JLabel myPillarCount;
    private DungeonController myController;

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

    private void setHealthPotionImage() {
        myHealthPotionImage.setBounds(15, 15, 100, 100);
        ImageIcon originalIcon = new ImageIcon("images/healthpotion.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        myHealthPotionImage.setIcon(new ImageIcon(scaledImage));
        myHealthPotionImage.setVisible(true);
        add(myHealthPotionImage);
    }

    private void setHealthPotionCount() {
        myHealthPotionCount.setBounds(150, 40, 200, 50);
        myHealthPotionCount.setText("Health Potion: x " + myController.getHero().getMyHealthPotions());
        myHealthPotionCount.setFont(new Font("Arial", Font.PLAIN, 20));
        add(myHealthPotionCount);
    }

    private void setUseHealthPotionButton() {
        myUseHealthPotion.setBounds(500, 40, 150, 40);
        myUseHealthPotion.setText("Use");
        myUseHealthPotion.setVisible(true);

        myUseHealthPotion.addActionListener(e -> {
            myPCS.firePropertyChange("use", null, null);
            myController.getHero().usePotion();
            setHealthPotionCount();
        });

        add(myUseHealthPotion);
    }

    private void setPillarImage() {
        myPillarImage.setBounds(15, 145, 100, 100);
        ImageIcon originalIcon = new ImageIcon("images/pillar.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        myPillarImage.setIcon(new ImageIcon(scaledImage));
        myPillarImage.setVisible(true);
        add(myPillarImage);
    }

    private void setPillarCount() {
        myPillarCount.setBounds(150, 170, 200, 50);
        myPillarCount.setText("Pillars Collected: x " + myController.getHero().getMyPillarsCollected() + "/4");
        myPillarCount.setFont(new Font("Arial", Font.PLAIN, 20));
        add(myPillarCount);
    }
    /**
     * Adds a listener for property change events in this class.
     *
     * @param theListener A property change listener to add.
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }

    /**
     * Removes a listener for property change events from this class.
     *
     * @param theListener A property change listener to remove.
     */
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(theListener);
    }
}
