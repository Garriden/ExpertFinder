package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.company.Node.TipusNode.*;

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

    public int afegir_node(Node.TipusNode tipusNode, String nomNode) {
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
        }
        return -1;
    }

    // Node Origen sempre es paper, perque les matrius son Paper x [Autor,Conferencia,Terme]
    public int afegir_aresta(Node nodeOrigen, Node nodeDesti) {
        switch (nodeDesti.get_tipus_node()) {
            case AUTOR:
                this.paperAutor.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 1.0);
                break;
            case CONFERENCIA:
                this.paperConferencia.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 1.0);
                break;
            case TERME:
                this.paperTerme.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 1.0);
                break;
            default:
                return -1;
        }
        return 0;
    }

    public int eliminar_node(Node node) {
        switch (node.get_tipus_node()) {
            case AUTOR:
                if (this.autor.remove(node.get_id()) != null) {
                    this.paperAutor.eliminar_columna(node.get_id());
                    for (int i = node.get_id(); i < autor.size(); ++i) {
                        autor.get(i).set_id(i);
                    }
                    return 0;
                }
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
            case TERME:
                if (this.terme.remove(node.get_id()) != null) {
                    this.paperTerme.eliminar_columna(node.get_id());
                    for (int i = node.get_id(); i < terme.size(); ++i) {
                        terme.get(i).set_id(i);
                    }
                    return 0;
                }
            case CONFERENCIA:
                if (this.conferencia.remove(node.get_id()) != null) {
                    this.paperConferencia.eliminar_columna(node.get_id());
                    for (int i = node.get_id(); i < conferencia.size(); ++i) {
                        conferencia.get(i).set_id(i);
                    }
                    return 0;
                }
        }
        return -1;
    }

    public int actualizar_node(Node node) {
        switch (node.get_tipus_node()) {
            case AUTOR:
                this.autor.get(node.get_id()).set_nom(node.get_nom());
                break;
            case PAPER:
                this.paper.get(node.get_id()).set_nom(node.get_nom());
                break;
            case TERME:
                this.terme.get(node.get_id()).set_nom(node.get_nom());
                break;
            case CONFERENCIA:
                this.conferencia.get(node.get_id()).set_nom(node.get_nom());
                break;
            default:
                return -1;
        }
        return 0;
    }

    public int eliminar_aresta(Node nodeOrigen, Node nodeDesti) {
        switch (nodeDesti.get_tipus_node()) {
            case AUTOR:
                this.paperAutor.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 0.0);
                break;
            case CONFERENCIA:
                this.paperConferencia.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 0.0);
                break;
            case TERME:
                this.paperTerme.set_valor(nodeOrigen.get_id(), nodeDesti.get_id(), 0.0);
                break;
            default:
                return -1;
        }
        return 0;
    }

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

    public Matriu get_paper_autor() {
        return this.paperAutor;
    }

    public Matriu get_paper_conferencia() {
        return this.paperConferencia;
    }

    public Matriu get_paper_terme() {
        return this.paperTerme;
    }

    public ArrayList<Node> get_paper() {
        return this.paper;
    }

    public ArrayList<Node> get_conferencia() {
        return this.conferencia;
    }

    public ArrayList<Node> get_autor() {
        return this.autor;
    }

    public ArrayList<Node> get_terme() {
        return this.terme;
    }



    public void set_paper_autor(double[][] matriuAdjecencia) {
        paperAutor.set_data(matriuAdjecencia);
    }
}
