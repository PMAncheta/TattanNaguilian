package com.example.tattannaguilian;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class SupabaseClient {
    private static final String BASE_URL = "https://your-project-url.supabase.co/";
    private static final String API_KEY = "your-anon-key";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(chain -> chain.proceed(
                            chain.request().newBuilder()
                                    .addHeader("apikey", API_KEY)
                                    .addHeader("Authorization", "Bearer " + API_KEY)
                                    .build()
                    ))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
