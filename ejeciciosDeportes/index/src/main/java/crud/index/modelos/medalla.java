package crud.index.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class medalla {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

       @Enumerated(EnumType.STRING)
    private tipo_medalla tipo;

    @ManyToOne
    @JoinColumn(name="resultado_id", referencedColumnName = "id")
    private resultado resultado;
}
