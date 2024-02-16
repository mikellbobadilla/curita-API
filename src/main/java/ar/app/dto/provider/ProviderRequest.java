package ar.app.dto.provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
