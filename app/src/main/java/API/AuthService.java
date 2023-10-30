package API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/email/password")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);
}

