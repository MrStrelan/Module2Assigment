import java.io.*;
import java.util.Scanner;

public class FileManager {
    //Creating files
    File usernames = new File("Accounts.txt");
    private Scanner reads;

    public void addUser(String username) {
        try {
            //Deciding how we gonna write in the files
            FileOutputStream addUsernames = new FileOutputStream(usernames, true);
            PrintStream inputUser = new PrintStream(addUsernames);
            inputUser.println(username);
            inputUser.close();
        } catch (FileNotFoundException e) {
            System.out.println("Adder didn't found file");
        }
    }

    public String readNextLine () {
        String line = "";
        try {
            reads = new Scanner(usernames);
            line = reads.nextLine();
        }catch (FileNotFoundException e) {
            System.out.println("Reader didn't got file");
        }
        return line;
    }

    public boolean checkLine()
    {
        boolean line = false;
        try {
            reads = new Scanner(usernames);
            line = reads.hasNextLine();
            String a = reads.nextLine();
        }catch (FileNotFoundException e)
        {
            System.out.println("Didn't found file while checking line");
        }
        return line;
    }

}
