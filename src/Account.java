import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Account implements Serializable {
    boolean menuState;
    int userID;
    private String login;
    private String password;
    private ArrayList<Integer> favMovies = new ArrayList<Integer>();
    private ArrayList<Integer> seenMovies = new ArrayList<Integer>();
    private ArrayList<LocalDate> seenDate = new ArrayList<LocalDate>();
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

    public void addDate(LocalDate num) {
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

    public ArrayList<LocalDate> getDates() {
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
    public static ArrayList<Integer> seenMovieList(int inSystem) {
        return dataBase.seeUsers().get(inSystem).getSeenMovies();
    }

    public static ArrayList<Integer> seenMovieTimes(int inSystem) {
        return dataBase.seeUsers().get(inSystem).getTimes();
    }

    public static ArrayList<LocalDate> seenMoviedate(int inSystem) {
        return dataBase.seeUsers().get(inSystem).getDates();
    }

    public static ArrayList<Integer> seeFavorite(int inSystem) {
        return dataBase.seeUsers().get(inSystem).getFavMovies();
    }

    public static Account logIn() {
        Account storingValuables = new Account();
        ArrayList<Account> users = dataBase.seeUsers();
        /*----------------------------------------------------------------------------------------
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getLogin());
        }
        System.out.println("" +
                "" +
                "");*/
        System.out.println("Enter username");
        Scanner usernameScan = new Scanner(System.in);
        String usernameIn = usernameScan.nextLine();
        boolean repeats = false;
        int index;
        for (int i = 0; i < users.size(); i++) {
            if (usernameIn.equals(users.get(i).getLogin())) {
                index = i;
                repeats = true;
                System.out.println("Enter password");
                Scanner passwordScan = new Scanner(System.in);
                String passwordIn = passwordScan.nextLine();
                if (passwordIn.equals(users.get(index).getPassword())) {
                    storingValuables.setMenuState(true);
                    storingValuables.setUserID(index);
                    return storingValuables;
                } else {
                    System.out.println("Password is wrong");
                    storingValuables.setMenuState(false);
                    return storingValuables;
                }
            }
            if (repeats = false) {
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
        ArrayList<Account> users = dataBase.seeUsers();
        /*---------------------------------------------------------------------------------------------------------------
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getLogin());
        }
        System.out.println("" +
                "" +
                "");*/
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
        storingValuables.setMenuState(true);
        return storingValuables;
    }

    public static void addFavorite(int ID, int inSystem) {
        ArrayList<Account> saving = dataBase.seeUsers();
        saving.get(inSystem).addFavorite(ID);
        dataBase.saveChange(saving);
    }

    public static void deleteFavorite(int ID, int inSystem) {
        ArrayList<Account> saving = dataBase.seeUsers();
        saving.get(inSystem).getFavMovies().remove(ID);
        dataBase.saveChange(saving);
    }
    //ID is movie Id
    public static void addSeen(int ID, int inSystem, LocalDate date) {
        ArrayList<Account> saving = dataBase.seeUsers();
        boolean repeats = false;
        for (int i = 0; i < dataBase.seeUsers().get(inSystem).seenMovies.size(); i++) {
            if (ID == dataBase.seeUsers().get(inSystem).getSeenMovie(i)) {
                repeats=true;
                saving.get(inSystem).seenTimes.set(i,dataBase.seeUsers().get(inSystem).seenTimes.get(i));
            }
        }
        if(repeats=false)
        {
            saving.get(inSystem).addSeen(ID);
            saving.get(inSystem).addDate(date);
            saving.get(inSystem).addTimes(1);
        }
        dataBase.saveChange(saving);
    }

    public static void watchMovie(int movieID, int inSystem) {
        ArrayList<Account> saving = dataBase.seeUsers();
        Menu.flush();
        FindMovie.SearchByID(movieID);
        boolean seen = false;
        boolean seenEmpty = true;
        int index;
        for (int i = 0; i < dataBase.seeUsers().get(inSystem).seenMovies.size(); i++) {
            seenEmpty = false;
            if (movieID == dataBase.seeUsers().get(inSystem).getSeenMovie(i)) {
                seen = true;
                int times = dataBase.seeUsers().get(inSystem).seenTimes.get(i);
                times++;
                saving.get(inSystem).seenTimes.set(i, times);
            }
            if (seen == false) {
                LocalDate date = LocalDate.now();
                addSeen(movieID, inSystem, date);
            }
        }
        if(seenEmpty = true)
        {
            LocalDate date = LocalDate.now();
            addSeen(movieID, inSystem, date);
        }
        boolean favEmpty = true;
        for (int i = 0; i < dataBase.seeUsers().get(inSystem).favMovies.size(); i++) {
            favEmpty = false;
            if (movieID != dataBase.seeUsers().get(inSystem).getFavorite(i)) {
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
                int menuIn = Menu.checkInt();
                if (menuIn == 1) {
                    saving.get(inSystem).favMovies.add(movieID);
                }
            } else {
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
                            
                                                       Would u like to keep this movie in favorites?
                                                                            
                                                             1.Yes
                                                                            
                                                             2.No
                                                             
                                                             
                                                             
                                                             
                                                             
                                    
                        =====================================================================================================
                        """);
                int menuIn = Menu.checkInt();
                if (menuIn == 2) {
                    deleteFavorite(movieID, inSystem);
                }
            }

        }
        if(favEmpty==true)
        {
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
            int menuIn = Menu.checkInt();
            if (menuIn == 1) {
                saving.get(inSystem).favMovies.add(movieID);
            }
        }
        dataBase.saveChange(saving);
        System.out.println(dataBase.seeUsers().get(inSystem).favMovies);
    }
}



