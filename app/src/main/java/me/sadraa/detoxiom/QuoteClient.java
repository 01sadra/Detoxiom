package me.sadraa.detoxiom;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by sadra on 10/8/17.
 */

public interface QuoteClient {
    @POST("random_quote")
    Call<QuoteModel> getQuote(@Body String api_key);

}
