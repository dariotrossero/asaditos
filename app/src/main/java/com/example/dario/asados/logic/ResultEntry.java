package com.example.dario.asados.logic;


public class ResultEntry {
    String deudor, acreedor;
    Float value;

    public ResultEntry(String deudor, String acreedor, Float valor) {
        this.deudor = deudor;
        this.acreedor = acreedor;
        this.value = valor;
    }

    public String getDeudor() {
        return this.deudor;
    }

    public String getAcreedor() {
        return this.acreedor;
    }

    public float getValue() {
        return this.value;
    }

    // esto es solo de prueba
    @Override
    public String toString() {
        return deudor + " paga a " + acreedor + " "
                + String.format("%.2f", value) + "\n";
    }
}