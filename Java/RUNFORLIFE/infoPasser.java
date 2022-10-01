/*
 * 
 */

public class infoPasser{
    int[][]map;
    int currentFloorNum;
    public infoPasser()
    {
        
    }
    public void setMap(int[][]a){
        map=a;
    }
    public int[][]getMap(){
        return map;
    }
    public void setFloorNum(int a){
        currentFloorNum = a;
    }
    public int getFloorNum(){
        return currentFloorNum;
    }
}
