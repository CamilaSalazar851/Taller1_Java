package com.educacion.ahorcado;

import java.util.Random;
import java.util.Scanner;

public class Main {

    // Unidad 1.1: constante inmutable
    static final int MAX_INTENTOS = 6;
    static final String[] PALABRAS = {"JAVA", "CONSOLA", "VARIABLE", "BUCLE", "CADENA", "OBJETO", "TECLADO"};

    static Scanner sc = new Scanner(System.in);

    // Estadísticas acumuladas entre partidas
    static int partidasJugadas = 0;
    static int partidasGanadas = 0;
    static double mejorPuntaje = 0.0;

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    jugar();
                    break;
                case 2:
                    mostrarEstadisticas();
                    break;
                case 3:
                    System.out.println("Gracias por jugar. ¡Hasta pronto!");
                    break;
            }
        } while (opcion != 3);
    }

    static void mostrarMenu() {
        System.out.println("\n===== AHORCADO DE CONSOLA =====");
        System.out.println("1. Jugar");
        System.out.println("2. Ver Estadísticas");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
    }

    // Fase 1: validación con while + Integer.parseInt
    static int leerOpcion() {
        int opcion = 0;
        boolean valida = false;
        while (!valida) {
            String linea = sc.nextLine().trim();
            try {
                opcion = Integer.parseInt(linea);
                if (opcion >= 1 && opcion <= 3) {
                    valida = true;
                } else {
                    System.out.print("Opción fuera de rango. Escribe 1, 2 o 3: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Eso no es un número válido. Escribe 1, 2 o 3: ");
            }
        }
        return opcion;
    }

    static void mostrarEstadisticas() {
        System.out.println("\n--- ESTADÍSTICAS ---");
        System.out.printf("Partidas jugadas: %d%n", partidasJugadas);
        System.out.printf("Partidas ganadas: %d%n", partidasGanadas);
        System.out.printf("Mejor puntaje: %.2f pts%n", mejorPuntaje);
    }

    static void jugar() {
        // Fase 2: registro y normalización del nombre
        String nombreJugador = "";
        while (nombreJugador.length() == 0) {
            System.out.print("\nIngresa tu nombre: ");
            nombreJugador = sc.nextLine().trim().toUpperCase();
            if (nombreJugador.length() == 0) {
                System.out.println("El nombre no puede estar vacío.");
            }
        }

        // Variables de estado
        int intentosRestantes = MAX_INTENTOS;
        double puntaje = 0.0;
        boolean juegoGanado = false;
        boolean rendido = false;
        char ultimaLetra = ' ';
        int aciertos = 0;
        int fallos = 0;

        String palabraSecreta = PALABRAS[new Random().nextInt(PALABRAS.length)];

        // Máscara tipo "J _ V _" con StringBuilder
        StringBuilder mascara = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            mascara.append("_ ");
        }
        StringBuilder historial = new StringBuilder();

        // Fase 3: bucle principal
        while (intentosRestantes > 0 && !juegoGanado) {
            System.out.println("\nPalabra: " + mascara);
            System.out.println("Intentos restantes: " + intentosRestantes + " / " + MAX_INTENTOS);
            System.out.println("Letras usadas: " + (historial.length() == 0 ? "(ninguna)" : historial));

            // Reto: pista cuando queda un solo intento
            if (intentosRestantes == 1) {
                System.out.println("Pista: la palabra empieza con '" + palabraSecreta.substring(0, 1) + "'");
            }

            System.out.print("Escribe una letra (o 'salir' para rendirte): ");
            String entrada = sc.nextLine().trim();

            // Validar que no esté vacía antes de usar charAt
            if (entrada.length() == 0) {
                System.out.println("No escribiste nada.");
                continue;
            }
            if (entrada.equalsIgnoreCase("salir")) {
                rendido = true;
                break;
            }
            if (entrada.length() > 1) {
                System.out.println("Escribe solo una letra.");
                continue;
            }

            ultimaLetra = Character.toUpperCase(entrada.charAt(0));
            if (!Character.isLetter(ultimaLetra)) {
                System.out.println("Eso no es una letra.");
                continue;
            }
            if (historial.indexOf(String.valueOf(ultimaLetra)) >= 0) {
                System.out.println("Ya habías probado la letra " + ultimaLetra + ".");
                continue;
            }
            historial.append(ultimaLetra).append(' ');

            // Buscar coincidencias y destapar la máscara
            boolean acierto = false;
            for (int i = 0; i < palabraSecreta.length(); i++) {
                String letraSecreta = String.valueOf(palabraSecreta.charAt(i));
                if (letraSecreta.equalsIgnoreCase(String.valueOf(ultimaLetra))) {
                    mascara.setCharAt(i * 2, ultimaLetra);
                    acierto = true;
                }
            }

            if (acierto) {
                aciertos++;
            } else {
                fallos++;
                intentosRestantes--;
            }

            // Operador ternario para el mensaje
            System.out.println(acierto ? "¡Bien! La letra " + ultimaLetra + " está en la palabra."
                                       : "Fallaste, la letra " + ultimaLetra + " no está.");

            if (mascara.indexOf("_") < 0) {
                juegoGanado = true;
                break;
            }
        }

        // Fase 4: cálculos y reporte
        if (juegoGanado) {
            puntaje = 100.0 + intentosRestantes * 12.5;
        }

        int totalJugadas = aciertos + fallos;
        // Casting a double para evitar la división entera
        double porcentaje = totalJugadas > 0 ? (double) aciertos / totalJugadas * 100 : 0.0;

        String estado = juegoGanado ? "¡VICTORIA!" : (rendido ? "RENDICIÓN" : "DERROTA");

        partidasJugadas++;
        if (juegoGanado) {
            partidasGanadas++;
        }
        if (puntaje > mejorPuntaje) {
            mejorPuntaje = puntaje;
        }

        System.out.println("\n====================================================");
        System.out.println("RESUMEN DE LA PARTIDA");
        System.out.println("====================================================");
        System.out.printf("%-22s %s%n", "Jugador:", nombreJugador);
        System.out.printf("%-22s %s%n", "Estado Final:", estado);
        System.out.printf("%-22s %s%n", "Palabra Secreta:", palabraSecreta);
        System.out.printf("%-22s %d / %d%n", "Intentos Restantes:", intentosRestantes, MAX_INTENTOS);
        System.out.printf("%-22s %.2f%%%n", "Porcentaje Aciertos:", porcentaje);
        System.out.printf("%-22s %.2f pts%n", "Puntaje Final:", puntaje);
        System.out.println("====================================================");
    }
}
