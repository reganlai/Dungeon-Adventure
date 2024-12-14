package View;

import Controller.DungeonController;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * GUI that shows the fighting between user and monster.
 *
 * @author George Njane
 * @version 1.0
 */
public class FightScene extends JPanel implements PropertyChangeListener {

    /** JPanel width in pixels. */
    private static final int FRAME_WIDTH = 1000;

    /** JPanel height in pixels. */
    private static final int FRAME_HEIGHT = 500;

    /** Int representation for hero standing by(not attacking or not blocking). */
    private static final int STANDBY = 0;

    /** Int representation for hero attacking. */
    private static final int ATTACK = 1;

    /** Int representation for hero blocking. */
    private static final int BLOCK = 2;

    /** Int representation for hero using special. */
    private static final int SPECIAL = 3;

    /** The controller of the program. */
    private DungeonController myController;

    /** The background image(a dungeon, won't be changed). */
    private JLabel myBackgroundImage;

    /** The JLabel showing the hero's image according to its current action(standby, attack, block, or special). */
    private JLabel myHeroImage;

    /** The JLabel that shows the current hp and the max hp of the hero. */
    private JLabel myHeroHp;

    /** The JLabel that shows the range of damage the hero can deal. */
    private JLabel myHeroDmg;

    /** The JLabel showing the monster's image according to its current action(standby, attack, or block). */
    private JLabel myMonsterImage;

    /** The JLabel that shows the current hp and the max hp of the monster. */
    private JLabel myMonsterHp;

    /** The JLabel that shows the range of damage the mosnter can deal. */
    private JLabel myMonsterDmg;

    /** The attack button. */
    private JButton myAttackButton;

    /** The block button. */
    private JButton myBlockButton;

    /** The special button. */
    private JButton mySpecialAttack;

    /**
     * Initializes the GUI.
     * @param theController the controller of this program
     */
    protected FightScene(final DungeonController theController) {
        setLayout(null);
        myController = theController;

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

        setVisible(true);
    }

    /**
     * Starts fight between user and monster.
     */
    protected void fight() {
        setAction(STANDBY);
        generateMonster();
    }

    /**
     * Sets position of JLabels.
     */
    public void paintScreen() {
        setHeroDmg();
        setMonsterHp();
        setHeroHp();
        setMonsterDmg();
        setBackground();
        repaint();
    }

    /**
     * Sets background image.
     */
    private void setBackground() {
        ImageIcon background = new ImageIcon("images/backgroundimage.png");
        Image scaledBackground = background.getImage().getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        myBackgroundImage.setIcon(new ImageIcon(scaledBackground));
        myBackgroundImage.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        add(myBackgroundImage);
    }

    /**
     * Does something according to the action chosen by user.
     */
    private void setAction(final int theAction) {
        myHeroImage.setBounds(280, 180, 190, 200);
        myHeroImage.setBackground(Color.BLACK);
        add(myHeroImage);
        myHeroImage.setIcon(new ImageIcon(myController.getMyHeroImage(theAction).getImage().
                getScaledInstance(190, 200, Image.SCALE_SMOOTH)));

        switch(theAction) {
            case ATTACK:
                attack();
                break;
            case SPECIAL:
                specialAttack();
                break;
            case BLOCK:
                block();
        }
    }

    /**
     * Sets the image of the hero according to the chosen action.
     */
    public void setHeroImage(final ImageIcon theImage) {
        myHeroImage.setIcon(new ImageIcon(theImage.getImage().
                getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
    }

    /**
     * Sets the image of the monster according to its current action.
     */
    public void setMyMonsterImage(final ImageIcon theImage) {
        myMonsterImage.setIcon(new ImageIcon(theImage.getImage().
                getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
    }

    /**
     * User attacks.
     */
    private void attack() {
        myController.attack();
    }

    /**
     * User blocks.
     */
    private void block() {
        myController.block();
    }

    /**
     * User uses special attack.
     */
    private void specialAttack() {
        myController.special();
    }

    /**
     * Shows hero's current and max hp.
     */
    protected void setHeroHp() {
        myHeroHp.setBounds(340, 135, 190, 30);
        myHeroHp.setForeground(Color.WHITE);
        int theCurrentHP = myController.getHero().getMyHp();
        int theMaxHP = myController.getHero().getMyMaxHp();
        myHeroHp.setVisible(true);
        myHeroHp.setText("HP: " + theCurrentHP + "/" + theMaxHP);
        add(myHeroHp);
        myHeroHp.repaint();
    }

    /**
     * Shows monster's current and max hp.
     */
    private void setMonsterHp() {
        myMonsterHp.setBounds(590, 135, 190, 30);
        myMonsterHp.setForeground(Color.WHITE);
        int currentHp = myController.getMyMonster().getMyHp();
        int maxHp = myController.getMyMonster().getMyMaxHp();
        myMonsterHp.setVisible(true);
        myMonsterHp.setText("HP: " + currentHp + "/" + maxHp);
        add(myMonsterHp);
    }

    /**
     * Displays the range of damage the hero can do.(Won't be changed)
     */
    private void setHeroDmg() {
        myHeroDmg.setBounds(340, 150, 190, 30);
        myHeroDmg.setForeground(Color.WHITE);
        int minDmg = myController.getHero().getMyMinAttack();
        int maxDmg = myController.getHero().getMyMaxAttack();
        myHeroDmg.setVisible(true);
        myHeroDmg.setText("Damage: " + minDmg + "-" + maxDmg);
        add(myHeroDmg);
    }

    /**
     * Displays the range of damage the hero can do.(Won't be changed)
     */
    private void setMonsterDmg() {
        myMonsterDmg.setBounds(590, 150, 190, 30);
        myMonsterDmg.setForeground(Color.WHITE);
        int minDmg = myController.getMyMonster().getMyMinAttack();
        int maxDmg = myController.getMyMonster().getMyMaxAttack();
        myMonsterDmg.setVisible(true);
        myMonsterDmg.setText("Damage: " + minDmg + "-" + maxDmg);
        add(myMonsterDmg);
    }

    /**
     * Generates a monster for the user to fight.
     */
    private void generateMonster() {
        myMonsterImage.setBounds(530, 180, 190, 200);

        myController.setMonster();
        Image monster = myController.getMyMonsterImage(STANDBY).getImage().
                getScaledInstance(190, 200, Image.SCALE_SMOOTH);
        myMonsterImage.setIcon(new ImageIcon(monster));
        add(myMonsterImage);

        paintScreen();
    }

    /**
     * Sets position and action listener of the action buttons.
     */
    private void setActionButtons() {
        myAttackButton.setBounds(200, 380, 100, 40);

        myAttackButton.addActionListener(theEvent -> {
            setAction(ATTACK);
        });
        myBlockButton.setBounds(300, 380, 100, 40);
        myBlockButton.addActionListener(event -> {
            setAction(BLOCK);
        });
        mySpecialAttack.setBounds(400, 380, 100, 40);
        mySpecialAttack.addActionListener(theEvent -> {
            setAction(SPECIAL);
        });
        add(myAttackButton);
        add(myBlockButton);
        add(mySpecialAttack);
    }

    /**
     * Property change listener for this class.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (theEvt.getPropertyName().equals("use")) {
            setHeroHp();
            repaint();
        }
    }
}
