package org.example;

import java.util.Scanner;

public class ModEjerUno {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa un año:");
        int ano = scanner.nextInt();
        if (ano%4==0) {
            System.out.println(ano + " es año BISIESTO");
        } else if (ano%100==0) {
            System.out.println(ano + " es año NO BISIESTO");
        } else if(ano%400==0){
            System.out.println(ano + " es año BISIESTO");
        }else{
            System.out.println(ano + " es año NO BISIESTO");
        }
    }
}
