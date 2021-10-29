import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileManager funk1 = new FileManager();
        boolean creatingAccount = true;
        while (creatingAccount == true) {
            System.out.println("1. Log in");
            System.out.println("2. Create account");
            Scanner menuSc = new Scanner(System.in);
            int input = menuSc.nextInt();
            if (input == 1) {
                System.out.println("Enter username");

            } else if (input == 2) {

                System.out.println("Create username");
                Scanner usernameSc = new Scanner(System.in);
                //Puts users input into variable "enterUsername"
                String enteredUsername = "";
                enteredUsername = usernameSc.nextLine();

                boolean creatingUsername = true;
                while (creatingUsername == true) {
                    //Checks if Username list is not empty
                    if (funk1.checkLine() == true) {
                        //Checks for matches of Usernames
                        boolean matchesUsername = false;
                        while (funk1.checkLine()==true) {
                            String username = "";
                            username = funk1.readNextLine();
                            if (enteredUsername.equals(username)){
                                matchesUsername = true;
                            }
                        }
                        //If match is not found adds username
                        if (matchesUsername == false) {
                            funk1.addUser(enteredUsername);
                            creatingUsername = false;
                            creatingAccount = false;
                            //If match is found asks to repeat
                        } else {
                            System.out.println("This username is already taken, try another one");
                        }
                        //Adds user is Username list is empty
                    } else {
                        funk1.addUser(enteredUsername);
                        creatingUsername = false;
                        creatingAccount = false;
                    }
                }

            } else {
                System.out.println("This option is not on menu, try again");
            }
        }

    }
}
