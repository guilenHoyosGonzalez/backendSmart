package com.backendSmart.backendSmart.repositorio;

import com.backendSmart.backendSmart.modelo.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TareaRepositorio extends JpaRepository<Tarea,Integer>{
    @Query(
            value = "SELECT t.id_tarea, t.titulo, t.descripcion, t.codigo_estado, e.id, e.estado, t.marca_finalizada  " +
                    "FROM bljrogxhfloex3bn3mib.tarea t " +
                    "INNER JOIN tareas_smarti_db.estado e ON t.codigo_estado = e.id",
            nativeQuery = true
    )
    List<Object[]> findAllTEstado();

}
