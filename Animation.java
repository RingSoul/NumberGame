public class Animation
{
    /* No instance variables */
    /* No/Empty constructor */
    public Animation()
    {
        /* Empty */
    }


    /* Methods */
    // This method animates text that indicates the result of the game as the user wins and the computer loses
    public static void userWinAnimation() throws InterruptedException
    {
        Main.clearScreen();

        // Colors
        final String GREEN = "\u001b[32m";
        final String WHITE = "\u001B[37m";

        // "You"
        String line1 = GREEN + "__          __";
        String line2 = GREEN + "\\ \\        / /";
        String line3 = GREEN + " \\ \\      / /";
        String line4 = GREEN + "  \\ \\    / /      =========        _        _";
        String line5 = GREEN + "   \\ \\  / /     //         \\\\     ||        ||";
        String line6 = GREEN + "    \\ \\/ /      ||         ||     ||        ||";
        String line789 = GREEN + "     |  |       ||         ||     ||        ||";
        String line10 = GREEN + "     |  |       \\\\         //      \\\\       ||";
        String line11 = GREEN + "     ----         =========         ========\\_\\";

        // "Win"
        String space = " ";
        for (int x = 1; x < 50; x++)
        {
            space += " ";
        } // space --> 50 spaces
        String line12 = GREEN + space + "_                              _      |=======|      _";
        String line13 = GREEN + space + "\\\\                            //          ||         ||/=======";
        String line14 = GREEN + space + " \\\\                          //           ||         | /       \\\\";
        String line15 = GREEN + space + "  \\\\          /\\            //            ||         ||        ||";
        String line16 = GREEN + space + "   \\\\        //\\\\          //             ||         ||        ||";
        String line17 = GREEN + space + "    \\\\      //  \\\\        //              ||         ||        ||";
        String line18 = GREEN + space + "     \\\\    //    \\\\      //               ||         ||        ||";
        String line19 = GREEN + space + "      \\\\  //      \\\\    //                ||         ||        ||";
        String line20 = GREEN + space + "       \\\\//        \\\\  //                 ||         ||        ||";
        String line21 = GREEN + space + "        \\ /         \\\\//                  ||         ||        ||";
        String line22 = GREEN + space + "         --          --                |=======|     -          -";

        // Animation
        String move = " ";
        for (int i = 5; i <= 50; i++)
        {
            // Move "You" towards right
            System.out.println(move + line1);
            System.out.println(move + line2);
            System.out.println(move + line3);
            System.out.println(move + line4);
            System.out.println(move + line5);
            System.out.println(move + line6);
            for (int x = 1; x <= 3; x++)
            {
                System.out.println(move + line789);
            }
            System.out.println(move + line10);
            System.out.println(move + line11);

            // Move "Win" towards left
            System.out.println(line12.substring(i));
            System.out.println(line13.substring(i));
            System.out.println(line14.substring(i));
            System.out.println(line15.substring(i));
            System.out.println(line16.substring(i));
            System.out.println(line17.substring(i));
            System.out.println(line18.substring(i));
            System.out.println(line19.substring(i));
            System.out.println(line20.substring(i));
            System.out.println(line21.substring(i));
            System.out.println(line22.substring(i));

            move += " ";
            Thread.sleep(200);
            Main.clearScreen();
        }
        System.out.print(WHITE);
    }


    // This method animates text that indicates the result of the game as the user loses and the computer wins
    public static void computerWinAnimation() throws InterruptedException
    {
        Main.clearScreen();

        // Colors
        final String RED = "\u001B[31m";
        final String WHITE = "\u001B[37m";

        // "You"
        String space = " ";
        for (int x = 1; x < 50; x++)
        {
            space += " ";
        } // space --> 50 spaces
        String line1 = RED + space + "__          __";
        String line2 = RED + space + "\\ \\        / /";
        String line3 = RED + space + " \\ \\      / /";
        String line4 = RED + space + "  \\ \\    / /      =========        _        _";
        String line5 = RED + space + "   \\ \\  / /     //         \\\\     ||        ||";
        String line6 = RED + space + "    \\ \\/ /      ||         ||     ||        ||";
        String line789 = RED + space + "     |  |       ||         ||     ||        ||";
        String line10 = RED + space + "     |  |       \\\\         //      \\\\       ||";
        String line11 = RED + space + "     ----         =========         ========\\_\\";

        // "Lose"
        String line12 = RED + " _";
        String line13 = RED + "| |              //=|    //==|";
        String line14 = RED + "| |              ||       ||";
        String line15 = RED + "| |     //===\\\\  \\\\=\\\\    ||==|";
        String line16 = RED + "| |___  ||   ||      ||   ||";
        String line17 = RED + "|_____| \\\\===//   |=//    \\\\==|";

        String move = " ";
        // Animation
        for (int i = 5; i <= 50; i++)
        {
            // Move "You" towards left
            System.out.println(line1.substring(i));
            System.out.println(line2.substring(i));
            System.out.println(line3.substring(i));
            System.out.println(line4.substring(i));
            System.out.println(line5.substring(i));
            System.out.println(line6.substring(i));
            for (int x = 1; x <= 3; x++)
            {
                System.out.println(line789.substring(i));
            }
            System.out.println(line10.substring(i));
            System.out.println(line11.substring(i));

            // Move "Lose" towards right
            System.out.println(move + line12);
            System.out.println(move + line13);
            System.out.println(move + line14);
            System.out.println(move + line15);
            System.out.println(move + line16);
            System.out.println(move + line17);

            move += " ";
            Thread.sleep(200);
            Main.clearScreen();
        }
        System.out.print(WHITE);
    }


    // This method animates text that indicates the result of the game as a tie between the user and the computer
    public static void tieAnimation() throws InterruptedException
    {
        Main.clearScreen();

        String emptyTop = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"; // 30 lines skipped
        String line1 = "|=======| |======|  ||===|";
        String line2 = "   | |       ||     ||";
        String line3 = "   | |       ||     ||===|";
        String line4 = "   | |       ||     ||";
        String line5 = "   |_|    |======|  ||===|";
        for (int i = 1; i <= 30; i++)
        {
            System.out.println(emptyTop.substring(i));
            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
            System.out.println(line4);
            System.out.println(line5);
            Thread.sleep(200);
            Main.clearScreen();
        }
    }
}