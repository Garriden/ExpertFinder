package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ruben Bagan Benavides on 17/04/2016.
 */

public class ControladorPresentacio {
    public ControladorDomini controladorDomini;

    public Node.TipusNode string_to_tipus_node(String tipusNode) {
        switch (tipusNode) {
            case "AUTOR": return Node.TipusNode.AUTOR;
            case "TERME": return Node.TipusNode.TERME;
            case "PAPER": return Node.TipusNode.PAPER;
            case "CONFERENCIA": return Node.TipusNode.CONFERENCIA;
            default: return null;
        }
    }

    public ControladorPresentacio() {
        controladorDomini = new ControladorDomini();
    }

    public int afegir_node(String tipusNode, String nom) {
        if (tipusNode == null || nom == null) return -2;
        Node.TipusNode tipus = string_to_tipus_node(tipusNode);
        if (tipus == null) return -3;
        return controladorDomini.afegir_node(tipus, nom);
    }

    public int eliminar_node(String tipusNode, int idNode) {
        if (tipusNode == null) return -2;
        if (idNode < 0) return -4;
        Node.TipusNode tipus = string_to_tipus_node(tipusNode);
        if (tipus == null) return -3;
        return controladorDomini.eliminar_node(tipus, idNode);
    }

    public int modificar_node(String tipusNode, int idNode, String nouNom) {
        if (tipusNode == null || nouNom == null) return -2;
        if (idNode < 0) return -4;
        Node.TipusNode tipus = string_to_tipus_node(tipusNode);
        if (tipus == null) return -3;
        return controladorDomini.modificar_node(tipus, idNode, nouNom);
    }

    public String[] get_llista_nodes(String tipusNode) {
        if (tipusNode == null) return null;
        Node.TipusNode tipus = string_to_tipus_node(tipusNode);
        if (tipus == null) return null;
        return controladorDomini.get_llista_nodes(tipus);
    }

    public int afegir_aresta(String tipusNodeDesti, int idNodeOrigen, int idNodeDesti) {
        if (tipusNodeDesti == null) return -2;
        if (idNodeOrigen < 0 || idNodeOrigen < 0) return -4;
        Node.TipusNode tipus = string_to_tipus_node(tipusNodeDesti);
        if (tipus == null) return -3;
        return controladorDomini.afegir_aresta(tipus, idNodeOrigen, idNodeDesti);
    }

    public int eliminar_aresta(String tipusNodeDesti, int idNodeOrigen, int idNodeDesti) {
        if (tipusNodeDesti == null) return -2;
        if (idNodeOrigen < 0 || idNodeOrigen < 0) return -4;
        Node.TipusNode tipus = string_to_tipus_node(tipusNodeDesti);
        if (tipus == null) return -3;
        return controladorDomini.eliminar_aresta(tipus, idNodeOrigen, idNodeDesti);
    }



}
