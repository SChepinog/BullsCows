package ru.bullsncows.passive.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameModelTest {

    @Test
    public void noMovesForGameModel() {
        List<Move> moves = new GameModel().getMoves();
        assertNotNull(moves);
        assertTrue(moves.isEmpty());
    }

}