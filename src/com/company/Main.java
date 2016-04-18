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
                                    boolean afegit = controladorPresentacio.afegir_node(tipus, nomNode);
                                    if (!afegit) System.out.println("No s'ha pogut afegir el node");
                                    else {
                                        System.out.println("S'ha afegit correctament");
                                        ////// DEBUG

                                        controladorPresentacio.controladorDomini.graf.get_paper_autor().print_matriu();
                                        System.out.println();
                                        controladorPresentacio.controladorDomini.graf.get_paper_conferencia().print_matriu();
                                        System.out.println();
                                        controladorPresentacio.controladorDomini.graf.get_paper_terme().print_matriu();
                                    }
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
                                    System.out.println("6\t Eliminar Node");
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
                                    if (opcio == 6) {
                                        System.out.println("Escriu l'identificador del node: ");
                                        int idNode = scan.nextInt();
                                        System.out.println("Escriu el tipus del node: ");
                                        scan.nextLine();
                                        tipusNode = scan.nextLine();
                                        tipus = controladorPresentacio.string_to_tipus_node(tipusNode);
                                        int error = controladorPresentacio.eliminar_node(tipus, idNode);
                                        if (error != -1) System.out.println("S'ha eliminat correctament");
                                        else System.out.println("No s'ha pogut eliminar");
                                    }
                                    // DEBUG

                                    controladorPresentacio.controladorDomini.graf.get_paper_autor().print_matriu();
                                    System.out.println();
                                    controladorPresentacio.controladorDomini.graf.get_paper_conferencia().print_matriu();
                                    System.out.println();
                                    controladorPresentacio.controladorDomini.graf.get_paper_terme().print_matriu();

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

    /////////////////////////////////////////////
    ////////////////// TEMPORAL //////////////

    public static void imprimir_nodos() {
        System.out.println("LLISTAT DE NODES");
        ArrayList<NodePresentacio> llistaNodes = controladorPresentacio.get_llista_nodes(Node.TipusNode.AUTOR);
        if (llistaNodes != null) {
            for (NodePresentacio node : llistaNodes) {
                System.out.println("AUTOR: Node amb id: " + node.id + " Nom: " + node.nom);
            }
        }
        llistaNodes = controladorPresentacio.get_llista_nodes(Node.TipusNode.TERME);
        if (llistaNodes != null) {
            for (NodePresentacio node : llistaNodes) {
                System.out.println("TERME: Node amb id: " + node.id + " Nom: " + node.nom);
            }
        }
        llistaNodes = controladorPresentacio.get_llista_nodes(Node.TipusNode.PAPER);
        if (llistaNodes != null) {
            for (NodePresentacio node : llistaNodes) {
                System.out.println("PAPER: Node amb id: " + node.id + " Nom: " + node.nom);
            }
        }
        llistaNodes = controladorPresentacio.get_llista_nodes(Node.TipusNode.CONFERENCIA);
        if (llistaNodes != null) {
            for (NodePresentacio node : llistaNodes) {
                System.out.println("CONFERENCIA: Node amb id: " + node.id + " Nom: " + node.nom);
            }
        }
    }

    public static void imprimir_matrices() {
        System.out.println("MATRIUS ADEJCENCIA");
        controladorPresentacio.controladorDomini.graf.get_paper_autor().print_matriu();
        System.out.println();
        controladorPresentacio.controladorDomini.graf.get_paper_conferencia().print_matriu();
        System.out.println();
        controladorPresentacio.controladorDomini.graf.get_paper_terme().print_matriu();
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

        controladorPresentacio.afegir_node(Node.TipusNode.AUTOR, "JOHN");
        controladorPresentacio.afegir_node(Node.TipusNode.CONFERENCIA, "MATH KDD");
        controladorPresentacio.afegir_node(Node.TipusNode.TERME, "OF");
        controladorPresentacio.afegir_node(Node.TipusNode.PAPER, "ALGEBRA");

        imprimir_matrices();
        imprimir_nodos();

        System.out.println("EDITAR NODO");
        controladorPresentacio.modificar_node(Node.TipusNode.PAPER, 1, "MATRICES");

        imprimir_matrices();
        imprimir_nodos();

        System.out.println("AÑADIR UNA ARISTA");
        controladorPresentacio.afegir_aresta(Node.TipusNode.AUTOR, 1, 1);

        imprimir_matrices();
        imprimir_nodos();

        System.out.println("ELIMINAR una ARESTA");
        controladorPresentacio.eliminar_aresta(Node.TipusNode.AUTOR, 1, 1);

        imprimir_matrices();
        imprimir_nodos();

        System.out.println("BORRAR NODO: ");
        controladorPresentacio.eliminar_node(Node.TipusNode.PAPER, 1);

        imprimir_matrices();
        imprimir_nodos();


        /*
        Matriu m = new Matriu();
        double[][] data = {{1,2,3,4},{1,2,3,4},{1,2,3,4}};
        m.set_data(data);
        m.print_matriu();
        System.out.println();
        m.eliminar_columna(1);
        m.print_matriu();
        System.out.println();
        m.eliminar_columna(2);
        m.print_matriu();
        System.out.println();
        m.eliminar_columna(1);
        m.print_matriu();
        System.out.println();
        m.eliminar_fila(1);
        m.print_matriu();
        System.out.println();
        m.afegir_columna();
        m.print_matriu();
        System.out.println();
        m.afegir_columna();
        m.print_matriu();
        System.out.println();
        m.afegir_fila();
        m.print_matriu();
        System.out.println();
        m.afegir_fila();
        m.print_matriu();
        System.out.println();
        m.eliminar_columna(1);
        m.print_matriu();
        System.out.println();
        m.eliminar_fila(1);
        m.print_matriu();*/
    }
}
