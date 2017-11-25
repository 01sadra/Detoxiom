package me.sadraa.detoxiom.features.time_save;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.sadraa.detoxiom.MyApplication;
import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.utils.SharedprefrenceProvider;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedTimeFragment extends Fragment {
    @BindView(R.id.opened_times) TextView openedTimesTV;
    @BindView(R.id.instagramTV) TextView instagramTV;
    @BindView(R.id.twitterTV) TextView twitterTV;
    @BindView(R.id.telegramTV) TextView telegramTV;
    @BindView(R.id.instagramIV) ImageView instagramIV;
    private Unbinder unbinder;
    int openedTimeInt;
    SharedprefrenceProvider sharedprefrenceProvider = MyApplication.getAppComponent().getSharedPrefrenceProvider();
    public SavedTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_saved_time, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //we call sharedprefrence for understanding how many time app was opened.
        openedTimeInt =sharedprefrenceProvider.loadOpenedTimes();
        openedTimesTV.setText("دفعاتی که دیتاکسیوم را باز کرده اید" + ":\n" + openedTimeInt +"");
        instagramTV.setText(realTimeInSocialMedia(openedTimeInt,"instagram") + "\n دقیقه");
        telegramTV.setText(realTimeInSocialMedia(openedTimeInt,"telegram") + "\n دقیقه");
        twitterTV.setText(realTimeInSocialMedia(openedTimeInt,"twitter") + "\n دقیقه");

        //I want add a fancy feature here! to just test how rxBinding library works
        RxView.clicks(instagramIV)
                .subscribe(aVoid ->
                        Toast.makeText(getContext(),"چیزی برای کلیک نداریم :(",Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public int realTimeInSocialMedia(int openedTime, String socialMedia){
        switch (socialMedia){
            case "twitter":
                return openedTime*5;
            case "instagram":
                return openedTime*6;
            case "telegram":
                return openedTime*4;
        }
        return openedTime;
    }

}