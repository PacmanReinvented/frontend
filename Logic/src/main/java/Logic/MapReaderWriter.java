package Logic;

import Enums.TileType;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MapReaderWriter {

    public static TileType[][] getMapFromFile(String filename) throws IOException {

        File file= new File(filename);
        int columnCount = 0;

        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(",");
                columnCount = values.length;
                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        TileType[][] grid = new TileType[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = new TileType[columnCount];
        }

        int lineNo = 0;
        for(List<String> line: lines) {
            int columnNo = 0;
            for (String value: line) {
                grid[lineNo][columnNo] = charToTileType(value);
                columnNo++;
            }
            lineNo++;
        }
        return grid;
    }

    private static TileType charToTileType(String character){
        TileType tileType;
        switch (character){
            case "x": tileType = TileType.WALL; break;
            case "0": tileType = TileType.EMPTY;break;
            case "p": tileType = TileType.PACMAN;break;
            case "i": tileType = TileType.PALLET;break;
            case "s": tileType = TileType.SUPERPALLET;break;
            case "f": tileType = TileType.EMPTY;break;//TODO add FRUIT TileType
            default: tileType = TileType.EMPTY;break;
        }
        return tileType;
    }




}
