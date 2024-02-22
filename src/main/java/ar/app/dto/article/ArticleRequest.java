package ar.app.dto.article;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public record ArticleRequest(
        @Length(min = 3, max = 40, message = "Name must be between 3 and 25 characters")
        @Pattern(regexp = "^[\\p{L}\\s\\p{P}\\p{M}!]+$", message = "Name must be only letters")
        String name,
        @Positive(message = "Stock must be greater than 0")
        @Digits(integer = 10, fraction = 0, message = "Stock must be a number.")
        Integer stock,
        @DecimalMin(value = "0", inclusive = false, message = "Value must be greater than 0.00")
        String price,
        @DecimalMin(value = "0", inclusive = false, message = "Value must be greater than 0.00")
        String cost,
        @Length(min = 20, max = 20, message = "Barcode must be 20 characters long")
        String barcode,
        Long providerId,
        Long sectionId,
        @Length(min = 3, max = 50, message = "Observation must be between 3 and 50 characters")
        String observation
) {
}
