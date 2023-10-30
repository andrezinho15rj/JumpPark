package API;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("accessToken")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

}

