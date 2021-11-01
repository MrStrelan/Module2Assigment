import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;

public class Menu {
    static int inSystem;

    //Prints the first Menu
    public static void menuLog() {
        boolean creatingAccount = false;
        while (creatingAccount==false) {
            String menuLogin = """
                    =====================================================================================================
                                
                        _____                       __  __            _        _____        _        _                   \s
                       / ____|                     |  \\/  |          (_)      |  __ \\      | |      | |                  \s
                      | |     _ __ __ _ _____   _  | \\  / | _____   ___  ___  | |  | | __ _| |_ __ _| |__   __ _ ___  ___\s
                      | |    | '__/ _` |_  / | | | | |\\/| |/ _ \\ \\ / / |/ _ \\ | |  | |/ _` | __/ _` | '_ \\ / _` / __|/ _ \\
                      | |____| | | (_| |/ /| |_| | | |  | | (_) \\ V /| |  __/ | |__| | (_| | || (_| | |_) | (_| \\__ \\  __/
                       \\_____|_|  \\__,_/___|\\__, | |_|  |_|\\___/ \\_/ |_|\\___| |_____/ \\__,_|\\__\\__,_|_.__/ \\__,_|___/\\___|
                                             __/ |                                                                       \s
                                            |___/                                                                        \s
                                                  What would you like to do today?
                                                                    
                                                        1.Login
                                                                       
                                                        2.Create Account
                                
                                                        3.Exit
                                
                                                                         
                                
                                                                         
                                
                                
                                
                    =====================================================================================================
                    """;
            System.out.println(menuLogin);
            int input;
            input = checkInt();
            if (input == 1) {
                Account storingValues;
                storingValues = Account.logIn();
                inSystem = storingValues.getUserID();
                creatingAccount = storingValues.getMenuState();
                if(creatingAccount==true)
                {
                    System.out.println("Logged in successfully");
                    Menu.flush();
                    Menu.menuUser(inSystem);
                    creatingAccount=false;
                }
            } else if (input == 2) {
                Account storingValues;
                storingValues = Account.creatingUser();
                inSystem = storingValues.getUserID();
                creatingAccount = storingValues.getMenuState();
                if(creatingAccount==true) {
                    System.out.println("Account created");
                    Menu.flush();
                    Menu.menuUser(inSystem);
                    creatingAccount=false;
                }
            } else if (input == 3) {
                creatingAccount = true;
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
                    =====================================================================================================
                                
                       _____                       __  __            _        _____        _        _                   
                      / ____|                     |  \\/  |          (_)      |  __ \\      | |      | |                  
                     | |     _ __ __ _ _____   _  | \\  / | _____   ___  ___  | |  | | __ _| |_ __ _| |__   __ _ ___  ___
                     | |    | '__/ _` |_  / | | | | |\\/| |/ _ \\ \\ / / |/ _ \\ | |  | |/ _` | __/ _` | '_ \\ / _` / __|/ _ \\
                     | |____| | | (_| |/ /| |_| | | |  | | (_) \\ V /| |  __/ | |__| | (_| | || (_| | |_) | (_| \\__ \\  __/
                      \\_____|_|  \\__,_/___|\\__, | |_|  |_|\\___/ \\_/ |_|\\___| |_____/ \\__,_|\\__\\__,_|_.__/ \\__,_|___/\\___|
                                            __/ |                                                                       
                                           |___/                                                                        
                        
                                                   What would you like to do today?
                                                                        
                                                         1.Search Movie
                                                                        
                                                         2.Create Movie
                                
                                                         3.Update Movie
                                
                                                         4.Delete Movie
                                
                                                         5.Log Out
                                
                                
                                
                    =====================================================================================================
                    """;
            System.out.println(menuActions);
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
            } else if (input == 5) {
                Menu.flush();
                useMenu = true;
            } else if (input == 6) {
                Menu.flush();
                MovieDB.CopyMovie();
            } else {
                Menu.print("This option is not on menu, try again");
            }
        }
    }

    //Prints the User Menu
    public static void menuUser(int userID) {
        Account currentUser = new Account();
        Scanner inputSc = new Scanner(System.in);
        boolean useMenu = false;
        while (useMenu==false) {
            String menuActions = """
                    =====================================================================================================
                                
                       _____                       __  __            _        _____        _        _                   
                      / ____|                     |  \\/  |          (_)      |  __ \\      | |      | |                  
                     | |     _ __ __ _ _____   _  | \\  / | _____   ___  ___  | |  | | __ _| |_ __ _| |__   __ _ ___  ___
                     | |    | '__/ _` |_  / | | | | |\\/| |/ _ \\ \\ / / |/ _ \\ | |  | |/ _` | __/ _` | '_ \\ / _` / __|/ _ \\
                     | |____| | | (_| |/ /| |_| | | |  | | (_) \\ V /| |  __/ | |__| | (_| | || (_| | |_) | (_| \\__ \\  __/
                      \\_____|_|  \\__,_/___|\\__, | |_|  |_|\\___/ \\_/ |_|\\___| |_____/ \\__,_|\\__\\__,_|_.__/ \\__,_|___/\\___|
                                            __/ |                                                                       
                                           |___/                                                                        
                        
                                                   What would you like to do today?
                                                                        
                                                         1.Search Movie
                                                                        
                                                         2.See Favorites
                                
                                                         3.See Statistics
                                
                                                         4.Log out
                                
                    =====================================================================================================
                    """;
            System.out.println(menuActions);
            int inputUsMenu = checkInt();
            if (inputUsMenu == 1) {
                Menu.flush();
                FindMovie.Find();
                Menu.AddToFavSeen(userID);
                /*int inputSeeMovie = checkInt();
                String str = "1.Watch one of movies in the list above\n" +
                             "2.Add to favorites one of movies above"+
                             "3.Back to menu";
                print(str);
                int movieID = 9822;
                if(inputSeeMovie==1)
                {
                    Account.watchMovie(movieID, inSystem);
                }else if(inputSeeMovie==2)
                {

                }else {
                    System.out.println("This option is not on menu, try again");
                }*/
            } else if (inputUsMenu == 2) {
                Menu.flush();
                for (int i = 0; i < currentUser.seeFavorite(userID).size(); i++) {
                    FindMovie.SearchByID(currentUser.seeFavorite(userID).get(i));
                    System.out.println("");
                }
            } else if (inputUsMenu == 3) {
                Menu.flush();
                for (int i = 0; i < currentUser.seenMovieList(userID).size(); i++) {
                    FindMovie.SearchByID(currentUser.seeFavorite(userID).get(i));
                    System.out.println("First seen:"+currentUser.seenMoviedate(userID).get(i)+" Times seen:"+currentUser.seenMovieTimes(userID).get(i));
                    System.out.println("");
                }
            } else if (inputUsMenu == 4) {
                Menu.flush();
                useMenu=true;
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
        String menuTop = """
                =====================================================================================================
                                                
                   _____                       __  __            _        _____        _        _                   
                  / ____|                     |  \\/  |          (_)      |  __ \\      | |      | |                  
                 | |     _ __ __ _ _____   _  | \\  / | _____   ___  ___  | |  | | __ _| |_ __ _| |__   __ _ ___  ___
                 | |    | '__/ _` |_  / | | | | |\\/| |/ _ \\ \\ / / |/ _ \\ | |  | |/ _` | __/ _` | '_ \\ / _` / __|/ _ \\
                 | |____| | | (_| |/ /| |_| | | |  | | (_) \\ V /| |  __/ | |__| | (_| | || (_| | |_) | (_| \\__ \\  __/
                  \\_____|_|  \\__,_/___|\\__, | |_|  |_|\\___/ \\_/ |_|\\___| |_____/ \\__,_|\\__\\__,_|_.__/ \\__,_|___/\\___|
                                        __/ |                                                                       
                                       |___/                                                                        
                                              """;
        String menuBottom = """
                                                                 
                                                                    
                                                                     
                            
                                                                    
                            
                                                                     
                            
                                                                     
                            
                            
                            
                =====================================================================================================
                """;
        System.out.println(menuTop);
        System.out.println("                          " + str);
        System.out.println(menuBottom);

    }

    public static void printFind() {
        String menuTop = """
                =====================================================================================================
                                                
                   _____                       __  __            _        _____        _        _                   
                  / ____|                     |  \\/  |          (_)      |  __ \\      | |      | |                  
                 | |     _ __ __ _ _____   _  | \\  / | _____   ___  ___  | |  | | __ _| |_ __ _| |__   __ _ ___  ___
                 | |    | '__/ _` |_  / | | | | |\\/| |/ _ \\ \\ / / |/ _ \\ | |  | |/ _` | __/ _` | '_ \\ / _` / __|/ _ \\
                 | |____| | | (_| |/ /| |_| | | |  | | (_) \\ V /| |  __/ | |__| | (_| | || (_| | |_) | (_| \\__ \\  __/
                  \\_____|_|  \\__,_/___|\\__, | |_|  |_|\\___/ \\_/ |_|\\___| |_____/ \\__,_|\\__\\__,_|_.__/ \\__,_|___/\\___|
                                        __/ |                                                                       
                                       |___/                                                                        
                                              """;
        System.out.println(menuTop);

    }

    public static int checkInt() {
        int check = 0;
        Scanner userInput = new Scanner(System.in);
        boolean watchint = false;
        while (!watchint) {
            try {
                check = userInput.nextInt();
                watchint = true;
            } catch (InputMismatchException e) {
                System.out.println("Please insert a number");
                userInput.nextLine();
            }
        }
        return check;
    }

    //Query that lets the user Search By
    public static void AddToFavSeen(int userID){
        boolean run=true;
        while (run){
            Scanner userInput = new Scanner(System.in);
            Menu.printFind();
            System.out.println("Do you want to add a movie to your Favorites/Seen Movies?\n" +
                    "1. Watch one of movies in the list above\\n\n" +
                    "2. Add to Favorites one of movies above\n" +
                    "3. Add to Seen onr of the movies above" +
                    "3. Back to the Menu");
            Menu.spaceEnd(1);
            int resp = Menu.checkInt();
            if (resp == 1){//Watch a movie
                Menu.print("Insert ID of the movie you want to add to Watched:");
                int MovID = Menu.checkInt();
                Account.watchMovie(MovID,userID);
                Menu.print(MovieDB.ReadDB().get(MovieDB.movieDBindex(MovID)).getTitle()+ " added to Watched");
                run=false;}

            if (resp == 2){//Add to Fav
                Menu.print("Insert ID of the movie you want to add to Favourites:");
                int MovID = Menu.checkInt();
                Account.addFavorite(MovID,userID);
                //Account.addSeen(MovID,userID,);
                Menu.print(MovieDB.ReadDB().get(MovieDB.movieDBindex(MovID)).getTitle()+ " added to Favourites");
                run=false;}
            if (resp == 3){//Add to Seen
                Menu.print("Insert ID of the movie you want to add to Seen:");
                int MovID = Menu.checkInt();
                //Menu.print("Insert ID of the movie you want to add to Seen:");

                //Account.addSeen(MovID,userID,);
                Menu.print(MovieDB.ReadDB().get(MovieDB.movieDBindex(MovID)).getTitle()+ " added to Seen");
                run=false;}
            if (resp == 4){//Back to menu
                run=false;}
            else {Menu.print("Insert proper answer");}
        }

    }

    //Prints a blank space as many times as needed and a end line
    public static void spaceEnd(int nr){

        String blank = """
                
                """;
        String endline = """
                =====================================================================================================
                """;
        if (nr >= 3){System.out.println(blank);}
        if (nr == 2){System.out.println(blank.repeat(5));}
        if (nr == 1){System.out.println(blank.repeat(11));}
        System.out.println(endline);
    }

    //makes 1 second wait
    public static void wait1s(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
