package ar.app.dto.section;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SectionResponse(
        Long id,
        String name,
        String observation
) {
}
