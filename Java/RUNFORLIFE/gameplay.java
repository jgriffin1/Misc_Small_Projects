
/**
 * Write a description of class gameplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.lang.Math;
public class gameplay extends MakeMapGUI {
    private static final int FLOOR = 1;
    private static final int WALL = 2;
    private static final int PORTAL = 3;
    private static final int LATTER = 4;
    private static final int STAIRS = 5;
    private static final int PLAYER = 9;
    private static final int MOB1 = 10;
    private static final int MOB2 = 11;
    private static final int MOB3 = 12;
    private static final int MOB4 = 13;
    private static final int MOB5 = 14;
    private static final int MOB6 = 15;
    
    //private final int DARKNESS = ;
    public static int PlayerX;
    private static int PlayerY;
    private static JFrame CurrentMap;
    private static ArrayList<MakeMapGUI>mapguis; 
    private static int CurrentFloorNum=0;
    private static final int NUM_OF_LEVELS = 20;
    private static double SPEED = 2;
    private static int MOB_NUM = 4;
     ///differnet nubmers are different PLAYERs
    private static int COVERED_BLOCK = 4;
    private static int[][]map;
    private static ArrayList<Point>portalLocations = new ArrayList<Point>();
    private static boolean test = true;
    
    private static ArrayList<mob>MOB=new ArrayList<mob>();
    
    public gameplay()throws InterruptedException{
        super();
        mapguis = new ArrayList<MakeMapGUI>();
        ///im gonna try and make a bunch of maps that i can choose from to make sure it works.
        
        
        
        //addKeyListener();
        for(int i = 0;i<NUM_OF_LEVELS;i++){
            MakeMapGUI x = new MakeMapGUI();
            mapguis.add(x);
        }
        
        
        
        //Scanner jeffery = new Scanner(System.in);
        
        
        //int previousMove=0;
        //int random = 0;
        //while(CurrentFloorNum!=-1){
            
            //mapguis.get(CurrentFloorNum).showit();
            /*
            refreshMap();
            previousMove = random;
            random =(int) ((5 - 1 +1 ) * Math.random() + 1);
            
            Thread.sleep(1000/SPEED);
            if(random==5)random=previousMove;
            //if(random==6)random=previousMove;
            if(random==1)moveUp();
            if(random==1)moveUp();
            if(random==2)moveDown();
            if(random==3)moveRight();
            if(random==4)moveLeft();
            
            //nextFloor(); 
            */
        //}
    }
    public static void getPortalLocations(){
        portalLocations.clear();
        for (int y=0; y < map.length; y++){
            for (int x=0; x<map[0].length;x++){
               if(map[y][x]==PORTAL){
                   portalLocations.add(new Point(x,y));
               }
            }
        }
    }
    public static void main(String[]args) throws InterruptedException{
        gameplay go = new gameplay();
        /*
        System.out.print("Entering the dungeon. Floor Zero in Three, ");
        Thread.sleep(1000);
        System.out.print("Two, ");
        Thread.sleep(1000);
        System.out.print("One.");
        Thread.sleep(1000);
        */
        refreshMap();
        setUpFirstMap();
        getPortalLocations();
        
        Thread.sleep(2000);
        //add timer event
        Timer timer = new Timer((int)(2000/SPEED),new MyTimerEventListener());
        timer.start();
        
        
        //Thread.sleep(5000);
        //Runtime rs=Runtime.getRuntime();
        /*
        while(true){
            moveUp();
            Thread.sleep(50);
            moveRight();
            Thread.sleep(50);
            moveDown();
            System.out.println("Runtime Before: "+rs.freeMemory());
            Thread.sleep(50);
            moveLeft();
            Thread.sleep(50);
            rs=Runtime.getRuntime();
            System.out.println("Runtime Before: "+rs.freeMemory());
        }
        */
        //while(true){
            
            //rs.gc();
            //System.out.println("Runtime Aafter: "+rs.freeMemory());
            //Thread.sleep(500);
            //System.out.println("System Before: "+System.freeMemory());
            //System.gc();
            //System.out.println("System Aafter: "+System.freeMemory());
            //Thread.sleep(500);
        //}
        
    }
    
    
    public static void nextFloor(){
        SPEED = SPEED *1.2;
        MOB_NUM += 1;
        
        mapguis.get(CurrentFloorNum).hideit();
        CurrentFloorNum=CurrentFloorNum+1;
        //map = mapguis.get(CurrentFloorNum).getMap();
        refreshMap();
        for (int y=0; y < map.length; y++){
            for (int x=0; x<map[0].length;x++){
               if(map[y][x]==LATTER){
                   map[y][x]=PLAYER;
               }
            }
        }
        mapguis.get(CurrentFloorNum).setMap(map);
        COVERED_BLOCK=LATTER;
        //PlayerX=(int)spot.getX();
        //PlayerY=(int)spot.getY();
        
        //mapguis.get(CurrentFloorNum).changemap((int)spot.getY(),(int)spot.getX(),PLAYER);
        setUpMobs();
        refreshMap();
        getPortalLocations();
    }
    public static void previousFloor(){
        SPEED = SPEED * .2;
        MOB_NUM -= 1;
        mapguis.get(CurrentFloorNum).hideit();
        
        CurrentFloorNum=CurrentFloorNum-1;
        //map = mapguis.get(CurrentFloorNum).getMap();
        refreshMap();
        //Point spot = new Point(-1,-1);
        for (int y=0; y < map.length; y++){
            for (int x=0; x<map[0].length;x++){
               if(map[y][x]==STAIRS){
                   map[y][x]=PLAYER;
               }
            }
        }
        mapguis.get(CurrentFloorNum).setMap(map);
        COVERED_BLOCK=STAIRS;
        setUpMobs();
        //mapguis.get(CurrentFloorNum).changemap((int)spot.getY(),(int)spot.getX(),PLAYER);
        refreshMap();
    }
    public static void setUpFirstMap(){
        //CurrentFloorNum=0;
        //search for the up staircase and remomve it
        Point spot = new Point(-1,-1);
        for (int y=0; y < map.length; y++){
            for (int x=0; x<map[0].length;x++){
               if(map[y][x]==LATTER){
                   spot = new Point(x,y);
               }
            }
        }
        COVERED_BLOCK=FLOOR;
        mapguis.get(CurrentFloorNum).changemap((int)spot.getY(),(int)spot.getX(),PLAYER);
        setUpMobs();
        refreshMap();
        getPortalLocations();
    }
    //private static int squirrel = 0;
    public static void refreshMap(){
        //System.out.println("refreshMap " + squirrel);
        //squirrel+=1;
        mapguis.get(CurrentFloorNum).setStuff();
        CurrentMap = mapguis.get(CurrentFloorNum).getframe();
        map = mapguis.get(CurrentFloorNum).getMap();
        //mapguis.get(CurrentFloorNum).setStuff();
        mapguis.get(CurrentFloorNum).showit();
    }
    public static Point getPlayerLocation(){
        Point p = mapguis.get(CurrentFloorNum).getPlayerCoordinates(PLAYER);
        return p;
    }
    //private String direction;
    public static void moveUp(){
        Point p = getPlayerLocation();
        PlayerX = (int)p.getX();
        PlayerY = (int)p.getY();
        mapguis.get(CurrentFloorNum).changemap(PlayerY,PlayerX,COVERED_BLOCK);
        PlayerY = PlayerY-1;
        COVERED_BLOCK=map[PlayerY][PlayerX];
        mapguis.get(CurrentFloorNum).changemap(PlayerY,PlayerX,PLAYER);
        checkSquare("up");
        //refreshMap();
        //mapguis.get(CurrentFloorNum).setStuff();
        //mapguis.get(CurrentFloorNum).showit();
    }
    public static void moveDown(){
        Point p = getPlayerLocation();
        PlayerX = (int)p.getX();
        PlayerY = (int)p.getY();
        mapguis.get(CurrentFloorNum).changemap(PlayerY,PlayerX,COVERED_BLOCK);
        PlayerY = PlayerY+1;
        COVERED_BLOCK=map[PlayerY][PlayerX];
        mapguis.get(CurrentFloorNum).changemap(PlayerY,PlayerX,PLAYER);
        checkSquare("down");
        //refreshMap();
        //mapguis.get(CurrentFloorNum).setStuff();
        //mapguis.get(CurrentFloorNum).showit();
    }
    public static void moveRight(){
        Point p = getPlayerLocation();
        PlayerX = (int)p.getX();
        PlayerY = (int)p.getY();
        mapguis.get(CurrentFloorNum).changemap(PlayerY,PlayerX,COVERED_BLOCK);
        PlayerX = PlayerX+1;
        COVERED_BLOCK=map[PlayerY][PlayerX];
        mapguis.get(CurrentFloorNum).changemap(PlayerY,PlayerX,PLAYER);
        checkSquare("right");
        //refreshMap();
        //mapguis.get(CurrentFloorNum).setStuff();
        //mapguis.get(CurrentFloorNum).showit();
    }
    public static void moveLeft(){
        Point p = getPlayerLocation();
        PlayerX = (int)p.getX();
        PlayerY = (int)p.getY();
        mapguis.get(CurrentFloorNum).changemap(PlayerY,PlayerX,COVERED_BLOCK);
        PlayerX = PlayerX-1;
        COVERED_BLOCK=map[PlayerY][PlayerX];
        mapguis.get(CurrentFloorNum).changemap(PlayerY,PlayerX,PLAYER);
        checkSquare("left");
        //refreshMap();
        //mapguis.get(CurrentFloorNum).setStuff();
        //mapguis.get(CurrentFloorNum).showit();
    }
    private static Point temp;
    public static void checkSquare(String x){//to be called by move left/right/up/down
        int CB = COVERED_BLOCK;
        //random =(int) ((high - low +1 ) * Math.random() + low);
        switch(CB){
            case(PORTAL):
                int num = portalLocations.size()-1;
                int oldX = PlayerX;
                int oldY = PlayerY;
                
                int rand = (int)((num-0+1)*Math.random()+0);
                temp = portalLocations.get(rand);
                
                mapguis.get(CurrentFloorNum).changemap(PlayerY,PlayerX,COVERED_BLOCK);
                PlayerX = (int)temp.getX();
                PlayerY = (int)temp.getY();
                if(oldX==PlayerX && oldY==PlayerY)checkSquare(x);
                mapguis.get(CurrentFloorNum).changemap(PlayerY,PlayerX,PLAYER);
                refreshMap();
            break;
            case(LATTER):
                previousFloor();
            break;
            case(STAIRS):
                nextFloor();
            break;
            case(WALL):
                if(x.equals("left"))moveRight();
                if(x.equals("right"))moveLeft();
                if(x.equals("up"))moveDown();
                if(x.equals("down"))moveUp();
            break;
            case(MOB1):
            case(MOB2):
            case(MOB3):
            case(MOB4):
            case(MOB5):
            case(MOB6):
                endgame();
                
            break;
        
        }
    }
    //static JFrame frame;
    //static JPanel contentPane;
    //static JLabel label;
    public static void endgame(){
    	/*
    	
    	//JFrame.setDefaultLookAndFeelDecorated(true);
    	mapguis.get(CurrentFloorNum).hideit();
    	mapguis.get(CurrentFloorNum).dispose();
    	frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        //create content pane
        contentPane = new JPanel();
        
        //Create nand add alabel
        label = new JLabel("YOU GOT KILLED ON FLOOR " + CurrentFloorNum);
        contentPane.add(label);
        
        //add content pane to freame
        frame.setContentPane(contentPane);
        
        //size and the ndisplay the freame
        frame.pack();
        frame.setVisible(true);
    	try{
        	Thread.sleep(10000);
    	}catch(Exception e){
    	}
    	*/
        
        //System.out.println("YOU GOT KILLED ON FLOOR: " +CurrentFloorNum+1);
        System.exit(0);
    }
    public static infoPasser recieveinfo(){
        infoPasser x = new infoPasser();
        x.setMap(map);
        x.setFloorNum(CurrentFloorNum);
        
        return x;
    }
    public void setMap(int[][]a){
        map = a;
    }
    
    public static void setUpMobs(){
        MOB.clear();
        MOB_NUM = CurrentFloorNum+4;
        for(int i = 0;i<MOB_NUM;i++){
            //check to make sure the thingy is the floor;
            boolean works = false;
            int mobX=0;
            int mobY=0;
            int skin=0;
            while(!works){
                mobX = (int)((mapguis.get(CurrentFloorNum).getMapW() - 2 + 1)*Math.random() + 1); 
                mobY = (int)((mapguis.get(CurrentFloorNum).getMapH() - 2 + 1)*Math.random() + 1);
               
                if (map[mobY][mobX]==FLOOR){
                   works = true;
                }
            }
            //random =(int) ((high - low +1 ) * Math.random() + low);
            skin = (int)((15-10+1) * Math.random() + 10);
            
            MOB.add(new mob(new Point(mobX,mobY),skin));
        }
        for(mob x : MOB){
            map[x.getY()][x.getX()]=x.getSkin();
        }
        mapguis.get(CurrentFloorNum).setMap(map);
    }
    public ArrayList<mob> getmoblist(){
        return MOB;
    }
    public static void moveMobs(){
        for(mob x : MOB){
            map[x.getY()][x.getX()]=x.getCB();
        }
        for(int i = 0; i<MOB.size();i++){
            MOB.get(i).setMap(map);
            
            //random =(int) ((high - low +1 ) * Math.random() + low);
            int dir = (int) ((5-1+1)*Math.random()+1);
            if(dir==1){
                MOB.get(i).goUp();
            }else if(dir==2){
                MOB.get(i).goRight();
            }else if(dir==3){
                MOB.get(i).goLeft();
            }else if(dir==4){
                MOB.get(i).goDown();
            }else{
                //stay in previous spot!
            }
            
        }
        for(mob x : MOB){
            map[x.getY()][x.getX()]=x.getSkin();
        }
        refreshMap();
    }
    
    /**
     * **************************************************************** 
     * **************************************************************** 
     * **************************************************************** 
     * **************************************************************** 
     * **************************************************************** 
     * **************************************************************** 
     * **************************************************************** 
     * **************************************************************** 
     * **************************************************************** 
     * **************************************************************** 
     * **************************************************************** 
     * END OF MOB MOVEMENT
     */
    
}
