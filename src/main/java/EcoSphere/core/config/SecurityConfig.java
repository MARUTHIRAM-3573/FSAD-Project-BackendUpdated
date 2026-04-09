package EcoSphere.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import EcoSphere.auth.JwtFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            // ✅ NEW WAY (NO .and())
            .cors(cors -> {}) 

            .authorizeHttpRequests(auth -> auth

                // 🔓 PUBLIC APIs
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/lessons/**").permitAll()
                .requestMatchers("/api/progress/stats").permitAll()

                // 👨‍💼 ADMIN APIs
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/users/all").hasRole("ADMIN")
                .requestMatchers("/api/users/create").hasRole("ADMIN")

                // 👤 USER APIs
                .requestMatchers("/api/users/change-password").authenticated()
                .requestMatchers("/api/progress/**").authenticated()

                // 🔒 EVERYTHING ELSE
                .anyRequest().authenticated()
            )

            // 🔥 JWT FILTER
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}