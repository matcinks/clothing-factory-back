package pl.clothingfactory.security.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.clothingfactory.user.UserBasicDetails;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class AuthenticationResponse {
    private String token;

    private UserBasicDetails userBasicDetails;
}
