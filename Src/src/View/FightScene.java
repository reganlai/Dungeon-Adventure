package View;

import Model.*;
import Model.Action;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * The FightScene class is responsible for handling fights between the user and the monster.
 *
 * @author Regan Lai
 * @author George Njane
 * @version 1.0
 */
public class FightScene extends JPanel {

    /** Width for JPanel*/
    private static final int FRAME_WIDTH = 1000;

    /** Height for JPanel*/
    private static final int FRAME_HEIGHT = 500;

    /** The main frame shared across different classes to update the same frame.*/
    private final JFrame myMainFrame;

    /** The CardLayout that deals with the screen changing.*/
    private final ExitGUI myExitPanel;

    /** The CardLayout that deals with the screen changing.*/
    private final CardLayout myCardLayout;

    /** The parent panel for all the screens. Used by the CardLayout.*/
    private final JPanel myCardPanel;

    /** The hero. */
    private DungeonCharacter myCharacter;

    /** The label to display the fight comments. */
    private JLabel myActionCommentDisplay;

    /** JLabel that stores only the background image. */
    private JLabel myBackgroundImage;

    /** JLabel that stores only the hero image. */
    private JLabel myHeroImage;

    /** JLabel that displays the hero's hp in "xxx/xxx" format. */
    private JLabel myHeroHp;

    /** JLabel that displays the hero's damage in "xx/xx" format. */
    private JLabel myHeroDmg;

    /** JButton that handles the hero's attacks. */
    private JButton myAttackButton;

    /** JButton that handles the hero's blocks. */
    private JButton myBlockButton;

    /** JButton that handles the hero's special attacks. */
    private JButton mySpecialAttack;

    /** The monster that the user is fighting. */
    private Monster myMonster;

    /** JLabel that stores only the monster image. */
    private JLabel myMonsterImage;

    /** JLabel that displays the monster's hp in "xxx/xxx" format. */
    private JLabel myMonsterHp;

    /** JLabel that displays the monster's damage in "xx/xx" format. */
    private JLabel myMonsterDmg;

    /** The hero that the user is using. */
    private Hero myHero;

    /**
     * Initializes the GUI.
     * @param theMainFrame JFrame shared across different classes
     * @param theHero the hero that the user is using right now
     * @param theExitPanel potentially needing to show this panel in case user loses
     * @param theCardLayout CardLayout that deals with the screen changing
     * @param theCardPanel parent panel for all the screens
     */
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
    }

    /**
     * Starts the fight between the user and monster.
     */
    protected void fight() {
        generateMonster();
        paintScreen();
    }

    /**
     * Calls methods that sets up the GUI.
     */
    private void paintScreen() {
        setHeroDmg();
        setMonsterHp();
        setHeroHp();
        setMonsterDmg();
        setBackground();
        repaint();
    }

    /**
     * Checks if user won the fight.
     * Goes back to Dungeon if user wins.
     * Goes to Exit if user loses.
     */
    private void doneFight(final boolean theHeroWin) {
        if (theHeroWin) {
            myCardLayout.show(myCardPanel, "Game");
        } else {
            myExitPanel.setGameResult("Lost");
            myCardLayout.show(myCardPanel, "Exit");
        }
    }

    /**
     * Hero uses special attack.
     */
    private void setBackground() {
        ImageIcon background = new ImageIcon("images/backgroundimage.png");
        Image scaledBackground = background.getImage().getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        myBackgroundImage.setIcon(new ImageIcon(scaledBackground));
        myBackgroundImage.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        add(myBackgroundImage);
    }

    /**
     * Sets the action chosen by the user, and changes the image of the hero accordingly.
     */
    private void setAction(final Action theAction) {

        myHeroImage.setBounds(280, 180, 190, 200);
        myHeroImage.setBackground(Color.BLACK);
        add(myHeroImage);

        switch(theAction) {
            case STANDBY:
                Image hero =  myHero.getImageIcon(Action.STANDBY).getImage().
                        getScaledInstance(190, 200, Image.SCALE_SMOOTH);
                myHeroImage.setIcon(new ImageIcon(hero));
                break;
            case ATTACK:
                attack();
                break;
            case SPECIAL:
                Image special = myHero.getImageIcon(Action.SPECIAL).getImage().
                        getScaledInstance(190, 200, Image.SCALE_SMOOTH);
                myHeroImage.setIcon(new ImageIcon(special));
                specialAttack();
                break;
            default:
                Image block = myHero.getImageIcon(Action.BLOCK).getImage().
                        getScaledInstance(190, 200, Image.SCALE_SMOOTH);
                myHeroImage.setIcon(new ImageIcon(block));
                block();
        }
    }

    /**
     * Hero uses attack.
     */
    private void attack() {
        Action theOpAction = myMonster.getmyAdaptiveCounterAttack().generateAttack();
        if (myHero.isAlive() && myMonster.isAlive()) {
            myHeroImage.setIcon(new ImageIcon(myHero.getImageIcon(Action.ATTACK).getImage().
                    getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
            myMonsterImage.setIcon(new ImageIcon(myMonster.getImageIcon(theOpAction).getImage().
                    getScaledInstance(190, 200, Image.SCALE_SMOOTH)));

            myHero.attack(myMonster, theOpAction);
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

    /**
     * Hero uses block.
     */
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

    /**
     * Hero uses special attack.
     */
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

    /**
     * Displays the hero's hp in "xxx/xxx" format.
     */
    protected void setHeroHp() {
        myHeroHp.setBounds(340, 135, 190, 30);
        myHeroHp.setForeground(Color.WHITE);
        int theCurrentHP = myHero.getMyHp();
        int theMaxHP = myHero.getMyMaxHp();
        myHeroHp.setVisible(true);
        myHeroHp.setText("HP: " + theCurrentHP + "/" + theMaxHP);
        add(myHeroHp);
    }

    /**
     * Displays the monster's hp in "xxx/xxx" format.
     */
    private void setMonsterHp() {
        myMonsterHp.setBounds(590, 135, 190, 30);
        myMonsterHp.setForeground(Color.WHITE);
        int currentHp = myMonster.getMyHp();
        int maxHp = myMonster.getMyMaxHp();
        myMonsterHp.setVisible(true);
        myMonsterHp.setText("HP: " + currentHp + "/" + maxHp);
        add(myMonsterHp);
    }

    /**
     * Displays the range of damage that the hero can deal(static, won't be changed)
     */
    private void setHeroDmg() {
        myHeroDmg.setBounds(340, 150, 190, 30);
        myHeroDmg.setForeground(Color.WHITE);
        int minDmg = myHero.getMyMinAttack();
        int maxDmg = myHero.getMyMaxAttack();
        myHeroDmg.setVisible(true);
        myHeroDmg.setText("Damage: " + minDmg + "-" + maxDmg);
        add(myHeroDmg);
    }

    /**
     * Displays the range of damage that the monster can deal(static, won't be changed)
     */
    private void setMonsterDmg() {
        myMonsterDmg.setBounds(590, 150, 190, 30);
        myMonsterDmg.setForeground(Color.WHITE);
        int minDmg = myMonster.getMyMinAttack();
        int maxDmg = myMonster.getMyMaxAttack();
        myMonsterDmg.setVisible(true);
        myMonsterDmg.setText("Damage: " + minDmg + "-" + maxDmg);
        add(myMonsterDmg);
    }

    /**
     * Generates a random monster for user to fight.
     */
    private void generateMonster() {
        myMonsterImage.setBounds(530, 180, 190, 200);

        final Random random = new Random();
        int randomInt = random.nextInt(3);
        switch(randomInt) {
            case 0:
                myMonster = new Gremlin();
                break;
            case 1:
                myMonster = new Ogre();
                break;
            case 2:
                myMonster = new Skeleton();
                break;
        }

        Image monster =  myMonster.getImageIcon(Action.STANDBY).getImage().
                getScaledInstance(190, 200, Image.SCALE_SMOOTH);
        myMonsterImage.setIcon(new ImageIcon(monster));
        add(myMonsterImage);

        paintScreen();
    }

    /**
     * Sets the position for the action buttons(attack, block, special).
     * And then adds action listeners to the buttons.
     */
    private void setActionButtons() {
        myAttackButton.setBounds(200, 380, 100, 40);

        myAttackButton.addActionListener(theEvent -> {
            setAction(Action.ATTACK);
        });
        myBlockButton.setBounds(300, 380, 100, 40);
        myBlockButton.addActionListener(event -> {
            setAction(Action.ATTACK);
        });
        mySpecialAttack.setBounds(400, 380, 100, 40);
        mySpecialAttack.addActionListener(theEvent -> {
            setAction(Action.SPECIAL);
        });
        add(myAttackButton);
        add(myBlockButton);
        add(mySpecialAttack);
    }
}
