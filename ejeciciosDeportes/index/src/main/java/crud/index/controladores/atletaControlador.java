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
import crud.index.modelos.deporte;
import crud.index.repositorio.atletaRepositorio;
import crud.index.repositorio.deporteRepositorio;







@RestController
public class atletaControlador {

    @Autowired
    private atletaRepositorio atletaRepo;

    @Autowired
    private deporteRepositorio deprepo;


    // Listar todos los atletas
    @GetMapping("/atletas")
    public List<atleta> getAllAtletas() {
        return atletaRepo.findAll();
    }


    //HACER CONSULTA DONDE EN VEZ DEL ID DEL PORTE APAREZCA EL NOMBRE
     @GetMapping("/atletasDeporte") 
  public List<Object[]> buscarAtletasConDeporte(){
      return atletaRepo.buscarAtletasConDeporte();}

    
















  //solo dejar este metodo para crear en atletas
   // Crear un nuevo atleta
@PostMapping("/guardarAtleta")
public ResponseEntity<Object> saveAtleta(@RequestBody atleta atleta) {
    // Verificar si el atleta ya existe en la base de datos
    if (atleta.getId() != null && atletaRepo.existsById(atleta.getId())) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("error", true);
        response.put("mensaje", "El ID del atleta ya está registrado");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    
    // Verificar si el atleta tiene un deporte asociado
    deporte deporte = atleta.getDeporte();
    if (deporte == null || deporte.getId() == null) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("error", true);
        response.put("mensaje", "El atleta debe tener un deporte asociado");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Verificar si el deporte existe
    Optional<deporte> deporteOptional = deprepo.findById(deporte.getId());
    if (deporteOptional.isEmpty()) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("error", true);
        response.put("mensaje", "El deporte especificado no existe");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Guardar el atleta en la base de datos
    atleta savedAtleta = atletaRepo.save(atleta);

    // Preparar la respuesta
    HashMap<String, Object> response = new HashMap<>();
    response.put("mensaje", "Atleta creado correctamente");
    response.put("data", savedAtleta);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
}

///---------------------------------------------------------------------------------------------------



// modificar o actualizar un atleta
@PutMapping("/modificaAtleta/{id}")
public ResponseEntity<String> updateAtleta(@PathVariable("id") Integer id, @RequestBody atleta atletaDetails) {
 
    Optional<atleta> atletaOptional = atletaRepo.findById(id);
    if (atletaOptional.isPresent()) {
        atleta atleta = atletaOptional.get();
        
    
        atleta.setNombre(atletaDetails.getNombre());
        atleta.setPrimer_apellido(atletaDetails.getPrimer_apellido());
        atleta.setSegundo_apellido(atletaDetails.getSegundo_apellido());
        atleta.setPais(atletaDetails.getPais());
        atleta.setEdad(atletaDetails.getEdad());
        atleta.setGenero(atletaDetails.getGenero());
        atleta.setDeporte(atletaDetails.getDeporte()); 
        atletaRepo.save(atleta);
        
        return new ResponseEntity<>("Atleta actualizado correctamente", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("No se encontró el atleta con ID: " + id, HttpStatus.NOT_FOUND);}
    }

//---------------------------------------------------------------------------------------------------------------
    // Eliminar un atleta por su ID
@DeleteMapping("/eliminarAtleta/{id}")
public ResponseEntity<String> Deleteatleta(@PathVariable("id") Integer id) {
    Optional<atleta> AtletaOptional = atletaRepo.findById(id);
    if (AtletaOptional.isPresent()) {
        atleta atleta = AtletaOptional.get();
        atletaRepo.delete(atleta);
        return new ResponseEntity<>("Atleta eliminado correctamente", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("No se encontró el atleta con ID: " + id, HttpStatus.NOT_FOUND);
    }
}




























}
    

     


