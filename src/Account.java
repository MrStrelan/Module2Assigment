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

    public void addSeenMovie(int num) {
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
    private int findIndex(int movieID,int inSystem)
    {
        for(int i=0;i<dataBase.seeUsers().get(inSystem).favMovies.size();i++)
        {
            if(movieID==dataBase.seeUsers().get(inSystem).favMovies.get(i))
            {
                return i;
            }
        }
        return 404;
    }

    //Complex functions
    public static void seeStatistics(int movieID, int inSystem)
    {
        System.out.println("Movie ID: "+dataBase.seeUsers().get(inSystem).seenMovies.get(dataBase.seeUsers().get(inSystem).findIndex(movieID,inSystem))+" Seen first: "+dataBase.seeUsers().get(inSystem).seenDate.get(dataBase.seeUsers().get(inSystem).findIndex(movieID,inSystem))+" Times seen: "+dataBase.seeUsers().get(inSystem).seenTimes.get(dataBase.seeUsers().get(inSystem).findIndex(movieID,inSystem)));
    }


    public static ArrayList<Integer> seenMovieList(int inSystem) {
        return dataBase.seeUsers().get(inSystem).seenMovies;
    }

    public static ArrayList<Integer> seenMovieTimes(int inSystem) {
        ArrayList<Integer> saving = dataBase.seeUsers().get(inSystem).seenTimes;
        return saving;
    }

    public static ArrayList<LocalDate> seenMoviedate(int inSystem) {
        ArrayList<LocalDate> saving = dataBase.seeUsers().get(inSystem).seenDate;
        return saving;
    }

    public static ArrayList<Integer> seeFavorite(int inSystem) {
        return dataBase.seeUsers().get(inSystem).favMovies;
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
        Menu.print("Enter username");
        Scanner usernameScan = new Scanner(System.in);
        String usernameIn = usernameScan.nextLine();
        boolean repeats = false;
        int index;
        for (int i = 0; i < users.size(); i++) {
            if (usernameIn.equals(users.get(i).getLogin())) {
                index = i;
                repeats = true;
                Menu.print("Enter password");
                Scanner passwordScan = new Scanner(System.in);
                String passwordIn = passwordScan.nextLine();
                if (passwordIn.equals(users.get(index).getPassword())) {
                    storingValuables.setMenuState(true);
                    storingValuables.setUserID(index);
                    return storingValuables;
                } else {
                    Menu.print("Password is wrong");
                    storingValuables.setMenuState(false);
                    return storingValuables;
                }
            }
            if (repeats = false) {
                Menu.print("This username doesn't exist - Try again or create new account");
                storingValuables.setMenuState(false);
                return storingValuables;
            }
        }
        Menu.print("No users are created");
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
            Menu.print("Create username");
            Scanner usernameScan = new Scanner(System.in);
            String usernameIn = usernameScan.nextLine();
            boolean repeats = false;
            for (int i = 0; i < users.size(); i++) {
                if (usernameIn.equals(users.get(i).getLogin())) {
                    repeats = true;
                    Menu.print("This username is already taken, try another one");
                }
            }
            if (repeats == false) {
                user.setLogin(usernameIn);
                Menu.print("Username created");
                creatingUsername = false;
            }
        }
        Menu.print("Now please choose password");
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
        saving.get(inSystem).favMovies.add(ID);
        dataBase.saveChange(saving);
    }

    public static void deleteFavorite(int ID, int inSystem) {
        ArrayList<Account> saving = dataBase.seeUsers();
        for (int i = 0; i < dataBase.seeUsers().get(inSystem).favMovies.size(); i++) {
            if (ID == dataBase.seeUsers().get(inSystem).favMovies.get(i))
                saving.get(inSystem).favMovies.remove(i);
        }
        dataBase.saveChange(saving);
    }

    //ID is movie Id
    public static void addSeen(int ID, int inSystem, LocalDate date) {
        ArrayList<Account> saving = dataBase.seeUsers();
        boolean repeats = false;
        for (int i = 0; i < dataBase.seeUsers().get(inSystem).seenMovies.size(); i++) {
            if (ID == dataBase.seeUsers().get(inSystem).getSeenMovie(i)) {
                repeats = true;
                saving.get(inSystem).seenTimes.set(i, dataBase.seeUsers().get(inSystem).seenTimes.get(i));
            }
        }
        if (repeats == false) {
            saving.get(inSystem).seenMovies.add(ID);
            saving.get(inSystem).seenDate.add(date);
            saving.get(inSystem).seenTimes.add(1);
        }
        dataBase.saveChange(saving);
        System.out.println(dataBase.seeUsers().get(inSystem).seenMovies.size());
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
            if (movieID == dataBase.seeUsers().get(inSystem).seenMovies.get(i)) {
                seen = true;
                Integer times = dataBase.seeUsers().get(inSystem).seenTimes.get(i);
                times++;
                //Error
                saving.get(inSystem).seenTimes.set(i, times);
            }
            if (seen == false) {
                LocalDate date = LocalDate.now();
                addSeen(movieID, inSystem, date);
            }
        }
        if (seenEmpty = true) {
            LocalDate date = LocalDate.now();
            addSeen(movieID, inSystem, date);
        }
        boolean favEmpty = true;
        boolean inFav = false;
        for (int i = 0; i < dataBase.seeUsers().get(inSystem).favMovies.size(); i++) {
            favEmpty = false;
            if (movieID == dataBase.seeUsers().get(inSystem).getFavorite(i)) {
                inFav = true;
            }
        }
        dataBase.saveChange(saving);

        if (inFav == false) {
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
                        
                                                   Would you like to add this movie to favorites?
                                                                        
                                                         1.Yes
                                                                        
                                                         2.No
                                                         
                                                         
                                                         
                                                         
                                                         
                                
                    =====================================================================================================
                    """);
            int menuIn = Menu.checkInt();
            if (menuIn == 1) {
                saving = dataBase.seeUsers();
                saving.get(inSystem).favMovies.add(movieID);
                dataBase.saveChange(saving);
            }
        } else {

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
                        
                                                   Would you like to keep this movie in favorites?
                                                                        
                                                         1.Yes
                                                                        
                                                         2.No
                                                         
                                                         
                                                         
                                                         
                                                         
                                
                    =====================================================================================================
                    """);
            int menuIn = Menu.checkInt();
            if (menuIn == 2) {
                deleteFavorite(movieID, inSystem);
            }
        }


        /*if (favEmpty == true) {

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
                        
                                                   Would you like to add this movie to favorites?
                                                                        
                                                         1.Yes
                                                                        
                                                         2.No
                                                         
                                                         
                                                         
                                                         
                                                         
                                
                    =====================================================================================================
                    """);
            int menuIn = Menu.checkInt();
            if (menuIn == 1) {
                saving = dataBase.seeUsers();
                saving.get(inSystem).favMovies.add(movieID);
                dataBase.saveChange(saving);

            }
        }*/
        Menu.print(dataBase.seeUsers().get(inSystem).favMovies.toString());

        //Account.seeStatistics(movieID, inSystem);
    }
}



