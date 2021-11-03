package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void values() {
        assertArrayEquals(new Direction []{
                Direction.UP,
                Direction.RIGHT,
                Direction.LEFT,
                Direction.DOWN,
                Direction.none
        }, Direction.values());

    }

    @Test
    void valueOf() {
        assertEquals(Direction.DOWN, Direction.valueOf("DOWN"));
        assertEquals(Direction.UP, Direction.valueOf("UP"));
        assertEquals(Direction.LEFT, Direction.valueOf("LEFT"));
        assertEquals(Direction.RIGHT, Direction.valueOf("RIGHT"));
    }
}