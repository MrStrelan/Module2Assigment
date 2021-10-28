import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.Scanner;

public class FindMovie {
    public static void Find() {
        Scanner userInput = new Scanner(System.in);
        String ask = """
                            How do you want to find the Movie?
                            1.Title
                            2.Year
                            3.Actor
                            4.ID
                            5.Roles
                """;
        System.out.println(ask);
        int answer = userInput.nextInt();
        if (answer == 1) {
            FindMovie.SearchTitle();
        } else if (answer == 2) {
            FindMovie.SearchYear();
        } else if (answer == 3) {
            FindMovie.SearchActor();
        } else if (answer == 4) {
            FindMovie.SearchID();
        } else if (answer == 5) {
            FindMovie.SearchRoles();
        } else if (answer > 5 || userInput.nextInt() < 1) {
            System.out.println("Insert Valid Number:");

        }

    }


    public static void SearchTitle() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Insert Title to Search:");
        String s = userInput.nextLine();
        Movie mn = new Movie();
        for (Movie m : MovieDB.ReadDB()
        ) {
            if (m.getTitle().equals(s)) {
                System.out.println(m);
            }
        }
    }

    public static void SearchYear() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Insert Year to Search:");
        int s = userInput.nextInt();
        userInput.nextLine();
        Movie mn = new Movie();
        for (Movie m : MovieDB.ReadDB()
        ) {
            if (m.getDate() == s) {
                System.out.println(m);
            }
        }
    }

    public static void SearchActor() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Insert Actor to Search:");
        String s = userInput.nextLine();
        Movie mn = new Movie();
        for (Movie m : MovieDB.ReadDB()
        ) {
            for (int i = 0; i < m.getActors().size(); i++) {
                if (m.getActors().get(i).equals(s)) {
                    System.out.println(m);
                }

            }

        }
    }

    public static void SearchID() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Insert ID to search:");
        int i = userInput.nextInt();
        userInput.nextLine();
        Movie mn = new Movie();
        for (Movie m : MovieDB.ReadDB()
        ) {
            if (m.getID() == i) {
                System.out.println(m);
            }
        }
    }

    public static void SearchRoles() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Insert Role to Search:");
        String s = userInput.nextLine();
        Movie mn = new Movie();
        for (Movie m : MovieDB.ReadDB()
        ) {
            for (int i = 0; i < m.getRoles().size(); i++) {
                if (m.getRoles().get(i).equals(s)) {
                    System.out.println(m);
                }

            }

        }
    }
}



