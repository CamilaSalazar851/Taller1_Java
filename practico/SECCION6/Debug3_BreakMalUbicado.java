package com.taller.practico.SECCION6;

public class Debug3_BreakMalUbicado {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0) {
                System.out.println("Número impar detectado: " + i);
                continue; // Corrección: continue en lugar de break
            }
            System.out.println("Número par procesado: " + i);
        }
    }
}