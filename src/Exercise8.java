import java.util.Scanner;

public class Exercise8 {

    public static String[] collectStrings() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String willBeArray = "";
        int counter = 0;
        do {
            System.out.println("Insert string");
            userInput = scanner.nextLine();
            willBeArray = willBeArray + userInput + ",";
            counter++;
        } while (userInput.length()>=4);

        String [] arr = new String[counter];
        int j=0;
        String word = "";
        for (int i=0; i<willBeArray.length(); i++) {
            if (willBeArray.charAt(i) == ',') {
                arr[j] = word;
                word = "";
                j++;
            }
            else {
                word = word + willBeArray.charAt(i);
            }
        }
        return arr;
    }

    public static int numOfSubs(int length) {
        int sum = 0;
        for (int i=length; i>1; i--) {
            sum = sum + (length-i+1);
        }
        return sum;
    }

    public static String [] allSubStrings(String str) {
        String [] subStrings = new String[numOfSubs(str.length())];
        int k = 0;
        for (int i=2; i<=str.length(); i++) {
            for (int j=0; j<=str.length()-i; j++) {
                subStrings[k] = str.substring(j, j+i);
                k++;
            }
        }
        return subStrings;
    }
    public static int appearsInArr(String [] string, String str) {
        int count = 0;
        for (int i=0; i<string.length; i++) {
            if (string[i].equals(str))
                count++;
        }
        return count;
    }

    public static String mostPopularSub(String [] strings) {
        int sumOfAllSubs = 0;
        for (int i=0; i<strings.length; i++) {
            sumOfAllSubs = sumOfAllSubs + allSubStrings(strings[i]).length;
        }
        String [] allSubs = new String [sumOfAllSubs];
        for (int i=0; i<strings.length; i++) {
            for (int j=0; j<allSubStrings(strings[i]).length; j++) {
                allSubs[j] = allSubStrings(strings[i])[j];
            }
        }

        int maxAppears = appearsInArr(strings,allSubs[0]), currentAppears=0;
        String popularStr = allSubs[0];
        for (int i=1; i<allSubs.length; i++) {
            currentAppears = appearsInArr(strings, allSubs[i]);
            if (currentAppears>maxAppears) {
                maxAppears = currentAppears;
                popularStr = allSubs[i];
            }

        }

        return popularStr;
    }

    public static void main(String[] args) {
        String [] arr = {"java", "abc", "a"};
        System.out.println(mostPopularSub(arr));
    }
}
