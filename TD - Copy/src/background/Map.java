package background;

import grid.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Map{
    private String nameOfMap;
    private int widthOfMap, heightOfMap;
    private String inputCorner;
    private PathTile entry;
    private Tile[][] mapTile;
    private int[][] mapArray;
    private int[][] cornerArray;
    private BufferedImage tileSheet;

    private static final int CELL_SIZE = 32;
    private boolean validityOfMap, validityOfPath;
    //init map
    public Map(){
        widthOfMap = 0;
        heightOfMap = 0;
        inputCorner = "";

        validityOfMap = true;
    }
    //set size of map
    public void setMapSize(int widthOfMap, int heightOfMap){
        this.widthOfMap = widthOfMap;
        this.heightOfMap = heightOfMap;
    }
    //set input Corner
    public void setInputCorner(String inputCorner){
        this.inputCorner=inputCorner;
    }
    //init map
    public void initializeMap(){
        if(widthOfMap>0 && heightOfMap>0){
            mapTile = new Tile[widthOfMap][heightOfMap];
            for(int i=0; i<widthOfMap; i++){
                for (int j=0; j<heightOfMap; j++){
                    mapTile[i][j]=new MapTile(i,j);
                }
            }
        }
        validityOfMap=true;
    }
    public int getWidthOfMap(){
        return widthOfMap;
    }
    public int getWidthInPixel(){
        return widthOfMap*CELL_SIZE;
    }
    public int getHeightOfMap(){
        return heightOfMap;
    }
    public int getHeightInPixel(){
        return heightOfMap*CELL_SIZE;
    }
    public String getInputCorner(){
        return inputCorner;
    }
    public Tile getTile(int x, int y){
        try{
            return this.mapTile[x][y];
        }catch (Exception e){

        }
        return null;
    }
    public int getPixelSize(){
        return CELL_SIZE;
    }
    public void placeEntry(int x, int y){
        mapTile[x][y]=new PathTile(x,y);
        mapTile[x][y].setType(2);
        entry = (PathTile) mapTile[x][y];
    }
    public PathTile getEntry(){
        return (PathTile)this.getTile(entry.getX(),entry.getY());
    }
    public void  placeExit(int x, int y){
        mapTile[x][y]=new PathTile(x,y);
        mapTile[x][y].setType(3);
    }
    public void placePathPoint(int x, int y){
        if(mapTile[x][y] instanceof MapTile){
            mapTile[x][y]=new PathTile(x,y);
        }
    }
    public String arrangePathPoint(ArrayList<Integer>pathPoints){
        String temp="";
        for(int i=0; i<pathPoints.size(); i+=2){
            temp+="("+pathPoints.get(i)+","+pathPoints.get(i+1)+")";
        }
        inputCorner = temp;
        return inputCorner;
    }
    public Queue<PathTile> multipleCoordinatesSplit(String s){
        inputCorner = s;
        if(inputCorner.isEmpty()){
            return null;
        }
        Queue<PathTile> path = new LinkedList<PathTile>();
        String[] arr = s.split("\\)\\s*");
        for(String xau:arr){
            String x=xau.substring(1,xau.indexOf(",")).trim();
            String y=xau.substring(xau.indexOf(",")+1,xau.length()).trim();

            PathTile p = new PathTile(Integer.valueOf(x),Integer.valueOf(y));
            path.add(p);
        }
        return path;
    }
    public int[][] cornerArray(Queue<PathTile> path){
        ArrayList<Integer> array = new ArrayList<Integer>();

        while (!path.isEmpty()){
            PathTile current = path.poll();//return element in head of queue;
            array.add(current.getX());
            array.add(current.getY());
        }

        int arraySize = array.size()/2;
        int count = 0;

        cornerArray = new int[arraySize][2];
        for(int i=0; i<arraySize; i++){
            if(count ==array.size()){
                break;
            }
            cornerArray[i][0]=array.get(count) *getPixelSize()+getPixelSize()/2;
            cornerArray[i][1]=array.get(count+1)*getPixelSize()+getPixelSize()/2;

            if (count == 0){
                if(array.get(count)==0){
                    cornerArray[0][0] = array.get(count) * getPixelSize();
                }
                if(array.get(count)==getWidthOfMap()-1){
                    cornerArray[0][0]=(array.get(count)+1 )*getPixelSize();
                }
                if(array.get(count+1)==0){
                    cornerArray[0][1] = array.get(count+1)*getPixelSize()+getPixelSize()/2;
                }
            }
            count+=2;
        }
        return cornerArray;
    }
    public int[][] getCornersList(){
        return cornerArray;
    }
    public void buildPath(Queue<PathTile> newPath){
        if(newPath == null){
            return;
        }
        //store the first input
        entry = newPath.peek();
        PathTile current, next;
        if(newPath.isEmpty()){
            return;
        }
        if(newPath.size()>=2){
        }
        current = newPath.remove();
        next = current;
        if(current.getX()<getWidthOfMap() && current.getY()<getHeightOfMap()){
            mapTile[next.getX()][next.getY()] = new PathTile(next.getX(),next.getY());
            while (!newPath.isEmpty()){
                next = newPath.remove();
                linkTwoPoints(current,next);
                current = next;
            }

            //Indicates the entry and exit of the path
            placeEntry(entry.getX(),entry.getY());
            placeExit(current.getX(),current.getY());
        }
    }
    public void linkTwoPoints(PathTile startPoint, PathTile endPoint){
        int dx, dy;

        int x1 = startPoint.getX();
        int y1 = startPoint.getY();

        int x2 = endPoint.getX();
        int y2 = endPoint.getY();

        if(x1!=x2 && y1!=y2){
            placePathPoint(x1,y1);
            placePathPoint(x2,y2);

            if(x1==0 || x1 == getWidthOfMap()-1 || y1==0 || y1==getHeightOfMap()-1){
                validityOfPath = true;
            }
            if(x2==0||x2==getWidthOfMap()-1||y2==0||y2==getHeightOfMap()-1){
                validityOfPath = true;
            }

        }else{
            PathTile currentPoint = (PathTile) mapTile[x1][y1];
            //handle when point1 != point2
            if (x1==x2){
                dy=y2-y1;
                if(dy==0){
                    return;
                }else if(dy>0) {
                    for(int i=1; i<=dy; i++){
                        mapTile[x1][y1+i]=new PathTile(x1,y1+i);
                        currentPoint.setNextTile((PathTile)mapTile[x1][y1+i]);
                        currentPoint = (PathTile)mapTile[x1][y1+i];
                    }
                }else{
                    for(int i=-1; i>=dy; i--){
                        mapTile[x1][y1+i]=new PathTile(x1,y1+i);
                        currentPoint.setNextTile((PathTile)mapTile[x1][y1+i]);
                        currentPoint = (PathTile)mapTile[x1][y1+i];
                    }
                }
                validityOfPath = true;
            }
            else {
                dx = x2 - x1;

                if (dx == 0){
                    return;
                }
                //If the difference is positive, link them from left to right
                //Otherwise, link them from right to left
                else if (dx > 0){
                    for (int i = 1 ; i <= dx; i++){
                        mapTile[x1 + i][y1] = new PathTile(x1 + i, y1);
                        currentPoint.setNextTile((PathTile)mapTile[x1 + i][y1]);
                        currentPoint = (PathTile)mapTile[x1 + i][y1];
                    }
                }
                else {
                    for (int i = -1 ; i >= dx; i--){
                        mapTile[x1 + i][y1] = new PathTile(x1 + i, y1);
                        currentPoint.setNextTile((PathTile)mapTile[x1 + i][y1]);
                        currentPoint = (PathTile)mapTile[x1 + i][y1];
                    }
                }
                validityOfPath = true;
            }
        }
    }
    //Convert Map to integer array version
    public int[][] convertToBinaryMap(Map map){
        mapArray = new int[map.getHeightOfMap()][map.getWidthOfMap()];

        for(int i=0; i<map.getHeightOfMap(); i++){
            for (int j=0 ; j<map.getWidthOfMap(); j++){
                if(map.getTile(j,i) instanceof PathTile){
                    if(map.getTile(j,i).getType()==2){
                        mapArray[i][j]=2;
                    }
                    else if(map.getTile(j,i).getType()==3){
                        mapArray[i][j]=3;
                    }
                    else {
                        mapArray[i][j]=1;
                    }
                }
                else {
                    mapArray[i][j]=0;
                }
            }
        }
        return mapArray;
    }
    //convert binaryMap to Object Map
    public Map convertFromBinaryMap(int[][] mapArray){
        this.setMapSize(mapArray[0].length,mapArray.length);
        this.initializeMap();

        for(int i=0; i<mapArray.length; i++){
            for(int j=0; j<mapArray[i].length; j++){
                if(mapArray[i][j]==1){
                    this.getTile(j,i).setType(1);
                }
                else if(mapArray[i][j]==2){
                    this.getTile(j,i).setType(2);
                }
                else if(mapArray[i][j]==3){
                    this.getTile(j,i).setType(3);
                }
                else{
                    this.getTile(j,i).setType(0);
                }
            }
        }
        return this;
    }
    public BufferedImage LoadTileSheet(String filenName){
        BufferedImage img = null;

        return img;
    }
    //check isvalid map?
    public boolean ValidityOfMap(){
        boolean validity;
        //if (validityOfMap || validityOfPath) validity = true;
        //        else validity = false; more simple
        validity = validityOfMap || validityOfPath;
        return validity;
    }
    //print map
    public String toString(){
        String s = "\n";
        for (int i=0; i<getHeightOfMap(); i++) {
            for (int j = 0; j < getWidthOfMap(); j++) {
                s += (getTile(j, i) + " ");
            }
            s += "\n";
        }
        return s;
    }
}