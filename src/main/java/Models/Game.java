package Models;

import Enums.FruitType;
import Enums.GameState;
import Enums.MoveDirection;
import Enums.TileType;

import java.util.*;

import static Enums.TileType.*;

public class Game extends Observable implements Observer {


    private List<Wall> walls;
    private List<Item> items;
    private List<Ghost> ghosts;
    private Pacman pacman;
    private TileType[][] map;// We use this to store the map, so we can easily reset it to its default state.
    private int width;
    private int height;

    private List<Integer> registeredPlayers = new ArrayList<>();

    private Map<Integer, Character> playerCharacterMap;//This is where we save which id belongs to which character
    private Map<Integer, Boolean> previousPacmen = new HashMap<>();// this is where we save which players have already been Pacman.
    private int currentPacman;

    public void newGame(TileType[][] tiles) {
        map = tiles;
        setUpMap();
        width = map[0].length;
        height = map[1].length;


        selectNewPacman();

    }


    public void startGame() {
        setUpMap();
        selectNewPacman();
    }

    private void selectNewPacman() {
        playerCharacterMap = new HashMap<>();
        Iterator iterator = registeredPlayers.iterator();
        boolean pacmanFound = false;
        while (iterator.hasNext()) {
            int currNr = (Integer) iterator.next();
            boolean wasPacman = previousPacmen.get(currNr);
            System.out.println("Was "+currNr+" pacman?: "+wasPacman);
            if (!wasPacman) {
                pacmanFound = true;
                previousPacmen.replace(currNr, true);
                currentPacman = currNr;
            }
            if(pacmanFound) break; //We don't want to do anymore checks if we already have a pacman.

        }
        if (!pacmanFound) {//If nobody could be assigned the role of Pacman, that means everyone got their turn and the game has ended.w
            setChanged();
            notifyObservers(GameState.ENDED);
        }
        int ghostIndex = 0;
        for (Integer number : registeredPlayers){
            System.out.println("[Game.java] assigning "+number+"...");
            if(number == currentPacman){
                playerCharacterMap.put(number,pacman);
            }
            else{//so the player is a ghost
                playerCharacterMap.put(number,ghosts.get(ghostIndex));
                ghostIndex++;
            }
        }
        System.out.println(playerCharacterMap.toString());
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
                    case GHOST:
                        Ghost ghost = new Ghost(i, j, false, false);
                        ghosts.add(ghost);
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
                    case FRUIT:
                        int value = new Random().nextInt(FruitType.values().length);
                        FruitType type = FruitType.values()[value];
                        Fruit fruit = new Fruit(i, j, type);
                        fruit.addObserver(this);
                        items.add(fruit);
                        break;
                    case EMPTY:
                        break;
                    default:
                        System.out.println("[Game.java] Tile type " + map[i][j] + " was not recognized.");
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
                    if (g.collidesWith(character)) {
                        g.doCollision(character);
                        System.out.println("[Game.java] a pacman/ ghost collision was detected.");
                        setChanged();
                        notifyObservers(GameState.PACMANDIED);
                    }
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

    public void updateGame() {
        //TODO set collision detection in this shit.

    }

    private boolean characterCanMove(Character character, MoveDirection direction, int amount) {
        int x = character.getPosX();
        int y = character.getPosY();
        for (Wall w : walls) {
            if (!w.canMoveTo(x, y, amount, direction)) return false;
        }
        return true;
    }

    private void cleanUp() {
        Iterator i = items.iterator();
        while (i.hasNext()) {
            Item item = (Item) i.next();
            if (item.isEaten()) i.remove();
        }
        if (items.size() == 0) {
            System.out.println("[Game.java] game has ended");
            setChanged();
            notifyObservers(GameState.ALLITEMSEATEN);
        }
    }

    private Character characterFromPlayerNr(int playerNr) {
        return playerCharacterMap.get(playerNr);
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
            tiles[g.getPosX()][g.getPosY()] = GHOST;//TODO ghost tile;
        }
        //adding items
        for (Item i : items) {
            TileType type = EMPTY;
            if (i instanceof Pallet) {
                if (((Pallet) i).isSuper()) type = SUPERPALLET;
                else type = PALLET;
            } else if (i instanceof Fruit) {
                type = FRUIT;
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

    public boolean registerPlayer(int playerNr){
        if(registeredPlayers.contains(playerNr)) return false;
        registeredPlayers.add(playerNr);
        previousPacmen.put(playerNr,false);
        return true;
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof Item) {
            System.out.println(o.toString() + " ate an item.");
            ((Item) observable).setEaten(true);
        }
    }
}
