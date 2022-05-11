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
        System.out.print("请输入你的用户名：");
        String userName = input.nextLine();
        System.out.print("选择 → 默认游玩局数（3） 或 自定义局数？（d 为默认， c 为自定义）：");
        String preference = input.nextLine();
        if (CheckInputValidity.checkPreference(preference) == false)
        {
            while (CheckInputValidity.checkPreference(preference) == false)
            {
                System.out.println(RED + "***你的输入很怪异……请重试……***" + WHITE);
                System.out.print("选择 → 默认游玩局数（3） 或 自定义局数？（d 为默认， c 为自定义）：");
                preference = input.nextLine();
            }
        }
        if (preference.equals("d"))
        {
            System.out.print("请输入一个4位数的数字（其各个数字都会在你的秘密序列中可见）：");
            String userDigits = input.next();
            if (CheckInputValidity.checkFourDigitNum(userDigits) == false)
            {
                while (CheckInputValidity.checkFourDigitNum(userDigits) == false)
                {
                    System.out.println(RED + "***无效的输入！确保你的数字为4位数、没有数字重复，也没有除了数字以外的字符！***" + WHITE);
                    System.out.print("请输入一个4位数的数字（其各个数字都会在你的秘密序列中可见）：");
                    userDigits = input.next();
                }
            }
            NumberGame ng = new NumberGame(userName, userDigits);
            for (int i = 0; i < 3; i++)
            {
                System.out.println("\n🌀🌀🌀🌀🌀🌀🌀🌀🌀🌀");
                System.out.println(ng);
                System.out.print("\n" + GREEN + "输入你要执行的操作（输入 \"" + WHITE + "+" + GREEN + "\" 以代表加法，\"" + WHITE + "-" + GREEN + "\" 以代表减法，\"" + WHITE + "*" + GREEN + "\" 以代表乘法，\"" + WHITE + "/" + GREEN + "\" 以代表除法，\"" + WHITE + "^" + GREEN + "\" 以代表指数，\"" + WHITE + "mod" + GREEN + "\" 以代表模数）：" + WHITE);
                String operation = input.next();
                if (CheckInputValidity.checkOperation(operation) == false)
                {
                    while (CheckInputValidity.checkOperation(operation) == false)
                    {
                        System.out.println(RED + "***你的输入无效，请务必按照说明进行操作！***" + WHITE);
                        System.out.print(GREEN + "输入你要执行的操作（输入 \"" + WHITE + "+" + GREEN + "\" 以代表加法，\"" + WHITE + "-" + GREEN + "\" 以代表减法，\"" + WHITE + "*" + GREEN + "\" 以代表乘法，\"" + WHITE + "/" + GREEN + "\" 以代表除法，\"" + WHITE + "^" + GREEN + "\" 以代表指数，\"" + WHITE + "mod" + GREEN + "\" 以代表模数）：" + WHITE);
                        operation = input.next();
                    }
                }
                System.out.println();
                ng.play(operation);
                System.out.println(RED + "\n🌀🌀🌀🌀🌀 请等待 🌀🌀🌀🌀🌀" + WHITE);
                Thread.sleep(3000);
                while (!(ng.getStrStatus().equals("0-1")))
                {
                    System.out.println(ng);
                    System.out.print("\n" + GREEN + "输入你要执行的操作（输入 \"" + WHITE + "+" + GREEN + "\" 以代表加法，\"" + WHITE + "-" + GREEN + "\" 以代表减法，\"" + WHITE + "*" + GREEN + "\" 以代表乘法，\"" + WHITE + "/" + GREEN + "\" 以代表除法，\"" + WHITE + "^" + GREEN + "\" 以代表指数，\"" + WHITE + "mod" + GREEN + "\" 以代表模数）：" + WHITE);
                    operation = input.next();
                    if (CheckInputValidity.checkOperation(operation) == false)
                    {
                        while (CheckInputValidity.checkOperation(operation) == false)
                        {
                            System.out.println(RED + "***你的输入无效，请务必按照说明进行操作！***" + WHITE);
                        System.out.print(GREEN + "输入你要执行的操作（输入 \"" + WHITE + "+" + GREEN + "\" 以代表加法，\"" + WHITE + "-" + GREEN + "\" 以代表减法，\"" + WHITE + "*" + GREEN + "\" 以代表乘法，\"" + WHITE + "/" + GREEN + "\" 以代表除法，\"" + WHITE + "^" + GREEN + "\" 以代表指数，\"" + WHITE + "mod" + GREEN + "\" 以代表模数）：" + WHITE);
                        operation = input.next();
                        }
                    }
                    System.out.println();
                    ng.play(operation);
                    System.out.println(RED + "\n🌀🌀🌀🌀🌀 请等待 🌀🌀🌀🌀🌀" + WHITE);
                    Thread.sleep(3000);
                }
                Thread.sleep(1500);
                clearScreen();
                ng.concludeRound();
            }
            System.out.println(RED + "距离游戏结束还有10秒…………" + WHITE);
            System.out.println("***动画即将出现……建议全屏***");
            Thread.sleep(10000);
            //animation
            ng.concludeGame();
        }
        else if (preference.equals("c"))
        {
            System.out.print("请输入你想游玩的回合数（建议为1到5回合之间）：");
            int roundNum = input.nextInt();
            System.out.print("请输入一个4位数的数字（其各个数字都会在你的秘密序列中可见）：");
            String userDigits = input.next();
            if (CheckInputValidity.checkFourDigitNum(userDigits) == false)
            {
                while (CheckInputValidity.checkFourDigitNum(userDigits) == false)
                {
                    System.out.println(RED + "***无效的输入！确保你的数字为4位数、没有数字重复，也没有除了数字以外的字符！***" + WHITE);
                    System.out.print("请输入一个4位数的数字（其各个数字都会在你的秘密序列中可见）：");
                    userDigits = input.next();
                }
            }
            NumberGame ng = new NumberGame(userName, userDigits, roundNum);
            for (int i = 0; i < roundNum; i++)
            {
                System.out.println("\n🌀🌀🌀🌀🌀🌀🌀🌀🌀🌀");
                System.out.println(ng);
                System.out.print("\n" + GREEN + "输入你要执行的操作（输入 \"" + WHITE + "+" + GREEN + "\" 以代表加法，\"" + WHITE + "-" + GREEN + "\" 以代表减法，\"" + WHITE + "*" + GREEN + "\" 以代表乘法，\"" + WHITE + "/" + GREEN + "\" 以代表除法，\"" + WHITE + "^" + GREEN + "\" 以代表指数，\"" + WHITE + "mod" + GREEN + "\" 以代表模数）：" + WHITE);
                String operation = input.next();
                if (CheckInputValidity.checkOperation(operation) == false)
                {
                    while (CheckInputValidity.checkOperation(operation) == false)
                    {
                        System.out.println(RED + "***你的输入无效，请务必按照说明进行操作！***" + WHITE);
                        System.out.print(GREEN + "输入你要执行的操作（输入 \"" + WHITE + "+" + GREEN + "\" 以代表加法，\"" + WHITE + "-" + GREEN + "\" 以代表减法，\"" + WHITE + "*" + GREEN + "\" 以代表乘法，\"" + WHITE + "/" + GREEN + "\" 以代表除法，\"" + WHITE + "^" + GREEN + "\" 以代表指数，\"" + WHITE + "mod" + GREEN + "\" 以代表模数）：" + WHITE);
                        operation = input.next();
                    }
                }
                System.out.println();
                ng.play(operation);
                System.out.println(RED + "\n🌀🌀🌀🌀🌀 请等待 🌀🌀🌀🌀🌀" + WHITE);
                Thread.sleep(3000);
                while (!(ng.getStrStatus().equals("0-1")))
                {
                    System.out.println(ng);
                    System.out.print("\n" + GREEN + "输入你要执行的操作（输入 \"" + WHITE + "+" + GREEN + "\" 以代表加法，\"" + WHITE + "-" + GREEN + "\" 以代表减法，\"" + WHITE + "*" + GREEN + "\" 以代表乘法，\"" + WHITE + "/" + GREEN + "\" 以代表除法，\"" + WHITE + "^" + GREEN + "\" 以代表指数，\"" + WHITE + "mod" + GREEN + "\" 以代表模数）：" + WHITE);
                    operation = input.next();
                    if (CheckInputValidity.checkOperation(operation) == false)
                    {
                        while (CheckInputValidity.checkOperation(operation) == false)
                        {
                            System.out.println(RED + "***你的输入无效，请务必按照说明进行操作！***" + WHITE);
                            System.out.print(GREEN + "输入你要执行的操作（输入 \"" + WHITE + "+" + GREEN + "\" 以代表加法，\"" + WHITE + "-" + GREEN + "\" 以代表减法，\"" + WHITE + "*" + GREEN + "\" 以代表乘法，\"" + WHITE + "/" + GREEN + "\" 以代表除法，\"" + WHITE + "^" + GREEN + "\" 以代表指数，\"" + WHITE + "mod" + GREEN + "\" 以代表模数）：" + WHITE);
                            operation = input.next();
                        }
                    }
                    System.out.println();
                    ng.play(operation);
                    System.out.println(RED + "\n🌀🌀🌀🌀🌀 请等待 🌀🌀🌀🌀🌀" + WHITE);
                    Thread.sleep(3000);
                }
                Thread.sleep(1500);
                clearScreen();
                ng.concludeRound();
            }
            System.out.println(RED + "距离游戏结束还有10秒…………" + WHITE);
            System.out.println("***动画即将出现……建议全屏***");
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