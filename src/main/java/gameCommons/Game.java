package gameCommons;
import util.Direction;
import util.Plateau;
import frog.Frog;

/**
 * Deroulement du jeu en fonction des actions de l'utilisateur
 */
public class Game implements IFrog {
    //Instentiation des objets utiles pour le jeu : un plateau, une grenouille...

    public Frog frog = new Frog(new int[2] , 1);
    public Plateau plateau;
    public boolean victoire = false;
    public int d_x;
    public int d_y;
    public int H;
    public int W;

    /**
     * Constructeur de la classe Game
     * @param dx
     *          largeur d'une case du plateau utilise pour le jeu
     * @param dy
     *          hauteur d'une case du plateau utilise pour le jeu
     * @param height
     *              hauteur du plateau utilise pour le jeu
     * @param W
     *         largeur du plateau utilise pour le jeu
     */
    public Game(int dx, int dy, int height, int W){
        this.d_x = dx;
        this.d_y = dy;
        this.W = W;
        this.H = height;
        this.plateau = new Plateau(W/dx, height/dy + 2, 1);
    }

    @Override
    /**
     * Fait evoluer la position de la grenouille d'une case dans la direction donnee en entree
     * si le déplacement est possible sans sortir du plateau de jeu
     * @param dir
     *          Direction dans laquelle doit se deplacer la grenouille
     */
    public void deplacementFrog(Direction dir){

        int x = frog.getPosition()[0];
        int y = frog.getPosition()[1];

        //Valeurs limites du plateau
        int x_lim = this.W;
        int y_lim = this.H + d_y;

        switch(dir){
            case UP:
                if(0<=y-d_y) frog.setPosition(x, y-d_y);
                break;
            case DOWN:
                if(y+d_y<=y_lim) frog.setPosition(x, y+d_y);
                break;
            case LEFT:
                if(-d_x/2<=x-d_x) frog.setPosition(x-d_x, y);
                break;
            case RIGHT:
                if(x+d_x<=x_lim)frog.setPosition(x+d_x, y);
                break;
            default:
                break;
        }
    }

}
