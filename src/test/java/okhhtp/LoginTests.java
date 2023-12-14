package okhhtp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import okhttp3.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void loginPositive() throws IOException {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("oleg@sher.biz")
                .password("123456")
                .build();
        RequestBody requestBody =RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        } else {
            System.out.println(response.code());
        }

    }
}
