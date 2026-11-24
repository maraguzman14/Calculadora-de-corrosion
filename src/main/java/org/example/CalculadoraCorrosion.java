package org.example;

import java.util.Scanner;

public class CalculadoraCorrosion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String electrodo1 = "";
        String electrodo2 = "";
        double conc1 = 0;
        double conc2 = 0;

        System.out.println("=================================================");
        System.out.println("   CÁLCULO DE CORROSIÓN Y CELDA ELECTROQUÍMICA   ");
        System.out.println("=================================================");
        System.out.println("Lista de electrodos disponibles (escriba el símbolo EXACTO):");
        System.out.println("Au, H2O, Pt, Ag, Hg, Fe2+, OH-, Cu, Sn2+, H2, Pb, Sn, Ni, Co, Cd, Fe, Cr, Zn, Al, Mg, Na");

        boolean validoE1 = false;
        while (!validoE1) {
            System.out.print("Seleccione el primer electrodo: ");
            electrodo1 = scanner.nextLine();

            if (esValido(electrodo1)) {
                validoE1 = true;
            } else {
                System.out.println("ERROR: El electrodo no existe o no está escrito correctamente (revisa mayúsculas). Intenta de nuevo.");
            }
        }

        // --- PEDIR EL SEGUNDO ELECTRODO (CON VALIDACIÓN Y QUE NO SEA IGUAL) ---
        boolean validoE2 = false;
        while (!validoE2) {
            System.out.print("Seleccione el segundo electrodo: ");
            electrodo2 = scanner.nextLine();

            if (!esValido(electrodo2)) {
                System.out.println("ERROR: El electrodo no existe o no está escrito correctamente. Intenta de nuevo.");
            } else if (electrodo1.equals(electrodo2)) {
                System.out.println("ERROR: No puedes elegir el mismo electrodo dos veces. Intenta de nuevo.");
            } else {
                validoE2 = true;
            }
        }

        System.out.print("Ingrese la concentración de la solución de " + electrodo1 + " (en Molaridad M): ");
        conc1 = scanner.nextDouble();

        System.out.print("Ingrese la concentración de la solución de " + electrodo2 + " (en Molaridad M): ");
        conc2 = scanner.nextDouble();
        scanner.nextLine();

        // --- LÓGICA PARA SABER QUIÉN ES ÁNODO Y QUIÉN CÁTODO ---
        double e1 = obtenerPotencial(electrodo1);
        double e2 = obtenerPotencial(electrodo2);

        String anodo, catodo;
        double concAnodo, concCatodo;
        double eAnodo, eCatodo;

        // El que tiene el potencial MÁS NEGATIVO (menor) es el ÁNODO
        if (e1 < e2) {
            anodo = electrodo1;
            concAnodo = conc1;
            eAnodo = e1;
            catodo = electrodo2;
            concCatodo = conc2;
            eCatodo = e2;
        } else {
            anodo = electrodo2;
            concAnodo = conc2;
            eAnodo = e2;
            catodo = electrodo1;
            concCatodo = conc1;
            eCatodo = e1;
        }

        // --- APLICANDO LA ECUACIÓN DE NERNST (Método exacto) ---
        int nAnodo = obtenerElectrones(anodo);
        int nCatodo = obtenerElectrones(catodo);

        // 1. Calculamos el Potencial Estándar de la Celda (FEM°)
        // Como la tabla es de oxidación, el cátodo necesita cambiar de signo
        double femEstandar = (-1 * eCatodo) - eAnodo;

        // 2. Calculamos el Potencial de Nernst para cada electrodo (solo para mostrarlo en pantalla)
        double eNernstAnodo = eAnodo + ((0.0592 / nAnodo) * Math.log10(concAnodo));
        double eNernstCatodo = (-1 * eCatodo) + ((0.0592 / nCatodo) * Math.log10(concCatodo));

        // 3. Calculamos la FEM final usando la fórmula general de la celda (Nernst)
        // Usamos n = 2 porque ambos transfieren 2 electrones (si fueran diferentes, se usa el mcm)
        int nCelda = 2;
        double fem = femEstandar - ((0.0592 / nCelda) * Math.log10(concAnodo / concCatodo));


        // --- IMPRIMIR LOS RESULTADOS ---
        System.out.println("=================================================");
        System.out.println("                 RESULTADOS                      ");
        System.out.println("=================================================");
        System.out.println("Ánodo (se oxida):    " + anodo);
        System.out.println("  Semirreacción:    " + obtenerReaccion(anodo));
        System.out.println("  Concentración:    " + concAnodo + " M");
        System.out.println("  Potencial (Nernst):" + eNernstAnodo + " V");

        System.out.println("Cátodo (se reduce):  " + catodo);
        System.out.println("  Semirreacción:    " + obtenerReaccion(catodo));
        System.out.println("  Concentración:    " + concCatodo + " M");
        System.out.println("  Potencial (Nernst):" + eNernstCatodo + " V");

        System.out.printf("FEM (Potencial de Celda): %.4f V\n", fem);

        // Evaluar si es espontánea
        if (fem > 0) {
            System.out.println("Conclusión: La reacción es ESPONTÁNEA (ocurre por sí sola).");
        } else if (fem < 0) {
            System.out.println("Conclusión: La reacción NO ES ESPONTÁNEA (necesita energía externa).");
        } else {
            System.out.println("Conclusión: La reacción está en EQUILIBRIO.");
        }
        scanner.close();
    }
    public static double obtenerPotencial(String electrodo) {
        switch (electrodo) {
            case "Au": return 1.498;
            case "H2O": return 1.229;
            case "Pt": return 1.200;
            case "Ag": return 0.799;
            case "Hg": return 0.788;
            case "Fe2+": return 0.771;
            case "OH-": return 0.401;
            case "Cu": return 0.337;
            case "Sn2+": return 0.150;
            case "H2": return 0.000;
            case "Pb": return -0.126;
            case "Sn": return -0.136;
            case "Ni": return -0.250;
            case "Co": return -0.277;
            case "Cd": return -0.403;
            case "Fe": return -0.440;
            case "Cr": return -0.744;
            case "Zn": return -0.763;
            case "Al": return -1.662;
            case "Mg": return -2.363;
            case "Na": return -2.714;
            default: return 0.0;
        }
    }

    // Este método devuelve cuántos electrones (n) se transfieren en la reacción
    public static int obtenerElectrones(String electrodo) {
        switch (electrodo) {
            case "Au": return 3;
            case "H2O": return 4;
            case "Pt": return 2;
            case "Ag": return 1;
            case "Hg": return 2;
            case "Fe2+": return 1;
            case "OH-": return 4;
            case "Cu": return 2;
            case "Sn2+": return 2;
            case "H2": return 2;
            case "Pb": return 2;
            case "Sn": return 2;
            case "Ni": return 2;
            case "Co": return 2;
            case "Cd": return 2;
            case "Fe": return 2;
            case "Cr": return 3;
            case "Zn": return 2;
            case "Al": return 3;
            case "Mg": return 2;
            case "Na": return 1;
            default: return 1;
        }
    }

    // Este método devuelve el texto de la semirreacción tal como está en la tabla
    public static String obtenerReaccion(String electrodo) {
        switch (electrodo) {
            case "Au": return "Au -> Au3+ + 3e-";
            case "H2O": return "2H2O -> O2 + 4H+ + 4e-";
            case "Pt": return "Pt -> Pt2+ + 2e-";
            case "Ag": return "Ag -> Ag+ + e-";
            case "Hg": return "2Hg -> Hg2+ + 2e-";
            case "Fe2+": return "Fe2+ -> Fe3+ + e-";
            case "OH-": return "4OH- -> O2 + 2H2O + 4e-";
            case "Cu": return "Cu -> Cu2+ + 2e-";
            case "Sn2+": return "Sn2+ -> Sn4+ + 2e-";
            case "H2": return "H2 -> 2H+ + 2e-";
            case "Pb": return "Pb -> Pb2+ + 2e-";
            case "Sn": return "Sn -> Sn2+ + 2e-";
            case "Ni": return "Ni -> Ni2+ + 2e-";
            case "Co": return "Co -> Co2+ + 2e-";
            case "Cd": return "Cd -> Cd2+ + 2e-";
            case "Fe": return "Fe -> Fe2+ + 2e-";
            case "Cr": return "Cr -> Cr3+ + 3e-";
            case "Zn": return "Zn -> Zn2+ + 2e-";
            case "Al": return "Al -> Al3+ + 3e-";
            case "Mg": return "Mg -> Mg2+ + 2e-";
            case "Na": return "Na -> Na+ + e-";
            default: return "";
        }
    }

    // Este método valida si el texto que escribió el usuario existe en la tabla
    public static boolean esValido(String electrodo) {
        return electrodo.equals("Au") || electrodo.equals("H2O") || electrodo.equals("Pt") ||
                electrodo.equals("Ag") || electrodo.equals("Hg") || electrodo.equals("Fe2+") ||
                electrodo.equals("OH-") || electrodo.equals("Cu") || electrodo.equals("Sn2+") ||
                electrodo.equals("H2") || electrodo.equals("Pb") || electrodo.equals("Sn") ||
                electrodo.equals("Ni") || electrodo.equals("Co") || electrodo.equals("Cd") ||
                electrodo.equals("Fe") || electrodo.equals("Cr") || electrodo.equals("Zn") ||
                electrodo.equals("Al") || electrodo.equals("Mg") || electrodo.equals("Na");
    }
}
