package com.taller.practico.SECCION6;

public class Debug2_DesfasePorUno {
    public static void main(String[] args) {
        int totalElementos = 5;

        System.out.println("Imprimiendo elementos del 1 al " + totalElementos + ":");

        // Corrección: <= en lugar de <
        for (int i = 1; i <= totalElementos; i++) {
            System.out.println("Elemento " + i);
        }
    }
}