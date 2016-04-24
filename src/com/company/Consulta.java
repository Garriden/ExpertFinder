package com.company;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adri
 */

public abstract class Consulta {
    private Node origen;
    private Cami cami;
    private Resultat resultat;

    public Consulta(Node origen, Cami cami) {
        this.origen = origen;
        this.cami = cami;
    }

    public void set_origen(Node origen) {
        this.origen = origen;
    }

    public void set_cami(Cami cami) {
        this.cami = cami;
    }

    public void set_resultat(Resultat resultat) {
        this.resultat = resultat;
    }

    public Node get_prigen() {
        return this.origen;
    }

    public Cami get_cami() {
        return this.cami;
    }

    public Resultat get_resultat() {
        return this.resultat;
    }

}