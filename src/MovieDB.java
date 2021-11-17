import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MovieDB implements Serializable {

    private static final String movieDbFilepath = "MovieDB.txt";

    //Loads the Arraylist of movies from the file
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
            Menu.print("ioexception");
        } catch (ClassNotFoundException c) {
            Menu.print("Movie Class not Found");
            c.printStackTrace();
        }
        //System.out.println(datab);
        //System.out.println("Deserialized MovieDBs...");

        return datab;
    }

    //Writes an ArrayList of Movies to Files
    public static void WriteDBToFile(ArrayList<Movie> MovieDatB) {//Writes DB to File
        try {
            FileOutputStream fileOut = new FileOutputStream(movieDbFilepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(MovieDatB);
            objectOut.close();
            fileOut.close();
            //System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            Menu.print("There was a problem writing to file!");
            ex.printStackTrace();
        }
    }

    //Creates new movie and adds it to the DB
    public static void CreateMovie() {//
        Scanner userInput = new Scanner(System.in);
        //userInput.nextLine();
        Menu.print("Do you want to insert a new Movie? y/n");
        String response = userInput.nextLine();
        boolean stop = false;
        while (!stop) {
            if (response.equals("y")) {
                ArrayList<String> actors = new ArrayList<>();
                ArrayList<String> roles = new ArrayList<>();
                //Add Title
                Menu.print("Insert Movie Title:");
                String title = userInput.nextLine();
                //Add ID

                Random rand = new Random();
                //System.out.println("Insert Movie ID:");
                int id = rand.nextInt(9999 + 10);
                while (MovieDB.sameID(id)) {//checks id the id is already in use
                    //System.out.println("ID already in use. Enter different ID");
                    id = rand.nextInt(9999 + 10);
                }
                //Add Year
                Menu.print("Insert Movie Release Year:");
                int year = Menu.checkInt();
                //Insert Actor/Role
                boolean stopRole = false;
                while (!stopRole) {
                    Menu.print("Insert an Actor/Actress:");
                    String actor = userInput.nextLine();
                    actors.add(actor);
                    Menu.print("Insert its/her role:");
                    String role = userInput.nextLine();
                    roles.add(role);
                    Menu.print("Do you want to add another one? y/n");
                    if (userInput.nextLine().equals("n")) {stopRole = true;}
                }
                Movie mov1 = new Movie(id, title, actors, roles, year);
                Menu.flush();
                MovieDB.addMovietoDB(mov1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stop = true;
            } else if (response.equals("n")) {
                Menu.print("Back to the Menu");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stop = true;
            } else {
                Menu.print("please enter y or n");
                stop = true;
            }
        }

    }

    //Loads an existing Movie From the DB and lets a power user Modify it
    public static void UpdateMovie() {
        boolean stop = false;
        while (!stop) {
            Movie mn;
            Menu.print("Do you want to Update a Movie? y/n");
            Scanner userInput = new Scanner(System.in);
            String answ = userInput.nextLine();
            if (answ.equals("y")) {
                FindMovie.knowID();
                Menu.print("Enter ID of the movie you want to Update:");
                int id = Menu.checkInt();
                while (!MovieDB.sameID(id)) {
                    Menu.print("ID not found. Enter different ID");
                    FindMovie.SearchByID(id);
                    id = Menu.checkInt();
                }

                int index = MovieDB.movieDBindex(id);
                mn = MovieDB.ReadDB().get(index);//Movie we want to update loaded
                Menu.print("We're editing: " + mn.toString());

                String ask = """
                                           What do you want to edit?
                                    
                                               1.Title
                                    
                                               2.Year
                                    
                                               3.Actors/Roles
                                    
                                               4.Add Actors/Roles
                                    
                                               5.ID
                        """;
                Menu.printMenu(11,ask);

                int answer = Menu.checkInt();
                ArrayList<String> actors = new ArrayList<>();
                ArrayList<String> roles = new ArrayList<>();
                if (answer == 1) {//Edit Title
                    Menu.print("Insert Movie Title:");
                    String title = userInput.nextLine();
                    mn.setTitle(title);
                } else if (answer == 2) {//Edit Year
                    Menu.print("Insert Movie Release Year:");
                    int year = Menu.checkInt();
                    mn.setDate(year);
                } else if (answer == 3) {//Edit Actors/Roles
                    //Insert Actor/Role
                    boolean stopRole = false;
                    while (!stopRole) {
                        Menu.print("Insert an Actor/Actress:");
                        String actor = userInput.nextLine();
                        actors.add(actor);
                        Menu.print("Insert its/her role:");
                        String role = userInput.nextLine();
                        roles.add(role);
                        Menu.print("Do you want to add another one? y/n");
                        String ans = userInput.nextLine();
                        if (ans.equals("n")) {stopRole = true;}
                    }
                    mn.setActors(actors);
                    mn.setRoles(roles);
                } else if (answer == 4) {//Add Actors/Roles
                    //Insert Actor/Role
                    boolean stopAdd = false;
                    while (!stopAdd) {
                        Menu.print("Insert an Actor/Actress:");
                        String actor = userInput.nextLine();
                        mn.getActors().add(actor);
                        Menu.print("Insert its/her role:");
                        String role = userInput.nextLine();
                        mn.getRoles().add(role);
                        Menu.print("Do you want to add another one? y/n");
                        String res = userInput.nextLine();
                        if (res.equals("n")) {
                            Menu.flush();
                            stopAdd = true;
                        }
                    }
                } else if (answer == 5) {//Edit ID
                    Menu.print("Insert Movie ID:");
                    int idt = Menu.checkInt();
                    while (MovieDB.sameID(idt)) {
                        Menu.print("ID not found. Enter different ID");
                        idt = Menu.checkInt();
                    }
                    mn.setID(idt);
                } else {
                    Menu.print("Insert Valid Number:");
                }

                ArrayList<Movie> movDB;
                movDB = MovieDB.ReadDB();//Create an arraylist Movies
                movDB.set(index, mn);//Sets the new movie created to
                MovieDB.WriteDBToFile(movDB);//Create an arraylist Movies
                Menu.flush();
                Menu.print(mn.getTitle() + " succesfuly edited");
                Menu.printMenu(3,mn.toString());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stop = true;
            } else if (answ.equals("n")) {
                Menu.print("Back to the Menu");
                stop = true;
            } else {
                Menu.print("please enter y or n");
            }

        }
    }

    //Deletes a Movie From the DB
    public static void deleteMovieFromDB() {
        ArrayList<Movie> movDB;
        movDB = MovieDB.ReadDB();
        Scanner userInput = new Scanner(System.in);
        FindMovie.knowID();
        Menu.print("Insert the ID of the Movie");
        int id = Menu.checkInt();

        Menu.print("Are you sure you want to remove " + movDB.get(movieDBindex(id)).getTitle() + "? y/n");
        String answer = userInput.nextLine();
        Menu.flush();
        boolean stop = false;
        while (!stop) {
            if (answer.equals("y")) {
                String removed = movDB.get(movieDBindex(id)).getTitle();
                movDB.remove(movieDBindex(id));
                MovieDB.WriteDBToFile(movDB);
                Menu.print(removed + " has been removed.");
                stop = true;
            } else if (answer.equals("n")) {
                Menu.print(movDB.get(movieDBindex(id)).getTitle() + " is still in the DB.");
                stop = true;
            } else {Menu.print("try again");}
        }

    }

    //Adds a Movie to the DB
    public static void addMovietoDB(Movie movie) {//Adds movie obj to DB
        ArrayList<Movie> movDB;
        movDB = MovieDB.ReadDB();
        movDB.add(movie);
        Menu.print(movie.getTitle() + " added to DB");
        MovieDB.WriteDBToFile(movDB);
    }

    //Returns the index of the movie Object in the DB Movie ArrayList, it is given the Movie ID
    public static int movieDBindex(int movieID) {
        int index = 0;
        for (int i = 0; i < MovieDB.ReadDB().size(); i++) {
            if (MovieDB.ReadDB().get(i).getID() == movieID) {
                index = i;
                //System.out.println(MovieDB.ReadDB().get(i).toString());
            }
        }
        return index;
    }

    //Boolean that returns true if the ID fed to it is already present in the DB
    public static boolean sameID(int ID) {
        boolean same = false;
        for (Movie current : MovieDB.ReadDB()) {
            same = current.getID() == ID;
        }
        return same;
    }

    //Lets a user copy a movie
    public static void CopyMovie() {
        boolean stopit = false;
        while (!stopit) {
            Movie mn;
            Menu.print("Do you want to Copy a Movie? y/n");
            Scanner userInput = new Scanner(System.in);
            String answ = userInput.nextLine();
            if (answ.equals("y")) {
                FindMovie.knowID();
                Menu.print("Enter ID:");
                int id = Menu.checkInt();
                while (MovieDB.sameID(id)) {
                    Menu.print("ID not found. Enter different ID");
                    id = Menu.checkInt();
                }

                int index = MovieDB.movieDBindex(id);

                mn = MovieDB.ReadDB().get(index);//Movie we want to update loaded
                Menu.print("We're copying: " + mn.getTitle());
                String ask = """
                                    Do you want to edit something?
                                     
                                           1.Title & Year
                                    
                                           2.Actors/Roles
                                    
                                           3.Add Actors/Roles
                        """;
                Menu.printMenu(7,ask);
                int answer = Menu.checkInt();
                ArrayList<String> actors = new ArrayList<>();
                ArrayList<String> roles = new ArrayList<>();
                if (answer == 1) {//Edit Title & Year
                    Menu.print("Insert Movie Title:");
                    String title = userInput.nextLine();
                    mn.setTitle(title);
                    Menu.print("Insert Movie Release Year:");
                    int year = Menu.checkInt();
                    mn.setDate(year);
                } else if (answer == 2) {//Edit Actors/Roles
                    //Insert Actor/Role
                    boolean stopRole = false;
                    while (!stopRole) {
                        Menu.print("Insert an Actor/Actress:");
                        String actor = userInput.nextLine();
                        actors.add(actor);
                        Menu.print("Insert its/her role:");
                        String role = userInput.nextLine();
                        roles.add(role);
                        Menu.print("Do you want to add another one? y/n");
                        String ans = userInput.nextLine();
                        if (ans.equals("n")) {stopRole = true;}
                    }
                    mn.setActors(actors);
                    mn.setRoles(roles);
                } else if (answer == 3) {//Add Actors/Roles
                    //Insert Actor/Role
                    boolean stopAdd = false;
                    while (!stopAdd) {
                        Menu.flush();
                        Menu.print("Insert an Actor/Actress:");
                        String actor = userInput.nextLine();
                        mn.getActors().add(actor);
                        Menu.print("Insert its/her role:");
                        String role = userInput.nextLine();
                        mn.getRoles().add(role);
                        Menu.print("Do you want to add another one? y/n");
                        String res = userInput.nextLine();
                        if (res.equals("n")) {
                            Menu.flush();
                            stopAdd = true;
                        }
                    }
                } else {
                    Menu.print("Insert Valid Number:");

                }

                Random rand = new Random();
                //System.out.println("Insert Movie ID:");
                int idt = rand.nextInt(9999 +10);
                while (MovieDB.sameID(id)) {//checks id the id is already in use
                    //System.out.println("ID already in use. Enter different ID");
                    idt = rand.nextInt(9999+10);
                }
                mn.setID(idt);
                MovieDB.addMovietoDB(mn);
                Menu.flush();
                Menu.print(mn.getID() + " " + mn.getTitle() + " succesfuly copied");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stopit = true;

            } else if (answ.equals("n")) {
                Menu.print("Back to the Menu");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopit = true;
            } else {
                Menu.print("please enter y or n");
            }

        }
    }

    //Lets a user copy a movie
    public static void CopyMovieAR() {
        boolean stopit = false;
        while (!stopit) {
            Movie aRless;
            Movie aRmore;
            Menu.print("Do you want to Copy a Movie Actors/Roles from anothe movie? y/n");
            Scanner userInput = new Scanner(System.in);
            String answ = userInput.nextLine();
            if (answ.equals("y")) {
                FindMovie.knowID();
                Menu.print("Enter ID of the movie you want to copy the info from:");
                int id = Menu.checkInt();
                while (MovieDB.sameID(id)) {
                    Menu.print("ID not found. Enter different ID");
                    id = Menu.checkInt();
                }
                aRmore = FindMovie.returnMovByID(id);//Movie we want to copy the info from
                Menu.print(aRmore.toString());
                int index2 = MovieDB.movieDBindex(id);
                Menu.print("Enter ID of the movie you want to copy the info to:");
                int idless = Menu.checkInt();
                while (MovieDB.sameID(idless)) {
                    Menu.print("ID not found. Enter different ID");
                    idless = Menu.checkInt();
                }
                aRless = FindMovie.returnMovByID(idless);//Movie we want to copy the info from
                Menu.print(aRless.toString());

                Menu.printMenu(8,"You want to copy: \n"+
                        aRmore.toString()+  " data "+
                        "\n"+ "to: "+aRless.toString());
                int index = MovieDB.movieDBindex(idless);

                ArrayList<String> actors = new ArrayList<>();
                ArrayList<String> roles = new ArrayList<>();

                aRless.setActors(aRmore.getActors());
                aRless.setRoles(aRmore.getRoles());

                ArrayList<Movie> movDB;
                movDB = MovieDB.ReadDB();//Create an arraylist Movies
                movDB.set(index, aRless);//Sets the new movie created to
                MovieDB.WriteDBToFile(movDB);//Create an arraylist Movies
                Menu.flush();
                Menu.printMenu(4, MovieDB.ReadDB().get(index).toString()+ " edited in the DB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stopit = true;

            } else if (answ.equals("n")) {
                Menu.print("Back to the Menu");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopit = true;
            } else {
                Menu.print("please enter y or n");
            }

        }
    }

}
