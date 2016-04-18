package com.company;

/**
 * Created by Ruben Bagan Benavides on 17/04/2016.
 */
public class Resultat {
    private Node node;
    private double grauRelevancia;

    public Resultat(Node node, double grauRelevancia) {
        this.node = node;
        this.grauRelevancia = grauRelevancia;
    }

    public Node get_node() {
        return this.node;
    }

    public double get_grau_relevancia() {
        return this.grauRelevancia;
    }
}
