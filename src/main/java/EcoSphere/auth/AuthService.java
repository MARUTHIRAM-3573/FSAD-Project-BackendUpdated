package EcoSphere.auth;

import EcoSphere.core.response.ApiResponse;

public interface AuthService {

    ApiResponse<AuthResponseDTO> register(AuthRequestDTO request);

    ApiResponse<AuthResponseDTO> login(AuthRequestDTO request);  
}