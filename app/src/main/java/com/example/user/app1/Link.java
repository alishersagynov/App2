package com.example.user.app1;
import com.example.user.app1.model.ForecastResponse;

import java.util.Map;

 import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Link {
    @GET("{latitude},{longitude}")
   Call<ForecastResponse> forecast(@Path("latitude") Double latitude,
                                   @Path("longitude") Double longitude);
}
