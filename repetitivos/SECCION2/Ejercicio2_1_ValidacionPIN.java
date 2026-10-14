
package com.taller.practico.SECCION2;

import java.util.Scanner;

public class Ejercicio2_1_ValidacionPIN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pin;

        do {
            System.out.print("Ingrese su clave de 4 dígitos: ");
            pin = sc.nextInt();

            if (pin < 1000 || pin > 9999) {
                System.out.println("Error: La clave debe tener exactamente 4 dígitos positivos");
            }
        } while (pin < 1000 || pin > 9999);

        System.out.println("Clave válida. Acceso concedido.");
        sc.close();
    }
}
 