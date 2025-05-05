package com.univille;

public class BubbleSort implements Sort {
    /**
     * Ordena o array usando o algoritmo Bubble Sort.
     *
     * @param array Array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            // Compara elementos adjacentes e troca se necessário
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            // Interrompe se não houve trocas
            if (!swapped) {
                break;
            }
        }
    }
}