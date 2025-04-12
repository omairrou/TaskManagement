package ma.taskmanagement.service;

import lombok.RequiredArgsConstructor;
import ma.taskmanagement.dto.AuthRequest;
import ma.taskmanagement.dto.AuthResponse;
import ma.taskmanagement.dto.RegisterRequest;
import ma.taskmanagement.model.User;
import ma.taskmanagement.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request){
        var user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generatetoken((UserDetails) user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse login(AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generatetoken((UserDetails) user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
