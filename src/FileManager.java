import java.io.*;
import java.util.Scanner;

public class FileManager
{

        File usernames = new File("Accounts.txt");
        File passwords = new File("Passwords.txt");
        FileOutputStream addUsernames = new FileOutputStream(usernames, false);
        FileOutputStream addPasswords = new FileOutputStream(passwords, false);
        PrintStream inputUser = new PrintStream(addUsernames);
        PrintStream inputPass = new PrintStream(addPasswords);
        Scanner reads = new Scanner("Accounts.txt");
    {
        System.out.println("File not found");
    }

    public void add(String username)
    {
        inputUser.println(username);
    }

}
