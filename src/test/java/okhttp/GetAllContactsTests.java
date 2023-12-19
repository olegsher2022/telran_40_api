package okhttp;

import okhttp3.Request;
import okhttp3.RequestBody;

public class GetAllContactsTests {

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO),JSON);
    Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .get()
                .build();
}
