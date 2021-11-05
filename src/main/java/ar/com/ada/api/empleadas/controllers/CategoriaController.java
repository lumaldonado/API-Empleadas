package ar.com.ada.api.empleadas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.empleadas.entitites.*;
import ar.com.ada.api.empleadas.models.response.GenericResponse;
import ar.com.ada.api.empleadas.services.CategoriaService;

@RestController
public class CategoriaController  {

    @Autowired
    CategoriaService service;

   @PostMapping("/categorias")
   //NINGUN WEB METHOD DEVUELVE VOID
   //El responseEntity con <?> dice que puede responder cualquier cosa
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria){
        
        GenericResponse respuesta = new GenericResponse();
        service.crearCategoria(categoria);
        respuesta.isOk = true;
        respuesta.id = categoria.getCategoriaId();
        respuesta.message = "La Categoria fue creada con exito";

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/categorias")//hacer mapping
    public ResponseEntity<List<Categoria>> traerCategorias(){
        // return response entity
        return ResponseEntity.ok(service.traerCategorias());
        //return entity valor esperado
    }

    @GetMapping("/categorias/sueldos-nuevos")
    public ResponseEntity<List<Empleada>> calcularProximosSueldos() {
        return ResponseEntity.ok(service.calcularProximosSueldos());
    }

    @GetMapping("/categorias/sueldos-actuales")
    public ResponseEntity<List<Empleada>> obtenerSueldosActuales() {
        return ResponseEntity.ok(service.obtenerSueldosActuales());
    }

    @GetMapping("/categorias/sin-empleadas")
    public ResponseEntity<List<Categoria>> obtenerCategoriasSinEmpleadas() {
        return ResponseEntity.ok(service.obtenerCategoriasSinEmpleadas());
    }

    @GetMapping("/categorias/minimo-sueldo")
    public ResponseEntity<Categoria> obtenerCategoriaConMinimoSueldo() {
        return ResponseEntity.ok(service.obtenerCategoriaConMinimoSueldo());
    }

    @GetMapping("/categorias/nombres")
    public ResponseEntity<List<String>> obtenerNombresCategorias() {
        return ResponseEntity.ok(service.obtenerNombresCategorias());
    }

    
}
