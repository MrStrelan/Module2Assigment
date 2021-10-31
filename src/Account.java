import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
public class Account implements Serializable {
    boolean menuState;
    int userID;
    private String login;
    private String password;
    private ArrayList<Integer> favMovies = new ArrayList<Integer>();
    private ArrayList<Integer> seenMovies = new ArrayList<Integer>();
    private ArrayList<Integer> seenDate = new ArrayList<Integer>();
    private ArrayList<Integer> seenTimes = new ArrayList<>();

    private static FileManager dataBase = new FileManager();

    public boolean getMenuState() {
        return menuState;
    }
    public void setMenuState(boolean menuState) {
        this.menuState = menuState;
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    public void addFavorite(int num) {
        favMovies.add(num);
    }
    public void addSeen(int num) {
        seenMovies.add(num);
    }
    public void addDate(int num) {
        seenDate.add(num);
    }
    public void addTimes(int num) {
        seenTimes.add(num);
    }

    public ArrayList<Integer> getFavMovies() {
        return favMovies;
    }
    public ArrayList<Integer> getTimes() {
        return seenTimes;
    }
    public ArrayList<Integer> getDates() {
        return seenDate;
    }
    public ArrayList<Integer> getSeenMovies() {
        return seenMovies;
    }

    public int getFavorite(int index) {
        return favMovies.get(index);
    }
    public int getSeenMovie(int index) {
        return seenMovies.get(index);
    }
    public int getTimes(int num) {
        return seenTimes.get(num);
    }

    //Complex functions
    public static Account logIn() {
        Account storingValuables = new Account();
        System.out.println("Enter username");
        Scanner usernameScan = new Scanner(System.in);
        String usernameIn = usernameScan.nextLine();
        ArrayList<Account> users = new ArrayList<Account>();
        users = dataBase.seeUsers();
        boolean repeats = false;
        int index;
        for (int i = 0; i < users.size(); i++) {
            if (usernameIn.equals(users.get(i).getLogin())) {
                index = i;
                System.out.println("Enter password");
                Scanner passwordScan = new Scanner(System.in);
                String passwordIn = passwordScan.nextLine();
                if (passwordIn.equals(users.get(index).getPassword())) {
                    storingValuables.setMenuState(false);
                    storingValuables.setUserID(index);
                    return storingValuables;
                } else {
                    System.out.println("Password is wrong");
                    storingValuables.setMenuState(false);
                    return storingValuables;
                }
            } else {
                System.out.println("This username doesn't exist\nTry again or create new account");
                storingValuables.setMenuState(false);
                return storingValuables;
            }
        }
        System.out.println("No users are created");
        storingValuables.setMenuState(false);
        return storingValuables;
    }

    public static Account creatingUser() {
        Account storingValuables = new Account();
        ArrayList<Account> users = new ArrayList<Account>();
        users = dataBase.seeUsers();
        Account user = new Account();
        boolean creatingUsername = true;
        while (creatingUsername == true) {
            System.out.println("Create username");
            Scanner usernameScan = new Scanner(System.in);
            String usernameIn = usernameScan.nextLine();
            boolean repeats = false;
            for (int i = 0; i < users.size(); i++) {
                if (usernameIn.equals(users.get(i).getLogin())) {
                    repeats = true;
                    System.out.println("This username is already taken, try another one");
                }
            }
            if (repeats == false) {
                user.setLogin(usernameIn);
                System.out.println("Username created");
                creatingUsername = false;
            }
        }
        System.out.println("Now please choose password");
        Scanner passwordScan = new Scanner(System.in);
        String password = passwordScan.nextLine();
        user.setPassword(password);
        dataBase.addUser(user);
        storingValuables.setUserID(users.size());
        storingValuables.setMenuState(false);
        return storingValuables;
    }

    public static void addFavorite(int ID, int inSystem) {
        dataBase.seeUsers().get(inSystem).addFavorite(ID);
    }

    public static void deleteFavorite(int ID, int inSystem) {
        dataBase.seeUsers().get(inSystem).getFavMovies().remove(ID);
    }
    //Id is movie Id
    public static void addSeen(int ID, int inSystem, int date) {
        dataBase.seeUsers().get(inSystem).addSeen(ID);
        dataBase.seeUsers().get(inSystem).addDate(date);
        int times = dataBase.seeUsers().get(inSystem).getTimes(inSystem);
        if (times >= 1) {
            times++;
        } else {
            times = 1;
        }
        dataBase.seeUsers().get(inSystem).addTimes(times);
    }

    public static ArrayList<Integer> seenMovieList(int inSystem) {
    return dataBase.seeUsers().get(inSystem).getSeenMovies();
    }
    public static ArrayList<Integer> seenMovieTimes(int inSystem) {
        return dataBase.seeUsers().get(inSystem).getTimes();
    }
    public static ArrayList<Integer> seenMoviedate(int inSystem) {
        return dataBase.seeUsers().get(inSystem).getDates();
    }
    public static ArrayList<Integer> seeFavorite(int inSystem) {
        return dataBase.seeUsers().get(inSystem).getFavMovies();
    }
    public static void watchMovie(int movieID, int inSystem)
    {
        Menu.flush();
        FindMovie.SearchByID(movieID);
        System.out.println("");
        System.out.println("""
                    =====================================================================================================
                                
                       _____                       __  __            _        _____        _        _                   
                      / ____|                     |  \\/  |          (_)      |  __ \\      | |      | |                  
                     | |     _ __ __ _ _____   _  | \\  / | _____   ___  ___  | |  | | __ _| |_ __ _| |__   __ _ ___  ___
                     | |    | '__/ _` |_  / | | | | |\\/| |/ _ \\ \\ / / |/ _ \\ | |  | |/ _` | __/ _` | '_ \\ / _` / __|/ _ \\
                     | |____| | | (_| |/ /| |_| | | |  | | (_) \\ V /| |  __/ | |__| | (_| | || (_| | |_) | (_| \\__ \\  __/
                      \\_____|_|  \\__,_/___|\\__, | |_|  |_|\\___/ \\_/ |_|\\___| |_____/ \\__,_|\\__\\__,_|_.__/ \\__,_|___/\\___|
                                            __/ |                                                                       
                                           |___/                                                                        
                        
                                                   Would u like to add this movie to favorites?
                                                                        
                                                         1.Yes
                                                                        
                                                         2.No
                                                         
                                                         
                                                         
                                                         
                                                         
                                
                    =====================================================================================================
                    """);

        Scanner menuScan = new Scanner(System.in);
        int menuIn = menuScan.nextInt();
        if(menuIn==1)
        {
            dataBase.seeUsers().get(inSystem).addFavorite(movieID);
        }
        addSeen(movieID,inSystem,5);

    }

}


