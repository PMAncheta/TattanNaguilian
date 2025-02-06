package com.example.tattannaguilian;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

// Replace "YourModel" with your data model class
public interface SupabaseService {
    @GET("rest/v1/your_table_name")
    Call<List<YourModel>> getItems(
            @Query("select") String select // Use "select=*" to fetch all columns
    );

    @POST("rest/v1/your_table_name")
    Call<Void> insertItem(@Body YourModel item);
}
