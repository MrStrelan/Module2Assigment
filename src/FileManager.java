import java.io.*;
import java.util.Scanner;

public class FileManager
{

        File usernames = new File("Accounts.txt");
        File passwords = new File("Passwords.txt");

    public void manage() {
        try {
            FileOutputStream addUsernames = new FileOutputStream(usernames, false);
            FileOutputStream addPasswords = new FileOutputStream(passwords, false);
            PrintStream inputUser = new PrintStream(addUsernames);
            PrintStream inputPass = new PrintStream(addPasswords);
            Scanner reads = new Scanner("Accounts.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}




