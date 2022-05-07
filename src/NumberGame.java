public class NumberGame
{
    // instance variables
    private String userName;
    private int roundNum;
    private String userDigits;
    private String userSeq;
    private int userResult; // a running result (updates after each calculation)
    private int userScore;
    private String computerSeq;
    private int computerResult; // a running result (updates after each calculation)
    private int computerScore;
    private int ASMnum; // track the number of operations for addition, subtraction, and modulus
    private int MDEnum; // track the number of operations for multiplication, division, and exponent
    private String userSeqHidden; // a running sequence that will be updated after calculations involving # (eventually match up with userSeq)
    private String compSeqHidden; // a running sequence that will be updated after calculations involving # (eventually match up with compSeq)
    private boolean isFirstStage; // true if the game should be performing the operation between the first two numbers within the sequence, false otherwise
    private String strStatus;
    // indicate the current two values that should be used for the upcoming mathematical operation.
    // Examples: "0-1", "r-2", "r-3" ...
    // numbers represent the index of a value (from left to right, start counting from 0)
    // r = current result (userResult or computerResult) after the previous calculation(s)
    private int roundsPlayed;


    //Constructors
    /**
     * One of the two constructors for the NumberGame class.
     * Initializes the instance variable "userName" to the argument provided for the parameter "userName".
     * Initializes the instance variable "userDigits" to the argument provided for the parameter "userDigits".
     * Initializes all int instance variables besides "roundNum" to 0.
     * Initializes the instance variable "roundNum" to 3.
     * Initializes the instance variable "userSeq" and "computerSeq" to randomly-generated sequences (each has 10 characters/numbers; ranging from 0 to 9 inclusive; no repeated characters).
     * Initializes the instance variable "userSeqHidden" to a sequence of 6 "#"s and 4 unique numbers (a total of 10 characters).
     * Initializes the instance variable "compSeqHidden" to a sequence of 10 "#"s.
     * Initializes the instance variable "isFirstStage" to a boolean literal of "true".
     * Initializes the instance variable "strStatus" to a String literal of "0-1".
     *
     * @param userName The name of the user; its argument will be assigned to the instance variable "userName"
     * @param userDigits The four-digit number (as a String literal) whose individual digits will be visible in the instance variable "userSeqHidden"; its argument will be assigned to the instance variable "userDigit"
     */
    public NumberGame(String userName, String userDigits)
    {
        this.userName = userName;
        this.userDigits = userDigits;
        this.roundNum = 3;
        this.userScore = 0;
        this.computerScore = 0;
        this.ASMnum = 0;
        this.MDEnum = 0;
        this.userSeq = generateRandomSequence();
        this.computerSeq = generateRandomSequence();
        this.userSeqHidden = encryptUserSeq();
        this.compSeqHidden = "##########";
        this.userResult = 0;
        this.computerResult = 0;
        this.isFirstStage = true;
        this.strStatus = "0-1";
        this.roundsPlayed = 0;
    }
    /**
     * One of the two constructors for the NumberGame class.
     * Initializes the instance variable "userName" to the argument provided for the parameter "userName".
     * Initializes the instance variable "userDigits" to the argument provided for the parameter "userDigits".
     * Initializes all int instance variables besides "roundNum" to 0.
     * Initializes the instance variable "roundNum" to the argument provided for the parameter "roundNum"
     * Initializes the instance variable "userSeq" and "computerSeq" to randomly-generated sequences (each has 10 characters/numbers; ranging from 0 to 9 inclusive; no repetition)
     * Initializes the instance variable "userSeqHidden" to a sequence of 6 "#"s and 4 unique numbers (a total of 10 characters)
     * Initializes the instance variable "compSeqHidden" to a sequence of 10 "#"s.
     * Initializes the instance variable "isFirstStage" to a boolean literal of "true";
     * Initializes the instance variable "strStatus" to a String literal of "0-1"
     *
     * @param userName The name of the user; its argument will be assigned to the instance variable "userName"
     * @param userDigits The four-digit number (as a String literal) whose individual digits will be visible in the instance variable "userSeqHidden"; its argument will be assigned to the instance variable "userDigit"
     * @param roundNum The number of round(s) that the game will be played; its argument will be assigned to the instance variable "roundNum"
     */
    public NumberGame(String userName, String userDigits, int roundNum)
    {
        this.userName = userName;
        this.userDigits = userDigits;
        this.roundNum = roundNum;
        this.userScore = 0;
        this.computerScore = 0;
        this.ASMnum = 0;
        this.MDEnum = 0;
        this.userSeq = generateRandomSequence();
        this.computerSeq = generateRandomSequence();
        this.userSeqHidden = encryptUserSeq();
        this.compSeqHidden = "##########";
        this.userResult = 0;
        this.computerResult = 0;
        this.isFirstStage = true;
        this.strStatus = "0-1";
        this.roundsPlayed = 0;
    }



    /* Methods */

    // This method prints statement that introduce the user to the rules of the game.
    /**
     * Prints statements that introduces the rules of the game
     */
    public static void introduce()
    {
        final String GREEN = "\u001b[32m"; // searched
        final String RED = "\u001B[31m"; // provided
        final String CYAN = "\u001B[36m"; // provided
        final String YELLOW = "\u001B[33m"; // provided
        final String WHITE = "\u001B[37m"; // provided
        final String BLUE = "\033[0;34m"; // provided
        final String BLUE_BRIGHT = "\033[0;94m"; // provided

        String introduction = BLUE + "Welcome to Number Game!";
        introduction += YELLOW + "\nGeneral Rules: ";
        introduction += BLUE + "\n1️⃣  You and the computer each will have a sequence of numbers shown as \"#\"s.";
        introduction += "\n2️⃣  Each sequence has 10 random numbers, ranges from 0 to 9 (inclusive of both), with no digit repeated.";
        introduction += BLUE_BRIGHT + "\nExample: Computer's shown sequence is ##########, while computer's actual sequence is 9830427165.";
        introduction += BLUE + "\n3️⃣  You will perform mathematical operations with the numbers (six options: addition, subtraction, multiplication, division, exponent, and modulus).  Procedures shown below: ";
        introduction += BLUE_BRIGHT + "\nFirst operation: between 1st number and 2nd number";
        introduction += "\nSecond operation: between the result from first operation and 3rd number";
        introduction += "\nThird operation: between the result from the second operation and 4th number";
        introduction += "\n...";
        introduction += GREEN + "\n(The numbers subjected to an operation will be marked with colors to ensure clarity)";
        introduction += BLUE_BRIGHT + "\nMinor details: ";
        introduction += "\n  - Results from divisions are rounded down if applicable.";
        introduction += "\n  - Division by 0 (which is invalid) will result in a 0.";
        introduction += "\n  - A result that is greater than " + Integer.MAX_VALUE + " or less than " + Integer.MIN_VALUE + " will automatically becomes 0.";
        introduction += BLUE + "\n4️⃣  After the last operation, winning or losing will be determined by the following rules: ";
        introduction += RED + "\n    ⬇  If the number of operations involving multiplication, division, and exponent is larger than the number of operations involving the other three, the final result that has a lesser value wins.";
        introduction += CYAN + "\n    ⬆  If the number of operations involving addition, subtraction, and modulus is larger than the number of operations involving the other three, the final result that has a greater value wins." + WHITE;

        System.out.println(introduction);
    }

    // This method returns a random String literal that represent a sequence of numbers (10 numbers in total, 0-9 inclusive, no number repeated)
    private String generateRandomSequence()
    {
        String str = "";
        while (str.length() < 10)
        {
            int randomNum = (int) (Math.random() * 10);
            while (str.indexOf("" + randomNum) != -1 && str.length() != 10)
            {
                randomNum = (int) (Math.random() * 10);
                if (str.indexOf("" + randomNum) == -1)
                {
                    str += randomNum;
                }
            }
            if (str.indexOf("" + randomNum) == -1)
            {
                str += randomNum;
            }
        }
        return str;
    }

    // This method return a String literal that replaces all digits in "userSeq" with #s BESIDES the four digits that the user inputs as the instance variable "userDigits"
    private String encryptUserSeq()
    {
        String result = "";
        String visibleDigits = userDigits;
        for (int i = 0; i < userSeq.length(); i++)
        {
            String num = userSeq.substring(i, i+1);
            for (int x = 0; x < visibleDigits.length(); x++)
            {
                String digit = visibleDigits.substring(x, x+1);
                if (digit.equals(num))
                {
                    result += num;
                }
            }
            if (result.length() == i)
            {
                result += "#";
            }
        }
        return result;
    }

    // This method updates the version of the computer's current hidden sequence in which the first # in the computer's current hidden sequence becomes visible as a number
    private void updateCompSeqHidden()
    {
        int i = compSeqHidden.indexOf("#");
        compSeqHidden = compSeqHidden.substring(0,i) + computerSeq.substring(i, i+1) + compSeqHidden.substring(i+1);
    }

    // This method updates the version of the user's current hidden sequence based on the argument for "index"; if the user's hidden sequence contains a # at that index, the sequence will be updated with that # replaced by the actual number (otherwise, do nothing)
    private void updateUserSeqHidden(int index)
    {
        String str = userSeqHidden.substring(index, index+1);
        if (str.equals("#"))
        {
            String actualNum = userSeq.substring(index, index+1);
            userSeqHidden = userSeqHidden.substring(0,index) + actualNum + userSeqHidden.substring(index+1);
        }
    }

    // This method changes the value of the instance variable "strStatus" to the corresponding next value (possible value for status IN ORDER includes: "0-1", "r-2", "r-3" ... "r-9")
    private void updateStrStatus()
    {
        int middle = strStatus.indexOf("-");
        String first = strStatus.substring(0, middle);
        String second = strStatus.substring(middle+1);
        int newSecond = Integer.parseInt(second) + 1;
        String newFirst = first;
        if (first.equals("1"))
        {
            newFirst = "r";
        }
        // reset after one round is completed
        if (newSecond == 10)
        {
            newFirst = "0";
            newSecond = 1;
        }
        strStatus = newFirst + "-" + newSecond;
    }

    // This method returns the literal of the instance variable "strStatus"
    /**
     * An accessor method that get the literal of the instance variable "strStatus"
     *
     * @return The String literal of the instance variable "strStatus"
     */
    public String getStrStatus()
    {
        return strStatus;
    }

    // Based on the user's input for the parameter "operation" and the computer-tracked status for "which two numbers will be used for performing the operation", play one part of the game and update things such as the results of both players, the status of the game, the sequences of both players with newly-revealed # if any, etc.
    /**
     * Plays one part of the game and updates the literals of different instance variables
     *
     * @param operation The symbol (as a String literal) for the corresponding mathematical operation
     */
    public void play(String operation)
    {
        final String RED = "\u001B[31m"; // provided
        final String WHITE = "\u001B[37m"; // provided
        int userFirstNum = 0;
        int userSecondNum = 0;
        int compFirstNum = 0;
        int compSecondNum = 0;
        boolean isErrorPossibleUser = false;
        boolean isErrorPossibleComp = false;

        if (isFirstStage)
        {
            updateUserSeqHidden(0);
            updateUserSeqHidden(1);
            userFirstNum = Integer.parseInt(userSeq.substring(0,1));
            userSecondNum = Integer.parseInt(userSeq.substring(1,2));
            compFirstNum = Integer.parseInt(computerSeq.substring(0,1));
            compSecondNum = Integer.parseInt(computerSeq.substring(1,2));
        }
        else
        {
            int middle = strStatus.indexOf("-");
            int secondNumIndex = Integer.parseInt(strStatus.substring(middle + 1));
            updateUserSeqHidden(secondNumIndex);

            userFirstNum = userResult;
            userSecondNum = Integer.parseInt(userSeq.substring(secondNumIndex, secondNumIndex + 1));
            compFirstNum = computerResult;
            compSecondNum = Integer.parseInt(computerSeq.substring(secondNumIndex, secondNumIndex + 1));
        }

        if (operation.equals("+"))
        {
            ASMnum ++;
            userResult = userFirstNum + userSecondNum;
            computerResult = compFirstNum + compSecondNum;
        }
        else if (operation.equals("-"))
        {
            ASMnum ++;
            userResult = userFirstNum - userSecondNum;
            computerResult = compFirstNum - compSecondNum;
        }
        else if (operation.equals("*"))
        {
            MDEnum ++;
            userResult = userFirstNum * userSecondNum;
            computerResult = compFirstNum * compSecondNum;
        }
        else if (operation.equals("/"))
        {
            MDEnum ++;
            if (userSecondNum == 0)
            {
                userResult = 0;
                isErrorPossibleUser = true;
            }
            else
            {
                userResult = userFirstNum / userSecondNum;
            }
            if (compSecondNum == 0)
            {
                computerResult = 0;
                isErrorPossibleComp = true;
            }
            else
            {
                computerResult = compFirstNum / compSecondNum;
            }
        }
        else if (operation.equals("^"))
        {
            MDEnum ++;
            double userResultTemp = Math.pow((double) userFirstNum, (double) userSecondNum);
            double computerResultTemp = Math.pow((double) compFirstNum, (double) compSecondNum);
            if (userResultTemp > Integer.MAX_VALUE || userResultTemp < Integer.MIN_VALUE)
            {
                userResult = 0;
                isErrorPossibleUser = true;
            }
            else
            {
                userResult = (int) userResultTemp;
            }
            if (computerResultTemp > Integer.MAX_VALUE || computerResultTemp < Integer.MIN_VALUE)
            {
                computerResult = 0;
                isErrorPossibleComp = true;
            }
            else
            {
                computerResult = (int) computerResultTemp;
            }
        }
        else if (operation.equals("mod"))
        {
            ASMnum ++;
            if (userSecondNum == 0)
            {
                userResult = 0;
                isErrorPossibleUser = true;
            }
            else
            {
                userResult = userFirstNum % userSecondNum;
            }
            if (compSecondNum == 0)
            {
                computerResult = 0;
                isErrorPossibleComp = true;
            }
            else
            {
                computerResult = compFirstNum % compSecondNum;
            }
        }

        System.out.println("🍥🍥🍥🍥🍥🍥🍥🍥🍥🍥");
        if (isErrorPossibleUser == false && isErrorPossibleComp == false)
        {
            System.out.println("User operation result --> " + userResult);
            System.out.println("Computer operation result --> " + computerResult);
        }
        else if (isErrorPossibleUser == true && isErrorPossibleComp == false)
        {
            System.out.println("User operation result --> " + RED + userResult + " (Possible modulus/division by 0, or result being excessively large/small)" + WHITE);
            System.out.println("Computer operation result --> " + computerResult + WHITE);
        }
        else if (isErrorPossibleComp == true && isErrorPossibleUser == false)
        {
            System.out.println("User operation result --> " + userResult + WHITE);
            System.out.println("Computer operation result --> " + RED + computerResult + " (Possible modulus/division by 0, or result being excessively large/small)" + WHITE);
        }
        else
        {
            System.out.println("User operation result --> " + RED + userResult + " (Possible modulus/division by 0, or result being excessively large/small)" + WHITE);
            System.out.println("Computer operation result --> " + RED + computerResult + " (Possible modulus/division by 0, or result being excessively large/small)" + WHITE);
        }
        System.out.println("🍥🍥🍥🍥🍥🍥🍥🍥🍥🍥");
        isFirstStage = false;
        updateStrStatus();
        updateCompSeqHidden();
    }

    // This method will be called implicitly when printing the reference variable, showing the game status to the user (return a String literal that includes information such as the current scores, the current results, the current sequences, etc.)
    /**
     * Returns a report for the current stage/status of the game.
     *
     * @return A report for the current stage/status of the game.
     */
    public String toString()
    {
        // provided colors
        final String GREEN = "\u001b[32m";
        final String WHITE = "\u001B[37m";
        final String RED = "\u001B[31m";
        final String CYAN = "\u001B[36m";
        final String YELLOW = "\u001B[33m";
        final String PURPLE = "\033[0;35m";

        String result = "";
        result += "\nTotal number of rounds that will be played: " + roundNum;
        result += "\nNumber of round(s) played: " + roundsPlayed;
        result += "\nCurrent user score: " + userScore;
        result += "\nCurrent computer score: " + computerScore;
        result += RED + "\nCurrent number of operations that involved multiplication, division, and exponent: " + MDEnum + WHITE;
        result += CYAN + "\nCurrent number of operations that involved addition, subtraction, and modulus: " + ASMnum + WHITE;

        String userFirstNum = "";
        String userSecondNum = "";
        String compFirstNum = "";
        String compSecondNum = "";

        if (isFirstStage)
        {
            result += "\nCurrent user result: " + userResult;
            result += "\nCurrent computer result: " + computerResult;

            userFirstNum = userSeqHidden.substring(0,1);
            userSecondNum = userSeqHidden.substring(1,2);
            compFirstNum = compSeqHidden.substring(0,1);
            compSecondNum = compSeqHidden.substring(1,2);

            result += "\nCurrent user sequence: " + PURPLE + userFirstNum + YELLOW + userSecondNum + WHITE + userSeqHidden.substring(2);
            result += "\nCurrent computer sequence: " + PURPLE + compFirstNum + YELLOW + compSecondNum + WHITE + compSeqHidden.substring(2);
        }
        else
        {
            result += "\nCurrent user result: " + PURPLE + userResult + WHITE;
            result += "\nCurrent computer result: " + PURPLE + computerResult + WHITE;

            int middle = strStatus.indexOf("-");
            int secondNumIndex = Integer.parseInt(strStatus.substring(middle + 1));

            userFirstNum = "" + userResult;
            userSecondNum = userSeqHidden.substring(secondNumIndex, secondNumIndex + 1);
            compFirstNum = "" + computerResult;
            compSecondNum = compSeqHidden.substring(secondNumIndex, secondNumIndex + 1);

            result += "\nCurrent user sequence (updated!): " + userSeqHidden.substring(0, secondNumIndex) + YELLOW + userSecondNum + WHITE + userSeqHidden.substring(secondNumIndex + 1);
            result += "\nCurrent computer sequence (updated!): " + compSeqHidden.substring(0, secondNumIndex) + YELLOW + compSecondNum + WHITE + compSeqHidden.substring(secondNumIndex + 1);
        }

        result += "\n" + GREEN + "Current user operation: " + PURPLE + userFirstNum + GREEN + " ? " + YELLOW + userSecondNum + WHITE;
        result += "\n" + GREEN + "Current computer operation: " + PURPLE + compFirstNum + GREEN + " ? " + YELLOW + compSecondNum;


        return result + WHITE;
    }

    // This method prints statements that state the result of one round (win/lose/tie)
    /**
     * Concludes the result of one round of the game (win/lose/tie)
     */
    public void concludeRound()
    {
        // provided colors
        final String RED = "\u001B[31m";
        final String CYAN = "\u001B[36m";
        final String WHITE = "\u001B[37m";

        System.out.println("🔔🔔🔔🔔🔔🔔🔔🔔🔔🔔");
        System.out.println("Round-Over Conclusion: ");
        System.out.println("Your sequence is " + userSeq);
        System.out.println("Computer's sequence is " + computerSeq);
        if (MDEnum > ASMnum)
        {
            System.out.println(RED + "More operations involved multiplication, division, and exponent.  The result with a less value will win.");
            if (userResult < computerResult)
            {
                System.out.println("Your result of " + userResult + " is less than the computer's result of " + computerResult + ", you win this round!");
                userScore ++;
            }
            else if (computerResult < userResult)
            {
                System.out.println("The computer's result of " + computerResult + " is less than your result of " + userResult + ", the computer wins this round!");
                computerScore ++;
            }
            else
            {
                System.out.println("Wow, both results are " + userResult + ", no one wins this round!");
            }
        }
        else
        {
            System.out.println(CYAN + "More operations involved addition, subtraction, and modulus.  The result with a greater value will win.");
            if (userResult > computerResult)
            {
                System.out.println("Your result of " + userResult + " is greater than the computer's result of " + computerResult + ", you win this round!");
                userScore ++;
            }
            else if (computerResult > userResult)
            {
                System.out.println("The computer's result of " + computerResult + " is greater than your result of " + userResult + ", the computer wins this round!");
                computerScore ++;
            }
            else
            {
                System.out.println("Wow, both results are " + userResult + ", no one wins this round!");
            }
        }
        System.out.println(WHITE + "🔔🔔🔔🔔🔔🔔🔔🔔🔔🔔");
        // reset certain variables after one round
        ASMnum = 0;
        MDEnum = 0;
        userSeq = generateRandomSequence();
        computerSeq = generateRandomSequence();
        userSeqHidden = encryptUserSeq();
        compSeqHidden = "##########";
        userResult = 0;
        computerResult = 0;
        isFirstStage = true;
        strStatus = "0-1";
        roundsPlayed ++;
    }

    // This method prints statements that state the result of the game (win/lose/tie) after all the rounds are played
    /**
     * Runs text animation based on the result of the entire game after all the rounds are played (win/lose/tie) & Concludes the result as who wins
     */
    public void concludeGame() throws InterruptedException
    {
        if (userScore > computerScore)
        {
            Animation.userWinAnimation();
        }
        else if (computerScore > userScore)
        {
            Animation.computerWinAnimation();
        }
        else
        {
            Animation.tieAnimation();
        }

        System.out.println("🔆🔆🔆🔆🔆🔆🔆🔆🔆🔆");
        System.out.println("Game-Over Conclusion: ");
        if (userScore > computerScore)
        {
            System.out.println(userName + "'s score of " + userScore + " is greater than the computer's score of " + computerScore + ", " + userName + " wins the game!");
        }
        else if (computerScore > userScore)
        {
            System.out.println("The computer's score of " + computerScore + " is greater than " + userName + "'s score of " + userScore + ", the computer wins the game!");
        }
        else
        {
            System.out.println("A creepy tie, no one wins the game 0_0");
        }
        System.out.println("🔆🔆🔆🔆🔆🔆🔆🔆🔆🔆");
    }
}