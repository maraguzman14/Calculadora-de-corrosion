
package org.example;

import java.util.Locale;
import java.util.Scanner;

public class Calculadora {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;

            while (continuar) {
                mostrarMenu();
                int opcion = leerEnteroEnRango(scanner, "Selecciona una opción (1-5): ", 1, 5);

                switch (opcion) {
                    case 1:
                        calcularTasaCorrosionQuimica(scanner);
                        break;
                    case 2:
                        calcularMetalDisueltoPorEstequiometria(scanner);
                        break;
                    case 3:
                        calcularEspesorYVidaUtil(scanner);
                        break;
                    case 4:
                        evaluarElectrodoYRedox(scanner);
                        break;
                    case 5:
                        continuar = false;
                        System.out.println("\nCerrando calculadora de corrosión química. ¡Éxito!");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=================================================================");
        System.out.println("             CALCULADORA DE CORROSIÓN QUÍMICA (JAVA)           ");
        System.out.println("=================================================================");
        System.out.println("1. Tasa de corrosión química por pérdida de masa");
        System.out.println("2. Metal disuelto por estequiometría química (a Metal + b Reactivo)");
        System.out.println("3. Pérdida de espesor y vida útil estimada");
        System.out.println("4. Calcular corrosión y determinar Ánodo/Cátodo y Oxidación/Reducción");
        System.out.println("5. Salir");
        System.out.println("-----------------------------------------------------------------");
    }

    private static void calcularTasaCorrosionQuimica(Scanner scanner) {
        System.out.println("\n--- 1. Tasa de Corrosión Química por Pérdida de Masa ---");
        System.out.println("1. mm/año (K = 87.6    | W en mg, densidad en g/cm³, área en cm², tiempo en h)");
        System.out.println("2. mpy    (K = 3.45e6  | W en mg, densidad en g/cm³, área en cm², tiempo en h)");

        int unidad = leerEnteroEnRango(scanner, "Unidad de salida (1-2): ", 1, 2);
        double constanteK = (unidad == 1) ? 87.6 : 3_450_000.0;
        String unidadTexto = (unidad == 1) ? "mm/año" : "mpy";

        double perdidaMasaMg = leerDoubleNoNegativo(scanner, "Masa perdida W (mg, >= 0): ");
        double densidadGCm3 = leerDoublePositivo(scanner, "Densidad del material (g/cm³, > 0): ");
        double areaCm2 = leerDoublePositivo(scanner, "Área expuesta al medio químico A (cm², > 0): ");
        double tiempoHoras = leerDoublePositivo(scanner, "Tiempo de exposición t (horas, > 0): ");

        double denominador = densidadGCm3 * areaCm2 * tiempoHoras;
        double numerador = constanteK * perdidaMasaMg;

        if (!esFinito(numerador) || !esFinito(denominador) || denominador <= 0.0) {
            System.out.println("Error: Los valores exceden el rango numérico permitido.");
            return;
        }

        double tasa = numerador / denominador;
        if (!esFinito(tasa) || tasa < 0.0) {
            System.out.println("Error: No se pudo calcular una tasa válida.");
            return;
        }

        System.out.printf("%nResultado: Tasa de corrosión química = %.6f %s%n", tasa, unidadTexto);
        imprimirDiagnosticoElectroquimico(
                "Metal base (pérdida de masa por ataque químico)",
                perdidaMasaMg > 0.0
        );
    }

    private static void calcularMetalDisueltoPorEstequiometria(Scanner scanner) {
        System.out.println("\n--- 2. Metal Disuelto por Estequiometría Química ---");
        System.out.println("Modelo: a * Metal + b * Reactivo químico -> Productos");

        double coefMetalA = leerDoublePositivo(scanner, "Coeficiente estequiométrico del metal 'a' (> 0): ");
        double coefReactivoB = leerDoublePositivo(scanner, "Coeficiente estequiométrico del reactivo 'b' (> 0): ");
        double masaMolarMetal = leerDoublePositivo(scanner, "Masa molar del metal M_metal (g/mol, > 0): ");

        System.out.println("¿Cómo ingresarás la cantidad del reactivo químico consumido?");
        System.out.println("1. En moles (mol)");
        System.out.println("2. En masa (g) usando la masa molar del reactivo");

        int modoReactivo = leerEnteroEnRango(scanner, "Selecciona una opción (1-2): ", 1, 2);
        double molesReactivo;

        if (modoReactivo == 1) {
            molesReactivo = leerDoubleNoNegativo(scanner, "Moles de reactivo consumidos (mol, >= 0): ");
        } else {
            double masaReactivoG = leerDoubleNoNegativo(scanner, "Masa de reactivo consumida (g, >= 0): ");
            double masaMolarReactivo = leerDoublePositivo(scanner, "Masa molar del reactivo (g/mol, > 0): ");
            molesReactivo = masaReactivoG / masaMolarReactivo;
        }

        if (!esFinito(molesReactivo) || molesReactivo < 0.0) {
            System.out.println("Error: Cantidad de reactivo inválida.");
            return;
        }

        double molesMetalDisuelto = molesReactivo * (coefMetalA / coefReactivoB);
        double masaMetalDisueltaG = molesMetalDisuelto * masaMolarMetal;
        double masaMetalDisueltaMg = masaMetalDisueltaG * 1000.0;

        if (!esFinito(molesMetalDisuelto) || !esFinito(masaMetalDisueltaG) || !esFinito(masaMetalDisueltaMg)) {
            System.out.println("Error: Los resultados exceden el rango numérico permitido.");
            return;
        }

        System.out.printf("%nResultado:%n");
        System.out.printf("- Moles de reactivo consumidos : %.6f mol%n", molesReactivo);
        System.out.printf("- Moles de metal disuelto      : %.6f mol%n", molesMetalDisuelto);
        System.out.printf("- Masa de metal disuelto       : %.6f g (%.2f mg)%n", masaMetalDisueltaG, masaMetalDisueltaMg);

        imprimirDiagnosticoElectroquimico(
                "Metal disuelto (a * Metal)",
                masaMetalDisueltaG > 0.0
        );
    }

    private static void calcularEspesorYVidaUtil(Scanner scanner) {
        System.out.println("\n--- 3. Pérdida de Espesor y Vida Útil Estimada ---");
        System.out.println("1. Calcular pérdida de espesor (mm) a partir de tasa (mm/año) y tiempo (años)");
        System.out.println("2. Calcular vida útil estimada (años) a partir de espesor permisible (mm) y tasa (mm/año)");

        int tipo = leerEnteroEnRango(scanner, "Selecciona una opción (1-2): ", 1, 2);

        if (tipo == 1) {
            double tasaMmAnio = leerDoublePositivo(scanner, "Tasa de corrosión (mm/año, > 0): ");
            double tiempoAnios = leerDoubleNoNegativo(scanner, "Tiempo de servicio (años, >= 0): ");

            double perdidaEspesorMm = tasaMmAnio * tiempoAnios;
            if (!esFinito(perdidaEspesorMm)) {
                System.out.println("Error: El valor calculado excede el rango permitido.");
                return;
            }

            System.out.printf("%nResultado: Pérdida de espesor estimada = %.6f mm%n", perdidaEspesorMm);
            imprimirDiagnosticoElectroquimico(
                    "Superficie metálica con pérdida de espesor",
                    perdidaEspesorMm > 0.0
            );
        } else {
            double espesorPermisibleMm = leerDoublePositivo(scanner, "Espesor permisible / tolerancia a corrosión (mm, > 0): ");
            double tasaMmAnio = leerDoublePositivo(scanner, "Tasa de corrosión (mm/año, > 0): ");

            double vidaUtilAnios = espesorPermisibleMm / tasaMmAnio;
            if (!esFinito(vidaUtilAnios) || vidaUtilAnios <= 0.0) {
                System.out.println("Error: No se pudo calcular una vida útil válida.");
                return;
            }

            System.out.printf("%nResultado: Vida útil estimada = %.4f años (%.1f días)%n",
                    vidaUtilAnios, vidaUtilAnios * 365.25);
            imprimirDiagnosticoElectroquimico(
                    "Metal en servicio bajo corrosión uniforme",
                    true
            );
        }
    }

    private static void evaluarElectrodoYRedox(Scanner scanner) {
        System.out.println("\n--- 4. Determinación de Ánodo/Cátodo y Oxidación/Reducción ---");
        String especie = leerTextoNoVacio(scanner, "Nombre de la especie, metal o electrodo (ej. Hierro, Zinc, Oxígeno): ");

        System.out.println("Selecciona el criterio de evaluación:");
        System.out.println("1. Por comportamiento del metal / pérdida de masa o electrones");
        System.out.println("2. Por potencial de electrodo (E_celda o E_electrodo vs referencia)");
        System.out.println("3. Por estado de oxidación inicial y final");

        int criterio = leerEnteroEnRango(scanner, "Elige una opción (1-3): ", 1, 3);

        switch (criterio) {
            case 1:
                System.out.println("1. Pierde electrones / se disuelve / sufre corrosión");
                System.out.println("2. Gana electrones / se deposita / consume oxidante");
                int accion = leerEnteroEnRango(scanner, "Selecciona (1-2): ", 1, 2);

                boolean seOxida = (accion == 1);
                System.out.printf("%nEspecie evaluada: %s%n", especie);
                imprimirDiagnosticoElectroquimico(especie, seOxida);
                break;

            case 2:
                double potencialEspecieV = leerDoubleFinito(scanner, "Potencial de la especie / media celda (V): ");
                double potencialReferenciaV = leerDoubleFinito(scanner, "Potencial de la otra media celda o referencia (V): ");

                if (Math.abs(potencialEspecieV - potencialReferenciaV) < 1e-9) {
                    System.out.printf("%nEspecie evaluada: %s%n", especie);
                    System.out.println("Resultado: Equilibrio electroquímico (sin tendencia neta a oxidarse ni reducirse; ΔE ≈ 0 V).");
                    return;
                }

                boolean seOxidaPorPotencial = potencialEspecieV < potencialReferenciaV;
                double deltaE = Math.abs(potencialEspecieV - potencialReferenciaV);

                System.out.printf("%nEspecie evaluada: %s (E = %.4f V vs %.4f V | |ΔE| = %.4f V)%n",
                        especie, potencialEspecieV, potencialReferenciaV, deltaE);
                imprimirDiagnosticoElectroquimico(especie, seOxidaPorPotencial);
                break;

            case 3:
                double estadoInicial = leerDoubleFinito(scanner, "Estado de oxidación inicial (ej. 0 para Fe metal): ");
                double estadoFinal = leerDoubleFinito(scanner, "Estado de oxidación final (ej. +2 para Fe²⁺): ");

                if (Math.abs(estadoFinal - estadoInicial) < 1e-9) {
                    System.out.printf("%nEspecie evaluada: %s (sin cambio en estado de oxidación: %.2f)%n",
                            especie, estadoInicial);
                    System.out.println("Resultado: No hay transferencia neta de electrones (ni Ánodo/Oxidación ni Cátodo/Reducción).");
                    return;
                }

                boolean seOxidaPorEstado = estadoFinal > estadoInicial;
                double deltaEstado = Math.abs(estadoFinal - estadoInicial);

                System.out.printf("%nEspecie evaluada: %s (cambio de estado de oxidación: %.2f -> %.2f, Δ = %.2f)%n",
                        especie, estadoInicial, estadoFinal, deltaEstado);
                imprimirDiagnosticoElectroquimico(especie, seOxidaPorEstado);
                break;

            default:
                System.out.println("Criterio no válido.");
                break;
        }
    }

    private static void imprimirDiagnosticoElectroquimico(String elemento, boolean seOxida) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Diagnóstico final para: " + elemento);
        if (seOxida) {
            System.out.println("• Electrodo / Rol : ÁNODO (ocurre corrosión / disolución si es metal)");
            System.out.println("• Proceso redox   : SE OXIDA (pierde electrones, aumenta su estado de oxidación)");
        } else {
            System.out.println("• Electrodo / Rol : CÁTODO (superficie protegida o sitio de descarga catódica)");
            System.out.println("• Proceso redox   : SE REDUCE (gana electrones, disminuye su estado de oxidación)");
        }
        System.out.println("-----------------------------------------------------------------");
    }

    private static int leerEnteroEnRango(Scanner scanner, String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                scanner.nextLine();
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.printf("Error: Ingresa un número entero entre %d y %d.%n", min, max);
            } else {
                System.out.println("Error: Entrada no válida. Debes ingresar un número entero.");
                scanner.nextLine();
            }
        }
    }

    private static double leerDoublePositivo(Scanner scanner, String mensaje) {
        while (true) {
            double valor = leerDoubleFinito(scanner, mensaje);
            if (valor > 0.0) {
                return valor;
            }
            System.out.println("Error: El valor debe ser estrictamente mayor que 0.");
        }
    }

    private static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            double valor = leerDoubleFinito(scanner, mensaje);
            if (valor >= 0.0) {
                return valor;
            }
            System.out.println("Error: El valor no puede ser negativo (>= 0).");
        }
    }

    private static double leerDoubleFinito(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                if (esFinito(valor)) {
                    return valor;
                }
                System.out.println("Error: El número debe ser finito (sin NaN ni infinito).");
            } else {
                System.out.println("Error: Entrada no válida. Ingresa un número (usa punto '.' para decimales).");
                scanner.nextLine();
            }
        }
    }

    private static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Error: El texto no puede estar vacío.");
        }
    }

    private static boolean esFinito(double valor) {
        return !Double.isNaN(valor) && !Double.isInfinite(valor);
    }
}