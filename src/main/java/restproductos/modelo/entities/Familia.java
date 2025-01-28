package restproductos.modelo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "familias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    // private Integer codigoFamilia

    private String descripcion;
}
