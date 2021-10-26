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
import util.Plateau;
import util.Voie;


/**
 * Classe qui gère l'IHM du jeu Frogger
 */
public class App extends Application implements IFroggerGraphics, VoitureGraphics {

    // canvas dimensions
    public static final int W = 500; //largeur de la route
    public static final int H = 500; //hauteur de la route

    // Dimension d'une case sur une voie
    public static final int d_x = W/6;
    public static final int d_y = H/10;

    //Game instance
    public Game game = new Game(d_x, d_y, H, W);
    public Frog frog = game.frog;
    Plateau plateau = new Plateau(W/d_x, H/d_y + 2);
    Voie[] voies = plateau.voie;


    //Images
    Image frog_img = new Image(imageFrog(),d_x, d_y, false, false);
    Image car_img = new Image(imageVehicule(), d_x, d_y, false, false);
    Image background_img = new Image(imageBackground(), W, H + 2 * d_y, false, false);


    @Override
    /**
     * Méthode abstraite de la classe Application
     *
     * @param stage
     *              Stage de l'application
     */
    public void start(Stage stage) {
        stage.setTitle("FROGGER");
        stage.setResizable(false);

        final Canvas canvas = new Canvas(W, H+2*d_y);

        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );
        root.getChildren().add( canvas );

        //On place la grenouille au point de départ (en bas au milieu du plateau)
        frog.setPosition(W/2-d_x/2, H+d_y);


        //Création de l'animation
        AnimationTimer timer = new AnimationTimer() {
            //Definition de la méthode abstraite handle de AnimationTimer()
            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, H + d_y, W, d_y);
                gc.setFill(Color.CORNSILK);
                gc.fillRect(0, d_y, W, H);

                //Affichage de la grenouille
                gc.drawImage(background_img, 0, 0, W, H + 2 * d_y);
                gc.drawImage(frog_img, frog.getPosition()[0], frog.getPosition()[1]);

                theScene.setOnKeyPressed(this::KeyPressed);

                for (int i=0; i < voies.length; i++) {
                    //Parcours de toutes les voies
                    if (voies[i].is_timeline) {
                        //S'il y a une timeline sur la voie (c-a-d une voiture) on affiche la voiture
                        for (int j = 0; j < voies[i].position.size(); j++) {

                            int x = (int) voies[i].position.get(j)[0].doubleValue();
                            int y = (int) voies[i].position.get(j)[1].doubleValue();

                            gc.drawImage(car_img, x, y, d_x, d_y);


                            //Vérification d'une éventuelle collision
                            if ((frog.getPosition()[0] - d_x <= x) & (x <= (frog.getPosition()[0]) & y == frog.getPosition()[1])) {
                                System.out.println("GAME OVER !!!!");
                                frog.setLife(0);
                                gc.setFill(Color.BLACK);
                                gc.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
                                gc.fillText("GAME OVER !!", W / 3, H / 2 + d_y);
                            }
                            if (frog.getPosition()[1] == 0) {
                                game.victoire = true;
                                gc.setFill(Color.BLACK);
                                gc.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
                                gc.fillText("Gagné !!", W / 3, H / 2 + d_y);
                            }
                        }
                    }
                }
                if(frog.getLife()==0 | game.victoire )this.stop(); //Arrêt de l'animation en cas de collision

                actu_timelines();
                actualisation();

            }


            /**
             * Méthode appelée lors de l'appui sur une touche du clavier
             * Elle permet de faire évoluer la position de la grenouille selon la touche utilisée par le joueur
             * @param e
             *          Evénement d'action sur une touche du clavier
             */
            private void KeyPressed(KeyEvent e) {
                String code = e.getCode().toString();
                System.out.println("Key" + " "+ code + " is pressed");

                // On fait évoluer la position de la grenouille
                try {
                    game.deplacementFrog(Direction.valueOf(code));
                } catch (IllegalArgumentException exception) {
                    System.out.println("Veillez utiliser uniquement les flèches du pavé numérique");
                } catch (Exception exception){
                    System.out.println(exception.toString());
                }
            }

        };

        stage.show();
        timer.start(); //On démarre l'animation

    }

    /**
     *Retourne le path de l'image de la grenouille
     * @return le chemin d'accès à l'image de la grenouille
     */
    public String imageFrog()
    {
        return "file:src/main/java/graphicalElements/frog_img.png";
    }

    /**
     * Retourne le path de l'image de la voiture
     * @return le chemin d'accès à l'image de la voiture
     */
    public String imageVehicule()
    {
        return "file:src/main/java/graphicalElements/car_img.png";
    }

    /**
     * Retourne le path de l'image de fond de l'application
     * @return le chemin d'accès à l'image de fond
     */
    public String imageBackground()
    {
        return "file:src/main/java/graphicalElements/background_img.png";
    }

    /**
     * Arrêt des animations sur les voies pour lesquels la voiture est arrivée à la fin de la voie
     */
    private void actu_timelines(){
        for(int i=0; i<voies.length; i++){
            if(voies[i].is_timeline){
                if(voies[i].timeline.getStatus()==Animation.Status.STOPPED){
                    voies[i].is_timeline = false;
                    voies[i].timeline.stop(); // on arrête réellement la timeline
                }
            }
        }
    }

    /**
     * Ajout d'une animation (timeline) sur les voies qui n'en ont pas avec une probabilité
     * définie dans la classe Voie avec le booléen passage.
     * @see Voie
     */
    private void actualisation(){
        for(int i=0; i<voies.length; i++){
            //Parcours de chaque voie
            if(!voies[i].is_timeline){
                //S'il n'y a pas de timeline sur la voie (ie pas de voitures)
                if(voies[i].passage()){
                    //Ajout d'une timeline avec une probabilité définie par le booléen passage
                    voies[i].is_timeline = true;
                    DoubleProperty x = new SimpleDoubleProperty();
                    DoubleProperty y = new SimpleDoubleProperty();

                    voies[i].position.add(new DoubleProperty[]{x, y});

                    //Création des voitures
                    voies[i].timeline = new Timeline(
                            new KeyFrame(Duration.seconds(0),
                                    new KeyValue(x, -d_x),
                                    new KeyValue(y, (i + 1) * d_y)
                            ),

                            new KeyFrame(Duration.seconds(voies[i].getVitesse()),
                                    new KeyValue(x, W),
                                    new KeyValue(y, (i + 1) * d_y)
                            )
                    );
                    voies[i].timeline.play();
                }
            };
        }
    }

    public static void main(String[] args) { launch(args); }

}
