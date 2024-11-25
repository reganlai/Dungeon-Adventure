package View;

import Model.Hero;

import javax.swing.*;
import java.awt.*;

public class InventoryGUI extends JFrame {

    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 300;

    private JLabel myHealthPotionImage;
    private JLabel myHealthPotionCount;
    private JButton myUseHealthPotion;

    private JLabel myPillarImage;
    private JLabel myPillarCount;

    private Hero myHero;

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
        myHealthPotionCount.setText("Health Potion: x " + myHero.getMyHealthPotions());
        myHealthPotionCount.setFont(new Font("Arial", Font.PLAIN, 20));
        add(myHealthPotionCount);
    }

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
        myPillarCount.setText("Pillars Collected: x " + myHero.getMyPillarsCollected() + "/4");
        myPillarCount.setFont(new Font("Arial", Font.PLAIN, 20));
        add(myPillarCount);
    }
}
