package com.svalero.ecotip.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Animal {
    private long id;
    private String nombre;
    private String especie;
    private float peso;
    private boolean EnPeligro;
    private LocalDate fechaAvistamiento;
    private boolean apadrinado;
    private Ecosistema ecosistema;
    private List<Usuario> usuarios;

    public Animal(long id, String nombre, String especie, float peso, boolean enPeligro, LocalDate fechaAvistamiento, boolean apadrinado, Ecosistema ecosistema, List<Usuario> usuarios) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.peso = peso;
        EnPeligro = enPeligro;
        this.fechaAvistamiento = fechaAvistamiento;
        this.apadrinado = apadrinado;
        this.ecosistema = ecosistema;
        this.usuarios = usuarios;
    }

    public Animal(){}
}
