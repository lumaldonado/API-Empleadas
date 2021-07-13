package ar.com.ada.api.empleadas.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleadas.entitites.Categoria;
import ar.com.ada.api.empleadas.entitites.Empleada;
import ar.com.ada.api.empleadas.entitites.Empleada.EstadoEmpleadaEnum;
import ar.com.ada.api.empleadas.models.request.InfoEmpleadaNueva;
import ar.com.ada.api.empleadas.models.request.SueldoNuevoEmpleada;
import ar.com.ada.api.empleadas.models.response.GenericResponse;
import ar.com.ada.api.empleadas.services.CategoriaService;
import ar.com.ada.api.empleadas.services.EmpleadaService;

@RestController
public class EmpleadaController {
    
    @Autowired
    private EmpleadaService service;

    @Autowired
    CategoriaService categoriaService;

    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleada(@RequestBody InfoEmpleadaNueva empleadaInfo){
        GenericResponse respuesta = new GenericResponse();

        //pasar todo esto a un metodo de service

        Empleada empleada = new Empleada();
        empleada.setNombre(empleadaInfo.nombre);
        empleada.setEdad(empleadaInfo.edad);
        empleada.setSueldo(empleadaInfo.sueldo);
        empleada.setFechaAlta(new Date());
        Categoria categoria = categoriaService.buscarCategoria(empleadaInfo.categoriaId);
        empleada.setCategoria(categoria);
        empleada.setEstado(EstadoEmpleadaEnum.ACTIVO);

        //hasta aca

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


   @GetMapping("/empleados/{id}")   
   //La variable de los parentesis del metodo, 
   //su nombre tiene que coincidir con la path variable
   //y tambien hay que aclarar si lo es
   public ResponseEntity<?> getEmpleadaPorId(@PathVariable Integer id){
    Empleada empleada = service.buscarEmpleada(id);  
    if (empleada == null){
         return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(empleada);
   }

   @DeleteMapping("/empleados/{id}")
   public ResponseEntity<GenericResponse> bajaEmpleada(@PathVariable Integer id){
    service.bajaEmpleadaPorId(id);
    GenericResponse respuesta = new GenericResponse();
    respuesta.isOk = true;
    respuesta.id = id;
    respuesta.message = "La empleada fue dada de baja con exito";
    return ResponseEntity.ok(respuesta);

   }
   @GetMapping("/empleados/categorias/{catId}")
   public ResponseEntity<List<Empleada>> obtenerEmpleadasPorCategoria(@PathVariable Integer catId){
      List<Empleada> empleadas = service.traerEmpleadaPorCategoria(catId); 
    return ResponseEntity.ok(empleadas);

   }

   @PutMapping("/empleados/{id}/sueldos")
   public ResponseEntity<GenericResponse> modificarSueldo(@PathVariable Integer id, @RequestBody SueldoNuevoEmpleada sueldoNuevoInfo){
       Empleada empleada = service.buscarEmpleada(id);
       empleada.setSueldo(sueldoNuevoInfo.sueldoNuevo);
       service.guardar(empleada);
    GenericResponse respuesta = new GenericResponse();
    respuesta.isOk = true;
    respuesta.message = "Sueldo actualizado";
    return ResponseEntity.ok(respuesta);

   }


}
