import kotlin.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
public class Account implements Serializable {
    private String login;
    private String password;
    private ArrayList<Integer> favMovies = new ArrayList<Integer>();
    private ArrayList<Integer> seenMovies = new ArrayList<Integer>();
    private ArrayList<Integer> seenDate = new ArrayList<Integer>();
    private ArrayList<Integer> seenTimes = new ArrayList<>();

    private static FileManager dataBase = new FileManager();

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
    public static Pair<Boolean, Integer> logIn() {
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
                    return new Pair<Boolean, Integer>(false, index);
                } else {
                    System.out.println("Password is wrong");
                    return new Pair<Boolean, Integer>(true, 404);
                }
            } else {
                System.out.println("This username doesn't exist\nTry again or create new account");
                return new Pair<Boolean, Integer>(true, 404);
            }
        }
        System.out.println("No users are created");
        return new Pair<Boolean, Integer>(true, 404);
    }

    public static Pair<Boolean, Integer> creatingUser() {
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
        return new Pair<Boolean, Integer>(false, users.size());
    }

    public static void addFavorite(int ID, int inSystem) {
        dataBase.seeUsers().get(inSystem).addFavorite(ID);
    }

    public static void deleteFavorite(int ID, int inSystem) {
        dataBase.seeUsers().get(inSystem).getFavMovies().remove(ID);
    }

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

    public static ArrayList<Integer> seenMovielist(int inSystem) {
    return dataBase.seeUsers().get(inSystem).getSeenMovies();
    }
    public static ArrayList<Integer> seenMovieTimes(int inSystem) {
        return dataBase.seeUsers().get(inSystem).getTimes();
    }
    public static ArrayList<Integer> seenMoviedate(int inSystem) {
        return dataBase.seeUsers().get(inSystem).getDates();
    }
    public static ArrayList<Integer> seenFavorite(int inSystem) {
        return dataBase.seeUsers().get(inSystem).getFavMovies();
    }
}


