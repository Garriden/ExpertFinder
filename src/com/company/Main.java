package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static ControladorPresentacio controladorPresentacio;

    public static int menu() {
        System.out.println("1\t Importar dades - NO ESTA AUN DISPONIBLE");
        System.out.println("2\t Exportar dades - NO ESTA AUN DISPONIBLE");
        System.out.println("3\t Gestionar graf");
        System.out.println("4\t Gestionar cami");
        System.out.println("5\t Gestio consulta");
        System.out.println("0\t Sortir");
        System.out.println("Escriu una opcio: ");

        return scan.nextInt();
    }

    public static void importar_dades() {
        System.out.println("1\t Importar Graf");
        System.out.println("2\t Importar Cami");
        System.out.println("0\t Tornar");
    }

    public static void exportar_dades() {
        System.out.println("1\t Exportar Graf");
        System.out.println("2\t Exportar Cami");
        System.out.println("0\t Tornar");
    }

    public static void gestionar_graf() {
        int opcio = 1;
        while (opcio != 0) {
            System.out.println("1\t Gestionar Nodes");
            System.out.println("2\t Gestionar Relacions");
            System.out.println("0\t Tornar");
            System.out.println("Escriu una opcio: ");
            opcio = scan.nextInt();

            switch (opcio) {
                case 1:
                    opcio = 1;
                    while (opcio != 0) {
                        System.out.println("1\t Afegir Node");
                        System.out.println("2\t Consultar Nodes");
                        System.out.println("0\t Tornar");
                        opcio = scan.nextInt();

                        switch (opcio) {
                            case 1:
                                scan.nextLine();
                                System.out.println("Introdueix el tipus de node <Autor,Conferencia,Terme,Paper>: ");
                                String tipusNode = scan.nextLine();
                                System.out.println("Introdueix el nom per el node de tipus " + tipusNode + ": ");
                                String nomNode = scan.nextLine();
                                Node.TipusNode tipus = controladorPresentacio.string_to_tipus_node(tipusNode.toUpperCase());
                                if (tipus != null) {
                                    int error = controladorPresentacio.afegir_node(tipus, nomNode);
                                    if (error == -1 ) System.out.println("No s'ha pogut afegir el node");
                                    else System.out.println("S'ha afegit correctament");
                                }
                                else System.out.println("Error: Tens que escriure <Autor,Conferencia,Terme,Paper>");
                                break;
                            case 2:
                                opcio = 1;
                                while (opcio != 0) {
                                    System.out.println("1\t Consultar Autors");
                                    System.out.println("2\t Consultar Termes");
                                    System.out.println("3\t Consultar Papers");
                                    System.out.println("4\t Consultar Conferencies");
                                    System.out.println("5\t Modificar Node");
                                    System.out.println("5\t Eliminar Node");
                                    System.out.println("0\t Tornar");
                                    opcio = scan.nextInt();

                                    if (opcio >= 1 && opcio <= 4) {
                                        ArrayList<NodePresentacio> llistaNodes = null;
                                        switch (opcio) {
                                            case 1:
                                                llistaNodes = controladorPresentacio.get_llista_nodes(Node.TipusNode.AUTOR);
                                                break;
                                            case 2:
                                                llistaNodes = controladorPresentacio.get_llista_nodes(Node.TipusNode.TERME);
                                                break;
                                            case 3:
                                                llistaNodes = controladorPresentacio.get_llista_nodes(Node.TipusNode.PAPER);
                                                break;
                                            case 4:
                                                llistaNodes = controladorPresentacio.get_llista_nodes(Node.TipusNode.CONFERENCIA);
                                                break;
                                        }
                                        if (llistaNodes != null) {
                                            for (NodePresentacio node : llistaNodes) {
                                                System.out.println("Node amb id: " + node.id + " Nom: " + node.nom);
                                            }
                                        }
                                        else {
                                            System.out.println("Error al consulta la llista " + opcio);
                                        }
                                    }

                                }
                        }
                    }
                    break;
                case 2:
                    break;
            }
        }
    }

    public static void gestionar_cami() {

    }

    public static void main(String[] args) {
        controladorPresentacio = new ControladorPresentacio();
        /*
        int opcio = menu();
        while (opcio != 0) {
            switch (opcio) {
                case 1:
                    importar_dades();
                    break;
                case 2:
                    exportar_dades();
                    break;
                case 3:
                    gestionar_graf();
                    break;
                case 4:
                    gestionar_cami();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcio incorrecte");
                    break;
            }
            opcio = menu();
        }
        */
        Matriu m = new Matriu();
        double[][] data = {{1,1,1,1},{2,2,2,2},{3,3,3,3}};
        m.set_data(data);
        m.print_matriu();
        System.out.println();
        m.eliminar_fila(1);
        m.print_matriu();
        System.out.println();
        m.eliminar_fila(0);
        m.print_matriu();
        System.out.println();

    }
}
