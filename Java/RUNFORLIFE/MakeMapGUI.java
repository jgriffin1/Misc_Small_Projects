/**
 * Josh Griffin
 * gui map thingy
 * page idk
 * 5-11-17
 */
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.lang.Math;

public class MakeMapGUI extends JFrame implements KeyListener{
    private final int FLOOR = 1;
    private final int WALL = 2;
    private final int PORTAL = 3;
    private final int LATTER = 4;
    private final int STAIRS = 5;
    private final int DARKNESS = 0;
    private final int PLAYER = 9;
    
    
    private static final int MOB1 = 10;
    private static final int MOB2 = 11;
    private static final int MOB3 = 12;
    private static final int MOB4 = 13;
    private static final int MOB5 = 14;
    private static final int MOB6 = 15;
    //private JFrame frame;
    private JPanel contentPane;
    
    public static final int WIDTH =160;
    public static final int HEIGHT = WIDTH;
    public static final int SCALE = 5;
    public static final int SIZE = HEIGHT*SCALE;
    public final int gridLength = 40;
    private final int gridWidth=40;
    int[][] map;
    GUIMAP level=new GUIMAP();
    //private JFrame frame;
    public Point getPlayerCoordinates(int type){
        return level.getPlayerLocation(type);
    }
    public JFrame getframe(){
        return this;
    }
    public int[][] getMap(){
        return map;
    }
    public int getMapH(){
        return level.getMAP_HEIGHT();
    }
    public int getMapW(){
        return level.getMAP_WIDTH();
    }
    public void setMap(int[][] asdf){
        int[][]map = asdf;
        //level.setMap2(asdf);
    }
    //public void add
    public MakeMapGUI(){
        super();
        
        
        
        //KeyListenerTester keylistener = new KeyListenerTester("Test");
        
        //frame = new JFrame("FightGame");
        
        addKeyListener(this);
        //create and set up the freame
        
        setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        pack();
        
        setResizable(false);
        setLocationRelativeTo(null);
        //frame.setVisible(true);
        
        
        //create a content pane with ta boxlayout and empty borders
        //create content pane
        
        //contentPane = new JPanel(new GridLayout(gridLength,gridWidth));
        //setStuff();
        
        
        
        
        
    }
    public void changemap(int y,int x,int block){
        level.changeBlock(y,x,block);
    }
    JPanel temp;
   // private static  int counter;
    public void setStuff(){
        //hideit();
        //System.out.println("SetStuff " + counter);
        //counter += 1;
        temp = null;
        
        map = level.getMap();
        temp = new JPanel(new GridLayout(gridLength,gridWidth));
        try{
            for(int i = 0;i<gridLength;i++){
                for(int q = 0;q<gridWidth;q++){
                    JLabel l;// = new JLabel(new ImageIcon("darkness.gif"), JLabel.CENTER);
                    if((map[i][q])==FLOOR){
                        l = new JLabel(new ImageIcon("floor.png"), JLabel.CENTER);
                    }else if((map[i][q])==WALL){
                        l = new JLabel(new ImageIcon("wall.png"), JLabel.CENTER);
                    }else if((map[i][q])==PORTAL){
                        l = new JLabel(new ImageIcon("portal.png"), JLabel.CENTER);
                    }else if((map[i][q])==LATTER){
                        l = new JLabel(new ImageIcon("latter.png"), JLabel.CENTER);
                    }else if((map[i][q])==STAIRS){
                        l = new JLabel(new ImageIcon("stairs.png"), JLabel.CENTER);
                    }else if((map[i][q])==PLAYER){
                        l = new JLabel(new ImageIcon("skin2.png"), JLabel.CENTER);
                    }else if((map[i][q])==MOB1){
                        l = new JLabel(new ImageIcon("skin1.png"), JLabel.CENTER);
                    }else if((map[i][q])==MOB2){
                        l = new JLabel(new ImageIcon("skin3.png"), JLabel.CENTER);
                    }else if((map[i][q])==MOB3){
                        l = new JLabel(new ImageIcon("skin4.png"), JLabel.CENTER);
                    }else if((map[i][q])==MOB4){
                        l = new JLabel(new ImageIcon("skin5.png"), JLabel.CENTER);
                    }else if((map[i][q])==MOB5){
                        l = new JLabel(new ImageIcon("skin6.png"), JLabel.CENTER);
                    }else if((map[i][q])==MOB6){
                        l = new JLabel(new ImageIcon("skin7.png"), JLabel.CENTER);
                    }else{
                        l = new JLabel(new ImageIcon("darkness.gif"), JLabel.CENTER);
                    }
                    temp.add(l);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //add content pane to freame
        this.add(temp);
        //size and the ndisplay the freame
        pack();
        //frame.setVisible(true);
        temp = null;
        System.gc();
    }
    //button click ebent
    /*
    public void actionPerformed(ActionEvent event){
        int newRoll;
        
        newRoll = (int)(6*Math.random() + 1);
        if(newRoll==1){
            dieFace.setIcon(new ImageIcon("die1.gif"));
        }else if(newRoll == 2){
            dieFace.setIcon(new ImageIcon("die2.gif"));
        }else if(newRoll == 3){
            dieFace.setIcon(new ImageIcon("die3.gif"));
        }else if(newRoll == 4){
            dieFace.setIcon(new ImageIcon("die4.gif"));
        }else if(newRoll == 5){
            dieFace.setIcon(new ImageIcon("die5.gif"));
        }else if(newRoll == 6){
            dieFace.setIcon(new ImageIcon("die6.gif"));
        }
        
        String eventName = event.getActionCommand();
        
        
    }
    */
    public void showit(){
        
        setVisible(true);
    }
    public void hideit(){
        setVisible(false);
    }
    public static void runGUI(){
        //JFrame.setDefaultLookAndFeelDecorated(true);
        
        MakeMapGUI mapFace = new MakeMapGUI();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        /*
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //gameplay.moveRight();
            //System.out.println("Right key adsfadsfaadf");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //System.out.println("Left key asdfadsfadsfa");
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //System.out.println("UP key asfasdfasdf");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //System.out.println("DOWN key asdfasdfads");
        }
        */
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //System.out.println("Right key pressed");
            gameplay.moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //System.out.println("Left key pressed");
            gameplay.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //System.out.println("UP key pressed");
            gameplay.moveUp();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //System.out.println("DOWN key pressed");
            gameplay.moveDown();
        }
        */
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //System.out.println("Right key pressed");
            gameplay.moveRight();
            gameplay.refreshMap();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //System.out.println("Left key pressed");
            gameplay.moveLeft();
            gameplay.refreshMap();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //System.out.println("UP key pressed");
            gameplay.moveUp();
            gameplay.refreshMap();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //System.out.println("DOWN key pressed");
            gameplay.moveDown();
            gameplay.refreshMap();
        }
        
    }
    /*
    public static void go(){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                runGUI();
            }
            
        });
    }
    */
}