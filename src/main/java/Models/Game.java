package Models;

import Enums.MoveDirection;
import Enums.TileType;

import java.util.*;

import static Enums.TileType.*;

public class Game implements Observer {


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
        width = map[0].length;
        height = map[1].length;
    }


    public void startGame() {
        setUpMap();
    }

    public void gameOver() {
        throw new UnsupportedOperationException("method gameOver() has not yet been implemented");
    }

    private void setUpMap() {
        walls = new ArrayList<>();
        ghosts = new ArrayList<>();
        items = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]) {
                    case WALL:
                        walls.add(new Wall(i, j));
                        break;
                    case PACMAN:
                        pacman = new Pacman(i, j, false, false);
                        break;
                    case PALLET:
                        Pallet pallet = new Pallet(i, j, false);
                        pallet.addObserver(this);
                        items.add(pallet);
                        break;
                    case SUPERPALLET:
                        Pallet superpallet = new Pallet(i, j, true);
                        superpallet.addObserver(this);
                        items.add(superpallet);
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
                //Now we go through all items and check if we need to eat them
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    Item item = (Item) iterator.next();
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

        } else {
            System.out.println("But we can't move to " + direction);
        }
        cleanUp();

    }

    private boolean characterCanMove(Character character, MoveDirection direction, int amount) {
        int x = character.getPosX();
        int y = character.getPosY();
        for (Wall w : walls) {
            if (!w.canMoveTo(x, y, amount, direction)) return false;
        }
        return true;
    }

    private void cleanUp(){
        Iterator i = items.iterator();
        while (i.hasNext()){
            Item item = (Item) i.next();
            if(item.isEaten()) i.remove();
        }
    }

    private Character characterFromPlayerNr(int playerNr) {
        System.out.println("characterFromPlayerNr() in Game needs to be unhackified. Keep track of playernumbers and their characters on registring.");
        //TODO unhackify this
        return pacman;
    }

    public TileType[][] getTilesFromState() {
        TileType[][] tiles = new TileType[width][height];
        //adding walls
        for (Wall w : walls) {
            tiles[w.getPosX()][w.getPosY()] = WALL;
        }
        //adding pacman
        tiles[pacman.getPosX()][pacman.getPosY()] = PACMAN;
        //adding ghosts
        for (Ghost g : ghosts) {
            tiles[g.getPosX()][g.getPosY()] = EMPTY;//TODO ghost tile;
        }
        //adding items
        for (Item i : items) {
            TileType type = EMPTY;
            if(i instanceof Pallet){
                if (((Pallet) i).isSuper()) type = SUPERPALLET;
                else type = PALLET;
            }
            tiles[i.getPosX()][i.getPosY()] = type;
            //TODO make this actually test for type of items
        }
        //fill empty tiles
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (tiles[i][j] == null) tiles[i][j] = EMPTY;
            }
        }
        return tiles;
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof Item) {
            System.out.println(o.toString()+" ate an item.");
            ((Item) observable).setEaten(true);
        }
    }
}
