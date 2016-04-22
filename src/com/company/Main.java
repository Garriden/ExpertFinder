package com.company;

// Autor: Ruben Bagan Benavides 20/04/2016

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.ErrorManager;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static ControladorPresentacio controladorPresentacio;

    public static int llegir_enter(int minim, int maxim) {
        int n = scan.nextInt();
        while (n < minim || n > maxim) {
            System.out.println("Error: tens que escriure un nombre entre " + minim + " i " + maxim);
            n = scan.nextInt();
        }
        return n;
    }

    public static void tractar_error(ControlError error) {
        switch (error.getMessage()) {
            case "CamiNotFound":
                System.out.println("Error: El que cami que busques no existeix en la llista de camins.");
                break;
            case "CamiRepetit":
                System.out.println("Error: El cami que vols afegir ja existeix a la llista de camins.");
                break;
            case "CamiNoValid":
                System.out.println("Error: El cami no es valid.");
                break;
            case "ArgumentNul":
                System.out.println("Error: Un dels arguments no esta inicialitzat correctament.");
                break;
            case "NomNodeInvalid":
                System.out.println("Error: El nom del node te que ser un de la llista <Autor,Conferencia,Terme,Paper>");
                break;
        }
    }

    public static int escriure_llista_nodes(String tipusNode) {
        if (tipusNode == null) return -2;
        String[] nodes = controladorPresentacio.get_llista_nodes(tipusNode.toUpperCase());
        if (nodes == null) return -3;
        for (int i = 0; i < nodes.length; ++i) {
            System.out.println("Node amb id: " + (i+1) + " Nom: " + nodes[i]);
        }
        return 0;
    }

    public static void print_debug() {
        System.out.println("MATRIUS");
        controladorPresentacio.controladorDomini.graf.get_paper_autor().print_matriu();
        System.out.println();
        controladorPresentacio.controladorDomini.graf.get_paper_conferencia().print_matriu();
        System.out.println();
        controladorPresentacio.controladorDomini.graf.get_paper_terme().print_matriu();
        System.out.println("NODES");
        String[] nodes = controladorPresentacio.get_llista_nodes("AUTOR");
        if (nodes != null) {
            for (int i = 0; i < nodes.length; ++i) {
                System.out.println("Node amb id: " + (i+1) + " Nom: " + nodes[i]);
            }
        }
        nodes = controladorPresentacio.get_llista_nodes("TERME");
        if (nodes != null) {
            for (int i = 0; i < nodes.length; ++i) {
                System.out.println("Node amb id: " + (i+1) + " Nom: " + nodes[i]);
            }
        }
        nodes = controladorPresentacio.get_llista_nodes("PAPER");
        if (nodes != null) {
            for (int i = 0; i < nodes.length; ++i) {
                System.out.println("Node amb id: " + (i+1) + " Nom: " + nodes[i]);
            }
        }
        nodes = controladorPresentacio.get_llista_nodes("CONFERENCIA");
        if (nodes != null) {
            for (int i = 0; i < nodes.length; ++i) {
                System.out.println("Node amb id: " + (i+1) + " Nom: " + nodes[i]);
            }
        }
    }

    public static int menu() {
        System.out.println("1\t Importar dades - NO ESTA AUN DISPONIBLE");
        System.out.println("2\t Exportar dades - NO ESTA AUN DISPONIBLE");
        System.out.println("3\t Gestionar graf");
        System.out.println("4\t Gestionar cami");
        System.out.println("5\t Gestio consulta");
        System.out.println("0\t Sortir");
        System.out.println("Escriu una opcio: ");
        return llegir_enter(0,5);
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

    public static void afegir_node() {
        scan.nextLine();
        System.out.println("Introdueix el tipus de node <Autor,Conferencia,Terme,Paper>: ");
        String tipusNode = scan.nextLine();
        System.out.println("Introdueix el nom del node de tipus " + tipusNode + ": ");
        String nomNode = scan.nextLine();
        int error = controladorPresentacio.afegir_node(tipusNode, nomNode);
        if (error != 0) tractar_error(error);
        else {
            System.out.println("S'ha afegit correctament");
            print_debug();
        }
    }

    public static void consultar_nodes() {
        int opcio = 1;
        while (opcio != 0) {
            System.out.println("1\t Consultar Autors");
            System.out.println("2\t Consultar Termes");
            System.out.println("3\t Consultar Papers");
            System.out.println("4\t Consultar Conferencies");
            System.out.println("5\t Modificar Node");
            System.out.println("6\t Eliminar Node");
            System.out.println("0\t Tornar");
            System.out.println("Escriu una opcio: ");
            opcio = llegir_enter(0,6);

            if (opcio >= 1 && opcio <= 4) {
                int error = 0;
                switch (opcio) {
                    case 1: error = escriure_llista_nodes("AUTOR");       break;
                    case 2: error = escriure_llista_nodes("TERME");        break;
                    case 3: error = escriure_llista_nodes("PAPER");        break;
                    case 4: error = escriure_llista_nodes("CONFERENCIA");  break;
                }
                if (error != 0) tractar_error(error);
            }
            else if (opcio == 5) {
                scan.nextLine();
                System.out.println("Escriu el tipus de node <Autor,Conferencia,Terme,Paper>: ");
                String tipusNode = scan.nextLine();
                int error = escriure_llista_nodes(tipusNode);
                if (error != 0) tractar_error(error);
                else {
                    System.out.println("Escriu l'identificador del node que vols modificar: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Escriu el nou nom del node amb id: " + id);
                    String nouNom = scan.nextLine();
                    error = controladorPresentacio.modificar_node(tipusNode, (id-1), nouNom);
                    if (error != 0) tractar_error(error);
                    else System.out.println("S'ha modificat el node amb id " + id + " amb el nou nom: " + nouNom);
                }
            }
            else if (opcio == 6) {
                scan.nextLine();
                System.out.println("Escriu el tipus de node <Autor,Conferencia,Terme,Paper>: ");
                String tipusNode = scan.nextLine();
                int error = escriure_llista_nodes(tipusNode);
                if (error != 0) tractar_error(error);
                else {
                    System.out.println("Escriu l'identificador del node que vols eliminar: ");
                    int id = scan.nextInt();
                    error = controladorPresentacio.eliminar_node(tipusNode, (id-1));
                    if (error != 0) tractar_error(error);
                    else System.out.println("El node amb identificador " + id + " s'ha eliminat correctament");
                }
            }

            print_debug();
        }
    }

    public static void gestionar_nodes() {
        int opcio = 1;
        while (opcio != 0) {
            System.out.println("1\t Afegir Node");
            System.out.println("2\t Consultar Nodes");
            System.out.println("0\t Tornar");
            System.out.println("Escriu una opcio: ");
            opcio = llegir_enter(0,2);

            switch (opcio) {
                case 1:
                    afegir_node();
                    break;
                case 2:
                    consultar_nodes();
                   break;
            }
        }
    }

    public static void afegir_aresta() {
        scan.nextLine();
        System.out.println("Escriu el tipus de node <Autor,Conferencia,Terme,Paper>: ");
        String tipusNode = scan.nextLine();
        System.out.println("Escriu l'identificador del node origen: ");
        int idNodeOrigen = scan.nextInt();
        System.out.println("Escriu l'identificador del node desti: ");
        int idNodeDesti = scan.nextInt();
        int error = controladorPresentacio.afegir_aresta(tipusNode, idNodeOrigen, idNodeDesti);
        if (error != 0) tractar_error(error);
        else {
            System.out.println("S'ha afegit una nova aresta entre els nodes amb id " + idNodeOrigen + " a " + idNodeDesti);
            print_debug();
        }
    }

    public static void eliminar_aresta() {
        System.out.println("Escriu el tipus de node <Autor,Conferencia,Terme,Paper>: ");
        String tipusNode = scan.nextLine();
        System.out.println("Escriu l'identificador del node origen: ");
        int idNodeOrigen = scan.nextInt();
        System.out.println("Escriu l'identificador del node desti: ");
        int idNodeDesti = scan.nextInt();
        int error = controladorPresentacio.eliminar_aresta(tipusNode, idNodeOrigen, idNodeDesti);
        if (error != 0) tractar_error(error);
        else {
            System.out.println("S'ha eliminat una aresta entre els nodes amb id " + idNodeOrigen + " a " + idNodeDesti);
            print_debug();
        }
    }

    public static void consultar_arestes() {
        print_debug();
    }

    public static void gestionar_arestes() {
        int opcio = 1;
        while (opcio != 0) {
            System.out.println("1\t Afegir aresta");
            System.out.println("2\t Eliminar aresta");
            System.out.println("3\t Consultar arestes");
            System.out.println("0\t Tornar");
            System.out.println("Escriu una opcio: ");
            opcio = llegir_enter(0,3);

            switch (opcio) {
                case 1:
                    afegir_aresta();
                    break;
                case 2:
                    eliminar_aresta();
                    break;
                case 3:
                    consultar_arestes();
                    break;
            }
        }
    }

    public static void gestionar_graf() {
        int opcio = 1;
        while (opcio != 0) {
            System.out.println("1\t Gestionar Nodes");
            System.out.println("2\t Gestionar Relacions");
            System.out.println("0\t Tornar");
            System.out.println("Escriu una opcio: ");
            opcio = llegir_enter(0,2);

            switch (opcio) {
                case 1:
                    gestionar_nodes();
                    break;
                case 2:
                    gestionar_arestes();
                    break;
            }
        }
    }

    public static void afegir_cami() {
        scan.nextLine();
        System.out.println("Introdueix els diferents nodes que formen el cami <Autor,Paper,Terme,Conferencia>, escriu FI per finalitzar: ");
        String node = scan.nextLine();
        String cami = "";
        while (!node.equalsIgnoreCase("FI")) {
            switch (node.toUpperCase()) {
                case "AUTOR":
                    cami = cami + "A";
                    break;
                case "TERME":
                    cami = cami + "T";
                    break;
                case "PAPER":
                    cami = cami + "P";
                    break;
                case "CONFERENCIA":
                    cami = cami + "C";
                    break;
                default: System.out.println("El node te que ser un de la llista:  <Autor,Paper,Terme,Conferencia>");
            }
            node = scan.nextLine();
        }
        System.out.println("Introdueix una descripcio per aquest cami: ");
        String descripcio = scan.nextLine();
        try {
            controladorPresentacio.afegir_cami(cami, descripcio);
        } catch (ControlError controlError) {
            tractar_error(controlError);
        }
    }

    public static void consultar_cami() {

    }

    public static void gestionar_cami() {
        int opcio = 1;
        while (opcio != 0) {
            System.out.println("1\t Afegir cami");
            System.out.println("2\t Consultar cami");
            System.out.println("0\t Sortir");
            System.out.println("Escriu una opcio: ");
            opcio = llegir_enter(0,2);
            switch (opcio) {
                case 1:
                    afegir_cami();
                    break;
                case 2:
                    consultar_cami();
                    break;
            }
        }

    }

    public static void inicializar_grafo() {
        double[][] data = {{1,0,0},{0,1,0}};
        Matriu m = new Matriu();
        m.set_data(data);

        ArrayList<Node> paper = new ArrayList<>();
        paper.add(new Node(0, "Papeles1", Node.TipusNode.PAPER));
        paper.add(new Node(1, "Papeles2", Node.TipusNode.PAPER));

        ArrayList<Node> autor = new ArrayList<>();
        autor.add(new Node(0, "Autor1", Node.TipusNode.AUTOR));
        autor.add(new Node(1, "Autor2", Node.TipusNode.AUTOR));
        autor.add(new Node(2, "Autor3", Node.TipusNode.AUTOR));


        ArrayList<Node> terme = new ArrayList<>();
        terme.add(new Node(0, "Term1", Node.TipusNode.TERME));
        terme.add(new Node(1, "Term2", Node.TipusNode.TERME));
        terme.add(new Node(2, "Term3", Node.TipusNode.TERME));

        ArrayList<Node> conferencia = new ArrayList<>();
        conferencia.add(new Node(0, "Conf1", Node.TipusNode.CONFERENCIA));
        conferencia.add(new Node(1, "Conf2", Node.TipusNode.CONFERENCIA));
        conferencia.add(new Node(2, "Conf3", Node.TipusNode.CONFERENCIA));

        controladorPresentacio.controladorDomini.graf.set_paper_autor(data);
        controladorPresentacio.controladorDomini.graf.set_paper_terme(data);
        controladorPresentacio.controladorDomini.graf.set_paper_conferencia(data);
        controladorPresentacio.controladorDomini.graf.set_paper(paper);
        controladorPresentacio.controladorDomini.graf.set_autor(autor);
        controladorPresentacio.controladorDomini.graf.set_conferencia(conferencia);
        controladorPresentacio.controladorDomini.graf.set_terme(terme);


        print_debug();
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        controladorPresentacio = new ControladorPresentacio();

        inicializar_grafo();
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

        /*
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
        */

        /*
        double[][] data = {{1,0,0},{0,1,0}};
        Matriu m = new Matriu();
        m.set_data(data);

        ArrayList<Node> paper = new ArrayList<>();
        paper.add(new Node(0, "Papeles1", Node.TipusNode.PAPER));
        paper.add(new Node(1, "Papeles2", Node.TipusNode.PAPER));

        ArrayList<Node> autor = new ArrayList<>();
        autor.add(new Node(0, "Autor1", Node.TipusNode.AUTOR));
        autor.add(new Node(1, "Autor2", Node.TipusNode.AUTOR));
        autor.add(new Node(2, "Autor3", Node.TipusNode.AUTOR));


        ArrayList<Node> terme = new ArrayList<>();
        terme.add(new Node(0, "Term1", Node.TipusNode.TERME));
        terme.add(new Node(1, "Term2", Node.TipusNode.TERME));
        terme.add(new Node(2, "Term3", Node.TipusNode.TERME));

        ArrayList<Node> conferencia = new ArrayList<>();
        conferencia.add(new Node(0, "Conf1", Node.TipusNode.CONFERENCIA));
        conferencia.add(new Node(1, "Conf2", Node.TipusNode.CONFERENCIA));
        conferencia.add(new Node(2, "Conf3", Node.TipusNode.CONFERENCIA));

        controladorPresentacio.controladorDomini.graf.set_paper_autor(data);
        controladorPresentacio.controladorDomini.graf.set_paper_terme(data);
        controladorPresentacio.controladorDomini.graf.set_paper_conferencia(data);
        controladorPresentacio.controladorDomini.graf.set_paper(paper);
        controladorPresentacio.controladorDomini.graf.set_autor(autor);
        controladorPresentacio.controladorDomini.graf.set_conferencia(conferencia);
        controladorPresentacio.controladorDomini.graf.set_terme(terme);


        imprimir_matrices();
        imprimir_nodos();
        System.out.println();
        System.out.println();

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

    */
    }
}
