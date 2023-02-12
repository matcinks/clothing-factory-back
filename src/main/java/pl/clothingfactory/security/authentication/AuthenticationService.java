package pl.clothingfactory.security.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.clothingfactory.exception.ObjectNotFoundInDBException;
import pl.clothingfactory.security.JwtService;
import pl.clothingfactory.user.User;
import pl.clothingfactory.user.UserBasicDetails;
import pl.clothingfactory.user.UserRepository;

@Service
@RequiredArgsConstructor
class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        UserBasicDetails userBasicDetails = userRepository.getUserByName(user.getName());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userBasicDetails(userBasicDetails)
                .build();
    }

    AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword())
        );
        User user = userRepository.findByName(request.getName()).orElseThrow(() -> new ObjectNotFoundInDBException("W bazie danych nie znaleziono użytkownika o nazwie: " + request.getName()));
        String jwtToken = jwtService.generateToken(user);
        UserBasicDetails userBasicDetails = userRepository.getUserByName(user.getName());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userBasicDetails(userBasicDetails)
                .build();
    }
}
