package com.svalero.ecotip.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Usuario {
    private long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private boolean colaborador;
    private LocalDate fechaNacimiento;
    private List<Animal> animales;

    public Usuario(long id, String nombre, String apellidos, String dni,
                   String email, boolean colaborador, LocalDate fechaNacimiento, List<Animal> animales){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.colaborador = colaborador;
        this.fechaNacimiento = fechaNacimiento;
        this.animales = animales;
    }

    public Usuario(){

    }
}


