package background;

import grid.PathTile;
import Object.Church;
import Object.Tree;

import java.util.Queue;

public class SmallMap extends Map{

    private Tree tree;
    private Church church;
    private static final String treeString ="(0,0) (1,2) (2,0) (3,3) (4,15) (5,0) (6,6) (7,0) (8,9) (9,0) (10,5) (11,6) (12,11) (13,0) (14,1) (15,0) (16,15) (17,12) (18,0) (19,13) (20,0) (21,0) (22,3) (23,0) (24,6) (25,12) (26,13) (27,12) (28,13) (29,13) (30,15) (31,0)";
    private static final String churchString = "(23,7)";
    private static final int width = 32;
    private static final int height = 16;
    private static final String userInput = "(0,10) (6,10) (6,15) (15,15) (15,6) (26,6) (26,2) (31,2)";

    public SmallMap() {
        super();
        super.setMapSize(width, height);
        super.setInputCorner(userInput);

        super.initializeMap();

        Queue<PathTile> path = super.multipleCoordinatesSplit(userInput);
        super.buildPath(path);

        Queue<PathTile> corner = super.multipleCoordinatesSplit(userInput);
        super.cornerArray(corner);

        super.convertToBinaryMap(this);
    }
    public String getUserInput(){
        return userInput;
    }
    public String getTreeString(){
        return treeString;
    }

    public  String getMountainString() {
        return churchString;
    }
    public int[][] placeTree(){
        int[][] treemap = new int[16][32];
        String[] arrTree = treeString.split("\\)\\s*");
        for(String xau:arrTree){
            String x=xau.substring(1,xau.indexOf(",")).trim();
            String y=xau.substring(xau.indexOf(",")+1,xau.length()).trim();
            int i = Integer.valueOf(y);
            int j = Integer.valueOf(x);
            treemap[i][j]=4;
        }
        String[] arrMoutain = churchString.split("\\)\\s*");
        for(String xau:arrMoutain){
            String x=xau.substring(1,xau.indexOf(",")).trim();
            String y=xau.substring(xau.indexOf(",")+1,xau.length()).trim();
            int i = Integer.valueOf(y);
            int j = Integer.valueOf(x);
            treemap[i][j]=5;
        }
        return treemap;
    }
}
