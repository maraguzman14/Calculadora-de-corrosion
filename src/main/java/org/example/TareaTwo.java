package org.example;
/*
@Version: 1.0.0
@Autor: Aymara Guzmán Velázquez
@Date: 04/10/26
 */

import java.util.Scanner;

public class TareaTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el número 1");
        double numUno = scanner.nextDouble();
        System.out.println("Introduce el número 2");
        double numDos = scanner.nextDouble();
        System.out.println("Introduce el número 3");
        double numTres = scanner.nextDouble();
        if (numUno == numDos && numDos == numTres) {
            System.out.println("Los tres números son iguales");
            // Número 1 y 2 son iguales
        } else if (numUno == numDos) {
            if (numUno > numTres) {
                System.out.println("El número uno y dos son iguales y son mayores a: " + numTres);
            } else {
                System.out.println("El número uno y dos son iguales y son menores a: " + numTres);
            }
            // Número 1 y 3 son iguales
        } else if (numUno == numTres) {
            if (numUno > numDos) {
                System.out.println("El número uno y tres son iguales y son mayores a: " + numDos);
            } else {
                System.out.println("El número uno y tres son iguales y son menores a: " + numDos);
            }
            // Número 2 y 3 son iguales
        } else if (numDos == numTres) {
            if (numDos > numUno) {
                System.out.println("El número dos y tres son iguales y son mayores a: " + numUno);
            } else {
                System.out.println("El número dos y tres son iguales y son menores a: " + numUno);
            }
            // Los tres números son diferentes
        } else {
            if (numUno > numDos && numUno > numTres) {
                System.out.println("El número mayor es: " + numUno);
            } else if (numDos > numUno && numDos > numTres) {
                System.out.println("El número mayor es: " + numDos);
            } else {
                System.out.println("El número mayor es: " + numTres);
            }
            if (numUno < numDos && numUno < numTres) {
                System.out.println("El número menor es: " + numUno);
            } else if (numDos < numUno && numDos < numTres) {
                System.out.println("El número menor es: " + numDos);
            } else {
                System.out.println("El número menor es: " + numTres);
            }
        }

    }
}
