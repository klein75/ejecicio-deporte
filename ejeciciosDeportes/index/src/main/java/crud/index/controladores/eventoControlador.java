package crud.index.controladores;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import crud.index.modelos.deporte;
import crud.index.modelos.evento;
import crud.index.repositorio.deporteRepositorio;
import crud.index.repositorio.eventoRepositorio;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class eventoControlador {

    @Autowired
    private eventoRepositorio evenrepo;

     @Autowired
    private deporteRepositorio deprepo;


    //generar lista de eventos <>

    @GetMapping("/enventos")
    public List<evento> getevento(){
        return evenrepo.findAll();}



    //______________________________________________________________________________________________
    
    //crear evento <>
    @PostMapping("/guardarEvento")
public ResponseEntity<Object> saveEvento(@RequestBody evento evento) {
    // Verificar si el evento ya existe en la base de datos
    if (evento.getId() != null && evenrepo.existsById(evento.getId())) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("error", true);
        response.put("mensaje", "El ID del evento ya está registrado");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    
    deporte deporte = evento.getDeporte();
    if (deporte == null || deporte.getId() == null) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("error", true);
        response.put("mensaje", "El evento debe tener un deporte asociado");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    Optional<deporte> deporteOptional = deprepo.findById(deporte.getId());
    if (deporteOptional.isEmpty()) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("error", true);
        response.put("mensaje", "El deporte especificado no existe");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    evento savedEvento = evenrepo.save(evento);

    HashMap<String, Object> response = new HashMap<>();
    response.put("mensaje", "Evento creado correctamente");
    response.put("data", savedEvento);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
}
    
//____________________________________________________________________________________________________

// Modificar o actualizar un evento por su ID
@PutMapping("/modificaEvento/{id}")
public ResponseEntity<String> updateEvento(@PathVariable("id") Integer id, @RequestBody evento eventoDetails) {
    Optional<evento> eventoOptional = evenrepo.findById(id);
    if (eventoOptional.isPresent()) {
        evento evento = eventoOptional.get();
        
        evento.setNombre_evento(eventoDetails.getNombre_evento());
        evento.setFecha_evento(eventoDetails.getFecha_evento());
        evento.setHora(eventoDetails.getHora());
        evento.setLugar(eventoDetails.getLugar());
        evento.setDeporte(eventoDetails.getDeporte());
        
        evenrepo.save(evento);
        
        return new ResponseEntity<>("Evento actualizado correctamente", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("No se encontró el evento con ID: " + id, HttpStatus.NOT_FOUND);
    }
}
//_______________________________________________________________________________________________________________________


// Eliminar un evento por su ID
@DeleteMapping("/eliminarEvento/{id}")
public ResponseEntity<String> deleteEvento(@PathVariable("id") Integer id) {
    Optional<evento> eventoOptional = evenrepo.findById(id);
    if (eventoOptional.isPresent()) {
        evento evento = eventoOptional.get();
        evenrepo.delete(evento);
        return new ResponseEntity<>("Evento eliminado correctamente", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("No se encontró el evento con ID: " + id, HttpStatus.NOT_FOUND);
    }
}













































}
