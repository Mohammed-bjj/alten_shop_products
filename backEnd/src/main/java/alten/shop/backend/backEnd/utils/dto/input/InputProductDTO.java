package alten.shop.backend.backEnd.utils.dto.input;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class InputProductDTO {

    @NotNull(message = "Code is required")
    @NotBlank(message = "Code is required")
    private String code;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;

    private String description = "";

    @PositiveOrZero(message = "Price must be positive or zero")
    private Long price = 0L;

    private String category = "";

    @PositiveOrZero(message = "Quantity must be positive or zero")
    private Long quantity = 0L;

    private String inventoryStatus = "";

    private Long rating = 0L;
    private String image = "";
}
