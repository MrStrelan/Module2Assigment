import java.util.ArrayList;

public class Movie
{
    private int ID;
    private String title;
    private ArrayList<String> actor = new ArrayList<String>();
    private ArrayList<String> role = new ArrayList<String>();
    private int date;
    private static int IDnum=1;

    public Movie(String title, int date)
    {
        this.title = title;
        this.date = date;
        this.ID = IDnum;
        IDnum++;
    }

    public void addActor(String name, String role)
    {
        actor.add(name);
        this.role.add(role);
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public int getDate() {
        return date;
    }

    public String getActor(int ID) {
        return actor.get(ID);
    }

    public String getRole(int ID) {
        return role.get(ID);
    }
}

