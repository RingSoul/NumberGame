// Importing Scanner class for obtaining user input
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //provided colors
        final String GREEN = "\u001b[32m";
        final String WHITE = "\u001B[37m";
        final String RED = "\u001B[31m";

        System.out.println();

        // creating a Scanner object
        Scanner input = new Scanner(System.in);

        // introduces the rules
        NumberGame.introduce();

        System.out.println();

        // obtaining user inputs prior to the game & playing the game & concluding the result
        System.out.print("Please enter your username: ");
        String userName = input.nextLine();
        System.out.print("CHOOSE --> Default number of rounds (3) or customized number of rounds? (Enter d for default and c for customized): ");
        String preference = input.nextLine();
        if (CheckInputValidity.checkPreference(preference) == false)
        {
            while (CheckInputValidity.checkPreference(preference) == false)
            {
                System.out.println(RED + "***Your preference is weird...Please try again.***" + WHITE);
                System.out.print("CHOOSE --> Default number of rounds (3) or customized number of rounds? (Enter d for default and c for customized): ");
                preference = input.nextLine();
            }
        }
        if (preference.equals("d"))
        {
            System.out.print("Please enter a 4-digit number (whose individual digits will be visible in your secret sequence): ");
            String userDigits = input.next();
            if (CheckInputValidity.checkFourDigitNum(userDigits) == false)
            {
                while (CheckInputValidity.checkFourDigitNum(userDigits) == false)
                {
                    System.out.println(RED + "***Invalid input! Ensure that your number is four-digit long and there is no repeated digit or characters other than numbers!***" + WHITE);
                    System.out.print("Please enter a 4-digit number (whose individual digits will be visible in your secret sequence): ");
                    userDigits = input.next();
                }
            }
            NumberGame ng = new NumberGame(userName, userDigits);
            for (int i = 0; i < 3; i++)
            {
                System.out.println("\n🌀🌀🌀🌀🌀🌀🌀🌀🌀🌀");
                System.out.println(ng);
                System.out.print("\n" + GREEN + "Enter the operation you want to perform (enter \"" + WHITE + "+" + GREEN + "\" for addition, \"" + WHITE + "-" + GREEN + "\" for subtraction, \"" + WHITE + "*" + GREEN + "\" for multiplication, \"" + WHITE + "/" + GREEN + "\" for division, \"" + WHITE + "^" + GREEN + "\" for exponent, \"" + WHITE + "mod" + GREEN + "\" for modulus): " + WHITE);
                String operation = input.next();
                if (CheckInputValidity.checkOperation(operation) == false)
                {
                    while (CheckInputValidity.checkOperation(operation) == false)
                    {
                        System.out.println(RED + "***Your input for operation is not valid, make sure to follow the instruction!***" + WHITE);
                        System.out.print(GREEN + "Enter the operation you want to perform (enter \"" + WHITE + "+" + GREEN + "\" for addition, \"" + WHITE + "-" + GREEN + "\" for subtraction, \"" + WHITE + "*" + GREEN + "\" for multiplication, \"" + WHITE + "/" + GREEN + "\" for division, \"" + WHITE + "^" + GREEN + "\" for exponent, \"" + WHITE + "mod" + GREEN + "\" for modulus): " + WHITE);
                        operation = input.next();
                    }
                }
                System.out.println();
                ng.play(operation);
                System.out.println(RED + "\n🌀🌀🌀🌀🌀 WAIT 🌀🌀🌀🌀🌀" + WHITE);
                Thread.sleep(3000);
                while (!(ng.getStrStatus().equals("0-1")))
                {
                    System.out.println(ng);
                    System.out.print("\n" + GREEN + "Enter the operation you want to perform (enter \"" + WHITE + "+" + GREEN + "\" for addition, \"" + WHITE + "-" + GREEN + "\" for subtraction, \"" + WHITE + "*" + GREEN + "\" for multiplication, \"" + WHITE + "/" + GREEN + "\" for division, \"" + WHITE + "^" + GREEN + "\" for exponent, \"" + WHITE + "mod" + GREEN + "\" for modulus): " + WHITE);
                    operation = input.next();
                    if (CheckInputValidity.checkOperation(operation) == false)
                    {
                        while (CheckInputValidity.checkOperation(operation) == false)
                        {
                            System.out.println(RED + "***Your input for operation is not valid, make sure to follow the instruction!***" + WHITE);
                            System.out.print(GREEN + "Enter the operation you want to perform (enter \"" + WHITE + "+" + GREEN + "\" for addition, \"" + WHITE + "-" + GREEN + "\" for subtraction, \"" + WHITE + "*" + GREEN + "\" for multiplication, \"" + WHITE + "/" + GREEN + "\" for division, \"" + WHITE + "^" + GREEN + "\" for exponent, \"" + WHITE + "mod" + GREEN + "\" for modulus): " + WHITE);
                            operation = input.next();
                        }
                    }
                    System.out.println();
                    ng.play(operation);
                    System.out.println(RED + "\n🌀🌀🌀🌀🌀 WAIT 🌀🌀🌀🌀🌀" + WHITE);
                    Thread.sleep(3000);
                }
                Thread.sleep(1500);
                clearScreen();
                ng.concludeRound();
            }
            System.out.println(RED + "10 SECONDS BEFORE THE GAME CONCLUDES......" + WHITE);
            System.out.println("***Upcoming animation...Full screen suggested***");
            Thread.sleep(10000);
            //animation
            ng.concludeGame();
        }
        else if (preference.equals("c"))
        {
            System.out.print("Please enter the number of rounds that you want to play (suggested is between 1 and 5): ");
            int roundNum = input.nextInt();
            System.out.print("Please enter a 4-digit number (whose individual digits will be visible in your secret sequence): ");
            String userDigits = input.next();
            if (CheckInputValidity.checkFourDigitNum(userDigits) == false)
            {
                while (CheckInputValidity.checkFourDigitNum(userDigits) == false)
                {
                    System.out.println(RED + "***Invalid input! Ensure that your number is four-digit long and there is no repeated digit or characters other than numbers!***" + WHITE);
                    System.out.print("Please enter a 4-digit number (whose individual digits will be visible in your secret sequence): ");
                    userDigits = input.next();
                }
            }
            NumberGame ng = new NumberGame(userName, userDigits, roundNum);
            for (int i = 0; i < roundNum; i++)
            {
                System.out.println("\n🌀🌀🌀🌀🌀🌀🌀🌀🌀🌀");
                System.out.println(ng);
                System.out.print("\n" + GREEN + "Enter the operation you want to perform (enter \"" + WHITE + "+" + GREEN + "\" for addition, \"" + WHITE + "-" + GREEN + "\" for subtraction, \"" + WHITE + "*" + GREEN + "\" for multiplication, \"" + WHITE + "/" + GREEN + "\" for division, \"" + WHITE + "^" + GREEN + "\" for exponent, \"" + WHITE + "mod" + GREEN + "\" for modulus): " + WHITE);
                String operation = input.next();
                if (CheckInputValidity.checkOperation(operation) == false)
                {
                    while (CheckInputValidity.checkOperation(operation) == false)
                    {
                        System.out.println(RED + "***Your input for operation is not valid, make sure to follow the instruction!***" + WHITE);
                        System.out.print(GREEN + "Enter the operation you want to perform (enter \"" + WHITE + "+" + GREEN + "\" for addition, \"" + WHITE + "-" + GREEN + "\" for subtraction, \"" + WHITE + "*" + GREEN + "\" for multiplication, \"" + WHITE + "/" + GREEN + "\" for division, \"" + WHITE + "^" + GREEN + "\" for exponent, \"" + WHITE + "mod" + GREEN + "\" for modulus): " + WHITE);
                        operation = input.next();
                    }
                }
                System.out.println();
                ng.play(operation);
                System.out.println(RED + "\n🌀🌀🌀🌀🌀 WAIT 🌀🌀🌀🌀🌀" + WHITE);
                Thread.sleep(3000);
                while (!(ng.getStrStatus().equals("0-1")))
                {
                    System.out.println(ng);
                    System.out.print("\n" + GREEN + "Enter the operation you want to perform (enter \"" + WHITE + "+" + GREEN + "\" for addition, \"" + WHITE + "-" + GREEN + "\" for subtraction, \"" + WHITE + "*" + GREEN + "\" for multiplication, \"" + WHITE + "/" + GREEN + "\" for division, \"" + WHITE + "^" + GREEN + "\" for exponent, \"" + WHITE + "mod" + GREEN + "\" for modulus): " + WHITE);
                    operation = input.next();
                    if (CheckInputValidity.checkOperation(operation) == false)
                    {
                        while (CheckInputValidity.checkOperation(operation) == false)
                        {
                            System.out.println(RED + "***Your input for operation is not valid, make sure to follow the instruction!***" + WHITE);
                            System.out.print(GREEN + "Enter the operation you want to perform (enter \"" + WHITE + "+" + GREEN + "\" for addition, \"" + WHITE + "-" + GREEN + "\" for subtraction, \"" + WHITE + "*" + GREEN + "\" for multiplication, \"" + WHITE + "/" + GREEN + "\" for division, \"" + WHITE + "^" + GREEN + "\" for exponent, \"" + WHITE + "mod" + GREEN + "\" for modulus): " + WHITE);
                            operation = input.next();
                        }
                    }
                    System.out.println();
                    ng.play(operation);
                    System.out.println(RED + "\n🌀🌀🌀🌀🌀 WAIT 🌀🌀🌀🌀🌀" + WHITE);
                    Thread.sleep(3000);
                }
                Thread.sleep(1500);
                clearScreen();
                ng.concludeRound();
            }
            System.out.println(RED + "10 SECONDS BEFORE THE GAME CONCLUDES......" + WHITE);
            System.out.println("***Upcoming animation...Full screen suggested***");
            Thread.sleep(10000);
            //animation
            ng.concludeGame();
        }

        // closing the Scanner object
        input.close();

    }

    // provided clearScreen method
    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}