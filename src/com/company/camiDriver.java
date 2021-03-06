package com.company;

import java.util.Scanner;

/**
 *
 * @author adri
 */

public class CamiDriver {
    public void run() {
        System.out.println("TEST CLASE CAMI");
        System.out.println("Creem un cami nou:");
        String s;
        Cami test = new Cami();
        System.out.println("Introdueix el camp cami:");
        Scanner capt = new Scanner(System.in);
        s = capt.next();
        test.set_cami(s);
        System.out.println("Introdueix el camp Descripcio");
        s = capt.next();
        test.set_descripcio(s);
        Integer menu = 99;
        while (menu != 0) {
            System.out.println("Selecciona una opció:");
            System.out.println("1:Cambiar atribut Cami.");
            System.out.println("2:Cambiar atribut Descripcio.");
            System.out.println("3:Consultar atribut Cami.");
            System.out.println("4:Consultar atribut Descripcio.");
            System.out.println("5.Consultar la longitud del atribut Cami.");
            System.out.println("6.Comprobar si el atribut Cami es concatenable amb un altre string.");
            System.out.println("7.Concatenar el atribut Cami amb un string.");
            System.out.println("8.Comprovar si es un cami valid.");
            System.out.println("9.Invertir atribut cami.");
            System.out.println("0.Sortir.");
            s = capt.next();
            menu = Integer.parseInt(s);
            switch (menu) {
                case 1:
                    System.out.println("Introdueix el camp cami:");
                    s = capt.next();
                    test.set_cami(s);
                    break;
                case 2:
                    s = capt.next();
                    test.set_descripcio(s);
                    break;
                case 3:
                    System.out.println("L'atribut Cami es:" + test.get_cami());
                    break;
                case 4:
                    System.out.println("L'atribut Descripcio es:" + test.get_descripcio());
                    break;
                case 5:
                    System.out.println("La longitud de l'atribut Cami es:" + test.get_longitud());
                    break;
                case 6:
                    System.out.println("Introdueix un cami a concatenar:");
                    s = capt.next();
                    Boolean b = test.es_concatenable(s);
                    if (b) System.out.println("l'atribut Cami es concatenable amb " + s);
                    else System.out.println("l'atribut Cami NO es concatenable amb " + s);
                    break;
                case 7:
                    System.out.println("Introdueix un cami a concatenar:");
                    s = capt.next();
                    System.out.println("El atribut Cami es " + test.get_cami());
                    System.out.println("El cami a concatenar es " + s);
                    test.concatenar(s);
                    System.out.println("El resultat de concatenar es " + test.get_cami());
                    break;
                case 8:
                    System.out.println("Introdueix un cami per a comprovar la se va validesa");
                    s = capt.next();
                    if (test.cami_valid(s)) System.out.println("El cami " + s + " es valid");
                    else System.out.println("El cami " + s + " NO es valid");
                    break;
                case 9:
                    test.invertir();
                    System.out.println("L'atribut Cami es:" + test.get_cami());

            }
        }
    }
}
