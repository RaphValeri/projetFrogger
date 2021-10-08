package gameCommons;
import util.Direction;
import util.Plateau;
import util.Voie;
import frog.Frog;


public class Game implements IEnvironment, IFrog {
    //Instentiation des objets utiles pour le jeu : un plateau, une grenouille...

    public Frog frog = new Frog(new int[2] , 1);
    public Plateau plateau = new Plateau();
    public boolean victoire = false;



    @Override
    public void deplacementFrog(Direction dir){
        //Modification de la position de la grenouille
        //en vérifiant que le déplacement est possible

        int x = frog.getPosition()[0];
        int y = frog.getPosition()[1];

        int x_lim = plateau.getLargeur()-1;
        int y_lim = plateau.getHauteur()-1;

        switch(dir){
            case up:
                if(0<=y-1) frog.setPosition(x, y-1);
                break;
            case down:
                if(y+1<=y_lim) frog.setPosition(x, y+1);
                break;
            case left:
                if(0<=x-1) frog.setPosition(x-1, y);
                break;
            case right:
                if(x+1<=x_lim)frog.setPosition(x+1, y);
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
