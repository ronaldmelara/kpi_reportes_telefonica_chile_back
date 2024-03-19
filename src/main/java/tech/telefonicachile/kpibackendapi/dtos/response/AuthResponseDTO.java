package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";



}
