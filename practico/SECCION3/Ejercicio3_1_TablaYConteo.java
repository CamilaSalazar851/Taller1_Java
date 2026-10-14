package com.taller.practico.SECCION3;

import java.util.Scanner;

public class Ejercicio3_1_TablaYConteo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un número entero N: ");
        int n = sc.nextInt();

        System.out.println("\nTabla de multiplicar del " + n + ":");
        for (int i = 1; i <= 12; i++) {
            System.out.printf("%d x %2d = %4d%n", n, i, n * i);
        }

        System.out.println("\nConteo regresivo:");
        for (int i = n; i >= 0; i--) {
            System.out.println(i);
        }
        sc.close();
    }
}