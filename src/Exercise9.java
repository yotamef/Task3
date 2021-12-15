import java.util.Scanner;

public class Exercise9 {
    public static final int ASCII_GAP = 48;

    public static int digitAtIndex(int num, int index) {
        String numString = num + "";
        int indexDigit = numString.charAt(index)-ASCII_GAP;
        return indexDigit;
    }
    public static boolean appearTwice(int code) {
        boolean flag = false;
        for (int i=0; i<4; i++) {
            for (int j=i+1; j<4; j++) {
                if (digitAtIndex(code,i) == digitAtIndex(code,j))
                    flag = true;
            }
        }
        return flag;
    }

    public static boolean goodCode(int code) {
        boolean goodCode = true;
        for (int i=0; i<4; i++) {
            if (digitAtIndex(code, i) > 6 || digitAtIndex(code, i)<1)
                goodCode = false;
        }
        if (appearTwice(code))
            goodCode = false;

        String code2 = code+"";
        if (code2.length() != 4)
            goodCode = false;

        return goodCode;
    }
    public static int randomCode() {
        int code;
        boolean goodCode;
        do {
            code = (int)(Math.random()*9000+1000);
            goodCode = goodCode(code);
        } while (!goodCode);

        return code;
    }
    public static int greatHits(int code, int userCode) {
        int count=0;
        for (int i=0; i<4; i++) {
            if (digitAtIndex(code,i) == digitAtIndex(userCode,i))
                count++;
        }
        return count;
    }
    public static int semiHits(int code, int userCode) {
        int count = 0;
        for (int i=0; i<4; i++) {
            for (int j = 0; j<4; j++) {
                if (digitAtIndex(code,i) == digitAtIndex(userCode,j) && j!=i) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int code = randomCode();
        System.out.println(code);
        int userCode = 0;
        int guesses=0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pick level: easy/mid/hard or surprise");
        String level = scanner.nextLine();
        switch (level) {
            case "easy":
                guesses = 20;
                break;
            case "mid":
                guesses = 15;
                break;
            case "hard":
                guesses = 10;
                break;
            case "surprise":
                guesses = (int)(Math.random()*21+5);
                break;
        }
        if (guesses==0) {
            System.out.println("Illegal level");
        }

        int greatHits=0, semiHits=0;
        for (int i=0; i<guesses; i++) {
            System.out.println("Guess code");
            userCode = scanner.nextInt();
            if (appearTwice(userCode)) {
                System.out.println("You have a digit that appears twice, minus two guesses");
                i = i+1;
                continue;
            }
            if (!goodCode(userCode)) {
                System.out.println("Illegal code");
                continue;
            }
            greatHits = greatHits(code,userCode);
            semiHits = semiHits(code, userCode);
            System.out.println("You have " + greatHits+ " greatHits and "+ semiHits+ " semiHits");
            if (greatHits==4) {
                System.out.println("You won");
                break;
            }
        }
        if (greatHits!=4)
            System.out.println("you lost");
    }
}
