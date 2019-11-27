package game;

import Object.Church;
import Object.Tree;
import background.MapEditor;
import background.SmallMap;
import entity.Enemies.Enemy;
import entity.Enemies.NormalEnemy;
import entity.Enemies.RapidEnemy;
import entity.Enemies.TankEnemy;
import entity.Enemies.Plane;
import entity.tower.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameField {

    public GameField() {
        SmallMap smallMap = new SmallMap();
        MapEditor mapEditor = new MapEditor(32, 16, smallMap.getUserInput());

        try {
            mapEditor.writeFile("Small Map");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        tree = new Tree();
        church = new Church();

        maparray = mapEditor.getMapArray();
        objecArray = smallMap.placeTree();

        for(int i=0;i<10;i++) {
            Enemy e = new NormalEnemy(maparray, 0, 10 * 32);
            enemyQueue.add(e);
        }
        for(int i=0;i<10;i++) {
            Enemy e = new RapidEnemy(maparray, 0, 10 * 32);
            enemyQueue.add(e);
        }
        for(int i=0;i<10;i++){
            Enemy e = new TankEnemy(maparray, 0, 10 * 32);
            enemyQueue.add(e);
        }
        for(int i=0;i<10;i++){
            Enemy e = new Plane(maparray, 0, 10 * 32);
            enemyQueue.add(e);
        }
    }

    private Tree tree;
    private Church church;
    int[][] maparray;
    int[][] objecArray;

    private ArrayList<Enemy> enemyArrayList = new ArrayList<>();
    private ArrayList<Tower> towerArrayList = new ArrayList<>();

    private Queue<Enemy> enemyQueue = new LinkedList<>();
    private int count_spawn = 0;
    private int count_dead = 0;

    public ArrayList<Enemy> getEnemyArrayList() {
        return enemyArrayList;
    }

    public void add_enemy() {
        // enemyQueue.size() = 20;
        if(enemyQueue.size() > 0) {
            // round 1 : 1 enemy
            if(count_dead == 0 && count_spawn == 0) {
                enemyArrayList.add(enemyQueue.poll());
                count_spawn++;
            }
            // round 2 : 4 enemy
            if(count_dead >= 1 && count_spawn <= 4) {
                enemyArrayList.add(enemyQueue.poll());
                count_spawn++;
            }
            // round 3 : 5 enemy
            if(count_dead >= 5 && count_spawn <= 9) {
                enemyArrayList.add(enemyQueue.poll());
                count_spawn++;
            }
            // round 4 : 1 rapid enemy
            if(count_dead >= 10 && count_spawn <= 10) {
                enemyArrayList.add(enemyQueue.poll());
                count_spawn++;
            }
            //round 5 : 3 rapid enemy
            if(count_dead >= 11 && count_spawn <= 13) {
                enemyArrayList.add(enemyQueue.poll());
                count_spawn++;
            }
            //round 6 : 5 rapid enemy
            if(count_dead >= 14 && count_spawn <= 19) {
                enemyArrayList.add(enemyQueue.poll());
                count_spawn++;
            }

            //round 7 : 1 tank enemy
            if(count_dead >= 20 && count_spawn <= 20) {
                enemyArrayList.add(enemyQueue.poll());
                count_spawn++;
            }
            //round 8 : 3 tank enemy
            if(count_dead >= 21 && count_spawn <= 23) {
                enemyArrayList.add(enemyQueue.poll());
                count_spawn++;
            }
            //round 9 : 5 t enemy
            if(count_dead >= 24 && count_spawn <= 29) {
                enemyArrayList.add(enemyQueue.poll());
                count_spawn++;
            }
            //round 10 :  1 plane
            if(count_dead >= 30 && count_spawn <= 30) {
                enemyArrayList.add(enemyQueue.poll());
                count_spawn++;
            }
            //round 11 : 5 plane
            if(count_dead >= 31 && count_spawn <= 36) {
                enemyArrayList.add(enemyQueue.poll());
                count_spawn++;
            }
        }
    }
    private double money = 50;

    public double getMoney() {
        return money;
    }

    private int live = 10;

    public int getLive() {
        return live;
    }

    public void update() {
        for(int i=0;i<enemyArrayList.size();i++) {
            if(enemyArrayList.get(i).onDestroy() == true) {
                // dem enemy dead
                if(enemyArrayList.get(i).outOfMap() == true) {
                    live--;
                    count_dead++;
                    enemyArrayList.remove(i);
                    i--;
                    continue;
                }
                money += enemyArrayList.get(i).getMoney_get();
                count_dead++;
                enemyArrayList.remove(i);
                i--;
            }
            else enemyArrayList.get(i).update();

            if(live == 0) System.exit(0);
        }
        for (Tower tower : towerArrayList) tower.update();
        //System.out.println(money);
    }

    public void createTower(Tower tower) {
        if(money - tower.getPrice() >= 0) {
            money -= tower.getPrice();
            towerArrayList.add(tower);
        }
    }

    public void deleteTower(MouseEvent mouseEvent) {
        if(towerArrayList.size() > 0) {
            for(int i=0;i<towerArrayList.size();i++) {
                if(towerArrayList.get(i).getxPos() - 32 < mouseEvent.getX() && mouseEvent.getX() < towerArrayList.get(i).getxPos() + 32 && towerArrayList.get(i).getyPos() -32 < mouseEvent.getY() && mouseEvent.getY()  < towerArrayList.get(i).getyPos() + 32) {
                    money += towerArrayList.get(i).getRefund();
                    towerArrayList.remove(i);
                }
            }
        }
    }

    public boolean check_position(Tower tower) {
        for(Tower tower_arrayList : towerArrayList) {
            if(Math.abs(tower_arrayList.getxPos() - tower.getxPos()) < 64 && Math.abs(tower_arrayList.getyPos() - tower.getyPos()) < 64) return false;
        }
        for(int i=0;i<xPos_road.size();i++) {
            if(Math.abs(tower.getxPos() - xPos_road.get(i)) < 48 && Math.abs(tower.getyPos() - yPos_road.get(i)) < 48) return false;
        }
        for(int i=0;i<xPos_arrayLists.size();i++) {
            if(Math.abs(tower.getxPos() - xPos_arrayLists.get(i)) < 64 && Math.abs(tower.getyPos() - yPos_arrayLists.get(i)) < 64) return false;
        }
        return true;
    }


    private ArrayList<Integer> xPos_road = new ArrayList<>();
    private ArrayList<Integer> yPos_road = new ArrayList<>();

    public void draw(GraphicsContext graphicsContext) {
        //System.out.println(live);
        double w = 0, h = 0;
        Image green = new Image("Assets/green.jpg");
        Image sandTile = new Image("Assets/SandTile.png");
        Image soilImage = new Image("Assets/soil.jpg");

        for (int i = 0; i < 16; i++) {
            w = 0;
            for (int j = 0; j < 32; j++) {
                if (maparray[i][j] == 0) {
                    graphicsContext.drawImage(soilImage, w, h, 32, 32);
                    w += 32;
                } else {
                    xPos_road.add(j*32);
                    yPos_road.add(i*32);
                    graphicsContext.drawImage(sandTile, w, h, 32, 32);
                    w += 32;
                }
            }
            h += 32;
        }

        for (Enemy enemy : enemyArrayList) enemy.draw(graphicsContext);
        for (Tower tower : towerArrayList) tower.draw(graphicsContext);

        graphicsContext.setFont(new Font(26));
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillText("money: " + getMoney(), 800, 500);

        graphicsContext.setFont(new Font(26));
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillText("live: " + getLive(), 700, 500);

        if(count_dead == 37) {
            graphicsContext.setFont(new Font(26));
            graphicsContext.setFill(Color.YELLOW);
            graphicsContext.fillText("You Win!", 500, 300);
        }

    }

    private ArrayList<Integer> xPos_arrayLists = new ArrayList<>();
    private ArrayList<Integer> yPos_arrayLists = new ArrayList<>();

    public void drawObject(GraphicsContext graphicsContext) {
        double w = 0, h = 0;

        for (int i = 0; i < 16; i++) {
            w = 0;
            for (int j = 0; j < 32; j++) {
                if (objecArray[i][j] == 4) {
                    xPos_arrayLists.add(j*32);
                    yPos_arrayLists.add(i*16);
                    graphicsContext.drawImage(tree.getImage(), w, h, 64, 64);
                } else if (objecArray[i][j] == 5) {
                    //yPos_arrayLists.add(j*16);
                    graphicsContext.drawImage(church.getImage(), w, h, 192, 192);
                }
                w += 32;
            }
            h += 32;
        }
    }
}
