package com.example.pokedexcompleta;

public class Pokemon {
    String nombre;

    int numero;

    public Pokemon(String nombre, String categoria, int numero) {
        this.nombre = nombre;

        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }



    public int getNumero() {
        return numero;
    }
}
