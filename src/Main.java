import java.io.*;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Scanner;
import kotlin.Pair;

public class Main {
    public static void main(String[] args) {

        //Load Movie Database (MovieDB is an Arraylist of Movie Objects)
        Menu.menuLog();
        ArrayList<Movie> movDB = new ArrayList<>();
        System.out.println(MovieDB.ReadDB().get(0).toString());
        //MovieDB.deleteMovieFromDB();//deletes movie from DB asking for movie ID
        MovieDB.CreateMovie();
        MovieDB.UpdateMovie();
        FindMovie.Find();
    }
}