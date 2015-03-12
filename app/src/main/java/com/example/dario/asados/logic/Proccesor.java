package com.example.dario.asados.logic;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Dario on 11/03/15.
 */
public class Proccesor {


    public float getPromedio(ArrayList<Contenedor> lista) {
        return this.getTotal(lista)  / lista.size();
    }

    public float getTotal(ArrayList<Contenedor> lista) {
        float total = 0;
        for (Contenedor contenedor : lista) {
            total = total + contenedor.getValue();
        }
        return total;
    }


    public ArrayList<ResultEntry> calculate(ArrayList<Contenedor> lista) {
        float each = 0;
        float price = 0;

        each = this.getPromedio(lista);
        ArrayList<Contenedor> deudores, acreedores = null;
        ArrayList<ResultEntry> resultado = new ArrayList<ResultEntry>();

        deudores = new ArrayList<Contenedor>();
        acreedores = new ArrayList<Contenedor>();
        for (Contenedor object : lista) {
            price = object.getValue() - each;
            object.setValue(Math.abs(price));
            if (price < 0)
                deudores.add(object);
            else
                acreedores.add(object);
        }

        Collections.sort(deudores);
        Collections.sort(acreedores);

        for (Contenedor acreedor : acreedores) {
            for (Contenedor deudor : deudores) {
                if (acreedor.getValue() != 0
                        && acreedor.getValue() > deudor.getValue()
                        && deudor.getValue() > 0) {
                    acreedor.setValue(acreedor.getValue() - deudor.getValue());
                    resultado.add(new ResultEntry(deudor.getNombre(), acreedor
                            .getNombre(), deudor.getValue()));

                    deudor.setValue((float) 0);

                } else if (acreedor.getValue() != 0
                        && acreedor.getValue() <= deudor.getValue()
                        && deudor.getValue() > 0) {
                    deudor.setValue(deudor.getValue() - acreedor.getValue());
                    resultado.add(new ResultEntry(deudor.getNombre(), acreedor
                            .getNombre(), acreedor.getValue()));
                    acreedor.setValue((float) 0);
                }

                if (acreedor.getValue() == 0)
                    break;

            }

        }
        return resultado;
    }

}
