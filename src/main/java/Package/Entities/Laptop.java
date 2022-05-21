package Package.Entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "laptops")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Laptop {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("This is the primary key of Laptop entity")
    private Long id;
    private String brand;
    private String model;
    private Double price;
    private Boolean warranty;

}
