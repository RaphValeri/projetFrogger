package graphicalElements;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import util.Direction;
import gameCommons.Game;

public class App extends Application {

    //Game instance
    public Game game = new Game();
    // canvas dimensions
    public static final int W = 500;
    public static final int H = 500;

    // array to browse through
    public static int[][] T = new int[10][10];

    // arraylist to store timelines
    public static ArrayList<Timeline> L = new ArrayList<>();

    // arraylist to store x,y
    public static ArrayList<DoubleProperty[]> P = new ArrayList<>();

    // arraylist to store the direction of the frog
    ArrayList<Direction> input = new ArrayList<Direction>();

    @Override public void start(Stage stage) {
        stage.setTitle("FROGGER");
        stage.setResizable(false);

        for (int i = 0; i < T.length; i++) {
            DoubleProperty x  = new SimpleDoubleProperty();
            DoubleProperty y  = new SimpleDoubleProperty();
            P.add(new DoubleProperty[]{x, y});

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

        Image frog_img = new Image("file:src/main/java/graphicalElements/frog_img.png",W/6, H/10, false, false);




        final Canvas canvas = new Canvas(W, H+H/5);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();

                gc.setFill(Color.CORNSILK);
                gc.fillRect(0, H/10, W, H);
                gc.setFill(Color.FORESTGREEN);

                gc.drawImage(frog_img, W/2-W/12, H+H/10);
                for (int i=0; i < P.size(); i++){
                    gc.fillRect(
                            P.get(i)[0].doubleValue(),
                            P.get(i)[1].doubleValue(),
                            W / 6,
                            H / 10
                    );
                }
            }
        };

        stage.setScene(new Scene(new Group(canvas)));
        stage.show();
        timer.start();

        for (int i = 0; i < T.length; i++) {
            L.get(i).play();
        }

    }

    public static void main(String[] args) { launch(args); }
}
