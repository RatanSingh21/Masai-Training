package in.ratansgh.order_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDto {

    private String name;
    private double price;
    private Integer quantity;
}
