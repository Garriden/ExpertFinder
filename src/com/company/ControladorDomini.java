package com.company;

import java.util.ArrayList;

/**
 * Created by Ruben Bagan Benavides on 17/04/2016.
 */

public class ControladorDomini {
    public Graf graf;
    private ControladorCami controladorCami;

    private String codificar_node(int idNode, String nomNode) {
        return (idNode + "|" + nomNode);
    }

    private String codificar_cami(Cami c) {
        return (c.get_descripcio() + "|" + c.get_cami());
    }

    public ControladorDomini() {
        double[][] data = {{1,0,0},{0,1,0}};
        Matriu m = new Matriu(2,3);
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

        graf = new Graf(m,m,m, paper, terme, autor, conferencia);
        controladorCami = new ControladorCami();
    }

    public int afegir_node(Node.TipusNode tipusNode, String nomNode) {
        return graf.afegir_node(tipusNode, nomNode);
    }

    public int afegir_aresta(Node.TipusNode tipusNodeDesti, int idNodeOrigen, int idNodeDesti) {
        return graf.afegir_aresta(graf.get_node(idNodeOrigen, Node.TipusNode.PAPER), graf.get_node(idNodeDesti, tipusNodeDesti));
    }

    public int eliminar_aresta(Node.TipusNode tipusNodeDesti, int idNodeOrigen, int idNodeDesti) {
        return graf.eliminar_aresta(graf.get_node(idNodeOrigen, Node.TipusNode.PAPER), graf.get_node(idNodeDesti, tipusNodeDesti));
    }

    public int eliminar_node(Node.TipusNode tipusNode, int idNode) {
        return graf.eliminar_node(graf.get_node(idNode, tipusNode));
    }

    public int modificar_node(Node.TipusNode tipusNode, int idNode, String nouNom) {
        return graf.actualizar_node(new Node(idNode, nouNom, tipusNode));
    }

    public String[] get_llista_nodes(Node.TipusNode tipusNode) {
        ArrayList<Node> llistaNodes;
        switch (tipusNode) {
            case AUTOR: llistaNodes = graf.get_autor();             break;
            case TERME: llistaNodes = graf.get_terme();             break;
            case PAPER: llistaNodes = graf.get_paper();             break;
            case CONFERENCIA: llistaNodes = graf.get_conferencia(); break;
            default: return null;
        }
        String[] nodes = new String[llistaNodes.size()];
        for (int i = 0; i < llistaNodes.size(); ++i) {
            nodes[i] = llistaNodes.get(i).get_nom();
        }
        return nodes;
    }

    public String get_cami(String descripcio) throws ControlError {
        Cami c = controladorCami.get_cami(descripcio);
        return codificar_cami(c);
    }

    public String[] get_camins() {
        ArrayList<Cami> camins = controladorCami.get_camins();
        String[] caminsCodificats = new String[camins.size()];
        for (int i = 0; i < camins.size(); ++i) {
            caminsCodificats[i] = codificar_cami(camins.get(i));
        }
        return caminsCodificats;
    }

    public void afegir_cami(String cami, String descripcio) throws ControlError {
        if (Cami.cami_valid(cami)) {
            controladorCami.afegir_cami(new Cami(cami, descripcio));
        }
        else throw new ControlError(TaulaErrors.CAMI_NO_VALID);
    }

    public void eliminar_cami(String descripcio) throws ControlError {
        controladorCami.eliminar_cami(descripcio);
    }


}
