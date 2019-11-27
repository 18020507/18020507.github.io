package Object;

import javafx.scene.image.Image;

public class Object {
    private String place;
    private int type;
    private String dir = "";
    public String getPlace(){
        return place;
    }

    protected Image image;

    public Image getImage(){return image;}

    public String getDir() {
        return dir;
    }
    public void setPlace(String place){
        this.place=place;
    }
    public void setDir(String dir){
        this.dir = dir;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object(){}
}