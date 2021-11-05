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
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping("/categorias")
    // NINGUN WEB METHOD DEVUELVE VOID
    // El responseEntity con <?> dice que puede responder cualquier cosa
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {

        GenericResponse respuesta = new GenericResponse();
        service.crearCategoria(categoria);
        respuesta.isOk = true;
        respuesta.id = categoria.getCategoriaId();
        respuesta.message = "La Categoria fue creada con exito";

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/categorias") // hacer mapping
    public ResponseEntity<List<Categoria>> traerCategorias() {
        // return response entity
        return ResponseEntity.ok(service.traerCategorias());
        // return entity valor esperado
    }


}
