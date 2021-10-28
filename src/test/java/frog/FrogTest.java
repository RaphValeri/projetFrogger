package frog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Direction;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FrogTest {

    @Test
    @DisplayName("Test du getter position")
    void getPosition() {
        var f1 = new Frog(new int[]{0, 4} , 1);
        var f2 = new Frog(new int[]{3, 1} , 1);
        assertArrayEquals(new int[]{0,4}, f1.getPosition());
        assertArrayEquals(new int[]{3,1}, f2.getPosition());

    }

    @Test
    @DisplayName("Test du setter position")
    void setPosition() {
        var f1 = new Frog(new int[]{2, 5} , 1);
        var f2 = new Frog(new int[]{0, 3} , 0);
        f1.setPosition(1, 7);
        f2.setPosition(0, 0);
        assertArrayEquals(new int[]{1, 7}, f1.getPosition());
        assertArrayEquals(new int[]{0, 0}, f2.getPosition());
    }

    @Test
    @DisplayName("Test du getter life")
    void getLife() {
        var f1 = new Frog(new int[]{2, 5}, 0);
        var f2 = new Frog(new int[]{1, 2}, 1);
        assertEquals(0, f1.getLife());
        assertEquals(1, f2.getLife());
    }

    @Test
    @DisplayName("Test du setter life")
    void setLife() {
        var f1 =new Frog(new int[]{2, 5}, 0);
        var f2 =new Frog(new int[]{0, 1}, 1);
        f1.setLife(1);
        f2.setLife(1); //test without changing the value of life
        assertEquals(1, f1.getLife());
        assertEquals(1, f2.getLife());
    }

    @Test
    @DisplayName("Test du getter Direction")
    void getDirection() {
        var f1 = new Frog(new int[]{3, 1}, 0);
        var f2 = new Frog(new int[]{0, 0}, 1);
        assertEquals(Direction.none, f1.getDirection());
        assertEquals(Direction.none, f2.getDirection());
    }

    @Test
    @DisplayName("Test du setter direction")
    void setDirection() {
        var f1 = new Frog(new int[]{3, 1}, 0);
        f1.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, f1.getDirection());
        f1.setDirection(Direction.UP);
        assertEquals(Direction.UP, f1.getDirection());
        f1.setDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, f1.getDirection());
    }

    @Test
    @DisplayName("Test de la méthode de representation")
    void testToString() {
        var f1 = new Frog(new int[]{3, 1}, 0);
        var f2 = new Frog(new int[]{0, 0}, 1);
        assertEquals(String.format("Position : %s\nVie : %d", Arrays.toString(new int[]{3, 1}), 0), f1.toString());
        assertEquals(String.format("Position : %s\nVie : %d", Arrays.toString(new int[]{0, 0}), 1), f2.toString());

    }
}