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
    private int width;
    private int height;

    public void newGame(TileType[][] tiles) {
        map = tiles;
        setUpMap();
        width = map[0].length+1;
        height = map[1].length+1;
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
        items = new ArrayList<>();
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

    public void moveCharacter(int playerNr, MoveDirection direction) {
        //TODO determine the parameter we get to determine which character to move.
        Character character = characterFromPlayerNr(playerNr);
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
        else{
            System.out.println("But we can't move to "+direction);
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

    private Character characterFromPlayerNr(int playerNr){
        System.out.println("characterFromPlayerNr() in Game needs to be unhackified. Keep track of playernumbers and their characters on registring.");
        //TODO unhackify this
        return pacman;
    }

    public TileType[][] getTilesFromState() {
        TileType[][] tiles = new TileType[width][height];
        //adding walls
        for (Wall w : walls){
            tiles[w.getPosX()][w.getPosY()] = WALL;
        }
        //adding pacman
        tiles[pacman.getPosX()][pacman.getPosY()] = PACMAN;
        //adding ghosts
        for (Ghost g : ghosts){
            tiles[g.getPosX()][g.getPosY()] = EMPTY;//TODO ghost tile;
        }
        //adding items
        for (Item i : items){
            tiles[i.getPosX()][i.getPosY()] = PALLET;
            //TODO make this actually test for type of items
        }
        return tiles;
    }
}
