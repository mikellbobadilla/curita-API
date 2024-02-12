package ar.app.dto.provider;

import lombok.Builder;

@Builder
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
