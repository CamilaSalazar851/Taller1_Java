package com.taller.practico.SECCION3;

import java.util.Scanner;

public class Ejercicio3_2_FactorialYTriangulo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        do {
            System.out.print("Ingrese un entero positivo N (máximo 20): ");
            n = sc.nextInt();
        } while (n < 1 || n > 20);

        // Parte A: factorial (long, porque int se desborda desde 13!)
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        System.out.println(n + "! = " + factorial);

        // Parte B: triángulo
        System.out.println("\nTriángulo de altura " + n + ":");
        for (int fila = 1; fila <= n; fila++) {
            for (int col = 1; col <= fila; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        sc.close();
    }
}