import kotlin.Pair;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    int AccountID = 0;

    //Prints the first Menu
    public static void menuLog() {
        boolean creatingAccount = true;
        Scanner inputSc = new Scanner(System.in);
        Pair<Boolean, Integer> logInfo;
        int inSystem;
        while (!creatingAccount) {
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
                //Account.CreateAccount()
                logInfo = Account.logIn();
                creatingAccount = logInfo.getFirst();
                inSystem = logInfo.getSecond();
                System.out.println("Logged in successfully");
                Menu.flush();
                Menu.menuActions();
            } else if (input == 2) {
                logInfo = Account.creatingUser();
                creatingAccount = logInfo.getFirst();
                inSystem = logInfo.getSecond();
                System.out.println("Account created");
                Menu.flush();
                Menu.menuActions();
            } else if (input == 3) {
                creatingAccount = true;
            } else {
                Menu.flush();
                Menu.print("This option is not on menu, try again");

            }
        }
    }
    //Prints the Admin Menu
    public static void menuActions() {
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

    //Flushes the Console to give a Clean
    public static void flush(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //Prints a single string in the menu way
    public static void print(String str){
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
        System.out.println("                          "+str);
        System.out.println(menuBottom);

    }

    public static void printFind(){
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

    public static int thisint(){
        return 5;
    }
}
