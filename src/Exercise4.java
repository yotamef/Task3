public class Exercise4 {

    public static void sortArr(int [] arr) {
        int remember;
        for (int i=0; i<arr.length-1; i++) {
            if (arr[i]>arr[i+1]) {
                remember = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = remember;
                i=-1;
            }
        }
    }
    public static boolean completeArr(int [] arr) {
        boolean complete = true;
        sortArr(arr);
        for (int i=0; i<arr.length-1; i++) {
            if (arr[i] + 1 != arr[i+1])
                complete = false;
        }
        return complete;
    }
}
