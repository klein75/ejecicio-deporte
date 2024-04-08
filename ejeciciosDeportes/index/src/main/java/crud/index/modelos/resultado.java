package crud.index.modelos;

import java.math.BigDecimal;


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
public class resultado {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer posision;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal tiempo_puntaje;

     @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private evento evento;

    @ManyToOne
    @JoinColumn(name = "atleta_id", referencedColumnName = "id")
    private atleta atleta;


}
