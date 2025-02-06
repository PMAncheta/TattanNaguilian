/*
package com.example.tattannaguilian;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class supabase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Fetch data from Supabase
        fetchDataFromSupabase();
    }

    private void fetchDataFromSupabase() {
        // Use SupabaseClient.getClient() to create the Retrofit instance
        SupabaseService service = SupabaseClient.getClient().create(SupabaseService.class);

        // Call Supabase API to fetch data
        Call<List<YourModel>> call = service.getItems("*"); // Fetch all columns
        call.enqueue(new Callback<List<YourModel>>() {
            @Override
            public void onResponse(Call<List<YourModel>> call, Response<List<YourModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<YourModel> items = response.body();
                    // Log the data fetched from Supabase
                    for (YourModel item : items) {
                        Log.d("Supabase", "Item: " + item.getName() + " - " + item.getDescription());
                    }
                } else {
                    Log.e("Supabase", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<YourModel>> call, Throwable t) {
                Log.e("Supabase", "Failed to fetch data", t);
            }
        });
    }
}
*/