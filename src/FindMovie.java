import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.Scanner;

public class FindMovie {

    public static void Find() {
        Scanner userInput = new Scanner(System.in);
        String ask = """
                =====================================================================================================
                                
                   _____                       __  __            _        _____        _        _                   \s
                  / ____|                     |  \\/  |          (_)      |  __ \\      | |      | |                  \s
                  | |     _ __ __ _ _____   _  | \\  / | _____   ___  ___  | |  | | __ _| |_ __ _| |__   __ _ ___  ___\s
                  | |    | '__/ _` |_  / | | | | |\\/| |/ _ \\ \\ / / |/ _ \\ | |  | |/ _` | __/ _` | '_ \\ / _` / __|/ _ \\
                  | |____| | | (_| |/ /| |_| | | |  | | (_) \\ V /| |  __/ | |__| | (_| | || (_| | |_) | (_| \\__ \\  __/
                 \\_____|_|  \\__,_/___|\\__, | |_|  |_|\\___/ \\_/ |_|\\___| |_____/ \\__,_|\\__\\__,_|_.__/ \\__,_|___/\\___|
                                         __/ |                                                                       \s
                                        |___/                                                                        \s
                                              How do you want to find the Movie?
                                                                                                                                           
                                                  1.Title
                            
                                                  2.Year
                            
                                                  3.Actor
                            
                                                  4.ID
                            
                                                  5.Roles
                                
                                                                                                                                                                                
                
                
                
                
                                                             
                =====================================================================================================
                          
                """;
        System.out.println(ask);
        int answer = Menu.checkInt();
        if (answer == 1) {
            Menu.flush();
            FindMovie.SearchTitle();
        } else if (answer == 2) {
            Menu.flush();
            FindMovie.SearchYear();
        } else if (answer == 3) {
            Menu.flush();
            FindMovie.SearchActor();
        } else if (answer == 4) {
            Menu.flush();
            FindMovie.SearchID();
        } else if (answer == 5) {
            Menu.flush();
            FindMovie.SearchRoles();
        } else  {
            Menu.flush();
            Menu.print("Insert Valid Number:");

        }

    }

    public static void SearchTitle() {
        Scanner userInput = new Scanner(System.in);
        int nr = 0;
        Menu.print("Insert Title to Search: (Case Sensitive)");
        CharSequence s = userInput.nextLine();
        Movie mn = new Movie();
        Menu.printFind();
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

    public static void SearchYear() {
        Scanner userInput = new Scanner(System.in);
        Menu.print("Insert Year to Search:");
        int s = Menu.checkInt();
        Movie mn = new Movie();
        Menu.printFind();
        for (Movie m : MovieDB.ReadDB()
        ) {
            if (m.getDate() == s) {
                System.out.println(m);
                //DisplayStatBasedOnAccount(m.getID())
            }
        }
        userInput.nextLine();
    }

    public static void SearchActor() {
        Scanner userInput = new Scanner(System.in);
        Menu.print("Insert Actor to Search: \n (Case Sensitive)\"");
        CharSequence s = userInput.nextLine();
        Movie mn = new Movie();
        for (Movie m : MovieDB.ReadDB()
        ) {
            for (int i = 0; i < m.getActors().size(); i++) {
                if (m.getActors().get(i).contains(s)) {
                    System.out.println(m);
                    //DisplayStatBasedOnAccount(m.getID())
                }

            }

        }
        userInput.nextLine();
    }

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

    public static void SearchByID(int i) {
        Scanner userInput = new Scanner(System.in);
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

    public static void SearchRoles() {
        Scanner userInput = new Scanner(System.in);
        Menu.print("Insert Role to Search: \\n (Case Sensitive)\"");
        CharSequence s = userInput.nextLine();
        Movie mn = new Movie();
        Menu.printFind();
        for (Movie m : MovieDB.ReadDB()
        ) {
            for (int i = 0; i < m.getRoles().size(); i++) {
                if (m.getRoles().get(i).contains(s)) {
                    System.out.println(m);
                    //DisplayStatBasedOnAccount(m.getID())
                }

            }
        }
        userInput.nextLine();
    }

    public static void knowID() {
        Scanner userInput = new Scanner(System.in);
        boolean knowId = false;
        while (!knowId) {
            System.out.println("Do you know the ID of the movie?: y/n");
            String respo = userInput.nextLine();
            if (respo.equals("y")) {
                knowId = true;
            } else if (respo.equals("n")) {
                System.out.println("Let's find that pesky ID");
                FindMovie.Find();
                knowId = true;
            } else {System.out.println("try again");}
        }
    }
}



