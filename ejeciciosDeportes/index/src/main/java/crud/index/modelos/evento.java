package crud.index.modelos;

import java.sql.Date;
import java.sql.Time;


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
public class evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String nombre_evento;

    @Column(nullable = false)
    private Date fecha_evento;

    @Column(nullable = false)
    private Time hora;

    @Column(columnDefinition = "VARCHAR(100)")
    private String lugar;

    @ManyToOne
    @JoinColumn(name = "deporte_id", referencedColumnName = "id")
    private deporte deporte;


}
