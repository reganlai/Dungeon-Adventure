package View;

import Model.*;
import Model.Action;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class FightScene extends JPanel {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    /** The main frame shared across different classes to update the same frame.*/
    private final JFrame myMainFrame;
    /** The CardLayout that deals with the screen changing.*/
    private final ExitGUI myExitPanel;
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
    private JButton mySpecialAttack;

    private Monster myMonster;
    private JLabel myMonsterImage;
    private JLabel myMonsterHp;
    private JLabel myMonsterDmg;



    //private int myClass;

    private Hero myHero;

    protected FightScene(final JFrame theMainFrame, final Hero theHero, final ExitGUI theExitPanel,
                         final CardLayout theCardLayout, final JPanel theCardPanel) {
        setLayout(null);
        myMainFrame = theMainFrame;
        myHero = theHero;
        myExitPanel = theExitPanel;
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
        mySpecialAttack = new JButton("Special");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setActionButtons();
        setAction(Action.STANDBY);


        setVisible(true);
        //new javax.swing.Timer(30000, e -> doneFight()).start();
    }
    protected void fight() {
        revertHeroImage();
        generateMonster();
        paintScreen();
    }
    private void paintScreen() {
        //setHeroDmg();

        setMonsterHp();
        setHeroHp();
        //setMonsterDmg();
        setBackground();
        repaint();
    }

    private void doneFight(final boolean theHeroWin) {
        if (theHeroWin) {
            myCardLayout.show(myCardPanel, "Game");
        } else {
            myExitPanel.setGameResult("Lost");
            myCardLayout.show(myCardPanel, "Exit");
        }
    }

    private void setBackground() {
        ImageIcon background = new ImageIcon("images/backgroundimage.png");
        Image scaledPillar = background.getImage().getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        myBackgroundImage.setIcon(new ImageIcon(scaledPillar));
        myBackgroundImage.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        add(myBackgroundImage);
    }

    private void setAction(final Action theAction) {

        myHeroImage.setBounds(280, 180, 190, 200);
        myHeroImage.setBackground(Color.BLACK);
        add(myHeroImage);

        switch(theAction) {
            case STANDBY:
                revertHeroImage();
//                Image hero =  myHero.getImageIcon(Action.STANDBY).getImage().
//                        getScaledInstance(190, 200, Image.SCALE_SMOOTH);
//                myHeroImage.setIcon(new ImageIcon(hero));
                break;
            case ATTACK:
//                Image attack = myHero.getImageIcon(Action.ATTACK).getImage().
//                        getScaledInstance(190, 200, Image.SCALE_SMOOTH);
//                myHeroImage.setIcon(new ImageIcon(attack));
                attack();
                //doneFight();
                break;
            case SPECIAL:
                Image special = myHero.getImageIcon(Action.SPECIAL).getImage().
                        getScaledInstance(190, 200, Image.SCALE_SMOOTH);
                myHeroImage.setIcon(new ImageIcon(special));
                specialAttack();
                break;
            default:
                //Block
                Image block = myHero.getImageIcon(Action.STANDBY).getImage().
                        getScaledInstance(190, 200, Image.SCALE_SMOOTH);
                myHeroImage.setIcon(new ImageIcon(block));
                block();
        }
    }

    private void attack() {
        Action theOpAction = myMonster.getmyAdaptiveCounterAttack().generateAttack();
        if (myHero.isAlive() && myMonster.isAlive()) {
            //myHeroImage.setIcon(myHero.getAttackImage());
            myHeroImage.setIcon(new ImageIcon(myHero.getImageIcon(Action.ATTACK).getImage().
                    getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
            myMonsterImage.setIcon(new ImageIcon(myMonster.getImageIcon(theOpAction).getImage().
                    getScaledInstance(190, 200, Image.SCALE_SMOOTH)));

            myHero.attack(myMonster, theOpAction);
            //setHeroHp(myHero.getMyHp(), myHero.getMyMaxHp());

            //doneFight(true);
        }

        if (!myMonster.isAlive() && myHero.isAlive()) {
            doneFight(true);
        } else if (myMonster.isAlive() && myHero.isAlive()){
            //continue
        } else {
            doneFight(false);
        }
        paintScreen();
    }

    private void block() {
        Action theOpAction = myMonster.getmyAdaptiveCounterAttack().generateAttack();
        myMonster.getmyAdaptiveCounterAttack().recordPlayerAction(Action.BLOCK);
        if (myHero.isAlive() && myMonster.isAlive()) {
            myHeroImage.setIcon(new ImageIcon(myHero.getImageIcon(Action.BLOCK).getImage().
                    getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
            myMonsterImage.setIcon(new ImageIcon(myMonster.getImageIcon(theOpAction).getImage().
                    getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
            myHero.shieldDamage(theOpAction);

            //setHeroHp(myHero.getMyHp(), myHero.getMyMaxHp());

        } else if (!myMonster.isAlive() && myHero.isAlive()){
            doneFight(true);
        } else {
            doneFight(false);
        }
        paintScreen();
    }

    private void specialAttack() {
        Action theOpAction = myMonster.getmyAdaptiveCounterAttack().generateAttack();
        if (myHero.isAlive() && myMonster.isAlive()) {
            myHeroImage.setIcon(new ImageIcon(myHero.getImageIcon(Action.ATTACK).getImage().
                    getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
            myMonsterImage.setIcon(new ImageIcon(myMonster.getImageIcon(theOpAction).getImage().
                    getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
            myHero.specialAbility(myMonster, theOpAction);
            //setHeroHp(myHero.getMyHp(), myHero.getMyMaxHp());
        }

        if (!myMonster.isAlive() && myHero.isAlive()) {
            doneFight(true);
        } else if (myMonster.isAlive() && myHero.isAlive()){
            //continue
        } else {
            doneFight(false);
        }
        paintScreen();

    }

    protected void setHeroHp() {
        myHeroHp.setBounds(340, 135, 190, 30);
        myHeroHp.setForeground(Color.WHITE);
        int theCurrentHP = myHero.getMyHp();
        int theMaxHP = myHero.getMyMaxHp();
        myHeroHp.setVisible(true);
        myHeroHp.setText("HP: " + theCurrentHP + "/" + theMaxHP);
        add(myHeroHp);
    }

    // Displays the range of damage that the hero can deal(static, won't be changed)
    private void setMonsterHp() {
        myMonsterHp.setBounds(590, 135, 190, 30);
        myMonsterHp.setForeground(Color.WHITE);
        int currentHp = myMonster.getMyHp();
        int maxHp = myMonster.getMyMaxHp();
        myMonsterHp.setVisible(true);
        myMonsterHp.setText("HP: " + currentHp + "/" + maxHp);
        add(myMonsterHp);
    }

    private void setHeroDmg() {
        myHeroDmg.setBounds(340, 150, 190, 30);
        myHeroDmg.setForeground(Color.WHITE);
        int minDmg = myHero.getMyMinAttack();
        int maxDmg = myHero.getMyMaxAttack();
        myHeroDmg.setVisible(true);
        myHeroDmg.setText("Damage: " + minDmg + "-" + maxDmg);
        add(myHeroDmg);
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

    private void generateMonster() {
        myMonsterImage.setBounds(530, 180, 190, 200);

        final Random random = new Random();
        int randomInt = random.nextInt(3);
        switch(randomInt) {
            case 0:
                myMonster = new Gremlin();
                //setMonsterHp();
                //setMonsterDmg();
                break;
            case 1:
                myMonster = new Ogre();
                //setMonsterHp();
                //setMonsterDmg();
                break;
            case 2:
                myMonster = new Skeleton();
//                setMonsterHp();
//                setMonsterDmg();
                break;
        }

        Image monster =  myMonster.getImageIcon(Action.STANDBY).getImage().
                getScaledInstance(190, 200, Image.SCALE_SMOOTH);
        myMonsterImage.setIcon(new ImageIcon(monster));
        add(myMonsterImage);

        paintScreen();
    }

    private void setActionButtons() {
        myAttackButton.setBounds(200, 380, 100, 40);

        myAttackButton.addActionListener(theEvent -> {
            setAction(Action.ATTACK);
            //other logic
        });
        myBlockButton.setBounds(300, 380, 100, 40);
        myBlockButton.addActionListener(event -> {
            setAction(Action.BLOCK);
        });
        mySpecialAttack.setBounds(400, 380, 100, 40);
        mySpecialAttack.addActionListener(theEvent -> {
            setAction(Action.SPECIAL);
        });
        add(myAttackButton);
        add(myBlockButton);
        add(mySpecialAttack);
    }

    private void revertHeroImage() {
        Image hero =  myHero.getImageIcon(Action.STANDBY).getImage().
                getScaledInstance(190, 200, Image.SCALE_SMOOTH);
        myHeroImage.setIcon(new ImageIcon(hero));
    }
}
