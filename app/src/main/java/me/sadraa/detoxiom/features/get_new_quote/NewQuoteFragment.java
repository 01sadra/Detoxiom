package me.sadraa.detoxiom.features.get_new_quote;

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

import java.util.Random;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.db.Models.QuoteDbModel;
import me.sadraa.detoxiom.db.QuoteDb;
import me.sadraa.detoxiom.features.MainActivity;
import me.sadraa.detoxiom.network.QuoteClient;
import me.sadraa.detoxiom.network.QuoteProvider;
import me.sadraa.detoxiom.network.models.QuoteModel;
import me.sadraa.detoxiom.utils.ClientConfig;
import retrofit2.Call;

public class NewQuoteFragment extends Fragment {
    int chanceFromPrefrence,firstAttemptCounter;
    BottomSheetBehavior mBottomSheetBehavior;
    QuoteDbModel quoteDbModel;
    Vibrator vibrator;
    private Unbinder unbinder;

    @BindView(R.id.saveQuote) Button mButtonSave;
    @BindView(R.id.ignoreQuote) Button mButtonIgonre;
    @BindView(R.id.quoteText) TextView quoteTV;
    @BindView(R.id.authorText) TextView authorTV;
    @BindView(R.id.counter_show) TextView chanceCounterTV;
    @BindView(R.id.bottom_sheet) View bottomSheet;
    @BindView(R.id.animation_refresh) LottieAnimationView lAnimation;

    public NewQuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_new_quote, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        chanceFromPrefrence = MainActivity.loadBadgeCount(getContext());
        firstAttemptCounter = 0;
        //check if there is any chance and show the user how many time s/he can try.
        if(chanceFromPrefrence>0){
            chanceCounterTV.setText(" فرصت‌های باقی مانده: "+"\n"+chanceFromPrefrence);
        }else {
            chanceCounterTV.setText("فرصت های شما تمام شده :(");
        }

         //Adding an animation as a button with lottie
        lAnimation.setAnimation("refresh.json");
        //shitty setting for bad animation
        lAnimation.setProgress(1);
        //set listener for animation states. this methods call when animatin palys
        lAnimation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //If you have chance new quote get triggered and you will have new quote
                //with little vibrate and a lot of green color
                if(makeChance()){
                    view.setBackgroundColor(getResources().getColor(R.color.primary_dark));
                    vibrator.vibrate(500);
                //using rx java for calling the server async
                    //rxJava is a comprehensive topic and I can't explain what happened here
                    //but for more info you can watch this quick video tutorial: https://www.youtube.com/watch?v=7IEPrihz1-E
                  getQuoteObservable()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(getQuoteObserver());

                }else{
                    view.setBackgroundColor(getResources().getColor(R.color.about_youtube_color));
                    Toast.makeText(getContext(),"یه بار دیگه امتحان کن",Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onAnimationEnd(Animator animation) {
                //After animation finished set background color to white
                view.setBackgroundColor(getResources().getColor(R.color.icons));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //set onClick listener for animation. If internet be connected
    //and bottomsheet be collapsed  and we have chance to try animation will play
    @OnClick(R.id.animation_refresh)
    public void getRefresh(){
        if(!isInternetConnected()) {
            Toast.makeText(getContext(), "به اینترنت متصل نیستید :(", Toast.LENGTH_SHORT).show();
            return;
        }
        if (lAnimation.isAnimating() || mBottomSheetBehavior.getState()==BottomSheetBehavior.STATE_EXPANDED){
            return;
        }

        if(chanceFromPrefrence > 0) {
            lAnimation.playAnimation();
            // minus 1 from badge counter and save it in prefrence
            MainActivity.saveBadgeCounter(getContext(), chanceFromPrefrence - 1);
            //Load chances from prefrence again
            chanceFromPrefrence = MainActivity.loadBadgeCount(getContext());

            //if chances is more than 0 show how many chance remain
            if (chanceFromPrefrence > 0) {
                chanceCounterTV.setText(" فرصت‌های باقی مانده: " + "\n" + chanceFromPrefrence);
                //If there is no chance then tell the user the truth:)
            } else {
                chanceCounterTV.setText("فرصت های شما تمام شده :(");
            }

        }
    }
    //save quote in archive and minimize the bottomsheet
    @OnClick(R.id.saveQuote)
    public void saveQuote(){
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
   //just ignore the quote and minimize the bottomsheet
    @OnClick(R.id.ignoreQuote)
    public void ignoreQuote(){
       mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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

    //this function just get a random number between  1 and 100 and if the number
    //divisible by 3 it return true.
    public boolean makeChance(){
        Random r = new Random();
        int mRandomNumber = r.nextInt(100) + 1;
        //Just make sure the first attempt is successful. it is necessary for gamifation
        if(MainActivity.loadOpenedTimes(getContext())<3 && firstAttemptCounter==0){
            firstAttemptCounter = 1;
            return true;
        }
        return mRandomNumber%3==0;
    }
    //check if internet is connected or not
     public boolean isInternetConnected(){
         ConnectivityManager connectivityManager
                 = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
         return activeNetworkInfo != null && activeNetworkInfo.isConnected();
     }
//make call to the server with retrofit interface and return an observer object
    public Observable<QuoteModel> getQuoteObservable(){
        //Creating object from provider class to get retrofit service
        String api_token = ClientConfig.api_token;
        QuoteProvider QProvider = new QuoteProvider();
        QuoteClient QService = QProvider.getmQService();
                    /* Call method and run it asynchronously :) */
        final Call<QuoteModel> call = QService.getQuote(api_token);

        return Observable.fromCallable(new Callable<QuoteModel>() {
            @Override
            public QuoteModel call() throws Exception {
                return call.execute().body();
            }
        });
    }
    //return an ovserver on quote and set the views in on next
    public Observer<QuoteModel> getQuoteObserver(){
        return new Observer<QuoteModel>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(QuoteModel quoteModel) {
                quoteTV.setText(quoteModel.getResult().getQuote());
                authorTV.setText(quoteModel.getResult().getAuthor());
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
