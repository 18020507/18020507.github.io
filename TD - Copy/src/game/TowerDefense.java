package game;

import Sound.backgoundsound;
import game.GameController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TowerDefense extends Application {
    public static void main(String[] args){
        Application.launch(args);
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        double width = 1024;
//        double height = 512;
//
//        //them
//        AnchorPane root = new AnchorPane();
//
//        Image image = new Image("Assets/BrickTile.png");
//        Image sandTile = new Image("Assets/SandTile.png");
//        Canvas canvas = new Canvas(width,height);
//
//        //them
//        GridPane buttonBox = new GridPane();
//        canvas.setOnMouseClicked(GameController::mouseClicked);
//        canvas.setOnMouseMoved(GameController::mouseMouse);
//
//
//        AnchorPane.setLeftAnchor(buttonBox, 1024.);
//        buttonBox.add(GameController.button1, 0, 0);
//        buttonBox.add(GameController.button2, 1, 0);
//        buttonBox.add(GameController.button3, 0, 1);
//
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        //them
//        GameController gameController = GameController.getInstance(new GameField(), gc);
//
//        gc.drawImage(image,0,0,32,32);
//
//        //them
//        root.getChildren().addAll(canvas, buttonBox);
//
//        primaryStage.setScene(new Scene(root,1224, 512));
//        //draw(gc);
//
//        primaryStage.setTitle("Tower Defense");
//
//        //them
//        gameController.start();
//        primaryStage.show();
//    }
//}
Scene menuScene, playScene,instructionScene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        double width = 1024;
        double height = 512;
        //Button
        Button play = new Button("Play");
        AnchorPane.setLeftAnchor(play,550.0);
        AnchorPane.setRightAnchor(play,550.0);
        AnchorPane.setTopAnchor(play,20.0);
        play.setPrefSize(200.,50.);

        Button newGame = new Button("New Game");
        newGame.setPrefSize(200,50);

        Button back = new Button("Back");
        AnchorPane.setLeftAnchor(back,1070.0);
        AnchorPane.setRightAnchor(back,50.0);
        AnchorPane.setBottomAnchor(back,90.0);
        back.setOnAction(e->primaryStage.setScene(menuScene));

        Button stop = new Button("Stop");
        AnchorPane.setLeftAnchor(stop,1070.0);
        AnchorPane.setRightAnchor(stop,50.0);
        AnchorPane.setBottomAnchor(stop,120.0);

        Button continous = new Button("Continous");
        AnchorPane.setLeftAnchor(continous,1070.0);
        AnchorPane.setRightAnchor(continous,50.);
        AnchorPane.setBottomAnchor(continous,150.);

        Button instruction = new Button("Instructions");
        AnchorPane.setLeftAnchor(instruction,550.);
        AnchorPane.setRightAnchor(instruction,550.);
        AnchorPane.setBottomAnchor(instruction,150.);
        instruction.setPrefSize(200.,50.);
        instruction.setOnAction(e->primaryStage.setScene(instructionScene));


        Button exit = new Button("Exit");
        AnchorPane.setLeftAnchor(exit,550.0);
        AnchorPane.setRightAnchor(exit,550.0);
        AnchorPane.setBottomAnchor(exit,100.0);
        exit.setPrefSize(200.,50.);
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        //Scene Instructions
        Text insText = new Text();
        insText.setText("Tower Defense là một tựa game phòng thủ chiến thuật thời gian thực \nkhá phổ biến trên thế giới.\nMục tiêu của trò chơi là ngăn chặn quân địch tiến tới đích \nbằng cách lắp đặt các tháp phòng thủ có tác dụng gây sát thương, đóng băng...\nClick chuột trái để đặt trụ vào vị trí bất kỳ\nClick chuột phải để bán trụ");
        insText.setFont(new Font(26));
        insText.setFill(Color.BLACK);
        insText.relocate(80.,100.);
        Button newBack = new Button("Back");
        newBack.setOnAction(e->primaryStage.setScene(menuScene));
        newBack.setPrefSize(200.,50.);
        Pane instructionLayOut = new Pane();
        Image instructionImage = new Image("Assets/instructions.png");
        ImageView insImageView = new ImageView(instructionImage);
        insImageView.setFitHeight(512.);
        insImageView.setFitWidth(1224.);
        newBack.relocate(550.,400.);
        instructionLayOut.getChildren().addAll(insImageView,insText,newBack);
        instructionScene = new Scene(instructionLayOut,1224,512);


        //Scene Play
//        Button playAgain = new Button("Play again");
//        AnchorPane.setLeftAnchor(playAgain,1070.0);
//        AnchorPane.setRightAnchor(playAgain,50.);
//        AnchorPane.setBottomAnchor(playAgain,60.);

        Button newExit = new Button("Exit");
        AnchorPane.setLeftAnchor(newExit,1070.0);
        AnchorPane.setRightAnchor(newExit,50.);
        AnchorPane.setBottomAnchor(newExit,60.);
        newExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        AnchorPane playLayout = new AnchorPane();
        Canvas canvas = new Canvas(width,height);
        GridPane buttonBox = new GridPane();
        canvas.setOnMouseClicked(GameController::mouseClicked);
        canvas.setOnMouseMoved(GameController::mouseMouse);

        AnchorPane.setLeftAnchor(buttonBox, 1024.);
        buttonBox.add(GameController.button1, 0, 0);
        buttonBox.add(GameController.button2, 1, 0);
        buttonBox.add(GameController.button3, 0, 1);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        GameController gameController = GameController.getInstance(new GameField(), gc);
        playLayout.getChildren().addAll(canvas, buttonBox);

        playScene=new Scene(playLayout,1224,512);
        playLayout.getChildren().addAll(back,stop,continous,newExit);

        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //backgoundsound.backGroundSound.play();
                primaryStage.setScene(playScene);
                gameController.start();
            }
        });
        backgoundsound.backGroundSound.play();
        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.stop();
            }
        });
        continous.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.start();
            }
        });
//        playAgain.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                gameController.start();
//            }
//        });

        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(playScene);
                new GameController(new GameField(), gc);
                gameController.start();
            }
        });
        //Scene menu
        Pane pane = new Pane();

        Image menuImage = new Image("Assets/menu.jpg");
        ImageView menuImageView = new ImageView(menuImage);
        menuImageView.setFitWidth(1224.);
        menuImageView.setFitHeight(512);
        play.relocate(550.,300.);
        exit.relocate(550,400);
        instruction.relocate(550., 350.);
        newGame.relocate(550.,250.);
        pane.getChildren().addAll(menuImageView,play,exit,instruction,newGame);
        menuScene = new Scene(pane,1224,512);

        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Tower Defense");
        primaryStage.show();
    }
}

