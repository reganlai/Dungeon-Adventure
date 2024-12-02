package View;

import Model.*;

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
    private JLabel myHeroHp;
    private JLabel myHeroDmg;

    private JButton myAttackButton;
    private JButton mySuperAttack;

    private Monster myMonster;
    private JLabel myMonsterImage;
    private JLabel myMonsterHp;
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
        myHeroHp = new JLabel();


        myMonsterImage = new JLabel();
        myMonsterDmg = new JLabel();
        myMonsterHp = new JLabel();

        myAttackButton = new JButton("Attack");
        myBlockButton = new JButton("Block");
        mySuperAttack = new JButton("Super Attack");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //repaint();

        setAttackButtons();
        setHeroImage("standing");


        setVisible(true);
        //new javax.swing.Timer(30000, e -> doneFight()).start();
    }
    public void fight() {
        generateMonster();
        setHeroDmg();
        setHeroHp();
        setBackground();
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
                break;
            case "attack":
                doneFight();
                break;
            case "block":
                break;
        }
    }

    private void setHeroHp() {
        myHeroHp.setBounds(340, 135, 190, 30);
        myHeroHp.setForeground(Color.WHITE);
        int currentHp = myHero.getMyHp();
        int maxHp = myHero.getMyMaxHp();
        myHeroHp.setVisible(true);
        myHeroHp.setText("HP: " + currentHp + "/" + maxHp);
        add(myHeroHp);
    }

    // Displays the range of damage that the hero can deal(static, won't be changed)
    private void setHeroDmg() {
        myHeroDmg.setBounds(340, 150, 190, 30);
        myHeroDmg.setForeground(Color.WHITE);
        int minDmg = myHero.getMyMinAttack();
        int maxDmg = myHero.getMyMaxAttack();
        myHeroDmg.setVisible(true);
        myHeroDmg.setText("Damage: " + minDmg + "-" + maxDmg);
        add(myHeroDmg);
    }


    private void generateMonster() {
        myMonsterImage.setBounds(530, 180, 190, 200);

        final Random random = new Random();
        int randomInt = random.nextInt(3);
        switch(randomInt) {
            case 0:
                myMonster = new Gremlin();
                Image gremlin =  myMonster.getImageIcon().getImage().
                        getScaledInstance(190, 200, Image.SCALE_SMOOTH);
                myMonsterImage.setIcon(new ImageIcon(gremlin));
                setMonsterHp();
                setMonsterDmg();
                break;
            case 1:
                myMonster = new Ogre();
                Image ogre =  myMonster.getImageIcon().getImage().
                        getScaledInstance(190, 200, Image.SCALE_SMOOTH);
                myMonsterImage.setIcon(new ImageIcon(ogre));
                setMonsterHp();
                setMonsterDmg();
                break;
            case 2:
                myMonster = new Skeleton();
                Image skeleton =  myMonster.getImageIcon().getImage().
                        getScaledInstance(190, 200, Image.SCALE_SMOOTH);
                myMonsterImage.setIcon(new ImageIcon(skeleton));
                setMonsterHp();
                setMonsterDmg();
                break;
        }
        add(myMonsterImage);
    }

    private void setMonsterHp() {
        myMonsterHp.setBounds(590, 135, 190, 30);
        myMonsterHp.setForeground(Color.WHITE);
        int currentHp = myMonster.getMyHp();
        int maxHp = myMonster.getMyMaxHp();
        myMonsterHp.setVisible(true);
        myMonsterHp.setText("HP: " + currentHp + "/" + maxHp);
        add(myMonsterHp);
    }

    //Displays the range of damage that the monster can deal(static, won't be changed)
    private void setMonsterDmg() {
        myMonsterDmg.setBounds(590, 150, 190, 30);
        myMonsterDmg.setForeground(Color.WHITE);
        int minDmg = myMonster.getMyMinAttack();
        int maxDmg = myMonster.getMyMaxAttack();
        myMonsterDmg.setVisible(true);
        myMonsterDmg.setText("Damage: " + minDmg + "-" + maxDmg);
        add(myMonsterDmg);
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
    }



}
