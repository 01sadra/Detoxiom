package me.sadraa.detoxiom.features.teaching;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.features.about_app.AboutActivity;
import me.sadraa.detoxiom.features.about_app.IntroActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeachingFragment extends Fragment implements TeachingContract.View {
    private Unbinder unbinder;

    @Inject
    TeachingContract.Presenter presenter;

    public TeachingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        DaggerTeachingPresenterComponent.builder()
                .teachingPresenterModule(new TeachingPresenterModule())
                .build()
                .inject(this);
        presenter.onViewAttached(this);
        presenter.subscribe();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
        unbinder.unbind();
    }


    @OnClick(R.id.about_call)
    public void aboutButtonClicked(){
        presenter.aboutAcitivtyButtonClicked();
    }
    @OnClick(R.id.intro_call)
    public void introButtonClicked(){
        presenter.IntroActivityButtonClicked();
    }


    @Override
    public void callIntroActivity() {
        Intent intent = new Intent(getActivity(), IntroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Override
    public void callAboutActivity() {
        Intent intent = new Intent(getActivity(), AboutActivity.class);
        startActivity(intent);
    }
}
