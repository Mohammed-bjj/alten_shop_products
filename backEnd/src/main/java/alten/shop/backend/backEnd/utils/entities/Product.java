package alten.shop.backend.backEnd.utils.entities;


import alten.shop.backend.backEnd.utils.dto.input.InputProductDTO;
import alten.shop.backend.backEnd.utils.enums.StatusStock;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Produits")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotBlank(message = "Code is required")
    private String code;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be positive or zero")
    private Long price=0L;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity must be positive or zero")
    private Long quantity=0L;

    @NotBlank(message = "InventoryStatus is required")
    private String inventoryStatus = String.valueOf(StatusStock.OUTOFSTOCK);

    @NotBlank(message = "Category is required")
    private String category;

    private Long rating;
    private String image;



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Réflexivité
        }
        if (obj == null ) {
            return false;   // null
        }
        System.out.println("3");
        InputProductDTO dto = (InputProductDTO) obj;

        return  this.name.trim().equals(dto.getName().trim()) &&
                this.description.trim().equals(dto.getDescription()) &&
                this.price == dto.getPrice() &&
                this.quantity == dto.getQuantity() &&
                this.inventoryStatus.trim().equals(dto.getInventoryStatus().trim()) &&
                this.category.trim().equals(dto.getCategory().trim()) ;
    }
    


}
