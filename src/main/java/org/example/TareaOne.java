package org.example;
/* @Version: 1.0.0
   @Autor: Aymara Guzmán Velázquez
   @Date: 25/09/2026
*/

import java.util.Scanner;

public class TareaOne {
    public static void main(String[] args){
        Scanner scanner= new Scanner(System.in);
        /*String nom="Aymara";
        int edad=17;
        double estatura= 1.57;
        char inicial='A';
        boolean sexo= true;
        int x=80;
        int y=20;
        int suma= x+y;
        int resta= x-y;
        double divi= x/y;
        System.out.println("Yo soy "+nom+","+" tengo "+edad+" años,"+" mido "+estatura+"cm, mi inicial es "+inicial+", sexo: "+sexo
                +". Resultados suma: "+suma+", resta: "+resta+", división: "+divi);*/
        System.out.println("Ingrese su nombre");
        String nom= scanner.nextLine();
        System.out.println("Ingrese su edad");
        int edad= scanner.nextInt();
        System.out.println("Ingrese su estatura");
        double estatura= scanner.nextDouble();
        System.out.println("Ingrese su inicial");
        String inicial= scanner.next();
        System.out.println("Ingrese su sexo");
        String sex= scanner.next();
        System.out.println("Ingrese un número");
        double nUno= scanner.nextDouble();
        System.out.println("Ingrese un número");
        System.out.println("Ingrese la cantidad de moles");
        Double nDos= scanner.nextDouble();
        double suma= nUno+nDos;
        double resta= nUno-nDos;
        double divi= nUno/nDos;
        System.out.println("Ingrese la cantidad de moles");
        double mol= scanner.nextDouble();
        final double AVO = 6.022e23;
        double particula= mol * AVO;
        System.out.println("El número de partículas es: "+particula);
        System.out.println("Yo soy "+nom+","+" tengo "+edad+" años,"+" mido "+estatura+"cm, mi inicial es "+inicial+", sexo: "+sex
                +". Resultados suma: "+suma+", resta: "+resta+", división: "+divi);
        System.out.println("El número de partículas es: "+particula);

    }
}
