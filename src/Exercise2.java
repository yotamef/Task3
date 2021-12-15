public class Exercise2 {

    public static int sumDigits(int num) {
        int sum = 0;
        while (num!=0) {
            sum = sum + num%10;
            num = num/10;
        }
        return sum;
    }

    public static boolean brothers (int num1, int num2) {
        int sum1 = sumDigits(num1);
        int sum2 = sumDigits(num2);
        return (sum1 == sum2);
    }

    public static int indexClose(int [] arr1, int [] arr2) {
        int index = -1, mostClose = 0;
        for (int i=0; i<arr1.length; i++) {
            int currentClose = 0;
            for (int j=0; j<arr2.length; j++) {
                if (brothers(arr1[i],arr2[j])) {
                    currentClose++;
                }
            }
            if (currentClose>mostClose) {
                mostClose = currentClose;
                index = i;
            }
        }
        return index;
    }
}
