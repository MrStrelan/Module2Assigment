import java.util.ArrayList;

public class Account
{
    private static int IDnum = 0;
    private String login;
    private String password;
    private ArrayList<Integer> favMovies = new ArrayList<Integer>();
    private ArrayList<Integer> seenMovies = new ArrayList<Integer>();
    private ArrayList<Integer> seenDate = new ArrayList<Integer>();

    public Account(){}
    public Account(String login, String password)
    {
        this.login = login;
        this.password = password;
        IDnum++;
    }

    public void addFavorite(int num)
    {
        favMovies.add(num);
    }

    public void setSeenDate(int date)
    {
        seenDate.add(date);
    }

    public int getFavMovie(int index) {
        return favMovies.get(index);
    }

    public int getSeenMovie(int index) {
        return seenMovies.get(index);
    }

    public int getSeenDate(int index) {
        return seenDate.get(index);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static int getIDnum() {
        return IDnum;
    }
}


