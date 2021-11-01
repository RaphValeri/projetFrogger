package frog;

import java.util.Arrays;
import util.Direction;

public class Frog {
    private int[] position;
    private int life;
    private Direction direction = Direction.none;

    public Frog(int[] position, int life) {
        this.position = position;
        this.life = life;
    }

    public int[] getPosition() { return this.position; }
    public void setPosition(int a, int b) { this.position = new int[]{a, b}; }

    public int getLife() { return this.life; }
    public void setLife(int c) { this.life = c; }

    public Direction getDirection() { return this.direction; }
    public void setDirection(Direction dir) { this.direction = dir; }



    public String toString() {
        return String.format("Position : %s\nVie : %d",
                Arrays.toString(this.position), this.life);
    }

}
