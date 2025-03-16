package com.backendSmart.backendSmart.servicio;

import com.backendSmart.backendSmart.modelo.Estado;
import com.backendSmart.backendSmart.repositorio.EstadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoServicio implements IEstadoServicio{

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @Override
    public List<Estado> listarEstados() {
        return estadoRepositorio.findAll();
    }

    @Override
    public Estado buscarEstadoPorId(Integer id) {
        return estadoRepositorio.findById(id).orElse(null);
    }

    @Override
    public void crearEstado(Estado estado) {
        estadoRepositorio.save(estado);
    }

    @Override
    public void eliminarEstado(Integer id) {
        estadoRepositorio.deleteById(id);
    }
}
