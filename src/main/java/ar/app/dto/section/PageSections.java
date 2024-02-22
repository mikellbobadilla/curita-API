package ar.app.dto.section;

import lombok.Builder;

import java.util.List;

@Builder
public record PageSections(
        List<SectionResponse> content,
        Integer pageNumber,
        Integer paseSize,
        Integer totalPages,
        Long totalElements
) {
}
