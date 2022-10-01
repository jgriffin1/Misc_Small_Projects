/**
 * JOsh 
 * maps with guis
 * 
 */
import java.awt.*;
public class GUIMAP{
    //a lot of things... i'm going to try not to hardcode anything
    //private final int PLAYER_X;
    //private final int PLAYER_Y;
    
    private static final int FLOOR = 1;
    private static final int WALL = 2;
    private static final int PORTAL = 3;
    private static final int LATTER = 4;
    private static final int STAIRS = 5;
    private static final int PLAYER = 9;
    
    private final int MAP_WIDTH = 40;
    private final int MAP_HEIGHT = 40;
    private final int MIN_ROOM_NUM = 9;
    private final int MAX_ROOM_NUM = 12;
    private int[][] TEXTmap = new int[MAP_HEIGHT][MAP_WIDTH];
    public void changeBlock(int y, int x, int block){
        TEXTmap[y][x]=block;
        //MakeMapGUI.setMap(TEXTmap);
    }
    public int[][] getMap(){
        return TEXTmap;
    }
    public int getMAP_WIDTH(){
        return MAP_WIDTH;
    }
    public int getMAP_HEIGHT(){
        return MAP_HEIGHT;
    }
    //public void setMap2(int[][]asdf){
        
   // }
    public Point getPlayerLocation(int type){
        for (int y=0; y < TEXTmap.length; y++){
            for (int x=0; x<TEXTmap[0].length;x++){
               if(TEXTmap[y][x]==type){
                   return (new Point(x,y));
               }
            }
        }
        return(new Point(-1,-1));
    }
    public GUIMAP(){
        //final int MAP_WIDTH = 50;
        //final int MAP_HEIGH = 50;
        int NUM_OF_ROOMS = (int)((MAX_ROOM_NUM - MIN_ROOM_NUM + 1)*Math.random() + MIN_ROOM_NUM); 
        
        //decair the simploe map with numbers instead of blocks
        //the gui map will get stuff based on what is in this
        
       
        
        TEXTmap = reset(TEXTmap);
        for (int i = 0; i<NUM_OF_ROOMS; i ++){
           TEXTmap = createrooms(TEXTmap);
        }
        
        
        
        
        //zero is going to be background nothingness
        //one is going to be a room/wall
        TEXTmap = addWalls(TEXTmap);
        //add stairs down
        //TEXTmap=roomdown(TEXTmap);
        //add stairs up
        /**
         * make add sairs  and latter
         */
        int stairX=0;
        int stairY=0;
        
        boolean works = false;
        while(!works){
           stairX = (int)((MAP_WIDTH - 2 + 1)*Math.random() + 1); 
           stairY = (int)((MAP_HEIGHT - 2 + 1)*Math.random() + 1);
           
           if (TEXTmap[stairY][stairX]==FLOOR){
               TEXTmap[stairY][stairX]=STAIRS;
               works = true;
            }
        }
        works = false;
        int latterX=0;
        int latterY=0;
        while(!works){
           latterX = (int)((MAP_WIDTH - 2 + 1)*Math.random() + 1); 
           latterY = (int)((MAP_HEIGHT - 2 + 1)*Math.random() + 1);
           
           if (TEXTmap[latterY][latterX]==FLOOR){
               TEXTmap[latterY][latterX]=LATTER;
               works = true;
            }
        }
        
        /**
         * end 
         */
        //TEXTmap=roomup(TEXTmap);
        //TEXTmap = mapWithNoX(TEXTmap);
        TEXTmap = addWalls(TEXTmap);
        //display();
       
    }
    final private int MAX_ROOM_SIZE = 10;
    final private int MIN_ROOM_SIZE = 7;
    public int[][] createrooms(int[][] map){
        //this keeps crashing... so i'm just going to loop it until it works
       
        boolean worked = false;
        while(!worked){
            try{
                // lets create one room to begin
        
                //this is the size
               
                int roomwidth = (int)((MAX_ROOM_SIZE-MIN_ROOM_SIZE+1)*Math.random()+MIN_ROOM_SIZE);
                int roomheight =(int)((MAX_ROOM_SIZE-MIN_ROOM_SIZE+1)*Math.random()+MIN_ROOM_SIZE);
                
                //lets turn the room into its own array
                //random =(int) ((high - low +1 ) * Math.random() + low);
                int[][] room = new int[roomheight][roomwidth];
                
                for (int i=0; i < room.length; i++){
                    for (int j=0; j< room[0].length; j++){
                        room[i][j] = 1;
                    }
                }
                
                room[3][3]=3;
                //now lets get a position
                //have to be at least width away from left and height away from the bottom..
                //lets make variables for max coordinates:
                int maxY = MAP_HEIGHT - (roomheight+2);
                int maxX = MAP_WIDTH- (roomwidth+2);
                //System.out.println("maxY: "+maxY+"\n"+"maxX:"+maxX);
                //now to find the position of top left corner
                int roomY = (int)((maxY-2+1)*Math.random() + 2);
                int roomX = (int)((maxX-2+1)*Math.random() + 2);
                //System.out.println("roomY: "+roomY+"\n"+"roomX:"+roomX);
                ///////////////now for putting my array into the other//////////////////////
                for(int w = 0; w < (room.length); w++){
                    for(int h = 0; h < (room[0].length); h++){
                        
                       // System.out.println("["+w+"] ["+h+"]");
                        map[w+roomY][h+roomX] =room[w][h];
                        
                    }
                }
                worked = true;
                
            }catch(Exception e){
                e.printStackTrace();
                worked = false;
            }
        }
        return map;
    }
    public int[][] addWalls(int[][] map){
        
        //left walls
        for(int w = 0; w < (map.length); w++){
            for(int h = 0; h < (map[0].length)-1; h++){
                
                if (map[w][h]==0 && map[w][h]!=map[w][h+1] && !(map[w][h+1]==0 ) ){
                    map[w][h] = 2;
                }
                
            }
        }
        
        //right walls
        for(int w = 0; w < (map.length); w++){
            for(int h = 1; h < (map[0].length); h++){
                
                if (map[w][h-1]==1 && map[w][h]==0){
                    map[w][h] = 2;
                }
                
            }
        }
        
        //top walls
        for(int w = 0; w < (map.length)-1; w++){
            for(int h = 0; h < (map[0].length); h++){
                
                if (map[w+1][h]==1 && (map[w][h]==0 )) {
                    map[w][h] = 2;
                }
                
            }
        }
       
        //bottom walls
        for(int w = 1; w < (map.length); w++){
            for(int h = 0; h < (map[0].length); h++){
                
                if (map[w-1][h]==1 && map[w][h]==0){
                    map[w][h] = 2;
                }
                
            }
        }
        return map;
    }
    public int[][] reset(int[][] map){
         for (int x=0; x < map.length; x++){
            for (int y=0; y<map[0].length;y++){
                map[x][y] = 0;
            }
        }
        return map;
    }
    public int crash(){
        String ugh= null;
        return ugh.length();
    }
     public void display(){
        for (int x=0; x < TEXTmap.length; x++){
            for (int y=0; y<TEXTmap[0].length;y++){
                System.out.print(TEXTmap[x][y]);
            }
            System.out.println();
        }
    }
    public int[][] roomdown(int[][] map){
        //this keeps crashing... so i'm just going to loop it until it works
       
        boolean worked = false;
        while(!worked){
            try{
                // lets create one room to begin
        
                //this is the size
               
                int roomwidth = (int)((MAX_ROOM_SIZE-MIN_ROOM_SIZE+1)*Math.random()+MIN_ROOM_SIZE);
                int roomheight =(int)((MAX_ROOM_SIZE-MIN_ROOM_SIZE+1)*Math.random()+MIN_ROOM_SIZE);
                
                //lets turn the room into its own array
                //random =(int) ((high - low +1 ) * Math.random() + low);
                int[][] room = new int[roomheight][roomwidth];
                
                for (int i=0; i < room.length; i++){
                    for (int j=0; j< room[0].length; j++){
                        room[i][j] = 1;
                    }
                }
                room[1][1]=3;
                room[3][3]=5;
                //now lets get a position
                //have to be at least width away from left and height away from the bottom..
                //lets make variables for max coordinates:
                int maxY = MAP_HEIGHT - (roomheight+2);
                int maxX = MAP_WIDTH- (roomwidth+2);
                //System.out.println("maxY: "+maxY+"\n"+"maxX:"+maxX);
                //now to find the position of top left corner
                int roomY = (int)((maxY-2+1)*Math.random() + 2);
                int roomX = (int)((maxX-2+1)*Math.random() + 2);
                //System.out.println("roomY: "+roomY+"\n"+"roomX:"+roomX);
                ///////////////now for putting my array into the other//////////////////////
                for(int w = 0; w < (room.length); w++){
                    for(int h = 0; h < (room[0].length); h++){
                        
                       // System.out.println("["+w+"] ["+h+"]");
                        map[w+roomY][h+roomX] =room[w][h];
                        
                    }
                }
                worked = true;
                
            }catch(Exception e){
                e.printStackTrace();
                worked = false;
            }
        }
        return map;
    }
    public int[][] roomup(int[][] map){
        //this keeps crashing... so i'm just going to loop it until it works
       
        boolean worked = false;
        while(!worked){
            try{
                // lets create one room to begin
        
                //this is the size
               
                int roomwidth = (int)((MAX_ROOM_SIZE-MIN_ROOM_SIZE+1)*Math.random()+MIN_ROOM_SIZE);
                int roomheight =(int)((MAX_ROOM_SIZE-MIN_ROOM_SIZE+1)*Math.random()+MIN_ROOM_SIZE);
                
                //lets turn the room into its own array
                //random =(int) ((high - low +1 ) * Math.random() + low);
                int[][] room = new int[roomheight][roomwidth];
                
                for (int i=0; i < room.length; i++){
                    for (int j=0; j< room[0].length; j++){
                        room[i][j] = 1;
                    }
                }
                room[2][1]=3;
                room[3][3]=4;
                
                //now lets get a position
                //have to be at least width away from left and height away from the bottom..
                //lets make variables for max coordinates:
                int maxY = MAP_HEIGHT - (roomheight+2);
                int maxX = MAP_WIDTH- (roomwidth+2);
                //System.out.println("maxY: "+maxY+"\n"+"maxX:"+maxX);
                //now to find the position of top left corner
                int roomY = (int)((maxY-2+1)*Math.random() + 2);
                int roomX = (int)((maxX-2+1)*Math.random() + 2);
                //System.out.println("roomY: "+roomY+"\n"+"roomX:"+roomX);
                ///////////////now for putting my array into the other//////////////////////
                for(int w = 0; w < (room.length); w++){
                    for(int h = 0; h < (room[0].length); h++){
                        
                       // System.out.println("["+w+"] ["+h+"]");
                        map[w+roomY][h+roomX] =room[w][h];
                        
                    }
                }
                worked = true;
                
            }catch(Exception e){
                e.printStackTrace();
                worked = false;
            }
        }
        return map;
    }
}
