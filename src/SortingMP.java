import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class SortingMP {
    private static int[] arrayForSorting;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String divider = "=========================";

        System.out.print("Enter the size of an array: ");
        try {
            int arraySize = userInput.nextInt();
            arrayForSorting = new int[arraySize];
            System.out.printf("Enter %d values: ", arraySize);

            for (int i = 0; i < arraySize; i++) {
                int value = userInput.nextInt();
                arrayForSorting[i] = value;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
            userInput.nextLine();
        }

//        While loop ensures that the program does not terminate until chosen so by the user
        while (true) {
            System.out.println(divider);
            System.out.println("Array Sorting");
            System.out.println("0. Randomize Array");
            System.out.println("1. Bubble");
            System.out.println("2. Insertion");
            System.out.println("3. Selection");
            System.out.println("4. Merge Sort");
            System.out.println("5. Quick Sort");
            System.out.println("6. Heap Sort");
            System.out.println("7. Exit");
            System.out.print("Enter Choice [1 - 8]: ");

            try {
                int choice = userInput.nextInt();
                System.out.println(divider);

                switch (choice) {
                    case 0 -> randomizeArray(arrayForSorting);
                    case 1 -> bubbleSort(arrayForSorting);
                    case 2 -> insertionSort(arrayForSorting);
                    case 3 -> selectionSort(arrayForSorting);
                    case 4 -> {
                        System.out.printf("Current Array: %s\n", Arrays.toString(arrayForSorting));
                        mergeSort(arrayForSorting);
                        System.out.printf("Sorted Array: %s\n", Arrays.toString(arrayForSorting));
                    }
                    case 5 -> {
                        System.out.printf("Current Array: %s\n", Arrays.toString(arrayForSorting));
                        quickSort(arrayForSorting, 0, arrayForSorting.length - 1);
                        System.out.printf("Sorted Array: %s\n", Arrays.toString(arrayForSorting));
                    }
                    case 6 -> heapSort(arrayForSorting);
                    case 7 -> {
                        userInput.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid Input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                userInput.nextLine();
            }
        }
    }

    static void bubbleSort(int[] array) {
        int limit = array.length - 1;
        int temp;
        System.out.printf("Current Array: %s\n", Arrays.toString(array));

//        Outer loop to manage the number of iterations to sort the array
        for (int i = 0; i < limit; i++) {
            System.out.printf("Pass %d\n", i + 1);
//            Inner loop to compare adjacent elements and swap them if needed
            for (int j = 0; j < limit; j++) {
                System.out.println(Arrays.toString(array));
//                Compare adjacent elements to determine if swapping is required
                if (array[j] > array[j + 1]) {
//                    If the current element is bigger, swap it with the next
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.printf("Sorted Array: %s\n", Arrays.toString(array));
    }

    static void insertionSort(int[] array) {
        int limit = array.length;
        System.out.printf("Current Array: %s\n", Arrays.toString(array));

//        Outer loop to manage the number of iterations to sort the array
        for (int i = 1; i < limit; i++) {
            int temp = array[i]; // initialize current element in a temporary variable
            int currentIndex = i - 1;

            System.out.printf("Pass %d ", i);
//            Check if the current index is within bounds.
            while (currentIndex >= 0 && temp < array[currentIndex]) {
//                While the current element is greater than the previous elements, shift elements to the right
                array[currentIndex + 1] = array[currentIndex];
                currentIndex--;
            }
            array[currentIndex + 1] = temp;
            System.out.println(Arrays.toString(array));
        }
        System.out.printf("Sorted Array: %s\n", Arrays.toString(array));
    }

    static void selectionSort(int[] array) {
        int limit = array.length;
        int temp;

        System.out.printf("Current Array: %s\n", Arrays.toString(array));
//        Outer loop to manage the number of iterations to sort the array
        for (int i = 0; i < limit; i++) {
            int minIndex = i;
            System.out.printf("Pass %d ", i + 1);
//            Inner loop to handle the search for the minimum element
            for (int j = i; j < limit; j++) {
                if (array[j] < array[minIndex]) {
//                    if the current element is smaller than the minimum element
                    minIndex = j; // The current element is the new minimum
                }
            }

//            Swap the positions of the current element and the minimum element
            temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
            System.out.println(Arrays.toString(array));
        }
        System.out.printf("Sorted Array: %s\n", Arrays.toString(array));
    }

    static void mergeSort(int[] array) {
        int limit = array.length;
        if (limit <= 1) return; //Base case

//        Initialize the middle index of an array and create 2 subarrays
        int mid = limit / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[limit - mid];

        int i = 0;
        int j = 0;

//        Loop through the array and assign elements to the left and right subarrays
        while (i < limit) {
            if (i < mid) {
                leftArray[i] = array[i];
            } else {
                rightArray[j] = array[i];
                j++;
            }
            i++;
        }

        System.out.printf("Splitting: %s into %s and %s\n",
                Arrays.toString(array),
                Arrays.toString(leftArray),
                Arrays.toString(rightArray));

//        Recursively split the array into 2 halves for sorting
        mergeSort(leftArray);
        mergeSort(rightArray);
//        merge() helper method merges sorted subarrays into a single sorted array
        merge(leftArray, rightArray, array);
    }

    //    Helper Method for Merge Sort
    private static void merge(int[] leftArray, int[] rightArray, int[] array) {
        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, leftIndex = 0, rightIndex = 0;

//        Merge elements from the left and right subarrays into the original array
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftArray[leftIndex] < rightArray[rightIndex]) {
//                If the current left element is smaller than the right, insert it to the original array
                array[i] = leftArray[leftIndex];
                i++;
                leftIndex++;
            } else {
//                Otherwise, insert the current right element to the original array
                array[i] = rightArray[rightIndex];
                i++;
                rightIndex++;
            }
        }
//        Ensure any remaining elements from either arrays are added to the end of the original array
        while (leftIndex < leftSize) {
            array[i] = leftArray[leftIndex];
            i++;
            leftIndex++;
        }
        while (rightIndex < rightSize) {
            array[i] = rightArray[rightIndex];
            i++;
            rightIndex++;
        }
        System.out.printf("Merged: %s and %s into %s\n",
                Arrays.toString(leftArray),
                Arrays.toString(rightArray),
                Arrays.toString(array));
    }

    static void quickSort(int[] array, int start, int end) {
        if (end <= start) return; //Base case

        int pivot = partition(array, start, end);
        quickSort(array, start, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start - 1;
        int temp;

        for (int j = start; j <= end - 1; j++) {
            if (array[j] < pivot) {
                i++;
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        i++;
        temp = array[i];
        array[i] = array[end];
        array[end] = temp;
        return i;
    }

    static void heapSort(int[] array) {
        System.out.println("Heap Sort Under Development");
    }

    static void randomizeArray(int[] array) {
        int limit = array.length - 1;
        Random random = new Random();

        System.out.printf("Current Array: %s\n", Arrays.toString(array));
        for (int i = limit; i > 0; i--) {
            int upperBound = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[upperBound];
            array[upperBound] = temp;
        }
        System.out.printf("Randomized Array: %s\n", Arrays.toString(array));
    }
}