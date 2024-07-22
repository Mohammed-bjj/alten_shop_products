package alten.shop.backend.backEnd.utils.dto.input;


import lombok.Data;
import jakarta.validation.constraints.*;


@Data
public class InputUserDTO {

    @NotNull(message = "Email is mandatory")
    private String username;

    @NotNull(message = "Password is mandatory")
    private String password;
}
