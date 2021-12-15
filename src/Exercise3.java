public class Exercise3 {

    public static int[] factors(int num) {
        int num2 = num, count = 0;
        for (int i = 2; num != 1; i++) {
            do {
                if (num%i == 0) {
                    count++;
                    num = num/i;
                }
            } while (num % i==0);
        }
        int [] arr = new int [count];
        int j=0;
        for (int i=2; num2 != 1; i++) {
            do {
                if (num2%i == 0) {
                    arr[j] = i;
                    j++;
                    num2 = num2/i;
                }
            } while (num2 % i==0);
        }
        return arr;
    }
}
