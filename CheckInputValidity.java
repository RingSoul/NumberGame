public class CheckInputValidity
{
    /* No instance variables */
    /* No/Empty constructor */
    public CheckInputValidity()
    {
        /* Empty */
    }

    /* Method */
    //This method checks if the user has entered the desired input for their preference on the number of gaming-rounds (d/c).  True requirement: must be "d" or "c"
    public static boolean checkPreference(String preference)
    {
        boolean isDesired = true;
        // check if preference is "d" or "c"
        if (!(preference.equals("d") || preference.equals("c")))
        {
            isDesired = false;
        }
        return isDesired;
    }


    // This method checks if the user has entered the desired four-digit number prior to the game (true requirement: length = 4; no repeated digit; characters are numbers)
    public static boolean checkFourDigitNum(String fourDigitNum)
    {
        // check length = 4
        boolean isFour = (fourDigitNum.length() == 4);
        boolean isDiverse = true;
        boolean areNumbers = true;
        for (int i = 0; i < fourDigitNum.length(); i++)
        {
            // check if characters are numbers
            String digit = fourDigitNum.substring(i, i+1);
            if (!(digit.equals("0") || digit.equals("1") || digit.equals("2") || digit.equals("3") || digit.equals("4") || digit.equals("5") || digit.equals("6") || digit.equals("7") || digit.equals("8") || digit.equals("9")))
            {
                areNumbers = false;
            }
            for (int x = i + 1; x < fourDigitNum.length(); x++)
            {
                // check if digits are repeated
                String check = fourDigitNum.substring(x, x+1);
                if (digit.equals(check))
                {
                    isDiverse = false;
                }
            }
        }
        return isFour && isDiverse && areNumbers;
    }


    // This method checks if the user has entered the desired symbols for operations (true requirement: must be one of the six symbols)
    public static boolean checkOperation(String operation)
    {
        boolean isValid = true;
        // check if operation is one of the six desired ones
        if (!(operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/") || operation.equals("^") || operation.equals("mod")))
        {
            isValid = false;
        }
        return isValid;
    }
}