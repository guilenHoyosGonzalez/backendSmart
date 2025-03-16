package com.backendSmart.backendSmart.servicio;

import com.backendSmart.backendSmart.modelo.Tarea;

import java.util.List;

public interface ITareaServicio {
    public List<Object[]> listarTareas();

    public Tarea buscarTareasPorId(Integer id);

    public  void crearTarea(Tarea tarea);

    public void eliminarTarea(Integer id);

}
