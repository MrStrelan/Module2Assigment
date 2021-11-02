import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.Scanner;

public class FindMovie {

    //Menu interface that lets the user Find a movie in the DB
    public static boolean Find() {
        Scanner userInput = new Scanner(System.in);
        Menu.flush();
        String ask = """
                ======================================================================================================
                                          \s
                   _____                       __  __            _        _____        _        _                  \s
                  / ____|                     |  \\/  |          (_)      |  __ \\      | |_     | |                 \s
                 | |     _ __ __ _ _____   _  | \\  / | _____   ___  ___  | |  | | __ _| _/ __ _| |__   __ _ ___  ___
                 | |    | '__/ _` |_  / | | | | |\\/| |/ _ \\ \\ / / |/ _ \\ | |  | |/ _` | | / _` | '_ \\ / _` / __|/ _ \\
                 | |____| | | (_| |/ /| |_| | | |  | | (_) \\ V /| |  __/ | |__| | (_| | || (_| | |_) | (_| \\__ \\  __/
                  \\_____|_|  \\__,_/___|\\__, | |_|  |_|\\___/ \\_/ |_|\\___| |_____/ \\__,_|\\__\\__,_|_.__/ \\__,_|___/\\___|
                                        __/ |                                                                      \s
                                       |___/                                                                       \s
                                                             
                                                             
                                                             \s
                                               How do you want to find the Movie?
                                               
                                                    1.Title         2.Year  \s
                                                                 \s
                                                    3.Actor         4.ID
                                                                 \s
                                                            5.Roles        \s
                         \s
                                                            6.Back
                                                                 \s
                                                                 
                                                                 
                                                                 
                                 \s
                ======================================================================================================
                   
                """;
        System.out.println(ask);
        int answer = Menu.checkInt();
        if (answer == 1) {
            Menu.flush();
            FindMovie.SearchTitle();
            return true;
        } else if (answer == 2) {
            Menu.flush();
            Menu.print("Do you want to search a range of years?y/n (n will search Single year)");
            String asks = userInput.nextLine();
            boolean resps = false;
            while (!resps){
                if (asks.equals("y")){
                    FindMovie.SearchYearRange();
                    resps = true;}
                if (asks.equals("n")){
                    FindMovie.SearchYear();
                    resps = true;}
                else {Menu.print("Please enter y or n");}
            }
            return true;
        } else if (answer == 3) {
            Menu.flush();
            FindMovie.SearchActor();
            return true;
        } else if (answer == 4) {
            Menu.flush();
            FindMovie.SearchID();
            return true;
        } else if (answer == 5) {
            Menu.flush();
            FindMovie.SearchRoles();
            return true;
        } else if (answer == 6)
        {
            return false;
        }
        else {
            Menu.flush();
            Menu.print("Insert Valid Number:");
            return true;
        }

    }

    //Query that lets the user Search By Title
    public static void SearchTitle() {
        Scanner userInput = new Scanner(System.in);
        Menu.print("Insert Title to Search: (Case Sensitive)");
        CharSequence s = userInput.nextLine();
        Movie mn = new Movie();
        Menu.printFind();
        int nr = 0;
        for (int i = 0; i < MovieDB.ReadDB().size(); i++) {
            if (MovieDB.ReadDB().get(i).getTitle().contains(s)) {
                System.out.println(MovieDB.ReadDB().get(i).toString());
                nr++;
            }
        }
        if (nr == 0) {Menu.print("Could not find any movie, try again");}
        if (nr > 0) {Menu.spaceEnd(nr);}
        userInput.nextLine();
    }

    //Query that lets the user Search By Release Year
    public static void SearchYear() {
        Scanner userInput = new Scanner(System.in);
        Menu.print("Searching single Year");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Menu.print("Insert Year to Search:");

        int s = Menu.checkInt();
        Movie mn = new Movie();
        Menu.printFind();
        int nr = 0;
        for (int i = 0; i < MovieDB.ReadDB().size(); i++) {
            if (MovieDB.ReadDB().get(i).getDate() == s) {
                System.out.println(MovieDB.ReadDB().get(i).toString());
                nr++;
            }
        }
        if (nr == 0) {Menu.print("Could not find any movie, try again");}
        if (nr > 0) {Menu.spaceEnd(nr);}
        userInput.nextLine();
    }

    //Query that lets the user Search By a Range of Production Years
    public static void SearchYearRange() {
        Scanner userInput = new Scanner(System.in);
        Menu.print("Searching Range of Years");
        //Menu.wait1s();
        Menu.print("Insert Year to start:");
        int s = Menu.checkInt();
        Menu.print("Insert Year to end:");
        int e = Menu.checkInt();
        Movie mn = new Movie();
        Menu.printFind();
        int nr = 0;
        for (int i = 0; i < MovieDB.ReadDB().size(); i++) {
            if (MovieDB.ReadDB().get(i).getDate() >= s && MovieDB.ReadDB().get(i).getDate() <= e) {
                System.out.println(MovieDB.ReadDB().get(i).toString());
                nr++;
            }
        }
        if (nr == 0) {Menu.print("Could not find any movie, try again");Menu.wait1s();}
        if (nr > 0) {Menu.spaceEnd(nr);}
        userInput.nextLine();
    }

    //Query that lets the user Search By Actors
    public static void SearchActor() {
        Scanner userInput = new Scanner(System.in);
        Menu.print("Insert Actor to Search: (Case Sensitive)");
        CharSequence s = userInput.nextLine();
        Movie mn = new Movie();
        Menu.printFind();
        int nr = 0;
        for (int i = 0; i < MovieDB.ReadDB().size(); i++) {
            for (int j = 0; j < MovieDB.ReadDB().get(i).getActors().size(); j++) {
                if (MovieDB.ReadDB().get(i).getActors().get(j).contains(s)) {
                    System.out.println(MovieDB.ReadDB().get(i));
                    nr++;
                }
            }
        }
        if (nr == 0) {Menu.print("Could not find any movie, try again");}
        if (nr > 0) {Menu.spaceEnd(nr);}
        userInput.nextLine();

    }

    //Query that lets the user Search By ID
    public static void SearchID() {
        Scanner userInput = new Scanner(System.in);
        Menu.print("Insert ID to search:");
        int i = Menu.checkInt();
        Movie mn = new Movie();
        Menu.printFind();
        for (Movie m : MovieDB.ReadDB()
        ) {
            if (m.getID() == i) {
                System.out.println(m);
                //DisplayStatBasedOnAccount(m.getID())
            }
        }
        userInput.nextLine();
    }

    //Query that lets the user Search By Roles
    public static void SearchRoles() {
        Scanner userInput = new Scanner(System.in);
        Menu.print("Insert Role to Search: (Case Sensitive)");
        CharSequence s = userInput.nextLine();
        Movie mn = new Movie();
        Menu.printFind();
        int nr = 0;
        for (int i = 0; i < MovieDB.ReadDB().size(); i++) {
            for (int j = 0; j < MovieDB.ReadDB().get(i).getRoles().size(); j++) {
                if (MovieDB.ReadDB().get(i).getRoles().get(j).contains(s)) {
                    System.out.println(MovieDB.ReadDB().get(i));
                    nr++;
                }
            }
        }
        if (nr == 0) {Menu.print("Could not find any movie, try again");}
        if (nr > 0) {Menu.spaceEnd(nr);}
        userInput.nextLine();

    }

    //Query that lets the system display  Search By
    public static void SearchByID(int i) {
        Scanner userInput = new Scanner(System.in);
        Movie mn = new Movie();

        for (Movie m : MovieDB.ReadDB()
        ) {
            if (m.getID() == i) {
                System.out.println(m);
                //DisplayStatBasedOnAccount(m.getID())
            }
        }
    }



    //Query that lets the user Search By
    public static void knowID() {
        Scanner userInput = new Scanner(System.in);
        boolean knowId = false;
        while (!knowId) {
            Menu.print("Do you know the ID of the movie?: y/n");
            String respo = userInput.nextLine();
            if (respo.equals("y")) {
                knowId = true;
            } else if (respo.equals("n")) {
                Menu.print("Let's find that pesky ID");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Menu.flush();
                FindMovie.Find();
                knowId = true;
            } else {Menu.print("try again");}
        }
    }
}



