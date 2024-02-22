package ar.app.dto.section;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record SectionRequest(
        @Length(min = 3, max = 25)
        @Pattern(regexp = "^[\\p{L}\\s\\p{P}\\p{M}!]+$", message = "Name must be only letters")
        String name,
        @Pattern(regexp = "^[\\w\\s.,¡!¿?áéíóúÁÉÍÓÚüÜñÑ-]+$", message = "Invalid input. Please only use alphanumeric characters, spaces, punctuation marks, and accents")
        String observation
) {
}
