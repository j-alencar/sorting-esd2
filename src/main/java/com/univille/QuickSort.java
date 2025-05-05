package com.univille;

public class QuickSort implements Sort {
    /**
     * Ordena o array usando o algoritmo Quick Sort.
     *
     * @param array Array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    // Método recursivo para dividir e conquistar
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Particiona o array em torno do pivot
    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    // Troca dois elementos no array
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}