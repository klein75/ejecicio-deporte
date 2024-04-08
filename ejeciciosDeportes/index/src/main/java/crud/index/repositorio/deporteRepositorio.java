package crud.index.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import crud.index.modelos.deporte;

public interface deporteRepositorio extends JpaRepository <deporte , Integer> {

    @Query("SELECT d FROM deporte d WHERE d.nombre_deporte = ?1")
    deporte findBynombre_deporte(String nombreDeporte);



    
}
