import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {

    private int ID;
    private String title;
    private ArrayList<String> actors = new ArrayList<>();
    private ArrayList<String> roles = new ArrayList<>();
    private int date;
    private static int IDnum=1;

    public Movie(){}
    public Movie(int ID, String title, ArrayList<String> actors, ArrayList<String> roles, int date) {
        this.ID = ID;
        this.title = title;
        this.actors = actors;
        this.roles = roles;
        this.date = date;
        IDnum++;
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

    public ArrayList<String> getActors() {return actors;}

    public ArrayList<String> getRoles() {return roles;}

    public static int getIDnum() {return IDnum;}

    public String getActor(int ID) {
        return actors.get(ID);
    }

    public String getRole(int ID) {
        return roles.get(ID);
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public static void setIDnum(int IDnum) {
        Movie.IDnum = IDnum;
    }

    public String toString(){

        return "\n ID:"+getID()+", "+getTitle() +", "+ getDate()+",\n"+ getActorRole();
    }

    public String getActorRole(){
        StringBuilder display = new StringBuilder("with: ");
        for (int i = 0; i < getActors().size(); i++) {
            display.append(" ").append(getActors().get(i)).append(" as ").append(getRoles().get(i));
            if (i < getActors().size()-1){
                display.append("\n");
            }
        }
        return display.toString();
    } //Print aid to nicely print the actor and their Role

}

