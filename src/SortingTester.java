import java.util.Arrays;

public class SortingTester {

    public static void bubbleSort(int[] nums) {
        for (int endIndex = nums.length - 1; endIndex > 0; endIndex--) {
            int swapCount = 0;
            for (int index = 0; index < endIndex; index++) {
                int val = nums[index];
                int val2 = nums[index+1];
                if (val > val2) {
                    // swap
                    nums[index] = val2;
                    nums[index+1] = val;
                    swapCount++;
                }

            }
            if (swapCount == 0) {
                System.out.println("sorted!");
                break;
            }
            System.out.println(Arrays.toString(nums));
        }
    }

    public static void selectionSort(int[] nums) {
        for (int endIndex = nums.length; endIndex > 1; endIndex--) {

            // single pass, find largest and move to back
            int maxValue = nums[0];
            int indexOfMax = 0;

            for (int index = 0; index < endIndex; index++) {
                int curValue = nums[index];
                if (curValue > maxValue) {
                    maxValue = curValue;
                    indexOfMax = index;
                }
            }

            // swap
            int val2 = nums[endIndex - 1];
            nums[indexOfMax] = val2;
            nums[endIndex - 1] = maxValue;

            System.out.println(Arrays.toString(nums));
        }
    }

    public static void insertionSort(int[] nums) {
        for (int insertIndex = 1; insertIndex < nums.length; insertIndex++) {

            // each pass, insert another value into the sorted portion of array
            int newValue = nums[insertIndex];
            for (int index = insertIndex - 1; index >= 0; index--) {
                int curValue = nums[index];
                if (newValue < curValue) {
                    // swap @ indices index and index + 1
                    nums[index] = newValue;
                    nums[index+1] = curValue;
                }
                else {
                    break;
                }

            }
            System.out.println(Arrays.toString(nums));
        }
    }

    public static void main(String[] args) {
    /*int[] nums = new int[10];
    for (int i = 0; i < 10; i++) {
      nums[i] = (int) (Math.random() * 100) + 50;
    }*/
        int[] nums = { 3, 9, 15, 7, 4, 10, 20, 8, 2 };
        System.out.println(Arrays.toString(nums));
        //bubbleSort(nums);
        //selectionSort(nums);
        insertionSort(nums);

    }

}


