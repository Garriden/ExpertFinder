package com.company;

import java.util.ArrayList;

import static com.company.TaulaErrors.CAMI_DUPLICATE;
import static com.company.TaulaErrors.CAMI_NOT_FOUND;

/**
 * Created by Ruben Bagan Benavides on 19/04/2016.
 */

public class ControladorCami {
    private ArrayList<Cami> camins;

    public ControladorCami() {
        this.camins = new ArrayList<>();
    }

    public Cami get_cami(String descripcio) throws ControlError {
        for (int i = 0; i < this.camins.size(); ++i) {
            if (this.camins.get(i).get_descripcio().equalsIgnoreCase(descripcio)) {
                return this.camins.get(i);
            }
        }
        throw new ControlError(CAMI_NOT_FOUND);
    }

    public ArrayList<Cami> get_camins() {
        return this.camins;
    }

    public void afegir_cami(Cami c) throws ControlError {
        if (!this.camins.contains(c)) {
            this.camins.add(c);
        }
        else throw new ControlError(CAMI_DUPLICATE);
    }

    public void eliminar_cami(String descripcio) throws ControlError {
        Cami c = get_cami(descripcio);
        this.camins.remove(c);
    }

    public void modificar_descripcio(String descripcio, String novaDescripcio) throws ControlError {
        get_cami(descripcio).set_descripcio(novaDescripcio);
    }

    public void modificar_cami(String descripcio, String cami) throws ControlError {
        get_cami(descripcio).set_cami(cami);
    }
}
