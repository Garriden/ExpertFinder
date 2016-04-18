package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ruben Bagan Benavides on 17/04/2016.
 */

public class ControladorPresentacio {
    public ControladorDomini controladorDomini;

    private NodePresentacio descodificar_node(String nodeCodificat) {
        NodePresentacio node = new NodePresentacio();
        int i  = nodeCodificat.indexOf('|');
        node.id = Integer.parseInt(nodeCodificat.substring(0, i));
        node.nom = nodeCodificat.substring(i+1, nodeCodificat.length());
        return node;
    }

    public ControladorPresentacio() {
        controladorDomini = new ControladorDomini();
    }

    public boolean afegir_node(Node.TipusNode tipusNode, String nom) {
        return controladorDomini.afegir_node(tipusNode, nom);
    }

    public int afegir_aresta(Node.TipusNode tipusNodeDesti, int idNodeOrigen, int idNodeDesti) {
        return controladorDomini.afegir_aresta(tipusNodeDesti, idNodeOrigen, idNodeDesti);
    }

    public int eliminar_aresta(Node.TipusNode tipusNodeDesti, int idNodeOrigen, int idNodeDesti) {
        return controladorDomini.eliminar_aresta(tipusNodeDesti, idNodeOrigen, idNodeDesti);
    }

    public int eliminar_node(Node.TipusNode tipusNode, int idNode) {
        return controladorDomini.eliminar_node(tipusNode, idNode);
    }

    public Node.TipusNode string_to_tipus_node(String tipusNode) {
        switch (tipusNode) {
            case "AUTOR": return Node.TipusNode.AUTOR;
            case "TERME": return Node.TipusNode.TERME;
            case "PAPER": return Node.TipusNode.PAPER;
            case "CONFERENCIA": return Node.TipusNode.CONFERENCIA;
            default: return null;
        }
    }

    public int modificar_node(Node.TipusNode tipusNode, int idNode, String nouNom) {
        return controladorDomini.modificar_node(tipusNode, idNode, nouNom);
    }

    public ArrayList<NodePresentacio> get_llista_nodes(Node.TipusNode tipusNode) {
        ArrayList<String> llistaNodesCodificats = controladorDomini.get_llista_nodes(tipusNode);
        if  (llistaNodesCodificats == null) return null;

        ArrayList<NodePresentacio> llista_nodes = new ArrayList<NodePresentacio>();
        for (String s : llistaNodesCodificats) {
            llista_nodes.add(descodificar_node(s));
        }

        return llista_nodes;
    }

}
