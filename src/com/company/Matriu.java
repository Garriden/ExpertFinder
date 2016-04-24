package com.company;

// Created by Ruben Bagan Benavides on 11/04/2016.

import java.util.Arrays;

public class Matriu {
    private double[][] data;

    // Pre:	 fila >= 1; columna >= 1.
    // Post: Crea una nova instància d'una matriu inicialitzada amb les dimensions fila x columna. Tots els valors son 0.
    // Cost: O(n²).
    public Matriu(int fila, int columna) {
        this.data = new double[fila][columna];
    }

    // Pre:  Cert.
    // Post: Retorna una referència a les dades de la matriu.
    // Cost: O(1)
    public double[][] get_data() {
        return this.data;
    }

    // Pre:  Cert.
    // Post: La matriu implícita conté una copia de les dades pasades per paràmetre.
    // Cost: O(n^2).
    public void set_data(double[][] data) {
        this.data = new double[data.length][data[0].length];
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[0].length; ++j) {
                this.data[i][j] = data[i][j];
            }
        }
    }

    // Pre:  0 <= fila < Matriu.fila; data != NULL. La longitud de data té que ser igual al numero de columnes de la
    //       matriu implícita.
    // Post: La fila i-èssima de la matriu implícita té com a valors data.
    // Cost: O(n)
    public void set_fila_iessima(int fila, double[] data) {
        System.arraycopy(data, 0, this.data[fila], 0, data.length);
    }

    // Pre:  0 <= columna < Matriu.columna; data != NULL. La longitud de data té que ser igual al numero de files de la
    //       matriu implícita.
    // Post: La columna i-èssima de la matriu implícita té com a valors data.
    // Cost: O(n)
    public void set_columna_iessima(int columna, double[] data) {
        for (int i = 0; i < data.length; i++) {
            this.data[i][columna] = data[i];
        }
    }

    // Pre:  0 <= fila < Matriu.fila; 0 <= columna < Matriu.columna
    // Post: La matriu implicita té un nou valor en la fila i columna pasades per paràmetre.
    // Cost: O(1).
    public void set_valor(int fila, int columna, double valor) {
        this.data[fila][columna] = valor;
    }

    // Pre:  Cert.
    // Post: Retorna el nombre de files que té la matriu implícita.
    // Cost: O(1).
    public int get_nombre_files() {
        return this.data.length;
    }

    // Pre:  Cert.
    // Post: Retorna el nombre de columnes que té la matriu implícita.
    // Cost: O(1).
    public int get_nombre_columnes() {
        return  this.data[0].length;
    }

    // Pre:  0 <= fila < Matriu.fila.
    // Post: Retorna una Matriu C coma resultat de obtenir la fila i-èssima de la matriu implícita.
    // Cost: O(n²).
    public Matriu get_fila_iessima(int fila) {
        Matriu m = new Matriu(1, this.data[0].length);
        System.arraycopy(this.data[fila], 0, m.data[0], 0, this.data[0].length);
        return m;
    }

    // Pre:  0 <= fila < Matriu.fila; 0 <= columna < Matriu.columna.
    // Post: Retorna el valor en la fila i columna pasades per paràmetre de la matriu implicita.
    // Cost: O(1).
    public double get_valor(int fila, int columna) {
        return this.data[fila][columna];
    }


    // Pre:  0 <= columna < Matriu.columna.
    // Post: Retorna una Matriu C coma resultat de obtenir la columna i-èssima de la matriu implícita.
    // Cost: O(n²).
    public Matriu get_columna_iessima(int columna) {
        Matriu m = new Matriu(this.data.length, 1);
        for (int i = 0; i < this.data.length; i++) {
            m.data[i][0] = this.data[i][columna];
        }

        return m;
    }

    // Pre:  0 <= fila < Matriu.fila; El numero de files de la matriu >= 2.
    // Post: S'ha eliminat de la matriu implícita la fila pasada com a paràmetre.
    // Cost: O(n²).
    public void eliminar_fila(int fila) {
        double[][] novaData = new double[this.data.length - 1][this.data[0].length];
        for (int i = 0; i < fila; i++) {
            System.arraycopy(this.data[i], 0, novaData[i], 0, this.data[0].length);
        }

        for (int i = fila; i < this.data.length - 1; i++) {
            System.arraycopy(this.data[i + 1], 0, novaData[i], 0, this.data[0].length);
        }
        this.data = novaData;
    }

    // Pre:  0 <= columna < Matriu.fila; El numero de columnes de la matriu >= 2.
    // Post: S'ha eliminat de la matriu implícita la columna pasada com a paràmetre.
    // Cost: O(n²).
    public void eliminar_columna(int columna) {
        double[][] novaData = new double[this.data.length][this.data[0].length - 1];
        for (int i = 0; i < this.data.length; i++) {
            System.arraycopy(this.data[i], 0, novaData[i], 0, columna);
            System.arraycopy(this.data[i], columna + 1, novaData[i], columna, this.data[0].length - 1 - columna);
        }

        this.data = novaData;
    }

    // Pre:  Cert.
    // Post: S'ha afegit a la matriu implícita una fila on tots els seus valors són 0. Aquesta nova fila és la
    //       última fila de la matriu. El numero de files de la matriu és Matriu.files + 1.
    // Cost: O(n²).
    public void afegir_fila() {
        double[][] novaData = new double[this.data.length + 1][this.data[0].length];
        for (int i = 0; i < this.data.length; ++i) {
            System.arraycopy(this.data[i], 0, novaData[i], 0, this.data[0].length);
        }
        this.data = novaData;
    }

    // Pre:  Cert.
    // Post: S'ha afegit a la matriu implícita una columna on tots els seus valors són 0. Aquesta nova columna és la
    //       última columna de la matriu. El numero de columnes de la matriu és Matriu.columnes + 1.
    // Cost: O(n²).
    public void afegir_columna() {
        double[][] novaData = new double[this.data.length][this.data[0].length + 1];
        for (int i = 0; i < this.data.length; ++i) {
            System.arraycopy(this.data[i], 0, novaData[i], 0, this.data[0].length);
        }
        this.data = novaData;
    }

    // Pre:  Anomenem la matriu implícita com A i la matriu pasada per referència com B, tant A com B tenen que tenir
    //       mateixes dimensions. B != NULL.
    // Post: Retorna una matriu C com a resultat de la suma de la matriu implícita (A) amb la matriu B. C = A + B.
    // Cost: O(n²).
    public Matriu sumar(Matriu b) {
        Matriu c = new Matriu(this.data.length, this.data[0].length);
        for (int i = 0; i < this.data.length; ++i) {
            for (int j = 0; j < this.data[0].length; ++j) {
                c.data[i][j] = this.data[i][j] + b.data[i][j];
            }
        }
        return c;
    }

    // Pre:  Anomenem la matriu implícita com A i la matriu pasada per referència com B, tant A com B tenen que tenir
    //       mateixes dimensions. B != NULL.
    // Post: Retorna una matriu C com a resultat de la resta de la matriu implícita (A) amb la matriu B. C = A - B.
    // Cost: O(n²).
    public Matriu restar(Matriu b) {
        Matriu c = new Matriu(this.data.length, this.data[0].length);
        for (int i = 0; i < this.data.length; ++i) {
            for (int j = 0; j < this.data[0].length; ++j) {
                c.data[i][j] = this.data[i][j] - b.data[i][j];
            }
        }

        return c;
    }

    // Pre:  Anomenem la matriu implícita com A i la matriu pasada per referència com B, B != NULL. El nombre files de
    //		 la Matriu A té que ser igual al nombre de columnes de la Matriu B.
    // Post: Retorna una matriu C com a resultat de multiplicar la matriu implícita (A) amb la matriu B. C = A * B.
    // Cost: O(n³).
    public Matriu multiplicar(Matriu b) {
        Matriu c = new Matriu(this.data.length, b.data[0].length);
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < b.data[0].length; j++) {
                for (int k = 0; k < this.data[0].length; k++) {
                    c.data[i][j] += this.data[i][k] * b.data[k][j];
                }
            }
        }

        return c;
    }

    // Pre:  Cert
    // Post: Retorna una matriu C com a resultat de transposar la matriu implícita.
    // Cost: O(n²).
    public Matriu transposar() {
        Matriu matriu_transposada = new Matriu(this.data[0].length, this.data.length);
        for (int i = 0; i < this.data[0].length; i++) {
            for (int j = 0; j < this.data.length; j++) {
                matriu_transposada.data[i][j] = this.data[j][i];
            }
        }

        return matriu_transposada;
    }

    // Pre:  Cert.
    // Post: Retorna una Matriu C com a resultat de normalitzar per files la matriu implícita.
    // Cost: O(n²).
    public Matriu normalitzar_fila() {
        Matriu m = new Matriu(this.data.length, this.data[0].length);
        for (int i = 0; i < this.data.length; i++) {
            double norma = 0;
            for (int j = 0; j < this.data[0].length; j++) {
                norma += Math.pow(this.data[i][j], 2);
            }
            norma = Math.sqrt(norma);
            for (int j = 0; j < this.data[0].length; j++) {
                m.data[i][j] = this.data[i][j] / norma;
            }
        }

        return m;
    }

    // Pre:  Cert.
    // Post: Retorna una Matriu C com a resultat de normalitzar per columnes la matriu implícita.
    // Cost: O(n²).
    public Matriu normalitzar_columna() {
        Matriu m = new Matriu(this.data.length, this.data[0].length);
        for (int i = 0; i < this.data[0].length; i++) {
            double norma = 0.0;
            for (int j = 0; j < this.data.length; ++j) {
                norma += Math.pow(this.data[j][i], 2);
            }
            norma = Math.sqrt(norma);
            for (int j = 0; j < this.data.length; j++) {
                m.data[j][i] = this.data[j][i] / norma;
            }
        }

        return m;
    }

    // Pre:  Cert.
    // Post: Retorna una copia de la matriu implícita, amb les mateixes dades i dimensions.
    // Cost: O(n^2);
    public Matriu copia_profunditat() {
        Matriu copia = new Matriu(this.data.length, this.data[0].length);
        for (int i = 0; i < this.data.length; ++i) {
            for (int j = 0; j < this.data[0].length; ++j) {
                copia.data[i][j] = this.data[i][j];
            }
        }

        return copia;
    }
}
