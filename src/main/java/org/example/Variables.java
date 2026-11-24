package org.example;
/*
@Version: 1.0.0
@Autor: Aymara Guzmán Velázquez
@Date: 28/09/26
 */
import java.util.Scanner;

public class Variables {
    public static void main(String[]args){
            Scanner scanner= new Scanner(System.in);
        //Constantes siempre con mayúsculas//
        final double VALORPI=3.1416;
        final double EULER=2.7182 ;
        final double AVOGADRO= 6.022e23;
        double moles= 1.2;
        double calculo= moles * AVOGADRO;

        System.out.println("Calcula el número de partículas (N)!!");
        System.out.println("El número de partículas es: "+calculo);

        System.out.println("Ingrese la cantidad de moles");
        double mol= scanner.nextDouble();
        final double AVO = 6.022e23;
        double particula= mol * AVO;
        System.out.println("El número de partículas es: "+particula);


    }
}
