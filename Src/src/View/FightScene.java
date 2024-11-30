package View;

import Model.DungeonCharacter;
import Model.Hero;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class FightScene extends JPanel {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    /** The main frame shared across different classes to update the same frame.*/
    private final JFrame myMainFrame;
    /** The CardLayout that deals with the screen changing.*/
    private final CardLayout myCardLayout;
    /** The parent panel for all the screens. Used by the CardLayout.*/
    private final JPanel myCardPanel;

    /** The block button. */
    private JButton myBlockButton;

    /** The hero. */
    private DungeonCharacter myCharacter;

    /** The label to display the fight comments. */
    private JLabel myActionCommentDisplay;

    private JLabel myBackgroundImage;
    private JLabel myHeroImage;
    private JLabel myMonsterImage;
    private JButton myAttackButton;
    private JButton mySuperAttack;

    private JLabel myHeroHp;
    private JLabel myMonsterHp;
    private JLabel myHeroDmg;
    private JLabel myMonsterDmg;

    //private int myClass;

    private Hero myHero;

    protected FightScene(final JFrame theMainFrame, final Hero theHero,
                         final CardLayout theCardLayout, final JPanel theCardPanel) {
        setLayout(null);
        myMainFrame = theMainFrame;
        myHero = theHero;
        myCardLayout = theCardLayout;
        myCardPanel = theCardPanel;

        myBackgroundImage = new JLabel();
        myHeroImage = new JLabel();
        myHeroDmg = new JLabel();

        myAttackButton = new JButton("Attack");
        myBlockButton = new JButton("Block");
        mySuperAttack = new JButton("Super Attack");
        //myClass = theHeroClass;
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //repaint();

        setAttackButtons();
        initializeHeroDmg();
        setHeroImage("standing");

        setBackground();


        setVisible(true);

        new javax.swing.Timer(30000, e -> doneFight()).start();
    }

    private void doneFight() {
        myCardLayout.show(myCardPanel, "Game");
    }

//    protected void paintComponent(Graphics g) {
//        /**Placeholder code. For simulation purposes*/
//        super.paintComponent(g); // Call the parent class's method to ensure proper painting
//        // Cast to Graphics2D for more options
//        Graphics2D g2d = (Graphics2D) g;
//
//        // Set background color (optional)
//        //setBackground(Color.RED);
//        ImageIcon backgroundIcon = new ImageIcon("images/backgroundimage.png");
//        Image backgroundImage = backgroundIcon.getImage();
//        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//
//
//        // Draw a rectangle
//        g2d.setColor(Color.BLUE);
//        g2d.fillRect(50, 50, 100, 100);
//
//
//        // Draw a string
//        g2d.setColor(Color.BLACK);
//        g2d.setFont(new Font("Arial", Font.BOLD, 16));
//        g2d.drawString("Fighttttttt!", 50, 200);
//
//        // Draw a line
//        g2d.setColor(Color.GREEN);
//        g2d.drawLine(50, 250, 300, 250);
//    }

    private void setBackground() {
        ImageIcon background = new ImageIcon("images/backgroundimage.png");
        Image scaledPillar = background.getImage().getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        myBackgroundImage.setIcon(new ImageIcon(scaledPillar));
        myBackgroundImage.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        add(myBackgroundImage);
    }

    private void setHeroImage(final String theStance) {

        myHeroImage.setBounds(280, 180, 190, 200);
        myHeroImage.setBackground(Color.BLACK);
        add(myHeroImage);

        switch(theStance) {
            case "standing":
                Image hero =  myHero.getImageIcon().getImage().
                        getScaledInstance(190, 200, Image.SCALE_SMOOTH);
                myHeroImage.setIcon(new ImageIcon(hero));
                setHeroDmg();
                break;
//                if (myClass == 0) {
//                    ImageIcon thief = new ImageIcon("images/standingthief.png");
//                    Image scaledThief = thief.getImage().getScaledInstance(190, 200, Image.SCALE_SMOOTH);
//                    myHeroImage.setIcon(new ImageIcon(scaledThief));
//                } else if (myClass == 1) {
//                    ImageIcon warrior = new ImageIcon("images/standingwarrior.png");
//                    Image scaledWarrior = warrior.getImage().getScaledInstance(190, 200, Image.SCALE_SMOOTH);
//                    myHeroImage.setIcon(new ImageIcon(scaledWarrior));
//                } else {
//                    ImageIcon priestess = new ImageIcon("images/standingpriestess.png");
//                    Image scaledPriestess = priestess.getImage().getScaledInstance(190, 200, Image.SCALE_SMOOTH);
//                    myHeroImage.setIcon(new ImageIcon(scaledPriestess));
//                }
//                break;
            case "attack":
                //setHeroDmg();
                break;
            case "block":
                break;
        }
    }

    private void setHeroHp() {

    }

    // Displays the range of damage that the hero can deal(static, won't be changed)
    private void setHeroDmg() {
        int minDmg = myHero.getMyMinAttack();
        int maxDmg = myHero.getMyMaxAttack();
        myHeroDmg.setVisible(true);
        myHeroDmg.setText("Damage: " + minDmg + "-" + maxDmg);
    }

    private void initializeHeroDmg() {
        //myHeroDmg.setText("placeholder");
        myHeroDmg.setBounds(300, 150, 190, 30);
        myHeroDmg.setForeground(Color.WHITE);
        add(myHeroDmg);
    }

    private void setMonsterImage() {

    }

    private void setMonsterHp() {

    }

    //Displays the range of damage that the monster can deal(static, won't be changed)
    private void setMonsterDmg() {

    }

    private void setAttackButtons() {
        myAttackButton.setBounds(300, 380, 150, 40);
        myAttackButton.addActionListener(theEvent -> {
            setHeroImage("attack");
            //other logic
        });
        mySuperAttack.setBounds(550, 380, 150, 40);
        add(myAttackButton);
        add(mySuperAttack);
    }

    public void setHero(final Hero theHero) {
        myHero = theHero;
        //setHeroDmg();
    }



}
