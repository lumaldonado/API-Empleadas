package ar.com.ada.api.empleadas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import ar.com.ada.api.empleadas.entitites.Categoria;
import ar.com.ada.api.empleadas.repos.CategoriaRepository;

@Service
public class CategoriaService {

    //el autowired va atado al resto de las categorias
    //por eso si pones un autowired en otro lado tiene que tener una notacion
    //Conecta las diferentes cosas entre si

    @Autowired
    CategoriaRepository repo;

    public void crearCategoria(Categoria categoria){
        repo.save(categoria);
    }

    public List<Categoria> traerCategorias(){
        //se usa return porque en este caso tiene que devolver algo
        return repo.findAll();
    }

    public Categoria buscarCategoria(Integer categoriaId){
        Optional<Categoria> resultado = repo.findById(categoriaId);
        Categoria categoria = null;

        if (resultado.isPresent())
        categoria = resultado.get();

        return categoria;
    }




}
