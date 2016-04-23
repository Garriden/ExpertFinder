package com.company;

// Autor: Ruben Bagan Benavides 20/04/2016

import java.util.ArrayList;
import java.util.Scanner;

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

    public static void print_matriu(Matriu m) {
        for (int i = 0; i < m.get_nombre_files(); i++) {
            for (int j = 0; j < m.get_nombre_columnes(); j++) {
                System.out.printf("%.2f\t", m.get_valor(i, j));
            }
            System.out.println();
        }
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

    public static void escriure_llista_nodes(String tipusNode) throws ControlError {
        String[] nodes = controladorPresentacio.get_llista_nodes(tipusNode.toUpperCase());
        for (int i = 0; i < nodes.length; ++i) {
            System.out.println("Node amb id: " + (i+1) + " Nom: " + nodes[i]);
        }
    }

    public static void print_debug() throws ControlError {
        System.out.println("MATRIUS");
        print_matriu(controladorPresentacio.controladorDomini.graf.get_paper_autor());
        System.out.println();
        print_matriu(controladorPresentacio.controladorDomini.graf.get_paper_conferencia());
        System.out.println();
        print_matriu(controladorPresentacio.controladorDomini.graf.get_paper_terme());
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
        try {
            controladorPresentacio.afegir_node(tipusNode, nomNode);
            System.out.println("S'ha afegit correctament");
        } catch (ControlError controlError) {
            tractar_error(controlError);
        }

        try {
            print_debug();
        } catch (ControlError controlError) {
            tractar_error(controlError);
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
                switch (opcio) {
                    case 1:
                        try {
                            escriure_llista_nodes("AUTOR");
                        } catch (ControlError controlError) {
                            tractar_error(controlError);
                        }
                        break;
                    case 2:
                        try {
                            escriure_llista_nodes("TERME");
                        } catch (ControlError controlError) {
                            tractar_error(controlError);
                        }
                        break;
                    case 3:
                        try {
                            escriure_llista_nodes("PAPER");
                        } catch (ControlError controlError) {
                            tractar_error(controlError);
                        }
                        break;
                    case 4:
                        try {
                            escriure_llista_nodes("CONFERENCIA");
                        } catch (ControlError controlError) {
                            tractar_error(controlError);
                        }
                        break;
                }
            }
            else if (opcio == 5) {
                scan.nextLine();
                System.out.println("Escriu el tipus de node <Autor,Conferencia,Terme,Paper>: ");
                String tipusNode = scan.nextLine();
                try {
                    escriure_llista_nodes(tipusNode);
                    System.out.println("Escriu l'identificador del node que vols modificar: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Escriu el nou nom del node amb id: " + id);
                    String nouNom = scan.nextLine();
                    controladorPresentacio.modificar_node(tipusNode, (id-1), nouNom);
                    System.out.println("S'ha modificat el node amb id " + id + " amb el nou nom: " + nouNom);
                } catch (ControlError controlError) {
                    tractar_error(controlError);
                }
            }
            else if (opcio == 6) {
                scan.nextLine();
                System.out.println("Escriu el tipus de node <Autor,Conferencia,Terme,Paper>: ");
                String tipusNode = scan.nextLine();
                try {
                    escriure_llista_nodes(tipusNode);
                    System.out.println("Escriu l'identificador del node que vols eliminar: ");
                    int id = scan.nextInt();
                    controladorPresentacio.eliminar_node(tipusNode, (id-1));
                    System.out.println("El node amb identificador " + id + " s'ha eliminat correctament");
                } catch (ControlError controlError) {
                    tractar_error(controlError);
                }
            }

            try {
                print_debug();
            } catch (ControlError controlError) {
                tractar_error(controlError);
            }
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
        try {
            controladorPresentacio.afegir_aresta(tipusNode, idNodeOrigen, idNodeDesti);
            System.out.println("S'ha afegit una nova aresta entre els nodes amb id " + idNodeOrigen + " a " + idNodeDesti);
        } catch (ControlError controlError) {
            tractar_error(controlError);
        }

        try {
            print_debug();
        } catch (ControlError controlError) {
            tractar_error(controlError);
        }
    }

    public static void eliminar_aresta() {
        System.out.println("Escriu el tipus de node <Autor,Conferencia,Terme,Paper>: ");
        String tipusNode = scan.nextLine();
        System.out.println("Escriu l'identificador del node origen: ");
        int idNodeOrigen = scan.nextInt();
        System.out.println("Escriu l'identificador del node desti: ");
        int idNodeDesti = scan.nextInt();
        try {
            controladorPresentacio.eliminar_aresta(tipusNode, idNodeOrigen, idNodeDesti);
            System.out.println("S'ha eliminat una aresta entre els nodes amb id " + idNodeOrigen + " a " + idNodeDesti);
        } catch (ControlError controlError) {
            tractar_error(controlError);
        }

        try {
            print_debug();
        } catch (ControlError controlError) {
            tractar_error(controlError);
        }
    }

    public static void consultar_arestes() {
        try {
            print_debug();
        } catch (ControlError controlError) {
            tractar_error(controlError);
        }
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

    public static String crear_cami() {
        System.out.println("Introdueix els diferents nodes que formaran el nou cami <Autor,Paper,Terme,Conferencia>, escriu FI per finalitzar: ");
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
        return cami;
    }

    public static void afegir_cami() {
        scan.nextLine();
        String cami = crear_cami();
        if (!cami.equals("")) {
            System.out.println("Introdueix una descripcio per aquest cami: ");
            String descripcio = scan.nextLine();
            try {
                controladorPresentacio.afegir_cami(cami, descripcio);
            } catch (ControlError controlError) {
                tractar_error(controlError);
            }
        }
    }

    public static void consultar_cami() {
        int opcio = 1;
        while (opcio != 0) {
            ArrayList<CamiPresentacio> camins = controladorPresentacio.get_camins();
            if (camins.size() > 0 ) {
                System.out.println("Camins disponibles: " + camins.size());
                for (int i = 0; i < camins.size(); ++i) {
                    System.out.println(i + 1 + " - Descripcio: " + camins.get(i).descripcio + " Cami: " + camins.get(i).cami);
                }
                System.out.println("=============================");
                System.out.println("1\t Modificar cami");
                System.out.println("2\t Eliminar cami");
                System.out.println("0\t Sortir");
                System.out.println("Escriu una opcio: ");
                opcio = llegir_enter(0, 2);
                switch (opcio) {
                    case 1:
                        System.out.println("Introdueix l'identificador del cami que vols eliminar: ");
                        int id = llegir_enter(1, camins.size());
                        --id;
                        System.out.println("1\t Modificar el cami");
                        System.out.println("2\t Modificar la descripcio");
                        System.out.println("0\t Sortir");
                        System.out.println("Escriu una opcio: ");
                        opcio = llegir_enter(0, 2);
                        switch (opcio) {
                            case 1:
                                scan.nextLine();
                                String cami = crear_cami();
                                try {
                                    controladorPresentacio.modificar_cami(camins.get(id).descripcio, cami);
                                    System.out.println("S'ha modificat correctament");
                                } catch (ControlError controlError) {
                                    tractar_error(controlError);
                                }
                                break;
                            case 2:
                                scan.nextLine();
                                System.out.println("Escriu la nova descripcio: ");
                                String novaDescripcio = scan.nextLine();
                                try {
                                    controladorPresentacio.modificar_descripcio_cami(camins.get(id).descripcio, novaDescripcio);
                                    System.out.println("S'ha modificat correctament");
                                } catch (ControlError controlError) {
                                    tractar_error(controlError);
                                }
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Introdueix l'identificador del cami que vols eliminar: ");
                        id = llegir_enter(1, camins.size());
                        --id;
                        try {
                            controladorPresentacio.eliminar_cami(camins.get(id).descripcio);
                            System.out.println("S'ha eliminat el cami.");
                        } catch (ControlError controlError) {
                            tractar_error(controlError);
                        }
                        break;
                }
            }
            else {
                System.out.println("No hi ha camins.");
                opcio = 0;
            }
        }
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

    public static void main(String[] args) {
        controladorPresentacio = new ControladorPresentacio();

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
    }
}
