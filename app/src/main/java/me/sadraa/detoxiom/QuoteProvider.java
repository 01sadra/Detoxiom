package me.sadraa.detoxiom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sadra on 10/8/17.
 */

public class QuoteProvider {

    private QuoteClient mQService;
    public QuoteProvider(){
        OkHttpClient httpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ClientConfig.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mQService =retrofit.create(QuoteClient.class);
    }
    //A method for returning retrofit service
    public QuoteClient getmQService() {
        return mQService;
    }
}
