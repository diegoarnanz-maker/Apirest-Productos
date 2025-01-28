package restproductos.modelo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    // private String codigoProducto

    private String descripcion;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column(name = "codigo_familia")
    private Integer codigoFamilia;

    private String marca;

    private String color;

    @ManyToOne
    @JoinColumn(name = "codigo_familia")
    private Familia familia;
}
