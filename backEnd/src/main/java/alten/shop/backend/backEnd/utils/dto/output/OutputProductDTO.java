package alten.shop.backend.backEnd.utils.dto.output;



import lombok.Data;
import jakarta.validation.constraints.*;


@Data
public class OutputProductDTO {

     @NotBlank(message = "Id is required")
     private Long id;

     @NotBlank(message = "Code is required")
     private String code;

     @NotBlank(message = "Name is required")
     private String name;

     @NotBlank(message = "Description is required")
     private String description;

     @NotNull(message = "Price must not be null")
     @PositiveOrZero(message = "Price must be positive or zero")
     private Long price;

     @NotBlank(message = "Category is required")
     private String category;

     @NotNull(message = "Quantity must not be null")
     @PositiveOrZero(message = "Quantity must be positive or zero")
     private Long quantity;

     @NotNull(message = "Inventory status must not be null")
     private String inventoryStatus;

     private Long rating ;
     private String image;

}
