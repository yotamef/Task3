public class Exercise1 {

    public static boolean alternate(int num) {
        if (num>0) {
            String strNum = "";
            while (num != 0) {
                strNum = num % 10 + strNum;
                num = num / 10;
            }

            boolean alternate = true;
            int a, b;
            for (int i = 0; i < strNum.length() - 1; i++) {
                a = strNum.charAt(i);
                b = strNum.charAt(i + 1);
                if ((a + b) % 2 != 1)
                    alternate = false;
            }
            return alternate;
        }
        else {
            return false;
        }
    }

    public static int sumDigits(int num) {
        int sum=0;
        while (num!=0) {
            sum = sum + num%10;
            num = num/10;
        }
        return sum;
    }

    public static int indexOfAlternate(int [] arr) {
        int indexOfMinimum = -1, sumOfMin=0;
        for (int i=0; i<arr.length; i++) {
            if (alternate(arr[i])) {
                sumOfMin = sumDigits(arr[i]);
                break;
            }
        }
        for (int i=0; i<arr.length; i++) {
            if (alternate(arr[i])) {
                int sumDigits = sumDigits(arr[i]);
                if (sumDigits<=sumOfMin) {
                    indexOfMinimum = i;
                    sumOfMin = sumDigits;
                }
            }
        }
        return indexOfMinimum;
    }
}
