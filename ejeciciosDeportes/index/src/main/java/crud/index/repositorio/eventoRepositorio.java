package crud.index.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import crud.index.modelos.evento;

public interface eventoRepositorio extends JpaRepository<evento, Integer> {

}
