package crud.index.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import crud.index.modelos.atleta;
import crud.index.modelos.evento;
import crud.index.modelos.resultado;
import crud.index.repositorio.resultadoRespositorio;

@RestController
public class resultadoControlador {

     @Autowired
    private resultadoRespositorio resulrepo;

    // Obtener todos los resultados
    @GetMapping("/resultados")
    public List<resultado> getAllResultados() {
        return resulrepo.findAll();
    }


    //________________________________________________________________________________________________________________________
    // Crear un nuevo resultado
    @PostMapping("/guardarResultado")
    public ResponseEntity<Object> saveResultado(@RequestBody resultado resultado) {
    
        if (resultado.getId() != null && resulrepo.existsById(resultado.getId())) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("error", true);
            response.put("mensaje", "El ID del resultado ya está registrado");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }


        Optional<evento> eventoOptional = Optional.ofNullable(resultado.getEvento());
        Optional<atleta> atletaOptional = Optional.ofNullable(resultado.getAtleta());
        if (eventoOptional.isEmpty() || atletaOptional.isEmpty()) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("error", true);
            response.put("mensaje", "El resultado debe tener un evento y un atleta asociados");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


        resultado savedResultado = resulrepo.save(resultado);

        HashMap<String, Object> response = new HashMap<>();
        response.put("mensaje", "Resultado creado correctamente");
        response.put("data", savedResultado);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //______________________________________________________________________________________________________________________________________

    // Modificar o actualizar un resultado por su ID
    @PutMapping("/modificarResultado/{id}")
    public ResponseEntity<String> updateResultado(@PathVariable("id") Integer id, @RequestBody resultado resultadoDetails) {
        Optional<resultado> resultadoOptional = resulrepo.findById(id);
        if (resultadoOptional.isPresent()) {
            resultado resultado = resultadoOptional.get();

            resultado.setPosision(resultadoDetails.getPosision());
            resultado.setTiempo_puntaje(resultadoDetails.getTiempo_puntaje());
            resultado.setEvento(resultadoDetails.getEvento());
            resultado.setAtleta(resultadoDetails.getAtleta());

            resulrepo.save(resultado);

            return new ResponseEntity<>("Resultado actualizado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el resultado con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
//___________________________________________________________________________________________________________________________________________
    // Eliminar un resultado por su ID
    @DeleteMapping("/eliminarResultado/{id}")
    public ResponseEntity<String> deleteResultado(@PathVariable("id") Integer id) {
        Optional<resultado> resultadoOptional = resulrepo.findById(id);
        if (resultadoOptional.isPresent()) {
            resultado resultado = resultadoOptional.get();
            resulrepo.delete(resultado);
            return new ResponseEntity<>("Resultado eliminado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el resultado con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
}


