package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CorrosionCalculadora {
    public static void main(String[] args) {
        List<String> electrodos = new ArrayList<>(Arrays.asList(
                "Oro", "Agua", "Platino", "Plata", "Mercurio", "Ion de Hierro", "Hidroxilo",
                "Cobre", "Ion Estaño II", "Hidrógeno molecular", "Plomo", "Estaño", "Níquel", "Cobalto",
                "Cadmio", "Hierro", "Cromo", "Zinc", "Aluminio", "Magnesio", "Sodio"));
        List<String> simbolos = new ArrayList<>(Arrays.asList(
                "Au", "H2O", "Pt", "Ag", "Hg", "Fe2+", "OH-",
                "Cu", "Sn2+", "H2", "Pb", "Sn", "Ni", "Co",
                "Cd", "Fe", "Cr", "Zn", "Al", "Mg", "Na"));
        List<Integer> electrones = new ArrayList<>(Arrays.asList(
                3, 4, 2, 1, 2, 1, 4,
                2, 2, 2, 2, 2, 2, 2,
                2, 2, 3, 2, 3, 2, 1));

        Scanner scanner = new Scanner(System.in);
        int eleccion = 0;

        while (eleccion != 2) {
            System.out.println("----------------------------------Menú----------------------------------------");
            System.out.println("1. Calcular corrosión y celda electroquímica (ecuación de Nernst).");
            System.out.println("2. Salir.");
            System.out.println("Elije una opción entre (1) y (2)");
            eleccion = pedirNumeroValido(scanner, 1, 2);

            if (eleccion == 2) {
                break;
            }

            System.out.println("----------------------------------Elementos----------------------------------------");
            System.out.println("Oro...................Au");
            System.out.println("Agua..................H2O");
            System.out.println("Platino...............Pt");
            System.out.println("Plata.................Ag");
            System.out.println("Mercurio..............Hg");
            System.out.println("Ion de Hierro.........Fe2+");
            System.out.println("Hidroxilo.............OH-");
            System.out.println("Cobre.................Cu");
            System.out.println("Ion Estaño II.........Sn2+");
            System.out.println("Hidrógeno molecular...H2");
            System.out.println("Plomo.................Pb");
            System.out.println("Estaño................Sn");
            System.out.println("Níquel................Ni");
            System.out.println("Cobalto...............Co");
            System.out.println("Cadmio................Cd");
            System.out.println("Hierro................Fe");
            System.out.println("Cromo.................Cr");
            System.out.println("Zinc..................Zn");
            System.out.println("Aluminio..............Al");
            System.out.println("Magnesio..............Mg");
            System.out.println("Sodio.................Na");

            int primerElectrodo = pedirElementoValido(scanner, simbolos,
                    "Ingrese el elemento 1 (escriba su símbolo/iniciales, ej. Ni, Fe, Na): ");
            int segundoElectrodo = pedirElementoValido(scanner, simbolos,
                    "Ingrese el elemento 2 (escriba su símbolo/iniciales, ej. Ni, Fe, Na): ");

            while (primerElectrodo == segundoElectrodo) {
                System.out.println("Error, no puedes elegir el mismo elemento.");
                segundoElectrodo = pedirElementoValido(scanner, simbolos,
                        "Ingrese el elemento 2 (escriba su símbolo/iniciales, ej. Ni, Fe, Na): ");
            }

            double concentracion1 = pedirConcentracionValida(scanner,
                    "Ingrese la concentración de la solución del primer electrodo (en Molaridad M): ");
            double concentracion2 = pedirConcentracionValida(scanner,
                    "Ingrese la concentración de la solución del segundo electrodo (en Molaridad M): ");

            double potencialElectrodo1 = potencialElectrodo(primerElectrodo);
            double potencialElectrodo2 = potencialElectrodo(segundoElectrodo);

            // Primer electrodo = Ánodo (se oxida), Segundo electrodo = Cátodo (se reduce)
            String anodo = electrodos.get(primerElectrodo - 1);
            int electronesAnodo = electrones.get(primerElectrodo - 1);
            double concAnodo = concentracion1;

            String catodo = electrodos.get(segundoElectrodo - 1);
            int electronesCatodo = electrones.get(segundoElectrodo - 1);
            double concCatodo = concentracion2;

            double fuerzaElectromotriz = calcularFemEstandar(potencialElectrodo2, potencialElectrodo1);
            int nCelda = calcularMCM(electronesAnodo, electronesCatodo);

            // Fórmula del profesor: E = E° + (0.0592 / n) * log10([ion])
            double nernstAnodo = potencialElectrodo1 + ((0.0592 / electronesAnodo) * Math.log10(concAnodo));
            double nernstCatodo = potencialElectrodo2 + ((0.0592 / electronesCatodo) * Math.log10(concCatodo));
            double fem = fuerzaElectromotriz + ((0.0592 / nCelda) * Math.log10(concCatodo / concAnodo));

            double femEstandarRedondeada = Math.round(fuerzaElectromotriz * 100.0) / 100.0;
            double femRedondeada = Math.round(fem * 100.0) / 100.0;

            System.out.println("----------------------------------Resultados----------------------------------------");
            System.out.println("Ánodo (especie que se oxida): " + anodo);
            System.out.println("  Potencial (Nernst): " + (Math.round(nernstAnodo * 100.0) / 100.0) + " V");
            System.out.println("Cátodo (especie que se reduce): " + catodo);
            System.out.println("  Potencial (Nernst): " + (Math.round(nernstCatodo * 100.0) / 100.0) + " V");
            System.out.println("FEM Estándar (E° celda): " + femEstandarRedondeada + " V");
            System.out.println("FEM (Potencial de Celda): " + femRedondeada + " V");

            if (femRedondeada > 0) {
                System.out.println("Conclusión: La reacción es ESPONTÁNEA (ocurre por sí sola / hay corrosión).");
            } else if (femRedondeada < 0) {
                System.out.println("Conclusión: La reacción NO ES ESPONTÁNEA (FEM negativa, necesita energía externa).");
            } else {
                System.out.println("Conclusión: La reacción está en EQUILIBRIO (FEM = 0, sin tendencia neta a avanzar).");
            }
        }

        scanner.close();
    }

    public static int pedirElementoValido(Scanner scanner, List<String> simbolos, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("Error: no escribiste ningún elemento.");
                continue;
            }

            if (entrada.matches("\\d+")) {
                System.out.println("Error: no se permiten números. Escribe el símbolo exacto del elemento (ej. Ni, Fe, Na).");
                continue;
            }

            if (simbolos.contains(entrada)) {
                return simbolos.indexOf(entrada) + 1;
            }

            boolean coincideIgnorandoMayusculas = false;
            for (String s : simbolos) {
                if (s.equalsIgnoreCase(entrada)) {
                    coincideIgnorandoMayusculas = true;
                    break;
                }
            }

            if (coincideIgnorandoMayusculas) {
                System.out.println("Error: respeta las mayúsculas y minúsculas exactas del símbolo (ej. Ni, Fe, Na).");
            } else {
                System.out.println("Error: dato inválido. Escribe exactamente uno de los símbolos mostrados (ej. Ni, Fe, Na).");
            }
        }
    }

    public static int pedirNumeroValido(Scanner scanner, int min, int max) {
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.print("No escribiste nada. Elige un número (" + min + "-" + max + "): ");
                continue;
            }

            if (!entrada.matches("\\d+")) {
                System.out.print("Entrada inválida. Escribe solo números enteros sin letras ni signos (" + min + "-" + max + "): ");
                continue;
            }

            try {
                numero = Integer.parseInt(entrada);
                if (numero >= min && numero <= max) {
                    valido = true;
                } else {
                    System.out.print("Número fuera de rango. Elige entre " + min + " y " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Escribe solo un número entero (" + min + "-" + max + "): ");
            }
        }
        return numero;
    }

    public static double pedirConcentracionValida(Scanner scanner, String mensaje) {
        double conc = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim().replace(',', '.');

            if (entrada.isEmpty()) {
                System.out.println("Error: no escribiste ninguna concentración.");
                continue;
            }

            if (!entrada.matches("-?(\\d+(\\.\\d+)?|\\.\\d+)")) {
                System.out.println("Error: escribe solo un número decimal válido sin letras ni símbolos.");
                continue;
            }

            try {
                conc = Double.parseDouble(entrada);
                if (Double.isNaN(conc) || Double.isInfinite(conc)) {
                    System.out.println("Error: el número no es válido.");
                } else if (conc <= 0) {
                    System.out.println("Error: la concentración debe ser mayor que 0.");
                } else {
                    valido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: escribe un número válido.");
            }
        }
        return conc;
    }

    public static double potencialElectrodo(int electrodo) {
        switch (electrodo) {
            case 1: return 1.498;
            case 2: return 1.229;
            case 3: return 1.200;
            case 4: return 0.799;
            case 5: return 0.788;
            case 6: return 0.771;
            case 7: return 0.401;
            case 8: return 0.337;
            case 9: return 0.150;
            case 10: return 0.000;
            case 11: return -0.126;
            case 12: return -0.136;
            case 13: return -0.250;
            case 14: return -0.277;
            case 15: return -0.403;
            case 16: return -0.440;
            case 17: return -0.744;
            case 18: return -0.763;
            case 19: return -1.662;
            case 20: return -2.363;
            case 21: return -2.714;
            default: return 0.0;
        }
    }

    public static double calcularFemEstandar(double potencialCatodo, double potencialAnodo) {
        return potencialCatodo - potencialAnodo;
    }

    public static int calcularMCM(int a, int b) {
        if (a <= 0 || b <= 0) {
            return 1;
        }
        int mcm = 1;
        int i = 2;
        while (i <= a || i <= b) {
            if (a % i == 0 || b % i == 0) {
                mcm = mcm * i;
                if (a % i == 0) a = a / i;
                if (b % i == 0) b = b / i;
            } else {
                i++;
            }
        }
        return mcm;
    }
}