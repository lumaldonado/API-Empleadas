package ar.com.ada.api.empleadas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleadas.entitites.Categoria;
import ar.com.ada.api.empleadas.entitites.Empleada;
import ar.com.ada.api.empleadas.entitites.Empleada.EstadoEmpleadaEnum;
import ar.com.ada.api.empleadas.repos.EmpleadaRepository;

@Service
public class EmpleadaService {
   
    @Autowired
    EmpleadaRepository repo;

    @Autowired
    CategoriaService categoriaService;

    public void crearEmpleada(Empleada empleada){
        repo.save(empleada);
    }

    public List<Empleada> traerEmpleadas(){
        return repo.findAll();
    }

    public Empleada buscarEmpleada(Integer empleadaId){
         Optional<Empleada> resultado = repo.findById(empleadaId);
         
         if(resultado.isPresent())
             return resultado.get();
         
         return null;    

    }
    //DELETE LOGICO
    //Mantiene en la base datos el empleado, pero cambiando el estado.
    public void bajaEmpleadaPorId(Integer id){
       Empleada empleada = this.buscarEmpleada(id); 
       empleada.setEstado(EstadoEmpleadaEnum.BAJA);
       empleada.setFechaBaja(new Date());
       repo.save(empleada);
    }


    public List<Empleada> traerEmpleadaPorCategoria(Integer catId) {
		Categoria categoria = categoriaService.buscarCategoria(catId);
        return categoria.getEmpleadas();

	}
}
