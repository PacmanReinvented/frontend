package Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MapReaderWriter {

    //public static TileType[][] getMapFromFile(String filename) throws IOException {
    public static void getMapFromFile(String filename) throws IOException {
        File f=new File(filename);     //Creation of File Descriptor for input file
        FileReader fr=new FileReader(f);   //Creation of File Reader object
        BufferedReader br=new BufferedReader(fr);  //Creation of BufferedReader object
        int c = 0;
        while((c = br.read()) != -1)         //Read char by Char
        {
            char character = (char) c;          //converting integer to char
            System.out.print(character);        //Display the Character
        }


    }




}
