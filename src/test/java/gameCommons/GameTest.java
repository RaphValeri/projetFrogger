package gameCommons;

import frog.Frog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Direction;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    @DisplayName("Test de la méthode deplacementFrog")
    void deplacementFrog() {
        Game game = new Game(500/6, 500/10, 500, 500);
        Frog frog = game.frog;
        frog.setPosition(250, 500);
        int x = frog.getPosition()[0];
        int y = frog.getPosition()[1];
        game.deplacementFrog(Direction.UP);
        assertArrayEquals(new int []{x, y-50}, frog.getPosition());
        game.deplacementFrog(Direction.RIGHT);
        assertArrayEquals(new int []{x+500/6, y-50}, frog.getPosition());
        game.deplacementFrog(Direction.LEFT);
        assertArrayEquals(new int []{x, y-50}, frog.getPosition());
        game.deplacementFrog(Direction.DOWN);
        assertArrayEquals(new int []{x, y}, frog.getPosition());
    }
}