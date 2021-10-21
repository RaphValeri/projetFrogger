package graphicalElements;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import util.Direction;
import gameCommons.Game;
import frog.Frog;

public class App extends Application {

    // canvas dimensions
    public static final int W = 500; //largeur de la route
    public static final int H = 500; //hauteur de la route

    // Dimension d'une case sur une voie
    int d_x = W/6;
    int d_y = H/10;

    //Game instance
    public Game game = new Game(d_x, d_y, H, W);
    public Frog frog = game.frog;

    //Images et elements graphiques
    Image frog_img = new Image("file:src/main/java/graphicalElements/frog_img.png",W/6, H/10, false, false);
    Image car_img = new Image("file:src/main/java/graphicalElements/car_img.png", d_x, d_y, false, false);
    Label label_G0 = new Label("GAME OVER ");
    // array to browse through
    public static int[][] T = new int[10][10];

    // arraylist to store timelines
    public static ArrayList<Timeline> L = new ArrayList<>();

    // arraylist to store x,y
    public static ArrayList<DoubleProperty[]> P = new ArrayList<>();


    @Override
    public void start(Stage stage) {
        stage.setTitle("FROGGER");
        stage.setResizable(false);

        final Canvas canvas = new Canvas(W, H+H/5);

        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );
        root.getChildren().add( canvas );

        //On place la grenouille au point de départ
        frog.setPosition(W/2-W/12, H+H/10);

        for (int i = 0; i < T.length; i++) {

            DoubleProperty x  = new SimpleDoubleProperty();
            DoubleProperty y  = new SimpleDoubleProperty();
            P.add(new DoubleProperty[]{x, y});

            //Création des voitures
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0),
                            new KeyValue(x, - W / 6),
                            new KeyValue(y, (i+1) * H / 10)
                    ),

                    new KeyFrame(Duration.seconds(i + 3),
                            new KeyValue(x, W),
                            new KeyValue(y, (i+1) * H / 10)
                    )
            );

            timeline.setCycleCount(Timeline.INDEFINITE);
            L.add(timeline);
        }

        //Création de l'animation
        AnimationTimer timer = new AnimationTimer() {
            //Definition de la méthode abstraite handle de AnimationTimer()
            @Override
            public void handle(long now) {

                GraphicsContext gc = canvas.getGraphicsContext2D();

                gc.setFill(Color.CORNSILK);
                gc.fillRect(0, H/10, W, H);
                gc.setFill(Color.FORESTGREEN);

                theScene.setOnKeyPressed(this::KeyPressed);

                for (int i=0; i < P.size(); i++){
                    int x = (int) P.get(i)[0].doubleValue();
                    int y = (int) P.get(i)[1].doubleValue();
                    gc.drawImage(frog_img, frog.getPosition()[0], frog.getPosition()[1]);
                    gc.drawImage(car_img, x, y, d_x, d_y);

                    //Vérification d'une éventuelle collision
                    if((frog.getPosition()[0] - d_x <= x ) & ( x <=(frog.getPosition()[0] ) & y==frog.getPosition()[1])) {
                        System.out.println("GAME OVER !!!!");
                        frog.setLife(0);
                        gc.setFill(Color.BLACK);
                        gc.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
                        gc.fillText("GAME OVER !!", W/3, H/2+d_y);
                    }
                    if((frog.getPosition()[1]==0)){
                        game.victoire = true;
                        gc.setFill(Color.BLACK);
                        gc.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
                        gc.fillText("Gagné !!", W/3, H/2+d_y);
                    }
                }
                if(frog.getLife()==0 | game.victoire )this.stop(); //Arrêt de l'aniamtion en cas de collision

            }

            //Méthode appelée lors de l'appui sur une touche du clavier
            private void KeyPressed(KeyEvent e) {
                String code = e.getCode().toString();
                System.out.println("Key" + " "+ code + " is pressed");

                // On fait évoluer la position de la grenouille
                game.deplacementFrog(Direction.valueOf(code));
            }
        };


        stage.show();
        timer.start(); //On démarre l'animation

        for (int i = 0; i < T.length; i++) {
            L.get(i).play();
        }

    }



    public static void main(String[] args) { launch(args); }


}
