package background;

import grid.PathTile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Queue;

public class MapEditor {
    private Map map;
    private int[][] mapArray;

    private int width;
    private int height;
    private String userInput = "";

    private static final String folderName = "D:/TD/src/MapSave";
    public MapEditor(int width, int height, String userInput){
        map = new Map();
        map.setMapSize(width,height);
        map.setInputCorner(userInput);

        map.initializeMap();

        Queue<PathTile> path = map.multipleCoordinatesSplit(userInput);
        map.buildPath(path);

        Queue<PathTile> corner = map.multipleCoordinatesSplit(userInput);
        map.cornerArray(corner);

        mapArray = map.convertToBinaryMap(map);
    }
    public Map getMap(){
        return map;
    }
    //write text file of map's information
    public void writeFile(String name) throws IOException{
        File file = new File(folderName +"/"+name+".txt");
        FileOutputStream fout = new FileOutputStream(file);

        StringBuffer results = new StringBuffer();
        String data ="";
        String nextLine = System.getProperty("line.separator");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        data+=map.getWidthOfMap()+nextLine;
        data+=map.getHeightOfMap()+nextLine;
        data+=map.getInputCorner()+nextLine;

        for(int i=0; i<mapArray.length; i++){
            for(int j=0; j<mapArray[i].length; j++){
                results.append(mapArray[i][j]).append(" ");
            }
            results.append(nextLine);
        }
        data+=results;
        try{
            fout.write(data.getBytes());
            fout.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    // return width of map
    public int[][] getMapArray(){
        return mapArray;
    }
    public int getWidthFromFile(){
        return width;
    }
    public int getHeightFromFile(){
        return  height;
    }
    public String getUserInput(){
        return  userInput;
    }
    public int[][] getUserInputFromFile(){
        return map.getCornersList();
    }
    public String toString(){
        return map.toString();
    }
}
