package com.univille;
import java.util.Random;

public class GeradorArrays {

    /**
     * Interface funcional para geração de arrays.
     */
    @FunctionalInterface
    public interface ArrayGenerator {
        int[] gerar(int tamanho);
    }

    /**
     * Gera um array com valores aleatórios.
     *
     * @param tamanho Tamanho do array
     * @return Array gerado
     */
    public static int[] gerarArrayAleatorio(int tamanho) {
        Random random = new Random();
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(tamanho);
        }
        return array;
    }

    /**
     * Gera um array ordenado em ordem crescente.
     *
     * @param tamanho Tamanho do array
     * @return Array gerado
     */
    public static int[] gerarArrayOrdenado(int tamanho) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = i;
        }
        return array;
    }

    /**
     * Gera um array ordenado em ordem decrescente.
     *
     * @param tamanho Tamanho do array
     * @return Array gerado
     */
    public static int[] gerarArrayOrdenadoReverso(int tamanho) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = tamanho - i;
        }
        return array;
    }

    public static ArrayGenerator getGeradorAleatorio() {
        return GeradorArrays::gerarArrayAleatorio;
    }
    
    public static ArrayGenerator getGeradorOrdenado() {
        return GeradorArrays::gerarArrayOrdenado;
    }
    
    public static ArrayGenerator getGeradorOrdenadoReverso() {
        return GeradorArrays::gerarArrayOrdenadoReverso;
    }
}
