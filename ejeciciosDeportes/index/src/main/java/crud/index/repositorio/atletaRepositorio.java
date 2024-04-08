package crud.index.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import crud.index.modelos.atleta;

public interface atletaRepositorio extends JpaRepository<atleta,Integer> {
    
    @Query(value = "SELECT a.id, a.edad, a.genero, a.nombre, a.pais, a.primer_apellido, a.segundo_apellido, d.nombre_deporte " +
                   "FROM atleta a " +
                   "JOIN deporte d ON a.deporte_id = d.id", nativeQuery = true)
    List<Object[]> buscarAtletasConDeporte();
}
