package ma.taskmanagement.controller;

import lombok.RequiredArgsConstructor;
import ma.taskmanagement.dto.AuthRequest;
import ma.taskmanagement.dto.AuthResponse;
import ma.taskmanagement.dto.RegisterRequest;
import ma.taskmanagement.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}
