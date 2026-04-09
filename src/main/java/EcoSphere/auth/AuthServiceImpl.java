package EcoSphere.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import EcoSphere.core.exception.CustomException;
import EcoSphere.core.response.ApiResponse;
import EcoSphere.users.Role;
import EcoSphere.users.User;
import EcoSphere.users.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;   // ✅ MAKE FINAL

    public AuthServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ApiResponse<AuthResponseDTO> register(AuthRequestDTO request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        AuthResponseDTO dto = new AuthResponseDTO("User registered successfully");

        return new ApiResponse<>(true, "SUCCESS", dto);
    }

    @Override
    public ApiResponse<AuthResponseDTO> login(AuthRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException("Invalid password");
        }

        // ✅ GENERATE TOKEN
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        AuthResponseDTO dto = new AuthResponseDTO(token);

        return new ApiResponse<>(true, "SUCCESS", dto);
    }
}