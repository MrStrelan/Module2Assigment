import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    //Creating files
    File usernames = new File("Accounts.txt");

    public void addUser(Account user) {
        ArrayList<Account> users = new ArrayList<Account>();
        users.add(user);
        try {
            FileOutputStream addUsers = new FileOutputStream(usernames);
            ObjectOutputStream usernamesIN = new ObjectOutputStream(addUsers);
            usernamesIN.writeObject(users);
            addUsers.close();
            usernamesIN.close();
        } catch (Exception e) {
            System.out.println("Failed file writing");
        }
    }

    public ArrayList<Account> seeUsers()
    {
        ArrayList<Account> users = new ArrayList<Account>();
        try {
            FileInputStream userList = new FileInputStream(usernames);
            ObjectInputStream usernamesOut = new ObjectInputStream(userList);
            users = (ArrayList<Account>) usernamesOut.readObject();
            userList.close();
            usernamesOut.close();
        }catch(Exception e)
        {
            System.out.println("Failed file reading");
        }
        return users;
    }

}