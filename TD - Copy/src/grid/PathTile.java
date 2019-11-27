package grid;

public class PathTile extends Tile{

    private PathTile nextTile;


    public PathTile(int x, int y) {
        super(x, y);

        super.setType(1);

        nextTile = null;
    }

    public void setNextTile(PathTile nextTile) {
        this.nextTile = nextTile;
    }


    public PathTile getNextTile() {
        return nextTile;
    }
}
