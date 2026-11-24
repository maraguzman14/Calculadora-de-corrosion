package org.example;

import java.util.Scanner;

public class SumaDigitos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese un número:");
        int numUno = scanner.nextInt();
        int digito= numUno/100;
        System.out.println("El primer digito es:"+digito);
        int digitoUno=(numUno/10)%10;
        System.out.println("El segundo digito es:"+digitoUno);
        int digitoDos=numUno%10;
        System.out.println("El tercer digito es:"+digitoDos);
        int suma= digito+digitoUno+digitoDos;
        System.out.println("La suma es:"+suma);
    }

}
