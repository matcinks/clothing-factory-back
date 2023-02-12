package pl.clothingfactory.security.authentication;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.clothingfactory.user.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class RegisterRequest {
    @NotBlank(message = "Nowy użytkownik musi posiadać nazwe.")
    private String name;
    @NotBlank(message = "Nowy użytkownik musi posiadać hasło.")
    private String password;
    @NotBlank(message = "Nowy użytkownik musi posiadać role.")
    @Enumerated(EnumType.STRING)
    private Role role;
}
