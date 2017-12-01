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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.sadraa.detoxiom.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedTimeFragment extends Fragment implements SavedTimeContract.View{
    @BindView(R.id.opened_times) TextView openedTimesTV;
    @BindView(R.id.instagramTV) TextView instagramTV;
    @BindView(R.id.twitterTV) TextView twitterTV;
    @BindView(R.id.telegramTV) TextView telegramTV;
    @BindView(R.id.instagramIV) ImageView instagramIV;
    private Unbinder unbinder;
    int openedTimeInt;
    @Inject
    SavedTimeContract.Presenter presenter;
    public SavedTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        DaggerSavedTimeComponent.builder()
                .savedTimeModule(new SavedTimeModule())
                .build().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_saved_time, container, false);
        unbinder = ButterKnife.bind(this,view);
        presenter.onViewAttached(this);
        presenter.subscribe();
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       setTextForViews();

        //I want add a fancy feature here! to just test how rxBinding library works
        //This probably Violate the MVP pattern but who cares man?
        RxView.clicks(instagramIV)
                .subscribe(aVoid ->
                        Toast.makeText(getContext(),"چیزی برای کلیک نداریم :(",Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.unsubscribe();
    }


    @Override
    public void setTextForViews() {
        openedTimeInt = presenter.LoadOpenedTimeFromInteractor();
        openedTimesTV.setText("دفعاتی که دیتاکسیوم را باز کرده اید" + ":\n" + openedTimeInt +"");
        instagramTV.setText(presenter.LoadFromInteractorRealTimeInSocialMedia(openedTimeInt,"instagram") + "\n دقیقه");
        telegramTV.setText(presenter.LoadFromInteractorRealTimeInSocialMedia(openedTimeInt,"telegram") + "\n دقیقه");
        twitterTV.setText(presenter.LoadFromInteractorRealTimeInSocialMedia(openedTimeInt,"twitter") + "\n دقیقه");

    }
}
