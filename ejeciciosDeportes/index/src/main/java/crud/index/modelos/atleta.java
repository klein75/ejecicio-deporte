package crud.index.modelos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

@Entity
public class atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String nombre;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String primer_apellido;

    @Column(columnDefinition = "VARCHAR(100)")
    private String segundo_apellido;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String pais;

    @Column(nullable = false)
    private Integer edad;

    @Column(columnDefinition = "VARCHAR(50)")
    private String genero;

    @ManyToOne
    @JoinColumn(name = "deporteid",  referencedColumnName = "id")
    private deporte deporte;

  


}