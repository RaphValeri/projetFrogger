package gameCommons;
import util.Direction;
import util.Plateau;
import util.Voie;
import frog.Frog;
import graphicalElements.App;

public class Game implements IEnvironment, IFrog {
    //Instentiation des objets utiles pour le jeu : un plateau, une grenouille...

    public Frog frog = new Frog(new int[2] , 1);
    public Plateau plateau;
    public boolean victoire = false;
    public int d_x;
    public int d_y;
    public int H;
    public int W;

    public Game(int dx, int dy, int height, int W){
        this.d_x = dx;
        this.d_y = dy;
        this.W = W;
        this.H = height;
        this.plateau = new Plateau(W/dx, height/dy + 2);
    }

    @Override
    public void deplacementFrog(Direction dir){
        //Modification de la position de la grenouille
        //en vérifiant que le déplacement est possible

        int x = frog.getPosition()[0];
        int y = frog.getPosition()[1];

        int x_lim = this.W; // Changer les limites avec les dimensions de la fenetre
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

    public int main(String[] args) {
        while(frog.getLife()==1 & !victoire){
            System.out.println("");

        }
        return 0;
    }
}
