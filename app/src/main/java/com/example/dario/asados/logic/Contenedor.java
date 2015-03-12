package com.example.dario.asados.logic;

import java.io.Serializable;
public class Contenedor implements Comparable<Contenedor>, Serializable {
    String nombre;
    Float value;

    public Contenedor(String nombre, Float valor) {
        this.nombre = nombre;
        this.value = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float valor) {
        this.value = valor;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Nombre: " + nombre + ", valor " + value;
    }

    @Override
    public int compareTo(Contenedor o) {

        return Float.compare(o.getValue(), this.value);
    }

}