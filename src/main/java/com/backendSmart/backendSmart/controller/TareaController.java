package com.backendSmart.backendSmart.controller;

import com.backendSmart.backendSmart.excepcion.RecursoNoEncontradoException;
import com.backendSmart.backendSmart.modelo.Estado;
import com.backendSmart.backendSmart.modelo.Tarea;
import com.backendSmart.backendSmart.servicio.EstadoServicio;
import com.backendSmart.backendSmart.servicio.TareaServicio;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api-smarti")
@CrossOrigin(value = "http://localhost:4200")
public class TareaController {

    private static final Logger logger=
            LoggerFactory.getLogger(TareaController.class);

    @Autowired
    TareaServicio tareaServicio;

    @Autowired
    EstadoServicio estadoServicio;

    @PostMapping("/agregarTarea")
    public ResponseEntity<?>  agregarTarea(@RequestBody Tarea bodyTarea) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(bodyTarea.getTitulo());
        tarea.setDescripcion(bodyTarea.getDescripcion());
        tarea.setTitulo(bodyTarea.getTitulo());

        Estado estado = new Estado();
        estado.setId(bodyTarea.getEstado().getId());
        tarea.setEstado(estado);

        tarea.setMarcaFinalizada(bodyTarea.getMarcaFinalizada());

        this.tareaServicio.crearTarea(tarea);

        return ResponseEntity.status(HttpStatus.OK).body(tarea);

     }

    @GetMapping("/getTareas")
    public List<Tarea> getTareas() {
         logger.info("Producto a tareas: ");
         List<Object[]> tareas =this.tareaServicio.listarTareas();
         List<Tarea> tareaDTO = new ArrayList<>();

         for (Object[] fila : tareas) {

             Tarea tarea = new Tarea();

             Integer idTarea = (Integer) fila[0];
             String titulo = (String) fila[1];
             String descricion = (String) fila[2];

             Estado estado = new Estado();
             estado.setId((Integer) fila[3]);
             estado.setEstado((String) fila[5]);


             Integer marca  = (Integer) fila[6];

            tarea.setIdTarea(idTarea);
            tarea.setTitulo(titulo);
            tarea.setDescripcion(descricion);
            tarea.setEstado(estado);
            tarea.setMarcaFinalizada(marca);

             tareaDTO.add(tarea);

            /* logger.info("\n"+" [0] : " + idTarea + "\n"+
                            " Titulo [1]: " +  titulo +"\n"+
                            "Descripcion [2] : " + descricion+ "\n"+
                             "codigo_estado [3] : " +estado + "\n"+
                             "id[4] : " + fila[4] + "\n"+
                             "Estado[5] : " + fila[5]+ "\n"+
                             "Marca[6] : " + marca + "\n"
                     );

             */

         }

         return tareaDTO;

     }


    @GetMapping("/getTareaPorId/{id}")
    public ResponseEntity<Tarea> getTareaPorId(@PathVariable int id){
        Tarea tarea = this.tareaServicio.buscarTareasPorId(id);
        if(tarea!=null){
            return  ResponseEntity.ok(tarea);
        }else{
            throw new RecursoNoEncontradoException();
        }
    }
    
    @PutMapping("/editarTarea/{id}")
    public ResponseEntity<?> editarTarea(
            @PathVariable int id,
            @RequestBody Tarea tareaRecibida){

        Tarea tarea = this.tareaServicio.buscarTareasPorId(id);
        if(tarea!=null){

            tarea.setTitulo(tareaRecibida.getTitulo());
            tarea.setDescripcion(tareaRecibida.getDescripcion());
            tarea.setTitulo(tareaRecibida.getTitulo());

            Estado estado = new Estado();
            estado.setId(tareaRecibida.getEstado().getId());
            tarea.setEstado(estado);
            tarea.setMarcaFinalizada(tareaRecibida.getMarcaFinalizada());

            this.tareaServicio.crearTarea(tarea);

            return ResponseEntity.status(HttpStatus.OK).body(tarea);

        }else{
            return ResponseEntity.status(HttpStatus.OK).body("No existe el registro");
        }

    }

    @DeleteMapping("/tarea/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarTarea(@PathVariable int id){
        Tarea tarea = this.tareaServicio.buscarTareasPorId(id);
        this.tareaServicio.eliminarTarea(tarea.getIdTarea());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado. ", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    // session  de logica de  estado
    @PostMapping("/agregarEstado")
    public ResponseEntity<?>  agregarTarea(@RequestBody Estado bodyEstado) {

        Estado estado = new Estado();
        estado.setEstado(bodyEstado.getEstado());

        this.estadoServicio.crearEstado(estado);
        return ResponseEntity.status(HttpStatus.OK).body(estado);

    }

    @GetMapping("/getEstados")
    public List<Estado> getEstados() {

        List<Estado> estados = this.estadoServicio.listarEstados();
        List<Estado> estadosResumidos = new ArrayList<>();

        for (Estado estado : estados) {

            Estado estadoResumido = new Estado();
            estadoResumido.setId(estado.getId());
            estadoResumido.setEstado(estado.getEstado());

            estadosResumidos.add(estadoResumido);
        }

        return estadosResumidos;
    }
}
