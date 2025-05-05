package com.univille;

public class SelectionSort implements Sort {
    /**
     * Ordena o array usando o algoritmo Selection Sort.
     *
     * @param array Array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            // Encontra o menor elemento no subarray não ordenado
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // Troca o menor elemento com o primeiro elemento do subarray
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}