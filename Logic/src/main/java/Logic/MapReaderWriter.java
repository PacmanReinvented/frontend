package Logic;

import Enums.TileType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MapReaderWriter {

    public static TileType[][] getMapFromFile(String filename) throws IOException {

        BufferedReader csvReader = new BufferedReader(new FileReader(filename));

        int rows = (int) csvReader.lines().count(); // aantal rows
        int columns = (int) csvReader.readLine().chars().count(); // aantal collomen
        columns += 1;
        columns = columns / 2;

        TileType[][] grid = new TileType[rows][columns];
        int rowCounter = 0;

        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            for (int i = 0; i < data.length; i++) {
                grid[rowCounter][i] = charToTileType(data[i]);
            }
            rowCounter++;
        }
        csvReader.close();

        return grid;
    }

    private static TileType charToTileType(String character){
        TileType tileType;
        switch (character){
            case "X": tileType = TileType.WALL;
            case "O": tileType = TileType.EMPTY;
            case "P": tileType = TileType.PACMAN;
            case "I": tileType = TileType.PALLET;
            case "S": tileType = TileType.SUPERPALLET;
            case "F": tileType = TileType.EMPTY;//TODO add FRUIT TileType
            default: tileType = TileType.EMPTY;
        }
        return tileType;
    }




}
