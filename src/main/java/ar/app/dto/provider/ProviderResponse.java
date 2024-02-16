package ar.app.dto.provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderResponse {
        Long id;
        String name;
        String address;
        String email;
        String contactProvider;
        String phone;
        String cuil;
        String startOperations;
        String observation;
}
