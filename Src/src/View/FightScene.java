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

public class FightScene extends JPanel implements PropertyChangeListener {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    private static final int STANDBY = 0;
    private static final int ATTACK = 1;
    private static final int BLOCK = 2;
    private static final int SPECIAL = 3;
    /** The main frame shared across different classes to update the same frame.*/
    private final DungeonGUI myMainFrame;
    /** The CardLayout that deals with the screen changing.*/

    private DungeonController myController;
//    private final ExitGUI myExitPanel;
//    private final CardLayout myCardLayout;
//    /** The parent panel for all the screens. Used by the CardLayout.*/
//    private final JPanel myCardPanel;

    /** The block button. */
    private JButton myBlockButton;

    /** The label to display the fight comments. */
    private JLabel myActionCommentDisplay;

    private JLabel myBackgroundImage;

    private JLabel myHeroImage;
    private JLabel myHeroHp;
    private JLabel myHeroDmg;

    private JButton myAttackButton;
    private JButton mySpecialAttack;
    private JLabel myMonsterImage;
    private JLabel myMonsterHp;
    private JLabel myMonsterDmg;

//    protected FightScene(final JFrame theMainFrame, final Hero theHero, final ExitGUI theExitPanel,
//                         final CardLayout theCardLayout, final JPanel theCardPanel) {
    protected FightScene(final DungeonGUI theMainFrame, final DungeonController theController) {
        setLayout(null);
        myMainFrame = theMainFrame;
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
    protected void fight() {
        setAction(STANDBY);
        generateMonster();
    }
    public void paintScreen() {
        //setHeroDmg();
        setMonsterHp();
        setHeroHp();
        //setMonsterDmg();
        setBackground();
        repaint();
    }

    private void doneFight(final boolean theHeroWin) {
        System.out.println(theHeroWin);
//        if (theHeroWin) {
//            myCardLayout.show(myCardPanel, "Game");
//        } else {
//            myExitPanel.setGameResult("Lost");
//            myCardLayout.show(myCardPanel, "Exit");
//        }
    }

    private void setBackground() {
        ImageIcon background = new ImageIcon("images/backgroundimage.png");
        Image scaledPillar = background.getImage().getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        myBackgroundImage.setIcon(new ImageIcon(scaledPillar));
        myBackgroundImage.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        add(myBackgroundImage);
    }

    private void setAction(final int theAction) {

        myHeroImage.setBounds(280, 180, 190, 200);
        myHeroImage.setBackground(Color.BLACK);
        add(myHeroImage);

        myHeroImage.setIcon(new ImageIcon(myController.getMyHeroImage(theAction).getImage().
                getScaledInstance(190, 200, Image.SCALE_SMOOTH)));

        switch(theAction) {
            case ATTACK:
                attack();
                //doneFight();
                break;
            case SPECIAL:
                specialAttack();
                break;
            case BLOCK:
                //Block
                block();
        }
    }

    public void setHeroImage(final ImageIcon theImage) {
        myHeroImage.setIcon(new ImageIcon(theImage.getImage().
                getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
    }
    public void setMyMonsterImage(final ImageIcon theImage) {
        myMonsterImage.setIcon(new ImageIcon(theImage.getImage().
                getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
    }

    private void attack() {
        myController.attack();
//        Action theOpAction = myMonster.getmyAdaptiveCounterAttack().generateAttack();
//        if (myHero.isAlive() && myMonster.isAlive()) {
//            //myHeroImage.setIcon(myHero.getAttackImage());
//            myHeroImage.setIcon(new ImageIcon(myHero.getImageIcon(Action.ATTACK).getImage().
//                    getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
//            myMonsterImage.setIcon(new ImageIcon(myMonster.getImageIcon(theOpAction).getImage().
//                    getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
//
//            myHero.attack(myMonster, theOpAction);
//            //setHeroHp(myHero.getMyHp(), myHero.getMyMaxHp());
//
//            //doneFight(true);
//        }
//
//        if (!myMonster.isAlive() && myHero.isAlive()) {
//            doneFight(true);
//        } else if (myMonster.isAlive() && myHero.isAlive()){
//            //continue
//        } else {
//            doneFight(false);
//        }
//        paintScreen();
    }

    private void block() {
        myController.block();
    }

    private void specialAttack() {
        myController.special();
//        Action theOpAction = myMonster.getmyAdaptiveCounterAttack().generateAttack();
//        if (myHero.isAlive() && myMonster.isAlive()) {
//            myHeroImage.setIcon(new ImageIcon(myHero.getImageIcon(Action.ATTACK).getImage().
//                    getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
//            myMonsterImage.setIcon(new ImageIcon(myMonster.getImageIcon(theOpAction).getImage().
//                    getScaledInstance(190, 200, Image.SCALE_SMOOTH)));
//            myHero.specialAbility(myMonster, theOpAction);
//            //setHeroHp(myHero.getMyHp(), myHero.getMyMaxHp());
//        }
//
//        if (!myMonster.isAlive() && myHero.isAlive()) {
//            doneFight(true);
//        } else if (myMonster.isAlive() && myHero.isAlive()){
//            //continue
//        } else {
//            doneFight(false);
//        }
//        paintScreen();
    }

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

    // Displays the range of damage that the hero can deal(static, won't be changed)
    private void setMonsterHp() {
        myMonsterHp.setBounds(590, 135, 190, 30);
        myMonsterHp.setForeground(Color.WHITE);
        int currentHp = myController.getMyMonster().getMyHp();
        int maxHp = myController.getMyMonster().getMyMaxHp();
        myMonsterHp.setVisible(true);
        myMonsterHp.setText("HP: " + currentHp + "/" + maxHp);
        add(myMonsterHp);
    }

    private void setHeroDmg() {
        myHeroDmg.setBounds(340, 150, 190, 30);
        myHeroDmg.setForeground(Color.WHITE);
        int minDmg = myController.getHero().getMyMinAttack();
        int maxDmg = myController.getHero().getMyMaxAttack();
        myHeroDmg.setVisible(true);
        myHeroDmg.setText("Damage: " + minDmg + "-" + maxDmg);
        add(myHeroDmg);
    }

    //Displays the range of damage that the monster can deal(static, won't be changed)
    private void setMonsterDmg() {
        myMonsterDmg.setBounds(590, 150, 190, 30);
        myMonsterDmg.setForeground(Color.WHITE);
        int minDmg = myController.getMyMonster().getMyMinAttack();
        int maxDmg = myController.getMyMonster().getMyMaxAttack();
        myMonsterDmg.setVisible(true);
        myMonsterDmg.setText("Damage: " + minDmg + "-" + maxDmg);
        add(myMonsterDmg);
    }

    private void generateMonster() {
        myMonsterImage.setBounds(530, 180, 190, 200);

        myController.setMonster();

//        final Random random = new Random();
//        int randomInt = random.nextInt(3);
//        switch(randomInt) {
//            case 0:
//                myMonster = new Gremlin();
//                //setMonsterHp();
//                //setMonsterDmg();
//                break;
//            case 1:
//                myMonster = new Ogre();
//                //setMonsterHp();
//                //setMonsterDmg();
//                break;
//            case 2:
//                myMonster = new Skeleton();
////                setMonsterHp();
////                setMonsterDmg();
//                break;
//        }

        Image monster = myController.getMyMonsterImage(STANDBY).getImage().
                getScaledInstance(190, 200, Image.SCALE_SMOOTH);

        myMonsterImage.setIcon(new ImageIcon(monster));
        add(myMonsterImage);

        paintScreen();
    }

    private void setActionButtons() {
        myAttackButton.setBounds(200, 380, 100, 40);

        myAttackButton.addActionListener(theEvent -> {
            setAction(ATTACK);
            //other logic
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

//    private void revertHeroImage() {
//        Image hero =  myController.getHero().getImageIcon(Action.STANDBY).getImage().
//                getScaledInstance(190, 200, Image.SCALE_SMOOTH);
//        myHeroImage.setIcon(new ImageIcon(hero));
//    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (theEvt.getPropertyName().equals("use")) {
            setHeroHp();
            repaint();
        }
    }
}
