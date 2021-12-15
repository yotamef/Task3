import java.util.Scanner;

public class Exercise5 {

    public static final int ABC_LETTERS = 26;
    public static final int ASCII_GAP = 97;

    public static int indexOfMax(int [] arr) {
        int indexMax = 0, max = arr[0];
        for (int i=1; i<arr.length; i++) {
            if (arr[i] > max) {
                indexMax = i;
                max = arr[i];
            }
        }
        return indexMax;
    }


    public static String code() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert text");
        String code = scanner.nextLine();
        code = code.toLowerCase();

        int [] countChars = new int [ABC_LETTERS];
        for (int i=0; i<code.length(); i++) {
            if (code.charAt(i) != ' ') {
                int currentChar = code.charAt(i);
                countChars[currentChar - ASCII_GAP]++;
            }
        }

        int indexMost = indexOfMax(countChars);
        countChars[indexMost] = -1;
        char most = (char)(indexMost +ASCII_GAP);
        char secondMost = (char)(indexOfMax(countChars)+ASCII_GAP);

        char [] charCode = new char[code.length()];
        for (int i=0; i<charCode.length; i++) {
            if (code.charAt(i) == most)
                charCode[i] = secondMost;
            else if (code.charAt(i) == secondMost)
                charCode[i] = most;
            else
                charCode[i] = code.charAt(i);
        }

        String codeToReturn = "";
        for (int i=0; i<charCode.length; i++) {
            codeToReturn = codeToReturn + charCode[i];
        }
        return codeToReturn;
    }
}
