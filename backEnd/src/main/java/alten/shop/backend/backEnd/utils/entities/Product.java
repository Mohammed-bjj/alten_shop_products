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


    @NotNull(message = "Code is required")
    @NotBlank(message = "Code is required")
    private String code;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;


    private String description;
    private Long price=0L;
    private Long quantity=0L;
    private String inventoryStatus = String.valueOf(StatusStock.OUTOFSTOCK);
    private String category;
    private Long rating;
    private String image;



    @Override
    public boolean equals(Object obj) {
        System.out.println("Init");
        if (this == obj) {
            System.out.println("1");

            return true; // Réflexivité
        }
        if (obj == null ) {
            System.out.println("2");

            return false;   // null
        }
        System.out.println("3");

        InputProductDTO dto = (InputProductDTO) obj;


        boolean isv = this.name.trim().equals(dto.getName().trim()) &&
                this.description.trim().equals(dto.getDescription()) &&
                this.price == dto.getPrice() &&
                this.quantity == dto.getQuantity() &&
                this.inventoryStatus.trim().equals(dto.getInventoryStatus().trim()) &&
                this.category.trim().equals(dto.getCategory().trim()) ;
        System.out.println("4 : "+ isv);

        return  this.name.trim().equals(dto.getName().trim()) &&
                this.description.trim().equals(dto.getDescription()) &&
                this.price == dto.getPrice() &&
                this.quantity == dto.getQuantity() &&
                this.inventoryStatus.trim().equals(dto.getInventoryStatus().trim()) &&
                this.category.trim().equals(dto.getCategory().trim()) ;
    }
    


}
