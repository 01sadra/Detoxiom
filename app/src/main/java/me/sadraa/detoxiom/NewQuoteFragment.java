package me.sadraa.detoxiom;


import android.animation.Animator;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.io.IOException;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewQuoteFragment extends Fragment {
    Button mButton, mButtonSave, mButtonIgonre;
    TextView quoteTV;
    TextView authorTV;
    BottomSheetBehavior mBottomSheetBehavior;
    QuoteDbModel quoteDbModel;
    Vibrator vibrator;
    LottieAnimationView lAnimation;
    boolean RandomChance ;
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
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    //To do : rewrite this shit with butterkinfe
        authorTV = (TextView) getView().findViewById(R.id.authorText);
        mButtonSave = (Button) getView().findViewById(R.id.saveQuote);
        mButtonIgonre= (Button) getView().findViewById(R.id.ignoreQuote);
        quoteTV = (TextView) getView().findViewById(R.id.quoteText);
        View bottomSheet = getView().findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        lAnimation = (LottieAnimationView) getView().findViewById(R.id.animation_refresh);
         vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        lAnimation.setAnimation("refresh.json");
        lAnimation.setProgress(1);
        lAnimation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                RandomChance = makeChance();
                if(RandomChance){
                    view.setBackgroundColor(getResources().getColor(R.color.primary_dark));
                    vibrator.vibrate(500);
                    //Creating object from provider class to get retrofit service
                    String api_token = ClientConfig.api_token;
                    QuoteProvider QProvider = new QuoteProvider();
                    QuoteClient QService = QProvider.getmQService();
            /* Call method and run it asynchronously :) */
                    Call<QuoteModel> call = QService.getQuote(api_token);
                    call.enqueue(new Callback<QuoteModel>() {
                        @Override
                        public void onResponse(Call<QuoteModel> call, Response<QuoteModel> response) {
                            if(response.isSuccessful()){
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
                            Toast.makeText(getContext(),"مشکل در برقراری اتصال به اینترنت!",Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });
                }else{
                  view.setBackgroundColor(getResources().getColor(R.color.about_youtube_color));
                  Toast.makeText(getContext(),"یه بار دیگه امتحان کن",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setBackgroundColor(getResources().getColor(R.color.icons));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        lAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInternetConnected()){
                    if (!lAnimation.isAnimating() && mBottomSheetBehavior.getState()==BottomSheetBehavior.STATE_COLLAPSED){
                        lAnimation.playAnimation();
                    }
                }else {
                    Toast.makeText(getContext(),"به اینترنت متصل نیستید :(",Toast.LENGTH_SHORT).show();

                }

            }
        });

        //save quote in archive and minimize the bottomsheet
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                Toast.makeText(getContext(),"ذخیره شد‌",Toast.LENGTH_SHORT).show();
                //Create a model from database and set value for that
                quoteDbModel = new QuoteDbModel();
                if(quoteTV!=null||authorTV!=null){
                    quoteDbModel.setAuthor(authorTV.getText().toString());
                    quoteDbModel.setQuote(quoteTV.getText().toString());
                    insertQuoteToDb(quoteDbModel);
                }

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
     //Creating a new thread for Runnig Room Query.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                QuoteDb quoteDb = QuoteDb.getQuoteDb(getContext());
                quoteDb.quoteDao().insertOne(quoteDbModel);
            }
        };
        new Thread(runnable).start();
    }

    public boolean makeChance(){
        Random r = new Random();
        int mRandomNumber = r.nextInt(100) + 1;
        return mRandomNumber%3==0;
    }
     public boolean isInternetConnected(){
         ConnectivityManager connectivityManager
                 = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
         return activeNetworkInfo != null && activeNetworkInfo.isConnected();
     }

}
