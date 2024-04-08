package crud.index.modelos;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
public class deporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String nombre_deporte;

   
    @Column(columnDefinition = "TEXT")
    private String descripcion;

  

    

   



}
