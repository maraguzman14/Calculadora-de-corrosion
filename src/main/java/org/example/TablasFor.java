package org.example;

import java.util.Scanner;

public class TablasFor {
    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        try {
            for (int i=0; i<=10; i++) {
                for (int j=0; j<=10; j++){
                    System.out.println(i+" x "+j+" = "+(i*j));
                }
            }
        } catch (Exception e) {
            System.out.println("Ingrese valores numéricos, por favor");

        }
    }
}
