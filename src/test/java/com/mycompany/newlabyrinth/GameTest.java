package com.mycompany.newlabyrinth;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    public GameTest() {
    }

    @Test
    public void testReadFromFile() {

    }

    @Test
    public void testPrint() {
    }

    @Test
    public void testFindEntrance() throws Exception {
        Game myGame = new Game();
        myGame.readFromFile();
        Position initialPos = new Position(0, 1);
        Position expectedInitialPos = myGame.findEntrance();
        assertEquals(initialPos, expectedInitialPos);
    }

    @Test
    public void testIsEmptySpace() {
        Game myGame = new Game();
        myGame.readFromFile();
        Position initialPos = new Position(0, 1);
        Position initialPos2 = new Position(1, 1);
        assertFalse(myGame.isEmptySpace(initialPos));
        assertTrue(myGame.isEmptySpace(initialPos2));
    }

    @Test
    public void testIsExit() {
        Game myGame = new Game();
        myGame.readFromFile();
        Position exitPosition = new Position(10, 38);
        Position notExitPosition = new Position(9, 38);
        assertTrue(myGame.isExit(exitPosition));
        assertFalse(myGame.isExit(notExitPosition));
    }

    @Test
    public void testGameMovement() throws Exception {
        Game myGame = new Game();
        myGame.readFromFile();
        Position initialPos = myGame.findEntrance();
        myGame.gameMovement(initialPos);
        ArrayList<Position> myRoute = myGame.getRoute();
        Position finalPos = new Position(10, 38);
        Position expectedposition = myRoute.get(myRoute.size() - 1);
        assertEquals(finalPos, expectedposition);
    }

}
