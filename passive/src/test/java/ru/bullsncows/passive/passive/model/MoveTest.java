package ru.bullsncows.passive.passive.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoveTest {

    @Test
    public void moveOfNullString() {
        assertFalse(new Move(null).isCorrect());
    }

    @Test
    public void moveOfEmptyString() {
        assertFalse(new Move("").isCorrect());
    }

    @Test
    public void moveOfShortString() {
        assertFalse(new Move("123").isCorrect());
    }

    @Test
    public void moveOfLongString() {
        assertFalse(new Move("12345").isCorrect());
    }

    @Test
    public void moveOfLiteralString() {
        assertFalse(new Move("123F").isCorrect());
    }

    @Test
    public void moveOfOkString() {
        assertTrue(new Move("1234").isCorrect());
    }

}