package me.sadraa.detoxiom;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewQuoteFragment extends Fragment {

    Button mButton;
    public NewQuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_quote, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButton = (Button) getView().findViewById(R.id.fetchButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // HashMap<String, String> api_token = new HashMap<>();
               // api_token.put("api_token", "01sadra@gmail.comysarbabi@gamil.comASDF8SD7GSA3457SNAF29B1062B58");

               // QuoteModel QModel = new QuoteModel();
                QuoteProvider QProvider = new QuoteProvider();
                QuoteClient QService = QProvider.getmQService();
               // Call<QuoteModel> call = QService.getQuote(ClientConfig.api_token);
                JSONObject josonBody = new JSONObject();
                try {
                    josonBody.put("api_token","01sadra@gmail.comysarbabi@gamil.comASDF8SD7GSA3457SNAF29B1062B58");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String api_key = josonBody.toString();


                Call<QuoteModel> call = QService.getQuote(api_key);
                call.enqueue(new Callback<QuoteModel>() {
                    @Override
                    public void onResponse(Call<QuoteModel> call, Response<QuoteModel> response) {
                         if(response.isSuccessful()){

                             Toast.makeText(getContext(),"shod",Toast.LENGTH_SHORT).show();

                         }else{
                             String code = String.valueOf(response.code());
                             Toast.makeText(getContext(),response.message() + code,Toast.LENGTH_SHORT).show();



                         }

                    }

                    @Override
                    public void onFailure(Call<QuoteModel> call, Throwable t) {
                        Toast.makeText(getContext(),"aslan nashod",Toast.LENGTH_SHORT).show();
                        t.printStackTrace();

                    }
                });
            }
        });

    }
}
