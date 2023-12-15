import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of array: ");
        int size = sc.nextInt();

        int[] array = fillRandom(size);
        Arrays.sort(array);
        System.out.println("Enter element to search: ");
        int elementToSearch = sc.nextInt();

        //---------------------------------------------------------------//

        System.out.println("Binary search: ");
        long startTime = System.nanoTime();
        int[] binarySearchResult = binarySearch(array, elementToSearch);
        printSearchResult(binarySearchResult, "Binary Search");
        long endTime = System.nanoTime();
        System.out.println("Binary Search time: " + (endTime - startTime));

        System.out.println("\nRecursive Binary Search: ");
        long startTime1 = System.nanoTime();
        int[] recursiveBinarySearchResult = binarySearchRecursive(array, 0, array.length - 1, elementToSearch, 0);
        printSearchResult(recursiveBinarySearchResult, "Recursive Binary Search");
        long endTime1 = System.nanoTime();
        System.out.println("Recursive Binary Search time: " + (endTime1 - startTime1));
    }

    private static int[] fillRandom(int size){
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(101);
        }
        return array;
    }
    private static int[] binarySearch(int[] array, int elementToSearch){
        int firstIndex = 0;
        int lastIndex = array.length - 1;
        int steps = 0;

        while(firstIndex <= lastIndex){
            int middleIndex = (firstIndex + lastIndex) / 2;
            steps++;
            if(array[middleIndex] == elementToSearch){
                return new int[]{middleIndex, steps};
            }
            else if (array[middleIndex] < elementToSearch){
                firstIndex = middleIndex + 1;
            } else if (array[middleIndex] > elementToSearch) {
                lastIndex = middleIndex - 1;
            }
        }
        return new int[]{-1, steps};
    }
    private static int[] binarySearchRecursive(int[] array, int firstElement, int lastElement, int elementToSearch, int steps){
        if(lastElement >= firstElement){
            int middleIndex = (lastElement + firstElement) / 2;
            steps++;
            if(array[middleIndex] == elementToSearch){
                return new int[] {middleIndex, steps};
            }
            else if(array[middleIndex] > elementToSearch){
                return binarySearchRecursive(array, firstElement, middleIndex - 1, elementToSearch, steps);
            }
            else if(array[middleIndex] < elementToSearch){
                return binarySearchRecursive(array, middleIndex + 1, lastElement, elementToSearch, steps);
            }
        }
        return new int[]{-1, steps};
    }


    private static void printSearchResult(int[] result, String searchType) {
        int index = result[0];
        int steps = result[1];
        if (index != -1) {
            System.out.println(searchType + " result: Element found at index " + index + ", Steps: " + steps);
        } else {
            System.out.println(searchType + " result: Element not found in the array, Steps: " + steps);
        }
    }
}
