package EcoSphere.auth;

import EcoSphere.core.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ApiResponse<AuthResponseDTO> register(@RequestBody AuthRequestDTO request) {
        return authService.register(request);
    }
    @PostMapping("/login")
    public ApiResponse<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        return authService.login(request);
    }
}