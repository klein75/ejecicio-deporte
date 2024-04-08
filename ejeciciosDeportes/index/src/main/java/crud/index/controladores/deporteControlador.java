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

import crud.index.modelos.deporte;
import crud.index.repositorio.deporteRepositorio;
@RestController
public class deporteControlador {



    //DEPREPO = DEPORTE REPOSITORIO 
    @Autowired
    private deporteRepositorio deprepo;

    

     //lista general de estados
    @GetMapping("/deportes")
    public List<deporte> getdeporte() {
        return deprepo.findAll();
    }


    //buscar deporte por nombre

    @GetMapping("/deportes/{nombreDeporte}")
    public deporte buscarDeportePorNombre(@PathVariable String nombreDeporte) {
        return deprepo.findBynombre_deporte(nombreDeporte);
    }



//__________________________________________________________________________________________________________________________
    //crear deporte
    @PostMapping("/creardeporte")
    public ResponseEntity <Object> savedeporte (@RequestBody deporte deporte){
        Optional<deporte> res = deprepo.findById(deporte.getId());
        HashMap<String, Object> dato = new HashMap<>();

        if (res.isPresent()){
            dato.put("error", true);
            dato.put("mensaje","el id ya se encuentra registrado");
            return new ResponseEntity<>(dato , HttpStatus.CONFLICT);

        }
        deprepo.save(deporte);
        dato.put("data", deporte);
        dato.put("mensaje", "creado correctamente");
        return new ResponseEntity<>(dato, HttpStatus.CREATED);


    }

 // modificar o actualizar un deporte
    @PutMapping("/modificadeporte/{id}")
    public ResponseEntity<String> updatedeporte(@PathVariable("id") Integer id, @RequestBody deporte deporteDetails) {
        deporte deporte = deprepo.findById(id).orElse(null);
        if (deporte != null) {
            deporte.setNombre_deporte(deporteDetails.getNombre_deporte());
            deporte.setDescripcion(deporteDetails.getDescripcion());
    
            deprepo.save(deporte);
            return new ResponseEntity<>("deporte actualizado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el deporte con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }


     // eliminar  
    @DeleteMapping("/eliminardeporte/{id}")
    public ResponseEntity<String> deletedeporte(@PathVariable("id") Integer id) {
        deporte deporte = deprepo.findById(id).orElse(null);
        if (deporte != null) {
            deprepo.delete(deporte);
            return new ResponseEntity<>("Deporte eliminado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró  ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
