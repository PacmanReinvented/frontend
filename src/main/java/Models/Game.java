package Models;

import Enums.MoveDirection;

import java.util.List;

public class Game {


    private List<Wall> walls;
    private List<Item> items;
    private List<Ghost> ghosts;
    private Pacman pacman;

    public void newGame() {
        throw new UnsupportedOperationException("method newGame() has not yet been implemented");

    }

    public void startGame() {
        throw new UnsupportedOperationException("method startGame() has not yet been implemented");
    }

    public void gameOver() {
        throw new UnsupportedOperationException("method gameOver() has not yet been implemented");
    }

    public void moveCharacter(Object o, MoveDirection direction) {
        //TODO determine the parameter we get to determine which character to move.
        Character character = null;//TODO we will assign this variable a reference once we solve the above issue
        if (characterCanMove(character, direction, 1)) {
            character.moveTowards(direction);
            if (character instanceof Pacman) {
                for (Item item : items) {
                    if (item.collidesWith((character))){
                        ((Pacman) character).takeItem(item);
                    }
                }
            }
            //Are we pacman? then iterate over all ghosts
            if (character instanceof Pacman) {
                for (Ghost g : ghosts) {
                    if (g.collidesWith(character)) g.doCollision(character);
                }

            }
            //Are we a ghost? then just check collision with pacman. we do not need to collide with fellow ghosts
            else if (character instanceof Ghost) {
                if (pacman.collidesWith(character)) pacman.doCollision(character);
            }

        }
    }

    private boolean characterCanMove(Character character, MoveDirection direction, int amount) {
        int x = character.getPosX();
        int y = character.getPosY();
        for (Wall w : walls) {
            if (!w.canMoveTo(x, y, amount, direction)) return false;
        }
        return true;
    }
}
