package com.mindstroid.mindstroidgameapplication.games.findindexsortedarray.custom_sorting_algos;

public class CustomInsertionSort {
    public static int[] startInsertionSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}
