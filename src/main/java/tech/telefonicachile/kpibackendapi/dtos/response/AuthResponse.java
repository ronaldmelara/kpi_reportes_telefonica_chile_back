package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";



}
