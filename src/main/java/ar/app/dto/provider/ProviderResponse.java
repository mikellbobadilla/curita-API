package ar.app.dto.provider;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProviderResponse(
        Long id,
        String name,
        String address,
        String email,
        String contactProvider,
        String phone,
        String cuil,
        String startOperations,
        String observation
) {
}
