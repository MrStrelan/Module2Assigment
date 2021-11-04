import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;

public class Menu {
    static int inSystem;

    //Prints the first Menu
    public static void menuLog() {
        boolean creatingAccount = false;
        while (creatingAccount == false) {
            String menuLogin = """
                                                   What would you like to do today?
                                                                    
                                                             1.Login
                                                                       
                                                             2.Create Account
                                
                                                             3.Exit
                    """;
            Menu.flush();
            Menu.printMenu(7, menuLogin);
            int input;
            input = checkInt();
            if (input == 1) {
                Account storingValues;
                storingValues = Account.logIn();
                inSystem = storingValues.getUserID();
                creatingAccount = storingValues.getMenuState();
                if (creatingAccount == true) {
                    Menu.print("Logged in successfully");
                    Menu.flush();
                    Menu.menuUser(inSystem);
                    creatingAccount = false;
                }
            } else if (input == 2) {
                Account storingValues;
                storingValues = Account.creatingUser();
                inSystem = storingValues.getUserID();
                creatingAccount = storingValues.getMenuState();
                if (creatingAccount == true) {
                    Menu.print("Account created");
                    Menu.flush();
                    Menu.menuUser(inSystem);
                    creatingAccount = false;
                }
            } else if (input == 3) {
                creatingAccount = true;
            } else if (input == 4) {
                Menu.print("Insert Numeric Password for Admin Area");
                if (checkInt() == 123){Menu.menuAdmin();}
                else {Menu.print("Wrong admin password, try again");}
            } else {
                Menu.flush();
                Menu.print("This option is not on menu, try again");

            }
        }
    }

    //Prints the Admin Menu
    public static void menuAdmin() {
        Scanner inputSc = new Scanner(System.in);
        boolean useMenu = false;
        while (!useMenu) {
            String menuActions = """
                                                    - Welcome to the Admin Section -
                                                                                                                                                              \s
                                                        1.Search         2.Create  \s
                                                                     \s
                                                        3.Update         4.Delete
                                                                     \s
                                                        5.Copy           6.Show entire Database
                                                                  
                                                                  7.Back
                    """;
            Menu.flush();
            Menu.printMenu(10, menuActions);
            int input;
            input = checkInt();
            if (input == 1) {
                Menu.flush();
                FindMovie.Find();
            } else if (input == 2) {
                Menu.flush();
                MovieDB.CreateMovie();
            } else if (input == 3) {
                Menu.flush();
                MovieDB.UpdateMovie();
            } else if (input == 4) {
                Menu.flush();
                MovieDB.deleteMovieFromDB();
            } else if (input == 7) {
                Menu.flush();
                useMenu = true;
            } else if (input == 5) {
                Menu.flush();
                MovieDB.CopyMovie();
            } else if (input == 6) {
                Menu.flush();

                Menu.printMenu(22, MovieDB.ReadDB().toString());
                inputSc.nextLine();
                MovieDB.CopyMovie();
            } else {
                Menu.print("This option is not on menu, try again");
            }
        }
    }

    //Prints the User Menu
    public static void menuUser(int userID) {
        Scanner userInput = new Scanner(System.in);
        Account currentUser = new Account();
        boolean useMenu = false;
        while (useMenu == false) {
            String menuActions = """
                                                   What would you like to do today?
                                                                        
                                                         1.Search Movie
                                                                        
                                                         2.See Favorites
                                
                                                         3.See Statistics
                                
                                                         4.Log out
                    """;
            Menu.flush();
            //Menu.print("Welcome!");
            Menu.printMenu(9,menuActions);
            int inputUsMenu = checkInt();
            if (inputUsMenu == 1) {
                Menu.flush();
                boolean foundMovies = false;
                foundMovies = FindMovie.Find();
                if (foundMovies == true) {
                    Menu.AddToFavSeen(userID);
                }
            } else if (inputUsMenu == 2) {// See Favourites
                Menu.flush();
                Menu.printFind();
                for (int i = 0; i < currentUser.seeFavorite(userID).size(); i++) {
                    FindMovie.SearchByID(currentUser.seeFavorite(userID).get(i));
                }
                Menu.spaceEnd(currentUser.seeFavorite(userID).size(),4);
                userInput.nextLine();
            } else if (inputUsMenu == 3) {
                Menu.flush();
                Menu.printFind();
                for (int i = 0; i <currentUser.seenMovieList(userID).size(); i++) {
                    FindMovie.SearchByID(currentUser.seenMovieList(userID).get(i));
                    System.out.println("First seen:"+currentUser.seenMoviedate(userID).get(i)+" Times seen:"+currentUser.seenMovieTimes(userID).get(i));
                }
                Menu.spaceEnd(currentUser.seenMovieList(userID).size(),4);
                userInput.nextLine();
            } else if (inputUsMenu == 4) {
                Menu.flush();
                useMenu = true;
            } else {
                Menu.print("This option is not on menu, try again");
            }
        }
    }

    //Flushes the Console to give a Clean
    public static void flush() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //Prints a single string in the menu way
    public static void print(String str) {
        Menu.flush();
        Menu.printMenu(0, "                                 " + str);
        Menu.wait1s(1000);

    }

    //printfind prints a menu top befor a find
    public static void printFind() {
        String menuTop = """
                ======================================================================================================
                                                
                   _____                       __  __            _        _____        _        _                   
                  / ____|                     |  \\/  |          (_)      |  __ \\      | |      | |                  
                 | |     _ __ __ _ _____   _  | \\  / | _____   ___  ___  | |  | | __ _| |_ __ _| |__   __ _ ___  ___
                 | |    | '__/ _` |_  / | | | | |\\/| |/ _ \\ \\ / / |/ _ \\ | |  | |/ _` | __/ _` | '_ \\ / _` / __|/ _ \\
                 | |____| | | (_| |/ /| |_| | | |  | | (_) \\ V /| |  __/ | |__| | (_| | || (_| | |_) | (_| \\__ \\  __/
                  \\_____|_|  \\__,_/___|\\__, | |_|  |_|\\___/ \\_/ |_|\\___| |_____/ \\__,_|\\__\\__,_|_.__/ \\__,_|___/\\___|
                                        __/ |                                                                       
                                       |___/                                                                        
                                              """;
        Menu.flush();
        System.out.println(menuTop);

    }

    //Prints a menu from a String and the string amount of rows
    public static void printMenu(int rows, String toPrint) {
        String menuTop = """
                ======================================================================================================
                                                
                   _____                       __  __            _        _____        _        _                   
                  / ____|                     |  \\/  |          (_)      |  __ \\      | |      | |                  
                 | |     _ __ __ _ _____   _  | \\  / | _____   ___  ___  | |  | | __ _| |_ __ _| |__   __ _ ___  ___
                 | |    | '__/ _` |_  / | | | | |\\/| |/ _ \\ \\ / / |/ _ \\ | |  | |/ _` | __/ _` | '_ \\ / _` / __|/ _ \\
                 | |____| | | (_| |/ /| |_| | | |  | | (_) \\ V /| |  __/ | |__| | (_| | || (_| | |_) | (_| \\__ \\  __/
                  \\_____|_|  \\__,_/___|\\__, | |_|  |_|\\___/ \\_/ |_|\\___| |_____/ \\__,_|\\__\\__,_|_.__/ \\__,_|___/\\___|
                                        __/ |                                                                       
                                       |___/                                                                        
                                              
                                              
                                              
                                              """;
        String blank = """
                                
                """;
        String endline = """
                ======================================================================================================
                """;
        int blanklines;
        blanklines = 15 - (rows+ 1);
        Menu.flush();
        System.out.println(menuTop);
        System.out.println(toPrint);
        if (blanklines>=0){System.out.println(blank.repeat(blanklines));}
        System.out.println(endline);

    }

    //Checks if the user inserted a proper int
    public static int checkInt() {
        int check = 0;
        Scanner userInput = new Scanner(System.in);
        boolean watchint = false;
        while (!watchint) {
            try {
                check = userInput.nextInt();
                watchint = true;
            } catch (InputMismatchException e) {
                Menu.print("Please insert a number");
                userInput.nextLine();
            }
        }
        return check;
    }

    //Query that lets the user Search By
    public static void AddToFavSeen(int userID) {
        boolean run = true;
        while (run) {
            Scanner userInput = new Scanner(System.in);
            String favSeen= """
                                     Do you want to add a movie to your Favorites/Seen Movies?"
                                     
                                           1. Watch one of movies in the list above
                                           
                                           2. Add to Favorites one of movies above
                                           
                                           3. Add to Seen one of the movies above
                                           
                                           4. Back to the Menu
                            """;
            Menu.printMenu(10,favSeen);
            int resp = Menu.checkInt();
            if (resp == 1) {//Watch a movie
                Menu.print("Insert ID of the movie you want to add to Watched:");
                int MovID = Menu.checkInt();
                Account.watchMovie(MovID, userID);
                Menu.print(MovieDB.ReadDB().get(MovieDB.movieDBindex(MovID)).getTitle() + " added to Watched");
                run = false;
            }

            if (resp == 2) {//Add to Fav
                Menu.print("Insert ID of the movie you want to add to Favourites:");
                int MovID = Menu.checkInt();
                Account.addFavorite(MovID, userID);
                //Account.addSeen(MovID,userID,);
                Menu.print(MovieDB.ReadDB().get(MovieDB.movieDBindex(MovID)).getTitle() + " added to Favourites");
                run = false;
            }
            if (resp == 3) {//Add to Seen
                Menu.print("Insert ID of the movie you want to add to Seen:");
                int MovID = Menu.checkInt();
                Menu.print("Insert year you have seen it");
                int year = Menu.checkInt();
                Menu.print("Insert month you have seen it");
                int month = Menu.checkInt();
                Menu.print("Insert day you have seen it");
                int day = Menu.checkInt();
                LocalDate firstSeen = LocalDate.of(year, month, day);
                Account.addSeen(MovID, userID, firstSeen);
                Menu.print(MovieDB.ReadDB().get(MovieDB.movieDBindex(MovID)).getTitle() + " added to Seen");
                Menu.flush();
                run = false;

            }
            if (resp == 4) {//Back to menu
                run = false;
            } else {
                Menu.print("Insert proper answer");
            }
        }

    }

    //Prints a blank space as many times as needed and a end line
    public static void spaceEnd(int nr,int lines) {

        String blank = """
                                
                """;
        String endline = """
                ======================================================================================================
                """;
        int blanklines =15 -((nr*lines)+1);
        if (blanklines >= 0) {System.out.println(blank.repeat(blanklines));}
        System.out.println(endline);
    }

    //makes 1 second wait
    public static void wait1s(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
