package com.svalero.ecotip.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ecosistema {
    private long id;
    private String nombre;
    private String descripcion;
    private float temperaturaMedia;
    private boolean active;
    private LocalDate createdAt;
    private List<Animal> animales;

    public Ecosistema(long id, String nombre, String descripcion, float temperaturaMedia, boolean active, LocalDate createdAt, List<Animal> animales) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.temperaturaMedia = temperaturaMedia;
        this.active = active;
        this.createdAt = createdAt;
        this.animales = animales;
    }
    public Ecosistema(){
    }
}
