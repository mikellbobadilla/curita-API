package ar.app.dto.provider;

import lombok.Builder;

import java.util.List;

@Builder
public record PageProviders(
        List<ProviderResponse> content,
        Integer pageNumber,
        Integer pageSize,
        Integer totalPages,
        Long totalElements
) {
}
