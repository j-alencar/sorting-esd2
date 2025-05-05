package com.univille;

public class MergeSort implements Sort {
    /**
     * Ordena o array usando o algoritmo Merge Sort.
     *
     * @param array Array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        // Divide em duas metades
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        // Ordena recursivamente as duas metades
        sort(left);
        sort(right);
        merge(array, left, right);
    }

    // Mescla dois subarrays ordenados
    private void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        // Copia os elementos restantes
        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
}