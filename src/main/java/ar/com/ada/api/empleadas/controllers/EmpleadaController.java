package ar.com.ada.api.empleadas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleadas.entitites.Empleada;
import ar.com.ada.api.empleadas.models.response.GenericResponse;
import ar.com.ada.api.empleadas.services.EmpleadaService;

@RestController
public class EmpleadaController {
    
    @Autowired
    private EmpleadaService service;

    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleada(@RequestBody Empleada empleada){

        GenericResponse respuesta = new GenericResponse();
        service.crearEmpleada(empleada);
        respuesta.isOk = true;
        respuesta.id = empleada.getEmpleadaId();
        respuesta.message = "La Empleada fue creada con exito";

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleada>> traerEmpleadas(){
        return ResponseEntity.ok(service.traerEmpleadas());

    }
}
