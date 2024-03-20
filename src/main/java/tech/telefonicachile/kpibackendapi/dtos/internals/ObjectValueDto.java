package tech.telefonicachile.kpibackendapi.dtos.internals;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ObjectValueDto {
    Integer Id;

    public ObjectValueDto(Integer id, String value) {
        Id = id;
        this.value = value;
    }

    String value;
}
