package org.example;

import java.util.Scanner;

public class CalculadoraNumComplejos {
    public static void main(String[]args){
        // Crea el objeto Scanner para capturar lo que el usuario escribe por teclado
        Scanner scanner = new Scanner(System.in);
        // Almacena la opción elegida por el usuario en el menú
        int eleccion = 0;

        // Bucle principal: el programa se repite hasta que el usuario elija la opción 7 (Salir)
        while (eleccion != 7) {
            System.out.println("----------------------------------Menú----------------------------------------");
            // Bloque de texto (text block) que muestra todas las operaciones disponibles
            System.out.println("""
            1. Sumar dos números complejos.
            2. Restar dos números complejos.
            3. Multiplicar dos números complejos.
            4. Dividir dos números complejos.
            5. Calcular el conjugado de un número complejo.
            6. Calcular potencias de la unidad imaginaria i.
            7. Salir.""");
            System.out.println("Elije una opción entre (1) y (7)");

            // Solicita y valida que la opción esté dentro del rango permitido (1 a 7)
            eleccion = pedirNumeroValido(scanner, 1, 7);

            // Si el usuario elige 7, rompe el ciclo while y termina el programa
            if (eleccion == 7) {
                break;
            }

            // Evalúa la opción elegida y ejecuta el bloque correspondiente
            switch (eleccion) {
                case 1: // Suma: (a + bi) + (c + di)
                    System.out.println("Escriba la parte REAL del primer número complejo.");
                    int a = pedirEntero(scanner);
                    System.out.println("Escriba la parte IMAGINARIA del primer número complejo (NO escriba la i).");
                    int b = pedirEntero(scanner);
                    System.out.println("Escriba la parte REAL del segundo número complejo.");
                    int c = pedirEntero(scanner);
                    System.out.println("Escriba la parte IMAGINARIA del segundo número complejo (NO escriba la i).");
                    int d = pedirEntero(scanner);
                    System.out.println("El resultado es:");
                    System.out.println(sumaComplejos(scanner, a, b, c, d));
                    break;

                case 2: // Resta: (a + bi) - (c + di)
                    System.out.println("Escriba la parte REAL del primer número complejo.");
                    int a1 = pedirEntero(scanner);
                    System.out.println("Escriba la parte IMAGINARIA del primer número complejo (NO escriba la i).");
                    int b1 = pedirEntero(scanner);
                    System.out.println("Escriba la parte REAL del segundo número complejo.");
                    int c1 = pedirEntero(scanner);
                    System.out.println("Escriba la parte IMAGINARIA del segundo número complejo (NO escriba la i).");
                    int d1 = pedirEntero(scanner);
                    System.out.println("El resultado es:");
                    System.out.println(restaComplejos(scanner, a1, b1, c1, d1));
                    break;

                case 3: // Multiplicación: (a + bi) * (c + di)
                    System.out.println("Escriba la parte REAL del primer número complejo.");
                    int a2 = pedirEntero(scanner);
                    System.out.println("Escriba la parte IMAGINARIA del primer número complejo (NO escriba la i).");
                    int b2 = pedirEntero(scanner);
                    System.out.println("Escriba la parte REAL del segundo número complejo.");
                    int c2 = pedirEntero(scanner);
                    System.out.println("Escriba la parte IMAGINARIA del segundo número complejo (NO escriba la i).");
                    int d2 = pedirEntero(scanner);
                    System.out.println("El resultado es:");
                    System.out.println(multiComplejos(scanner, a2, b2, c2, d2));
                    break;

                case 4: // División: (a + bi) / (c + di)
                    System.out.println("Escriba la parte REAL del primer número complejo.");
                    int a3 = pedirEntero(scanner);
                    System.out.println("Escriba la parte IMAGINARIA del primer número complejo (NO escriba la i).");
                    int b3 = pedirEntero(scanner);
                    System.out.println("Escriba la parte REAL del segundo número complejo.");
                    int c3 = pedirEntero(scanner);
                    System.out.println("Escriba la parte IMAGINARIA del segundo número complejo (NO escriba la i).");
                    int d3 = pedirEntero(scanner);
                    System.out.println("El resultado es:");
                    System.out.println(diviComplejos(scanner, a3, b3, c3, d3));
                    break;

                case 5: // Conjugado: dado a + bi, su conjugado es a - bi
                    System.out.println("Escriba la parte REAL del primer número complejo.");
                    int a4 = pedirEntero(scanner);
                    System.out.println("Escriba la parte IMAGINARIA del primer número complejo (NO escriba la i).");
                    int b4 = pedirEntero(scanner);
                    System.out.println(conjuComplejos(scanner, a4, b4));
                    break;

                case 6: // Potencia de i: calcula i^a5
                    System.out.println("Escriba la potencia.");
                    int a5 = pedirEntero(scanner);
                    System.out.println(potencia(scanner, a5));
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * Lee una línea de texto y garantiza que se convierta en un número entero (int).
     * Vuelve a preguntar si la entrada está vacía o no es un entero válido.
     */
    public static int pedirEntero(Scanner scanner) {
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            // Lee la línea completa y elimina espacios en blanco al inicio y al final
            String entrada = scanner.nextLine().trim();

            // Verifica que el usuario haya escrito algo
            if (entrada.isEmpty()) {
                System.out.print("No escribiste nada. Escribe un número entero: ");
                continue;
            }

            try {
                // Intenta convertir el texto a int; si falla, lanza NumberFormatException
                numero = Integer.parseInt(entrada);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor escribe solo un número entero: ");
            }
        }
        return numero;
    }

    /**
     * Solicita un número entero y valida que esté dentro del intervalo [min, max].
     */
    public static int pedirNumeroValido(Scanner scanner, int min, int max) {
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.print("No escribiste nada. Elige un número (" + min + "-" + max + "): ");
                continue;
            }

            try {
                numero = Integer.parseInt(entrada);

                // Comprueba que el entero esté dentro del rango permitido
                if (numero >= min && numero <= max) {
                    valido = true;
                } else {
                    System.out.print("Número fuera de rango. Elige entre " + min + " y " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor escribe solo un número entero (" + min + "-" + max + "): ");
            }
        }
        return numero;
    }

    /**
     * Suma dos números complejos (a + bi) + (c + di):
     * Parte real = a + c | Parte imaginaria = b + d
     */
    public static String sumaComplejos(Scanner scanner, int a, int b, int c, int d) {
        int sumaReales = (a + c);
        int sumaComplejos = (b + d);
        String resultado = sumaReales + "" + sumaComplejos + "i.";
        return resultado;
    }

    /**
     * Resta dos números complejos (a + bi) - (c + di):
     * Parte real = a - c | Parte imaginaria = b - d
     */
    public static String restaComplejos(Scanner scanner, int a, int b, int c, int d) {
        int restaReales = (a - c);
        int restaComplejos = (b - d);
        String resultado = restaReales + "" + restaComplejos + "i.";
        return resultado;
    }

    /**
     * Multiplica dos números complejos (a + bi) * (c + di):
     * Usando i^2 = -1 -> Real = ac - bd | Imaginaria = ad + bc
     */
    public static String multiComplejos(Scanner scanner, int a, int b, int c, int d) {
        int multiReales = ((a * c) - (b * d));
        int multiComplejos = ((a * d) + (b * c));
        String resultado = multiReales + "" + multiComplejos + "i.";
        return resultado;
    }

    /**
     * Divide dos números complejos (a + bi) / (c + di) multiplicando arriba y abajo
     * por el conjugado del denominador (c - di):
     * Denominador = c^2 + d^2 | Numerador = (ac + bd) + (bc - ad)i
     */
    public static String diviComplejos(Scanner scanner, int a, int b, int c, int d) {
        int denominador = (c * c) + (d * d);
        // Evita la división entre cero si el segundo número complejo es 0 + 0i
        if (denominador == 0) {
            return "No se puede dividir entre 0 + 0i.";
        }
        int diviReales = (a * c) + (b * d);
        int diviComplejos = (b * c) - (a * d);
        // Se hace cast a double para obtener decimales y no truncar la división entera
        double rReales = (double) diviReales / denominador;
        double rComplejos = (double) diviComplejos / denominador;
        String resultado = rReales + "" + rComplejos + "i.";
        return resultado;
    }

    /**
     * Calcula el conjugado de a + bi, que consiste en cambiar el signo de la parte imaginaria (a - bi).
     */
    public static String conjuComplejos(Scanner scanner, int a, int b) {
        int cnj = b * -1;
        if (cnj > 0) {
            System.out.println("El número original era: ");
            System.out.println(a + " " + b + "i.");
            System.out.println("El número conjugado es:");
            String resultado = a + " + " + cnj + "i.";
            return resultado;
        } else {
            System.out.println("El número original era: ");
            System.out.println(a + " + " + b + "i.");
            System.out.println("El número conjugado es:");
            String resultado = a + " " + cnj + "i.";
            return resultado;
        }
    }

    /**
     * Calcula i^a aprovechando que las potencias de i se repiten cada 4:
     * i^0 = 1, i^1 = i, i^2 = -1, i^3 = -i.
     * La fórmula ((a % 4) + 4) % 4 maneja correctamente tanto exponentes positivos como negativos.
     */
    public static String potencia(Scanner scanner, int a) {
        int mod = ((a % 4) + 4) % 4;
        if (mod == 0) {
            System.out.println("El resultado es:");
            String resultado = "1";
            return resultado;
        } else if (mod == 1) {
            System.out.println("El resultado es:");
            String resultado = "i";
            return resultado;
        } else if (mod == 2) {
            System.out.println("El resultado es: ");
            String resultado = "-1";
            return resultado;
        } else {
            System.out.println("El resultado es:");
            String resultado = "-i";
            return resultado;
        }
    }
}
