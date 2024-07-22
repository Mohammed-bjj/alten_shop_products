package alten.shop.backend.backEnd.services;


import alten.shop.backend.backEnd.utils.dto.input.InputUserDTO;
import alten.shop.backend.backEnd.utils.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class AuthService {


    private JwtEncoder jwtEncoder;

    public Map<String, String> authenticate(InputUserDTO inputUserDTO, Authentication authentication) {
        Instant instant = Instant.now();
        String scopeAuth = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(60, ChronoUnit.MINUTES))
                .subject(inputUserDTO.getUsername())
                .claim("scope", scopeAuth)
                .build();
        JwtEncoderParameters jwtEncoderParameters =
                JwtEncoderParameters.from(
                        JwsHeader.with(MacAlgorithm.HS512).build(),
                        jwtClaimsSet
                );
        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return Map.of("accessToken", jwt);
    }
}
