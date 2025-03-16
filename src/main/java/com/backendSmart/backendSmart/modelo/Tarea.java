package com.backendSmart.backendSmart.modelo;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idTarea;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigoEstado")  // Columna en la tabla tareas que referencia a estado
    private Estado estado;

    String titulo;
    String descripcion;
    Integer marcaFinalizada;

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getMarcaFinalizada() {
        return marcaFinalizada;
    }

    public void setMarcaFinalizada(Integer marcaFinalizada) {
        this.marcaFinalizada = marcaFinalizada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
