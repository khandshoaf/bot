package util;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import storage.AppStorage;
import storage.model.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AppRequest {
    protected OkHttpClient client;
    private AppStorage appStorage;

    public AppRequest() {
        this.appStorage = AppStorage.getInstance();
        this.client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public void request(Callback callback) {
        try {
            String url = callback.getUrl();
            Request.Builder builder = new Request.Builder();
            builder.url(url);
            Map<String, String> headers = callback.getHeader();
            if (headers != null) builder.headers(Headers.of(headers));
            Request request = builder.build();

            Response response = client.newCall(request).execute();
            String json = response.body().string();
            callback.execute(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public interface Callback {
        String getUrl();

        Map<String, String> getHeader();

        void execute(String data);
    }
}