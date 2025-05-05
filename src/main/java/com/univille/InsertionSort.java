package com.univille;

/**
 * Implementação do algoritmo Insertion Sort.
 */
public class InsertionSort implements Sort {

    /**
     * Ordena o array usando o algoritmo Insertion Sort.
     *
     * @param array Array a ser ordenado
     */
    @Override
    public void sort(int[] array) {
        // Itera pelo array a partir do segundo elemento
        for (int i = 1; i < array.length; i++) {
            int key = array[i]; // Elemento a ser inserido na porção ordenada
            int j = i - 1;

            // Move elementos maiores para a direita
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            // Insere o elemento na posição correta
            array[j + 1] = key;
        }
    }
}