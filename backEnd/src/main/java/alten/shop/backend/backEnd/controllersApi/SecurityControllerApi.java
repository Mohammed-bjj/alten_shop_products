package alten.shop.backend.backEnd.controllersApi;


import alten.shop.backend.backEnd.services.AuthService;
import alten.shop.backend.backEnd.utils.dto.input.InputUserDTO;
import alten.shop.backend.backEnd.utils.exceptions.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class SecurityControllerApi {

    private AuthenticationManager authenticationManager;
    private AuthService authService;

    @Operation(summary = "Send Profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Send Profite"),
            @ApiResponse(responseCode = "404", description = "")
    })
    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication) {
        return  authentication;
    }

    @Operation(summary = "Authentificat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentificat"),
            @ApiResponse(responseCode = "404", description = "Not found User")
    })
    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody InputUserDTO inputUserDTO)  {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(inputUserDTO.getUsername(), inputUserDTO.getPassword())
        );
        return this.authService.authenticate(inputUserDTO, authentication);
    }

}
