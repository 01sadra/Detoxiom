package me.sadraa.detoxiom;

import me.sadraa.detoxiom.utils.ClientConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sadra on 10/8/17.
 */

public class QuoteProvider {

    public QuoteClient mQService;
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
