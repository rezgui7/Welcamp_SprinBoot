package tn.esprit.welcamp.dto.chatGPTdto;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class ChatGPTClient {
    private static final String API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient httpClient;

    public ChatGPTClient(String apiKey) {
        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Authorization", "Bearer " + apiKey)
                            .build();
                    return chain.proceed(request);
                }).build();
    }

    public String complete(String prompt, int maxTokens) throws IOException {
        JSONObject json = null;
        try {
            json = new JSONObject()
                    .put("prompt", prompt)
                    .put("max_tokens", maxTokens);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        ResponseBody responseBody = response.body();
        return responseBody.string();
    }
}
