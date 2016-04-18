package com.company;

import java.util.ArrayList;

/**
 * Created by Ruben Bagan Benavides on 17/04/2016.
 */

public class ControladorDomini {
    public Graf graf;

    private String codificar_node(int idNode, String nomNode) {
        return (idNode + "|" + nomNode);
    }

    public ControladorDomini() {
        graf = new Graf();
    }

    public boolean afegir_node(Node.TipusNode tipusNode, String nomNode) {
        return graf.afegir_node(tipusNode, nomNode);
    }

    public boolean afegir_aresta(Node.TipusNode tipusNodeDesti, int idNodeOrigen, int idNodeDesti) {
        return graf.afegir_aresta(graf.get_node(idNodeOrigen, Node.TipusNode.PAPER), graf.get_node(idNodeDesti, tipusNodeDesti));
    }

    public String get_node_iessim(Node.TipusNode tipusNode, int idNode) {
        Node node = graf.get_node(idNode, tipusNode);
        if (node == null) return null;
        return codificar_node(node.get_id(), node.get_nom());
    }

    public int eliminar_node(Node.TipusNode tipusNode, int idNode) {
        return graf.eliminar_node(graf.get_node(idNode, tipusNode));
    }

    public int modificar_node(Node.TipusNode tipusNode, int idNode, String nouNom) {
        Node node = graf.get_node(idNode, tipusNode);
        node.set_nom(nouNom);
        return graf.actualizar_node(node);
    }

    public ArrayList<String> get_llista_nodes(Node.TipusNode tipusNode) {
        ArrayList<Node> llistaNodes;
        switch (tipusNode) {
            case AUTOR: llistaNodes = graf.get_autor(); break;
            case CONFERENCIA: llistaNodes = graf.get_conferencia(); break;
            case TERME: llistaNodes = graf.get_terme(); break;
            case PAPER: llistaNodes = graf.get_paper(); break;
            default: return null;
        }
        if (llistaNodes == null) return null;
        ArrayList<String> llistaNodesCodificada = new ArrayList<String>();
        for (Node n : llistaNodes) {
            llistaNodesCodificada.add(codificar_node(n.get_id(), n.get_nom()));
        }
        return llistaNodesCodificada;
    }


}
