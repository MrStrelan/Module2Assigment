import java.io.*;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Scanner;
import kotlin.Pair;

public class Main {
    public static void main(String[] args) {

        //Load Movie Database (MovieDB is an Arraylist of Movie Objects)
        Menu.menuLog();

        System.out.println("1. Log in");
        System.out.println("2. Create account");
        Account newAcc = new Account();
        Pair<Boolean, Integer> logInfo;
        //Index in ArrayList of user who is using the system
        int inSystem;
        boolean creatingAccount = true;
        while (creatingAccount == true) {
            Scanner menuScan = new Scanner(System.in);
            int input = menuScan.nextInt();
            if (input == 1)
            {
                logInfo = Account.logIn();
                creatingAccount = logInfo.getFirst();
                inSystem = logInfo.getSecond();
                System.out.println("Logged in successfully");
            } else if (input == 2)
            {
                logInfo = Account.creatingUser();
                creatingAccount = logInfo.getFirst();
                inSystem = logInfo.getSecond();
                System.out.println("Account created");
            } else
            {
                System.out.println("This option is not on menu, try again");
            }
        }
    }
}