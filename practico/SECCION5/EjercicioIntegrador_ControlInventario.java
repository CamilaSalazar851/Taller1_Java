package com.taller.practico.SECCION5;

import java.util.Scanner;

public class EjercicioIntegrador_ControlInventario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int CAPACIDAD_MAXIMA = 500;
        int totalUnidades = 0;
        double valorInventario = 0;
        int productosRegistrados = 0;
        int opcion;

        do {
            System.out.println("\n===== ALMACÉN =====");
            System.out.println("1. Registrar Lote de Productos");
            System.out.println("2. Ver Estado del Almacén");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Cantidad de productos del lote: ");
                    int n = sc.nextInt();

                    for (int i = 1; i <= n; i++) {
                        System.out.println("\nProducto " + i + " de " + n);

                        System.out.print("¿Está descontinuado? (s/n): ");
                        String respuesta = sc.next();
                        if (respuesta.equalsIgnoreCase("s")) {
                            System.out.println("Producto descontinuado, se omite.");
                            continue;
                        }

                        System.out.print("Precio: ");
                        double precio = sc.nextDouble();
                        while (precio <= 0) {
                            System.out.print("Debe ser mayor a cero. Precio: ");
                            precio = sc.nextDouble();
                        }

                        System.out.print("Cantidad en stock: ");
                        int stock = sc.nextInt();
                        while (stock <= 0) {
                            System.out.print("Debe ser mayor a cero. Cantidad en stock: ");
                            stock = sc.nextInt();
                        }

                        totalUnidades += stock;
                        valorInventario += precio * stock;
                        productosRegistrados++;

                        if (totalUnidades > CAPACIDAD_MAXIMA) {
                            System.out.println("ALERTA: sobrecupo. Se superó la capacidad de "
                                    + CAPACIDAD_MAXIMA + " unidades. Se aborta el resto del lote.");
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("\n--- Estado del almacén ---");
                    System.out.println("Productos registrados: " + productosRegistrados);
                    System.out.println("Unidades totales: " + totalUnidades + " / " + CAPACIDAD_MAXIMA);
                    System.out.println("Valor del inventario: $" + valorInventario);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);

        sc.close();
    }
}