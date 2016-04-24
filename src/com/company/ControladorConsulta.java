package com.company;


//  Created by Edgar Perez


import java.util.ArrayList;

public class ControladorConsulta {
    private Resultat resultat_nc;
    private Resultat resultat_ncg;
    private double resultat_ncn;

    public Resultat consulta_node_cami(Node node, Cami cami, Graf graf){
        HeteSim hetesim = new HeteSim();
        Matriu matriu = hetesim.relacio_node_matriu(node, cami, graf);
        double[][] mResultado = matriu.get_data();

        String path = cami.get_cami();
        char[] aux = path.toCharArray();
        char tn = aux[aux.length-1];
        ArrayList<Node> type;
        if(tn == 'A') type = graf.get_autor();
        else if(tn == 'C') type = graf.get_conferencia();
        else if(tn == 'P') type = graf.get_paper();
        else type = graf.get_terme();

        for(int i = 0; i < Mresultado[0].length; ++i){
            if(Mresultado[0][i] != 0)resultat_nc.add_res(type.get(i), Mresultado[0][i]);
        }
        return resultat_nc;
    }

    public Resultado consulta_nodo_cami_grado(Node node, Cami cami, double factor, double error, Graf graf){
        Hetesim hetesim;
        Matriu matriu = hetesim.relacio_node_matriu(node, cami, graf);
        double[][] Mresultado = matriu.get_data();

        String path = cami.getCami();
        char[] aux = path.toCharArray();
        char tn = aux[aux.length-1];
        ArrayList<Node> type;
        if(tn == 'A') type = graf.get_autor();
        else if(tn == 'C') type = graf.get_conferencia();
        else if(tn == 'P') type = graf.get_paper();
        else type = graf.get_terme();

        for(int i = 0; i < Mresultado[0].length; ++i){
            if(Mresultado[0][i] != 0) resultat_nc.add_res(type.get(i), Mresultado[0][i]);
        }

        double min = factor + error;
        double max = factor - error;
        resultat_ncg.filtrar_resultat(min, max);

        return resultat_ncg;

    }

    public double consulta_nodo_cami_nodo(Node node, Cami cami, Node node2, Graf graf){
        Hetesim hetesim;
        resultat_ncn = hetesim.grau_relacio(node, node2, cami, graf);
        return resultat_ncn;
    }
}
