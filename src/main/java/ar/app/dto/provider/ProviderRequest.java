package ar.app.dto.provider;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ProviderRequest(
        @NotBlank String name,
        @NotBlank String address,
        String email,
        String contactProvider,
        @NotBlank String phone,
        @NotBlank String cuil,
        @NotNull Date startOperations,
        String observation
) {
}
