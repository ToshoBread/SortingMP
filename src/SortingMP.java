import java.lang.reflect.Array;
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

        while (true) {
            System.out.println(divider);
            System.out.println("Array Sorting");
            System.out.println("1. Bubble");
            System.out.println("2. Insertion");
            System.out.println("3. Selection");
            System.out.println("4. Merge Sort");
            System.out.println("5. Quick Sort");
            System.out.println("6. Heap Sort");
            System.out.println("7. Randomize Array");
            System.out.println("8. Exit");
            System.out.println("Enter Choice [1 - 8]: ");

            try {
                int choice = userInput.nextInt();
                System.out.println(divider);

                switch (choice) {
                    case 1 -> bubbleSort(arrayForSorting);
                    case 2 -> insertionSort(arrayForSorting);
                    case 3 -> selectionSort(arrayForSorting);
                    case 4 -> {
                        mergeSort(arrayForSorting);
                        System.out.printf("Sorted Array: %s", Arrays.toString(arrayForSorting));
                    }
                    case 5 -> quickSort(arrayForSorting);
                    case 6 -> heapSort(arrayForSorting);
                    case 7 -> randomizeArray(arrayForSorting);
                    case 8 -> {
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

    /*
        In Bubble sort algorithm, elements are sorted by pairs,
        where if the element on the left is greater than the element on the right,
        swap the positions of both elements;
        otherwise, ignore and move on to the next pair.
     */
    static void bubbleSort(int[] array) {
        int limit = array.length - 1;
        int temp;
        for (int i = 0; i < limit; i++) {
            System.out.printf("Pass %d\n", i + 1);
            for (int j = 0; j < limit; j++) {
                System.out.println(Arrays.toString(array));
                if (array[j] > array[j + 1]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.printf("Sorted Array: %s\n", Arrays.toString(array));
    }

    /*
        In Insertion sort algorithm, elements are sorted by extracting an element
        and determining its target position.
        If the current element is lesser than any of the elements to its left,
        shift all greater elements to the right by 1
        then place the current element to its new position.
     */
    static void insertionSort(int[] array) {
        int limit = array.length;
        System.out.printf("Current Array: %s\n", Arrays.toString(array));

        for (int i = 1; i < limit; i++) {
            int temp = array[i];
            int currentIndex = i - 1;

            System.out.printf("Pass %d ", i);
            while (currentIndex >= 0 && temp < array[currentIndex]) {
                array[currentIndex + 1] = array[currentIndex];
                currentIndex--;
            }
            array[currentIndex + 1] = temp;
            System.out.println(Arrays.toString(array));
        }
        System.out.printf("Sorted Array: %s\n", Arrays.toString(array));
    }

    /*
        In Selection sort algorithm, elements are sorted by swapping
        an unsorted element from the left with the next smallest element
        from its right.
     */
    static void selectionSort(int[] array) {
        int limit = array.length;
        int temp;

        System.out.printf("Current Array: %s\n", Arrays.toString(array));
        for (int i = 0; i < limit; i++) {
            int minIndex = i;
            System.out.printf("Pass %d ", i + 1);
            for (int j = i; j < limit; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
            System.out.println(Arrays.toString(array));
        }
        System.out.printf("Sorted Array: %s\n", Arrays.toString(array));
    }

    static void mergeSort(int[] array) {
        int limit = array.length;
        if (limit <= 1) return;

        int mid = limit / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[limit - mid];

        int i = 0;
        int j = 0;

        while (i < limit) {
            if (i < mid) {
                leftArray[i] = array[i];
            } else {
                rightArray[j] = array[i];
                j++;
            }
            i++;
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    }

    //    Helper Method for Merge Sort
    private static void merge(int[] leftArray, int[] rightArray, int[] array) {
        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, leftIndex = 0, rightIndex = 0;

        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftArray[leftIndex] < rightArray[rightIndex]) {
                array[i] = leftArray[leftIndex];
                i++;
                leftIndex++;
            } else {
                array[i] = rightArray[rightIndex];
                i++;
                rightIndex++;
            }
        }
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
    }

    static void quickSort(int[] array) {
        System.out.println("Quick Sort Under Development");
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