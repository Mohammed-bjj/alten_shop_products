package alten.shop.backend.backEnd.utils.dto.input;

import alten.shop.backend.backEnd.utils.enums.Category;
import alten.shop.backend.backEnd.utils.enums.StatusStock;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class InputProductDTO {

    @NotBlank(message = "Code is required")
    private String code;

    @NotBlank(message = "Name is required")
    private String name;


    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be positive or zero")
    private Long price;

    @NotNull(message = "Category is required")
    private Category category;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity must be positive or zero")
    private Long quantity ;

    @NotNull(message = "InventoryStatus is required")
    private StatusStock inventoryStatus;

    private Long rating = 0L;
    private String image = "";
}
