package com.company;

import java.util.ArrayList;

/**
 * Created by Marc Garrido on 17/04/2016. Col·laboracio Ruben Bagan Benavides
 */

public class Graf {
    private Matriu paperAutor;
    private Matriu paperConferencia;
    private Matriu paperTerme;

    private ArrayList<Node> paper;
    private ArrayList<Node> conferencia;
    private ArrayList<Node> autor;
    private ArrayList<Node> terme;


    // Para crear un grafo hay que inicializarlo siempre, sino no tienes ningun grafo, seria un grafo vacio.
    // Los ArrayList de nodos, tienen que tener almenos 1 elemento cada uno, y las Matrices son P x Y, donde Y es Conf,
    // Autor o Termino y P es Paper. Los nodos en los ArrayList su id tiene que ser igual a la posicion donde estan
    // en el vector, y la misma en su Matriz.
    /* Ejemplo:
        ArrayList<Nodo> Autor <-- Contiene 2 elementos, en la posicion 0 hay el Nodo (0 , Marco), en la posicion 1
                                  hay el Nodo (1, Polo)
        ArrayList<Nodo> Paper <-- Contiene 3 elementos: en la pos 0, hay el nodo (0, Papel1), en la pos 1 (1, Papel2),
                                  y en la pos 2 (2, Papel3)

        La matriz es de 3 x 2:
                Marco   Polo
        Papel1  0.0     0.0
        Papel2  0.0     0.0
        Papel3  0.0     0.0


     */


    public Graf(Matriu paperAutor, Matriu paperConferencia, Matriu paperTerme, ArrayList<Node> paper,
                ArrayList<Node> terme, ArrayList<Node> autor, ArrayList<Node> conferencia)
    {
        this.paperAutor = paperAutor;
        this.paperConferencia = paperConferencia;
        this.paperTerme = paperTerme;

        this.paper = paper;
        this.conferencia = conferencia;
        this.autor = autor;
        this.terme = terme;
    }


    // Pre:  Cert.
    // Post: Afegeix el node desitjat al final del vector corresponent i amplia les 
    //        columnes i/o files de la seva matriu. Retorna 0 si el node s’afegeix correctament.
    // Errors:
    //      -1 = argument null
    //      -2 = el node que es vol afegir ja existeix.
    //      -3 = el tipus node no existeix
    public int afegir_node(Node.TipusNode tipusNode, String nomNode) {
        if (tipusNode == null || nomNode == null) return -1;
        switch (tipusNode) {
            case AUTOR:
                int id = this.autor.size();
                if (this.autor.add(new Node(id, nomNode, tipusNode))) {
                    this.paperAutor.afegir_columna();
                    return 0;
                }
                break;
            case PAPER:
                id = this.paper.size();
                if (this.paper.add(new Node(id, nomNode, tipusNode))) {
                    this.paperAutor.afegir_fila();
                    this.paperTerme.afegir_fila();
                    this.paperConferencia.afegir_fila();
                    return 0;
                }
                break;
            case TERME:
                id = this.terme.size();
                if (this.terme.add(new Node(id, nomNode, tipusNode))) {
                    paperTerme.afegir_columna();
                    return 0;
                }
                break;
            case CONFERENCIA:
                id = this.conferencia.size();
                if (this.conferencia.add(new Node(id, nomNode, tipusNode))) {
                    this.paperConferencia.afegir_columna();
                    return 0;
                }
                break;
            default:
                return -3;
        }
        return -2;
    }

    //Pre:  Node Origen sempre serà paper, les matrius son Paper x [Autor,Conferencia,Terme].
    //Post: Afegeix un '1' en la posició corresponent de la matriu paper_(TipusNodeDestí). Retorna 0 si l’aresta s’afegeix correctament.
    // Errors:
    //      -1 = argument null
    //      -2 = id negatiu
    //      -3 = id >= tamany del vector
    //      -4 = tipus node incorrecte
    //      -5 = nodeOrigen no es de tipus paper
    public int afegir_aresta(Node nodeOrigen, Node nodeDesti) {
        if (nodeOrigen == null || nodeDesti == null) return -1;
        if (nodeOrigen.get_id() < 0 || nodeDesti.get_id() < 0) return -2;
        if (nodeOrigen.get_tipus_node() != Node.TipusNode.PAPER) return -5;
        switch (nodeDesti.get_tipus_node()) {
            case AUTOR:
                if (nodeOrigen.get_id() >= this.paper.size() || nodeDesti.get_id() >= this.autor.size()) return -3;
                this.paperAutor.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 1.0);
                break;
            case CONFERENCIA:
                if (nodeOrigen.get_id() >= this.paper.size() || nodeDesti.get_id() >= this.conferencia.size()) return -3;
                this.paperConferencia.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 1.0);
                break;
            case TERME:
                if (nodeOrigen.get_id() >= this.paper.size() || nodeDesti.get_id() >= this.terme.size()) return -3;
                this.paperTerme.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 1.0);
                break;
            default:
                return -4;
        }
        return 0;
    }

    //Pre:  Cert.
    //Post: Elimina el node desitjat al final de l'Array corresponent i treu les columnes i/o files la matriu corresponent. 
    //      Retorna 0 si el node s'elimina correctament.
    // Errors:
    //      -1 = argument null;
    //      -2 = no hay ningun nodo con esa id ni tipo.
    //      -3 = tipus node incorrecte
    public int eliminar_node(Node node) {
        if (node == null) return -1;
        switch (node.get_tipus_node()) {
            case AUTOR:
                if (this.autor.remove(node.get_id()) != null) {
                    this.paperAutor.eliminar_columna(node.get_id());
                    for (int i = node.get_id(); i < autor.size(); ++i) {
                        autor.get(i).set_id(i);
                    }
                    return 0;
                }
                return -2;
            case PAPER:
                if (this.paper.remove(node.get_id()) != null) {
                    this.paperAutor.eliminar_fila(node.get_id());
                    this.paperTerme.eliminar_fila(node.get_id());
                    this.paperConferencia.eliminar_fila(node.get_id());
                    for (int i = node.get_id(); i < paper.size(); ++i) {
                        paper.get(i).set_id(i);
                    }
                    return 0;
                }
                return -2;
            case TERME:
                if (this.terme.remove(node.get_id()) != null) {
                    this.paperTerme.eliminar_columna(node.get_id());
                    for (int i = node.get_id(); i < terme.size(); ++i) {
                        terme.get(i).set_id(i);
                    }
                    return 0;
                }
                return -2;
            case CONFERENCIA:
                if (this.conferencia.remove(node.get_id()) != null) {
                    this.paperConferencia.eliminar_columna(node.get_id());
                    for (int i = node.get_id(); i < conferencia.size(); ++i) {
                        conferencia.get(i).set_id(i);
                    }
                    return 0;
                }
                return -2;
        }
        return -3;
    }

    //Pre:  Node Origen sempre serà paper, les matrius son Paper x [Autor,Conferencia,Terme].
    //Post: Afegeix un '0' en la posició corresponent de la matriu paper_(TipusNodeDestí). Retorna 0 si l'aresta s'elimina correctament.
    // Errors:
    //      -1 = argument null;
    //      -2 = id negatiu
    //      -3 = id >= tamany del vector
    //      -4 = tipus node incorrecte
    //      -5 = nodeOrigen no es de tipus paper
    public int eliminar_aresta(Node nodeOrigen, Node nodeDesti) {
        if (nodeOrigen == null || nodeDesti == null) return -1;
        if (nodeOrigen.get_id() < 0 || nodeDesti.get_id() < 0) return -2;
        if (nodeOrigen.get_tipus_node() != Node.TipusNode.PAPER) return -5;
        switch (nodeDesti.get_tipus_node()) {
            case AUTOR:
                if (nodeOrigen.get_id() >= this.paper.size() || nodeDesti.get_id() >= this.autor.size()) return -3;
                this.paperAutor.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 0.0);
                break;
            case CONFERENCIA:
                if (nodeOrigen.get_id() >= this.paper.size() || nodeDesti.get_id() >= this.conferencia.size()) return -3;
                this.paperConferencia.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 0.0);
                break;
            case TERME:
                if (nodeOrigen.get_id() >= this.paper.size() || nodeDesti.get_id() >= this.terme.size()) return -3;
                this.paperTerme.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 0.0);
                break;
            default:
                return -4;
        }

        return 0;
    }

    // Pre: Cert.
    // Post: Canvia el nom del node indicat. Retorna 0 si el node s'actualitza correctament.
    // Errors:
    //      -1 = argument null;
    //      -2 = id negatiu
    //      -3 = id >= tamany del vector
    //      -4 = tipus node incorrecte
    public int actualizar_node(Node node) {
        if (node == null) return -1;
        if (node.get_id() < 0) return -2;
        switch (node.get_tipus_node()) {
            case AUTOR:
                if (node.get_id() >= this.autor.size()) return -3;
                this.autor.get(node.get_id()).set_nom(node.get_nom());
                break;
            case PAPER:
                if (node.get_id() >= this.paper.size()) return -3;
                this.paper.get(node.get_id()).set_nom(node.get_nom());
                break;
            case TERME:
                if (node.get_id() >= this.terme.size()) return -3;
                this.terme.get(node.get_id()).set_nom(node.get_nom());
                break;
            case CONFERENCIA:
                if (node.get_id() >= this.conferencia.size()) return -3;
                this.conferencia.get(node.get_id()).set_nom(node.get_nom());
                break;
            default:
                return -4;
        }
        return 0;
    }



    //Pre: Cert.
    //Post: Retorna el node indicat.
    public Node get_node(int i, Node.TipusNode tipus)
    {
        switch(tipus) {
            case AUTOR:
                if(i < autor.size()) return autor.get(i);
            case CONFERENCIA:
                if(i < conferencia.size()) return conferencia.get(i);
            case PAPER:
                if(i < paper.size()) return paper.get(i);
            case TERME:
                if(i < terme.size()) return terme.get(i);
            default:
                return null;
        }
    }

    //Pre: Cert.
    //Post: Retorna la matriu paperAutor.
    public Matriu get_paper_autor() {
        return this.paperAutor;
    }

    //Pre: Cert.
    //Post: Retorna la matriu paperConferencia.
    public Matriu get_paper_conferencia() {
        return this.paperConferencia;
    }

    //Pre: Cert.
    //Post: Retorna la matriu paperTerme.
    public Matriu get_paper_terme() {
        return this.paperTerme;
    }

    //Pre: Cert.
    //Post: Retorna la ArrayList get_paper.
    public ArrayList<Node> get_paper() {
        return this.paper;
    }

    //Pre: Cert.
    //Post: Retorna la ArrayList get_conferencia.
    public ArrayList<Node> get_conferencia() {
        return this.conferencia;
    }

    //Pre: Cert.
    //Post: Retorna la ArrayList get_autor.
    public ArrayList<Node> get_autor() {
        return this.autor;
    }

    //Pre: Cert.
    //Post: Retorna la ArrayList get_terme.
    public ArrayList<Node> get_terme() {
        return this.terme;
    }


}
