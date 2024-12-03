package com.krystov.websearch.service;

import org.springframework.stereotype.Service;
import java.util.Collections;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@Service
public class SortingService {

    public int[] sort(int[] data, String algorithm) {
        int[] numbers = data;

        switch (algorithm.toLowerCase()) {
            case "heapsort":
                return heapSort(numbers);
            case "quicksort":
                return quickSort(numbers, 0, numbers.length - 1);
            case "mergesort":
                return mergeSort(numbers);
            case "radixsort":
                return radixSort(numbers);
            case "bucketsort":
                return bucketSort(numbers);
            default:
                throw new IllegalArgumentException("Unknown sorting algorithm: " + algorithm);
        }
    }

    // Heap Sort Implementation
    private int[] heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root (maximum element) to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
        return arr;
    }

    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Check if left child exists and is greater than root
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // Check if right child exists and is greater than largest so far
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // If largest is not root, swap and continue heapifying
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
    // Quick Sort Implementation
    private int[] quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
        return arr;
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private int[] mergeSort(int[] arr) {
        // Base case: if array is 1 or fewer elements, it's already sorted
        if (arr.length <= 1) {
            return arr;
        }

        // Divide the array into two halves
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        // Recursively sort both halves
        left = mergeSort(left);
        right = mergeSort(right);

        // Merge the sorted halves
        return merge(arr, left, right);
    }

    private int[] merge(int[] arr, int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        // Compare and merge elements from left and right arrays
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // Copy remaining elements from left array, if any
        while (i < left.length) {
            result[k++] = left[i++];
        }

        // Copy remaining elements from right array, if any
        while (j < right.length) {
            result[k++] = right[j++];
        }

        // Copy merged result back to original array
        System.arraycopy(result, 0, arr, 0, result.length);
        return arr;
    }

    // Radix Sort Implementation
    private int[] radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(0);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
        return arr;
    }

    private void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    private int[] bucketSort(int[] arr) {
        // Handle empty or single-element array
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        // Find max and min values
        int max = Arrays.stream(arr).max().orElse(0);
        int min = Arrays.stream(arr).min().orElse(0);

        // Calculate range and bucket count
        int range = max - min + 1;
        int bucketCount = Math.max(8, arr.length / 10); // Ensure a reasonable number of buckets

        // Create bucket array
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribute elements into buckets
        for (int num : arr) {
            // Calculate bucket index
            int bucketIndex = (int) ((double)(num - min) / range * (bucketCount - 1));
            buckets.get(bucketIndex).add(num);
        }

        // Sort individual buckets
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // Merge sorted buckets back into original array
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                arr[index++] = num;
            }
        }

        return arr;
    }
}

