package EcoSphere.users;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 🔥 Constructor Injection
    public UserController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Existing profile API
    @GetMapping("/profile")
    public String getProfile() {
        return "This is a protected API";
    }

    // ✅ ADMIN: Get all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 🔥 ADMIN: Create user
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {

        // 🔐 Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // 🔥 USER: Change password
    @PutMapping("/change-password")
    public String changePassword(
            @RequestHeader("User-Email") String email,
            @RequestBody String newPassword
    ) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return "Password updated successfully";
    }
}