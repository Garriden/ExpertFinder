package com.company;


import java.util.ArrayList;
/**
 * Created by Edgar Perez
 */

public class Resultat {
    private class Pair{
        private Node node;
        private double grauRellevancia;
        private Pair(Node node, double grauRellevancia){
            this.node = node;
            this.grauRellevancia = grauRellevancia;
        }
    }

    private ArrayList<Pair> taula;

    public Resultat(){
        taula = new ArrayList<Pair>();
    }

    public void afegir_resultat(Node node, double grauRellevancia){
        Pair p = new Pair(node, grauRellevancia);
        taula.add(p);
    }

    public int mida(){
        return taula.size();
    }

    public double get_factor(int pos){
        return (taula.get(pos)).grauRellevancia;
    }

    public Node get_node(int pos){
        return (taula.get(pos)).node;
    }

    public void eliminar_resultat(int pos){
        taula.remove(pos);
    }

    public int get_pos(Node node, double grauRellevancia){
        Pair p = new Pair(node, grauRellevancia);
        return taula.indexOf(p);
    }

    public void filtrar_resultat(double min, double max){
        for(int i = 0; i < taula.size(); ++i){
            if(taula.get(i).grauRellevancia < min || taula.get(i).grauRellevancia > max) taula.remove(i);
        }
    }
}
