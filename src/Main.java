import java.io.*;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Scanner;
import kotlin.Pair;

public class Main {
    public static void main(String[] args) {

        //Load Movie Database (MovieDB is an Arraylist of Movie Objects)
        Menu.print(MovieDB.ReadDB().toString());
        Menu.menuLog();

    }
}