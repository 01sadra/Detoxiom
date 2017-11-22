package me.sadraa.detoxiom.di;

import dagger.Module;
import dagger.Provides;
import me.sadraa.detoxiom.network.QuoteClient;
import me.sadraa.detoxiom.utils.ClientConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sadra on 11/22/17.
 */
@Module
public class NetworkModule {
    @MyAppComponentScope
    @Provides
    public QuoteClient quoteClient(Retrofit retrofit){
       return retrofit.create(QuoteClient.class);
    }

    @MyAppComponentScope
    @Provides
    public Retrofit retrofit(OkHttpClient httpClient, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl(ClientConfig.BASE_URL)
                .client(httpClient)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @MyAppComponentScope
    @Provides
    public OkHttpClient okHttpClient(){
        return new OkHttpClient();
    }

    @MyAppComponentScope
    @Provides
    public GsonConverterFactory gsonConverterFactory(){
        return GsonConverterFactory.create();
    }
}
