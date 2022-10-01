
/**
 * Write a description of class entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Point;
public class mob{
    private Point location;
    private int skin;
    infoPasser info;
    private int coveredblock;
    
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
    
    private static int[][]map;
    public static void setMap(int[][]a){
        map = a;
    }
    public mob(Point p, int s){
        skin = s;
        location = p;
        info = gameplay.recieveinfo();
        coveredblock = FLOOR;
    }
    public int getCB(){
        return coveredblock;
    }
    public void setCB(int x){
        coveredblock = x;
    }
    public Point getLocation(){
        return location;
    }
    public int getSkin(){
        return skin;
    }
    public void changeLocation(Point p){
        location = p;
    }
    public void changeLocation(int x, int y){
        location.setLocation(x,y);
    }
    public void goUp(){
        if(map[(int)location.getY()-1][(int)location.getX()]==FLOOR
              ||map[(int)location.getY()-1][(int)location.getX()]==PLAYER){
            location.setLocation(location.getX(),location.getY()-1);
            coveredblock= map[(int)location.getY()][(int)location.getX()];
        }
        
    }
    public void goDown(){
        if(map[(int)location.getY()+1][(int)location.getX()]==FLOOR
              ||map[(int)location.getY()+1][(int)location.getX()]==PLAYER){
            location.setLocation(location.getX(),location.getY()+1);
            coveredblock= map[(int)location.getY()][(int)location.getX()];
        }
    }
    public void goLeft(){
        if(map[(int)location.getY()][(int)location.getX()-1]==FLOOR
              ||map[(int)location.getY()][(int)location.getX()-1]==PLAYER){
            location.setLocation(location.getX()-1,location.getY());
            coveredblock= map[(int)location.getY()][(int)location.getX()];
        }
        if(map[(int)location.getY()][(int)location.getX()]==PLAYER){
            gameplay.endgame();
        }
    }
    public void goRight(){
        
        if(map[(int)location.getY()][(int)location.getX()+1]==FLOOR
              ||map[(int)location.getY()][(int)location.getX()+1]==PLAYER){
            
            location.setLocation(location.getX()+1,location.getY());
            coveredblock= map[(int)location.getY()][(int)location.getX()];
        }
        
    }
    public int getX(){
        return (int)location.getX();
    }
    public int getY(){
        return (int)location.getY();
    }
    
}
