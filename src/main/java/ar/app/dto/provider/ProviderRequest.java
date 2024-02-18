package ar.app.dto.provider;

import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


public record ProviderRequest(
        @Length(min = 3, max = 25, message = "Name must be between 3 and 25 characters")
        @Pattern(regexp = "^\\p{L}+$", message = "Name must be only letters")
        String name,
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\.,#-]+$", message = "The provided address is not valid. Please ensure it is in the correct format.")
        String address,
        String email,
        String contactProvider,
        @Pattern(regexp = "^\\+?\\d{0,3}\\s?\\d{0,3}\\s?\\d{9}$", message = "Please enter a valid phone number. Country code is optional.")
        String phone,
        @Pattern(regexp = "^(20|23|24|27|30)-\\d{8}-\\d$", message = "Please enter a valid CUIL or CUIT number in the format: XX-XXXXXXXX-X.")
        String cuil,
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Please enter a valid date in the format YYYY-MM-DD.")
        String startOperations,
        @Pattern(regexp = "^\\p{L}+$", message = "Name must be only letters")
        String observation
) {
}
