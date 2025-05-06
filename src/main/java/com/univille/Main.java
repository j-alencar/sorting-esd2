package com.univille;

import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import com.univille.GeradorArrays.ArrayGenerator;

public class Main {

    /**
     * Executa um experimento de ordenação e retorna os resultados formatados.
     *
     * @param tipoSort Algoritmo de ordenação
     * @param tamanho Tamanho do array
     * @param iteracoes Número de iterações
     * @param descricao Descrição do experimento
     * @param gerador Gerador de arrays
     * @return Resultados formatados como string
     */
    public static String rodarExperimento(Sort tipoSort, int tamanho, int iteracoes, String descricao,
        ArrayGenerator gerador) {
        long[] times = new long[iteracoes];

        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < iteracoes; i++) {
            int[] array = gerador.gerar(tamanho);
            long startTime = System.nanoTime();
            tipoSort.sort(array);
            long endTime = System.nanoTime();

            times[i] = endTime - startTime;
            String linha = tipoSort.getClass().getSimpleName() + ", " + times[i] + ", " + tamanho
                    + ", " + descricao;
            result.append(linha).append(System.lineSeparator());
        }
        return result.toString();
    }

    /**
     * Executa os experimentos e salva os resultados em um arquivo CSV.
     *
     * @param args Argumentos da linha de comando
     */
    public static void main(String[] args) {
        // Algoritmos de ordenação disponíveis
        Sort[] algoritmos = { new InsertionSort(), new QuickSort(), new MergeSort(), new SelectionSort(), new BubbleSort() };

        ArrayGenerator[] geradores = { 
            GeradorArrays.getGeradorAleatorio(), 
            GeradorArrays.getGeradorOrdenado(), 
            GeradorArrays.getGeradorOrdenadoReverso() 
        };

        String[] descs = { "Lista Aleatória", "Lista Ordenada", "Lista Inversamente Ordenada" };
        
        int[] tamanhos = { 1000, 10000 };
        int iteracoes = 30;

        try (FileWriter writer = new FileWriter("output.csv")) {
            writer.write("Algoritmo, Tempo (ms), Tamanho, Tipo de Array\n"); // Cabeçalho das colunas

            // Experimentos para cada combinação de algoritmo, tamanho e gerador
            for (Sort sort : algoritmos) {
                for (int tamanho : tamanhos) {
                    for (int i = 0; i < geradores.length; i++) {
                        String resultado = rodarExperimento(sort, tamanho, iteracoes, descs[i], geradores[i]);
                        writer.write(resultado.trim() + System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
