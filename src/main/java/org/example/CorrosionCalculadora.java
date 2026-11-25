package org.example;
/*
@Version: 1.0.0
@Autores: Aymara Guzmán Velázquez, Sandra Elisa García Martínez y Christian Uzziel Silva Aguilar
@Date: 24/11/26
 */
import java.util.*;

public class CorrosionCalculadora {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        String[] simbolos = {
                "Au", "H2O", "Pt", "Ag", "Hg", "Fe2+", "OH-", "Cu", "Sn2+", "H2",
                "Pb", "Sn", "Ni", "Co", "Cd", "Fe", "Cr", "Zn", "Al", "Mg", "Na"
        };

        String[] nombres = {
                "Oro", "Agua", "Platino", "Plata", "Mercurio", "Hierro (II)", "Hidroxido",
                "Cobre", "Estano (II)", "Hidrogeno", "Plomo", "Estano", "Niquel",
                "Cobalto", "Cadmio", "Hierro", "Cromo", "Zinc", "Aluminio", "Magnesio", "Sodio"
        };

        String[] semirreaccionesOxidacion = {
                "Au -> Au3+ + 3e-",
                "2H2O -> O2 + 4H+ + 4e-",
                "Pt -> Pt2+ + 2e-",
                "Ag -> Ag+ + e-",
                "2Hg -> Hg2(2+) + 2e-",
                "Fe2+ -> Fe3+ + e-",
                "4(OH)- -> O2 + 2H2O + 4e-",
                "Cu -> Cu2+ + 2e-",
                "Sn2+ -> Sn4+ + 2e-",
                "H2 -> 2H+ + 2e-",
                "Pb -> Pb2+ + 2e-",
                "Sn -> Sn2+ + 2e-",
                "Ni -> Ni2+ + 2e-",
                "Co -> Co2+ + 2e-",
                "Cd -> Cd2+ + 2e-",
                "Fe -> Fe2+ + 2e-",
                "Cr -> Cr3+ + 3e-",
                "Zn -> Zn2+ + 2e-",
                "Al -> Al3+ + 3e-",
                "Mg -> Mg2+ + 2e-",
                "Na -> Na+ + e-"
        };

        String[] semirreaccionesReduccion = {
                "Au3+ + 3e- -> Au",
                "O2 + 4H+ + 4e- -> 2H2O",
                "Pt2+ + 2e- -> Pt",
                "Ag+ + e- -> Ag",
                "Hg2(2+) + 2e- -> 2Hg",
                "Fe3+ + e- -> Fe2+",
                "O2 + 2H2O + 4e- -> 4(OH)-",
                "Cu2+ + 2e- -> Cu",
                "Sn4+ + 2e- -> Sn2+",
                "2H+ + 2e- -> H2",
                "Pb2+ + 2e- -> Pb",
                "Sn2+ + 2e- -> Sn",
                "Ni2+ + 2e- -> Ni",
                "Co2+ + 2e- -> Co",
                "Cd2+ + 2e- -> Cd",
                "Fe2+ + 2e- -> Fe",
                "Cr3+ + 3e- -> Cr",
                "Zn2+ + 2e- -> Zn",
                "Al3+ + 3e- -> Al",
                "Mg2+ + 2e- -> Mg",
                "Na+ + e- -> Na"
        };

        double[] eOxidacion = {
                1.498,  1.229,  1.200,  0.799,  0.788,  0.771,  0.401,
                0.337,  0.150,  0.000, -0.126, -0.136, -0.250, -0.277,
                -0.403, -0.440, -0.744, -0.763, -1.662, -2.363, -2.714
        };

        int[] electrones = {
                3, 4, 2, 1, 2, 1, 4, 2, 2, 2,
                2, 2, 2, 2, 2, 2, 3, 2, 3, 2, 1
        };

        System.out.println("=== ELECTRODOS DISPONIBLES (SIMBOLO EXACTO) ===");
        for (int i = 0; i < simbolos.length; i++) {
            System.out.printf("%-5s -> %-12s (E° ox: %+.3f V, n: %d)%n",
                    simbolos[i], nombres[i], eOxidacion[i], electrones[i]);
        }

        int idx1 = pedirElectrodoValido(sc, simbolos, -1,
                "\nIngresa el simbolo exacto del primer electrodo (ej. Zn, Ni, Au): ");
        double conc1 = pedirNumeroPositivo(sc,
                "Ingresa la concentracion (C_ion en M) de " + simbolos[idx1] + ": ");

        int idx2 = pedirElectrodoValido(sc, simbolos, idx1,
                "\nIngresa el simbolo exacto del segundo electrodo (diferente al primero): ");
        double conc2 = pedirNumeroPositivo(sc,
                "Ingresa la concentracion (C_ion en M) de " + simbolos[idx2] + ": ");

        int idxAnodo, idxCatodo;
        double concAnodo, concCatodo;

        if (idx1 > idx2) {
            idxAnodo = idx1;
            concAnodo = conc1;
            idxCatodo = idx2;
            concCatodo = conc2;
        } else {
            idxAnodo = idx2;
            concAnodo = conc2;
            idxCatodo = idx1;
            concCatodo = conc1;
        }

        double e0Catodo = -eOxidacion[idxCatodo];
        double e0Anodo = eOxidacion[idxAnodo];
        double e0Celda = e0Catodo + e0Anodo;

        int nAnodo = electrones[idxAnodo];
        int nCatodo = electrones[idxCatodo];

        double eNoEstandarAnodo = eOxidacion[idxAnodo] + (0.0592 / nAnodo) * Math.log10(concAnodo);
        double eNoEstandarCatodo = -(eOxidacion[idxCatodo] + (0.0592 / nCatodo) * Math.log10(concCatodo));
        double eNoEstandarCelda = eNoEstandarCatodo + eNoEstandarAnodo;

        // Mostrar resultados
        System.out.println("\n================ RESULTADOS ================");
        System.out.println("Anodo: " + simbolos[idxAnodo] + " (" + nombres[idxAnodo] + ") | Concentracion: " + concAnodo + " M");
        System.out.println("Semirreaccion del anodo: " + semirreaccionesOxidacion[idxAnodo]);
        System.out.println("Catodo: " + simbolos[idxCatodo] + " (" + nombres[idxCatodo] + ") | Concentracion: " + concCatodo + " M");
        System.out.println("Semirreaccion del catodo: " + semirreaccionesReduccion[idxCatodo]);
        System.out.println("Oxidacion: " + semirreaccionesOxidacion[idxAnodo]);
        System.out.println("Reduccion: " + semirreaccionesReduccion[idxCatodo]);
        System.out.printf("Potencial estandar (E° celda): %+.4f V%n", e0Celda);
        System.out.printf("Potencial no estandar del anodo (E anodo): %+.4f V%n", eNoEstandarAnodo);
        System.out.printf("Potencial no estandar del catodo (E catodo): %+.4f V%n", eNoEstandarCatodo);
        System.out.printf("Potencial no estandar final (E celda): %+.4f V%n", eNoEstandarCelda);

        System.out.println("\n--- Espontaneidad (segun potencial no estandar E) ---");
        imprimirEspontaneidad(eNoEstandarCelda);

        sc.close();
    }

    public static int pedirElectrodoValido(Scanner sc, String[] simbolos, int indiceProhibido, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.next().trim();

            int encontrado = -1;
            for (int i = 0; i < simbolos.length; i++) {
                if (simbolos[i].equals(entrada)) {
                    encontrado = i;
                    break;
                }
            }

            if (encontrado == -1) {
                System.out.println("Error: '" + entrada + "' no es un simbolo valido. " +
                        "Respeta mayusculas y minusculas (ejemplo: Au, no au ni AU).");
            } else if (encontrado == indiceProhibido) {
                System.out.println("Error: no puedes elegir el mismo electrodo dos veces.");
            } else {
                return encontrado;
            }
        }
    }

    public static double pedirNumeroPositivo(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (!sc.hasNextDouble()) {
                String invalido = sc.next();
                System.out.println("Error: '" + invalido + "' no es un numero valido. Ingresa un numero decimal (ej. 0.10).");
                continue;
            }
            double valor = sc.nextDouble();
            if (!Double.isFinite(valor) || valor <= 0) {
                System.out.println("Error: el valor debe ser un numero positivo mayor que 0.");
            } else {
                return valor;
            }
        }
    }

    public static void imprimirEspontaneidad(double eNoEstandar) {
        if (eNoEstandar > 0) {
            System.out.printf("E = %+.4f V -> La reaccion es ESPONTANEA porque es positivo y es aquella " +
                    "que ocurre por si sola, sin necesitar energia externa.%n", eNoEstandar);
        } else if (eNoEstandar < 0) {
            System.out.printf("E = %+.4f V -> La reaccion NO ES ESPONTANEA porque es negativo y " +
                    "necesita energia externa.%n", eNoEstandar);
        } else {
            System.out.printf("E = %.4f V -> La reaccion esta en EQUILIBRIO (potencial igual a 0).%n", eNoEstandar);
        }
    }
}