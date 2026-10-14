package com.taller.practico.SECCION1;

import java.util.Scanner;

public class Ejercicio1_2_BanderaTransacciones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final double LIMITE = 1_000_000;
        double acumulado = 0;
        boolean procesando = true;
        String causa = "";

        while (procesando) {
            System.out.print("Ingrese el monto de la transacción: ");
            double monto = sc.nextDouble();

            if (monto < 0) {
                procesando = false;
                causa = "Se ingresó un monto negativo (no válido).";
            } else {
                acumulado += monto;
                if (acumulado > LIMITE) {
                    procesando = false;
                    causa = "Se superó el límite máximo de $1'000.000.";
                }
            }
        }

        System.out.println("---- Informe final ----");
        System.out.println("Saldo final acumulado: $" + acumulado);
        System.out.println("Causa de finalización: " + causa);
        sc.close();
    }
}
