package Logic;

import Enums.TileType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MapReaderWriter {

    public static TileType[][] getMapFromFile(String filename) throws IOException {
        File f=new File(filename);     //Creation of File Descriptor for input file
        FileReader fr=new FileReader(f);   //Creation of File Reader object
        BufferedReader br=new BufferedReader(fr);  //Creation of BufferedReader object
        int c = 0;
        System.out.println("TODO: Make sure that the tile grid size is dynamically, rather than hardcoded.");
        TileType[][] tileTypes = new TileType[16][16];

        int x = 0;
        int y = 0;
        while((c = br.read()) != -1)         //Read char by Char
        {

            char character = (char) c;
            System.out.print(character);
            if (String.valueOf(character).matches(".")){ //testing it like this, will make sure all types of NewLine are detected
                tileTypes[x][y] = charToTileType(character);
                x++;
            }
            else{
                System.out.println("Line: "+y);
                y++;
                x=0;
            }

        }

        return tileTypes;

    }

    private static TileType charToTileType(char character){
        TileType tileType;
        switch (character){
            case 'X': tileType = TileType.WALL;
            case 'O': tileType = TileType.EMPTY;
            case 'P': tileType = TileType.PACMAN;
            case 'I': tileType = TileType.PALLET;
            case 'S': tileType = TileType.SUPERPALLET;
            case 'F': tileType = TileType.EMPTY;//TODO add FRUIT TileType
            default: tileType = TileType.EMPTY;
        }

        return tileType;
    }




}
