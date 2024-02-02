
package com.mycompany.newlabyrinth;

public class NewLabyrinth {

    public static void main(String[] args) throws Exception {

        
        Game myGame = new Game();
        myGame.readFromFile();
        Position initialPos = myGame.findEntrance();
        myGame.gameMovement(initialPos);
        myGame.printRoute();
    }
}
