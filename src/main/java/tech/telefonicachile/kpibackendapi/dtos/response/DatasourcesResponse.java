package tech.telefonicachile.kpibackendapi.dtos.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class DatasourcesResponse {
    Integer Id;
    Integer idParent;
    String value;

    public DatasourcesResponse(Integer id, Integer idParent, String value) {
        Id = id;
        this.idParent = idParent;
        this.value = value;
    }
}
