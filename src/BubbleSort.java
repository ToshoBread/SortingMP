import java.util.Arrays;

class BubbleSort {
    public static void main(String[] args) {
//        Test Case
        bubble_sort(new int[]{75, 83, 90, 65, 88, 65, 65});
    }

    static void bubble_sort(int[] array) {
//        This method sorts an array in descending order.
//        Returns immediately if the there is no more than 1 element
//        Prints the array's state after each pass, along with the number of swaps performed.
//        Stops early if the array has been sorted in a pass.

        int limit = array.length - 1;
        int temp, numberOfSwaps;

        if (array.length <= 1) {
            System.out.println("Array is already sorted.");
            return;
        }

        System.out.printf("Initial scores: %s\n", Arrays.toString(array));
//        Outer loop to manage the number of iterations to sort the array
        for (int i = 0; i < limit; i++) {

            numberOfSwaps = 0; // Reset the swap counter after each pass
            System.out.printf("Pass %d: ", i + 1);
//            Inner loop to compare adjacent elements and swap them if needed
            for (int j = 0; j < limit - i; j++) {
//                Compare adjacent elements to determine if swapping is required
                if (array[j] < array[j + 1]) {
//                    If the current element is smaller, swap it with the next and increment swap counter
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    numberOfSwaps++;
                }
            }
            System.out.print(Arrays.toString(array));
            System.out.printf("(%d swap%s)\n", numberOfSwaps, numberOfSwaps == 1 ? "" : "s");

//            If no swaps were made, then array is sorted. Terminate early.
            if (numberOfSwaps == 0) {
                System.out.printf("Final sorted scores: %s\n", Arrays.toString(array));
                return;
            }
        }
    }
}