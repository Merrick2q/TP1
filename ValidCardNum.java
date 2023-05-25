package TP1;

import javax.swing.*;

public class ValidCardNum {
    public static void main(String[] args) {
        //Asking for a credit / debit card number using swing
        Long input = Long.valueOf(JOptionPane.showInputDialog(null,
                "Enter a credit card / debit card number as long integer, \nQUIT to end:\n",
                "Validation of Credit Card / Debit Card Numbers", JOptionPane.PLAIN_MESSAGE));
        //Changing the number into a string
        String strinput = Long.toString(input);
        //To always run the program unless the user type "quit"
        while (!strinput.equalsIgnoreCase("quit")) {
            //Showing the result
            JOptionPane.showMessageDialog(null,
                    "The number " + input + " is " + ((isValid(input)) ? "valid" : "invalid")
                    , "Validation of Credit Card / Debit Card Numbers", JOptionPane.PLAIN_MESSAGE);
            //Getting a new credit / debit card number
            input = Long.valueOf(JOptionPane.showInputDialog(null,
                    "Enter a credit card / debit card number as long integer, \nQUIT to end:\n",
                    "Validation of Credit Card / Debit Card Numbers", JOptionPane.PLAIN_MESSAGE));
        }
    }
    public static boolean isValid(long number) { //Checking whether the number is valid or not
        return (getSize(number) >= 13 && getSize(number) <= 16) /* if the number is between 13 to 16 */
                && (prefixMatched(number, 4) || prefixMatched(number, 5) || prefixMatched(number, 37) || prefixMatched(number, 6))
                /* whether the first number is 4, 5, 37, or 6 */
                && ((sumOfDoubleEvenPlace(number) + sum0fOddPlace(number)) % 10 == 0);
                /* if adding the result of doubling the digit and the result of adding the odd placed digit and modulo it by 10 equals to 0 */
                // if all the requirement is fulfilled it will return true
    }
    public static int sumOfDoubleEvenPlace(long number) { //Multiplying all digit with 2
        int multiply2 = 0;
        String num = Long.toString(number);
        //for loop to get each digit
        for (int i = getSize(number) - 2; i >= 0; i -= 2) {
            multiply2 += getDigit(Integer.parseInt(String.valueOf(num.charAt(i))) * 2);
        }
        return multiply2;
    }
    public static int getDigit(int number) { //Adding the two digit if the result of multiplying by 2 > 10
        if (number < 9){
            return number; //return the number if it is already < 10
        } else {
            return number / 10 + number % 10; //return the sum of the two digit
        }
    }
    public static int sum0fOddPlace(long number) { //Adding all digit in odd places
        int summary = 0;
        String num = Long.toString(number);
        //for loop to get each digit
        for (int i = getSize(number) - 1; i >= 0; i -= 2) {
            summary += Integer.parseInt(String.valueOf(num.charAt(i)));
        }
        return summary;
    }
    public static boolean prefixMatched(long number, int d) { //Matching the first digit with 4, 5, 37, 6
        boolean match = getPrefix(number, getSize(d)) == d;
        return match;
    }
    public static int getSize(long d) { //Getting the length of the number
        String num = Long.toString(d);
        return num.length();
    }
    public static long getPrefix(long number, int k) { //Getting the first digit
        String num = Long.toString(number);
        long P= Long.parseLong(num.substring(0, k));
        if (getSize(number) > k) {
            return P;
        } else { //if the length of number is less than k
            return number;
        }
    }
}
