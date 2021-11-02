package frog;

import java.util.Arrays;
import util.Direction;

/**
 * Classe qui définie les caractéristiques de la grenouille
 */
public class Frog {
    private int[] position;
    private int life;
    private Direction direction = Direction.none;


    /**
     * Constructeur de la classe Frog
     * @param position
     *                  position de la grenouille
     * @param life
     *                  vie de la grenouille (1 pour une grenouille en vie, 0 sinon)
     */
    public Frog(int[] position, int life) {
        this.position = position;
        this.life = life;
    }

    /**
     * Getter de la position de la grenouille : permet d'accéder à la valeur de cette variable d'instance
     * @return position de la grenouille
     */
    public int[] getPosition() { return this.position; }

    /**
     * Setter de la position de la grenouille : permet de modifier la variable d'instance
     * @param a
     *          nouvelle posistion de la grenouille selon x
     * @param b
     *          nouvelle position de la grenouille selon y
     */
    public void setPosition(int a, int b) { this.position = new int[]{a, b}; }

    /**
     * Getter de life : permet d'accéder à la variable d'instance life
     * @return la valeur de life
     */
    public int getLife() { return this.life; }

    /**
     * Setter de la variable d'instance life : permet de la modifier
     * @param c
     *          nouvelle valeur de life
     */
    public void setLife(int c) { this.life = c; }

    /**
     * Getter de la variable d'instance de la direction de la grenouille
     * @return direction de la grenouille
     */
    public Direction getDirection() { return this.direction; }

    /**
     * Setter de la variable d'instance direction
     * @param dir
     *             nouvelle direction de la grenouille
     */
    public void setDirection(Direction dir) { this.direction = dir; }


    /**
     * Méthode de représentation des données de la grenouille
     * @return  Informations sur la grenouille
     */
    public String toString() {
        return String.format("Position : %s\nVie : %d",
                Arrays.toString(this.position), this.life);
    }

}
