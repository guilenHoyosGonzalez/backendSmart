package com.backendSmart.backendSmart.servicio;

import com.backendSmart.backendSmart.modelo.Tarea;
import com.backendSmart.backendSmart.repositorio.TareaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaServicio implements ITareaServicio{

    @Autowired
    private TareaRepositorio tareaRepositorio;

    @Override
    public List<Object[]> listarTareas() {
        return  tareaRepositorio.findAllTEstado();
    }

    @Override
    public Tarea buscarTareasPorId(Integer id) {
        Tarea tarea = tareaRepositorio.findById(id).orElse(null);
        return tarea;
    }

    @Override
    public void crearTarea(Tarea tarea) {
        tareaRepositorio.save(tarea);
    }

    @Override
    public void eliminarTarea(Integer id) {
        tareaRepositorio.deleteById(id);
    }
}
