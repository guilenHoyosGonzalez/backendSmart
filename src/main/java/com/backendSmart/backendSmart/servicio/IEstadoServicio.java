package com.backendSmart.backendSmart.servicio;

import com.backendSmart.backendSmart.modelo.Estado;
import com.backendSmart.backendSmart.modelo.Tarea;

import java.util.List;

public interface IEstadoServicio {
    public List<Estado> listarEstados();

    public Estado buscarEstadoPorId(Integer id);

    public  void crearEstado(Estado estado);

    public void eliminarEstado(Integer id);
}
