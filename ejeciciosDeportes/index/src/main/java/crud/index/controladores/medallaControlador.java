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

import crud.index.modelos.medalla;
import crud.index.repositorio.medallaRepositorio;

@RestController
public class medallaControlador {

    @Autowired 
    private medallaRepositorio medallaRepo;

        @GetMapping("/medallas")
    public List<medalla> getAllMedallas() {
        return medallaRepo.findAll();
    }

    // Crear una nueva medalla
    @PostMapping("/nuevaMedallas")
    public ResponseEntity<Object> saveMedalla(@RequestBody medalla medalla) {
        medalla savedMedalla = medallaRepo.save(medalla);
        HashMap<String, Object> response = new HashMap<>();
        response.put("mensaje", "Medalla creada correctamente");
        response.put("data", savedMedalla);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
//_______________________________________________________________________________________________________________________________
    // MODIFICAR MEDALLA
    @PutMapping("/modificarMedallas/{id}")
    public ResponseEntity<String> updateMedalla(@PathVariable("id") Integer id, @RequestBody medalla medallaDetails) {
        Optional<medalla> medallaOptional = medallaRepo.findById(id);
        if (medallaOptional.isPresent()) {
            medalla medalla = medallaOptional.get();
            medalla.setTipo(medallaDetails.getTipo());
            medalla.setResultado(medallaDetails.getResultado());
            medallaRepo.save(medalla);
            return new ResponseEntity<>("Medalla actualizada correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró la medalla con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
//_________________________________________________________________________________________________________________________________________
 // Eliminar una medalla 
    @DeleteMapping("/medallas/{id}")
    public ResponseEntity<String> deleteMedalla(@PathVariable("id") Integer id) {
        Optional<medalla> medallaOptional = medallaRepo.findById(id);
        if (medallaOptional.isPresent()) {
            medallaRepo.delete(medallaOptional.get());
            return new ResponseEntity<>("Medalla eliminada correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró la medalla con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }




}
