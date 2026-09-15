public class HybridSort {

    // Threshold value (typically between 10 and 32)
    private static final int THRESHOLD = 16;

    public static void hybridSort(int[] arr, int left, int right) {
        if (right - left + 1 <= THRESHOLD) {
            insertionSort(arr, left, right);
        } else if (left < right) {
            int mid = left + (right - left) / 2;

            hybridSort(arr, left, mid);
            hybridSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k++] = leftArr[i++];
        }

        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        int[] data = { 38, 27, 43, 3, 9, 82, 10, 19, 50, 61, 2, 8 };

        System.out.println("Original array:");
        printArray(data);

        hybridSort(data, 0, data.length - 1);

        System.out.println("\nSorted array:");
        printArray(data);
    }

    private static void printArray(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}