import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {

        //Load Movie Database (MovieDB is an Arraylist of Movie Objects)

        ArrayList<Movie> movDB = new ArrayList<>();
        System.out.println(MovieDB.ReadDB().get(0).toString());
        //MovieDB.deleteMovieFromDB();//deletes movie from DB asking for movie ID
        MovieDB.CreateMovie();
        MovieDB.UpdateMovie();
        FindMovie.Find();












            /*FileManager manag = new FileManager();
            Account test = new Account();
            boolean creatingAccount = false;
            while (creatingAccount == false) {
                System.out.println("1. Log in");
                System.out.println("2. Create account");
                Scanner inputSc = new Scanner(System.in);
                int input;
                input = inputSc.nextInt();
                if (input == 1) {
                    creatingAccount = true;
                    boolean creatingUsername = false;
                    while(creatingUsername == false) {
                        System.out.println("Enter username");
                        String enteredUsername = inputSc.nextLine();
                            while (manag.manage().reads.hasNextLine()) {
                                if (enteredUsername == manag.reads.nextLine()) {
                                    System.out.println("This username is already taken, try another one");
                                } else {
                                    creatingUsername = true;
                                    manag.add(enteredUsername);
                                }
                            }

                    }

                } else if (input == 2) {
                    creatingAccount = true;
                    System.out.println("Create username");

                } else {
                    System.out.println("This option is not on menu, try again");
                }
            }
        */
    }
}
