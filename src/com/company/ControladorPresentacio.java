package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ruben Bagan Benavides on 17/04/2016.
 */

public class ControladorPresentacio {
    public ControladorDomini controladorDomini;

    private CamiPresentacio descodificar_cami(String cami) {
        String c = cami.substring(0, cami.indexOf('|'));
        String descripcio = cami.substring(cami.indexOf('|')+1, cami.length());
        return new CamiPresentacio(c, descripcio);
    }

    public Node.TipusNode string_to_tipus_node(String tipusNode) {
        switch (tipusNode.toUpperCase()) {
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

    public void afegir_node(String tipusNode, String nom) throws ControlError {
        if (tipusNode == null || nom == null) throw new ControlError(TaulaErrors.ARGUMENT_NUL);
        Node.TipusNode tipus = string_to_tipus_node(tipusNode);
        if (tipus == null) throw new ControlError(TaulaErrors.NOM_NODE_INVALID);
        controladorDomini.afegir_node(tipus, nom);
    }

    public int eliminar_node(String tipusNode, int idNode) throws ControlError {
        if (tipusNode == null) throw new ControlError(TaulaErrors.ARGUMENT_NUL);
        if (idNode < 0) throw new ControlError(TaulaErrors.NOMBRE_NO_NATURAL);
        Node.TipusNode tipus = string_to_tipus_node(tipusNode);
        if (tipus == null) throw new ControlError(TaulaErrors.NOM_NODE_INVALID);
        return controladorDomini.eliminar_node(tipus, idNode);
    }

    public int modificar_node(String tipusNode, int idNode, String nouNom) throws ControlError {
        if (tipusNode == null || nouNom == null) throw new ControlError(TaulaErrors.ARGUMENT_NUL);
        if (idNode < 0) throw new ControlError(TaulaErrors.NOMBRE_NO_NATURAL);
        Node.TipusNode tipus = string_to_tipus_node(tipusNode);
        if (tipus == null) throw new ControlError(TaulaErrors.NOM_NODE_INVALID);
        return controladorDomini.modificar_node(tipus, idNode, nouNom);
    }

    public String[] get_llista_nodes(String tipusNode) throws ControlError {
        if (tipusNode == null)  throw new ControlError(TaulaErrors.ARGUMENT_NUL);
        Node.TipusNode tipus = string_to_tipus_node(tipusNode);
        if (tipus == null) throw new ControlError(TaulaErrors.NOM_NODE_INVALID);
        return controladorDomini.get_llista_nodes(tipus);
    }

    public int afegir_aresta(String tipusNodeDesti, int idNodeOrigen, int idNodeDesti) throws ControlError {
        if (tipusNodeDesti == null) throw new ControlError(TaulaErrors.ARGUMENT_NUL);
        if (idNodeOrigen < 0 || idNodeOrigen < 0) throw new ControlError(TaulaErrors.NOMBRE_NO_NATURAL);
        Node.TipusNode tipus = string_to_tipus_node(tipusNodeDesti);
        if (tipus == null) throw new ControlError(TaulaErrors.NOM_NODE_INVALID);
        return controladorDomini.afegir_aresta(tipus, idNodeOrigen, idNodeDesti);
    }

    public int eliminar_aresta(String tipusNodeDesti, int idNodeOrigen, int idNodeDesti) throws ControlError {
        if (tipusNodeDesti == null) throw new ControlError(TaulaErrors.ARGUMENT_NUL);
        if (idNodeOrigen < 0 || idNodeOrigen < 0) throw new ControlError(TaulaErrors.NOMBRE_NO_NATURAL);
        Node.TipusNode tipus = string_to_tipus_node(tipusNodeDesti);
        if (tipus == null) throw new ControlError(TaulaErrors.NOM_NODE_INVALID);
        return controladorDomini.eliminar_aresta(tipus, idNodeOrigen, idNodeDesti);
    }

    public CamiPresentacio get_cami(String descripcio) throws ControlError {
        if (descripcio == null) throw new ControlError(TaulaErrors.ARGUMENT_NUL);
        String s = controladorDomini.get_cami(descripcio);
        return descodificar_cami(s);
    }

    public ArrayList<CamiPresentacio> get_camins() {
        String[] caminsCodificats = controladorDomini.get_camins();
        ArrayList<CamiPresentacio> camins = new ArrayList<>(caminsCodificats.length);
        for (int i = 0; i < caminsCodificats.length; ++i) {
            camins.add(descodificar_cami(caminsCodificats[i]));
        }

        return camins;
    }

    public void afegir_cami(String cami, String descripcio) throws ControlError {
        if (descripcio == null || descripcio == null) throw new ControlError(TaulaErrors.ARGUMENT_NUL);
        controladorDomini.afegir_cami(cami, descripcio);
    }

    public void eliminar_cami(String descripcio) throws ControlError {
        if (descripcio == null) throw new ControlError(TaulaErrors.ARGUMENT_NUL);
        controladorDomini.eliminar_cami(descripcio);
    }



}
