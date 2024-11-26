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

    //    Helper Method for mergeSort()
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

        System.out.printf("Sorting Array: %s\n", Arrays.toString(Arrays.copyOfRange(array, start, end + 1)));

//        partition() helper method splits the array and reorders elements based around the pivot
        int pivot = partition(array, start, end);

        System.out.printf("Pivot(%d) placed at index %d: %s\n", array[pivot], pivot, Arrays.toString(array));

//        Recursively sort the subarrays before and after the pivot
        quickSort(array, start, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    //    Helper Method for quickSort()
    private static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start - 1;
        int temp;

//        Loop through an array and partition it around the pivot
        for (int j = start; j <= end - 1; j++) {
            if (array[j] < pivot) {
                i++;
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
//        Place the pivot in its correct position
        i++;
        temp = array[i];
        array[i] = array[end];
        array[end] = temp;

        return i; // return the index of the pivot
    }

    static void heapSort(int[] array) {
        int arrayLength = array.length;
        int temp;

        System.out.printf("Current Array: %s\n", Arrays.toString(array));

//        Loop that builds the max heap
        System.out.println("Building max heap:");
        for (int i = arrayLength / 2 - 1; i >= 0; i--) {
            heapify(array, arrayLength, i);
        }
        System.out.printf("Heap after heapify: %s\n", Arrays.toString(array));

//        Loop extracts from the heap one by one, sorting the array in ascending order
        System.out.println("Sorting Array:");
        for (int i = arrayLength - 1; i > 0; i--) {
            temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            System.out.printf("After swapping root with index %d: %s\n", i, Arrays.toString(array));

//            heapify() helper method maintains the max heap property
            heapify(array, i, 0);
            System.out.printf("Heap after heapify: %s\n", Arrays.toString(array));
        }
        System.out.printf("Sorted Array: %s\n", Arrays.toString(array));
    }

    //    Helper Method for heapSort()
    private static void heapify(int[] array, int size, int root) {
        int largest = root;
        int temp;
        while (true) {
            int left = 2 * root + 1;
            int right = 2 * root + 2;

//            Compare if either the left or right children is larger than the root
            largest = left < size && array[left] > array[largest] ? left : largest;
            largest = right < size && array[right] > array[largest] ? right : largest;

            if (largest == root) break;

//            Swap the root with the largest child
            temp = array[root];
            array[root] = array[largest];
            array[largest] = temp;

            root = largest;
        }
    }

    static void randomizeArray(int[] array) {
        int limit = array.length - 1;
        Random random = new Random();

        System.out.printf("Current Array: %s\n", Arrays.toString(array));
//        Iterate over an array starting from the last element
        for (int i = limit; i > 0; i--) {
//            Swap the position of the current element and a random element to its left
            int upperBound = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[upperBound];
            array[upperBound] = temp;
        }
        System.out.printf("Randomized Array: %s\n", Arrays.toString(array));
    }
}