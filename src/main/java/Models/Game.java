package Models;

import Enums.MoveDirection;
import Enums.TileType;

import java.util.ArrayList;
import java.util.List;

import static Enums.TileType.*;

public class Game {


    private List<Wall> walls;
    private List<Item> items;
    private List<Ghost> ghosts;
    private Pacman pacman;
    private TileType[][] map;// We use this to store the map, so we can easily reset it to its default state.

    public void newGame(TileType[][] tiles) {
        map = tiles;
        setUpMap();
    }


    public void startGame() {
        setUpMap();
    }

    public void gameOver() {
        throw new UnsupportedOperationException("method gameOver() has not yet been implemented");
    }

    private void setUpMap(){
        walls = new ArrayList<>();
        ghosts = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case WALL:
                        walls.add(new Wall(j, i));
                        break;
                    case PACMAN:
                        pacman = new Pacman(j, i, false, false);
                        break;
                    case PALLET:
                        items.add(new Pallet(j, i, false));
                        break;
                    case SUPERPALLET:
                        items.add(new Pallet(j, i, true));
                        break;
                    default:
                        System.out.println("Tile type " + map[i][j] + " was not recognized.");
                        break;
                }
            }
        }
    }

    public void moveCharacter(Object o, MoveDirection direction) {
        //TODO determine the parameter we get to determine which character to move.
        Character character = null;//TODO we will assign this variable a reference once we solve the above issue
        if (characterCanMove(character, direction, 1)) {
            character.moveTowards(direction);
            if (character instanceof Pacman) {
                for (Item item : items) {
                    if (item.collidesWith((character))) {
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
