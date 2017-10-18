package me.sadraa.detoxiom;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewQuoteFragment extends Fragment {

    Button mButton;
    Button mButtonSave;
    Button mButtonIgonre;
    TextView quoteTV;
    TextView authorTV;
    BottomSheetBehavior mBottomSheetBehavior;
    MyApplication mApplication;
    QuoteDbModel quoteDbModel;
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

        authorTV = (TextView) getView().findViewById(R.id.authorText);
        mButton = (Button) getView().findViewById(R.id.fetchButton);
        mButtonSave = (Button) getView().findViewById(R.id.saveQuote);
        mButtonIgonre= (Button) getView().findViewById(R.id.ignoreQuote);
        quoteTV = (TextView) getView().findViewById(R.id.quoteText);
        View bottomSheet = getView().findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //Creating object from provider class to get retrofit service
                String api_token = ClientConfig.api_token;
                QuoteProvider QProvider = new QuoteProvider();
                QuoteClient QService = QProvider.getmQService();
            /* Call method and run it asyncornisly :) */
                Call<QuoteModel> call = QService.getQuote(api_token);
                call.enqueue(new Callback<QuoteModel>() {



                    @Override
                    public void onResponse(Call<QuoteModel> call, Response<QuoteModel> response) {
                         if(response.isSuccessful()){
                          //   Toast.makeText(getContext(),"shod",Toast.LENGTH_SHORT).show();
                             quoteTV.setText(response.body().getResult().getQuote());
                             authorTV.setText(response.body().getResult().getAuthor());
                             mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                         }else{

                             try {
                                 Toast.makeText(getContext(),response.errorBody().string(),Toast.LENGTH_SHORT).show();
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
                         }

                    }

                    @Override
                    public void onFailure(Call<QuoteModel> call, Throwable t) {
                        Toast.makeText(getContext(),"The request failed",Toast.LENGTH_SHORT).show();
                        t.printStackTrace();

                    }
                });
            }
        });

        //save quote in archive and minimize the bottomsheet
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                Toast.makeText(getContext(),"Saved!",Toast.LENGTH_SHORT).show();
                quoteDbModel = new QuoteDbModel();
                quoteDbModel.setAuthor(quoteTV.getText().toString());
                quoteDbModel.setQuote(authorTV.getText().toString());
                insertQuoteToDb(quoteDbModel);
            }
        });

        //igonre and don't save quote in archive
        mButtonIgonre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

    }

    public void insertQuoteToDb(final QuoteDbModel quoteDbModel){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                QuoteDb quoteDb = QuoteDb.getQuoteDb(getContext());
                quoteDb.quoteDao().insertOne(quoteDbModel);
            }
        };
        new Thread(runnable).start();
    }
}
