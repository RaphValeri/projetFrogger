package graphicalElements;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.*;
import gameCommons.Game;
import frog.Frog;


/**
 * Classe qui gere l'IHM du jeu Frogger
 */
public class App extends Application implements IFroggerGraphics, Voiture, Camion {

    // canvas dimensions
    public static final int W = 500; //largeur de la route
    public static final int H = 500; //hauteur de la route

    // Dimension d'une case sur une voie
    public static final int d_x = W/6;
    public static final int d_y = H/10;

    //Game instance
    public Game game = new Game(d_x, d_y, H, W);
    public Frog frog = game.frog;
    Plateau plateau = new Plateau(W/d_x, H/d_y + 2, 1);
    Voie[] voies = plateau.voie;


    //Images
    Image frog_img = imageFrog(d_x, d_y);
    Image car_img = imageVehicules(imageVoiture, d_x, d_y);
    Image truck_img = imageVehicules(imageCamion, d_x, d_y);
    Image background_img = imageBackground(W, H+2*d_y);

    public App() throws Exception {
    }

    @Override
    /**
     * Definition de la methode abstraite de la classe Application
     * Creation de la fenetre principale du jeu Frogger
     *
     * @param stage
     *              Stage de l'application
     */
    public void start(Stage stage) {
        stage.setTitle("FROGGER");
        stage.setResizable(false);

        final Canvas canvas = new Canvas(W, H+2*d_y);

        StackPane root = new StackPane();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );
        root.getChildren().add( canvas );

        //On place la grenouille au point de d??part (en bas au milieu du plateau)
        frog.setPosition(W/2-d_x/2, H+d_y);


        //Cr??ation de l'animation
        AnimationTimer timer = new AnimationTimer() {
            //Definition de la m??thode abstraite handle de AnimationTimer()
            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, H + d_y, W, d_y);
                gc.drawImage(background_img, 0, 0, W, H + 2 * d_y);

                theScene.setOnKeyPressed(this::KeyPressed);

                for (int i=0; i < voies.length; i++) {
                    //Parcours de toutes les voies
                    if (voies[i].is_timeline) {
                        //S'il y a une timeline sur la voie (c-a-d une voiture) on affiche la voiture
                        for (int j = 0; j < voies[i].position.size(); j++) {

                            int x = (int) voies[i].position.get(j)[0].doubleValue();
                            int y = (int) voies[i].position.get(j)[1].doubleValue();

                            condition_creation(x, i, 1);

                            Image img = car_img;
                            double dx = d_x;
                            if (voies[i].getType() == 3) {
                                img = truck_img;
                                dx = 1.5 * d_x;
                            }

                            if (voies[i].sens == 1) {
                                gc.drawImage(img, x, y, dx, d_y);
                            } else { gc.drawImage(rotate(img), x, y, dx, d_y);}


                            //Affichage de la grenouille
                            gc.drawImage(frog_img, frog.getPosition()[0], frog.getPosition()[1]);


                            //V??rification d'une ??ventuelle collision
                            if ((frog.getPosition()[0] - d_x <= x) & (x <= (frog.getPosition()[0]) & y == frog.getPosition()[1])) {
                                System.out.println("GAME OVER !!!!");
                                frog.setLife(0);

                            }
                            if (frog.getPosition()[1] == 0) {
                                game.victoire = true;
                            }
                        }
                    }
                    if (voies[i].is_timeline2) {
                        //S'il y a une 2eme timeline sur la voie (c-a-d un 2eme vehicule) on affiche le vehicule
                        for (int j = 0; j < voies[i].position2.size(); j++) {

                            int x = (int) voies[i].position2.get(j)[0].doubleValue();
                            int y = (int) voies[i].position2.get(j)[1].doubleValue();

                            condition_creation(x, i, 2);

                            Image img = car_img;
                            double dx = d_x;
                            if (voies[i].getType() == 3) {
                                img = truck_img;
                                dx = 1.5 * d_x;
                            }

                            if (voies[i].sens == 1) {
                                gc.drawImage(img, x, y, dx, d_y);
                            } else { gc.drawImage(rotate(img), x, y, dx, d_y);}


                            //Affichage de la grenouille
                            gc.drawImage(frog_img, frog.getPosition()[0], frog.getPosition()[1]);


                            //V??rification d'une ??ventuelle collision
                            if ((frog.getPosition()[0] - d_x <= x) & (x <= (frog.getPosition()[0]) & y == frog.getPosition()[1])) {
                                System.out.println("GAME OVER !!!!");
                                frog.setLife(0);
                            }
                            if (frog.getPosition()[1] == 0) {
                                game.victoire = true;
                            }
                        }
                    }
                }

                if (frog.getLife()==0 || game.victoire) {
                    this.stop(); //Arr??t de l'animation en cas de collision

                    frog.setLife(1);

                    int[] tabScore = plateau.recupererPartie("test.txt");   //Affiche les meilleurs scores.

                    System.out.println("Les " + plateau.getNScore() + " meilleurs scores.");
                    for(int k = tabScore.length - 1; k >= 0; --k)
                    {
                        System.out.println(10 - k + ".   " + tabScore[k]);
                    }



                    Button bt1 = new Button("Try again");
                    bt1.setPrefSize(W/6, H/4);
                    Button bt2 = new Button("Next level");
                    bt2.setPrefSize(W/6, H/4);
                    Button bt3 = new Button("Quit");
                    bt3.setPrefSize(W/6, H/4);
                    Button bt4 = new Button("Save game");
                    bt4.setPrefSize(W/6, H/4);

                    bt1.setOnMouseClicked((MouseEvent ME) -> {
                        plateau = new Plateau(W/d_x, H/d_y + 2, plateau.getLevel());
                        voies = plateau.voie;
                        actualisation();
                        this.start();
                        frog.setPosition(W/2-d_x/2, H+d_y);
                        root.getChildren().remove(1);
                    });

                    bt2.setOnMouseClicked((MouseEvent ME) -> {
                        plateau = new Plateau(W/d_x, H/d_y + 2, plateau.getLevel() + 1);
                        voies = plateau.voie;
                        actualisation();
                        this.start();
                        frog.setPosition(W/2-d_x/2, H+d_y);
                        root.getChildren().remove(1);
                    });

                    bt3.setOnMouseClicked((MouseEvent ME) -> System.exit(0));

                    bt4.setOnMouseClicked((MouseEvent ME) -> plateau.enregistrerPartie("test.txt", plateau.getLevel()));

                    HBox HB;
                    if (!game.victoire) {
                        HB = new HBox(bt1, bt3, bt4);
                    } else {HB = new HBox(bt1, bt2, bt3, bt4);}

                    HB.setAlignment(Pos.CENTER);
                    root.getChildren().add(HB);
                }

                if (game.victoire) {
                    game.victoire = false;
                }

                try {actu_timelines();}
                catch(Exception e){e.printStackTrace();}

                actualisation();

            }


            /**
             * Methode appelee lors de l'appui sur une touche du clavier
             * Elle permet de faire evoluer la position de la grenouille selon la touche utilisee par le joueur
             * @param e
             *          Evenement d'action sur une touche du clavier
             * @exception IllegalArgumentException
             *                                      Exception levee si le joueur utilise une touche du clavier autre que les fleches
             */
            private void KeyPressed(KeyEvent e) {
                String code = e.getCode().toString();
                System.out.println("Key" + " "+ code + " is pressed");

                // On fait ??voluer la position de la grenouille
                try {
                    game.deplacementFrog(Direction.valueOf(code));
                } catch (IllegalArgumentException exception) {
                    System.out.println("Veillez utiliser uniquement les fl??ches du pav?? num??rique");
                } catch (Exception exception){
                    System.out.println(exception.toString());
                }
            }

        };

        stage.show();
        timer.start(); //On d??marre l'animation

    }

    /**
     *Retourne l'image de la grenouille
     * @param dx
     *          largeur souhaitee de l'image
     * @param dy
     *          hauteur souhaitee de l'image
     * @return Image de la grenouille
     * @throws Exception Exception levee en cas de ressource non valide
     */
    public Image imageFrog(int dx, int dy) throws Exception{
        try {
            String ressource = "file:src/main/java/graphicalElements/frog_img.png";
            Image im = new Image(ressource, dx, dy, false, false);
            return im;
        } catch (IllegalArgumentException e) {
            System.out.println("Ressource image grenouille non valide");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Chargement de l'image de fond
     * @param dx
     *          largeur souhaitee de l'image
     * @param dy
     *          hauteur souhaitee de l'image
     * @return Image de fond
     * @throws Exception Exception levee lorsque la ressource est mal renseign??e
     */
    public Image imageBackground(int dx, int dy) throws Exception
    {
        try{
            String ressource =  "file:src/main/java/graphicalElements/background_img.png";
            Image im = new Image(ressource, dx, dy, false, false);
            return im;
        } catch (IllegalArgumentException e){
            System.out.println("Ressource image de fond non valide");
            e.printStackTrace();
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Chargement d'une image de vehicule
     * @param res path de l'image a charger
     * @param dx  largeur souhaitee pour l'image
     * @param dy  hauteur souhaitee pour l'image
     * @return Image d'un vehicule
     * @throws Exception Exception levee en cas de ressource non valide
     */
    public Image imageVehicules(String res, int dx, int dy) throws Exception
    {
        try{
            String ressource = res;
            Image im = new Image(ressource, dx, dy, false, false);
            return im;
        } catch (IllegalArgumentException e){
            System.out.println("Ressource image du v??hicule non valide");
            e.printStackTrace();
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Rotation de 180?? d'une image
     * @param img Image ?? laquelle on souhaite faire effectuer uen rotation
     * @return image qui a effectue une rotation de 180??
     */
    public Image rotate(Image img) {
        ImageView IV = new ImageView();
        IV.setImage(img);
        IV.setRotate(180);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        return IV.snapshot(params, null);
    }

    /**
     * Arret des animations pour les vehicules arrives ?? la fin de la voie
     */
    private void actu_timelines(){
        for(int i=0; i<voies.length; i++){
            if(voies[i].is_timeline & voies[i].timeline != null){
                if(voies[i].timeline.getStatus()==Animation.Status.STOPPED){
                    voies[i].is_timeline = false;
                    voies[i].timeline.stop(); // on arr??te r??ellement la timeline
                }
            }
            if(voies[i].is_timeline2 & voies[i].timeline2 != null){
                if(voies[i].timeline2.getStatus()==Animation.Status.STOPPED){
                    voies[i].is_timeline2 = false;
                    voies[i].timeline2.stop(); // on arr??te r??ellement la timeline
                }
            }
        }
    }

    /**
     * Ajout d'une animation (timeline) sur les voies qui peuvent contenir un vehicule de plus avec une probabilite
     * definie dans la classe Voie avec le bool??en passage.
     * @see Voie
     */
    private void actualisation(){
        for(int i=0; i<voies.length; i++){
            //Parcours de chaque voie
            if(!voies[i].is_timeline & !voies[i].voiture2){
                //S'il n'y a pas de timeline pr la voiture 1 et que la voiture 2 n'empeche pas la 1
                if(voies[i].passage(plateau.getLevel())){
                    //Ajout d'une timeline avec une probabilit?? d??finie par le bool??en passage
                    voies[i].is_timeline = true;
                    creation_timeline(i, 1);
                }
            };
            if(!voies[i].is_timeline2 & !voies[i].voiture1){
                //S'il n'y a pas une 2eme timeline et que le vehicule 1 n'emp??che pas la cr??ation d'un autre
                if(voies[i].passage(plateau.getLevel())){
                    //Ajout d'une autre timeline avec une probabilit?? d??finie par le bool??en passage
                    voies[i].is_timeline2 = true;
                    creation_timeline(i, 2);
                }
            };
        }
    }


    /**
     * Creation d'une Timeline pour un vehicule sur une voie
     * @param i indice de la voie
     * @param vehicule vehicule auquel est associee la Timeline
     */
    private void creation_timeline(int i, int vehicule){
        Timeline timeline;
        DoubleProperty x = new SimpleDoubleProperty();
        DoubleProperty y = new SimpleDoubleProperty();

        if(vehicule == 1) voies[i].position.add(new DoubleProperty[]{x, y});
        if(vehicule == 2) voies[i].position2.add(new DoubleProperty[]{x, y});

        double dx = d_x;
        if (voies[i].getType() == 3) {
            dx = 1.5 * d_x;
        }

        //D??finition de la Timeline en fonction du sens de la voie
        if(voies[i].sens==1) {

            timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0),
                            new KeyValue(x, -dx),
                            new KeyValue(y, (i + 1) * d_y)
                    ),

                    new KeyFrame(Duration.seconds(1),
                            new KeyValue(x, W),
                            new KeyValue(y, (i + 1) * d_y)
                    )
            );

            timeline.setRate(voies[i].getVitesse());
        }
        else{
             timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0),
                            new KeyValue(x, W),
                            new KeyValue(y, (i + 1) * d_y)
                    ),

                    new KeyFrame(Duration.seconds(1),
                            new KeyValue(x, -dx),
                            new KeyValue(y, (i + 1) * d_y)
                    )
            );

            timeline.setRate(voies[i].getVitesse());

        }
        if(vehicule == 1) {
            voies[i].voiture1 = true;
            voies[i].timeline = timeline;
            voies[i].timeline.play();
        }
        if(vehicule == 2) {
            voies[i].voiture2 = true;
            voies[i].timeline2 = timeline;
            voies[i].timeline2.play();
        }

    }

    /**
     * Methode qui met ?? jour les booleen indiquant la possibilite d'une creation d'un autre vehicule
     * sur une meme voie
     * @param x position de la voiture sur la voie i
     * @param i voie
     * @param vehicule le vehicule actuellement sur la voie
     */
    private void condition_creation(int x, int i, int vehicule){
        //En fonction du sens de la voie, les conditions diff??rent
        switch (voies[i].sens){
            case 1 :
                if(vehicule==1) {
                    voies[i].voiture1 = x <= 3 * d_x;
                }
                if(vehicule==2) {
                    voies[i].voiture2 = x <= 3 * d_x;
                }
                break;
            case -1:
                if(vehicule==1) {
                    voies[i].voiture1 =  x > (500 - 2 * d_x);
                }
                if(vehicule==2) {
                    voies[i].voiture2 = x > (500 - 2 * d_x);
                }
        }
    }



    public static int main(String[] args) {
        launch(args);
        return 0;
    }


}
