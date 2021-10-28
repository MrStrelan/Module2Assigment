import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieDB implements Serializable {

    private static final String movieDbFilepath = "MovieDB.ser";


    public static void deleteMovieFromDB() {
        ArrayList<Movie> movDB;
        movDB = MovieDB.ReadDB();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Insert ID to delete:");
        int id = userInput.nextInt();
        userInput.nextLine();
        Movie mn = new Movie();
        for (int i = 0; i < movDB.size(); i++) {
            if (movDB.get(i).getID() == id)
                movDB.remove(i);
        }

        MovieDB.WriteDBToFile(movDB);
    }


    public static void CreateMovie() {

        System.out.println("Do you want to insert a new Movie? y/n");
        Scanner userInput = new Scanner(System.in);

        if (userInput.nextLine().equals("y")) {
            ArrayList<String> actors = new ArrayList<>();
            ArrayList<String> roles = new ArrayList<>();

            //Add Title
            System.out.println("Insert Movie Title:");
            String title = userInput.nextLine();
            //Add ID
            System.out.println("Insert Movie ID:");
            int id = userInput.nextInt();
            while (MovieDB.sameID(id)) {
                System.out.println("ID already in use. Enter different ID");
                id = userInput.nextInt();
            }

            //Add Year
            System.out.println("this is the title:" + title);
            System.out.println("Insert Movie Production Year:");
            int year = userInput.nextInt();
            userInput.nextLine();
            //Insert Actor/Role
            boolean stop = false;
            while (!stop) {
                System.out.println("Insert an Actor/Actress:");
                String actor = userInput.nextLine();
                actors.add(actor);
                System.out.println("Insert its/her role:");
                String role = userInput.nextLine();
                roles.add(role);
                System.out.println("Do you want to add another one? y/n");
                if (userInput.nextLine().equals("n")) {stop = true;}
            }
            Movie mov1 = new Movie(id, title, actors, roles, year);
            MovieDB.addMovietoDB(mov1);
        } else if (userInput.nextLine().equals("n")) {
            System.out.println("Back to the Menu");
        } else if (!userInput.nextLine().equals("n") || !userInput.nextLine().equals("y")) {
            System.out.println("please enter y or n");

        }

    }

    public static void UpdateMovie() {
        System.out.println("Do you want to Update a Movie? y/n");
        Scanner userInput = new Scanner(System.in);

        if (userInput.nextLine().equals("y")) {
            System.out.println("What movie do you want to edit? Enter ID:");
            int id = userInput.nextInt();
            while (MovieDB.sameID(id)) {
                System.out.println("ID not found. Enter different ID");
                id = userInput.nextInt();
            }
            Movie mn = new Movie();
            int index = 0;
            for (int i = 0; i < MovieDB.ReadDB().size(); i++) {
                if (MovieDB.ReadDB().get(i).getID() == id) {
                    mn = MovieDB.ReadDB().get(i);
                    index = i;
                    System.out.println(MovieDB.ReadDB().get(i).toString());
                }


            }

            String ask = """
                                What do you want to edit?
                                1.Title
                                2.Year
                                3.Actors/Roles
                                4.Add Actors/Roles
                                5.ID
                    """;
            System.out.println(ask);
            int answer = userInput.nextInt();
            ArrayList<String> actors = new ArrayList<>();
            ArrayList<String> roles = new ArrayList<>();
            if (answer == 1) {//Edit Title
                System.out.println("Insert Movie Title:");
                String title = userInput.nextLine();
                mn.setTitle(title);
            } else if (answer == 2) {//Edit Year
                System.out.println("Insert Movie Production Year:");
                int year = userInput.nextInt();
                userInput.nextLine();
                mn.setDate(year);
            } else if (answer == 3) {//Edit Actors/Roles
                //Insert Actor/Role
                boolean stop = false;
                while (!stop) {
                    System.out.println("Insert an Actor/Actress:");
                    userInput.nextLine();
                    String actor = userInput.nextLine();
                    actors.add(actor);
                    userInput.nextLine();
                    System.out.println("Insert its/her role:");
                    String role = userInput.nextLine();
                    roles.add(role);
                    System.out.println("Do you want to add another one? y/n");
                    if (userInput.nextLine().equals("n")) {stop = true;}
                }
                mn.setActors(actors);
                mn.setRoles(roles);
            } else if (answer == 4) {//Add Actors/Roles
                //Insert Actor/Role
                boolean stop = false;
                while (!stop) {
                    System.out.println("Insert an Actor/Actress:");
                    userInput.nextLine();
                    String actor = userInput.nextLine();
                    mn.getActors().add(actor);
                    userInput.nextLine();
                    System.out.println("Insert its/her role:");
                    String role = userInput.nextLine();
                    mn.getRoles().add(role);
                    System.out.println("Do you want to add another one? y/n");
                    if (userInput.nextLine().equals("n")) {stop = true;}
                }
            } else if (answer == 5) {//Edit ID
                System.out.println("Insert Movie ID:");
                int idt = userInput.nextInt();
                while (MovieDB.sameID(idt)) {
                    System.out.println("ID not found. Enter different ID");
                    idt = userInput.nextInt();
                }
                mn.setID(idt);
            } else if (answer > 5 || userInput.nextInt() < 1) {
                System.out.println("Insert Valid Number:");
            }
            ArrayList<Movie> movDB;
            movDB = MovieDB.ReadDB();
            movDB.set(index,mn);
            MovieDB.WriteDBToFile(movDB);
        } else if(userInput.nextLine().equals("n"))

        {
            System.out.println("Back to the Menu");
        } else if(!userInput.nextLine().equals("n")||!userInput.nextLine().equals("y"))

        {
            System.out.println("please enter y or n");
        }


    }




        /*/Add ID
        System.out.println("Insert Movie ID:");
        int id = userInput.nextInt();
        if (!MovieDB.sameID(id)) {
            System.out.println("ID not found. Enter different ID");
            id = userInput.nextInt();
        }
        Movie mn = new Movie();
        for (Movie m : MovieDB.ReadDB()
        ) {
            if (m.getID() == id)
                System.out.println(m.toString());
        }
        userInput.nextLine();
        //Add Title
        System.out.println("Insert Movie Title:");
        String title = userInput.nextLine();

        //Add Year
        System.out.println("Insert Movie Production Year:");
        int year = userInput.nextInt();
        userInput.nextLine();
        //Insert Actor/Role
        boolean stop = false;
        while (!stop) {
            System.out.println("Insert an Actor/Actress:");
            String actor = userInput.nextLine();
            actors.add(actor);
            System.out.println("Insert its/her role:");
            String role = userInput.nextLine();
            roles.add(role);
            System.out.println("Do you want to add another one? y/n");
            if (userInput.nextLine().equals("n")) {stop = true;}
        }
        Movie mov1 = new Movie(id, title, actors, roles, year);
        MovieDB.addMovietoDB(mov1);
    } else if(userInput.equals("n"))

    {
        System.out.println("Back to the Menu");
    } else if(!userInput.equals("n")||!userInput.equals("y"))

    {
        System.out.println("please enter y or n");
    }

}
*/

    public static void updateMovie(Movie movie, ArrayList<Movie> movDB) {
        Boolean rightID = false;
        for (Movie current : movDB) {
            if (current.getID() == movie.getID()) {
                current = movie;
                System.out.println("Movie details updated");
                System.out.println(current);
                break;
            } else {
                System.out.println("Could not find movie in DB");
            }
        }
        movDB.add(movie);
        System.out.println(movie.getTitle());
        MovieDB.WriteDBToFile(movDB);
    }

    public static boolean sameID(int ID) { //Checks ID is already in use
        boolean same = false;
        for (Movie current : MovieDB.ReadDB()) {
            same = current.getID() == ID;
        }
        return same;
    }


    public static ArrayList<Movie> ReadDB() {//Loads DB on a ArrayList Obj
        ArrayList<Movie> datab = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(movieDbFilepath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            datab = (ArrayList<Movie>) in.readObject(); // type casting
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("ioexception");
        } catch (ClassNotFoundException c) {
            System.out.println("Movie Class not Found");
            c.printStackTrace();
        }
        //System.out.println(datab);
        //System.out.println("Deserialized MovieDBs...");

        return datab;
    }

    public static void addMovietoDB(Movie movie) {//Adds movie obj to DB
        ArrayList<Movie> movDB;
        movDB = MovieDB.ReadDB();
        movDB.add(movie);
        System.out.println(movie.getTitle());
        MovieDB.WriteDBToFile(movDB);
    }

    public static void WriteDBToFile(ArrayList<Movie> MovieDatB) {//Writes DB to File
        try {
            FileOutputStream fileOut = new FileOutputStream(movieDbFilepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(MovieDatB);
            objectOut.close();
            //System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }






    /*public static void printMovieInfo(String name, ArrayList<Movie> movDB) {

        System.out.println("This");
        boolean foundMatch = false;
        for (Movie currentMovie : movDB) {
            if (currentMovie.getTitle().equalsIgnoreCase(name)) {
                System.out.println(currentMovie.getTitle() + " " + currentMovie.getDate());
                foundMatch = true;
            }
        }
        if (!foundMatch) {
            System.out.println("There are no such Movies");
        }
    }*/


}
