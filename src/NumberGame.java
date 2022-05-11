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

        String introduction = BLUE + "欢迎来到Number Game！";
        introduction += YELLOW + "\n规则: ";
        introduction += BLUE + "\n1️⃣  你和电脑各自会有一串数字，显示为 \"#\"。";
        introduction += "\n2️⃣  每个序列会有10个随机的数字，范围从0到9（包括两者），没有一个数字会重复。";
        introduction += BLUE_BRIGHT + "\n举例：计算机显示的序列为##########，但计算机实际的序列会是9830427165.";
        introduction += BLUE + "\n3️⃣  你将对数字进行运算（共有六个选项：加法、减法、乘法、除法、指数以及模数）。过程如下所示：";
        introduction += BLUE_BRIGHT + "\n第一次操作：在第1个数字和第2个数字之间";
        introduction += "\n第二次操作：在第一次操作的结果和第3个数字之间";
        introduction += "\n第三次操作：在第二次操作的结果和第4个数字之间";
        introduction += "\n……";
        introduction += GREEN + "\n（为确保清晰，受操作影响的数字将会用颜色标注）";
        introduction += BLUE_BRIGHT + "\n小细节：";
        introduction += "\n  - 如果适用，除法的结果将会向下取整。";
        introduction += "\n  - 当除以的数字为0时，结果会是0。";
        introduction += "\n  - 结果大于 " + Integer.MAX_VALUE + " 或小于 " + Integer.MIN_VALUE + " 都会自动转变为0。";
        introduction += BLUE + "\n4️⃣  最后一次操作后，输赢将由以下规则决定：";
        introduction += RED + "\n    ⬇  如果涉及乘法、除法和指数的运算数量大于涉及其它三种的运算数量，则数值最小的结果获胜。";
        introduction += CYAN + "\n    ⬆  如果涉及加法、减法和模数的运算数量大于涉及其它三种的运算数量，则数值最大的结果获胜。" + WHITE;

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
            System.out.println("用户操作结果 --> " + userResult);
            System.out.println("电脑操作结果 --> " + computerResult);
        }
        else if (isErrorPossibleUser == true && isErrorPossibleComp == false)
        {
            System.out.println("用户操作结果 --> " + RED + userResult + " （可能模数/除以的为0，或结果过大/过小）" + WHITE);
            System.out.println("电脑操作结果 --> " + computerResult + WHITE);
        }
        else if (isErrorPossibleComp == true && isErrorPossibleUser == false)
        {
            System.out.println("用户操作结果 --> " + userResult + WHITE);
            System.out.println("电脑操作结果 --> " + RED + computerResult + " （可能模数/除以的为0，或结果过大/过小）" + WHITE);
        }
        else
        {
            System.out.println("用户操作结果 --> " + RED + userResult + " （可能模数/除以的为0，或结果过大/过小）" + WHITE);
            System.out.println("电脑操作结果 --> " + RED + computerResult + " （可能模数/除以的为0，或结果过大/过小）" + WHITE);
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
        result += "\n将要进行的总轮数：" + roundNum;
        result += "\n已游玩的轮数：" + roundsPlayed;
        result += "\n目前用户得分：" + userScore;
        result += "\n目前电脑得分：" + computerScore;
        result += RED + "\n目前涉及乘法、除法和指数的运算数量：" + MDEnum + WHITE;
        result += CYAN + "\n目前涉及加法、减法和模数的运算数量：" + ASMnum + WHITE;

        String userFirstNum = "";
        String userSecondNum = "";
        String compFirstNum = "";
        String compSecondNum = "";

        if (isFirstStage)
        {
            result += "\n目前用户结果：" + userResult;
            result += "\n目前电脑结果：" + computerResult;

            userFirstNum = userSeqHidden.substring(0,1);
            userSecondNum = userSeqHidden.substring(1,2);
            compFirstNum = compSeqHidden.substring(0,1);
            compSecondNum = compSeqHidden.substring(1,2);

            result += "\n目前用户序列：" + PURPLE + userFirstNum + YELLOW + userSecondNum + WHITE + userSeqHidden.substring(2);
            result += "\n目前电脑序列：" + PURPLE + compFirstNum + YELLOW + compSecondNum + WHITE + compSeqHidden.substring(2);
        }
        else
        {
            result += "\n目前用户结果：" + PURPLE + userResult + WHITE;
            result += "\n目前电脑结果：" + PURPLE + computerResult + WHITE;

            int middle = strStatus.indexOf("-");
            int secondNumIndex = Integer.parseInt(strStatus.substring(middle + 1));

            userFirstNum = "" + userResult;
            userSecondNum = userSeqHidden.substring(secondNumIndex, secondNumIndex + 1);
            compFirstNum = "" + computerResult;
            compSecondNum = compSeqHidden.substring(secondNumIndex, secondNumIndex + 1);

            result += "\n目前用户序列（已更新）：" + userSeqHidden.substring(0, secondNumIndex) + YELLOW + userSecondNum + WHITE + userSeqHidden.substring(secondNumIndex + 1);
            result += "\n目前电脑序列（已更新）：" + compSeqHidden.substring(0, secondNumIndex) + YELLOW + compSecondNum + WHITE + compSeqHidden.substring(secondNumIndex + 1);
        }

        result += "\n" + GREEN + "当前用户操作：" + PURPLE + userFirstNum + GREEN + " ? " + YELLOW + userSecondNum + WHITE;
        result += "\n" + GREEN + "当前电脑操作：" + PURPLE + compFirstNum + GREEN + " ? " + YELLOW + compSecondNum;


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
        System.out.println("回合总结：");
        System.out.println("你的序列为 " + userSeq);
        System.out.println("电脑的序列为 " + computerSeq);
        if (MDEnum > ASMnum)
        {
            System.out.println(RED + "更多的操作涉及到了乘法、除法和指数。结果数值最小的一方将获胜。");
            if (userResult < computerResult)
            {
                System.out.println("你的结果 " + userResult + " 要比电脑的结果 " + computerResult + " 小，你赢了这一回合！");
                userScore ++;
            }
            else if (computerResult < userResult)
            {
                System.out.println("电脑的结果 " + computerResult + " 要比你的结果 " + userResult + " 小，电脑赢了这一回合！");
                computerScore ++;
            }
            else
            {
                System.out.println("天啊撸，双方的结果都是 " + userResult + "，谁都没赢！");
            }
        }
        else
        {
            System.out.println(CYAN + "更多的操作涉及到了加法、减法和模数。结果数值最大的一方将获胜。");
            if (userResult > computerResult)
            {
                System.out.println("你的结果 " + userResult + " 要比电脑的结果 " + computerResult + " 大，你赢了这一回合！");
                userScore ++;
            }
            else if (computerResult > userResult)
            {
                System.out.println("电脑的结果 " + computerResult + " 要比你的结果 " + userResult + "大，电脑赢了这一回合！");
                computerScore ++;
            }
            else
            {
                System.out.println("天啊撸，双方的结果都是 " + userResult + "，谁都没赢！");
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
        System.out.println("游戏结束总结：");
        if (userScore > computerScore)
        {
            System.out.println(userName + " 的分数 " + userScore + " 高于电脑的分数 " + computerScore + "，" + userName + " 赢了这场游戏！");
        }
        else if (computerScore > userScore)
        {
            System.out.println("电脑的分数 " + computerScore + " 高于 " + userName + " 的分数 " + userScore + "，电脑赢了这场游戏！");
        }
        else
        {
            System.out.println("令人毛骨悚然的平局，谁都没有赢，这一切的意义又何在 0_0");
        }
        System.out.println("🔆🔆🔆🔆🔆🔆🔆🔆🔆🔆");
    }
}