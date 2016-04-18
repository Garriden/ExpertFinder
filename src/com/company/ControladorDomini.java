package com.company;

import java.util.ArrayList;

/**
 * Created by Ruben Bagan Benavides on 17/04/2016.
 */

public class ControladorDomini {
    private Graf graf;

    private String codificar_node(int idNode, String nomNode) {
        return (idNode + "|" + nomNode);
    }

    public ControladorDomini() {
        graf = new Graf();
    }

    public int afegir_node(Node.TipusNode tipusNode, String nomNode) {
        return graf.afegir_node(tipusNode, nomNode);
    }

    public String get_node_iessim(int i, Node.TipusNode tipusNode) {
        Node node = graf.get_node(1, tipusNode);
        if (node == null) return null;
        return codificar_node(node.get_id(), node.get_nom());
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
