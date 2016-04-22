package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ruben Bagan Benavides on 11/04/2016.
 */

public class MatriuDriver {
    ArrayList<Matriu> cjtMatrius;
    Scanner scan = new Scanner(System.in);

    private int llegir_enter(int minim, int maxim) {
        int n = scan.nextInt();
        while (n < minim || n > maxim) {
            System.out.println("Error: tens que escriure un nombre entre " + minim + " i " + maxim);
            n = scan.nextInt();
        }
        return n;
    }

    private void llistar_matrius() {
        System.out.println("Llistar matrius: ");
        int id = 0;
        for (Matriu m : cjtMatrius) {
            System.out.println("ID: " + id);
            m.print_matriu();
            System.out.println();
            ++id;
        }
    }

    private Matriu demanar_matriu(String msg) {
        llistar_matrius();
        System.out.println(msg);
        int id = llegir_enter(0, cjtMatrius.size() - 1);
        return cjtMatrius.get(id);
    }

    private int menu() {
        System.out.println("Per obtenir el llistat de identificadors de les matrius tria l'opcio 17");
        System.out.println("1\t Nova matriu");
        System.out.println("2\t Multiplicar");
        System.out.println("3\t Sumar");
        System.out.println("4\t Restar");
        System.out.println("5\t Transposar");
        System.out.println("6\t Normalitzar per files");
        System.out.println("7\t Normalitzar per columnes");
        System.out.println("8\t Obtenir fila i-èssima");
        System.out.println("9\t Obtenir columna i-èssima");
        System.out.println("10\t Inicialitzar fila i-èssima");
        System.out.println("11\t Inicialitzar columna i-èssima");
        System.out.println("12\t Eliminar columna");
        System.out.println("13\t Eliminar fila");
        System.out.println("14\t Afegir columna");
        System.out.println("15\t Afegir fila");
        System.out.println("16\t Llistar matrius");
        System.out.println("17\t Demo");
        System.out.println("0\t Sortir");
        System.out.println("Escriu una opció: ");
        return llegir_enter(0, 17);
    }

    private void nova_matriu() {
        System.out.println("Introdueix el nombre de files i columnes (ej: 4 2): ");
        int files = scan.nextInt();
        int columnes = scan.nextInt();
        System.out.println("Introdueix les dades: ");
        double[][] dades = new double[files][columnes];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                dades[i][j] = scan.nextDouble();
            }
        }
        Matriu m = new Matriu();
        m.set_data(dades);
        cjtMatrius.add(m);
    }

    private void multiplicar() {
        Matriu a = demanar_matriu("Introdueix l'identificador de la matriu A: ");
        Matriu b = demanar_matriu("Introdueix l'identificador de la matriu B: ");
        if (a.get_data().length == b.get_data()[0].length) {
            System.out.println("Resultat de la multiplicacio: ");
            a.multiplicar(b).print_matriu();
        }
        else {
            System.out.println("Error: Les dimensions de les matrius son incompatibles");
        }
    }

    private void sumar() {
        Matriu a = demanar_matriu("Introdueix l'identificador de la matriu A: ");
        Matriu b = demanar_matriu("Introdueix l'identificador de la matriu B: ");
        if (a.get_data().length == b.get_data().length && a.get_data()[0].length == b.get_data()[0].length) {
            System.out.println("Resultat de la suma: ");
            a.sumar(b).print_matriu();
        }
        else {
            System.out.println("Les dimensions de les matrius son incompatibles");
        }
    }

    private void restar() {
        Matriu a = demanar_matriu("Introdueix l'identificador de la matriu A: ");
        Matriu b = demanar_matriu("Introdueix l'identificador de la matriu B: ");
        if (a.get_data().length == b.get_data().length && a.get_data()[0].length == b.get_data()[0].length) {
            System.out.println("Resultat de la resta: ");
            a.restar(b).print_matriu();
        }
        else {
            System.out.println("Les dimensions de les matrius son incompatibles");
        }
    }

    private void transposar() {
        Matriu m = demanar_matriu("Introdueix l'identificador de la matriu: ");
        System.out.println("Resultat de la transposada: ");
        m.transposar().print_matriu();
    }

    private void normalitzar_files() {
        Matriu m = demanar_matriu("Introdueix l'identificador de la matriu: ");
        System.out.println("Resultat de la normalitzada per files: ");
        m.normalitzar_fila().print_matriu();
    }

    private void normalitzar_columnes() {
        Matriu m = demanar_matriu("Introdueix l'identificador de la matriu: ");
        System.out.println("Resultat de la normalitzada per columnes: ");
        m.normalitzar_columna().print_matriu();
    }

    private void obtenir_fila_iessima() {
        Matriu m = demanar_matriu("Introdueix l'identificador de la matriu: ");
        System.out.println("Introdueix la fila [1,"+ m.get_data().length + "]: ");
        int fila = llegir_enter(1, m.get_nombre_files());
        --fila;
        System.out.println("Fila i-èssima: ");
        m.get_fila_iessima(fila).print_matriu();
    }

    private void obtenir_columna_iessima() {
        Matriu m = demanar_matriu("Introdueix l'identificador de la matriu: ");
        System.out.println("Introdueix la columna [1,"+ m.get_data()[0].length + "]: ");
        int columna = llegir_enter(1, m.get_nombre_columnes());
        --columna;
        System.out.println("Columna i-èssima: ");
        m.get_columna_iessima(columna).print_matriu();

    }

    private void inicialitzar_fila_iessima() {
        Matriu m = demanar_matriu("Introdueix l'identificador de la matriu: ");
        System.out.println("Introdueix la fila i-èssima que vols inicialitzar [1,"+ m.get_nombre_files()+"]: ");
        int fila = llegir_enter(1, m.get_nombre_files());
        --fila;
        System.out.println("Escriu " + m.get_nombre_columnes() + " reals: ");
        for (int i = 0; i < m.get_nombre_columnes(); i++) {
            double valor = scan.nextDouble();
            m.set_valor(fila, i, valor);
        }
        m.print_matriu();
    }

    private void inicialitzar_columna_iessima() {
        Matriu m = demanar_matriu("Introdueix l'identificador de la matriu: ");
        System.out.println("Introdueix la columna i-èssima que vols inicialitzar [1,"+ m.get_nombre_columnes()+"]: ");
        int columna = llegir_enter(1, m.get_nombre_columnes());
        --columna;
        System.out.println("Escriu " + m.get_nombre_files() + " reals: ");
        for (int i = 0; i < m.get_nombre_files(); i++) {
            double valor = scan.nextDouble();
            m.set_valor(i, columna, valor);
        }
        m.print_matriu();
    }

    private void eliminar_columna_iessima() {
        Matriu m = demanar_matriu("Introdueix l'identificador de la matriu: ");
        System.out.println("Introdueix la columna i-èssima que vols eliminar [1,"+ m.get_nombre_columnes()+"]: ");
        int columna = llegir_enter(1, m.get_nombre_columnes());
        --columna;
        m.eliminar_columna(columna);
        m.print_matriu();
    }

    private void eliminar_fila_iessima() {
        Matriu m = demanar_matriu("Introdueix l'identificador de la matriu: ");
        System.out.println("Introdueix la fila i-èssima que vols eliminar [1,"+ m.get_nombre_files()+"]: ");
        int fila = llegir_enter(1, m.get_nombre_files());
        --fila;
        m.eliminar_fila(fila);
        m.print_matriu();
    }

    private void afegir_columna() {
        Matriu m = demanar_matriu("Introdueix l'identificador de la matriu: ");
        m.afegir_columna();
        m.print_matriu();
    }

    private void afegir_fila() {
        Matriu m = demanar_matriu("Introdueix l'identificador de la matriu: ");
        m.afegir_fila();
        m.print_matriu();
    }

    private void demo() {
        System.out.println("Creo una matriu anomenada A de 8 x 8: ");
        Matriu a = new Matriu(8,8);
        a.print_matriu();
        System.out.println("Inicialitzare la fila 4 amb valor 1");
        double[] dades_fila_iessima = {1,1,1,1,1,1,1,1};
        a.set_fila_iessima(3, dades_fila_iessima);
        a.print_matriu();
        System.out.println("Inicialitzare la columna 4 amb valor 1");
        a.set_columna_iessima(3, dades_fila_iessima);
        a.print_matriu();
        System.out.println("Inicialitzare la posicio 0,0 amb valor 5");
        a.set_valor(0,0, 5);
        a.print_matriu();
        System.out.println("Escriu la fila 2");
        a.get_fila_iessima(1).print_matriu();
        System.out.println("Escriu la columna 1");
        a.get_columna_iessima(0).print_matriu();
        System.out.println("Elimina la fila 3");
        a.eliminar_fila(2);
        a.print_matriu();
        System.out.println("Elimina la columna 3");
        a.eliminar_columna(2);
        a.print_matriu();
        System.out.println("Afeigr una nova fila");
        a.afegir_fila();
        a.print_matriu();
        System.out.println("Afegir una nova columna");
        a.afegir_columna();
        a.print_matriu();
        System.out.println("Elimina la ultima columna i fila afegida i la primera columna i primera fila ");
        a.eliminar_columna(0);
        a.eliminar_columna(a.get_nombre_columnes() - 1);
        a.eliminar_fila(0);
        a.eliminar_fila(a.get_nombre_files() - 1);
        a.print_matriu();
        System.out.println("Duplicare la matriu A en una nova matrui B");
        Matriu b = a;
        b.print_matriu();
        System.out.println("Suma A + B");
        a.sumar(b).print_matriu();
        System.out.println("Resta A - B");
        a.restar(b).print_matriu();
        System.out.println("Multiplica A * B");
        a.multiplicar(b).print_matriu();
        System.out.println("Elimina la ultima columna");
        a.eliminar_columna(a.get_nombre_columnes() - 1);
        a.print_matriu();
        System.out.println("Transapoa la matriu A");
        a.transposar().print_matriu();
        System.out.println("Normalitza la matriu A per files");
        a.normalitzar_fila().print_matriu();
        System.out.println("Normalitza la matyrui A per Columnes");
        a.normalitzar_columna().print_matriu();
    }

    public MatriuDriver() {
        cjtMatrius = new ArrayList<Matriu>();
    }

    public void run() {
        int opcio = menu();
        while (opcio != 0) {
            if (opcio != 1 && opcio != 17 && cjtMatrius.isEmpty()) {
                System.out.println("Necesites crear una matriu primer");
            }
            else {
                switch (opcio) {
                    case 1:
                        nova_matriu();
                        break;
                    case 2:
                        multiplicar();
                        break;
                    case 3:
                        sumar();
                        break;
                    case 4:
                        restar();
                        break;
                    case 5:
                        transposar();
                        break;
                    case 6:
                        normalitzar_files();
                        break;
                    case 7:
                        normalitzar_columnes();
                        break;
                    case 8:
                        obtenir_fila_iessima();
                        break;
                    case 9:
                        obtenir_columna_iessima();
                        break;
                    case 10:
                        inicialitzar_fila_iessima();
                        break;
                    case 11:
                        inicialitzar_columna_iessima();
                        break;
                    case 12:
                        eliminar_columna_iessima();
                        break;
                    case 13:
                        eliminar_fila_iessima();
                        break;
                    case 14:
                        afegir_columna();
                        break;
                    case 15:
                        afegir_fila();
                        break;
                    case 16:
                        llistar_matrius();
                        break;
                    case 17:
                        demo();
                        break;
                }
            }
            opcio = menu();
        }
    }
}
