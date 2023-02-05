package ru.bullsncows.passive.passive.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameModelTest {

    @Test
    public void noMovesForGameModel() {
        assertNotNull(new GameModel().getMoves());
        assertTrue(new GameModel().getMoves().isEmpty());
    }

}